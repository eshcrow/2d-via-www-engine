<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Cookie;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;
use App\Models\Character;

class GameController extends Controller {

    public function enter ($character_id, $token): object {
        $this->checkToken($token);

        $character_data = Character::where("id", "=", $character_id)
                            ->where("user_id", "=", Auth::user()->id)->first();

        if (!$character_data) {
            return redirect()->route('home')
                    ->with("status-error", "Postać której szukasz nie istnieje.");
        }

        Cookie::queue("auth_token", $character_data->generateAuthToken(), 0);

        $character_data->last_action = time(); 
        $character_data->save(); 

        return redirect()->route("game.render");
    }

    public function renderClient (): object {
        return view("layouts.game_client");
    }

}
