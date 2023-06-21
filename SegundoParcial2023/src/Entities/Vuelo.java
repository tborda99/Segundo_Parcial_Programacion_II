package Entities;

import uy.edu.um.adt.hash.MyHash;
import uy.edu.um.adt.hash.MyHashImpl;

import java.time.LocalDate;
import java.util.Objects;

public class Vuelo implements Comparable<Vuelo> {

    private String numeroVuelo;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private int duracion;
    private int asientoDisponibles;
    private MyHash<Integer,Reserva> reservas = new MyHashImpl<>();

    public Vuelo() {
    }

    public Vuelo(String numeroVuelo, String origen, String destino, LocalDate fechaSalida, int duracion, int asientoDisponibles) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.duracion = duracion;
        this.asientoDisponibles = asientoDisponibles;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAsientoDisponibles() {
        return asientoDisponibles;
    }

    public void setAsientoDisponibles(int asientoDisponibles) {
        this.asientoDisponibles = asientoDisponibles;
    }

    public MyHash<Integer, Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(MyHash<Integer, Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return numeroVuelo.equals(vuelo.numeroVuelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroVuelo);
    }

    //Devuelve True si el vuelo esta completo
    public boolean VueloCompleto(){                //ASUMO.
        int cantidad_vendida = reservas.size(); //PREGUNTE AL PROFESSOR SOBRE LA IMPL Y DIJO QUE DEVOLVIA EL ESPACIO UTILIZADO HASTA EL MOMENTO
        return(cantidad_vendida >= this.asientoDisponibles);

    }

    //Devuelve True si hay asiento disponible
    public boolean AsientoDisponible(int asiento){
        return(!reservas.contains(asiento));
    }

    @Override
    public int compareTo(Vuelo o) {
        return 0;
    }
}
