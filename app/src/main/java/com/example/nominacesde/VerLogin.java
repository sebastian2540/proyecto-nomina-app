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

    public int validacioncorreo(){
        if((getUsuario().equals("administradornomina@cesde.com")&& getContrasena().equals("123456"))){
            return 1;
        } else {
            return 0;
        }
    }
}
