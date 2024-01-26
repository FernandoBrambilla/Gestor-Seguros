<?php

//ENCODE PASSWORD
$password = $_GET['password'];
$iterations = 600000;
$salt = random_bytes(16);
$hash = hash_pbkdf2("sha256", $password,$salt,$iterations,20);

//QUERY DADOS DO USUÁRIO
$dataForRegister = (
array(
    'full_name' => $_GET['name'],
    'user_name' => $_GET['name'],
    'phone' => $_GET['phone'],
    'email' => $_GET['email'],
    'enabled' => true,
    'password' => $hash
    )
);

//ENCODE DATA EM JSON
$dataForRegisterJson = json_encode($dataForRegister);
print_r($dataForRegisterJson);
//cURL
$url = "http://localhost:8080/users";
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_POST,1);
curl_setopt($ch, CURLOPT_POSTFIELDS, $dataForRegisterJson);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$server_output = curl_exec($ch);
curl_close ($ch);



