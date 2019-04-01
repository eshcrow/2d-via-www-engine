@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Nowa postać</div>

                <div class="card-body">
                    @if (session('character_create_success'))
                        <div class="alert alert-success" role="alert">
                            {{ session('character_create_success') }}
                        </div>
                    @endif

                    <form method="post" action="{{ route('character.create') }}">
                        @csrf

                        <div class="form-group row">
                            <label for="nick" class="col-md-4 col-form-label text-md-right">Nick</label>

                            <div class="col-md-6">
                                <input id="nick" type="text" class="form-control{{ $errors->has('nick') ? ' is-invalid' : '' }}" name="nick" value="{{ old('nick') }}" required autofocus>

                                @if ($errors->has('nick'))
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $errors->first('nick') }}</strong>
                                    </span>
                                @endif
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="nick" class="col-md-4 col-form-label text-md-right">Płeć</label>

                            <div class="col-md-6">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="gender" id="man" value="0" checked>
                                    <label class="form-check-label" for="man">
                                        Mężczyzna
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="gender" id="woman" value="1">
                                    <label class="form-check-label" for="woman">
                                        Kobieta
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Stwórz
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-3">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Istniejące postacie</div>

                <div class="card-body">
                    @if (session('character_no_exists'))
                        <div class="alert alert-danger" role="alert">
                            {{ session('character_no_exists') }}
                        </div>
                    @endif


                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Nick</th>
                                <th scope="col">Poziom</th>
                                <th scope="col">Edytuj</th>
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
                                        <a href="{{ route('character.edit', ['character_id' => $character->getId()]) }}">
                                            Edytuj
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
