<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestor</title>
    <link rel="stylesheet" href="style/style_app.css">
    <script src="https://kit.fontawesome.com/2fe3c7f37b.js" crossorigin="anonymous"></script>

</head>
<body>
<header>
    <?php
        session_start();
    ?>
    <section id="header">
        </div>
            <div class="nav">
                <ul>
                <a id="icon" href="app_clients.php"><i class="fa-solid fa-people-group"></i>  Pessoas</a>
                <a id="icon" href=""><i class="fa-solid fa-car-burst"></i>  Seguros</a>
                <a id="icon" href=""> <i class="fa-regular fa-user"></i>  Usuários</a>
                <a id="icon" href="index.html"> <i class="fa-solid fa-house"></i>  Início</a>
                <a id="icon" href=""> <i id="icon" class="fa-solid fa-right-from-bracket"></i>  Sair </a>  
                </div>
            </ul>
        </div>
    </section>
    <section class="init">
        <h1>Gerenciador de Clientes</h1>
        <div class="find">
            <form action="" method="get">
                <div id="search">
                    <input type="text" name="search" placeholder="Digite um nome e clique em Pesquisar">
                </div>
                <div id="btn_search">
                    <button type="submit" method="GET"><i class="fa-solid fa-magnifying-glass"></i> Pesquisar</button>
            </div>
            </form>
        </div>
        <div class="button">
            <form action="" method="GET">
                <button class="btn"> <i class="fa-solid fa-trash"></i>  Excluir</button>
                <button class="btn"> <i class="fa-solid fa-user-pen"></i>  Editar</button>
                <button class="btn"> <i class="fa-solid fa-user-plus"></i>  Novo</button>
                <button name="searchAll" class="btn"> <i class="fa-solid fa-list"></i>  Mostrar Todos</button>
            </form>              
        </div>
    </section>
</header>
    <section class="table_container">
        <div class="tbody">
            <tbody>
                <tr>
                    <td>
                        <?php
                        //BOTÃO PESQUISAR
                         if(isset($_GET['search'])){
                            //VERIFICA CAMPO VAZIO
                            if(empty($_GET['search'])){
                                ?>
                                <div class="alert">
                                    <p>Digite um nome válido!</p>
                                    <img src="Img/not_found" alt="">
                                </div>
                                <?php
                            }else{
                                findByName();
                            }
                        }
                        else{
                            findAll();
                        }
                        //BOTÃO MOSTRAR TODOS
                        if(isset($_GET['searchAll'])){
                            findAll();
                        }?>
                    </td>
                </tr>
            </tbody>
        </div>
        <?php 
        
        //Função findByName
        function findByName (){
            $tokenAccess =  $_SESSION['accessToken'];
            $header =(
                array(
                    'Authorization: Bearer '.$tokenAccess
                )
            );
            $valueToSearch = $_GET['search'];
            $url = 'localhost:8080/clientspf/name?name=';
            $curl = curl_init();
            curl_setopt_array($curl,
                array(
                    CURLOPT_URL => $url.$valueToSearch,
                    CURLOPT_RETURNTRANSFER => true,
                    CURLOPT_ENCODING => '',
                    CURLOPT_MAXREDIRS => 10,
                    CURLOPT_TIMEOUT => 0,
                    CURLOPT_FOLLOWLOCATION => true,
                    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                    CURLOPT_CUSTOMREQUEST => 'GET',
                    CURLOPT_HTTPHEADER => $header,
                )
            );
            $response = curl_exec($curl);
            $responseJson = json_decode($response);
            curl_close($curl);
            if(empty($responseJson)){
                ?>
                <div class="alert">
                    <p class="alert">Ops! Nada encontrado!</p>
                    <img src="Img/not_found.jpg" alt="">
                </div>
                <?php
                die();
            }
            populeTable($responseJson);
        }  

        //Função findAll
        function findAll(){
            $tokenAccess =  $_SESSION['accessToken'];
            $header =(
                array(
                    'Authorization: Bearer '.$tokenAccess
                )
            );    
            $curl = curl_init();
            curl_setopt_array(
                $curl, array(
                    CURLOPT_URL => 'localhost:8080/clientspf',
                    CURLOPT_RETURNTRANSFER => true,
                    CURLOPT_ENCODING => '',
                    CURLOPT_MAXREDIRS => 10,
                    CURLOPT_TIMEOUT => 0,
                    CURLOPT_FOLLOWLOCATION => true,
                    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                    CURLOPT_CUSTOMREQUEST => 'GET',
                    CURLOPT_HTTPHEADER => $header
                )
            );
            $response = curl_exec($curl);
            $dataJson = json_decode($response);
            curl_close($curl);
            populeTable ($dataJson);
        }
               
        //Função para polular a tabela de resultados
        function populeTable ($data){ 
            ?>
            <table class="table">
                <thead>
                    <tr>
                        <th id="id">ID</th>
                        <th id="name">Nome</th>
                        <th>CPF</th>
                        <th>RG</th>
                        <th>Data Expedição</th>
                        <th>Data Nascimento</th>
                        <th>Telefone</th>
                        <th>E-mail</th>
                        <th>Endereço</th>
                        <th>Nº</th>
                        <th>Complemento</th>
                        <th>Cidade</th>
                        <th>Estado</th>
                        <th>CEP</th>
                        <th>Dados Bancários</th>
                    </tr>
                </thead>
                <?php
                foreach ($data as $key => $value){
                    $id = $value -> id;
                    $name = $value -> name;
                    $cpf = $value -> cpf;
                    $rg = $value -> rg;
                    $date_exp = $value -> dateExp;
                    $date_nasc = $value -> dateNasc;
                    $phone = $value -> phone;
                    $email = $value -> email;
                    $address = $value -> address;
                    $address_number = $value -> addressNumber;
                    $address_complement= $value -> addressComplement;
                    $cep= $value -> cep;
                    $city= $value -> city;
                    $uf= $value -> uf;
                    $bank = $value ->bank;
                    ?>
                    <tr>
                        <td><?php echo $id ?> </td>
                        <td><?php echo $name ?> </td>
                        <td><?php echo $cpf ?> </td>
                        <td><?php echo $rg ?> </td>
                        <td><?php echo $date_exp ?> </td>
                        <td><?php echo $date_nasc ?> </td>
                        <td><?php echo $phone ?> </td>
                        <td><?php echo $email ?> </td>
                        <td><?php echo $address ?> </td>
                        <td><?php echo $address_number ?> </td>
                        <td><?php echo $address_complement ?> </td>
                        <td><?php echo $city ?> </td>
                        <td><?php echo $uf ?> </td>
                        <td><?php echo $cep ?> </td>
                    </tr><?php
                } ?>
            </table><?php
        }?>   
    </section>
<footer>
    <hr>
    <h2>Gestor - Gerenciador de Clientes v. 3.0</h2>
    <p>Brambilla Sistemas</p>
    <p>Suporte</p>
</footer>
    
</body>
</html>