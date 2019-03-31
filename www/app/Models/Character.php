<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Character extends Model {

    protected $table = "players";
    protected $fillable = [
        'user_id',
        'nick',
        'gender',
        'outfit_id',
        'map_id',
        'x',
        'y'
    ];

}