<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Str;
use App\Models\Image;

class Character extends Model {

    public $timestamps = false;
    protected $table = "players";
    protected $fillable = [
        'userId',
        'nick',
        'gender',
        'outfitId',
        'mapId',
        'x',
        'y'
    ];

    public function generateAuthToken (): string {
        $this->authToken = Str::slug(str_replace("$", "", substr(sha1(
                            mt_rand()),10,10) . 
                            password_hash($this->nick, PASSWORD_DEFAULT) . 
                            substr(sha1(mt_rand()),10,10)));
        $this->save();
        return $this->authToken;
    }

    public function getOutfit (): string {
        return url("game_client/src/images/outfits/" . Image::where("id", "=", $this->outfitId)->first()->file_name);
    }

    public function getOutfitImgId (): int {
        return $this->outfitId;
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