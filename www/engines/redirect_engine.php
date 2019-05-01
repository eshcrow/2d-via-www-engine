<?php
require '../vendor/autoload.php';

$dotenv = Dotenv\Dotenv::create("../");
$dotenv->load();

if (
    empty($_COOKIE['auth_token']) ||
    empty($_COOKIE['pid'])
) {
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
} else if (empty($_POST)) {
    echo json_encode([
        "events" => ["error"],
        "data" => [
            [
                "lvl" => 2,
                "msg" => "Too few arguments for server request."
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
$clientAuthToken = getenv('CLIENT_AUTH_TOKEN');

/**
 * @var client-lang Language selected by client
 * @var cat Client Auth Token
 * @var pat Player Auth Token
 * @var pid Player ID
 */

$headers = [
    "client-lang" => "pl",
    "cat" => $clientAuthToken,
    "pat" => sanitize($_COOKIE['auth_token']),
    "pid" => sanitize($_COOKIE['pid'])
];

$requestArguments = json_decode(file_get_contents('php://input'));

foreach ($requestArguments as $key => $value) {
    $sanitizedKey = sanitize($key);
    $sanitizedValue = sanitize($value);

    $headers[$sanitizedKey] = $sanitizedValue;
}

$headers = json_encode($headers);

$curl = curl_init($url);

curl_setopt($curl, CURLOPT_POST, 1);
curl_setopt($curl, CURLOPT_HTTPHEADER, array(
    'Content-type: application/x-www-form-urlencoded',
    "data: {$headers}"
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

function sanitize ($value) {
    return preg_replace('/[^\-\s\pN\pL]+/u', '', $value);
}