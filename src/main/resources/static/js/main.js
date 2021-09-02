/****************************  (classes)  **********************************/

class User {
    constructor(id) {
        this.id = id;
    }
}

class Machine {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

class StepOrder {
    constructor(name, gcode, machine) {
        this.name = name;
        this.gcode = gcode;
        this.machine = machine;
        this.concluded = false;
    }
}

class ProcessOrder {
    constructor(steps) {
        this.steps = steps;
        this.concluded = false;
    }
}

class OrderType {
    constructor(id, type) {
        this.id = id;
        this.type = type;
    }
}

class ModelType {
    constructor(id, modelName) {
        this.id = id;
        this.modelName = modelName;
    }
}

class Order {
    constructor(user, process, type, dimensions, units, ordername, model) {
        this.user = user;
        this.process = process;
        this.type = type;
        this.dimensions = dimensions;
        this.units = units;
        this.ordername = ordername;
        this.produced = false;
        this.unitsProduced = 0;
        this.manufacturing = false;
        this.model = model;
    }
}

/*---------  Objetos Teste de GCODE Torno --------*/

var turnGcode3 = `
N730 X&FMS_VAR1& 
N740 G0 X44. 
N750 G96 S302 
N760 Z-19.604 
N770 G1 X25.25 
N780 G0 X44. 
N790 Z-17.396 
N800 G1 X25.25 F.1 
N810 G0 X&FMS_VAR2& 
N820 Z-21.813 
N830 G1 X&FMS_VAR3& 
N840 X25.25 Z-&FMS_VAR4& 
N850 G0 X&FMS_VAR1& 
N860 Z-15.187 `;

/*---------  Objetos Teste de GCODE Centro --------*/
var millGcode1 = `
N750 X37.395 Z-4.386
N760 G0 Z4.9
N770 X30.233
N780 G1 Z2.9
N790 Z-5.8
N800 X32.6
N810 X&FMS_VAR1& Z-&FMS_VAR2&
N820 G0 Z4.9
N830 X28.267
N840 G1 Z2.9
N850 Z-5.8
N860 X30.633
N870 X33.462 Z-&FMS_VAR2&
N880 G0 Z4.9
N890 X26.3
N900 G1 Z2.9
N910 Z-5.8
N920 X28.667
N930 X31.495 Z-&FMS_VAR2&
N940 G0 Z4.9
N950 X24.333
N960 G1 Z2.9
N970 Z-5.8
N980 X26.7
N990 X29.528 Z-&FMS_VAR2&
N1000 G0 Z4.9
N1010 X22.367
N1020 G1 Z2.9
N1030 Z-5.8
N1040 X24.733
N1050 X27.562 Z-&FMS_VAR2&
N1060 G0 Z4.9
N1070 X20.4
N1080 G1 Z2.9
N1090 Z.2
N1100 Z-5.8
N1110 X22.767
N1120 X25.595 Z-&FMS_VAR2&
N1130 G0 Z2.25
N1140 M09
N1150 G28 W0 U0
N1160 T0303 ( OD RIGHT 55 DEG - RAIO= 0.80)
N1170 G96 S550
N1180 G92 S3600 M04
N1190 G0 X0. Z2.25 M08
N1200 Z2.
N1210 G1 Z0. F.5
N1220 X18.4
N1230 G3 X&FMS_VAR3& Z-.8 K-.8
N1240 G1 Z-6.
N1250 X38.4
N1260 G3 X&FMS_VAR3& Z-6.8 K-.8
N1270 G1 Z-9.8
N1280 Z-15.8
N1290 Z-19.8
N1300 Z-25.8
N1310 Z-30.8 `;

/****************************  (VARIÁVEIS)  **********************************/
let DEFAULT_USER = new User(38738276895);

//user
var actualCookieUser = new User(0);

//object to be used in POST requests
var turnMachine = new Machine(3, "torno");
var millingMachine = new Machine(4, "centro");

//order's type
var orderTurnType = new OrderType(6, "order-turn");
var customTurnType = new OrderType(7, "custom-turn");
var orderMillType = new OrderType(8, "order-mill");
var customMillType = new OrderType(9, "custom-mill");
var orderFlexType = new OrderType(10, "flex");

//model's type
var modelTypeWhite = new ModelType(1, "branco");
var modelTypeBlack = new ModelType(2, "preto");
var modelTypeList = [modelTypeBlack, modelTypeWhite];

//pattern do change gcode
var patternDimensionVar = "FMS_VAR";
var patternDimVarTerminator = "&";

//count to refresh parts - still not used
var countPartsTurnDatabase;
var countPartsMillDatabase;

//total parts in database
var PartsTurnDatabase = "initial";
var PartsMillDatabase = "initial";
var PartsFlexDatabase = "initial";

//total orders in database
var OrdersDatabase = "initial";
var numberOfOrdersDatabase = 0;


//actual view selected in screen
var viewActive = "ajax-custom";
//var to get html code when turn/mill pages
var htmlViewsTurnMillFlex = ["", "", ""];
//var to divide html code to fill dynamic code inside
var htmlViewsSplitParts = ["", "", ""];

//number of fields to customize
var turnPartsNoFields = 0;
var oldTurnPartsNoFields = 0;
var millPartsNoFields = 0;
var oldMillPartsNoFields = 0;
var flexPartsNoFields = 0;
var oldFlexPartsNoFields = 0;


//initial variables
var initialLoadDataParts = 0;
var initialLoadDataTurn = 0;
var initialLoadDataMill = 0;
var initialLoadDataFlex = 0;
var initialLoadHtmlVar = 0;
var initialLoadOrders = 0;


/****************************  (Cookies)  **********************************/

function get_Cookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(";");
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == " ") c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function erase_Cookie(name) {
    document.cookie = name + "=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;";
}

function getActualUser() {
    if (actualCookieUser.id != null && actualCookieUser.id > 0) {
        return actualCookieUser;
    } else {
        return DEFAULT_USER;
    }
}

/****************************  (Submits)  **********************************/

/*---------  CUSTOM TURN/MILL SUBMIT --------*/
//function loaded when pressed the send button in flex page
function submitCustom(element) {
    event.preventDefault();
    let typeM = "";
    let codeSelector = "";
    let myType = "";

    if (element.getAttribute("id") === "send_turn") {
        typeM = turnMachine;
        codeSelector = "turn";
        myType = customTurnType;
    } else {
        typeM = millingMachine;
        codeSelector = "mill";
        myType = customMillType;
    }

    let mygcode = document.querySelector("#code_" + codeSelector).value;
    let myunits = document.querySelector("#" + codeSelector + "_no").value;
    let myordername = document.querySelector(
        "#" + codeSelector + "_program"
    ).value;

    if (mygcode === "" || myunits === "" || myordername === "") {
        alert(
            "Preencha todos os campos corretamente antes de clicar no botão de enviar ao cnc!"
        );
    } else {
        let myStep = new StepOrder(myordername, mygcode, typeM);

        let listStep = [myStep];

        let processOrder = new ProcessOrder(listStep);

        let myOrder = new Order(
            getActualUser(),
            processOrder,
            myType,
            "",
            myunits,
            myordername,
            modelTypeBlack
        );
        // console.log("ORDEM A SER SALVA");
        // console.log(myOrder);
        orderSave(myOrder);
    }
}

//internal function loaded when pressed send button in part pages
//ps: the onclick event is in javascript not in html page (ajax)
function submitPart(element) {
    let inputs_part = ""; //fields filled in form part
    let values_validated = [];
    let process_validated = [];
    let partno = element.getAttribute("b-partno");
    let typeM = "";
    let dimensionsChosen = "";
    //get the values in fields, the gcode and machine
    if (element.getAttribute("class") === "btn_part_turn") {
        inputs_part = document.querySelectorAll(".ipt_part_turn");
        process_validated = PartsTurnDatabase[partno]["process"];
        typeM = orderTurnType;
    } else if (element.getAttribute("class") === "btn_part_mill") {
        inputs_part = document.querySelectorAll(".ipt_part_mill");
        process_validated = PartsMillDatabase[partno]["process"];
        typeM = orderMillType;
    } else {
        //so, it's a flex part
        inputs_part = document.querySelectorAll(".ipt_part_flex");
        process_validated = PartsFlexDatabase[partno]["process"];
        typeM = orderFlexType;
    }

    //check all fields whether are valid
    for (let i = 0; i < inputs_part.length; i++) {
        let value = inputs_part[i].value;

        if (value === "") {
            alert("Preencha todos os campos necessários!");
            return;
        }
        //ignore first fields: name, amount and model
        else if (i > 2) {
            dimensionsChosen += value + "mm" + " ";
            value = parseFloat(value);
            let min = parseFloat(inputs_part[i].getAttribute("min"));
            let max = parseFloat(inputs_part[i].getAttribute("max"));
            if (value > max || value < min) {
                inputs_part[i].style.backgroundColor = "#f0cec7";
                alert(
                    "Valores Preenchidos não permitidos, verifique os limites de cada campo"
                );
                dimensionsChosen = "";
                return;
            }
        }
        inputs_part[i].style.backgroundColor = "white";
        values_validated[i] = inputs_part[i].value;
    }

    //function to change gcode according to chosen customizations
    process_validated = renderGcode(values_validated, process_validated);

    // console.log("values validated");
    // console.log(values_validated);    
    // console.log("Peça escolhida:");
    // console.log(PartsFlexDatabase[partno]);

    let listStep = [];

    process_validated['steps'].forEach(element => {
        listStep.push(new StepOrder(
            element.name,
            element.gcode,
            element.machine
        ));
    });
    // console.log("list step");
    // console.log(listStep);

    let processOrder = new ProcessOrder(listStep);

    let myOrder = new Order(
        getActualUser(),
        processOrder,
        typeM,
        dimensionsChosen,
        values_validated[1],
        values_validated[0],
        modelTypeList[values_validated[2]]
    );


    console.log("minha ordem:");
    console.log(myOrder);

    orderSave(myOrder);
}

//internal function, loaded after part be ordered and fields validated
function renderGcode(values, gcodeValid) {
    //copy structure
    let finalGcode = JSON.parse(JSON.stringify(gcodeValid));

    //it's not a flex part:
    if (gcodeValid["steps"].length === 1) {
        //turn
        if (gcodeValid["steps"][0]["machine"].name === turnMachine.name) {
            finalGcode["steps"][0]["gcode"] = renderGcodeTurn(
                values,
                finalGcode["steps"][0]["gcode"],
                false
            );
        }
        //mill
        else {
            finalGcode["steps"][0]["gcode"] = renderGcodeMill(
                values,
                finalGcode["steps"][0]["gcode"],
                false
            );
        }
    }
    //flex part:
    else {
        finalGcode["steps"][0]["gcode"] = renderGcodeTurn(
            values,
            finalGcode["steps"][0]["gcode"],
            true
        );
        finalGcode["steps"][1]["gcode"] = renderGcodeMill(
            values,
            finalGcode["steps"][1]["gcode"],
            true
        );
    }
    console.log(finalGcode);

    return finalGcode;
}

function renderGcodeMill(values, gcode, isFlex) {

    let val = parseFloat(values[3]);
    let valTurn = 25;

    if (isFlex == true) {
        valTurn = parseFloat(values[4]);
    }


    let compensacao = (val + 10);
    let cos = Math.cos((30 / 57.2957795131));
    let diametro = (parseFloat(compensacao) / cos);
    let compensacao52 = ((45 - valTurn) / 2);


    let xDims = [
        (diametro / 2),
        (diametro / 4),
        (-diametro / 4),
        (-diametro / 2),
        (-diametro / 4),
        (diametro / 4),
        (diametro / 2)
    ];

    let yDims = [
        (0),
        (compensacao / 2),
        (compensacao / 2),
        (0),
        (-compensacao / 2),
        (-compensacao / 2),
        (0)
    ];



    let exchanges = [
        compensacao52,
        xDims[0],
        yDims[0],
        xDims[0],
        yDims[0],
        xDims[1],
        yDims[1],
        xDims[2],
        yDims[2],
        xDims[3],
        yDims[3],
        xDims[4],
        yDims[4],
        xDims[5],
        yDims[5],
        xDims[6],
        yDims[6]
    ];

    let gcodeModified = gcode.split("&FMS_VAR1&");
    let gcodeRendered = "";
    for (let i = 0; i < gcodeModified.length; i++) {
        if (i === gcodeModified.length - 1) {
            gcodeRendered += gcodeModified[i];
        } else {
            gcodeRendered += gcodeModified[i] + exchanges[i];
        }
    }

    //  console.log("gcodeRendered");
    //  console.log(gcodeRendered);

    // "O7101\n"
    // "N10 G54 G0 Z30\n"
    // "N20 M6 T4\n"
    // "N30 G54 X0 Y0\n"
    // "N40 M3 S1500\n"

    // "N50 G52 X-&FMS_EQ1&\n"

    // "N60 G90 G54 G0 X0 Y0 Z2\n"
    // "N70 G0X0.000Y0.000\r\n" .........

    // "N4780 G1 X&FMS_VAR1& Y&FMS_VAR1& F500\n"

    // "N4790 Z-28\n"				

    // "N4800 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4810 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4820 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4830 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4840 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4850 X&FMS_VAR1& Y&FMS_VAR1&\n"
    // "N4860 X&FMS_VAR1& Y&FMS_VAR1&\n"

    // "N4870 G54 G90 G0 Z30\n"				
    // "N4880 M30\n"


    return gcodeRendered;
}

function renderGcodeTurn(values, gcode, isFlex) {
    let val = values[3];
    if (isFlex) {
        val = values[4];
    }
    let increment = (45 - val) / 5;
    let exchanges = [
        45 - increment,
        45 - 2 * increment,
        45 - 3 * increment,
        45 - 4 * increment,
        45 - 5 * increment + 0.5,
        45 - 5 * increment,
        45 - 5 * increment - 3,
        45 - 5 * increment,
    ];
    let gcodeModified = gcode.split("&FMS_VAR1&");
    let gcodeRendered = "";
    for (let i = 0; i < gcodeModified.length; i++) {
        if (i === gcodeModified.length - 1) {
            gcodeRendered += gcodeModified[i];
        } else {
            gcodeRendered += gcodeModified[i] + exchanges[i];
        }
    }
    /*
        ("O7101\n");
        ("N10 G90 G40 G95\n");
        ("N20 G54 X100 Z100\n");
        ("N30 T0101\n");
        ("N40 G96 S275 M4\n");
        ("N50 G0 X47 Z2\n");

        ("N60 G20 X&FMS_EQ1& Z-40 F0.25\n");
        ("N70 X&FMS_EQ1&\n");
        ("N80 X&FMS_EQ1&\n");
        ("N90 X&FMS_EQ1&\n");
        ("N100 X&FMS_EQ1&\n");
        
        ("N110 G0 G28 U0 W0\n");
        ("N120 T0303\n");
        ("N130 G96 S275 M4\n");
        
        ("N140 G0 X&FMS_EQ1& Z2\n");
        ("N150 G1 X&FMS_EQ1& Z0 F0.1\n");
        ("N160 X&FMS_EQ1& Z-3\n");
        
        ("N170 Z-40\n");
        ("N180 X47\n");
        ("N190 G0 G28 U0 W0\n");
        ("N200 M30\n");
    */
    return gcodeRendered;
}



/*--------- FUNCTION ORDER SAVE --------*/
//internal function loaded when the order is checked and can be saved in database
function orderSave(myOrder) {
    let url = defaultUrl + "ordersave";

    let body = myOrder;

    //asynchronous function to make the POST request
    makePostReturn(url, body, orderSaveResponse);
}

//response after request execution
function orderSaveResponse(responseStatus, responseText) {
    if (responseStatus === 200 && responseText === "feito") {
        window.location.href = "/success";
    } else {
        window.location.href = "/fail";
    }
}

/*--------- DATA LOAD PARTS BY MACHINE --------*/
//internal function loaded to receive all parts in database
function loadDataParts(dataType) {
    let url = defaultUrl + "load_part_order_type";
    //asynchronous function to make the POST request
    makePostReturn(url, dataType, loadDataPartsResponse);
}

//response after request execution
function loadDataPartsResponse(responseStatus, responseText) {
    //load parts from database
    if (responseStatus === 200) {
        let dbResponse = JSON.parse(responseText);
        // console.log("DBBBBBB");
        // console.log(dbResponse);

        if (dbResponse.length > 0) {
            switch (dbResponse[0]["type"].type) {
                case "order-turn":
                    PartsTurnDatabase = dbResponse;
                    initialLoadDataTurn = 1;
                    break;

                case "order-mill":
                    PartsMillDatabase = dbResponse;
                    initialLoadDataMill = 1;
                    break;

                case "flex":
                    PartsFlexDatabase = dbResponse;
                    initialLoadDataFlex = 1;
                    break;
            }
        }

        checkInitialLoad();
    } else {
        window.location.href = "/fail";
    }
}

/*--------- DATA LOAD ALL PARTS --------*/
//internal function to load all parts - not used
function loadAllDataParts() {
    let url = defaultUrl + "load_all_parts";

    makePostReturn(url, null, loadAllDataPartsResponse);
}

//response of load all parts - not used
function loadAllDataPartsResponse(responseStatus, responseText) {
    if (responseStatus === 200) {
        console.log(responseText);
    } else {
        window.location.href = "/fail";
    }
}

/*--------- DATA COUNT ALL ORDERS --------*/
//internal function to load all orders in database
function countOrdersList() {
    let url = defaultUrl + "count_orders";

    makePostReturn(url, null, countAllOrdersResponse);
}

function countAllOrdersResponse(responseStatus, responseText) {

    if (responseStatus === 200) {
        let dbResponse = responseText;

        if (dbResponse > 0) {
            numberOfOrdersDatabase = dbResponse;

            console.log("NUMBER ORDERS:");
            console.log(numberOfOrdersDatabase);
        }

    } else {
        window.location.href = "/fail";
    }
}

/*--------- DATA LOAD ALL ORDERS --------*/
//internal function to load all orders in database
function loadOrdersList() {
    let url = defaultUrl + "load_all_orders";

    makePostReturn(url, null, loadAllOrdersResponse);
}
//response of load all orders
function loadAllOrdersResponse(responseStatus, responseText) {
    if (responseStatus === 200) {
        let dbResponse = JSON.parse(responseText);
        if (dbResponse.length > 0) {
            OrdersDatabase = dbResponse;
            renderOrdersList(dbResponse);
            console.log("ORDERS DATABASE");
            console.log(OrdersDatabase);
        }
    } else {
        window.location.href = "/fail";
    }
}
//function to change the way of data to be showed in table
function dataFormatter(data) {
    if (data === "order-turn") {
        return "Padrão Torno";
    } else if (data === "order-mill") {
        return "Padrão Centro";
    } else if (data === "custom-turn") {
        return "Custom Torno";
    } else if (data === "custom-mill") {
        return "Custom Centro";
    } else if (data === "flex") {
        return "Flex";
    } else if (data === true) {
        return "Sim";
    } else if (data === false) {
        return "Não";
    } else if (data === null) {
        return "-";
    } else {
        return data;
    }
}
//render data in list of orders when is active
function renderOrdersList(dataOrders) {

    let tableBody = document.querySelector("#body_tb_prodList");
    if (tableBody != null) {
        let htmlString = "";
        let color = "#cccccc";

        let head = document.querySelectorAll("#head_tb_prodList tr th");
        for (let i = 0; i < head.length; i++) {
            head[i].style["background-color"] = "#2124298c";
            head[i].style["border-bottom"] = "2px solid black";
        }

        // console.log("dataOrders");
        // console.log(dataOrders);
        for (var i = 0; i < dataOrders.length; i++) {
            if (color === "#cccccc") {
                color = "#ebe1e1";
            } else {
                color = "#cccccc";
            }

            let mach = "";
            if (dataOrders[i]['process']['steps'].length > 1) {
                mach = dataOrders[i]['process']['steps'][0]['machine']['name'] + ' - ';
                mach += dataOrders[i]['process']['steps'][1]['machine']['name'];
            } else {
                mach = dataOrders[i]['process']['steps'][0]['machine']['name'];
            }


            htmlString +=
                '<tr style="background-color:' +
                color +
                ';">' +
                '<th class="thId" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["id"] +
                "</th>" +
                '<th class = "thName" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["ordername"] +
                "</th>" +
                '<th class = "thMach" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                mach +
                "</th>" +
                '<th class = "thUser" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["user"]["name"] +
                "</th>" +
                '<th class = "thTypeProd" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataFormatter(dataOrders[i]["type"]["type"]) +
                "</th>" +
                '<th class = "thToProd" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["units"] +
                "</th>" +
                '<th class = "thProd" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["unitsProduced"] +
                "</th>" +
                '<th class = "thOk" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataFormatter(dataOrders[i]["produced"]) +
                "</th>" +
                '<th class = "thIn" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataOrders[i]["inputDate"] +
                "</th>" +
                '<th class = "thOut" style="background-color:' +
                color +
                '; border-bottom: 2px solid black;">' +
                dataFormatter(dataOrders[i]["outputDate"]) +
                "</th>" +
                "</tr>";
        }

        // console.log("html string");
        // console.log(htmlString);

        if ((htmlString != "") && (htmlString != null)) {
            tableBody.innerHTML = htmlString;
        }
    }
}

/*--------- COUNT ALL PARTS --------*/
//count parts - not used
function loadCountParts() {
    let url = defaultUrl + "count_parts";

    makePostReturn(url, null, loadCountPartsResponse);
}
//response of count parts - not used
function loadCountPartsResponse(responseStatus, responseText) {
    if (responseStatus === 200) {
        console.log(responseText);
    } else {
        window.location.href = "/fail";
    }
}

/*--------- COUNT PARTS BY MACHINE --------*/
//count parts - not used
function loadCountMachineParts(machine) {
    let url = defaultUrl + "count_parts_machine";

    makePostReturn(url, machine, loadCountMachinePartsResponse);
}
//response of count parts - not used
function loadCountMachinePartsResponse(responseStatus, responseText) {
    if (responseStatus === 200) {
        console.log(responseText);
    } else {
        window.location.href = "/fail";
    }
}

/*--------- HTTP REQUEST --------*/
//internal function responsible to make POST requests
function makePostReturn(url, body, functionResponse) {
    let request = new XMLHttpRequest();
    let enable = true;

    request.onreadystatechange = function() {
        if (enable == true) {
            if (request.readyState === 4) {
                functionResponse(request.status, request.responseText);
                enable = false;
            }
        }
    };

    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(body));
}

/****************************  (Ajax)  **********************************/
/*--------- AJAX DASH CONTENT --------*/
//let btn_flex = document.querySelector(".inner_submenu");
let content = document.querySelector(".dash_content");

//external function loaded when pressed buttons to exchange pages
function fetchContent(element) {
    oldTurnPartsNoFields = 0;
    oldMillPartsNoFields = 0;
    oldFlexPartsNoFields = 0;

    viewActive = element.getAttribute("a-view");
    let path = "/html/ajax/" + viewActive + ".html";

    //turn on timer to refresh dashboards
    if (viewActive === "ajax-dashboards") {
        onTimerChartsDashboards();
    } else {
        stopTimerChartsDashboards();
    }
    //turn on timer to refresh production list
    if (viewActive === "ajax-productionList") {
        onTimerGetOrdersList();
        numberOfOrdersDatabase = 0;
    } else {
        stopTimerGetOrdersList();
    }
    //turn on timer to refresh screen of parts turn/mill
    //ps: do not make fetch in this case because html is ready
    if (
        viewActive === "ajax-turn" ||
        viewActive === "ajax-mill" ||
        viewActive === "ajax-flex"
    ) {
        onTimerGetPartTurnMill();
        //get html code to each machine (0-turn, 1-mill)
        if (viewActive === "ajax-turn") {
            content.innerHTML = htmlViewsTurnMillFlex[0];
        } else if (viewActive === "ajax-mill") {
            content.innerHTML = htmlViewsTurnMillFlex[1];
        } else {
            content.innerHTML = htmlViewsTurnMillFlex[2];
        }
    } else {
        stopTimerGetPartTurnMill();

        fetch(path)
            .then((response) => {
                let html = response.text();
                return html;
            })
            .then((html) => {
                content.innerHTML = html;
                console.log(html);
            });
    }
}
//pre-load html of parts
function fetchLoadHtmlParts(functionResponse) {
    let views = ["ajax-turn", "ajax-mill", "ajax-flex"];

    for (let v = 0; v < views.length; v++) {
        let path = "/html/ajax/" + views[v] + ".html";

        fetch(path)
            .then((response) => {
                let html = response.text();
                return html;
            })
            .then((html) => {
                let subStr = html.split('class="carousel-inner">');
                subStr[0] = subStr[0] + 'class="carousel-inner">';
                htmlViewsSplitParts[v] = subStr;
                initialLoadHtmlVar++;
                functionResponse();
            });
    }
}
//after loading html parts, we have to render
function fetchLoadHtmlPartsResponse() {
    if (initialLoadHtmlVar === 3) {
        renderHtmlViewParts();
        countOrdersList();
        loadOrdersList();
    }
}

//render html parts with images, attributes...
function renderHtmlViewParts() {
    console.log("PARTS_DATABASE:");
    console.log(PartsTurnDatabase);
    console.log(PartsMillDatabase);
    console.log(PartsFlexDatabase);
    // console.log(htmlViewsSplitParts);
    let data = [PartsTurnDatabase, PartsMillDatabase, PartsFlexDatabase];

    for (let htView = 0; htView < data.length; htView++) {
        htmlViewsTurnMillFlex[htView] = htmlViewsSplitParts[htView][0];
        //console.log("htview:" + htView);
        // console.log(htmlViewsTurnMillFlex[htView]);
        // console.log(htmlViewsSplitParts[htView][0]);

        let secondPart = "";
        for (let partInView = 0; partInView < data[htView].length; partInView++) {
            if (partInView === 0) {
                secondPart += '\r\n<div class="carousel-item active" d-nofields="';
            } else {
                secondPart += '\r\n<div class="carousel-item" d-nofields="';
            }

            secondPart +=
                data[htView][partInView]["process"]["customizations"].length +
                '" d-partno="' +
                partInView +
                '">\r\n' +
                data[htView][partInView]["pathImg"] +
                "\r\n</div>\r\n";
        }

        htmlViewsTurnMillFlex[htView] += secondPart;
        htmlViewsTurnMillFlex[htView] += htmlViewsSplitParts[htView][1];
        //  console.log("htmlViewsTurnMillFlex: " + htView);
        //  console.log(htmlViewsTurnMillFlex[htView]);
    }

    initialLoadHtmlVar = 4;
}

/*--------- AJAX TURN PARTS --------*/
/*--------- AJAX MILL PARTS --------*/

var letters = ["A", "B", "C", "D", "E", "F", "G", "I"];
//internal function loaded to refresh
function changePart() {
    if (
        viewActive === "ajax-turn" ||
        viewActive === "ajax-mill" ||
        viewActive === "ajax-flex"
    ) {
        let typeCarousel, typeForm, typeM;
        let carousel, formPart, fields;
        //let typeCustom;
        let typeNo;

        if (viewActive === "ajax-turn") {
            //machine is turn
            typeCarousel = "#carouselTurnContent";
            typeForm = "#formTurnPart_";
            typeM = "turn";

            turnPartsNoFields = document
                .querySelector(typeCarousel)
                .querySelector(".active")
                .getAttribute("d-partno");

            if (turnPartsNoFields === oldTurnPartsNoFields) {
                return;
            } else {
                oldTurnPartsNoFields = turnPartsNoFields;
                typeNo = turnPartsNoFields;
                // typeCustom = PartsTurnDatabase[turnPartsNoFields]['customizations'];
            }
        } else if (viewActive === "ajax-mill") {
            //machine is milling machine
            typeCarousel = "#carouselMillContent";
            typeForm = "#formMillPart_";
            typeM = "mill";

            millPartsNoFields = document
                .querySelector(typeCarousel)
                .querySelector(".active")
                .getAttribute("d-partno");

            if (millPartsNoFields === oldMillPartsNoFields) {
                return;
            } else {
                oldMillPartsNoFields = millPartsNoFields;
                typeNo = millPartsNoFields;
                //typeCustom = PartsMillDatabase[millPartsNoFields]['customizations'];
            }
        } else {
            //machine view is flex
            typeCarousel = "#carouselFlexContent";
            typeForm = "#formFlexPart_";
            typeM = "flex";

            flexPartsNoFields = document
                .querySelector(typeCarousel)
                .querySelector(".active")
                .getAttribute("d-partno");

            if (flexPartsNoFields === oldFlexPartsNoFields) {
                return;
            } else {
                oldFlexPartsNoFields = flexPartsNoFields;
                typeNo = flexPartsNoFields;
                //typeCustom = PartsMillDatabase[millPartsNoFields]['customizations'];
            }
        }

        // all carousels in view
        carousel = document
            .querySelector(typeCarousel)
            .querySelectorAll(".carousel-item");

        // the local to put the form
        formPart = document.querySelector(typeForm);

        if (viewActive === "ajax-turn") {
            fields = carousel[turnPartsNoFields].getAttribute("d-nofields");
        } else if (viewActive === "ajax-mill") {
            fields = carousel[millPartsNoFields].getAttribute("d-nofields");
        } else {
            fields = carousel[flexPartsNoFields].getAttribute("d-nofields");
        }
        console.log(fields, formPart, typeM, typeNo);

        /*let typeName = document
                .querySelector(typeCarousel)
                .querySelector(".active")
                .querySelector("img")
                .getAttribute("alt");*/

        //send to function to create forms:
        //amount of fields, local to put fields, type machine, part active
        createFieldsForm(fields, formPart, typeM, typeNo);
    }
}

function createFieldsForm(totalFields, element, typemachine, typeno) {
    let nameP;
    let typecustom;
    if (typemachine === "turn") {
        nameP = PartsTurnDatabase[typeno]["name"];
        typecustom = PartsTurnDatabase[typeno]["process"]["customizations"];
    } else if (typemachine === "mill") {
        nameP = PartsMillDatabase[typeno]["name"];
        typecustom = PartsMillDatabase[typeno]["process"]["customizations"];
    } else {
        nameP = PartsFlexDatabase[typeno]["name"];
        typecustom = PartsFlexDatabase[typeno]["process"]["customizations"];
    }

    let htmlString =
        "<div><h4><strong>Peça: " +
        nameP +
        "</strong></h4></div>" +
        '<div><label for="' +
        typemachine +
        '_part_name"><strong>Nome Peça</strong></label><input id="' +
        typemachine +
        '_part_name" type="text" placeholder="Nome Peça" required="" class="ipt_part_' +
        typemachine +
        '"><i class="fas fa-info-circle" ' +
        'data-bs-toggle="tooltip" data-bs-placement="bottom" title="Nome da Peça" ' +
        'data-trigger="hover"></i></div>';

    htmlString +=
        '<div><label for="' +
        typemachine +
        '_part_amount"><strong>Quantidade</strong></label><input id="' +
        typemachine +
        '_part_amount" type="number" placeholder="Quantidade peça" required="" class="ipt_part_' +
        typemachine +
        '" min="1" max="20"><i class="fas fa-info-circle" ' +
        'data-bs-toggle="tooltip" data-bs-placement="bottom" title="Quantidade à produzir" ' +
        'data-trigger="hover"></i></div>';

    htmlString +=
        '<div><label for="' +
        typemachine +
        '_part_color"><strong>Cor da Peça</strong></label><select id="' +
        typemachine +
        '_part_color" placeholder="Selecione a cor" class="ipt_part_' +
        typemachine +
        '" > ' +
        '<option value="0">Preto</option>' +
        '<option value="1">Branco</option> </select>' +
        '<i class="fas fa-info-circle" ' +
        'data-bs-toggle="tooltip" data-bs-placement="bottom" title="Cor da peça" ' +
        'data-trigger="hover"></i></div>';

    for (var i = 0; i < totalFields; i++) {
        htmlString +=
            '<div><label for="' +
            typemachine +
            "_dim_" +
            letters[i] +
            '"><strong>Dimensão ' +
            letters[i] +
            '</strong></label><input id="' +
            typemachine +
            "_dim_" +
            letters[i] +
            '" type="number" min="' +
            typecustom[i]["minimum"] +
            '" max="' +
            typecustom[i]["maximum"] +
            '" placeholder="Medida em mm" required="" class="ipt_part_' +
            typemachine +
            '"><i class="fas fa-info-circle"' +
            ' data-bs-toggle="tooltip" data-bs-placement="bottom" title="Mínimo: ' +
            typecustom[i]["minimum"] +
            ", Máximo: " +
            typecustom[i]["maximum"] +
            '"' +
            ' data-trigger="hover"></i></div>';
    }

    htmlString +=
        '<button class="btn_part_' +
        typemachine +
        '" b-partno="' +
        typeno +
        '" onclick="submitPart(this)"><i class="far fa-share-square"></i>Enviar</button>';

    // console.log("html string");
    // console.log(htmlString);

    element.innerHTML = htmlString;

    let selectColor = element.querySelector("select");

    selectColor.addEventListener("change", () => {
        let colorString = "";
        if (selectColor.value == "1") {
            colorString = "0.72,0.69,0.69,1";
        } else {
            colorString = "0,0,0,1";
        }
        const color = colorString
            .split(",")
            .map((numberString) => parseFloat(numberString));
        console.log("Changing color to: ", color);

        const [material] = document
            .querySelector(".carousel-inner")
            .querySelector(".active")
            .querySelector("model-viewer").model.materials;
        material.pbrMetallicRoughness.setBaseColorFactor(color);
    });
}

/****************************  (eventsOnPage)  **********************************/
/*--------- REFRESH CHARTS --------*/
var timerChartsDashboards;

function refreshChartsDashboards() {
    drawDonut(
        document.getElementById("parts_turn_chart"),
        turnDataParts,
        configParts
    );
    drawDonut(
        document.getElementById("parts_mill_chart"),
        millDataParts,
        configParts
    );

    drawDonut(document.getElementById("oee_turn_chart"), turnDataOee, configOee);
    drawDonut(document.getElementById("oee_mill_chart"), millDataOee, configOee);

    drawTimeline(
        document.getElementById("timeline_turn_chart"),
        "Torno",
        turnDataTimeline,
        configTimeline
    );
    drawTimeline(
        document.getElementById("timeline_mill_chart"),
        "Centro",
        millDataTimeline,
        configTimeline
    );
}

function onTimerChartsDashboards() {
    timerChartsDashboards = setInterval(refreshChartsDashboards, 4000);

}

function stopTimerChartsDashboards() {
    clearInterval(refreshChartsDashboards);
}

/*--------- REFRESH PARTS CHOOSEN --------*/
var timerGetPartTurnMill;

//this is important to pre-load all data parts in background
//to make the pages exchange more fast
if (initialLoadDataParts == 0) {
    actualCookieUser.id = get_Cookie("fmsusercookie");
    console.log("USUARIO ATUAL =" + actualCookieUser.id);
    loadDataParts(orderTurnType);
    loadDataParts(orderMillType);
    loadDataParts(orderFlexType);
    initialLoadDataParts = 1;
}
//if all pre-load of part are finished, so we can pre-load
//the html of ajax pages also to optimize performance
function checkInitialLoad() {
    if (
        initialLoadDataTurn === 1 &&
        initialLoadDataMill === 1 &&
        initialLoadDataFlex === 1 &&
        initialLoadHtmlVar !== 4
    ) {
        fetchLoadHtmlParts(fetchLoadHtmlPartsResponse);
        //console.log("FINALIZADO!");
    }
}
//check if user changed part
function refreshGetPartTurnMill() {
    changePart();
    refresh3dModel();
}

function onTimerGetPartTurnMill() {
    timerGetPartTurnMill = setInterval(refreshGetPartTurnMill, 500);
}

function stopTimerGetPartTurnMill() {
    clearInterval(refreshGetPartTurnMill);
}

/*--------- REFRESH ORDERS LIST --------*/
var timerGetOrdersList;

function refreshGetOrdersList() {
    countOrdersList();
    if (numberOfOrdersDatabase != OrdersDatabase.length) {
        loadOrdersList();
    }


}

function onTimerGetOrdersList() {

    if (initialLoadOrders == 0) {
        loadOrdersList();
    }
    initialLoadOrders = 1;
    timerGetOrdersList = setInterval(refreshGetOrdersList, 5000);
}

function stopTimerGetOrdersList() {
    initialLoadOrders = 0;
    clearInterval(refreshGetOrdersList);
}

/*--------- REFRESH TOOLTIPS --------*/
var timerRefreshToolTips = setInterval(function() {
    var tooltipTriggerList = [].slice.call(
        document.querySelectorAll('[data-bs-toggle="tooltip"]')
    );
    var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}, 1000);

/****************************  (3d models)  **********************************/

function refresh3dModel() {
    let model3d = document.querySelectorAll(".scalePart");

    for (let m in model3d) {
        if (model3d[m].loaded) {
            model3d[m].scale = model3d[m].getAttribute("customScale");
        }
    }
}

/****************************  (charts)  **********************************/
/*--------- LOAD CHARTS PACKAGES --------*/
google.charts.load("current", { packages: ["corechart", "line"] });
//google.charts.setOnLoadCallback(drawDonut);
//google.charts.setOnLoadCallback(drawTimeline);

// google.charts.load('current', {'packages':['line']});
// google.charts.setOnLoadCallback(drawChart);

/*--------- CONFIG PARTS --------*/
var turnDataParts = [
    ["Task", "Percentage"],
    ["Parafuso", 5],
    ["Eixo", 1],
    ["Rolamento", 2],
    ["Fuso", 2],
];

var millDataParts = [
    ["Task", "Hours per Day"],
    ["Castanha", 11],
    ["Flange", 2],
];

var configParts = {
    title: "Peças Produzidas",
    pieHole: 0.4,
};

/*--------- CONFIG OEE --------*/
var turnDataOee = [
    ["Task", "Percentage"],
    ["Eficiência", 3],
    ["Ineficiência", 5],
];

var millDataOee = [
    ["Task", "Hours per Day"],
    ["Eficiência", 11],
    ["Ineficiência", 8],
];

var configOee = {
    title: "OEE",
    pieHole: 0.4,
    slices: {
        0: { color: "blue" },
        1: { color: "red" },
    },
};

/*--------- DRAW CHARTS --------*/
function drawDonut(myElement, data, configArray) {
    var data = google.visualization.arrayToDataTable(data);

    var chart = new google.visualization.PieChart(
        myElement // document.getElementById("donutTeste")
    );
    chart.draw(data, configArray);
}

/*--------- LINE CHARTS --------*/
var turnDataTimeline = [
    [1, 37.8],
    [2, 30.9],
    [3, 25.4],
    [4, 11.7],
    [5, 11.9],
    [6, 8.8],
    [7, 7.6],
    [8, 12.3],
    [9, 16.9],
    [10, 12.8],
    [11, 5.3],
    [12, 6.6],
    [13, 4.8],
    [14, 4.2],
];

var millDataTimeline = [
    [1, 37.8],
    [2, 30.9],
    [3, 25.4],
    [4, 11.7],
    [5, 11.9],
    [6, 8.8],
    [7, 7.6],
    [8, 12.3],
    [9, 16.9],
    [10, 12.8],
    [11, 5.3],
    [12, 6.6],
    [13, 4.8],
    [14, 4.2],
];

var configTimeline = {
    chart: {
        title: "Linha do Tempo Produção",
        subtitle: "em unidades por dia",
    },
    width: 400,
    height: 300,
};

function drawTimeline(myElement, name, timeline, configArray) {
    var data = new google.visualization.DataTable();
    data.addColumn("number", "Day");
    data.addColumn("number", name);
    data.addRows(timeline);

    var chart = new google.charts.Line(myElement);

    chart.draw(data, google.charts.Line.convertOptions(configArray));
}

function logout() {
    erase_Cookie("fmsusercookie");
    window.location.href = "/index";
}

if (interfaceType == CLOUD_TYPE) {
    let titleAdapter = document.querySelector("header #title_adapter");
    titleAdapter.style.display = "none";
}

function renew() {
    window.location.href = "/renew";
}