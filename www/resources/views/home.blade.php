@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Wybór postaci</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Nick</th>
                                <th scope="col">Poziom</th>
                                <th scope="col">Graj</th>
                            </tr>
                        </thead>
                        <tbody>

                            @foreach ($characters as $character)
                                <tr>
                                    <td>
                                        <div id="hero" style="background: url('{{ $character->getOutfit() }}')"></div>
                                    </td>
                                    <td>{{ $character->getNick() }}</td>
                                    <td>{{ $character->getLvl() }}</td>
                                    <td>
                                        <a href="{{ route('game.enter', ['character_id' => $character->getId(), 'token' => Session::token()]) }}" class="btn btn-primary">
                                            Wejdź do gry
                                        </a>
                                    </td>
                                </tr>
                            @endforeach

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
