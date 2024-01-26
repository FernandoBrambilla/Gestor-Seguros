
//VALIDA EMAIL NO CADASTRO
function validateEmail(){
    var input = document.getElementById("email");
    var email = input.value;
    var elemento = document.getElementById("response");
    var btn = document.getElementById("btn_register");
    const re = /\S+@\S+\.\S+/;
    if(email.search(re)){
        elemento.style.color = 'rgb(240, 59, 31)';
        elemento.innerHTML="Digite um email válido!";
        input.style.color= 'rgb(240, 59, 31)';
        input.style.border = '2px solid rgb(240, 59, 31)'
        btn.disabled = true;
        return false;
    }
    if(!email.search(re)){
        elemento.style.color = '#1cbb1c';
        elemento.innerHTML="Email válido!";
        input.style = 'none';
        btn.disabled = false;
        return true;
    }  
}

function returnOk(){
    var elemento = document.getElementById("response");
    if(validateEmail()){
        elemento.innerHTML = "Email enviado com sucesso!"
    }
}

//VERIFICA CAMPOS VAZIOS NO CADASTRO
function notNull(){
    var name = document.getElementById("name");
    var phone = document.getElementById("phone"); 
    var email = document.getElementById("email");
    var password = document.getElementById("password");
    var response = document.getElementById('response');
    
    if(name.value.length  == 0 || phone.value.length == 0 || password.value.length == 0
        || email.value.length == 0){
        response.innerHTML="Atenção! Campos Obrigatórios!";
        response.style.color='rgb(240, 59, 31)';
        
    }
    else{
        response.innerHTML= "Cadastro realizado com Sucesso!"
        response.style.color = '#1cbb1c';
    }
    if(name.value.length  == 0){
        name.style.border = '2px solid rgb(240, 59, 31)';
    }
    else{
        name.style = 'none';   
    }
    if(phone.value.length == 0){
        phone.style.border = '2px solid rgb(240, 59, 31)';
    }
    else{
        phone.style = 'none';
    }
    if(email.value.length == 0){
        email.style.border = '2px solid rgb(240, 59, 31)';
    }
    else{
        email.style = 'none';
    }
    if(password.value.length == 0){
        password.style.border = '2px solid rgb(240, 59, 31)';
    }
    else{
        password.style = 'none';
    }
}

