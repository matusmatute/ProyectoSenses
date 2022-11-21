package com.example.clinica_senses.ActualizarCita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_senses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Actualizar_Cita extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

            TextView Id_cita_A, Uid_Usuario_A, Correo_usuario_A, Fecha_registro_A, Fecha_A, Estado_A, Estado_nuevo;
            EditText Ultima_Visita_A, Motivo_A,Como_Se_Entero_A;
            Button Btn_Calendario_A;

            //DECLARAR STRINGS PARA ACTUALIZAR DATOS
            String id_cita_R, uid_usuario_R, correo_usuario_R, fecha_registro_R, fecha_R, estado_R, ultima_visita_R, motivo_R, como_se_entero_R;

            ImageView Tarea_Finalizada, Tarea_No_Finalizada;

            Spinner Spinner_estado;

            int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cita);
         ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actualizar cita");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        InicializaeVistas();
        RecuperarDatos();
        SetearDatos();
        ComprobarEstadoCita();
        Spinner_Estado();

        Btn_Calendario_A.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SeleccionarFecha();
            }
        });
    }

    private void InicializaeVistas(){

        Id_cita_A = findViewById(R.id.Id_cita_A);
        Uid_Usuario_A = findViewById(R.id.uid_usuario_A);
        Correo_usuario_A = findViewById(R.id.Correo_usuario_A);
        Fecha_registro_A = findViewById(R.id.Fecha_registro_A);
        Fecha_A = findViewById(R.id.Fecha_A);
        Estado_A = findViewById(R.id.Estado_A);
        Ultima_Visita_A = findViewById(R.id.Ultima_Visita_A);
        Motivo_A = findViewById(R.id.Motivo_A);
        Como_Se_Entero_A = findViewById(R.id.Como_Se_Entero_A);
        Btn_Calendario_A = findViewById(R.id.Btn_Calendario_A);


        Tarea_Finalizada = findViewById(R.id.Tarea_Finalizada);
        Tarea_No_Finalizada = findViewById(R.id.Tarea_No_Finalizada);
        Spinner_estado = findViewById(R.id.Spinner_estado);
        Estado_nuevo = findViewById(R.id.Estado_nuevo);


    }
    
    
    
    private void RecuperarDatos(){
        Bundle intent = getIntent().getExtras();

        id_cita_R = intent.getString("id_cita");
        uid_usuario_R = intent.getString("uid");
        correo_usuario_R = intent.getString("correo_usuario");
        fecha_registro_R = intent.getString("fecha_registro");
        fecha_R = intent.getString("fecha_cita");
        estado_R = intent.getString("estado");
        ultima_visita_R =intent.getString("ultima_visita");
        motivo_R = intent.getString("motivo");
        como_se_entero_R = intent.getString("como_se_entero");
        }


        private void SetearDatos(){
            Id_cita_A.setText(id_cita_R);
            Uid_Usuario_A.setText(uid_usuario_R);
            Correo_usuario_A.setText(correo_usuario_R);
            Fecha_registro_A.setText(fecha_registro_R);
            Fecha_A.setText(fecha_R);
            Estado_A.setText(estado_R);
            Ultima_Visita_A.setText(ultima_visita_R);
            Motivo_A.setText(motivo_R);
            Como_Se_Entero_A.setText(como_se_entero_R);

        }
        

        private void ComprobarEstadoCita(){
            String estado_cita = Estado_A.getText().toString();

            if (estado_cita.equals("No finalizado")){
                Tarea_No_Finalizada.setVisibility(View.VISIBLE);

            }

            if(estado_cita.equals("Finalizado")){
                Tarea_Finalizada.setVisibility(View.VISIBLE);
            }
        }


        private void SeleccionarFecha(){
            final Calendar calendario = Calendar.getInstance();
               
               dia = calendario.get(Calendar.DAY_OF_MONTH);
               mes= calendario.get(Calendar.MONTH);
               anio= calendario.get(Calendar.YEAR);


              DatePickerDialog datePickerDialog = new DatePickerDialog(Actualizar_Cita.this, new DatePickerDialog.OnDateSetListener() {
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
                      Fecha_A.setText(diaFormateado + "/" + mesFormateado + "/" + AnioSeleccionado);

                  }

              }
                ,anio,mes,dia);
               datePickerDialog.show();
        }

        private void Spinner_Estado(){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
               R.array.Estados_cita, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner_estado.setAdapter(adapter);
            Spinner_estado.setOnItemSelectedListener(this);
        }


        private void Actualizar_Cita_BD(){
            String ultima_visitaActualizar = Ultima_Visita_A.getText().toString();
            String motivoActualizar = Motivo_A.getText().toString();
            String como_se_enteroActualizar = Como_Se_Entero_A.getText().toString();
            String fechaActualizar = Fecha_A.getText().toString();
            String estadoActualizar = Estado_nuevo.getText().toString();

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("Citas_Registradas");


            //Consulta
            Query query = databaseReference.orderByChild("id_cita").equalTo(id_cita_R);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        ds.getRef().child("ultima_visita").setValue(ultima_visitaActualizar);
                        ds.getRef().child("motivo").setValue(motivoActualizar);
                        ds.getRef().child("como_se_entero").setValue(como_se_enteroActualizar);
                        ds.getRef().child("fecha_cita").setValue(fechaActualizar);
                        ds.getRef().child("estado").setValue(estadoActualizar);
                    }
                    Toast.makeText(Actualizar_Cita.this, "Cita actualizada con exito", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
           }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
            String ESTADO_ACTUAL = Estado_A.getText().toString();

            String Posicion_1 = adapterView.getItemAtPosition(1).toString();

            String estado_seleccionado = adapterView.getItemAtPosition(i).toString();
            Estado_nuevo.setText(estado_seleccionado);

            if (ESTADO_ACTUAL.equals("Finalizado")){
                Estado_nuevo.setText(Posicion_1);
            }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

       @Override 
    public boolean onCreateOptionsMenu(Menu menu) {

       MenuInflater menuInflater = getMenuInflater();
       menuInflater.inflate(R.menu.menu_actualizar, menu);
       return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch(item.getItemId()) {
             case R.id.Actualizar_Cita_BD:
                 Actualizar_Cita_BD();
                 Toast.makeText(this, "La cita ha sido actualizada", Toast.LENGTH_SHORT).show();
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