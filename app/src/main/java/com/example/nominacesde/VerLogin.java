package com.example.nominacesde;

public class VerLogin {

    private String usuario;
    private String contrasena;

    public VerLogin(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int validacionUsuario(){
        if((getUsuario().equals("1010028893")&& getContrasena().equals("1010028893"))){
            return 1;
        } else {
            return 0;
        }

    }
}
