<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Character;
use App\Models\DefaultOutfit;
use Illuminate\Support\Facades\Auth;

class CharacterController extends Controller {

    public function index (): object {
        return view('character')
                ->with("characters", 
                    Character::where("user_id", "=", Auth::user()->id)->get()
                );
    }

    public function editSave (Request $request, $character_id): object {
        $character_data = Character::where("id", "=", $character_id)
                            ->where("user_id", "=", Auth::user()->id)
                            ->first();

        if (!$character_data) {
            return redirect()->route('character')
                    ->with("character_no_exists", "Postać której szukasz nie istnieje.");
        }

        if ($request->nick != $character_data->getNick()) {
            $this->validate($request, [
                "nick" => "required|string|min:3|max:40|unique:players"
            ]);
        }

        $this->validate($request, [
            "gender" => "required|min:0|max:1"
        ]);

        $outfit = DefaultOutfit::where("id", "=", $request->outfit)
                    ->where("gender", "=", $request->gender)->first();

        if (!$outfit) {
            $outfit = DefaultOutfit::where("gender", "=", $request->gender)->first();
        }

        $character_data->nick = $request->nick;
        $character_data->gender = $request->gender;
        $character_data->outfit_id = $outfit->getImgObject()->id;
        $character_data->save();

        return redirect()->back()
                ->with("success", "Dane Twojej postaci zostały zapisane.");
    }

    public function editView ($character_id): object {
        $character_data = Character::where("id", "=", $character_id)
                            ->where("user_id", "=", Auth::user()->id)
                            ->first();

        if (!$character_data) {
            return redirect()->route('character')
                    ->with("character_no_exists", "Postać której szukasz nie istnieje.");
        }

        return view("edit_character")
                ->with("character", $character_data)
                ->with("outfits", DefaultOutfit::where("gender", "=", $character_data->getGender())->get());
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
