package com.example.clinica_senses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Pantalla_De_Carga extends AppCompatActivity {
        //Declarar  
        FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);

        //INICIALIZAR
        firebaseAuth = FirebaseAuth.getInstance();

        //Creamos la variable tiempo de 3000 que es = a 3 segundos
        int Tiempo = 3000;

        //Una vez entrando a la pantalla de carga dará el tiempo de ejecución y nos mandará a la Main_Activity

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            /*startActivity(new Intent(Pantalla_De_Carga.this, MainActivity.class));
            finish();*/
            VerificarUsuario();
            }
        },Tiempo);

    }
    //Crear Método fuera de OnCreate para verificar usuario y llamar a firebase
    //Con esto si el usuario no tiene una cuenta lo manda a la pantalla principal y si no es nulo, lo manda al menú princial
    private void VerificarUsuario(){
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
       startActivity(new Intent(Pantalla_De_Carga.this, MainActivity.class));
       finish();

        }else {
         startActivity(new Intent(Pantalla_De_Carga.this, MenuPrincipal.class));
         finish();
        }
    }
}