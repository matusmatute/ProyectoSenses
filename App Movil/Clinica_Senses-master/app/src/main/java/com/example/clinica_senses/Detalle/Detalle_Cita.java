package com.example.clinica_senses.Detalle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.clinica_senses.R;

public class Detalle_Cita extends AppCompatActivity {


     TextView Id_cita_detalle, Uid_Detalle, Correo_usuario_Detalle, Ultima_Visita_Detalle,
              Motivo_Detalle,Fecha_Registro_Detalle, Fecha_Cita_Detalle, Estado_Detalle, Como_Se_Entero;

    //DECLARAR STRINGS PARA ACTUALIZAR DATOS
    String id_cita_R, uid_usuario_R, correo_usuario_R, fecha_registro_R, fecha_R, estado_R, ultima_visita_R, motivo_R, como_se_entero_R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cita);
        
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        //Aquí el señor lde io click a setTitle y eligió que el actionBar !=null (No es NULO)
        actionBar.setTitle("Detalle de cita");  
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        
        InicializarVistas();
        RecuperarDatos();
        SetearDatosRecuperados();
    }

    private void InicializarVistas(){
        Id_cita_detalle = findViewById(R.id.Id_cita_detalle);
        Uid_Detalle = findViewById(R.id.Uid_Detalle);
        Correo_usuario_Detalle = findViewById(R.id.Correo_usuario_Detalle);
        Ultima_Visita_Detalle = findViewById(R.id.Ultima_Visita_Detalle);
        Motivo_Detalle = findViewById(R.id.Motivo_Detalle);
        Fecha_Registro_Detalle = findViewById(R.id.Fecha_Registro_Detalle);
        Fecha_Cita_Detalle = findViewById(R.id.Fecha_Cita_Detalle);
        Estado_Detalle = findViewById(R.id.Estado_Detalle);
        Como_Se_Entero = findViewById(R.id.Como_Se_Entero_Detalle);

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

    private void SetearDatosRecuperados(){
        Id_cita_detalle.setText(id_cita_R);
        Uid_Detalle.setText(uid_usuario_R);
        Correo_usuario_Detalle.setText(correo_usuario_R);
        Fecha_Registro_Detalle.setText(fecha_registro_R);
        Fecha_Cita_Detalle.setText(fecha_R);
        Estado_Detalle.setText(estado_R);
        Ultima_Visita_Detalle.setText(ultima_visita_R);
        Motivo_Detalle.setText(motivo_R);
        Como_Se_Entero.setText(como_se_entero_R);
        
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}