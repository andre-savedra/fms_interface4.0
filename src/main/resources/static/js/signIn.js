function index_return() {
    window.location.href = "/index";
}

function eventFormValidation(element) {
    if (element.value.length > element.maxLength) {
        element.value = element.value.slice(0, element.maxLength);
    }
}


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


function signInValidation() {
    event.preventDefault();
    let cpf = document.querySelector("#cpf");
    let name = document.querySelector("#name");
    let birth = document.querySelector("#birthday");
    let gender = document.querySelector("#gender");
    let email = document.querySelector("#e-mail");
    let phone = document.querySelector("#phone");
    let password = document.querySelector("#pass");
    let pass_confirm = document.querySelector("#pass_confirmation");
    let regex = /^(?=(?:.*?[A-Z]){1})(?=(?:.*?[0-9]){2})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%;*(){}_+^&]*$/;

    if (checkCPF(cpf.value.toString()) === false) {
        alert("CPF Inválido!");
        cpf.style.backgroundColor = "#fcc2c7";
    } else if (gender.value === "choose") {
        alert("Selecione o sexo");
        gender.style.backgroundColor = "#fcc2c7";
    } else if (phone.value.length != 11) {
        alert("Digite um número de telefone celular válido");
        phone.style.backgroundColor = "#fcc2c7";
    } else if (password.value != pass_confirm.value) {
        password.style.backgroundColor = "#fcc2c7";
        pass_confirm.style.backgroundColor = "#fcc2c7";
        alert("Senhas devem ser iguais");
    } else if (password.value.length < 6) {
        password.style.backgroundColor = "#fcc2c7";
        pass_confirm.style.backgroundColor = "#fcc2c7";
        alert("Senha deve conter no mínimo 6 dígitos");
    } else if (!regex.exec(password.value)) {
        alert("A senha deve conter no mínimo: 1 caractere em maiúsculo, 1 número e 1 caractere especial!");
    } else {
        let body = {
            id: cpf.value,
            name: name.value,
            email: email.value,
            phone: phone.value,
            gender: gender.value,
            birth: birth.value,
            password: password.value,
        };
        console.log(body);
        send_signIn(body);
    }
};

/*--------- HTTP REQUEST --------*/
function send_signIn(body) {
    let request = new XMLHttpRequest();
    let url = defaultUrl + "save_user";
    let enable = true;

    request.onreadystatechange = function() {
        if (enable == true) {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    if (request.responseText === "feito") {
                        window.location.href = "/successRegister";
                    } else {
                        window.location.href = "/failRegister";
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