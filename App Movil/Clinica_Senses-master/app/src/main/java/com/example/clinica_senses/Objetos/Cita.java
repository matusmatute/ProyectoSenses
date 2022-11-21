package com.example.clinica_senses.Objetos;

public class Cita {
    String id_cita, correo_usuario, fecha_hora_registro, ultima_visita, motivo, como_se_entero, fecha_cita, estado, uid;

    public Cita() {
    }

    public Cita(String id_cita, String correo_usuario, String fecha_hora_registro, String ultima_visita, String motivo, String como_se_entero, String fecha_cita, String estado, String uid) {
        this.id_cita = id_cita;
        this.correo_usuario = correo_usuario;
        this.fecha_hora_registro = fecha_hora_registro;
        this.ultima_visita = ultima_visita;
        this.motivo = motivo;
        this.como_se_entero = como_se_entero;
        this.fecha_cita = fecha_cita;
        this.estado = estado;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setFecha_hora_registro(String fecha_hora_registro) {
        this.fecha_hora_registro = fecha_hora_registro;
    }

    public String getId_cita() {
        return id_cita;
    }

    public void setId_cita(String id_cita) {
        this.id_cita = id_cita;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getFecha_hora_registro() {
        return fecha_hora_registro;
    }

    public void fecha_hora_registro(String fecha_hora_registro) {
        this.fecha_hora_registro = fecha_hora_registro;
    }

    public String getUltima_visita() {
        return ultima_visita;
    }

    public void setUltima_visita(String ultima_visita) {
        this.ultima_visita = ultima_visita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getComo_se_entero() {
        return como_se_entero;
    }

    public void setComo_se_entero(String como_se_entero) {
        this.como_se_entero = como_se_entero;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
