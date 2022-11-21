<?php

use App\Http\Controllers\AdminController;
use App\Http\Controllers\HomeController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
Route::get('/doctors', function () {
    return view('user/doctors');
});

Route::get('/homepublic', function () {
    return view('user/home');
});

Route::get('/about', function () {
    return view('user/about');
});

Route::get('/contact', function () {
    return view('user/contact');
});

Route::get('/',[HomeController::class,'index']);

Route::get('/home',[HomeController::class,'redirect'])->middleware('auth','verified');

Route::middleware([
    'auth:sanctum',
    config('jetstream.auth_session'),
    'verified'
])->group(function () {
    Route::get('/dashboard', function () {
        return view('dashboard');
    })->name('dashboard');
});
Route::get('/add_dentista_view',[AdminController::class,'addview']);

Route::post('/upload_dentist',[AdminController::class,'upload']);

Route::post('/appointment',[HomeController::class,'appointment']);

Route::get('/myappointment',[HomeController::class,'myappointment']);

Route::get('/cancel_appoint/{id}',[HomeController::class,'cancel_appoint']);

Route::get('/showappointment',[AdminController::class,'showappointment']);

Route::get('/approved/{id}',[AdminController::class,'approved']);

Route::get('/canceled/{id}',[AdminController::class,'canceled']);

Route::get('/showdentist',[AdminController::class,'showdentist']);

Route::get('/deletedentist/{id}',[AdminController::class,'deletedentist']);

Route::get('/updatedentist/{id}',[AdminController::class,'updatedentist']);

Route::post('/edit_dentist/{id}',[AdminController::class,'edit_dentist']);

Route::get('/email/{id}',[AdminController::class,'email']);

Route::get('/sendemail/{id}',[AdminController::class,'sendemail']);




