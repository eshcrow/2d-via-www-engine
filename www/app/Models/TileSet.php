<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Tile;

class TileSet extends Model {

    public $timestamps = false;
    protected $table = "tilesets";
    protected $fillable = [
        "name",
        "fileName"
    ];

    public function getTiles () {
        return Tile::where("tilesetId", "=", $this->id)->results();
    }

}