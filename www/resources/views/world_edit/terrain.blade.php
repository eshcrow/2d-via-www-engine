<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Game Terrain Editor</title>

        <!-- CSRF Token -->
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <!-- IE support -->
        <script type="text/javascript">
            if (/MSIE \d|Trident.*rv:/.test(navigator.userAgent))
                document.write('<script src="https://cdnjs.cloudflare.com/ajax/libs/bluebird/3.3.4/bluebird.min.js"><\/script>');
        </script>

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
            <div id="menu">
                <div id="coords"></div>
            </div>
            <div id="tilesets-list">
                <ul>
                    <li onclick="terrainEditor.tilesets.upload.showWindow()"><b>Dodaj nowy tileset.</b></li>
                </ul>
            </div>
            <div id="tileset">
                <div>
                    <div id="tile-cursor"></div>
                </div>
            </div>
        </div>

        <div id="terrain">
            <canvas id="terrainCanvas"></canvas>
        </div>

    </body>

</html>