/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package boleteria;

/**
 *
 * @author cymaniatico
 */
public class Boleta {

   private int id;
   private String descripcion;
   private int precio;

    public Boleta(int id, String descripcion, int precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }
   
   
    
}
