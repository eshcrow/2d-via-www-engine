<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Character;
use App\Models\DefaultOutfit;
use Illuminate\Support\Facades\Auth;

class CharacterController extends Controller {

    public function index (): object {
        return view('character');
    }

    public function create (Request $request): object {

        $this->validate($request, [
            "nick" => "required|string|min:3|max:40|unique:players",
            "gender" => "required|min:0|max:1"
        ]);

        $outfit_id = DefaultOutfit::where("gender", "=", $request->gender)->first()->id;

        Character::create([
            "user_id" => Auth::user()->id,
            "nick" => $request->nick,
            "gender" => $request->gender,
            "outfit_id" => $outfit_id,
            "map_id" => env("START_MAP_ID"),
            "x" => env("START_X"),
            "y" => env("START_Y")
        ]);

        return redirect()
                ->back()
                ->with("character_create_success", "Postać utworzona pomyślnie.");
    } 

}
