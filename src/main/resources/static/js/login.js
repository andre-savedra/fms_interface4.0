var panel = document.querySelector(".center_panel");
var state = false;
var button = document.querySelector("#submit_login");

/*--------- SIGN IN - REGISTER NEW USER --------*/
function signin() {
    window.location.href = "/signIn";
}

/*--------- COLLAPSE --------*/
function change_height() {
    console.log("pressionado!");
    state = !state;

    if (state) {
        panel.style.height = "900px";
    } else {
        panel.style.height = "88%";
    }
}

/*--------- EVENT FORM LENGHT VALIDATION --------*/
function eventFormValidation(element) {
    if (element.value.length > element.maxLength) {
        element.value = element.value.slice(0, element.maxLength);
    }
}

/*--------- VALIDATE CPF --------*/
function checkCPF(strCPF) {
    var Soma;
    var Resto;
    var cpfsInvalidos = [
        "11111111111",
        "22222222222",
        "33333333333",
        "44444444444",
        "55555555555",
        "66666666666",
        "77777777777",
        "88888888888",
        "99999999999"
    ];

    Soma = 0;
    for (let i = 0; i < cpfsInvalidos.length; i++) {
        if (strCPF == cpfsInvalidos[i]) return false;
    }

    for (i = 1; i <= 9; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10))) return false;

    Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11))) return false;
    return true;
}


function loginValidation() {
    event.preventDefault();
    let cpf = document.querySelector("#cpf_login");
    let pass = document.querySelector("#pass_login");
    let regex = /^(?=(?:.*?[A-Z]){1})(?=(?:.*?[0-9]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%;*(){}_+^&]*$/;


    if (checkCPF(cpf.value.toString()) === false) {
        alert("CPF Inválido!");
        cpf.style.backgroundColor = "#fcc2c7";
    } else if (pass.value.length < 6) {
        pass.style.backgroundColor = "#fcc2c7";
        alert("Senha deve conter no mínimo 6 dígitos");
    } else if (!regex.exec(pass.value)) {
        alert("A senha deve conter no mínimo: 1 caractere em maiúsculo, 1 número e 1 caractere especial!");
    } else {
        let body = {
            id: cpf.value,
            password: pass.value,
        };
        console.log(body);
        send_RequestLogin(body);
    }
};

/*--------- COOKIE USER --------*/
function setCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}


/*--------- HTTP REQUEST --------*/
function send_RequestLogin(body) {
    let request = new XMLHttpRequest();
    let url = defaultUrl + "validate_user";
    let enable = true;

    request.onreadystatechange = function() {
        if (enable == true) {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    if (request.responseText === "userOk") {
                        let cpf = document.querySelector("#cpf_login");
                        eraseCookie('fmsusercookie');
                        setCookie('fmsusercookie', cpf.value, 1);
                        window.location.href = "/successlogin";
                    } else if (request.responseText === "notFound") {
                        alert("Usuário não encontrado!");
                        cleanSpinner(button);
                    } else if (request.responseText === "blocked") {
                        alert("Senha incorreta!");
                        cleanSpinner(button);
                    }

                } else {
                    window.location.href = "/fail";
                }

            }
        }
    };

    request.open("POST", url, true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(body));
}

function setSpinnerButton(element){
    const actual = element.innerHTML;
    element.innerHTML = actual + '<span id="spinBtn" class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';
}

function cleanSpinner(element){
    element.removeChild(document.getElementById("spinBtn"));
}