<?php

namespace App\Http\Controllers;

use App\Models\Appointment;
use App\Models\Dentist;
use App\Notifications\SendEmailNotification;
use Illuminate\Http\Request;
use Illuminate\Notifications\Notification;


class AdminController extends Controller
{
    public function addview() {

        return view('admin.add_dentista');

    }

    public function upload(Request $request) {

        $dentist=new dentist;

        $image=$request->photo;
        $imagename=time().'.'.$image->getClientoriginalExtension();

        $request->photo->move('dentistimage', $imagename);

        $dentist->image=$imagename;

        $dentist->name=$request->name;
        $dentist->last_name=$request->last_name;
        $dentist->phone=$request->phone;
        $dentist->email=$request->email;
        $dentist->speciality=$request->speciality;

        $dentist->save();

        return redirect()->back();

    }

    public function showappointment() {

        $data=appointment::all();

        return view('admin.showappointment',compact('data'));
    }

    public function approved($id) {

        $data=appointment::find($id);

        $data->status='Aprobado';

        $data->save();

        return redirect()->back();

    }

    public function canceled($id) {

        $data=appointment::find($id);

        $data->status='Cancelado';

        $data->save();

        return redirect()->back();

    }

    public function showdentist() {

        $data=Dentist::all();

        return view('admin.showdentist', compact('data'));
    }

    public function deletedentist($id) {

        $data=Dentist::find($id);

        $data->delete();

        return redirect()->back();
    }

    public function updatedentist($id) {

        $data = Dentist::find($id);

        return view('admin.updatedentist', compact('data'));
    }

    public function edit_dentist(Request $request, $id) {

        $dentist = Dentist::find($id);

        $image=$request->photo;

        if($image) {

            $imagename=time().'.'.$image->getClientoriginalExtension();

            $request->photo->move('dentistimage', $imagename);

            $dentist->image=$imagename;
        }

        $dentist->name=$request->name;
        $dentist->last_name=$request->last_name;
        $dentist->phone=$request->phone;
        $dentist->email=$request->email;
        $dentist->speciality=$request->speciality;

        $dentist->save();

        return redirect()->back()->with('message','Actualizacion Exitosa');
    }

    public function email($id) {

        $data=appointment::find($id);

        return view('admin.email', compact('data'));

    }

    public function sendemail(Request $request, $id) {

        $data=appointment::find($id);

        $details=[

            'greeting' => $request->greeting,

            'body' => $request->body,

            'actiontext' => $request->actiontext,

            'actionurl' => $request->actionurl,

            'endpart' => $request->endpart
        ];

        Notification::send($data, new SendEmailNotification($details));

        return redirect()->back()->with('message','Bien');

    }

}
