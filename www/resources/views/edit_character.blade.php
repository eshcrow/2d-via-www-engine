@extends('layouts.app')

@section('content')

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Edytuj postać <b>{{ $character->getNick() }}</b></div>

                <div class="card-body">
                    @if (session('success'))
                        <div class="alert alert-success" role="alert">
                            {{ session('success') }}
                        </div>
                    @endif

                    <form method="post" action="{{ route('character.edit.save', ['character_id' => $character->getId()]) }}">
                        @csrf

                        <div class="form-group row">
                            <label for="nick" class="col-md-4 col-form-label text-md-right">Nick</label>

                            <div class="col-md-6">
                                <input id="nick" type="text" class="form-control{{ $errors->has('nick') ? ' is-invalid' : '' }}" name="nick" value="{{ old('nick') ? old('nick') : $character->getNick() }}" required autofocus>

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
                                    <input class="form-check-input" type="radio" name="gender" id="man" value="0" {{ $character->getGender() == 1 ?: 'checked' }}>
                                    <label class="form-check-label" for="man">
                                        Mężczyzna
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="gender" id="woman" value="1" {{ $character->getGender() == 0 ?: 'checked' }}>
                                    <label class="form-check-label" for="woman">
                                        Kobieta
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-md-4 col-form-label text-md-right">Strój</label>

                            <div class="col-md-6">
                                @foreach ($outfits as $outfit)

                                        <label>
                                            <input type="radio" id="img_radio" name="outfit" value="{{ $outfit->getId() }}" {{ $character->getOutfitImgId() == $outfit->getImgObject()->id ? 'checked' : '' }}>
                                            <div id="hero" style="background: url('{{ $outfit->getImgSrc() }}')"></div>
                                        </label>

                                @endforeach
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-8 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Zapisz
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
