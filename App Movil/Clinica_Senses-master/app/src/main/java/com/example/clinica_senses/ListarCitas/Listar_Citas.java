package com.example.clinica_senses.ListarCitas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinica_senses.ActualizarCita.Actualizar_Cita;
import com.example.clinica_senses.Detalle.Detalle_Cita;
import com.example.clinica_senses.Objetos.Cita;
import com.example.clinica_senses.R;
import com.example.clinica_senses.ViewHolder.ViewHolder_Cita;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Listar_Citas extends AppCompatActivity {


    RecyclerView recyclerViewCitas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference BASE_DE_DATOS;

    LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Cita, ViewHolder_Cita>firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Cita>options;

    Dialog dialog;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_citas);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Mis citas");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        //Inicializar
        recyclerViewCitas = findViewById(R.id.recyclerviewCitas);
        recyclerViewCitas.setHasFixedSize(false);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        BASE_DE_DATOS = firebaseDatabase.getReference("Citas_Registradas");
        dialog = new Dialog(Listar_Citas.this);
        ListarCitasUsuarios();

    }

    //Método de listas
    private void ListarCitasUsuarios() {
        //consulta para mostrar solo las citas a los dueños del uid de la cita
       Query query =BASE_DE_DATOS.orderByChild("uid").equalTo(user.getUid());
       options = new FirebaseRecyclerOptions.Builder<Cita>().setQuery(query, Cita.class).build();



       firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cita, ViewHolder_Cita>(options) {
           @Override
           protected void onBindViewHolder(@NonNull ViewHolder_Cita viewHolder_cita, int position, @NonNull Cita cita) {
                viewHolder_cita.setearDatos(
                        getApplicationContext(),
                        cita.getId_cita(),
                        cita.getCorreo_usuario(),
                        cita.getFecha_cita(),
                        cita.getUltima_visita(),
                        cita.getMotivo(),
                        cita.getComo_se_entero(),
                        cita.getFecha_cita(),
                        cita.getEstado()
                );

           }


           @NonNull
           @Override
           public ViewHolder_Cita onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false );
               ViewHolder_Cita viewHolder_cita = new ViewHolder_Cita(view);
               viewHolder_cita.setOnClickListener(new ViewHolder_Cita.ClickListener() {
                   @Override
                   public void onItemClick(View view, int position) {
                       //Toast.makeText(Listar_Citas.this, "on item click", Toast.LENGTH_SHORT).show();
                       //startActivity(new Intent(Listar_Citas.this, Detalle_Cita.class));

                        //Obtener los datos de la nota seleccionada
                       String id_cita = getItem(position).getId_cita();
                       String correo_usuario = getItem(position).getCorreo_usuario();
                       String fecha_registro = getItem(position).getFecha_hora_registro();
                       String ultima_visita = getItem(position).getUltima_visita();
                       String motivo = getItem(position).getMotivo();
                       String como_se_entero = getItem(position).getComo_se_entero();
                       String fecha_cita = getItem(position).getFecha_cita();
                       String estado = getItem(position).getEstado();
                       String uid = getItem(position).getUid();

                    
                    //Enviar datos  a la siguiente actividad
                               Intent intent = new Intent(Listar_Citas.this, Detalle_Cita.class);
                               intent.putExtra("id_cita", id_cita);
                               intent.putExtra("correo_usuario", correo_usuario);
                               intent.putExtra("fecha_registro",fecha_registro);
                               intent.putExtra("ultima_visita", ultima_visita);
                               intent.putExtra("motivo", motivo);
                               intent.putExtra("como_se_entero", como_se_entero);
                               intent.putExtra("fecha_cita",fecha_cita);
                               intent.putExtra("estado", estado);
                               intent.putExtra("uid", uid);
                               startActivity(intent);
                               dialog.dismiss();
                       
                   }

                   @Override
                   public void onItemLongClick(View view, int position) {
                      
                       //Obtener los datos de la nota seleccionada
                       String id_cita = getItem(position).getId_cita();
                       String correo_usuario = getItem(position).getCorreo_usuario();
                       String fecha_registro = getItem(position).getFecha_hora_registro();
                       String ultima_visita = getItem(position).getUltima_visita();
                       String motivo = getItem(position).getMotivo();
                       String como_se_entero = getItem(position).getComo_se_entero();
                       String fecha_cita = getItem(position).getFecha_cita();
                       String estado = getItem(position).getEstado();
                       String uid = getItem(position).getUid();


                       // Declarar las vistas
                       Button CD_Eliminar, CD_Acutalizar;

                       //Realizar conexión con el diseño
                       dialog.setContentView(R.layout.dialogo_opciones);

                       //Inicializamos las vitas
                       CD_Eliminar = dialog.findViewById(R.id.CD_Eliminar);
                       CD_Acutalizar = dialog.findViewById(R.id.CD_Actuallizar);

                       CD_Eliminar.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               EliminarNota(id_cita);
                               dialog.dismiss();
                           }
                       });
                       CD_Acutalizar.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               //Toast.makeText(Listar_Citas.this, "Actualizar Nota", Toast.LENGTH_SHORT).show();
                               //startActivity(new Intent(Listar_Citas.this, Actualizar_Cita.class));
                               Intent intent = new Intent(Listar_Citas.this, Actualizar_Cita.class);
                               intent.putExtra("id_cita", id_cita);
                               intent.putExtra("correo_usuario", correo_usuario);
                               intent.putExtra("fecha_registro",fecha_registro);
                               intent.putExtra("ultima_visita", ultima_visita);
                               intent.putExtra("motivo", motivo);
                               intent.putExtra("como_se_entero", como_se_entero);
                               intent.putExtra("fecha_cita",fecha_cita);
                               intent.putExtra("estado", estado);
                               intent.putExtra("uid", uid);
                               startActivity(intent);
                               dialog.dismiss();
                           }
                       });

                       dialog.show();
                   }
               });
               return viewHolder_cita;
           }
       };

       linearLayoutManager = new LinearLayoutManager(Listar_Citas.this, LinearLayoutManager.VERTICAL, false);
       linearLayoutManager.setReverseLayout(true);
       linearLayoutManager.setStackFromEnd(true);


       recyclerViewCitas.setLayoutManager(linearLayoutManager);
       recyclerViewCitas.setAdapter(firebaseRecyclerAdapter);
     }

    private void EliminarNota(String id_cita) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Listar_Citas.this);
        builder.setTitle("Eliminar Nota");
        builder.setMessage("¿Desea eliminar la cita?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Eliminar nota en BD
                Query query = BASE_DE_DATOS.orderByChild("id_cita").equalTo(id_cita);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(Listar_Citas.this, "La cita ha sido eliminada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Listar_Citas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Listar_Citas.this, "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter!=null){
            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}