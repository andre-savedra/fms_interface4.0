package com.spring.fms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.fms.config.InterfaceConfig;
import com.spring.fms.managerProduction.GcodeWriter;
import com.spring.fms.managerProduction.Threads;
import com.spring.fms.model.Machine;
import com.spring.fms.model.Message;
import com.spring.fms.model.OpcUaVarsMilling;
import com.spring.fms.model.OpcUaVarsRobot;
import com.spring.fms.model.OpcUaVarsTurn;
import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;
import com.spring.fms.model.ProcessOrder;
import com.spring.fms.model.StepOrder;
import com.spring.fms.model.SupervisoryDataExchange;
import com.spring.fms.model.User;
import com.spring.fms.service.FmsOrderService;
import com.spring.fms.service.FmsProcessOrderService;
import com.spring.fms.service.FmsStepOrderService;
import com.spring.fms.service.OpcUaVarsMillingService;
import com.spring.fms.service.OpcUaVarsRobotService;
import com.spring.fms.service.OpcUaVarsTurnService;
import com.spring.fms.service.SupervisoryDataExchangeService;
import com.spring.fms.utils.EmailSender;

//andre
@Controller
public class FmsOrderController {

	private static Threads threadGetProduction;
	private static Threads threadMakeProduction;
	private static Threads threadSender;

	private static Machine mTurn;
	private static Machine mMill;

	private static OrderType orderTurn;

	private static List<Order> ordersInProduction;
	private static List<Order> totalOrders;
	private static List<Order> ordersTurn;
	private static List<Order> ordersMill;
	private static Order currentOrderTurn;
	private static Order currentOrderMill;

	private static OpcUaVarsTurn uaVarTurn;
	private static OpcUaVarsMilling uaVarMill;
	private static OpcUaVarsRobot uaVarRobot;

	private static SupervisoryDataExchange supervisoryDataExchange;

	private static boolean started = false;
	private static boolean bufferProductionTurn = false;
	private static boolean bufferProductionMill = false;

	private GcodeWriter gcodeWriterTurn, gcodeWriterMill;

	private final String pathTurn = "D:\\gcode\\";
	private final String pathMill = "D:\\gcode\\";
	private final String fileNameTurn = "O7000";
	private final String fileNameMill = "O8000";

	private List<Message> arrayMessages;

	EmailSender emailSender;

	private static Integer count = 0;

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

	/************************** MANAGER PRODUCTION ************************/
	
	private byte gcodeSetter(Order orderToGetGcode) {
		byte status = 0;
		
		//for
		
		
		return status;		
	}
	
	
	private Order findNextOrder(List<Order> orders) {
		Order nextOrder = new Order();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).isProduced() == false) {
				nextOrder = orders.get(i);
				break;
			}
		}

		return nextOrder;
	}

	// function to manage production, with threads inside
	private void managerProduction() {

		// thread to get productions frequently
		threadGetProduction = new Threads(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadGetProduction.isAllDone()) {
						return;
					}
					System.out.println("thread GET PRODUCTION is running!!!!!!!!!!!!!!!!!");

					totalOrders = getOrdersMethod();
					ordersTurn.clear();
					ordersMill.clear();

					// get all orders and split then to each list (turn and mill)
					for (int i = 0; i < totalOrders.size(); i++) {
						String type = totalOrders.get(i).getType().getType();

						if (type.equals("order-turn") || type.equals("custom-turn") || type.equals("flex")) {
							ordersTurn.add(totalOrders.get(i));
						} else {
							ordersMill.add(totalOrders.get(i));
						}
					}

					try {
						threadGetProduction.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // while(true)
			} // public void run()
		}); // threadProduction

		// thread to manage production factory
		threadMakeProduction = new Threads(new Runnable() {

			@Override
			public void run() {

				while (true) {

					if (threadMakeProduction.isAllDone()) {
						return;
					}
					System.out.println("thread MAKE PRODUCTION is running!!!!!!!!!!!!!!!!!");

					// refresh status of variables between supervisory and MES
					uaVarTurn = uaTurnService.findTurnLastVar();
					uaVarMill = uaMillingService.findMillingLastVar();
					uaVarRobot = uaRobotService.findRobotLastVar();
					supervisoryDataExchange = supervisoryService.findDataById(1L);

					if ((started) && (ordersTurn.size() > 0) && (!currentOrderTurn.isProduced())) {
						// System.out.println(currentOrderTurn.getOrdername());

						// Turn is machining - start rising edge
						if (!uaVarTurn.isTurnMachining()) {
							bufferProductionTurn = true;

							if (supervisoryDataExchange.isGcodeTurnLoaded()) {
								supervisoryDataExchange.setGcodeTurnLoaded(false);
								supervisoryDataExchange.setOrderTurnToManufacture(1L);
								supervisoryService.saveData(supervisoryDataExchange);
							}

							System.out.println("TORNO usinando");
						}

						// Turn ended machining operation - end of rising edge
						if (uaVarTurn.isTurnMachining() && bufferProductionTurn) {
							System.out.println("TORNO TERMINOUUUUUUUUU");
							bufferProductionTurn = false;
							currentOrderTurn.setProduced(true);
							currentOrderTurn.setUnitsProduced(1);
							currentOrderTurn.setOutputDate(LocalDateTime.now());

							User u = currentOrderTurn.getUser();
							String msg = u.getName() + ", sua peca com nome " + currentOrderTurn.getOrdername()
									+ " acabou de ficar pronta! =) ";

							Message message = new Message(u.getName(), u.getPhone().toString(), msg);
							arrayMessages.add(message);

							/*
							 * if (currentOrderTurn.getGcode() != null) {
							 * orderService.saveOrder(currentOrderTurn); } -new
							 */
						}
					}

					// if already initialized and part finished
					if ((started) && (ordersTurn.size() > 0) && (currentOrderTurn.isProduced())) {

						// not implemented, standby:
						// ordersTurn = getOrdersByMachineMethod(mTurn);
						currentOrderTurn = findNextOrder(ordersTurn);

						if (currentOrderTurn.getProcess().getSteps()
							.get(0).getGcode() != null) {
														
							if (gcodeWriterTurn.createFile(
									currentOrderTurn.getProcess().getSteps()
									.get(0).getGcode()
								)) {
																
								supervisoryDataExchange.setGcodeTurnLoaded(true);
								supervisoryDataExchange.setOrderTurnToManufacture(currentOrderTurn.getId());
								supervisoryService.saveData(supervisoryDataExchange);

								System.out.println("ARQUIVO CRIADO COM SUCESSO DO TORNO!");
							} else {
								supervisoryDataExchange.setGcodeTurnLoaded(false);
								supervisoryDataExchange.setOrderTurnToManufacture(1L);
								supervisoryService.saveData(supervisoryDataExchange);

								System.out.println("FALHA CRIACAO TORNO");
							}

							System.out.println("------------------------------");
							System.out.println("ORDEM ATUAL CARREGADA E:");
							System.out.println(currentOrderTurn.getOrdername());
							System.out.println("------------------------------");
						}

						// there is no order to produce, so we can retain variable to false
						/*
						 * else if (supervisoryDataExchange.isGcodeTurnLoaded()){
						 * supervisoryDataExchange.setGcodeTurnLoaded(false);
						 * supervisoryDataExchange.setOrderTurnToManufacture(1L);
						 * supervisoryService.saveData(supervisoryDataExchange); } -new
						 */

					}

					/*
					 * if ((started) && (ordersMill.size() > 0) && (currentOrderMill.isProduced()))
					 * {
					 * 
					 * currentOrderMill = findNextOrder(ordersMill);
					 * if(gcodeWriterMill.createFile(currentOrderMill.getGcode())) {
					 * System.out.println("ARQUIVO CRIADO COM SUCESSO DO CENTRO!"); } else {
					 * System.out.println("FALHA CRIAÇÃO CENTRO"); }
					 * System.out.println("------------------------------");
					 * System.out.println("ORDEM ATUAL CARREGADA É:");
					 * System.out.println(currentOrderMill.getOrdername());
					 * System.out.println("------------------------------"); }
					 */

					try {
						threadMakeProduction.sleep(1500);
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
						threadSender.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("EXCEPTION SENDER!");
						e.printStackTrace();
					}
				} // while(true)
			} // public void run()
		}); // threadProduction

		threadGetProduction.start();
		threadMakeProduction.start();
		threadSender.start();
	}

	/************************** METHOD REQUESTS ************************/
	
	private List<Order> getOrdersNotProduced(){
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

			// Initialize the email sender
			emailSender = new EmailSender();

			// create machines
			mTurn = new Machine();
			mTurn.setId(1L);
			mTurn.setName("torno");

			mMill = new Machine();
			mMill.setId(2L);
			mMill.setName("centro");

			// create current order and set to produced to force find new one
			currentOrderTurn = new Order();
			currentOrderTurn.setProduced(true);

			currentOrderMill = new Order();
			currentOrderMill.setProduced(true);

			// create array of orders
			
			totalOrders = new ArrayList<>();
			ordersTurn = new ArrayList<>();
			ordersMill = new ArrayList<>();

			// create the gcode writers
			gcodeWriterTurn = new GcodeWriter(fileNameTurn, pathTurn);
			gcodeWriterMill = new GcodeWriter(fileNameMill, pathMill);

			// create instance of supervisory
			supervisoryDataExchange = supervisoryService.findDataById(1L);

			// call manager Production before continue
		    //managerProduction();

			// get last status of variables
			uaVarTurn = uaTurnService.findTurnLastVar();
			uaVarMill = uaMillingService.findMillingLastVar();
			uaVarRobot = uaRobotService.findRobotLastVar();

			// create array of messages to send
			arrayMessages = new ArrayList<>();

			// now, we started the initialization
			started = true;
		}

		return orderService.findOrderAll();
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

				System.out.print("--------------\n\n");
				System.out.println(steps.get(i).getName());
				System.out.println("Machine id:" + steps.get(i).getMachine().getId());
				System.out.println(steps.get(i).isConcluded());
				System.out.println(steps.get(i).getGcode());

				System.out.print("--------------\n\n");

				stepOrderService.saveStepOrder(steps.get(i));
			}
			return true;

		} catch (Exception e) {
			System.out.println("ERRO AO SALVAR OS STEPS!");
			return false;
		}
	}

	private List<Order> getOrdersByMachineMethod(OrderType type) {
		List<Order> list = orderService.findAllByType(type);

		return list;
	}

	/************************** HTTP REQUEST ************************/

	/** ------ SAVE AN ORDER ------- **/
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

		if (saveStepOrder(mySteps) == false) {
			return "erro";
		}

		if (saveProcessOrder(process) == false) {
			return "erro";
		}

		orderService.saveOrder(order);
		System.out.println("SOLICITACAO OKKK");

		return "feito";
	}

	/************* REQUEST TOTAL COUNT ***********/
	@ResponseBody
	@PostMapping(value = "/count_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long getAllOrderCount() {

		return orderService.count();
	}

	/************* REQUEST DELETE ORDERS ***********/
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

	/** ------ REQUEST ALL ORDERS ------- **/
	@ResponseBody
	@PostMapping(value = "/load_all_orders", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrder() {

		return getOrdersMethod();
	}
	
	/** ------ REQUEST ALL ORDERS NOT PRODUCED ------- **/
	@ResponseBody
	@PostMapping(value = "/load_orders_to_produce", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getOrdersProduce() {

		return getOrdersNotProduced();
	}
	

	/** ------ REQUEST ORDERS BY MACHINE ------- **/
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
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String getSuccess() {
		System.out.println("success");
		return "successed";
	}

	/** ------ FAIL ------- **/
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String getFail() {
		System.out.println("fail");
		return "failure";
	}

}
