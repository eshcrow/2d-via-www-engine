<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Game Terrain Editor</title>

        <!-- CSRF Token -->
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <!-- Scripts -->
        <script src="{{ asset('js/TerrainEditor.js') }}" defer></script>

        <!-- Styles -->
        <link href="{{ asset('css/normalize.css') }}" rel="stylesheet">
        <link href="{{ asset('css/TerrainEditor.css') }}" rel="stylesheet">
        <link href="{{ asset('css/buttons.css') }}" rel="stylesheet">
        <link href="{{ asset('css/windows.css') }}" rel="stylesheet">
        <link href="{{ asset('css/forms.css') }}" rel="stylesheet">

    </head>

    <body>

        <div id="tilesets">
            <div id="manu"></div>
            <div id="tilesets-list">
                <ul>
                    <li onclick="terrainEditor.tilesets.upload.showWindow()"><b>Dodaj nowy tileset.</b></li>
                </ul>
            </div>
            <div id="tileset">
                <font size="6" color="white">Nie wybrano żadnego tilesetu!</font>
            </div>
        </div>

        <div id="terrain"></div>

    </body>

</html>