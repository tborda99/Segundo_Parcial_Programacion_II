import Entities.Reserva;
import Entities.Vuelo;
import Exceptions.InformacionInvalida;
import Exceptions.PasajeroNoExiste;
import Exceptions.ReservaRechazada;
import org.junit.Assert;

import org.testng.annotations.Test;
import uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

import static org.junit.Assert.fail;

public class SistemaReservasVuelosImplTest {
    SistemaReservasVuelosImpl sistemaReservasVuelos = new SistemaReservasVuelosImpl();
    @Test
    public void registrarVuelo_ValidInput_VueloRegistered() throws InformacionInvalida {

        String numeroVuelo = "FL001";
        String origen = "CityA";
        String destino = "CityB";
        LocalDate fechaSalida = LocalDate.now().plusDays(1);
        int asientosDisponibles = 100;
        int duracion = 120;

        sistemaReservasVuelos.registrarVuelo(numeroVuelo, origen, destino, fechaSalida, asientosDisponibles, duracion);

        // Assert that the vuelo is registered successfully
        // You can add additional assertions as per your requirements
        Assert.assertTrue(sistemaReservasVuelos.buscarVuelo(numeroVuelo));
    }
    @Test
    public void buscarVuelos_ValidInput_VuelosFound() {
        String origen_b = "CityA";
        String destino_b = "CityB";
        LocalDate fecha = LocalDate.now().plusDays(1);

        String numeroVuelo = "FL001";
        String origen = "CityA";
        String destino = "CityB";
        LocalDate fechaSalida = LocalDate.now().plusDays(1);
        int asientosDisponibles = 100;
        int duracion = 120;
        try {
            sistemaReservasVuelos.registrarVuelo(numeroVuelo, origen, destino, fechaSalida, asientosDisponibles, duracion);
        } catch (InformacionInvalida e) {
            fail();
        }

        MyList<Vuelo> vuelos = sistemaReservasVuelos.buscarVuelos(origen_b, destino_b, fecha);

        // Assert that the vuelos list is not null or empty
        Assert.assertNotNull(vuelos);
        Assert.assertTrue(vuelos.size() != 0);
    }

    @Test
    public void realizarReserva_ValidInput_ReservaCreated() throws InformacionInvalida, ReservaRechazada {
        String numeroVuelo = "FL001";
        String origen = "CityA";
        String destino = "CityB";
        LocalDate fechaSalida = LocalDate.now().plusDays(1);
        int asientosDisponibles = 100;
        int duracion = 120;
        try {
            sistemaReservasVuelos.registrarVuelo(numeroVuelo, origen, destino, fechaSalida, asientosDisponibles, duracion);
        } catch (InformacionInvalida e) {
            fail();
        }


        String cedula = "123456789";
        String nombrePasajero = "John Doe";
        int asiento = 1;

        sistemaReservasVuelos.realizarReserva(numeroVuelo, cedula, nombrePasajero, asiento);

        // Assert that the reservation is created successfully
        // You can add additional assertions as per your requirements
        MyList<Reserva> reservas = null;
        try {
            reservas = sistemaReservasVuelos.obtenerReservasPasajero(cedula, LocalDate.MIN, LocalDate.MAX);
        } catch (PasajeroNoExiste e) {
            fail();
        }
        Assert.assertNotNull(reservas);
        Assert.assertTrue(reservas.size()!=0);
    }

    @Test
    public void obtenerReservasPasajero_ValidInput_ReservasFound() throws PasajeroNoExiste {
        String numeroVuelo = "FL001";
        String origen = "CityA";
        String destino = "CityB";
        LocalDate fechaSalida = LocalDate.now().plusDays(1);
        int asientosDisponibles = 100;
        int duracion = 120;
        try {
            sistemaReservasVuelos.registrarVuelo(numeroVuelo, origen, destino, fechaSalida, asientosDisponibles, duracion);
        } catch (InformacionInvalida e) {
            fail();
        }


        String cedula = "123456789";
        String nombrePasajero = "John Doe";
        int asiento = 1;

        try {
            sistemaReservasVuelos.realizarReserva(numeroVuelo, cedula, nombrePasajero, asiento);
        } catch (InformacionInvalida e) {
            fail();
        } catch (ReservaRechazada e) {
            fail();
        }


        LocalDate fecha1 = LocalDate.MIN;
        LocalDate fecha2 = LocalDate.MAX;

        MyList<Reserva> reservas = sistemaReservasVuelos.obtenerReservasPasajero(cedula, fecha1, fecha2);

        // Assert that the reservas list is not null
        Assert.assertNotNull(reservas);
    }

    @Test
    public void mostrarVuelosOrdenadosPorDuracion_ValidInput_VuelosOrderedByDuration() {
        String numeroVuelo = "FL001";
        String origen = "CityA";
        String destino = "CityB";
        LocalDate fechaSalida = LocalDate.now().plusDays(1);
        int asientosDisponibles = 100;
        int duracion = 120;

        try {
            sistemaReservasVuelos.registrarVuelo(numeroVuelo, origen, destino, fechaSalida, asientosDisponibles, duracion);
        } catch (InformacionInvalida e) {
            fail();
        }
        MyList<Vuelo> vuelosOrdenados = sistemaReservasVuelos.mostrarVuelosOrdenadosPorDuracion();

        // Assert that the vuelosOrdenados list is not null or empty
        Assert.assertNotNull(vuelosOrdenados);
        Assert.assertTrue(vuelosOrdenados.size()!=0);

        // You can add additional assertions to verify the order of vuelos
    }

}