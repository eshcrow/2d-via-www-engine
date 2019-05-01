<?php

namespace App\Http\Controllers\WorldEdit;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;

class WorldEditController extends Controller {

    public function __construct () {
        return redirect()->back();
    }

    public function indexPage () : object {
        return view("world_edit.home");
    }

}
