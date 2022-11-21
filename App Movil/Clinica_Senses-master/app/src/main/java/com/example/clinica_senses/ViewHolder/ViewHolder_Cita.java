package com.example.clinica_senses.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinica_senses.R;

public class ViewHolder_Cita extends RecyclerView.ViewHolder {

    View mView;

    private ViewHolder_Cita.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder_Cita.ClickListener clickListener){
        mClickListener =  clickListener;
    }

    public ViewHolder_Cita(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getBindingAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getBindingAdapterPosition());
                return false;
            }
        });


    }

    public void setearDatos(Context context, String id_cita, String correo_usuario, String fecha_hora_registro, String ultima_visita,
                            String motivo, String como_se_entero, String fecha_cita, String estado){

       ImageView Tarea_Finalizada_Item, Tarea_No_Finalizada_Item;
       
        TextView Id_cita_Item, Correo_usuario_Item, Fecha_hora_registro_Item, Ultima_Visita_Item,
                 Motivo_Item, Como_Se_Entero_Item, Fecha_Item, Estado_Item;

        Id_cita_Item = mView.findViewById(R.id.Id_cita_Item);
        Correo_usuario_Item = mView.findViewById(R.id.Correo_usuario_Item);
        Fecha_hora_registro_Item = mView.findViewById(R.id.Fecha_hora_registro_Item);
        Ultima_Visita_Item = mView.findViewById(R.id.Ultima_Visita_Item);
        Motivo_Item = mView.findViewById(R.id.Motivo_Item);
        Como_Se_Entero_Item = mView.findViewById(R.id.Como_Se_Entero_Item);
        Fecha_Item = mView.findViewById(R.id.Fecha_Item);
        Estado_Item = mView.findViewById(R.id.Estado_Item);

        Tarea_Finalizada_Item = mView.findViewById(R.id.Tarea_Finalizada_Item);
        Tarea_No_Finalizada_Item = mView.findViewById(R.id.Tarea_No_Finalizada_Item);

        //Setear la información
        Id_cita_Item.setText(id_cita);
        Correo_usuario_Item.setText(correo_usuario);
        Fecha_hora_registro_Item.setText(fecha_hora_registro);
        Ultima_Visita_Item.setText(ultima_visita);
        Motivo_Item.setText(motivo);
        Como_Se_Entero_Item.setText(como_se_entero);
        Fecha_Item.setText(fecha_cita);
        Estado_Item.setText(estado);
        

        //GESTIONAR EL COLOR DEL ESTADO
        if (estado.equals("Finalizado")){
            Tarea_Finalizada_Item.setVisibility(View.VISIBLE);

        }else{
            Tarea_No_Finalizada_Item.setVisibility(View.VISIBLE);
        }

    }
}
