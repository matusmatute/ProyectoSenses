package com.example.clinica_senses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_senses.AcercaDe.Acerca_De;
import com.example.clinica_senses.AgendarCita.Agendar_Citas;
import com.example.clinica_senses.Archivados.Archivados;
import com.example.clinica_senses.ListarCitas.Listar_Citas;
import com.example.clinica_senses.Perfil.Perfil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {
    Button CerrarSesion, AgendarCita, ListarCitas, Archivados,Perfil,AcercaDe;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
   
    TextView NombresPrincipal,CorreoPrincipal, UidPrincipal;
    Button EstadoCuentaPrincipal;
    ProgressBar progressBarDatos;
    ProgressDialog progressDialog;

    LinearLayoutCompat Linear_Nombres, Linear_Correo, Linear_Verificacion;
    DatabaseReference users;

    Dialog dialog_cuenta_verificada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Clinica Senses");

        AgendarCita = findViewById(R.id.AgendarCita);
        ListarCitas = findViewById(R.id.ListarCitas);
        Archivados = findViewById(R.id.Archivados);
        Perfil = findViewById(R.id.Perfil);
        AcercaDe = findViewById(R.id.AcercaDe);


        UidPrincipal = findViewById(R.id.UidPrincipal);
        NombresPrincipal = findViewById(R.id.NombresPrincipal);
        CorreoPrincipal = findViewById(R.id.CorreoPrincipal);
        EstadoCuentaPrincipal = findViewById(R.id.EstadoCuentaPrincipal);
        progressBarDatos = findViewById(R.id.progressBarDatos);
 
        dialog_cuenta_verificada = new Dialog(this);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Espere por favor...");
        progressDialog.setCanceledOnTouchOutside(false); 

        Linear_Nombres = findViewById(R.id.Linear_Nombres);
        Linear_Correo = findViewById(R.id.Linear_Correo);
        Linear_Verificacion = findViewById(R.id.Linear_Verificacion); 

        users = FirebaseDatabase.getInstance().getReference("users");

        CerrarSesion = findViewById(R.id.CerrarSesion);


            //Autenticacion en firebase
           firebaseAuth = FirebaseAuth.getInstance();
           user =  firebaseAuth.getCurrentUser();

           EstadoCuentaPrincipal.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   if(user.isEmailVerified()){
                       //Si la cuenta está verificada
                       //Toast.makeText(MenuPrincipal.this,"Cuenta ya verificada", Toast.LENGTH_SHORT).show();
                        AnimacionCuentaVerificada();
                   }else{
                       //Si la cuenta NO está verificada
                       VerificarCuentaCorreo();
                   }

               }
           });


           //Evento para el boton de agendar cita
           AgendarCita.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                    //Obtener información 
                    String Correo_usuario = CorreoPrincipal.getText().toString();
                    String uid = UidPrincipal.getText().toString();

                    //Pasar datos a la siguiente actividad
                    Intent intent = new Intent(MenuPrincipal.this, Agendar_Citas.class);
                    intent.putExtra("Correo_usuario",Correo_usuario);
                    intent.putExtra("uid_usuario", uid);
                    startActivity(intent);
               }
           });

           //Evento para el boton Listar Citas
           ListarCitas.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startActivity(new Intent(MenuPrincipal.this, Listar_Citas.class));
                   Toast.makeText(MenuPrincipal.this, "Listar citas", Toast.LENGTH_SHORT).show();
               }
           });

           //Evento para el boton Archivados
           Archivados.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startActivity(new Intent(MenuPrincipal.this, Archivados.class));
                   Toast.makeText(MenuPrincipal.this, "Tus citas archivadas", Toast.LENGTH_SHORT).show();
               }
           });

           //Evento para el boton Perfil
           Perfil.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startActivity(new Intent(MenuPrincipal.this, Perfil.class));
                   Toast.makeText(MenuPrincipal.this, "Perfil", Toast.LENGTH_SHORT).show();
               }
           });

           AcercaDe.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startActivity(new Intent(MenuPrincipal.this, Acerca_De.class));
                   Toast.makeText(MenuPrincipal.this, "Acerca De", Toast.LENGTH_SHORT).show();
               }
           });

            //Evento para Cerrar sesion en la aplicacion
            CerrarSesion.setOnClickListener(new View.OnClickListener(){
                  @Override
                     public void onClick(View view) { 
                          SalirAplicacion();
                      }
                  });
    }


    private void VerificarCuentaCorreo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("verificar cuenta")
                .setMessage("¿Estás seguro (a) de enviar instrucciones de verificación a su correo electrónico? "
                +user.getEmail())
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        EnviarCorreoAVerificacion();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i){
                    Toast.makeText(MenuPrincipal.this, "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                   } 
                }).show();
    }

    private void EnviarCorreoAVerificacion(){
        progressDialog.setMessage("Enviando instrucciones de verificaación a su correo electrónico " + user.getEmail());
        progressDialog.show();

        user.sendEmailVerification()
              .addOnSuccessListener(new OnSuccessListener<Void>(){
               @Override
                public void onSuccess(Void unused){
                    //Envío fue exitoso
                    progressDialog.dismiss();
                    Toast.makeText(MenuPrincipal.this, "Instrucciones enviadas, revise su bandeja "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }                
               
              }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Falló el envío
                        Toast.makeText(MenuPrincipal.this, "Falló debido a "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    
    }


    private void VerificarEstadoDeCuenta(){
        String Verficado = "Verficado";
        String No_Verificado = "No Verificado";

        if(user.isEmailVerified()){
            EstadoCuentaPrincipal.setText(Verficado);
            EstadoCuentaPrincipal.setBackgroundColor(Color.rgb(127, 179, 213));
        }else{
            EstadoCuentaPrincipal.setText(No_Verificado);
            EstadoCuentaPrincipal.setBackgroundColor(Color.rgb(192, 57, 43));
        }
    }


    private void AnimacionCuentaVerificada(){
        Button EntendidoVerificado;

        dialog_cuenta_verificada.setContentView(R.layout.dialogo_cuenta_verificada);

        EntendidoVerificado = dialog_cuenta_verificada.findViewById(R.id.EntendidoVerificado);

        EntendidoVerificado.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dialog_cuenta_verificada.dismiss();
            }
        });

        dialog_cuenta_verificada.show();
        dialog_cuenta_verificada.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }

    private void ComprobarInicioSesion(){
        if(user!=null){
            //El usuario ha iniciado sesion
            CargaDeDatos();
        }else{
            //Lo dirigira al MainActivity
            startActivity(new Intent(MenuPrincipal.this,MainActivity.class));
            finish();
        }
    }

    private void CargaDeDatos(){

        VerificarEstadoDeCuenta();

        users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //si el usuario existe
                if(snapshot.exists()){
                    //El progress bar se oculta
                    progressBarDatos.setVisibility(View.GONE);
                    //Los textView Se muestran
                    //NombresPrincipal.setVisibility(View.VISIBLE);
                    //CorreoPrincipal.setVisibility(View.VISIBLE);
                    Linear_Nombres.setVisibility(View.VISIBLE);
                    Linear_Correo.setVisibility(View.VISIBLE);
                    Linear_Verificacion.setVisibility(View.VISIBLE);

                    //Obtener los datos
                    String uid = ""+snapshot.child("uid").getValue();
                    String nombres = ""+snapshot.child("nombres").getValue();
                    String correo = ""+snapshot.child("correo").getValue();

                    //setear los datos en los respectivos TextView
                    UidPrincipal.setText(uid);
                    NombresPrincipal.setText(nombres);
                    CorreoPrincipal.setText(correo);

                    //Habilitar los botones del menu
                    AgendarCita.setEnabled(true);
                    ListarCitas.setEnabled(true);
                    Archivados.setEnabled(true);
                    Perfil.setEnabled(true);
                    AcercaDe.setEnabled(true);
                    CerrarSesion.setEnabled(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SalirAplicacion() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Has cerrado sesión exitosamente", Toast.LENGTH_SHORT).show();
    }
}