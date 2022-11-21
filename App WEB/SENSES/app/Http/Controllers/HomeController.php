<?php

namespace App\Http\Controllers;

use App\Models\Appointment;
use App\Models\Dentist;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class HomeController extends Controller
{
    public function redirect() {

        if(Auth::id()) {
            if(Auth::user()->usertype=='0') {

                $dentist = Dentist::all();

                return view('user.home', compact('dentist'));

            }
            else {

                return view('admin.home');
            }

        }
        else {

            return redirect()->back();
        }
    }

    public function index() {

        if(Auth::id()) {

            return redirect('home');
        }
        else {
            
            $dentist = Dentist::all();
            return view('user.home', compact('dentist'));
        }
    }

    public function appointment(Request $request) {

        $data=new Appointment;

        $data->name=$request->name;

        $data->phone=$request->number;

        $data->email=$request->email;

        $data->date=$request->date;

        $data->dentist=$request->dentist;

        $data->message=$request->message;

        $data->status='En preogreso';

        if (Auth::id()) {

            $data->user_id=Auth::user()->id;
        }

        $data->save();

        return redirect()->back()->with('message', 'La cita fue enviada con exito');
    }

    public function myappointment(Request $request) {

        if (Auth::id()) {
            
            $userid=Auth::user()->id;

            $appoint=appointment::where('user_id',$userid)->get();

            return view('user.myappointment', compact('appoint'));

        }

        return redirect()->back();

    }

    public function cancel_appoint($id) {

        $data=appointment::find($id);

        $data->delete();

        return redirect()->back();

    }

}
