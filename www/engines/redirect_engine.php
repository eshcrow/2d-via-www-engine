<?php
require '../vendor/autoload.php';

$dotenv = Dotenv\Dotenv::create("../");
$dotenv->load();

if (empty($_COOKIE['auth_token'])) {
    echo json_encode([
        "events" => ["error"],
        "data" => [
            [
                "lvl" => 1,
                "msg" => "Session expired. Log in again in game client home page."
            ]
        ]
    ]);
    die();
}

/**
 * @var url where Game HTTP server is started.
 * @var clientAuthToken auth token from .env.
 */

$url = 'http://127.0.0.1:666';
$clientAuthToken = $_ENV['CLIENT_AUTH_TOKEN'];

/**
 * @var cat Client Auth Token
 * @var pat Player Auth Token
 * @var pid Player ID
 */

$data = json_encode([
    "cat" => $clientAuthToken,
    "pat" => $_COOKIE['auth_token'],
    "pid" => 1,
    "request" => "init",
    "data" => "dfgdfgfg"
]);

$context = stream_context_create(array(
    'http' => array(
        'method' => 'POST',
        'header' => "Content-type: application/x-www-form-urlencoded\ndata: {$data}",
        'timeout' => 60
    )
));

$curl = curl_init($url);

curl_setopt($curl, CURLOPT_POST, 1);
curl_setopt($curl, CURLOPT_HTTPHEADER, array(
    'Content-type: application/x-www-form-urlencoded',
    "data: {$data}"
));
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($curl);
$status = curl_getinfo($curl);

if ($status["http_code"] !== 200) {
    echo json_encode([
        "events" => ["error"],
        "data" => [
            [
                "lvl" => 1,
                "msg" => "Game server is not responding. Please try again later."
            ]
        ]
    ]);
} else if ($status["content_type"] !== "application/object") {
    echo json_encode([
        "events" => ["error"],
        "data" => [
            [
                "lvl" => 1,
                "msg" => "Internal Server Error. Try again letter."
            ]
        ]
    ]);
} else {
    print_r($response);
}

//echo "<pre>" , var_dump(curl_getinfo($curl)) , "</pre>";
curl_close($curl);