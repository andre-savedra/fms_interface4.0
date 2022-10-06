package com.spring.fms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.fms.config.InterfaceConfig;
import com.spring.fms.managerProduction.GcodeWriter;
import com.spring.fms.managerProduction.Threads;
import com.spring.fms.model.Machinery;
import com.spring.fms.model.ManutVariables;
import com.spring.fms.model.Message;
import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;
import com.spring.fms.model.ProcessOrder;
import com.spring.fms.model.StepOrder;
import com.spring.fms.model.User;
import com.spring.fms.service.FmsMachineryService;
import com.spring.fms.service.FmsOrderService;
import com.spring.fms.service.FmsProcessOrderService;
import com.spring.fms.service.FmsStepOrderService;
import com.spring.fms.service.ManutVariablesService;
import com.spring.fms.service.OpcUaVarsMillingService;
import com.spring.fms.service.OpcUaVarsRobotService;
import com.spring.fms.service.OpcUaVarsTurnService;
import com.spring.fms.service.SupervisoryDataExchangeService;
import com.spring.fms.utils.EmailSender;
import com.spring.fms.utils.MaintenanceRequester;

@CrossOrigin
@Controller
public class FmsOrderController {

	private static Threads threadMakeProduction;
	private static Threads threadSender;
	private static Threads threadMaintenance;

	private static List<ManutVariables> manutVariablesList;

	MaintenanceRequester maintenanceRequester;

	private static List<Machinery> machineriesList;
	private static Machinery mTurn;
	private static Machinery mMill;
	private static Machinery mRobot;

	private static List<Order> totalOrders;
	private static boolean usingTotalOrders;

	private static List<Order> ordersInProduction;

	// private static OpcUaVarsTurn uaVarTurn;
	// private static OpcUaVarsMilling uaVarMill;
	// private static OpcUaVarsRobot uaVarRobot;

	private static boolean started = false;

	private GcodeWriter gcodeWriter;

	private List<Message> arrayMessages;

	EmailSender emailSender;

	private final int SUPERVISORY_TURN = 1;
	private final int SUPERVISORY_MILL = 2;
	private final int SUPERVISORY_FLEX_TURN = 3;
	private final int SUPERVISORY_FLEX_MILL = 4;

	@Autowired
	FmsOrderService orderService;

	@Autowired
	FmsProcessOrderService processOrderService;

	@Autowired
	FmsStepOrderService stepOrderService;

	@Autowired
	OpcUaVarsMillingService uaMillingService;

	@Autowired
	OpcUaVarsTurnService uaTurnService;

	@Autowired
	OpcUaVarsRobotService uaRobotService;

	@Autowired
	SupervisoryDataExchangeService supervisoryService;

	@Autowired
	FmsMachineryService machineryService;

	@Autowired
	ManutVariablesService manutVariablesService;

	private boolean saveTheOrder(Order orderToSave) {

		try {

			if (saveStepOrder(orderToSave.getProcess().getSteps()) == false) {
				return false;
			}

			if (saveProcessOrder(orderToSave.getProcess()) == false) {
				return false;
			}

			orderService.saveOrder(orderToSave);

			return true;
		} catch (Exception e) {
			System.out.println("ERRO AO SALVAR A ORDEM");
			return false;
		}
	}

	private boolean saveProcessOrder(ProcessOrder process) {

		try {
			processOrderService.saveProcessOrder(process);
			return true;
		} catch (Exception e) {
			System.out.println("ERRO AO SALVAR O PROCESSO");
			return false;
		}
	}

	private boolean saveStepOrder(List<StepOrder> steps) {

		try {

			for (int i = 0; i < steps.size(); i++) {

				/*
				 * System.out.print("--------------\n\n");
				 * System.out.println(steps.get(i).getName()); System.out.println("Machine id:"
				 * + steps.get(i).getMachine().getId());
				 * System.out.println(steps.get(i).isConcluded());
				 * System.out.println(steps.get(i).getGcode());
				 * 
				 * System.out.print("--------------\n\n");
				 */

				stepOrderService.saveStepOrder(steps.get(i));
			}
			return true;

		} catch (Exception e) {
			System.out.println("ERRO AO SALVAR OS STEPS!");
			return false;
		}
	}

	/************************** MANAGER PRODUCTION ************************/

	private void clearMachinery(Machinery myMachinery) {
		myMachinery.setJobName("");
		myMachinery.setJobAccepted(false);
		myMachinery.setJobEnded(false);
		myMachinery.setNotMachining(false);
		// myMachinery.setMachineReady(false);
		myMachinery.setPermissionToStart(false);
		myMachinery.setOrderId(0L);
		myMachinery.setOrderSubIndex(0);
		myMachinery.setStepId(0);
		myMachinery.setHasJob(false);
		myMachinery.setFlex(false);
		// myMachinery.setInfo("");
	}

	private boolean jobSetter(Machinery theMachinery, Order theOrder, int theStepId) {
		boolean status = false;

		if ((theMachinery.getMachine().getName().equals(mTurn.getMachine().getName()))
				|| (theMachinery.getMachine().getName().equals(mMill.getMachine().getName()))) {

			if (theOrder.getProcess().getSteps().get(theStepId).getGcode() != null) {

				// clear important values
				clearMachinery(theMachinery);

				theMachinery.setHasJob(true);
				theMachinery.setJobName(theOrder.getProcess().getSteps().get(theStepId).getName());
				theMachinery.setOrderId(theOrder.getId());
				theMachinery.setStepId(theStepId);
				theMachinery.setInfo(theOrder.getType().getType());

				if (theOrder.getUnits() > 1) {
					theMachinery.setOrderSubIndex(theOrder.getUnitsProduced() + 1);
				} else {
					theMachinery.setOrderSubIndex(0);
				}

				if (theOrder.getType().getType().equals("flex")) {
					theMachinery.setFlex(true);
					System.out.println("É FLEXXXXXXX");
				} else {
					theMachinery.setFlex(false);
				}

				if (gcodeWriter.createFile(theMachinery.getFilename(), theMachinery.getPath(),
						theOrder.getProcess().getSteps().get(theStepId).getGcode())) {

					theMachinery.setPermissionToStart(true);
					machineryService.saveMachinery(theMachinery);
					System.out.println("ARQUIVO CRIADO COM SUCESSO DO TORNO!");
					status = true;
				} else {
					theMachinery.setPermissionToStart(false);
					machineryService.saveMachinery(theMachinery);
					System.out.println("FALHA CRIACAO TORNO");
				}

				System.out.println("------------------------------");
				System.out.println("ORDEM ATUAL CARREGADA E: ");
				System.out.println(theOrder.getId() + ", " + theOrder.getOrdername());
				System.out.println("------------------------------");

			}

		}

		return status;
	}

	// function to manage production, with threads inside
	private void managerProduction() {

		// thread to manage production factory
		threadMakeProduction = new Threads(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadMakeProduction.isAllDone()) {
						return;
					}

					// refresh total Orders
					totalOrders = getOrdersNotProduced();

					/*
					 * for (Order t : totalOrders) { System.out.println("----------------------");
					 * System.out.println("Order Id:" + t.getId()); System.out.println("Order Name:"
					 * + t.getOrdername()); }
					 */

					// refresh status of variables between supervisory and MES
					// uaVarTurn = uaTurnService.findTurnLastVar();
					// uaVarMill = uaMillingService.findMillingLastVar();
					// uaVarRobot = uaRobotService.findRobotLastVar();

					// refresh machinery status
					machineriesList = machineryService.findMachineryAll();

					// initialized, has machineries and orders
					if ((started) && (machineriesList.size() > 0) && (totalOrders.size() > 0)) {

						// logical to each machinery
						for (Machinery myMachinery : machineriesList) {

							// if machinery is enabled
							if (myMachinery.isEnabled()) {
								// if machine enabled, has not a job set and is ready
								if ((myMachinery.isEnabled()) && (myMachinery.isHasJob() == false)
										&& (myMachinery.isMachineReady())
										&& (myMachinery.isNotMachining() && (myMachinery.isJobEnded() == false))) {
									boolean jobSet = false;

									// if has some order in production
									if (ordersInProduction.size() > 0) {
										// for all orders in production, we compare the status
										for (Order orderInProd : ordersInProduction) {
											List<StepOrder> steps = orderInProd.getProcess().getSteps();

											// if job were set, so we can't continue
											if (jobSet == true) {
												break;
											}

											// for all steps of this order
											for (int step = 0; step < steps.size(); step++) {
												// if not concluded, and the machine match:
												if ((steps.get(step).isConcluded() == false)
														&& (steps.get(step).getMachine().getName().equalsIgnoreCase(
																myMachinery.getMachine().getName()))) {
													// steJob
													jobSetter(myMachinery, orderInProd, step);
													jobSet = true;
												}
												// if not concluded and not matched, so we can
												// conclude that cannot continue before finish the first step
												else if (steps.get(step).isConcluded() == false) {
													break; // break this order, but continue finding another
												}
											}
										}
									} // end if(ordersInProduction.size() > 0)

									// orders in production must be set or could not find a job for one machinery
									if ((ordersInProduction.size() == 0) || (jobSet == false)) {
										usingTotalOrders = true;
										// for all orders not produced, we compare the status
										for (Order order : totalOrders) {
											List<StepOrder> steps = order.getProcess().getSteps();

											// if job were set, so we can't continue
											if (jobSet == true) {
												break;
											}

											// just if not manufacturing to avoid repetitive production
											if (order.isManufacturing() == false) {
												// for all steps of this order
												for (int step = 0; step < steps.size(); step++) {
													// if not concluded, and the machine match:
													if ((steps.get(step).isConcluded() == false)
															&& (steps.get(step).getMachine().getName().equalsIgnoreCase(
																	myMachinery.getMachine().getName()))) {

														// set that order now, can't be called again
														order.setManufacturing(true);
														saveTheOrder(order);

														// add this order to list of orders in production:
														ordersInProduction.add(order);
														System.out.println(
																"ADICIONADO NAS ORDENS EM PROD.: " + order.getId());

														// set Job
														jobSetter(myMachinery, order, step);
														jobSet = true;
													}
													// if not concluded and not matched, so we can
													// conclude that cannot continue before finish the first step
													else if (steps.get(step).isConcluded() == false) {
														break; // break this order, but continue finding another
													}
												} // for of steps
											} // end of is manufacturing
										}
										usingTotalOrders = false;
									} // end if ( (ordersInProduction.size() == 0) || (jobSet == false) )

								} // end if((myMachinery.isEnabled()) && (myMachinery.isHasJob() == false) &&
									// (myMachinery.isMachineReady()) )
								else {

									// if machinery accepted job
									if ((myMachinery.isEnabled()) && (myMachinery.isHasJob() == true)
											&& (myMachinery.isJobAccepted()) && (myMachinery.isNotMachining())) {
										myMachinery.setPermissionToStart(false);
										machineryService.saveMachinery(myMachinery);
									}

								}

								/********* MANAGE END OF PRODUCTION *********/

								// if machinery enabled, already have job and accepted one
								if ((myMachinery.isEnabled()) && (myMachinery.isHasJob())
										&& (myMachinery.isJobEnded())) {
									System.out.println("DETECTOU FIM DE PRODUCAO");

									int orderEndedIndex = 0;

									for (Order orderEnded : ordersInProduction) {

										// were found the order finished:
										if (orderEnded.getId().equals(myMachinery.getOrderId())) {

											System.out.println("FIM DA ORDEM DETECTADO");

											// refreshed orders and database
											orderEnded.getProcess().getSteps().get(myMachinery.getStepId())
													.setConcluded(true);

											// so this is the last step of that process:
											if (orderEnded.getProcess().getSteps()
													.size() == (myMachinery.getStepId() + 1)) {
												// count one more part
												orderEnded.increaseUnits();

												// now we have to check if the amount of parts to produce is ok
												if (orderEnded.getUnits() >= orderEnded.getUnitsProduced()) {

													// FINISH THIS ORDER, AMOUNT PRODUCED!
													orderEnded.getProcess().setConcluded(true);
													orderEnded.setProduced(true);
													orderEnded.setManufacturing(false);
													orderEnded.setOutputDate(LocalDateTime.now());

													// save order
													saveTheOrder(orderEnded);
													// remove from list of production
													ordersInProduction.remove(orderEndedIndex);
													System.out.println("REMOVIDA ORDEM " + orderEnded.getId()
															+ ", DAS ORDENS EM PROD.");

													// restart machinery
													myMachinery.resetMachinery();
													myMachinery.setJobEnded(false);
													// save machinery
													machineryService.saveMachinery(myMachinery);

													// send message to client
													User u = orderEnded.getUser();
													String msg = u.getName() + ", sua peça com nome: \'"
															+ orderEnded.getOrdername() + "\' e número de ordem: "
															+ orderEnded.getId().toString()
															+ ", acabou de ficar pronta! =) ";

													Message message = new Message(u.getName(), u.getPhone().toString(),
															msg);
													arrayMessages.add(message);

												}
												// last step was done, but amount of parts not reached,
												// so we have to reset and continue with this order
												else {

													// FINISH JUST ONE SEGMENT OF THIS ORDER, so, restart!!!
													orderEnded.getProcess().resetStepsProcess();

													// just in case....
													orderEnded.getProcess().setConcluded(false);
													orderEnded.setProduced(false);
													orderEnded.setManufacturing(false);

												}
											} // end of last step

											// if was the last step or it's not, the
											// commands above is the same:

											// refresh order in database
											saveTheOrder(orderEnded);

											for (int j = 0; j < orderEnded.getProcess().getSteps().size(); j++) {
												System.out.println("orderEnded step" + j + ": "
														+ orderEnded.getProcess().getSteps().get(j).isConcluded());
											}

											// restart machinery
											myMachinery.resetMachinery();
											// save machinery
											machineryService.saveMachinery(myMachinery);

											break; // can break because we found out the order ended
										} else {
											System.out.println("ESSA NAO E A ORDEM FINALIZADA, POIS ORDEM ESCANEADA E:"
													+ orderEnded.getId().toString() + ", E A FINALIZADA E: "
													+ myMachinery.getOrderId().toString());

										}

										orderEndedIndex++;
									} // for order ended

								} // machinery ended job

							} // if machinery is enabled

						} // end for of all machineries
					} // started, machinery exists, etc...

					System.out.println("\n Ordens em prod:");
					for (Order orderP : ordersInProduction) {
						System.out.println("ID: " + orderP.getId() + ", nome: " + orderP.getOrdername());
					}
					System.out.println("\n FIM Ordens em prod:");

					/*
					 * // if initialized, have machineries and orders to produce if ((started) &&
					 * (machineriesList.size() > 0) && (ordersInProduction.size() > 0)) { // check
					 * all orders in production for (Order ordersManufacturing : ordersInProduction)
					 * { List<StepOrder> steps = ordersManufacturing.getProcess().getSteps(); int
					 * numberOfSteps = steps.size(); int counterStepsConcluded = 0;
					 * 
					 * // check all steps of this order for (StepOrder st : steps) { if
					 * (st.isConcluded()) { counterStepsConcluded++; } else { counterStepsConcluded
					 * = 0; break; } }
					 * 
					 * // so all steps were concluded if (counterStepsConcluded == numberOfSteps) {
					 * // so the process is concluded
					 * ordersManufacturing.getProcess().setConcluded(true);
					 * 
					 * }
					 * 
					 * }
					 * 
					 * }
					 */

					try {
						threadMakeProduction.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // while(true)
			} // public void run()
		}); // threadProduction

		threadSender = new Threads(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadSender.isAllDone()) {
						return;
					}

					if (started) {
						if (arrayMessages.size() > 0) {
							System.out.println("Enviando mensagem para " + arrayMessages.get(0).getName());
							// send
							emailSender.sendMail(arrayMessages.get(0).getChannel(), arrayMessages.get(0).getMessage());
							// remove from list
							arrayMessages.remove(0);
						}
					}

					try {
						threadSender.sleep(10000);
					} catch (InterruptedException e) {
						System.out.println("EXCEPTION SENDER!");
						e.printStackTrace();
					}
				} // while(true)
			} // public void run()
		}); // threadProduction

		threadMaintenance = new Threads(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadMaintenance.isAllDone()) {
						return;
					}

					if (started) {

						manutVariablesList = manutVariablesService.findManutAll();

						for (ManutVariables manutVariable : manutVariablesList) {

							boolean status = false;

							if (!manutVariable.isRequested())
								status = maintenanceRequester.check(
										"Bearer Euj-YOboI5f2PTUTwPLxnBZQebwE0b0H2DZuQEUq3TCire7vim7-I6NbATzuMvv1xhPR0XslbsSueYEiIVY5FupO1n9LCtmLxQjvwk3ZLPdRQc-oeo-Gjo1RZFBzS-xPyjtGJefETLOzDHRE7LJHVSf6Xc58ou9WzySQzx_WtMJrmMUcOgreCUXlRDUsEvc5fTIBPsIltdYywMkHTS1W2SOUMS-Rqrw5z9Nxmz0WSIWQXq3lnOy3WY6Hoa8UCKYHLonSQthycFyZqH7c-xazlmIU1FsltfI-ZuclvPTaL651tHrJXOTAzvkNYIw1uWn9-rqeb14DTS3Hd6EcaKrbjYLDoT05oQThbyReBhTlgao",
										manutVariable);
								/*status = maintenanceRequester.check(
										"Bearer YYP7w_Td5Pm481an66JeZ2eDmKELxYYTDchGxteGus70gL_rkwfDlGOAP5GqDByIc67z2_tJCiMb8n0zvnryW1g7b0C4K1ieDXQCEaDgxfIzkI6ql2kJtZQx_txRY1WevWC--N1X6xxG7BzKKon0kfq53dQAg6QgGSl5dMI0WZkUJ393E-knwRv_XR7OBe5y1g7T7L-wXa2MIAbXxVN7DoJH4xWSJXbv31e11qgs4brSRcWbePoRU6E6l71FSOnrVPfFdYjl6-p8WrFEKD2oeR0oeXYqVgr3JtHvx3sH-MVqbSoO7htg_woRLFD-iN315RW-gZMJcb2xtQ6ULSBx15AKMkezTLNrC2HbBMoxS_U",
										manutVariable);*/

							if (status)
								manutVariablesService.saveManut(manutVariable);
						}

					}

					try {
						threadMaintenance.sleep(10000);
					} catch (InterruptedException e) {
						System.out.println("EXCEPTION MAINTENANCE!");
						e.printStackTrace();
					}
				} // while(true)
			} // public void run()
		}); // threadMaintenance

		threadMakeProduction.start();
		threadSender.start();
		threadMaintenance.start();
	}

	/************************** METHOD REQUESTS ************************/

	private List<Order> getOrdersNotProduced() {
		try {
			return orderService.findOrderAllToProduce();
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR ORDENS NÃO PRODUZIDAS");
			return null;
		}
	}

	private List<Order> getOrdersMethod() {

		// INITIALIZATION: FIRST CONFIGURATION
		if ((started == false) && (InterfaceConfig.interfaceType == InterfaceConfig.LOCAL_TYPE)) {
			System.out.println("iniciado");
			// Initialize the email sender and maintenance requester
			emailSender = new EmailSender();
			maintenanceRequester = new MaintenanceRequester();

			// create machineries
			mTurn = machineryService.findMachineryById(3L);
			mMill = machineryService.findMachineryById(4L);
			mRobot = machineryService.findMachineryById(5L);

			// create machine list of this 4.0 cell
			machineriesList = new ArrayList<>();
			machineriesList.add(mTurn);
			machineriesList.add(mMill);
			// machineriesList.add(mRobot);

			// create array of orders
			totalOrders = new ArrayList<>();
			ordersInProduction = new ArrayList<>();

			// get orders not finished
			ordersInProduction = orderService.findOrderAllManufacturing();

			// create the gcode writers
			gcodeWriter = new GcodeWriter();

			// call manager Production before continue
			managerProduction();

			// get last status of variables
			// uaVarTurn = uaTurnService.findTurnLastVar();
			// uaVarMill = uaMillingService.findMillingLastVar();
			// uaVarRobot = uaRobotService.findRobotLastVar();

			// create array of messages to send
			arrayMessages = new ArrayList<>();

			// now, we started the initialization
			started = true;
		}

		return orderService.findOrderAll();
	}

	private List<Order> getOrdersByMachineMethod(OrderType type) {
		List<Order> list = orderService.findAllByType(type);

		return list;
	}

	/************************** HTTP REQUEST ************************/

	/** ------ SAVE AN ORDER ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/ordersave", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveOrder(@RequestBody @Valid Order order, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			System.out.println("DEU ERRO AO SALVAR ORDEM!");
			return "erro";
		}

		ProcessOrder process = order.getProcess();
		List<StepOrder> mySteps = new ArrayList<>();
		mySteps = order.getProcess().getSteps();
		order.setInputDate(LocalDateTime.now());

		/*
		 * System.out.println("USER:" + order.getUser().getId());
		 * System.out.println(order.getType().getType());
		 * System.out.println(order.getDimensions());
		 * System.out.println(order.getUnits());
		 * System.out.println(order.getOrdername());
		 * System.out.println(order.isProduced());
		 * System.out.println(order.getUnitsProduced());
		 * System.out.println(order.isManufacturing());
		 * 
		 * System.out.println("-----processo----");
		 * System.out.println(process.isConcluded());
		 * System.out.println(process.getSteps().get(0).isConcluded());
		 * System.out.println(process.getSteps().get(0).getGcode());
		 */

		if (saveTheOrder(order) == false) {
			return "erro";
		}

		System.out.println("SOLICITACAO OKKK");

		return "feito";
	}

	/************* REQUEST TOTAL COUNT ***********/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/count_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long getAllOrderCount() {

		return orderService.count();
	}

	/************* REQUEST DELETE ORDERS ***********/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/delete_all_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDeleteAllOrders() {

		try {
			orderService.deleteAllOrder();
			return "feito";
		} catch (Exception ex) {
			return "erro";
		}
	}

	/************* REQUEST DELETE ONE ORDER ***********/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/delete_order/{orderId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDeleteOrder(@PathVariable(value="orderId") Integer orderId) {
		try {
			if(orderId != null && orderId > 0)
			{
				Optional<Order> orderToDelete = orderService.findOrderById(orderId);
				if(orderToDelete.isPresent()){
					orderService.deleteOrderById(orderToDelete.get());
				}
			}
			return "feito";
		} catch (Exception ex) {
			return "erro";
		}
	}

	/** ------ CHANGE ORDER PRODUCTION ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/change_order/{orderId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String postChangeOrder(@PathVariable(value="orderId") Integer orderId,
								  @RequestParam(value="produced") boolean statusProduced) {
		try {
			if(orderId != null && orderId > 0)
			{
				Optional<Order> orderToChange = orderService.findOrderById(orderId);
				if(orderToChange.isPresent()){
					Order order = orderToChange.get();
					LocalDateTime out = (statusProduced)? LocalDateTime.now() : null;
					Integer unt = (statusProduced)? order.getUnits() : 0;

					order.setManufacturing(false);
					order.setProduced(statusProduced);
					order.setOutputDate(out);
					order.setUnitsProduced(unt);
					orderService.saveOrder(order);
				}
			}
			return "feito";
		} catch (Exception ex) {
			return "erro";
		}
	}

	/** ------ CHANGE ALL ORDERS PRODUCTION ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/change_all_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public String postChangeAllOrders(@RequestParam(value="produced") boolean statusProduced) {
		try {
				List<Order> ordersToChange = getOrdersNotProduced();
				LocalDateTime out = (statusProduced)? LocalDateTime.now() : null;

				for(Order order : ordersToChange){
					Integer unt = (statusProduced)? order.getUnits() : 0;
					order.setManufacturing(false);
					order.setProduced(statusProduced);
					order.setOutputDate(out);
					order.setUnitsProduced(unt);
					orderService.saveOrder(order);
				}

			return "feito";
		} catch (Exception ex) {
			return "erro";
		}
	}

	/** ------ REQUEST ALL ORDERS ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/load_all_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrder() {
		
		return getOrdersMethod();
	}
	
	/** ------ REQUEST ALL ORDERS NOT PRODUCED ------- **/
	@CrossOrigin
	@ResponseBody
	@GetMapping(value = "/xdk", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getXDKVariables() {		
		
		/* XDK CONTROLLER */
		StringBuilder responseContent = new StringBuilder();
		
		try {

			URL url = new URL(
					"https://api.qubitro.com/v1/projects/1b53f2f9-7d0a-45a6-8847-c8a9eaa94a74/devices/a93bd7ed-74ec-4799-8801-265d6555685b/data?keys=light,pressure,humidity,accx,accy,accz,gyx,gyy,gyz,temperature&period=30&limit=1000");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer 0txx4lvwpQBkMBVnXJANQXOm6dPoAHaw3WqwgZv3");
			con.setRequestProperty("Accept", "application/json");
			// con.setDoOutput(true);

			BufferedReader reader;
			String line;

			int status = con.getResponseCode();

			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);					
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
					
				}
				reader.close();
			}
			System.out.println("response code: " + status);
			System.out.println(responseContent.toString());			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseContent.toString();
	}

	/** ------ REQUEST ALL ORDERS NOT PRODUCED ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/load_orders_to_produce", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getOrdersProduce() {

		return getOrdersNotProduced();
	}

	/** ------ REQUEST ORDERS BY MACHINE ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/load_order_by_type", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrderMachine(@RequestBody OrderType type, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			System.out.println("DEU ERRO NO getAllOrderMachine!");
			return null;
		}

		return getOrdersByMachineMethod(type);
	}

	/** ------ SUCCESS ------- **/
	@CrossOrigin
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String getSuccess() {
		System.out.println("success");
		return "successed";
	}

	/** ------ FAIL ------- **/
	@CrossOrigin
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String getFail() {
		System.out.println("fail");
		return "failure";
	}

	/** ------ ALL MACHINERIES ------- **/
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/load_all_machineries", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Machinery> getAllMachineries() {

		return machineryService.findMachineryAll();
	}

}
