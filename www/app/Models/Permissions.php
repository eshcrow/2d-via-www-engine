<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Permissions extends Model {

    public $timestamps = false;
    protected $table = "permissions";

    public function has ($key) : bool {
        return $this[$key] == 1;
    }

}