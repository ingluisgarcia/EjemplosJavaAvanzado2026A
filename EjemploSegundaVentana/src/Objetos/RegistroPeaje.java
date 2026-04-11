/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author cymaniatico
 */
public class RegistroPeaje {
    
    private String placa;
    private String tipoVehiculo;
    private int valorPagado;
    private String user;
    private String fechaRegistro;

    public RegistroPeaje(String placa, String tipoVehiculo, int valorPagado, String user) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.valorPagado = valorPagado;
        this.user = user;
        fechaRegistro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(Calendar.getInstance().getTime());
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getValorPagado() {
        return valorPagado;
    }

    public String getUser() {
        return user;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    
    
    
}
