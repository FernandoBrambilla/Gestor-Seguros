<?php

    //VERIFICA CAMPOS VAZIO
        if (empty($_GET['userName']) || empty($_GET['password'])) {
            header('Location: http://gestor/app/login.php');
            exit();
        }
        //GET FORM HTML
        $postFields = json_encode(
            array(
                "username" => $_GET['userName'],
                "password" => $_GET['password']
            )
        );
        //POST API
        $curl = curl_init();
        curl_setopt_array($curl, array(
            CURLOPT_URL => 'localhost:8080/auth/signin',
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_ENCODING => '',
            CURLOPT_MAXREDIRS => 10,
            CURLOPT_TIMEOUT => 0,
            CURLOPT_FOLLOWLOCATION => true,
            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
            CURLOPT_CUSTOMREQUEST => 'POST',
            CURLOPT_POSTFIELDS => $postFields,
            CURLOPT_HTTPHEADER => array(
                'Content-Type: application/json'
            ),
        ));
        $response = curl_exec($curl);
        curl_close($curl);
        $responseJson = json_decode($response);

        //GET ACCESSTOKEN
    function getAccessToken($responseJson){
        return $responseJson->accessToken;
    }
    //GET REFLESHTOKEN
    function getRefleshToken($respondeJson){
        return $respondeJson->refleshToken;
    }
    //VERIFICA AUTENTICAÇÃO
    if ($responseJson->authenticated == 1) {
        session_start();
        $_SESSION['accessToken'] = getAccessToken($responseJson);
        $_SESSION['refleshToken'] = getRefleshToken($responseJson);
        header('Location: http://gestor/app/app_gestor_init.php');
        die();
    } else {
        header('Location: http://gestor/login/login.php');
        exit();
    }




