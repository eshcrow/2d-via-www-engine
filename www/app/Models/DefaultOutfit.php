<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use App\Models\Image;

class DefaultOutfit extends Model {

    public $timestamps = false;
    protected $table = "default_outfits";

    public function getId (): int {
        return $this->id;
    }

    public function getImgSrc (): string {
        return url("game_client/src/images/outfits/" . Image::where("id", "=", $this->image_id)->first()->file_name);
    }

    public function getImgObject (): object {
        return Image::where("id", "=", $this->image_id)->first();
    }

}
