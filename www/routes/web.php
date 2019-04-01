<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', 'HomeController@index');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::get('/character', [
    'uses' => '\App\Http\Controllers\CharacterController@index',
    'as' => 'character',
    'middleware' => ['auth']
]);

Route::post('/character/create', [
    'uses' => '\App\Http\Controllers\CharacterController@create',
    'as' => 'character.create',
    'middleware' => ['auth']
]);

Route::get('/character/edit/{character_id}', [
    'uses' => '\App\Http\Controllers\CharacterController@editView',
    'as' => 'character.edit',
    'middleware' => ['auth']
]);

Route::post('/character/edit/{character_id}', [
    'uses' => '\App\Http\Controllers\CharacterController@editSave',
    'as' => 'character.edit.save',
    'middleware' => ['auth']
]);

Route::get('/enter_game/{character_id}/{token}', [
    'uses' => '\App\Http\Controllers\GameController@enter',
    'as' => 'game.enter',
    'middleware' => ['auth']
]);

Route::get('/game', [
    'uses' => '\App\Http\Controllers\GameController@renderClient',
    'as' => 'game.render',
    'middleware' => ['auth']
]);