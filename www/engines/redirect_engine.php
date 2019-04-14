<?php
require '../vendor/autoload.php';

$dotenv = Dotenv\Dotenv::create("../");
$dotenv->load();

if (empty($_COOKIE['auth_token'])) {
    echo json_encode([
        "error" => "Sesja wygasła. Zaloguj się ponownie na stronie głównej klienta gry."
    ]);
    die();
}

$url = 'http://127.0.0.1:666';
$serverAuthToken = $_ENV['SERVER_AUTH_TOKEN'];
$data = json_encode([
    "server_auth_token" => $serverAuthToken,
    "player_auth_token" => $_COOKIE['auth_token'],
    "request" => "hui",
    "data" => "Jebać disa"
]);

$context = stream_context_create(array(
    'http' => array(
        'method' => 'POST',
        'header' => "Content-type: application/x-www-form-urlencoded\ndata: {$data}",
        'timeout' => 60
    )
));

$resp = file_get_contents($url, FALSE, $context);
print_r($resp);