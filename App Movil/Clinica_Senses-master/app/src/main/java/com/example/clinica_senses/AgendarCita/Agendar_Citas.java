package com.example.clinica_senses.AgendarCita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_senses.Objetos.Cita;
import com.example.clinica_senses.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Agendar_Citas extends AppCompatActivity {

    TextView Correo_usuario, Fecha_hora_actual, Fecha, Estado, Uid;
    EditText Ultima_Visita, Motivo, Como_Se_Entero;
    Button Btn_Calendario;

    int dia, mes, anio; 


    DatabaseReference BD_Firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_citas);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        InicializarVariables();
        ObtenerDatos();
        Obtener_Fecha_Hora_Actual();


       
    Btn_Calendario.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View view) {
               final Calendar calendario = Calendar.getInstance();
               
               dia = calendario.get(Calendar.DAY_OF_MONTH);
               mes= calendario.get(Calendar.MONTH);
               anio= calendario.get(Calendar.YEAR);


              DatePickerDialog datePickerDialog = new DatePickerDialog(Agendar_Citas.this, new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker datePicker, int AnioSeleccionado, int MesSeleccionado, int DiaSeleccionado) {
                      String diaFormateado, mesFormateado;

                      //Obtener día
                      if(DiaSeleccionado <10){
                          diaFormateado = "0"+String.valueOf(DiaSeleccionado);

                      }else {
                          diaFormateado = String.valueOf(DiaSeleccionado);
                      }

                      //Obtener el mes
                      int Mes = MesSeleccionado + 1;

                      if(Mes <10){
                          mesFormateado = "0"+String.valueOf(Mes);

                      }else{
                          mesFormateado = String.valueOf(Mes);
                      }

                      //Setear fecha en textView
                      Fecha.setText(diaFormateado + "/" + mesFormateado + "/" + AnioSeleccionado);

                  }

              }
                ,anio,mes,dia);
               datePickerDialog.show();

            }

   });

    }



    private void InicializarVariables(){
        Correo_usuario = findViewById(R.id.Correo_usuario);
        Uid = findViewById(R.id.uid_usuario);
        Fecha_hora_actual = findViewById(R.id.Fecha_hora_actual);
        Fecha = findViewById(R.id.Fecha);
        Estado = findViewById(R.id.Estado);

        Ultima_Visita = findViewById(R.id.Ultima_Visita);
        Motivo = findViewById(R.id.Motivo);
        Como_Se_Entero = findViewById(R.id.Como_Se_Entero);
        Btn_Calendario = findViewById(R.id.Btn_Calendario);

        BD_Firebase = FirebaseDatabase.getInstance().getReference();
    }


 
    private void ObtenerDatos(){
        String Correo_recuperado = getIntent().getStringExtra("Correo_usuario");
        String Uid_Recuperado = getIntent().getStringExtra("uid_usuario");
        Correo_usuario.setText(Correo_recuperado);
        Uid.setText(Uid_Recuperado);
    }

    private void Obtener_Fecha_Hora_Actual(){

        String Fecha_hora_registro = new SimpleDateFormat("dd-MM-yyyy / HH:mm:ss a",
           Locale.getDefault()).format(System.currentTimeMillis());

        Fecha_hora_actual.setText(Fecha_hora_registro);
    }

    private void Agregar_Cita(){

        //Obtener los datos
        String correo_usuario = Correo_usuario.getText().toString();
        String fecha_hora_actual= Fecha_hora_actual.getText().toString();
        String ultima_visita= Ultima_Visita.getText().toString();
        String motivo= Motivo.getText().toString();
        String como_se_entero= Como_Se_Entero.getText().toString();
        String fecha= Fecha.getText().toString();
        String estado=Estado.getText().toString();
        String uid = Uid.getText().toString();

         //Validar datos
         if(!correo_usuario.equals(" ") && !fecha_hora_actual.equals(" ") && !ultima_visita.equals(" ") && !motivo.equals(" ") &&
            !como_se_entero.equals(" ") && !fecha.equals(" ") && !estado.equals(" ") && !uid.equals(" ")) {
           
           Cita cita = new Cita(correo_usuario+"/"+fecha_hora_actual,
                            correo_usuario,
                            fecha_hora_actual,
                            ultima_visita,
                            motivo,
                            como_se_entero,
	                        fecha,
                            estado,
                            uid);

             String Cita_usuario = BD_Firebase.push().getKey();
             //Establecer nombre de la base de datos
             String Nombre_BD = "Citas_Registradas";

             BD_Firebase.child(Nombre_BD).child(Cita_usuario).setValue(cita);

             Toast.makeText(this, "Se ha craedo la cita exitosamente", Toast.LENGTH_SHORT).show();

              onBackPressed();

            
         }
           else{
                   Toast.makeText(this, "Llenar todos los campos, por favor", Toast.LENGTH_SHORT).show();
           }

    }

    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {

       MenuInflater menuInflater = getMenuInflater();
       menuInflater.inflate(R.menu.menu_agregar, menu);
       return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch(item.getItemId()) {
             case R.id.Agregar_Cita_BD:
                    Agregar_Cita();
                    break;
        }
      return super.onOptionsItemSelected(item);
   }


    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}