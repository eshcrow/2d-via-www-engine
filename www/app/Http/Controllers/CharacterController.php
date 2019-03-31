<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class CharacterController extends Controller {

    public function index(): object {
        return view('character');
    }
}
