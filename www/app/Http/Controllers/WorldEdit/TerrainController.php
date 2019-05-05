<?php

namespace App\Http\Controllers\WorldEdit;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;
use App\Models\TileSet;
use App\Models\Tile;

class TerrainController extends Controller {

    private $allowedTilesetExt = [
        "bmp",
        "png"
    ];

    public function renderToolPage () : object {
        return view("world_edit.terrain");
    }

    public function getTilesets () {
        $tileSets = TileSet::get();

        $sets = [];

        foreach ($tileSets as $tileset) {
            array_push($sets, [
                "id" => $tileset->id,
                "name" => $tileset->name,
                "fileName" => $tileset->fileName
            ]);
        }

        return json_encode($sets);
    }

    public function uploadTileset (Request $request) {
        if (!$request->hasFile('tileset_file')) {
            return json_encode(["error" => "Nie wybrano żadnego pliku z tilesetem!"]);
        }

        $file = $request->file('tileset_file');

        if (!in_array($file->getClientOriginalExtension(), $this->allowedTilesetExt)) {
            return json_encode(["error" => "Niepoprawne rozszerzenie pliku z tilesetem!"]);
        }

        $tilesetName = empty($request->input("tileset_name")) ? pathinfo($file->getClientOriginalName(), PATHINFO_FILENAME) : $request->input("tileset_name");

        $tileset = TileSet::create([
            "name" => $tilesetName,
            "fileName" => $file->getClientOriginalName()
        ]);

        $file->move("game_client/src/images/tilesets", $file->getClientOriginalName());

        return json_encode(["tilesetPath" => $tileset->fileName]);
    }

}
