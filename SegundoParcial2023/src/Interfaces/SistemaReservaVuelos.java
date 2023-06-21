package Interfaces;

import Entities.Reserva;
import Entities.Vuelo;
import Exceptions.InformacionInvalida;
import Exceptions.PasajeroNoExiste;
import Exceptions.ReservaRechazada;
import uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

public interface SistemaReservaVuelos {

    void registrarVuelo(String numeroVuelo, String origen, String destino, LocalDate fechaSalida, int asientosDisponibles,
                        int duracion) throws InformacionInvalida;

    MyList<Vuelo> buscarVuelos(String origen, String destino, LocalDate fecha);

    void realizarReserva(String numeroVuelo, String cedula, String nombrePasajero, int asiento) throws InformacionInvalida, ReservaRechazada;

    MyList<Reserva> obtenerReservasPasajero(String cedula, LocalDate fecha1, LocalDate fecha2) throws PasajeroNoExiste;

    MyList<Vuelo> mostrarVuelosOrdenadosPorDuracion();


}
