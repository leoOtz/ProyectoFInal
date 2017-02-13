package ec.edu.epn.proyecto2.Objetos;

import java.io.Serializable;

/**
 * Created by Juan on 12/02/2017.
 */

public class Recaudo implements Serializable
{

    private String recaudo;
    private String fecha;



    public String getRecaudo() {
        return recaudo;
    }
    public void setRecaudo(String recaudo) {
        this.recaudo = recaudo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Recaudo() {
        super();
    }
}
