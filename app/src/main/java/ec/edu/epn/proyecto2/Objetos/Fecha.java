package ec.edu.epn.proyecto2.Objetos;

import java.io.Serializable;

/**
 * Created by Juan on 12/12/2016.
 */

public class Fecha implements Serializable
{
    private String HoraSalida;
    private String HoraLlegada;

    public Fecha(String horaSalida, String horaLlegada) {
        HoraSalida = horaSalida;
        HoraLlegada = horaLlegada;
    }

    public String getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(String horaSalida) {
        HoraSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return HoraLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        HoraLlegada = horaLlegada;
    }

    @Override
    public String toString() {
        return "Fecha{" +
                "HoraSalida='" + HoraSalida + '\'' +
                ", HoraLlegada='" + HoraLlegada + '\'' +
                '}';
    }
}
