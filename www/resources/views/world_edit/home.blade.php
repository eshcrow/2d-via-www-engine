@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Wybór narzędzia</div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif

                    <div class="list-group">
                      <a href="{{ route('world_edit.terrain') }}" class="list-group-item list-group-item-action">Edytor terenu</a>
                      <div class="list-group-item list-group-item-action disabled">Edytor NPC</div>
                      <div class="list-group-item list-group-item-action disabled">Edytor dialogów</div>
                      <div class="list-group-item list-group-item-action disabled">Edytor zadań</div>
                      <div class="list-group-item list-group-item-action disabled">Edytor przedmiotów</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
