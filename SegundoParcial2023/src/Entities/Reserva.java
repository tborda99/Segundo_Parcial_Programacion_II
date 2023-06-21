package Entities;

import java.util.Objects;

public class Reserva {

    private int asiento;
    private String numeroVuelo;

    public Reserva() {
    }

    public Reserva(int asiento, String numeroVuelo) {
        this.asiento = asiento;
        this.numeroVuelo = numeroVuelo;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return asiento == reserva.asiento && numeroVuelo.equals(reserva.numeroVuelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asiento, numeroVuelo);
    }
}
