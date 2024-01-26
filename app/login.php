<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestor - Login</title>
        <link rel="stylesheet" href="style/style_login.css">
        <script src="scripts/script.js"></script>
    </head>
    <body>
        <section id="welcome">
            <div class="text_welcome">
                <h1>Olá, seja bem vindo!</h1>
                <hr>
            </div>
        </section>
        <section id="login">

            <div class="login_container">
                <div class="img_login">
                    <img src="Img/img_login.svg" width="380" alt="">
                </div>
                <div class="login_screen">
                    <p id="response">Faça o login para acessar o sistema!</p>
                    <form action="scripts/login.php" method="get">
                        <div class="login_data">
                            <div class="user_data_login">
                                <label for="login"> Usuário</label>
                                <input type="text" name="userName" id="userName">
                            </div>
                            <br>
                            <div class="password_data_login">
                                <label for="login">Senha</label>
                                <input type="password" name="password" id="password">
                            </div>
                        </div>
                        <div class="button">
                            <input type="submit" value="Entrar" id="btnLogin" >
                        </div>
                    </form>
                    <hr>
                    <div class="first_access">
                        <a href="register.html" rel="noopener noreferrer">Primeiro Acesso? Cadastre-se.</a>
                    </div>
                    <div class="lost_password">
                        <a href="recoverPassword.html" rel="noopener noreferrer">Esqueceu a senha?</a>  
                    </div>  
                </div>    
            </div>  
        </section>
        <hr> 
        <footer>
    
        </footer>
         
    </body>
</html>