<?php

use Illuminate\Database\Seeder;
use App\Models\Permissions;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {

        $permissions = [
            "Użytkownik" => [
                "user" => true,
                "admin" => false,
                "game_master" => false,
                "moderator" => false
            ],
            "Administrator" => [
                "user" => true,
                "admin" => true,
                "game_master" => false,
                "moderator" => false
            ],
            "Moderator" => [
                "user" => true,
                "admin" => false,
                "game_master" => false,
                "moderator" => true
            ],
            "Mistrz Gry" => [
                "user" => true,
                "admin" => false,
                "game_master" => true,
                "moderator" => false
            ]
        ];

        foreach ($permissions as $key => $value) {
            Permissions::create([
                "name" => $key,
                "user" => $value["user"],
                "admin" => $value["admin"],
                "game_master" => $value["game_master"],
                "moderator" => $value["moderator"]
            ]);
        }

    }
}
