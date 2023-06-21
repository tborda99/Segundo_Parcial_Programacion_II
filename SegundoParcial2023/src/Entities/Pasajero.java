package Entities;

import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.util.Objects;

public class Pasajero {

    private String nombre;
    private String cedula;
    private MyList<Reserva> reservas = new MyLinkedListImpl<>();

    public Pasajero() {
    }

    public Pasajero(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public MyList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(MyList<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return cedula.equals(pasajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
