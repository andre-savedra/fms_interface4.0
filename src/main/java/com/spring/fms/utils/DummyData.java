package com.spring.fms.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.fms.model.Customization;
import com.spring.fms.model.Machine;
import com.spring.fms.model.Machinery;
import com.spring.fms.model.ManutVariables;
import com.spring.fms.model.Model;
import com.spring.fms.model.Order;
import com.spring.fms.model.OrderType;
import com.spring.fms.model.Part;
import com.spring.fms.model.ProcessOrder;
import com.spring.fms.model.ProcessPart;
import com.spring.fms.model.StepOrder;
import com.spring.fms.model.StepPart;
import com.spring.fms.model.User;
import com.spring.fms.repository.FmsCustomRepository;
import com.spring.fms.repository.FmsMachineRepository;
import com.spring.fms.repository.FmsMachineryRepository;
import com.spring.fms.repository.FmsModelRepository;
import com.spring.fms.repository.FmsOrderRepository;
import com.spring.fms.repository.FmsOrderTypeRepository;
import com.spring.fms.repository.FmsPartRepository;
import com.spring.fms.repository.FmsProcessOrderRepository;
import com.spring.fms.repository.FmsProcessPartRepository;
import com.spring.fms.repository.FmsStepOrderRepository;
import com.spring.fms.repository.FmsStepPartRepository;
import com.spring.fms.repository.FmsUserRepository;
import com.spring.fms.repository.ManutVariablesRepository;

@Component
public class DummyData {

	@Autowired
	FmsMachineRepository machineRepository_;

	@Autowired
	FmsOrderTypeRepository orderTypeRepository_;

	@Autowired
	FmsUserRepository userRepository_;

	@Autowired
	FmsOrderRepository orderRepository_;

	@Autowired
	FmsCustomRepository customRepository_;

	@Autowired
	FmsPartRepository partRepository_;

	@Autowired
	FmsProcessOrderRepository processOrderRepository_;

	@Autowired
	FmsProcessPartRepository processPartRepository_;

	@Autowired
	FmsStepPartRepository stepPartRepository_;

	@Autowired
	FmsStepOrderRepository stepOrderRepository_;
	
	@Autowired
	FmsModelRepository modelRepository_;
	
	@Autowired
	FmsMachineryRepository machineryRepository_;
		
	@Autowired
	ManutVariablesRepository manutVariablesRepository;

		
	//@PostConstruct
	public void loadDummy() {
				
						
		//MODELS
		Model model1 = new Model();
		model1.setModelName("branco");
		model1.setInfo1("#ede4e4");
		model1.setInfo2("#d9c7c7");
		modelRepository_.save(model1);
		
		Model model2 = new Model();
		model2.setModelName("preto");
		model2.setInfo1("#36332c");
		model2.setInfo2("#b3ac9b");
		modelRepository_.save(model2);
		
		
		List<Model> models1 = new ArrayList<>();
		models1.add(model1);
		models1.add(model2);
				
		
		// MACHINES
		Machine mach = new Machine();
		mach.setName("torno");

		Machine mach2 = new Machine();
		mach2.setName("centro");

		Machine mach3 = new Machine();
		mach3.setName("robo");

		
		List<Machine> machineList = new ArrayList<>();
		machineList.add(mach);
		machineList.add(mach2);
		machineList.add(mach3);

		for (Machine theMachines : machineList) {
			Machine saved = machineRepository_.save(theMachines);
			System.out.println("Machine:");
			System.out.println(saved.getId());
		}
		
		Machinery machinery = new Machinery(mach.getId(),mach, false);
		//machinery.setFilename("O7000");
		//machinery.setPath("D:\\gcode\\");
		
		Machinery machinery2 = new Machinery(mach2.getId(),mach2, false);
		//machinery2.setFilename("O8000");
		//machinery2.setPath("D:\\gcode\\");
		
		Machinery machinery3 = new Machinery(mach3.getId(),mach3, false);
		
		List<Machinery> machineryList = new ArrayList<>();
		machineryList.add(machinery);
		machineryList.add(machinery2);
		machineryList.add(machinery3);
		
		for (Machinery theMachineries : machineryList) {
			Machinery saved = machineryRepository_.save(theMachineries);
			System.out.println("Machineries:");
			System.out.println(saved.getId());
		}
		

		// ORDERS TYPE
		OrderType orderTp = new OrderType();
		orderTp.setType("order-turn");

		OrderType orderTp2 = new OrderType();
		orderTp2.setType("custom-turn");

		OrderType orderTp3 = new OrderType();
		orderTp3.setType("order-mill");

		OrderType orderTp4 = new OrderType();
		orderTp4.setType("custom-mill");

		OrderType orderTp5 = new OrderType();
		orderTp5.setType("flex");

		List<OrderType> orderTypeList = new ArrayList<>();
		orderTypeList.add(orderTp);
		orderTypeList.add(orderTp2);
		orderTypeList.add(orderTp3);
		orderTypeList.add(orderTp4);
		orderTypeList.add(orderTp5);

		for (OrderType theOrders : orderTypeList) {
			OrderType saved = orderTypeRepository_.save(theOrders);
			System.out.println("OrdersType:");
			System.out.println(saved.getId());
		}

		// USERS
		User myuser = new User();
		myuser.setId(1L);
		myuser.setName("Teste");
		myuser.setEmail("a@gmail.com");
		myuser.setBirth(LocalDate.of(1991, 11, 11));
		myuser.setGender("male");
		myuser.setPassword("1");
		myuser.setPhone(1L);
		// myuser.setOrders(new ArrayList<Order>());

		User myuser2 = new User();
		myuser2.setId(2L);
		myuser2.setName("Teste2");
		myuser2.setEmail("c@gmail.com");
		myuser2.setBirth(LocalDate.of(1986, 1, 8));
		myuser2.setGender("female");
		myuser2.setPassword("2");
		myuser2.setPhone(19989395707L);
		// myuser2.setOrders(new ArrayList<Order>());

		List<User> usersList = new ArrayList<>();
		usersList.add(myuser);
		usersList.add(myuser2);

		for (User theUsers : usersList) {
			User saved = userRepository_.save(theUsers);
			System.out.println("usuario");
			System.out.println(saved.getId());
		}

		// FLEX1
		List<Customization> CustomLists1 = new ArrayList<>();

		Customization ctm1_1 = new Customization();
		ctm1_1.setMaximum(29F);
		ctm1_1.setMinimum(26F);
		CustomLists1.add(ctm1_1);
		customRepository_.save(ctm1_1);

		Customization ctm1_2 = new Customization();
		ctm1_2.setMaximum(28F);
		ctm1_2.setMinimum(25F);
		CustomLists1.add(ctm1_2);
		customRepository_.save(ctm1_2);

		// FRESA2
		List<Customization> CustomLists2 = new ArrayList<>();

		Customization ctm2_1 = new Customization();
		ctm2_1.setMaximum(29F);
		ctm2_1.setMinimum(26F);
		CustomLists2.add(ctm2_1);
		customRepository_.save(ctm2_1);

		// TORNO2
		List<Customization> CustomLists3 = new ArrayList<>();

		Customization ctm3_1 = new Customization();
		ctm3_1.setMaximum(45F);
		ctm3_1.setMinimum(30F);
		CustomLists3.add(ctm3_1);
		customRepository_.save(ctm3_1);

		// CUSTOMS
		List<List<Customization>> totalCustomLists = new ArrayList<>();
		totalCustomLists.add(CustomLists1);
		totalCustomLists.add(CustomLists2);
		totalCustomLists.add(CustomLists3);

		int seq_customs[] = { 2, 3, 3, 2, 2, 2, 3, 2 };

		for (long j = 1; j < (seq_customs.length + 1); j++) {
			List<Customization> innerCustomLists = new ArrayList<>();
			int idx = (int) (j - 1);

			for (long i = 1; i < (seq_customs[idx] + 1); i++) {
				Customization ctm = new Customization();
				ctm.setMaximum(40F * i * j);
				ctm.setMinimum(25F * i * j);
				innerCustomLists.add(ctm);
				customRepository_.save(ctm);
				System.out.println(ctm.getMaximum());
			}
			totalCustomLists.add(innerCustomLists);
		}

		System.out.println("CUSTOMIZACOES OK");

		// STEP PART

		// FLEX 1
		StepPart stepPart1 = new StepPart();
		stepPart1.setName("Torneamento corpo pino");
		stepPart1.setMachine(mach);
		stepPart1.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N10 G90 G40 G95\n")
				.append("N20 G54 X100 Z100\n")
				.append("N30 T0101\n")
				.append("N40 G96 S275 M4\n")
				.append("N50 G0 X47 Z2\n")
				.append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n")
				.append("N70 X&FMS_VAR1&\n")
				.append("N80 X&FMS_VAR1&\n")
				.append("N90 X&FMS_VAR1&\n")
				.append("N100 X&FMS_VAR1&\n")
				.append("N110 G0 G28 U0 W0\n")
				.append("N120 T0303\n")
				.append("N130 G96 S275 M4\n")
				.append("N140 G0 X&FMS_VAR1& Z2\n")
				.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n")
				.append("N160 X&FMS_VAR1& Z-3\n")
				.append("N170 Z-40\n")
				.append("N180 X47\n")
				.append("N190 G0 G28 U0 W0\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart1);

		StepPart stepPart2 = new StepPart();
		stepPart2.setName("Fresagem cabeça sextavada");
		stepPart2.setMachine(mach2);
		stepPart2.setGcode(new StringBuilder()
				.append("O7101\n")
				.append("N10 G54 G0 Z30\n")
				.append("N20 M6 T4\n")
				.append("N30 G54 X0 Y0\n")
				.append("N40 M3 S1500\n")
				.append("N50 G52 X-&FMS_VAR1&\n")
				.append("N60 G90 G54 G0 X0 Y0 Z2\n")
				.append("N70 G0X0.000Y0.000\r\n"
						+ "N80 G0X0.000Y7.500Z3.000\r\n"
						+ "N90 G0Z0.000\r\n"
						+ "N100 G1Z-3.600F240.0\r\n"
						+ "N110 G1X-1.087Y7.421F360.0\r\n"
						+ "N120 X-2.204Y7.169\r\n"
						+ "N130 X-3.310Y6.730\r\n"
						+ "N140 X-4.358Y6.104\r\n"
						+ "N150 X-5.303Y5.303\r\n"
						+ "N160 X-6.104Y4.358\r\n"
						+ "N170 X-6.730Y3.310\r\n"
						+ "N180 X-7.169Y2.204\r\n"
						+ "N190 X-7.421Y1.087\r\n"
						+ "N200 X-7.500Y0.000\r\n"
						+ "N210 X-7.435Y-0.986\r\n"
						+ "N220 X-7.228Y-2.000\r\n"
						+ "N230 X-6.869Y-3.012\r\n"
						+ "N240 X-6.353Y-3.987\r\n"
						+ "N250 X-5.687Y-4.889\r\n"
						+ "N260 X-4.889Y-5.687\r\n"
						+ "N270 X-3.987Y-6.353\r\n"
						+ "N280 X-3.012Y-6.869\r\n"
						+ "N290 X-2.000Y-7.228\r\n"
						+ "N300 X-0.986Y-7.435\r\n"
						+ "N310 X0.000Y-7.500\r\n"
						+ "N320 X1.087Y-7.421\r\n"
						+ "N330 X2.204Y-7.169\r\n"
						+ "N340 X3.310Y-6.730\r\n"
						+ "N350 X4.358Y-6.104\r\n"
						+ "N360 X5.303Y-5.303\r\n"
						+ "N370 X6.104Y-4.358\r\n"
						+ "N380 X6.730Y-3.310\r\n"
						+ "N390 X7.169Y-2.204\r\n"
						+ "N400 X7.421Y-1.087\r\n"
						+ "N410 X7.500Y0.000\r\n"
						+ "N420 X7.421Y1.087\r\n"
						+ "N430 X7.169Y2.204\r\n"
						+ "N440 X6.730Y3.310\r\n"
						+ "N450 X6.104Y4.358\r\n"
						+ "N460 X5.303Y5.303\r\n"
						+ "N470 X4.358Y6.104\r\n"
						+ "N480 X3.310Y6.730\r\n"
						+ "N490 X2.204Y7.169\r\n"
						+ "N500 X1.087Y7.421\r\n"
						+ "N510 X0.000Y7.500\r\n"
						+ "N520 G1Y17.500\r\n"
						+ "N530 G1X-2.301Y17.348\r\n"
						+ "N540 X-4.667Y16.866\r\n"
						+ "N550 X-7.027Y16.027\r\n"
						+ "N560 X-9.302Y14.823\r\n"
						+ "N570 X-11.409Y13.270\r\n"
						+ "N580 X-13.270Y11.409\r\n"
						+ "N590 X-14.823Y9.302\r\n"
						+ "N600 X-16.027Y7.027\r\n"
						+ "N610 X-16.866Y4.667\r\n"
						+ "N620 X-17.348Y2.301\r\n"
						+ "N630 X-17.500Y0.000\r\n"
						+ "N640 X-17.348Y-2.301\r\n"
						+ "N650 X-16.866Y-4.667\r\n"
						+ "N660 X-16.027Y-7.027\r\n"
						+ "N670 X-14.823Y-9.302\r\n"
						+ "N680 X-13.270Y-11.409\r\n"
						+ "N690 X-11.409Y-13.270\r\n"
						+ "N700 X-9.302Y-14.823\r\n"
						+ "N710 X-7.027Y-16.027\r\n"
						+ "N720 X-4.667Y-16.866\r\n"
						+ "N730 X-2.301Y-17.348\r\n"
						+ "N740 X0.000Y-17.500\r\n"
						+ "N750 X2.301Y-17.348\r\n"
						+ "N760 X4.667Y-16.866\r\n"
						+ "N770 X7.027Y-16.027\r\n"
						+ "N780 X9.302Y-14.823\r\n"
						+ "N790 X11.409Y-13.270\r\n"
						+ "N800 X13.270Y-11.409\r\n"
						+ "N810 X14.823Y-9.302\r\n"
						+ "N820 X16.027Y-7.027\r\n"
						+ "N830 X16.866Y-4.667\r\n"
						+ "N840 X17.348Y-2.301\r\n"
						+ "N850 X17.500Y0.000\r\n"
						+ "N860 X17.348Y2.301\r\n"
						+ "N870 X16.866Y4.667\r\n"
						+ "N880 X16.027Y7.027\r\n"
						+ "N890 X14.823Y9.302\r\n"
						+ "N900 X13.270Y11.409\r\n"
						+ "N910 X11.409Y13.270\r\n"
						+ "N920 X9.302Y14.823\r\n"
						+ "N930 X7.027Y16.027\r\n"
						+ "N940 X4.667Y16.866\r\n"
						+ "N950 X2.301Y17.348\r\n"
						+ "N960 X0.000Y17.500\r\n"
						+ "N970 G0Z3.000\r\n"
						+ "N980 G0X0.000Y0.000\r\n"
						+ "N990 G0Z0.000\r\n"
						+ "N1000 G1Z-3.600F240.0\r\n"
						+ "N1010 G0Z3.000\r\n"
						+ "N1020 G0X0.000Y7.500\r\n"
						+ "N1030 G0Z0.000\r\n"
						+ "N1040 G1Z-7.200F240.0\r\n"
						+ "N1050 G1X-1.087Y7.421F360.0\r\n"
						+ "N1060 X-2.204Y7.169\r\n"
						+ "N1070 X-3.310Y6.730\r\n"
						+ "N1080 X-4.358Y6.104\r\n"
						+ "N1090 X-5.303Y5.303\r\n"
						+ "N1100 X-6.104Y4.358\r\n"
						+ "N1110 X-6.730Y3.310\r\n"
						+ "N1120 X-7.169Y2.204\r\n"
						+ "N1130 X-7.421Y1.087\r\n"
						+ "N1140 X-7.500Y0.000\r\n"
						+ "N1150 X-7.435Y-0.986\r\n"
						+ "N1160 X-7.228Y-2.000\r\n"
						+ "N1170 X-6.869Y-3.012\r\n"
						+ "N1180 X-6.353Y-3.987\r\n"
						+ "N1190 X-5.687Y-4.889\r\n"
						+ "N1200 X-4.889Y-5.687\r\n"
						+ "N1210 X-3.987Y-6.353\r\n"
						+ "N1220 X-3.012Y-6.869\r\n"
						+ "N1230 X-2.000Y-7.228\r\n"
						+ "N1240 X-0.986Y-7.435\r\n"
						+ "N1250 X0.000Y-7.500\r\n"
						+ "N1260 X1.087Y-7.421\r\n"
						+ "N1270 X2.204Y-7.169\r\n"
						+ "N1280 X3.310Y-6.730\r\n"
						+ "N1290 X4.358Y-6.104\r\n"
						+ "N1300 X5.303Y-5.303\r\n"
						+ "N1310 X6.104Y-4.358\r\n"
						+ "N1320 X6.730Y-3.310\r\n"
						+ "N1330 X7.169Y-2.204\r\n"
						+ "N1340 X7.421Y-1.087\r\n"
						+ "N1350 X7.500Y0.000\r\n"
						+ "N1360 X7.421Y1.087\r\n"
						+ "N1370 X7.169Y2.204\r\n"
						+ "N1380 X6.730Y3.310\r\n"
						+ "N1390 X6.104Y4.358\r\n"
						+ "N1400 X5.303Y5.303\r\n"
						+ "N1410 X4.358Y6.104\r\n"
						+ "N1420 X3.310Y6.730\r\n"
						+ "N1430 X2.204Y7.169\r\n"
						+ "N1440 X1.087Y7.421\r\n"
						+ "N1450 X0.000Y7.500\r\n"
						+ "N1460 G1Y17.500\r\n"
						+ "N1470 G1X-2.301Y17.348\r\n"
						+ "N1480 X-4.667Y16.866\r\n"
						+ "N1490 X-7.027Y16.027\r\n"
						+ "N1500 X-9.302Y14.823\r\n"
						+ "N1510 X-11.409Y13.270\r\n"
						+ "N1520 X-13.270Y11.409\r\n"
						+ "N1530 X-14.823Y9.302\r\n"
						+ "N1540 X-16.027Y7.027\r\n"
						+ "N1550 X-16.866Y4.667\r\n"
						+ "N1560 X-17.348Y2.301\r\n"
						+ "N1570 X-17.500Y0.000\r\n"
						+ "N1580 X-17.348Y-2.301\r\n"
						+ "N1590 X-16.866Y-4.667\r\n"
						+ "N1600 X-16.027Y-7.027\r\n"
						+ "N1610 X-14.823Y-9.302\r\n"
						+ "N1620 X-13.270Y-11.409\r\n"
						+ "N1630 X-11.409Y-13.270\r\n"
						+ "N1640 X-9.302Y-14.823\r\n"
						+ "N1650 X-7.027Y-16.027\r\n"
						+ "N1660 X-4.667Y-16.866\r\n"
						+ "N1670 X-2.301Y-17.348\r\n"
						+ "N1680 X0.000Y-17.500\r\n"
						+ "N1690 X2.301Y-17.348\r\n"
						+ "N1700 X4.667Y-16.866\r\n"
						+ "N1710 X7.027Y-16.027\r\n"
						+ "N1720 X9.302Y-14.823\r\n"
						+ "N1730 X11.409Y-13.270\r\n"
						+ "N1740 X13.270Y-11.409\r\n"
						+ "N1750 X14.823Y-9.302\r\n"
						+ "N1760 X16.027Y-7.027\r\n"
						+ "N1770 X16.866Y-4.667\r\n"
						+ "N1780 X17.348Y-2.301\r\n"
						+ "N1790 X17.500Y0.000\r\n"
						+ "N1800 X17.348Y2.301\r\n"
						+ "N1810 X16.866Y4.667\r\n"
						+ "N1820 X16.027Y7.027\r\n"
						+ "N1830 X14.823Y9.302\r\n"
						+ "N1840 X13.270Y11.409\r\n"
						+ "N1850 X11.409Y13.270\r\n"
						+ "N1860 X9.302Y14.823\r\n"
						+ "N1870 X7.027Y16.027\r\n"
						+ "N1880 X4.667Y16.866\r\n"
						+ "N1890 X2.301Y17.348\r\n"
						+ "N1900 X0.000Y17.500\r\n"
						+ "N1910 G0Z3.000\r\n"
						+ "N1920 G0X0.000Y0.000\r\n"
						+ "N1930 G0Z0.000\r\n"
						+ "N1940 G1Z-7.200F240.0\r\n"
						+ "N1950 G0Z3.000\r\n"
						+ "N1960 G0X0.000Y7.500\r\n"
						+ "N1970 G0Z0.000\r\n"
						+ "N1980 G1Z-10.800F240.0\r\n"
						+ "N1990 G1X-1.087Y7.421F360.0\r\n"
						+ "N2000 X-2.204Y7.169\r\n"
						+ "N2010 X-3.310Y6.730\r\n"
						+ "N2020 X-4.358Y6.104\r\n"
						+ "N2030 X-5.303Y5.303\r\n"
						+ "N2040 X-6.104Y4.358\r\n"
						+ "N2050 X-6.730Y3.310\r\n"
						+ "N2060 X-7.169Y2.204\r\n"
						+ "N2070 X-7.421Y1.087\r\n"
						+ "N2080 X-7.500Y0.000\r\n"
						+ "N2090 X-7.435Y-0.986\r\n"
						+ "N2100 X-7.228Y-2.000\r\n"
						+ "N2110 X-6.869Y-3.012\r\n"
						+ "N2120 X-6.353Y-3.987\r\n"
						+ "N2130 X-5.687Y-4.889\r\n"
						+ "N2140 X-4.889Y-5.687\r\n"
						+ "N2150 X-3.987Y-6.353\r\n"
						+ "N2160 X-3.012Y-6.869\r\n"
						+ "N2170 X-2.000Y-7.228\r\n"
						+ "N2180 X-0.986Y-7.435\r\n"
						+ "N2190 X0.000Y-7.500\r\n"
						+ "N2200 X1.087Y-7.421\r\n"
						+ "N2210 X2.204Y-7.169\r\n"
						+ "N2220 X3.310Y-6.730\r\n"
						+ "N2230 X4.358Y-6.104\r\n"
						+ "N2240 X5.303Y-5.303\r\n"
						+ "N2250 X6.104Y-4.358\r\n"
						+ "N2260 X6.730Y-3.310\r\n"
						+ "N2270 X7.169Y-2.204\r\n"
						+ "N2280 X7.421Y-1.087\r\n"
						+ "N2290 X7.500Y0.000\r\n"
						+ "N2300 X7.421Y1.087\r\n"
						+ "N2310 X7.169Y2.204\r\n"
						+ "N2320 X6.730Y3.310\r\n"
						+ "N2330 X6.104Y4.358\r\n"
						+ "N2340 X5.303Y5.303\r\n"
						+ "N2350 X4.358Y6.104\r\n"
						+ "N2360 X3.310Y6.730\r\n"
						+ "N2370 X2.204Y7.169\r\n"
						+ "N2380 X1.087Y7.421\r\n"
						+ "N2390 X0.000Y7.500\r\n"
						+ "N2400 G1Y17.500\r\n"
						+ "N2410 G1X-2.301Y17.348\r\n"
						+ "N2420 X-4.667Y16.866\r\n"
						+ "N2430 X-7.027Y16.027\r\n"
						+ "N2440 X-9.302Y14.823\r\n"
						+ "N2450 X-11.409Y13.270\r\n"
						+ "N2460 X-13.270Y11.409\r\n"
						+ "N2470 X-14.823Y9.302\r\n"
						+ "N2480 X-16.027Y7.027\r\n"
						+ "N2490 X-16.866Y4.667\r\n"
						+ "N2500 X-17.348Y2.301\r\n"
						+ "N2510 X-17.500Y0.000\r\n"
						+ "N2520 X-17.348Y-2.301\r\n"
						+ "N2530 X-16.866Y-4.667\r\n"
						+ "N2540 X-16.027Y-7.027\r\n"
						+ "N2550 X-14.823Y-9.302\r\n"
						+ "N2560 X-13.270Y-11.409\r\n"
						+ "N2570 X-11.409Y-13.270\r\n"
						+ "N2580 X-9.302Y-14.823\r\n"
						+ "N2590 X-7.027Y-16.027\r\n"
						+ "N2600 X-4.667Y-16.866\r\n"
						+ "N2610 X-2.301Y-17.348\r\n"
						+ "N2620 X0.000Y-17.500\r\n"
						+ "N2630 X2.301Y-17.348\r\n"
						+ "N2640 X4.667Y-16.866\r\n"
						+ "N2650 X7.027Y-16.027\r\n"
						+ "N2660 X9.302Y-14.823\r\n"
						+ "N2670 X11.409Y-13.270\r\n"
						+ "N2680 X13.270Y-11.409\r\n"
						+ "N2690 X14.823Y-9.302\r\n"
						+ "N2700 X16.027Y-7.027\r\n"
						+ "N2710 X16.866Y-4.667\r\n"
						+ "N2720 X17.348Y-2.301\r\n"
						+ "N2730 X17.500Y0.000\r\n"
						+ "N2740 X17.348Y2.301\r\n"
						+ "N2750 X16.866Y4.667\r\n"
						+ "N2760 X16.027Y7.027\r\n"
						+ "N2770 X14.823Y9.302\r\n"
						+ "N2780 X13.270Y11.409\r\n"
						+ "N2790 X11.409Y13.270\r\n"
						+ "N2800 X9.302Y14.823\r\n"
						+ "N2810 X7.027Y16.027\r\n"
						+ "N2820 X4.667Y16.866\r\n"
						+ "N2830 X2.301Y17.348\r\n"
						+ "N2840 X0.000Y17.500\r\n"
						+ "N2850 G0Z3.000\r\n"
						+ "N2860 G0X0.000Y0.000\r\n"
						+ "N2870 G0Z0.000\r\n"
						+ "N2880 G1Z-10.800F240.0\r\n"
						+ "N2890 G0Z3.000\r\n"
						+ "N2900 G0X0.000Y7.500\r\n"
						+ "N2910 G0Z0.000\r\n"
						+ "N2920 G1Z-14.400F240.0\r\n"
						+ "N2930 G1X-1.087Y7.421F360.0\r\n"
						+ "N2940 X-2.204Y7.169\r\n"
						+ "N2950 X-3.310Y6.730\r\n"
						+ "N2960 X-4.358Y6.104\r\n"
						+ "N2970 X-5.303Y5.303\r\n"
						+ "N2980 X-6.104Y4.358\r\n"
						+ "N2990 X-6.730Y3.310\r\n"
						+ "N3000 X-7.169Y2.204\r\n"
						+ "N3010 X-7.421Y1.087\r\n"
						+ "N3020 X-7.500Y0.000\r\n"
						+ "N3030 X-7.435Y-0.986\r\n"
						+ "N3040 X-7.228Y-2.000\r\n"
						+ "N3050 X-6.869Y-3.012\r\n"
						+ "N3060 X-6.353Y-3.987\r\n"
						+ "N3070 X-5.687Y-4.889\r\n"
						+ "N3080 X-4.889Y-5.687\r\n"
						+ "N3090 X-3.987Y-6.353\r\n"
						+ "N3100 X-3.012Y-6.869\r\n"
						+ "N3110 X-2.000Y-7.228\r\n"
						+ "N3120 X-0.986Y-7.435\r\n"
						+ "N3130 X0.000Y-7.500\r\n"
						+ "N3140 X1.087Y-7.421\r\n"
						+ "N3150 X2.204Y-7.169\r\n"
						+ "N3160 X3.310Y-6.730\r\n"
						+ "N3170 X4.358Y-6.104\r\n"
						+ "N3180 X5.303Y-5.303\r\n"
						+ "N3190 X6.104Y-4.358\r\n"
						+ "N3200 X6.730Y-3.310\r\n"
						+ "N3210 X7.169Y-2.204\r\n"
						+ "N3220 X7.421Y-1.087\r\n"
						+ "N3230 X7.500Y0.000\r\n"
						+ "N3240 X7.421Y1.087\r\n"
						+ "N3250 X7.169Y2.204\r\n"
						+ "N3260 X6.730Y3.310\r\n"
						+ "N3270 X6.104Y4.358\r\n"
						+ "N3280 X5.303Y5.303\r\n"
						+ "N3290 X4.358Y6.104\r\n"
						+ "N3300 X3.310Y6.730\r\n"
						+ "N3310 X2.204Y7.169\r\n"
						+ "N3320 X1.087Y7.421\r\n"
						+ "N3330 X0.000Y7.500\r\n"
						+ "N3340 G1Y17.500\r\n"
						+ "N3350 G1X-2.301Y17.348\r\n"
						+ "N3360 X-4.667Y16.866\r\n"
						+ "N3370 X-7.027Y16.027\r\n"
						+ "N3380 X-9.302Y14.823\r\n"
						+ "N3390 X-11.409Y13.270\r\n"
						+ "N3400 X-13.270Y11.409\r\n"
						+ "N3410 X-14.823Y9.302\r\n"
						+ "N3420 X-16.027Y7.027\r\n"
						+ "N3430 X-16.866Y4.667\r\n"
						+ "N3440 X-17.348Y2.301\r\n"
						+ "N3450 X-17.500Y0.000\r\n"
						+ "N3460 X-17.348Y-2.301\r\n"
						+ "N3470 X-16.866Y-4.667\r\n"
						+ "N3480 X-16.027Y-7.027\r\n"
						+ "N3490 X-14.823Y-9.302\r\n"
						+ "N3500 X-13.270Y-11.409\r\n"
						+ "N3510 X-11.409Y-13.270\r\n"
						+ "N3520 X-9.302Y-14.823\r\n"
						+ "N3530 X-7.027Y-16.027\r\n"
						+ "N3540 X-4.667Y-16.866\r\n"
						+ "N3550 X-2.301Y-17.348\r\n"
						+ "N3560 X0.000Y-17.500\r\n"
						+ "N3570 X2.301Y-17.348\r\n"
						+ "N3580 X4.667Y-16.866\r\n"
						+ "N3590 X7.027Y-16.027\r\n"
						+ "N3600 X9.302Y-14.823\r\n"
						+ "N3610 X11.409Y-13.270\r\n"
						+ "N3620 X13.270Y-11.409\r\n"
						+ "N3630 X14.823Y-9.302\r\n"
						+ "N3640 X16.027Y-7.027\r\n"
						+ "N3650 X16.866Y-4.667\r\n"
						+ "N3660 X17.348Y-2.301\r\n"
						+ "N3670 X17.500Y0.000\r\n"
						+ "N3680 X17.348Y2.301\r\n"
						+ "N3690 X16.866Y4.667\r\n"
						+ "N3700 X16.027Y7.027\r\n"
						+ "N3710 X14.823Y9.302\r\n"
						+ "N3720 X13.270Y11.409\r\n"
						+ "N3730 X11.409Y13.270\r\n"
						+ "N3740 X9.302Y14.823\r\n"
						+ "N3750 X7.027Y16.027\r\n"
						+ "N3760 X4.667Y16.866\r\n"
						+ "N3770 X2.301Y17.348\r\n"
						+ "N3780 X0.000Y17.500\r\n"
						+ "N3790 G0Z3.000\r\n"
						+ "N3800 G0X0.000Y0.000\r\n"
						+ "N3810 G0Z0.000\r\n"
						+ "N3820 G1Z-14.400F240.0\r\n"
						+ "N3830 G0Z3.000\r\n"
						+ "N3840 G0X0.000Y7.500\r\n"
						+ "N3850 G0Z0.000\r\n"
						+ "N3860 G1Z-18.000F240.0\r\n"
						+ "N3870 G1X-1.087Y7.421F360.0\r\n"
						+ "N3880 X-2.204Y7.169\r\n"
						+ "N3890 X-3.310Y6.730\r\n"
						+ "N3900 X-4.358Y6.104\r\n"
						+ "N3910 X-5.303Y5.303\r\n"
						+ "N3920 X-6.104Y4.358\r\n"
						+ "N3930 X-6.730Y3.310\r\n"
						+ "N3940 X-7.169Y2.204\r\n"
						+ "N3950 X-7.421Y1.087\r\n"
						+ "N3960 X-7.500Y0.000\r\n"
						+ "N3970 X-7.435Y-0.986\r\n"
						+ "N3980 X-7.228Y-2.000\r\n"
						+ "N3990 X-6.869Y-3.012\r\n"
						+ "N4000 X-6.353Y-3.987\r\n"
						+ "N4010 X-5.687Y-4.889\r\n"
						+ "N4020 X-4.889Y-5.687\r\n"
						+ "N4030 X-3.987Y-6.353\r\n"
						+ "N4040 X-3.012Y-6.869\r\n"
						+ "N4050 X-2.000Y-7.228\r\n"
						+ "N4060 X-0.986Y-7.435\r\n"
						+ "N4070 X0.000Y-7.500\r\n"
						+ "N4080 X1.087Y-7.421\r\n"
						+ "N4090 X2.204Y-7.169\r\n"
						+ "N4100 X3.310Y-6.730\r\n"
						+ "N4110 X4.358Y-6.104\r\n"
						+ "N4120 X5.303Y-5.303\r\n"
						+ "N4130 X6.104Y-4.358\r\n"
						+ "N4140 X6.730Y-3.310\r\n"
						+ "N4150 X7.169Y-2.204\r\n"
						+ "N4160 X7.421Y-1.087\r\n"
						+ "N4170 X7.500Y0.000\r\n"
						+ "N4180 X7.421Y1.087\r\n"
						+ "N4190 X7.169Y2.204\r\n"
						+ "N4200 X6.730Y3.310\r\n"
						+ "N4210 X6.104Y4.358\r\n"
						+ "N4220 X5.303Y5.303\r\n"
						+ "N4230 X4.358Y6.104\r\n"
						+ "N4240 X3.310Y6.730\r\n"
						+ "N4250 X2.204Y7.169\r\n"
						+ "N4260 X1.087Y7.421\r\n"
						+ "N4270 X0.000Y7.500\r\n"
						+ "N4280 G1Y17.500\r\n"
						+ "N4290 G1X-2.301Y17.348\r\n"
						+ "N4300 X-4.667Y16.866\r\n"
						+ "N4310 X-7.027Y16.027\r\n"
						+ "N4320 X-9.302Y14.823\r\n"
						+ "N4330 X-11.409Y13.270\r\n"
						+ "N4340 X-13.270Y11.409\r\n"
						+ "N4350 X-14.823Y9.302\r\n"
						+ "N4360 X-16.027Y7.027\r\n"
						+ "N4370 X-16.866Y4.667\r\n"
						+ "N4380 X-17.348Y2.301\r\n"
						+ "N4390 X-17.500Y0.000\r\n"
						+ "N4400 X-17.348Y-2.301\r\n"
						+ "N4410 X-16.866Y-4.667\r\n"
						+ "N4420 X-16.027Y-7.027\r\n"
						+ "N4430 X-14.823Y-9.302\r\n"
						+ "N4440 X-13.270Y-11.409\r\n"
						+ "N4450 X-11.409Y-13.270\r\n"
						+ "N4460 X-9.302Y-14.823\r\n"
						+ "N4470 X-7.027Y-16.027\r\n"
						+ "N4480 X-4.667Y-16.866\r\n"
						+ "N4490 X-2.301Y-17.348\r\n"
						+ "N4500 X0.000Y-17.500\r\n"
						+ "N4510 X2.301Y-17.348\r\n"
						+ "N4520 X4.667Y-16.866\r\n"
						+ "N4530 X7.027Y-16.027\r\n"
						+ "N4540 X9.302Y-14.823\r\n"
						+ "N4550 X11.409Y-13.270\r\n"
						+ "N4560 X13.270Y-11.409\r\n"
						+ "N4570 X14.823Y-9.302\r\n"
						+ "N4580 X16.027Y-7.027\r\n"
						+ "N4590 X16.866Y-4.667\r\n"
						+ "N4600 X17.348Y-2.301\r\n"
						+ "N4610 X17.500Y0.000\r\n"
						+ "N4620 X17.348Y2.301\r\n"
						+ "N4630 X16.866Y4.667\r\n"
						+ "N4640 X16.027Y7.027\r\n"
						+ "N4650 X14.823Y9.302\r\n"
						+ "N4660 X13.270Y11.409\r\n"
						+ "N4670 X11.409Y13.270\r\n"
						+ "N4680 X9.302Y14.823\r\n"
						+ "N4690 X7.027Y16.027\r\n"
						+ "N4700 X4.667Y16.866\r\n"
						+ "N4710 X2.301Y17.348\r\n"
						+ "N4720 X0.000Y17.500\r\n"
						+ "N4730 G0Z3.000\r\n"
						+ "N4740 G0X0.000Y0.000\r\n"
						+ "N4750 G0Z0.000\r\n"
						+ "N4760 G1Z-18.000F240.0\r\n"
						+ "N4770 G0Z3.000\r\n"
						+ "")
				.append("N4780 G1 X&FMS_VAR1& Y&FMS_VAR1& F500\n")
				.append("N4790 Z-28\n")				
				.append("N4800 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4810 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4820 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4830 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4840 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4850 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4860 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4870 G54 G90 G0 Z30\n")
				.append("N4880 G28 X0 Y0 Z0\n")
				.append("N4890 M30\n").toString());
		stepPartRepository_.save(stepPart2);

		// FLEX2
		StepPart stepPart3 = new StepPart();
		stepPart3.setName("Torneamento corpo parafuso");
		stepPart3.setMachine(mach);
		stepPart3.setGcode(
				new StringBuilder()
				.append("O7101\n")				
		        .append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart3);

		StepPart stepPart4 = new StepPart();
		stepPart4.setName("Fresagem cabeça parafuso");
		stepPart4.setMachine(mach2);
		stepPart4.setGcode(
				new StringBuilder()
				.append("O7101\n")				
		        .append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart4);

		// FRESA1
		StepPart stepPart5 = new StepPart();
		stepPart5.setName("Fresagem corpo espaçador");
		stepPart5.setMachine(mach2);
		stepPart5.setGcode(
				new StringBuilder()
				.append("O7101\n")				
		        .append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart5);

		// FRESA2
		StepPart stepPart6 = new StepPart();
		stepPart6.setName("Fresagem cabeça sextavada");
		stepPart6.setMachine(mach2);
		stepPart6.setGcode(new StringBuilder()
				.append("O7101\n")
				.append("N10 G54 G0 Z30\n")
				.append("N20 M6 T4\n")
				.append("N30 G54 X0 Y0\n")
				.append("N40 M3 S1500\n")
				.append("N50 G52 X-&FMS_VAR1&\n")
				.append("N60 G90 G54 G0 X0 Y0 Z2\n")
				.append("N70 G0X0.000Y0.000\r\n"
						+ "N80 G0X0.000Y7.500Z3.000\r\n"
						+ "N90 G0Z0.000\r\n"
						+ "N100 G1Z-3.600F240.0\r\n"
						+ "N110 G1X-1.087Y7.421F360.0\r\n"
						+ "N120 X-2.204Y7.169\r\n"
						+ "N130 X-3.310Y6.730\r\n"
						+ "N140 X-4.358Y6.104\r\n"
						+ "N150 X-5.303Y5.303\r\n"
						+ "N160 X-6.104Y4.358\r\n"
						+ "N170 X-6.730Y3.310\r\n"
						+ "N180 X-7.169Y2.204\r\n"
						+ "N190 X-7.421Y1.087\r\n"
						+ "N200 X-7.500Y0.000\r\n"
						+ "N210 X-7.435Y-0.986\r\n"
						+ "N220 X-7.228Y-2.000\r\n"
						+ "N230 X-6.869Y-3.012\r\n"
						+ "N240 X-6.353Y-3.987\r\n"
						+ "N250 X-5.687Y-4.889\r\n"
						+ "N260 X-4.889Y-5.687\r\n"
						+ "N270 X-3.987Y-6.353\r\n"
						+ "N280 X-3.012Y-6.869\r\n"
						+ "N290 X-2.000Y-7.228\r\n"
						+ "N300 X-0.986Y-7.435\r\n"
						+ "N310 X0.000Y-7.500\r\n"
						+ "N320 X1.087Y-7.421\r\n"
						+ "N330 X2.204Y-7.169\r\n"
						+ "N340 X3.310Y-6.730\r\n"
						+ "N350 X4.358Y-6.104\r\n"
						+ "N360 X5.303Y-5.303\r\n"
						+ "N370 X6.104Y-4.358\r\n"
						+ "N380 X6.730Y-3.310\r\n"
						+ "N390 X7.169Y-2.204\r\n"
						+ "N400 X7.421Y-1.087\r\n"
						+ "N410 X7.500Y0.000\r\n"
						+ "N420 X7.421Y1.087\r\n"
						+ "N430 X7.169Y2.204\r\n"
						+ "N440 X6.730Y3.310\r\n"
						+ "N450 X6.104Y4.358\r\n"
						+ "N460 X5.303Y5.303\r\n"
						+ "N470 X4.358Y6.104\r\n"
						+ "N480 X3.310Y6.730\r\n"
						+ "N490 X2.204Y7.169\r\n"
						+ "N500 X1.087Y7.421\r\n"
						+ "N510 X0.000Y7.500\r\n"
						+ "N520 G1Y17.500\r\n"
						+ "N530 G1X-2.301Y17.348\r\n"
						+ "N540 X-4.667Y16.866\r\n"
						+ "N550 X-7.027Y16.027\r\n"
						+ "N560 X-9.302Y14.823\r\n"
						+ "N570 X-11.409Y13.270\r\n"
						+ "N580 X-13.270Y11.409\r\n"
						+ "N590 X-14.823Y9.302\r\n"
						+ "N600 X-16.027Y7.027\r\n"
						+ "N610 X-16.866Y4.667\r\n"
						+ "N620 X-17.348Y2.301\r\n"
						+ "N630 X-17.500Y0.000\r\n"
						+ "N640 X-17.348Y-2.301\r\n"
						+ "N650 X-16.866Y-4.667\r\n"
						+ "N660 X-16.027Y-7.027\r\n"
						+ "N670 X-14.823Y-9.302\r\n"
						+ "N680 X-13.270Y-11.409\r\n"
						+ "N690 X-11.409Y-13.270\r\n"
						+ "N700 X-9.302Y-14.823\r\n"
						+ "N710 X-7.027Y-16.027\r\n"
						+ "N720 X-4.667Y-16.866\r\n"
						+ "N730 X-2.301Y-17.348\r\n"
						+ "N740 X0.000Y-17.500\r\n"
						+ "N750 X2.301Y-17.348\r\n"
						+ "N760 X4.667Y-16.866\r\n"
						+ "N770 X7.027Y-16.027\r\n"
						+ "N780 X9.302Y-14.823\r\n"
						+ "N790 X11.409Y-13.270\r\n"
						+ "N800 X13.270Y-11.409\r\n"
						+ "N810 X14.823Y-9.302\r\n"
						+ "N820 X16.027Y-7.027\r\n"
						+ "N830 X16.866Y-4.667\r\n"
						+ "N840 X17.348Y-2.301\r\n"
						+ "N850 X17.500Y0.000\r\n"
						+ "N860 X17.348Y2.301\r\n"
						+ "N870 X16.866Y4.667\r\n"
						+ "N880 X16.027Y7.027\r\n"
						+ "N890 X14.823Y9.302\r\n"
						+ "N900 X13.270Y11.409\r\n"
						+ "N910 X11.409Y13.270\r\n"
						+ "N920 X9.302Y14.823\r\n"
						+ "N930 X7.027Y16.027\r\n"
						+ "N940 X4.667Y16.866\r\n"
						+ "N950 X2.301Y17.348\r\n"
						+ "N960 X0.000Y17.500\r\n"
						+ "N970 G0Z3.000\r\n"
						+ "N980 G0X0.000Y0.000\r\n"
						+ "N990 G0Z0.000\r\n"
						+ "N1000 G1Z-3.600F240.0\r\n"
						+ "N1010 G0Z3.000\r\n"
						+ "N1020 G0X0.000Y7.500\r\n"
						+ "N1030 G0Z0.000\r\n"
						+ "N1040 G1Z-7.200F240.0\r\n"
						+ "N1050 G1X-1.087Y7.421F360.0\r\n"
						+ "N1060 X-2.204Y7.169\r\n"
						+ "N1070 X-3.310Y6.730\r\n"
						+ "N1080 X-4.358Y6.104\r\n"
						+ "N1090 X-5.303Y5.303\r\n"
						+ "N1100 X-6.104Y4.358\r\n"
						+ "N1110 X-6.730Y3.310\r\n"
						+ "N1120 X-7.169Y2.204\r\n"
						+ "N1130 X-7.421Y1.087\r\n"
						+ "N1140 X-7.500Y0.000\r\n"
						+ "N1150 X-7.435Y-0.986\r\n"
						+ "N1160 X-7.228Y-2.000\r\n"
						+ "N1170 X-6.869Y-3.012\r\n"
						+ "N1180 X-6.353Y-3.987\r\n"
						+ "N1190 X-5.687Y-4.889\r\n"
						+ "N1200 X-4.889Y-5.687\r\n"
						+ "N1210 X-3.987Y-6.353\r\n"
						+ "N1220 X-3.012Y-6.869\r\n"
						+ "N1230 X-2.000Y-7.228\r\n"
						+ "N1240 X-0.986Y-7.435\r\n"
						+ "N1250 X0.000Y-7.500\r\n"
						+ "N1260 X1.087Y-7.421\r\n"
						+ "N1270 X2.204Y-7.169\r\n"
						+ "N1280 X3.310Y-6.730\r\n"
						+ "N1290 X4.358Y-6.104\r\n"
						+ "N1300 X5.303Y-5.303\r\n"
						+ "N1310 X6.104Y-4.358\r\n"
						+ "N1320 X6.730Y-3.310\r\n"
						+ "N1330 X7.169Y-2.204\r\n"
						+ "N1340 X7.421Y-1.087\r\n"
						+ "N1350 X7.500Y0.000\r\n"
						+ "N1360 X7.421Y1.087\r\n"
						+ "N1370 X7.169Y2.204\r\n"
						+ "N1380 X6.730Y3.310\r\n"
						+ "N1390 X6.104Y4.358\r\n"
						+ "N1400 X5.303Y5.303\r\n"
						+ "N1410 X4.358Y6.104\r\n"
						+ "N1420 X3.310Y6.730\r\n"
						+ "N1430 X2.204Y7.169\r\n"
						+ "N1440 X1.087Y7.421\r\n"
						+ "N1450 X0.000Y7.500\r\n"
						+ "N1460 G1Y17.500\r\n"
						+ "N1470 G1X-2.301Y17.348\r\n"
						+ "N1480 X-4.667Y16.866\r\n"
						+ "N1490 X-7.027Y16.027\r\n"
						+ "N1500 X-9.302Y14.823\r\n"
						+ "N1510 X-11.409Y13.270\r\n"
						+ "N1520 X-13.270Y11.409\r\n"
						+ "N1530 X-14.823Y9.302\r\n"
						+ "N1540 X-16.027Y7.027\r\n"
						+ "N1550 X-16.866Y4.667\r\n"
						+ "N1560 X-17.348Y2.301\r\n"
						+ "N1570 X-17.500Y0.000\r\n"
						+ "N1580 X-17.348Y-2.301\r\n"
						+ "N1590 X-16.866Y-4.667\r\n"
						+ "N1600 X-16.027Y-7.027\r\n"
						+ "N1610 X-14.823Y-9.302\r\n"
						+ "N1620 X-13.270Y-11.409\r\n"
						+ "N1630 X-11.409Y-13.270\r\n"
						+ "N1640 X-9.302Y-14.823\r\n"
						+ "N1650 X-7.027Y-16.027\r\n"
						+ "N1660 X-4.667Y-16.866\r\n"
						+ "N1670 X-2.301Y-17.348\r\n"
						+ "N1680 X0.000Y-17.500\r\n"
						+ "N1690 X2.301Y-17.348\r\n"
						+ "N1700 X4.667Y-16.866\r\n"
						+ "N1710 X7.027Y-16.027\r\n"
						+ "N1720 X9.302Y-14.823\r\n"
						+ "N1730 X11.409Y-13.270\r\n"
						+ "N1740 X13.270Y-11.409\r\n"
						+ "N1750 X14.823Y-9.302\r\n"
						+ "N1760 X16.027Y-7.027\r\n"
						+ "N1770 X16.866Y-4.667\r\n"
						+ "N1780 X17.348Y-2.301\r\n"
						+ "N1790 X17.500Y0.000\r\n"
						+ "N1800 X17.348Y2.301\r\n"
						+ "N1810 X16.866Y4.667\r\n"
						+ "N1820 X16.027Y7.027\r\n"
						+ "N1830 X14.823Y9.302\r\n"
						+ "N1840 X13.270Y11.409\r\n"
						+ "N1850 X11.409Y13.270\r\n"
						+ "N1860 X9.302Y14.823\r\n"
						+ "N1870 X7.027Y16.027\r\n"
						+ "N1880 X4.667Y16.866\r\n"
						+ "N1890 X2.301Y17.348\r\n"
						+ "N1900 X0.000Y17.500\r\n"
						+ "N1910 G0Z3.000\r\n"
						+ "N1920 G0X0.000Y0.000\r\n"
						+ "N1930 G0Z0.000\r\n"
						+ "N1940 G1Z-7.200F240.0\r\n"
						+ "N1950 G0Z3.000\r\n"
						+ "N1960 G0X0.000Y7.500\r\n"
						+ "N1970 G0Z0.000\r\n"
						+ "N1980 G1Z-10.800F240.0\r\n"
						+ "N1990 G1X-1.087Y7.421F360.0\r\n"
						+ "N2000 X-2.204Y7.169\r\n"
						+ "N2010 X-3.310Y6.730\r\n"
						+ "N2020 X-4.358Y6.104\r\n"
						+ "N2030 X-5.303Y5.303\r\n"
						+ "N2040 X-6.104Y4.358\r\n"
						+ "N2050 X-6.730Y3.310\r\n"
						+ "N2060 X-7.169Y2.204\r\n"
						+ "N2070 X-7.421Y1.087\r\n"
						+ "N2080 X-7.500Y0.000\r\n"
						+ "N2090 X-7.435Y-0.986\r\n"
						+ "N2100 X-7.228Y-2.000\r\n"
						+ "N2110 X-6.869Y-3.012\r\n"
						+ "N2120 X-6.353Y-3.987\r\n"
						+ "N2130 X-5.687Y-4.889\r\n"
						+ "N2140 X-4.889Y-5.687\r\n"
						+ "N2150 X-3.987Y-6.353\r\n"
						+ "N2160 X-3.012Y-6.869\r\n"
						+ "N2170 X-2.000Y-7.228\r\n"
						+ "N2180 X-0.986Y-7.435\r\n"
						+ "N2190 X0.000Y-7.500\r\n"
						+ "N2200 X1.087Y-7.421\r\n"
						+ "N2210 X2.204Y-7.169\r\n"
						+ "N2220 X3.310Y-6.730\r\n"
						+ "N2230 X4.358Y-6.104\r\n"
						+ "N2240 X5.303Y-5.303\r\n"
						+ "N2250 X6.104Y-4.358\r\n"
						+ "N2260 X6.730Y-3.310\r\n"
						+ "N2270 X7.169Y-2.204\r\n"
						+ "N2280 X7.421Y-1.087\r\n"
						+ "N2290 X7.500Y0.000\r\n"
						+ "N2300 X7.421Y1.087\r\n"
						+ "N2310 X7.169Y2.204\r\n"
						+ "N2320 X6.730Y3.310\r\n"
						+ "N2330 X6.104Y4.358\r\n"
						+ "N2340 X5.303Y5.303\r\n"
						+ "N2350 X4.358Y6.104\r\n"
						+ "N2360 X3.310Y6.730\r\n"
						+ "N2370 X2.204Y7.169\r\n"
						+ "N2380 X1.087Y7.421\r\n"
						+ "N2390 X0.000Y7.500\r\n"
						+ "N2400 G1Y17.500\r\n"
						+ "N2410 G1X-2.301Y17.348\r\n"
						+ "N2420 X-4.667Y16.866\r\n"
						+ "N2430 X-7.027Y16.027\r\n"
						+ "N2440 X-9.302Y14.823\r\n"
						+ "N2450 X-11.409Y13.270\r\n"
						+ "N2460 X-13.270Y11.409\r\n"
						+ "N2470 X-14.823Y9.302\r\n"
						+ "N2480 X-16.027Y7.027\r\n"
						+ "N2490 X-16.866Y4.667\r\n"
						+ "N2500 X-17.348Y2.301\r\n"
						+ "N2510 X-17.500Y0.000\r\n"
						+ "N2520 X-17.348Y-2.301\r\n"
						+ "N2530 X-16.866Y-4.667\r\n"
						+ "N2540 X-16.027Y-7.027\r\n"
						+ "N2550 X-14.823Y-9.302\r\n"
						+ "N2560 X-13.270Y-11.409\r\n"
						+ "N2570 X-11.409Y-13.270\r\n"
						+ "N2580 X-9.302Y-14.823\r\n"
						+ "N2590 X-7.027Y-16.027\r\n"
						+ "N2600 X-4.667Y-16.866\r\n"
						+ "N2610 X-2.301Y-17.348\r\n"
						+ "N2620 X0.000Y-17.500\r\n"
						+ "N2630 X2.301Y-17.348\r\n"
						+ "N2640 X4.667Y-16.866\r\n"
						+ "N2650 X7.027Y-16.027\r\n"
						+ "N2660 X9.302Y-14.823\r\n"
						+ "N2670 X11.409Y-13.270\r\n"
						+ "N2680 X13.270Y-11.409\r\n"
						+ "N2690 X14.823Y-9.302\r\n"
						+ "N2700 X16.027Y-7.027\r\n"
						+ "N2710 X16.866Y-4.667\r\n"
						+ "N2720 X17.348Y-2.301\r\n"
						+ "N2730 X17.500Y0.000\r\n"
						+ "N2740 X17.348Y2.301\r\n"
						+ "N2750 X16.866Y4.667\r\n"
						+ "N2760 X16.027Y7.027\r\n"
						+ "N2770 X14.823Y9.302\r\n"
						+ "N2780 X13.270Y11.409\r\n"
						+ "N2790 X11.409Y13.270\r\n"
						+ "N2800 X9.302Y14.823\r\n"
						+ "N2810 X7.027Y16.027\r\n"
						+ "N2820 X4.667Y16.866\r\n"
						+ "N2830 X2.301Y17.348\r\n"
						+ "N2840 X0.000Y17.500\r\n"
						+ "N2850 G0Z3.000\r\n"
						+ "N2860 G0X0.000Y0.000\r\n"
						+ "N2870 G0Z0.000\r\n"
						+ "N2880 G1Z-10.800F240.0\r\n"
						+ "N2890 G0Z3.000\r\n"
						+ "N2900 G0X0.000Y7.500\r\n"
						+ "N2910 G0Z0.000\r\n"
						+ "N2920 G1Z-14.400F240.0\r\n"
						+ "N2930 G1X-1.087Y7.421F360.0\r\n"
						+ "N2940 X-2.204Y7.169\r\n"
						+ "N2950 X-3.310Y6.730\r\n"
						+ "N2960 X-4.358Y6.104\r\n"
						+ "N2970 X-5.303Y5.303\r\n"
						+ "N2980 X-6.104Y4.358\r\n"
						+ "N2990 X-6.730Y3.310\r\n"
						+ "N3000 X-7.169Y2.204\r\n"
						+ "N3010 X-7.421Y1.087\r\n"
						+ "N3020 X-7.500Y0.000\r\n"
						+ "N3030 X-7.435Y-0.986\r\n"
						+ "N3040 X-7.228Y-2.000\r\n"
						+ "N3050 X-6.869Y-3.012\r\n"
						+ "N3060 X-6.353Y-3.987\r\n"
						+ "N3070 X-5.687Y-4.889\r\n"
						+ "N3080 X-4.889Y-5.687\r\n"
						+ "N3090 X-3.987Y-6.353\r\n"
						+ "N3100 X-3.012Y-6.869\r\n"
						+ "N3110 X-2.000Y-7.228\r\n"
						+ "N3120 X-0.986Y-7.435\r\n"
						+ "N3130 X0.000Y-7.500\r\n"
						+ "N3140 X1.087Y-7.421\r\n"
						+ "N3150 X2.204Y-7.169\r\n"
						+ "N3160 X3.310Y-6.730\r\n"
						+ "N3170 X4.358Y-6.104\r\n"
						+ "N3180 X5.303Y-5.303\r\n"
						+ "N3190 X6.104Y-4.358\r\n"
						+ "N3200 X6.730Y-3.310\r\n"
						+ "N3210 X7.169Y-2.204\r\n"
						+ "N3220 X7.421Y-1.087\r\n"
						+ "N3230 X7.500Y0.000\r\n"
						+ "N3240 X7.421Y1.087\r\n"
						+ "N3250 X7.169Y2.204\r\n"
						+ "N3260 X6.730Y3.310\r\n"
						+ "N3270 X6.104Y4.358\r\n"
						+ "N3280 X5.303Y5.303\r\n"
						+ "N3290 X4.358Y6.104\r\n"
						+ "N3300 X3.310Y6.730\r\n"
						+ "N3310 X2.204Y7.169\r\n"
						+ "N3320 X1.087Y7.421\r\n"
						+ "N3330 X0.000Y7.500\r\n"
						+ "N3340 G1Y17.500\r\n"
						+ "N3350 G1X-2.301Y17.348\r\n"
						+ "N3360 X-4.667Y16.866\r\n"
						+ "N3370 X-7.027Y16.027\r\n"
						+ "N3380 X-9.302Y14.823\r\n"
						+ "N3390 X-11.409Y13.270\r\n"
						+ "N3400 X-13.270Y11.409\r\n"
						+ "N3410 X-14.823Y9.302\r\n"
						+ "N3420 X-16.027Y7.027\r\n"
						+ "N3430 X-16.866Y4.667\r\n"
						+ "N3440 X-17.348Y2.301\r\n"
						+ "N3450 X-17.500Y0.000\r\n"
						+ "N3460 X-17.348Y-2.301\r\n"
						+ "N3470 X-16.866Y-4.667\r\n"
						+ "N3480 X-16.027Y-7.027\r\n"
						+ "N3490 X-14.823Y-9.302\r\n"
						+ "N3500 X-13.270Y-11.409\r\n"
						+ "N3510 X-11.409Y-13.270\r\n"
						+ "N3520 X-9.302Y-14.823\r\n"
						+ "N3530 X-7.027Y-16.027\r\n"
						+ "N3540 X-4.667Y-16.866\r\n"
						+ "N3550 X-2.301Y-17.348\r\n"
						+ "N3560 X0.000Y-17.500\r\n"
						+ "N3570 X2.301Y-17.348\r\n"
						+ "N3580 X4.667Y-16.866\r\n"
						+ "N3590 X7.027Y-16.027\r\n"
						+ "N3600 X9.302Y-14.823\r\n"
						+ "N3610 X11.409Y-13.270\r\n"
						+ "N3620 X13.270Y-11.409\r\n"
						+ "N3630 X14.823Y-9.302\r\n"
						+ "N3640 X16.027Y-7.027\r\n"
						+ "N3650 X16.866Y-4.667\r\n"
						+ "N3660 X17.348Y-2.301\r\n"
						+ "N3670 X17.500Y0.000\r\n"
						+ "N3680 X17.348Y2.301\r\n"
						+ "N3690 X16.866Y4.667\r\n"
						+ "N3700 X16.027Y7.027\r\n"
						+ "N3710 X14.823Y9.302\r\n"
						+ "N3720 X13.270Y11.409\r\n"
						+ "N3730 X11.409Y13.270\r\n"
						+ "N3740 X9.302Y14.823\r\n"
						+ "N3750 X7.027Y16.027\r\n"
						+ "N3760 X4.667Y16.866\r\n"
						+ "N3770 X2.301Y17.348\r\n"
						+ "N3780 X0.000Y17.500\r\n"
						+ "N3790 G0Z3.000\r\n"
						+ "N3800 G0X0.000Y0.000\r\n"
						+ "N3810 G0Z0.000\r\n"
						+ "N3820 G1Z-14.400F240.0\r\n"
						+ "N3830 G0Z3.000\r\n"
						+ "N3840 G0X0.000Y7.500\r\n"
						+ "N3850 G0Z0.000\r\n"
						+ "N3860 G1Z-18.000F240.0\r\n"
						+ "N3870 G1X-1.087Y7.421F360.0\r\n"
						+ "N3880 X-2.204Y7.169\r\n"
						+ "N3890 X-3.310Y6.730\r\n"
						+ "N3900 X-4.358Y6.104\r\n"
						+ "N3910 X-5.303Y5.303\r\n"
						+ "N3920 X-6.104Y4.358\r\n"
						+ "N3930 X-6.730Y3.310\r\n"
						+ "N3940 X-7.169Y2.204\r\n"
						+ "N3950 X-7.421Y1.087\r\n"
						+ "N3960 X-7.500Y0.000\r\n"
						+ "N3970 X-7.435Y-0.986\r\n"
						+ "N3980 X-7.228Y-2.000\r\n"
						+ "N3990 X-6.869Y-3.012\r\n"
						+ "N4000 X-6.353Y-3.987\r\n"
						+ "N4010 X-5.687Y-4.889\r\n"
						+ "N4020 X-4.889Y-5.687\r\n"
						+ "N4030 X-3.987Y-6.353\r\n"
						+ "N4040 X-3.012Y-6.869\r\n"
						+ "N4050 X-2.000Y-7.228\r\n"
						+ "N4060 X-0.986Y-7.435\r\n"
						+ "N4070 X0.000Y-7.500\r\n"
						+ "N4080 X1.087Y-7.421\r\n"
						+ "N4090 X2.204Y-7.169\r\n"
						+ "N4100 X3.310Y-6.730\r\n"
						+ "N4110 X4.358Y-6.104\r\n"
						+ "N4120 X5.303Y-5.303\r\n"
						+ "N4130 X6.104Y-4.358\r\n"
						+ "N4140 X6.730Y-3.310\r\n"
						+ "N4150 X7.169Y-2.204\r\n"
						+ "N4160 X7.421Y-1.087\r\n"
						+ "N4170 X7.500Y0.000\r\n"
						+ "N4180 X7.421Y1.087\r\n"
						+ "N4190 X7.169Y2.204\r\n"
						+ "N4200 X6.730Y3.310\r\n"
						+ "N4210 X6.104Y4.358\r\n"
						+ "N4220 X5.303Y5.303\r\n"
						+ "N4230 X4.358Y6.104\r\n"
						+ "N4240 X3.310Y6.730\r\n"
						+ "N4250 X2.204Y7.169\r\n"
						+ "N4260 X1.087Y7.421\r\n"
						+ "N4270 X0.000Y7.500\r\n"
						+ "N4280 G1Y17.500\r\n"
						+ "N4290 G1X-2.301Y17.348\r\n"
						+ "N4300 X-4.667Y16.866\r\n"
						+ "N4310 X-7.027Y16.027\r\n"
						+ "N4320 X-9.302Y14.823\r\n"
						+ "N4330 X-11.409Y13.270\r\n"
						+ "N4340 X-13.270Y11.409\r\n"
						+ "N4350 X-14.823Y9.302\r\n"
						+ "N4360 X-16.027Y7.027\r\n"
						+ "N4370 X-16.866Y4.667\r\n"
						+ "N4380 X-17.348Y2.301\r\n"
						+ "N4390 X-17.500Y0.000\r\n"
						+ "N4400 X-17.348Y-2.301\r\n"
						+ "N4410 X-16.866Y-4.667\r\n"
						+ "N4420 X-16.027Y-7.027\r\n"
						+ "N4430 X-14.823Y-9.302\r\n"
						+ "N4440 X-13.270Y-11.409\r\n"
						+ "N4450 X-11.409Y-13.270\r\n"
						+ "N4460 X-9.302Y-14.823\r\n"
						+ "N4470 X-7.027Y-16.027\r\n"
						+ "N4480 X-4.667Y-16.866\r\n"
						+ "N4490 X-2.301Y-17.348\r\n"
						+ "N4500 X0.000Y-17.500\r\n"
						+ "N4510 X2.301Y-17.348\r\n"
						+ "N4520 X4.667Y-16.866\r\n"
						+ "N4530 X7.027Y-16.027\r\n"
						+ "N4540 X9.302Y-14.823\r\n"
						+ "N4550 X11.409Y-13.270\r\n"
						+ "N4560 X13.270Y-11.409\r\n"
						+ "N4570 X14.823Y-9.302\r\n"
						+ "N4580 X16.027Y-7.027\r\n"
						+ "N4590 X16.866Y-4.667\r\n"
						+ "N4600 X17.348Y-2.301\r\n"
						+ "N4610 X17.500Y0.000\r\n"
						+ "N4620 X17.348Y2.301\r\n"
						+ "N4630 X16.866Y4.667\r\n"
						+ "N4640 X16.027Y7.027\r\n"
						+ "N4650 X14.823Y9.302\r\n"
						+ "N4660 X13.270Y11.409\r\n"
						+ "N4670 X11.409Y13.270\r\n"
						+ "N4680 X9.302Y14.823\r\n"
						+ "N4690 X7.027Y16.027\r\n"
						+ "N4700 X4.667Y16.866\r\n"
						+ "N4710 X2.301Y17.348\r\n"
						+ "N4720 X0.000Y17.500\r\n"
						+ "N4730 G0Z3.000\r\n"
						+ "N4740 G0X0.000Y0.000\r\n"
						+ "N4750 G0Z0.000\r\n"
						+ "N4760 G1Z-18.000F240.0\r\n"
						+ "N4770 G0Z3.000\r\n"
						+ "")
				.append("N4780 G1 X&FMS_VAR1& Y&FMS_VAR1& F500\n")
				.append("N4790 Z-28\n")				
				.append("N4800 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4810 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4820 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4830 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4840 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4850 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4860 X&FMS_VAR1& Y&FMS_VAR1&\n")
				.append("N4870 G54 G90 G0 Z30\n")				
				.append("N4880 M30\n").toString());
		stepPartRepository_.save(stepPart6);

		// FRESA3
		StepPart stepPart7 = new StepPart();
		stepPart7.setName("Fresagem suporte chassi");
		stepPart7.setMachine(mach2);
		stepPart7.setGcode(new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart7);

		// FRESA4
		StepPart stepPart8 = new StepPart();
		stepPart8.setName("Fresagem base perfurada");
		stepPart8.setMachine(mach2);
		stepPart8.setGcode(new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart8);

		// TORNO 1
		StepPart stepPart9 = new StepPart();
		stepPart9.setName("Torneamento corpo pino");
		stepPart9.setMachine(mach);
		stepPart9.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart9);

		// TORNO 2
		StepPart stepPart10 = new StepPart();
		stepPart10.setName("Torneamento cilindro");
		stepPart10.setMachine(mach);
		stepPart10.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N10 G90 G40 G95\n")
				.append("N20 G54 X100 Z100\n")
				.append("N30 T0101\n")
				.append("N40 G96 S275 M4\n")
				.append("N50 G0 X47 Z2\n")
				.append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n")
				.append("N70 X&FMS_VAR1&\n")
				.append("N80 X&FMS_VAR1&\n")
				.append("N90 X&FMS_VAR1&\n")
				.append("N100 X&FMS_VAR1&\n")
				.append("N110 G0 G28 U0 W0\n")
				.append("N120 T0303\n")
				.append("N130 G96 S275 M4\n")
				.append("N140 G0 X&FMS_VAR1& Z2\n")
				.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n")
				.append("N160 X&FMS_VAR1& Z-3\n")
				.append("N170 Z-40\n")
				.append("N180 X47\n")
				.append("N190 G0 G28 U0 W0\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart10);

		// TORNO 3
		StepPart stepPart11 = new StepPart();
		stepPart11.setName("Torneamento adaptador");
		stepPart11.setMachine(mach);
		stepPart11.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart11);

		// TORNO 4
		StepPart stepPart12 = new StepPart();
		stepPart12.setName("Torneamento engrenagem");
		stepPart12.setMachine(mach);
		stepPart12.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart12);

		// FRESA 6
		StepPart stepPart13 = new StepPart();
		stepPart13.setName("Fresagem");
		stepPart13.setMachine(mach2);
		stepPart13.setGcode(
				new StringBuilder()
				.append("O7101\n")
				.append("N200 M30\n").toString());
		stepPartRepository_.save(stepPart13);

		// PROCESS PART

		List<StepPart> stepsList1 = new ArrayList<>();
		List<StepPart> stepsList2 = new ArrayList<>();
		List<StepPart> stepsList3 = new ArrayList<>();
		List<StepPart> stepsList4 = new ArrayList<>();
		List<StepPart> stepsList5 = new ArrayList<>();
		List<StepPart> stepsList6 = new ArrayList<>();
		List<StepPart> stepsList7 = new ArrayList<>();
		List<StepPart> stepsList8 = new ArrayList<>();
		List<StepPart> stepsList9 = new ArrayList<>();
		List<StepPart> stepsList10 = new ArrayList<>();
		List<StepPart> stepsList11 = new ArrayList<>();

		// FLEX1
		stepsList1.add(stepPart1);
		stepsList1.add(stepPart2);

		// FLEX2
		stepsList2.add(stepPart3);
		stepsList2.add(stepPart4);

		// FRESA1
		stepsList3.add(stepPart5);

		// FRESA2
		stepsList4.add(stepPart6);

		// FRESA3
		stepsList5.add(stepPart7);

		// FRESA4
		stepsList6.add(stepPart8);

		// TORNO1
		stepsList7.add(stepPart9);

		// TORNO2
		stepsList8.add(stepPart10);

		// TORNO3
		stepsList9.add(stepPart11);

		// TORNO4
		stepsList10.add(stepPart12);

		// FRESA6
		stepsList11.add(stepPart13);

		// FLEX1
		ProcessPart processPart1 = new ProcessPart();
		processPart1.setSteps(stepsList1);
		processPart1.setCustomizations(totalCustomLists.get(0));
		processPartRepository_.save(processPart1);

		// FLEX2
		ProcessPart processPart2 = new ProcessPart();
		processPart2.setSteps(stepsList2);
		processPart2.setCustomizations(totalCustomLists.get(3));
		processPartRepository_.save(processPart2);

		// FRESA1
		ProcessPart processPart3 = new ProcessPart();
		processPart3.setSteps(stepsList3);
		processPart3.setCustomizations(totalCustomLists.get(4));
		processPartRepository_.save(processPart3);

		// FRESA2
		ProcessPart processPart4 = new ProcessPart();
		processPart4.setSteps(stepsList4);
		processPart4.setCustomizations(totalCustomLists.get(1));
		processPartRepository_.save(processPart4);

		// FRESA3
		ProcessPart processPart5 = new ProcessPart();
		processPart5.setSteps(stepsList5);
		processPart5.setCustomizations(totalCustomLists.get(5));
		processPartRepository_.save(processPart5);

		// FRESA4
		ProcessPart processPart6 = new ProcessPart();
		processPart6.setSteps(stepsList6);
		processPart6.setCustomizations(totalCustomLists.get(6));
		processPartRepository_.save(processPart6);

		// TORNO1
		ProcessPart processPart7 = new ProcessPart();
		processPart7.setSteps(stepsList7);
		processPart7.setCustomizations(totalCustomLists.get(8));
		processPartRepository_.save(processPart7);

		// TORNO2
		ProcessPart processPart8 = new ProcessPart();
		processPart8.setSteps(stepsList8);
		processPart8.setCustomizations(totalCustomLists.get(2));
		processPartRepository_.save(processPart8);

		// TORNO3
		ProcessPart processPart9 = new ProcessPart();
		processPart9.setSteps(stepsList9);
		processPart9.setCustomizations(totalCustomLists.get(9));
		processPartRepository_.save(processPart9);

		// TORNO4
		ProcessPart processPart10 = new ProcessPart();
		processPart10.setSteps(stepsList10);
		processPart10.setCustomizations(totalCustomLists.get(10));
		processPartRepository_.save(processPart10);

		// FRESA6
		ProcessPart processPart11 = new ProcessPart();
		processPart11.setSteps(stepsList11);
		processPart11.setCustomizations(totalCustomLists.get(7));
		processPartRepository_.save(processPart11);

		// PARTS

		// FLEX1		
		StringBuilder strPart1 = new StringBuilder();
		strPart1
		.append(		
		"<model-viewer src=\"/images/Flex1.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
	    + "shadow-intensity=\"1\" >\r\n")
		.append(
	    " <div class=\"spotDimension\" slot=\"hotspot-1\" data-position=\"-10.4m 0.0m 0.45m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Dimensão Sextavado\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">A</div>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotA\" slot=\"hotspot-2\" data-position=\"-8.4m 8.2m 0.45m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotA\" slot=\"hotspot-3\" data-position=\"-8.4m -8.4m 0m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-4\" data-position=\"31.2m -1m 0m\"\r\n"
	    + "            data-normal=\"0.692672311072419m 0.7212524311734374m 0m\" data-visibility-attribute=\"visible\"\r\n"
	    + "            data-bs-toggle=\"tooltip\" title=\"Diâmetro\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">B</div>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotB\" slot=\"hotspot-5\" data-position=\"29m 4.75m 0m\" data-normal=\"0.69m 0.7m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotB\" slot=\"hotspot-6\" data-position=\"29m -4.75m 0m\" data-normal=\"0.69m 0.7m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
				
		
		Part part1 = new Part();
		part1.setName("Pino Sextavado");
		part1.setPathImg(strPart1.toString());
		part1.setProcess(processPart1);
		part1.setType(orderTp5);
		part1.setModel(models1);
		partRepository_.save(part1);

		// FLEX2
		StringBuilder strPart2 = new StringBuilder();
		strPart2
		.append(
		"<model-viewer class=\"scalePart\" customScale=\"0.7 0.7 0.7\" src=\"/images/Flex2.glb\" camera-controls\r\n"
		+ "          ar-modes=\"scene-viewer webxr quick-look\" shadow-intensity=\"1\" min-camera-orbit=\"auto 1deg auto\"\r\n"
		+ "          max-camera-orbit=\"auto 169deg auto\" min-field-of-view=\"auto\" max-field-of-view=\"auto\" camera-target=\"0m 0m 0m\"\r\n"
		+ "          exposure=\"1\" ar>")
		.append(
	    "<div class=\"spotAppointer\" slot=\"hotspot-1\" data-position=\"-4.8m 8.1m -2.8m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-2\" data-position=\"4.8m 8.1m 2.8m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-3\" data-position=\"0.5m 9.6m 0.5m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Dimensão Sextavado\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">A</div>\r\n"
	    + "          </div>\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-4\" data-position=\"3.3m -2.7m 2.8m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-5\" data-position=\"-3.3m -2.7m -2.8m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-6\" data-position=\"1.5m -3.8m -0.5m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Dimensão Cilindro\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">B</div>\r\n"
	    + "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part2 = new Part();
		part2.setName("Adaptador sextavado");
		part2.setPathImg(strPart2.toString());
		part2.setProcess(processPart2);
		part2.setType(orderTp5);
		part2.setModel(models1);
		partRepository_.save(part2);

		// FRESA1
		
		StringBuilder strPart3 = new StringBuilder();
		strPart3
		.append(
		"<model-viewer class=\"scalePart\" customScale=\"0.9 0.9 0.9\" src=\"/images/Fresa1.glb\" camera-controls ar\r\n"
		+ "          ar-modes=\"scene-viewer webxr quick-look\" min-camera-orbit=\"auto auto auto\" max-camera-orbit=\"auto auto auto\"\r\n>")		
		.append(
	    "<div class=\"spotAppointer\" slot=\"hotspot-1\" data-position=\"5.4m 6.3m 0m\" data-normal=\"0m 0m 1m\"\r\n"
	    + "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-2\" data-position=\"5.4m 6.3m -8m\" data-normal=\"0m 1m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-3\" data-position=\"-0.2m 6.3m -8m\" data-normal=\"0m 1m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer\" slot=\"hotspot-4\" data-position=\"5.4m 0m 0m\" data-normal=\"0m 0m 1m\"\r\n"
	    + "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i></div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-5\" data-position=\"6.3m 3.15m 0m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Altura\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">A</div>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-6\" data-position=\"3.15m 6.75m -8m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Comprimento\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">B</div>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotDimension\" slot=\"hotspot-7\" data-position=\"6.8m 6.3m -3.5m\" data-normal=\"0m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Largura\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">C</div>\r\n"
	    + "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		
		Part part3 = new Part();
		part3.setName("Espaçador");
		part3.setPathImg(strPart3.toString());
		//part3.setPathImg("DamagedHelmet.glb");
		part3.setProcess(processPart3);
		part3.setType(orderTp3);
		part3.setModel(models1);
		partRepository_.save(part3);

		// FRESA2
		
		StringBuilder strPart4 = new StringBuilder();
		strPart4
		.append(
		"<model-viewer src=\"/images/Fresa2.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\">")
		.append(
	    "<div class=\"spotDimension\" slot=\"hotspot-1\" data-position=\"-10.4m 0.0m 0.45m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Dimensão Sextavado\">\r\n"
	    + "            <div class=\"HotspotAnnotation\">A</div>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotA\" slot=\"hotspot-2\" data-position=\"-8.4m 8.2m 0.45m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>\r\n"
	    + "\r\n"
	    + "          <div class=\"spotAppointer spotA\" slot=\"hotspot-3\" data-position=\"-8.4m -8.4m 0m\" data-normal=\"-1m 0m 0m\"\r\n"
	    + "            data-visibility-attribute=\"visible\">\r\n"
	    + "            <i class=\"fas fa-bullseye\"></i>\r\n"
	    + "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part4 = new Part();
		part4.setName("Pino sextavado");
		part4.setPathImg(strPart4.toString());
		part4.setProcess(processPart4);
		part4.setType(orderTp3);
		part4.setModel(models1);
		partRepository_.save(part4);

		// FRESA3
		
		StringBuilder strPart5 = new StringBuilder();
		strPart5
		.append(
		"<model-viewer src=\"/images/Fresa3.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\" >")		
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		
		Part part5 = new Part();
		part5.setName("Alojamento Engrenagem");
		part5.setPathImg(strPart5.toString());
		part5.setProcess(processPart5);
		part5.setType(orderTp3);
		part5.setModel(models1);
		partRepository_.save(part5);

		// FRESA4
		
		StringBuilder strPart6 = new StringBuilder();
		strPart6
		.append(
		"<model-viewer src=\"/images/Fresa4.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\" >")		
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part6 = new Part();
		part6.setName("Fixação");
		part6.setPathImg(strPart6.toString());
		part6.setProcess(processPart6);
		part6.setType(orderTp3);
		part6.setModel(models1);
		partRepository_.save(part6);

		// TORNO1
		
		StringBuilder strPart7 = new StringBuilder();
		strPart7
		.append(
		"<model-viewer src=\"/images/Torno1.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\" >")		
		.append(
		" <div class=\"spotAppointer\" slot=\"hotspot-1\" data-position=\"10.5m 11.6m -6.8m\" data-normal=\"0m 0m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i></div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotAppointer\" slot=\"hotspot-2\" data-position=\"10.5m 2.55m -6.8 m\" data-normal=\"0m 0m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i></div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotAppointer\" slot=\"hotspot-3\" data-position=\"-0.5m 11.6m -6.8m\" data-normal=\"0m 0m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\"><i class=\"fas fa-bullseye\"></i></div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotDimension\" slot=\"hotspot-4\" data-position=\"12m 6.9m -6.8m\" data-normal=\"0m 0m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Diâmetro Externo\">\r\n"
		+ "            <div class=\"HotspotAnnotation\">A</div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotDimension\" slot=\"hotspot-5\" data-position=\"5.5m 11.6m -6.8m\" data-normal=\"0m 0m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\" data-bs-toggle=\"tooltip\" title=\"Comprimento\">\r\n"
		+ "            <div class=\"HotspotAnnotation\">B</div>\r\n"
		+ "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part7 = new Part();
		part7.setName("Eixo");
		part7.setPathImg(strPart7.toString());
		part7.setProcess(processPart7);
		part7.setType(orderTp);
		part7.setModel(models1);
		partRepository_.save(part7);

		// TORNO2
		
		StringBuilder strPart8 = new StringBuilder();
		strPart8
		.append(
		"<model-viewer src=\"/images/Torno2.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\">")
		.append(
		"<div class=\"spotDimension\" slot=\"hotspot-4\" data-position=\"31.2m -1m 0m\"\r\n"
		+ "            data-normal=\"0.692672311072419m 0.7212524311734374m 0m\" data-visibility-attribute=\"visible\"\r\n"
		+ "            data-bs-toggle=\"tooltip\" title=\"Diâmetro\">\r\n"
		+ "            <div class=\"HotspotAnnotation\">A</div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotAppointer spotA\" slot=\"hotspot-5\" data-position=\"29m 4.75m 0m\" data-normal=\"0.69m 0.7m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\">\r\n"
		+ "            <i class=\"fas fa-bullseye\"></i>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "          <div class=\"spotAppointer spotA\" slot=\"hotspot-6\" data-position=\"29m -4.75m 0m\" data-normal=\"0.69m 0.7m 0m\"\r\n"
		+ "            data-visibility-attribute=\"visible\">\r\n"
		+ "            <i class=\"fas fa-bullseye\"></i>\r\n"
		+ "          </div>")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part8 = new Part();
		part8.setName("Parafuso sextavado");
		part8.setPathImg(strPart8.toString());
		part8.setProcess(processPart8);
		part8.setType(orderTp);
		part8.setModel(models1);
		partRepository_.save(part8);

		
		// TORNO3
		
		StringBuilder strPart9 = new StringBuilder();
		strPart9
		.append(
		"<model-viewer src=\"/images/Torno3.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\" >")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part9 = new Part();
		part9.setName("Engrenagem Cilíndrica 1");
		part9.setPathImg(strPart9.toString());
		part9.setProcess(processPart9);
		part9.setType(orderTp);
		part9.setModel(models1);
		partRepository_.save(part9);

		
		// TORNO4
		
		StringBuilder strPart10 = new StringBuilder();
		strPart10
		.append(
		"<model-viewer src=\"/images/Torno4.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\" >")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part10 = new Part();
		part10.setName("Engrenagem Cilíndrica 2");
		part10.setPathImg(strPart10.toString());
		part10.setProcess(processPart10);
		part10.setType(orderTp);
		part10.setModel(models1);
		partRepository_.save(part10);

		
		// FRESA6
		
		StringBuilder strPart11 = new StringBuilder();
		strPart11
		.append(
		"<model-viewer src=\"/images/Fresa6.glb\" camera-controls ar ar-modes=\"scene-viewer webxr quick-look\"\r\n"
		+ "          shadow-intensity=\"1\" >")
		.append(
		"			<div class=\"progress-bar hide\" slot=\"progress-bar\">\r\n"
		+ "            <div class=\"update-bar\"></div>\r\n"
		+ "          </div>\r\n"
		+ "\r\n"
		+ "        </model-viewer>");
		
		Part part11 = new Part();
		part11.setName("Suporte");
		part11.setPathImg(strPart11.toString());
		part11.setProcess(processPart11);
		part11.setType(orderTp3);
		part11.setModel(models1);
		partRepository_.save(part11);


		// STEP ORDER
		StepOrder stepOrder1 = new StepOrder();
		stepOrder1.setName("Torneamento corpo Parafuso");
		stepOrder1.setMachine(mach);
		stepOrder1.setConcluded(false);
		stepOrder1.setGcode(
				new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n").append("N20 G54 X100 Z100\n")
						.append("N30 T0101\n").append("N40 G96 S275 M4\n").append("N50 G0 X47 Z2\n")
						.append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n").append("N70 X&FMS_VAR1&\n").append("N80 X&FMS_VAR1&\n")
						.append("N90 X&FMS_VAR1&\n").append("N100 X&FMS_VAR1&\n").append("N110 G0 G28 U0 W0\n")
						.append("N120 T0303\n").append("N130 G96 S275 M4\n").append("N140 G0 X&FMS_VAR1& Z2\n")
						.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n").append("N160 X&FMS_VAR1& Z-3\n").append("N170 Z-40\n")
						.append("N180 X47\n").append("N190 G0 G28 U0 W0\n").append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder1);

		StepOrder stepOrder2 = new StepOrder();
		stepOrder2.setName("Fresagem cabeça parafuso");
		stepOrder2.setMachine(mach2);
		stepOrder2.setConcluded(false);
		stepOrder2.setGcode(new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n")
				.append("N20 G54 X100 Z100\n").append("N30 T0101\n").append("N40 G96 S275 M4\n")
				.append("N50 G0 X47 Z2\n").append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n").append("N70 X&FMS_VAR1&\n")
				.append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder2);

		StepOrder stepOrder3 = new StepOrder();
		stepOrder3.setName("Torneamento pino");
		stepOrder3.setMachine(mach);
		stepOrder3.setConcluded(true);
		stepOrder3.setGcode(
				new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n").append("N20 G54 X100 Z100\n")
						.append("N30 T0101\n").append("N40 G96 S275 M4\n").append("N50 G0 X47 Z2\n")
						.append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n").append("N70 X&FMS_VAR1&\n").append("N80 X&FMS_VAR1&\n")
						.append("N90 X&FMS_VAR1&\n").append("N100 X&FMS_VAR1&\n").append("N110 G0 G28 U0 W0\n")
						.append("N120 T0303\n").append("N130 G96 S275 M4\n").append("N140 G0 X&FMS_VAR1& Z2\n")
						.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n").append("N160 X&FMS_VAR1& Z-3\n").append("N170 Z-40\n")
						.append("N180 X47\n").append("N190 G0 G28 U0 W0\n").append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder3);

		StepOrder stepOrder4 = new StepOrder();
		stepOrder4.setName("Fresagem base morsa");
		stepOrder4.setMachine(mach2);
		stepOrder4.setConcluded(true);
		stepOrder4.setGcode(
				new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n").append("N140 G0 X&FMS_VAR1& Z2\n")
						.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n").append("N160 X&FMS_VAR1& Z-3\n").append("N170 Z-40\n")
						.append("N180 X47\n").append("N190 G0 G28 U0 W0\n").append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder4);

		StepOrder stepOrder5 = new StepOrder();
		stepOrder5.setName("Fresagem corpo engrenagem");
		stepOrder5.setMachine(mach2);
		stepOrder5.setConcluded(false);
		stepOrder5.setGcode(
				new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n").append("N20 G54 X100 Z100\n")
						.append("N30 T0101\n").append("N40 G96 S275 M4\n").append("N50 G0 X47 Z2\n")
						.append("N60 G20 X&FMS_VAR1& Z-40 F0.25\n").append("N70 X&FMS_VAR1&\n").append("N80 X&FMS_VAR1&\n")
						.append("N90 X&FMS_VAR1&\n").append("N100 X&FMS_VAR1&\n").append("N110 G0 G28 U0 W0\n")
						.append("N120 T0303\n").append("N130 G96 S275 M4\n").append("N140 G0 X&FMS_VAR1& Z2\n")
						.append("N150 G1 X&FMS_VAR1& Z0 F0.1\n").append("N160 X&FMS_VAR1& Z-3\n").append("N170 Z-40\n")
						.append("N180 X47\n").append("N190 G0 G28 U0 W0\n").append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder5);

		StepOrder stepOrder6 = new StepOrder();
		stepOrder6.setName("Torneamento pino engrenagem");
		stepOrder6.setMachine(mach);
		stepOrder6.setConcluded(true);
		stepOrder6.setGcode(new StringBuilder().append("O7101\n").append("N10 G90 G40 G95\n").append("N170 Z-40\n")
				.append("N180 X47\n").append("N190 G0 G28 U0 W0\n").append("N200 M30\n").toString());
		stepOrderRepository_.save(stepOrder6);

		// PROCESS ORDER

		List<StepOrder> stepsOList1 = new ArrayList<>();
		List<StepOrder> stepsOList2 = new ArrayList<>();
		List<StepOrder> stepsOList3 = new ArrayList<>();
		List<StepOrder> stepsOList4 = new ArrayList<>();

		stepsOList1.add(stepOrder1);
		stepsOList1.add(stepOrder2);

		stepsOList2.add(stepOrder3);

		stepsOList3.add(stepOrder4);

		stepsOList4.add(stepOrder5);
		stepsOList4.add(stepOrder6);

		// torno - centro
		ProcessOrder processOrder1 = new ProcessOrder();
		processOrder1.setSteps(stepsOList1);
		processOrder1.setConcluded(false);
		processOrderRepository_.save(processOrder1);

		// torno
		ProcessOrder processOrder2 = new ProcessOrder();
		processOrder2.setSteps(stepsOList2);
		processOrder2.setConcluded(false);
		processOrderRepository_.save(processOrder2);

		// centro
		ProcessOrder processOrder3 = new ProcessOrder();
		processOrder3.setSteps(stepsOList3);
		processOrder3.setConcluded(true);
		processOrderRepository_.save(processOrder3);

		// centro-torno
		ProcessOrder processOrder4 = new ProcessOrder();
		processOrder4.setSteps(stepsOList4);
		processOrder4.setConcluded(true);
		processOrderRepository_.save(processOrder4);

		// ORDER
		Order order = new Order();
		order.setOrdername("peca1");
		order.setType(orderTp5);
		order.setUser(myuser);
		order.setUnits(2);
		order.setUnitsProduced(2);
		order.setInputDate(LocalDateTime.now());
		order.setDimensions("30mm");
		order.setManufacturing(false);
		order.setProcess(processOrder1);
		order.setModel(model1);
		order.setProduced(true);


		Order order1 = new Order();
		order1.setOrdername("peca2");
		order1.setType(orderTp);
		order1.setUser(myuser2);
		order1.setUnits(3);
		order1.setUnitsProduced(3);
		order1.setInputDate(LocalDateTime.now());
		order1.setDimensions("35mm");
		order1.setManufacturing(false);
		order1.setProcess(processOrder2);
		order1.setModel(model1);
		order1.setProduced(true);


		Order order2 = new Order();
		order2.setOrdername("peca3");
		order2.setType(orderTp3);
		order2.setUser(myuser);
		order2.setUnits(5);
		order2.setUnitsProduced(5);
		order2.setInputDate(LocalDateTime.now());
		order2.setDimensions("40mm");
		order2.setManufacturing(false);
		order2.setProcess(processOrder3);
		order2.setModel(model2);
		order2.setProduced(true);

		
		Order order3 = new Order();
		order3.setOrdername("peca4");
		order3.setType(orderTp5);
		order3.setUser(myuser);
		order3.setUnits(5);
		order3.setUnitsProduced(5);
		order3.setInputDate(LocalDateTime.now());
		order3.setDimensions("45mm");
		order3.setManufacturing(false);
		order3.setProcess(processOrder4);
		order3.setModel(model2);
		order3.setProduced(true);
		
		List<Order> ordersList = new ArrayList<>();
		ordersList.add(order);
		ordersList.add(order1);
		ordersList.add(order2);
		ordersList.add(order3);

		for (Order myorder : ordersList) {
			Order saved = orderRepository_.save(myorder);
			System.out.println("Ordens:");
			System.out.println(saved.getId());
		}
		
			
		
		/*MANUT VARIABLES TURN*/
		ManutVariables manutTurn = new ManutVariables();
		manutTurn.setMachine(mach);
		manutTurn.setCounterClamping(0L);
		manutTurn.setCounterPart(0L);
		manutTurn.setCounterPort(0L);
		manutTurn.setHoursMachining(0F);
		manutTurn.setLastUpdate(LocalDateTime.now());	
		manutTurn.setCounterPortMax(0L);
		manutTurn.setCounterClampingMax(0L);
		manutTurn.setCounterPartMax(0L);
		manutTurn.setHoursMachiningMax(0F);

		manutVariablesRepository.save(manutTurn);
		
		/*MANUT VARIABLES MILL*/
		ManutVariables manutMill = new ManutVariables();
		manutMill.setMachine(mach2);
		manutMill.setCounterClamping(0L);
		manutMill.setCounterPart(0L);
		manutMill.setCounterPort(0L);
		manutMill.setHoursMachining(0F);
		manutMill.setLastUpdate(LocalDateTime.now());
		manutMill.setCounterPortMax(0L);
		manutMill.setCounterClampingMax(0L);
		manutMill.setCounterPartMax(0L);
		manutMill.setHoursMachiningMax(0F);

		manutVariablesRepository.save(manutMill);
		
		/*MANUT VARIABLES ROBOT*/
		ManutVariables manutRobot = new ManutVariables();
		manutRobot.setMachine(mach3);
		manutRobot.setCounterClamping(0L);
		manutRobot.setCounterPart(0L);
		manutRobot.setCounterPort(0L);
		manutRobot.setHoursMachining(0F);
		manutRobot.setLastUpdate(LocalDateTime.now());
		manutRobot.setCounterPortMax(0L);
		manutRobot.setCounterClampingMax(0L);
		manutRobot.setCounterPartMax(0L);
		manutRobot.setHoursMachiningMax(0F);
		manutVariablesRepository.save(manutRobot);
		

		System.out.println("DUMMY DATA1 OK!");

	}
}
