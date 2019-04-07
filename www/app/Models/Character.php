<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Str;
use App\Models\Image;

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

    public function generateAuthToken (): string {
        $this->auth_token = Str::slug(str_replace("$", "", substr(sha1(
                            mt_rand()),10,10) . 
                            password_hash($this->nick, PASSWORD_DEFAULT) . 
                            substr(sha1(mt_rand()),10,10)));
        $this->save();
        return $this->auth_token;
    }

    public function getOutfit (): string {
        return url("game_client/src/images/outfits/" . Image::where("id", "=", $this->outfit_id)->first()->file_name);
    }

    public function getOutfitImgId (): int {
        return $this->outfit_id;
    }

    public function getLvl (): int {
        return $this->lvl;
    }

    public function getNick (): string {
        return $this->nick;
    }

    public function getId (): int {
        return $this->id;
    }

    public function getGender (): int {
        return $this->gender;
    }

}