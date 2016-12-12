package ec.edu.epn.proyecto2.Objetos;

import java.io.Serializable;

/**
 * Created by Juan on 03/12/2016.
 */

public class Bus  implements Serializable
{
    private String placa;
    private String permiso;
    private String nombre;
    private String nombreImagen;

    public Bus(String placa, String permiso, String nombre, String nombreImagen) {
        this.placa = placa;
        this.permiso = permiso;
        this.nombre = nombre;
        this.nombreImagen = "bus";
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    @Override
    public String toString() {
        return "placa=" + placa +
                ", permiso=" + permiso +
                ", nombre='" + nombre ;
    }

    public Bus()
    {

    }
}
