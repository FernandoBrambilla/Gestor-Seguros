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
    <section id="header">
        </div>
            <div class="nav">
                <ul>
                <a id="icon" href=""> <i class="fa-solid fa-people-group"></i>  Pessoas</a>
                <a id="icon" href=""><i class="fa-solid fa-car-burst"></i>  Seguros</a>
                <a id="icon" href=""> <i class="fa-regular fa-user"></i>  Usuários</a>
                <a id="icon" href="index.html"> <i class="fa-solid fa-house"></i>  Início</a>
                <a id="icon" href=""> <i id="icon" class="fa-solid fa-right-from-bracket"></i>  Sair </a>  
                </div>
            </ul>
        </div>
    </section>
</header>

<section class="init">
    <h1>Gerenciador de Clientes</h1>
    <div class="find">
        <form action="" method="get">
            <div id="search">
                <input type="text" name="search" placeholder="Digite um nome e clique em Pesquisar">
            </div>
            <div id="btn_search">
                <button type="submit"><i class="fa-solid fa-magnifying-glass"></i> Pesquisar</button>
            </div>
            
        </form>
    </div>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col" id="id">ID</th>
                    <th scope="col" id="name">Nome</th>
                    <th scope="col">CPF</th>
                    <th scope="col">RG</th>
                    <th scope="col">Data Expedição</th>
                    <th scope="col">Data Nascimento</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">E-mail</th>
                    <th scope="col">Endereço</th>
                    <th scope="col">Nº</th>
                    <th scope="col">Complemento</th>
                    <th scope="col">Cidade</th>
                    <th scope="col">Estado</th>
                    <th scope="col">CEP</th>
                    <th scope="col">Dados Bancários</th>

                </tr>
            </thead>
                <tbody>
                <?php
                session_start();
                $tokenAccess =  $_SESSION['accessToken'];

                $header =(
                array(
                'Authorization: Bearer '. $tokenAccess
                )
                );


                $curl = curl_init();
                curl_setopt_array($curl, array(
                CURLOPT_URL => 'localhost:8080/clientspf',
                CURLOPT_RETURNTRANSFER => true,
                CURLOPT_ENCODING => '',
                CURLOPT_MAXREDIRS => 10,
                CURLOPT_TIMEOUT => 0,
                CURLOPT_FOLLOWLOCATION => true,
                CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                CURLOPT_CUSTOMREQUEST => 'GET',
                CURLOPT_HTTPHEADER => $header
                ));

                $response = curl_exec($curl);
                $dataJson = json_decode($response);
                curl_close($curl);

                foreach ($dataJson as $key => $value){
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
            <tbody>
            <tr>

                <td> <?php echo $id ?> </td>
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

            </tr>
            </tbody>
            <?php
    }
?>
            <tbody>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>

<div class="button">
    <button class="btn"> <i class="fa-solid fa-trash"></i>  Excluir</button>
    <button class="btn"> <i class="fa-solid fa-user-pen"></i>  Editar</button>
    <button class="btn"> <i class="fa-solid fa-user-plus"></i>  Novo</button>
</div>



  

</section>

<footer>
    <hr>
    <h2>Gestor - Gerenciador de Clientes v. 1.0</h2>
    <p>Brambilla Sistemas</p>
    <p>Suporte</p>
</footer>
    
</body>
</html>