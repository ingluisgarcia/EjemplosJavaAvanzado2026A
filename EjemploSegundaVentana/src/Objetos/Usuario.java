/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Objetos;

/**
 *
 * @author cymaniatico
 */
public class Usuario {

    private int idUser;
    private String user;
    private String pass;
    private int idProfile;

    public Usuario(int idUser, String user, String pass, int idProfile) {
        this.idUser = idUser;
        this.user = user;
        this.pass = pass;
        this.idProfile = idProfile;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getIdProfile() {
        return idProfile;
    }
    
    
    
}
