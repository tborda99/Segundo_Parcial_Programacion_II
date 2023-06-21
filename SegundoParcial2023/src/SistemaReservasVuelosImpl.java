import Entities.HeapSortImpl;
import Entities.Pasajero;
import Entities.Reserva;
import Entities.Vuelo;
import Exceptions.InformacionInvalida;
import Exceptions.PasajeroNoExiste;
import Exceptions.ReservaRechazada;
import Interfaces.SistemaReservaVuelos;
import uy.edu.um.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.adt.hash.MyHash;
import uy.edu.um.adt.hash.MyHashImpl;
import uy.edu.um.adt.heap.MyHeap;
import uy.edu.um.adt.heap.MyHeapImpl;
import uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;


public class SistemaReservasVuelosImpl implements SistemaReservaVuelos {

    MySearchBinaryTree<String, Pasajero> pasajeros = new MySearchBinaryTreeImpl<>();
    MyHash<String,Vuelo> vuelos = new MyHashImpl<>();


    @Override
    public void registrarVuelo(String numeroVuelo, String origen, String destino, LocalDate fechaSalida, int asientosDisponibles, int duracion) throws InformacionInvalida {
        if(numeroVuelo == null || origen == null || destino == null || fechaSalida == null || fechaSalida.isBefore(LocalDate.now()) || asientosDisponibles <=0 || duracion <= 0){
            throw new InformacionInvalida();
        }else{
            if(buscarVuelo(numeroVuelo)){
                //Ya existe
                throw new InformacionInvalida();
            }else{
                //Creo el vuelo
                Vuelo nuevo_vuelo = new Vuelo(numeroVuelo,origen,destino,fechaSalida,duracion,asientosDisponibles);
                vuelos.put(numeroVuelo,nuevo_vuelo);
            }

        }

    }


    @Override
    public MyList<Vuelo> buscarVuelos(String origen, String destino, LocalDate fecha) {
        MyList<Vuelo> vuelos_list = vuelos.values();

        for (int i = 0; i < vuelos_list.size(); i++) {
            if(!vuelos_list.get(i).getDestino().equals(destino) && !vuelos_list.get(i).getOrigen().equals(origen) && !vuelos_list.get(i).getFechaSalida().isEqual(fecha)){
                //El vuelo no cumple ninguna condicion
                vuelos_list.remove(vuelos_list.get(i)); //Si fuera mi implementacion de lista cambiaria esto porque es re ineficiente.
            }

        }
        return vuelos_list;
    }

    @Override
    public void realizarReserva(String numeroVuelo, String cedula, String nombrePasajero, int asiento) throws InformacionInvalida, ReservaRechazada {
        if(!buscarVuelo(numeroVuelo)){
            throw new InformacionInvalida();
        }else{
            Vuelo vuelo_Reserva = vuelos.get(numeroVuelo);
            if(vuelo_Reserva.VueloCompleto() || !vuelo_Reserva.AsientoDisponible(asiento)){
                throw new ReservaRechazada();
            }else{
                //Hay asiento disponible y Existe el vuelo
                //Delego Agregar Pasajero
                Pasajero pasajero_Reserva = buscarOAgregarPasajero(cedula, nombrePasajero);
                Reserva reservaNueva = new Reserva(asiento,numeroVuelo);

                vuelos.put(numeroVuelo, vuelo_Reserva);
                pasajero_Reserva.getReservas().add(reservaNueva);

            }
        }


    }

    @Override
    public MyList<Reserva> obtenerReservasPasajero(String cedula, LocalDate fecha1, LocalDate fecha2) throws PasajeroNoExiste {
        if(!(buscarPasajero(cedula))){
            throw new PasajeroNoExiste();
        }else{
            Pasajero pasajero_consulta = pasajeros.find(cedula);
            MyList<Reserva> reservas= pasajero_consulta.getReservas();

            for (int i = 0; i < reservas.size(); i++) {
                String vuelo = reservas.get(i).getNumeroVuelo();
                if(vuelos.get(vuelo).getFechaSalida().isBefore(fecha1) || vuelos.get(vuelo).getFechaSalida().isAfter(fecha2)){
                    reservas.remove(reservas.get(i)); //Nuevamente, si fuera mi impl la cambiaria o agregaria otra funcion mas directa.
                }

            }
            return reservas;
        }
    }

    @Override
    public MyList<Vuelo> mostrarVuelosOrdenadosPorDuracion() {
        MyList<Vuelo> listaVuelos = vuelos.values();

        MyHeap<Vuelo> heap = new MyHeapImpl<>(false);

        for (int i = 0; i < listaVuelos.size(); i++) {
            heap.insert(listaVuelos.get(i));

        }

        // Crear un max heap using MyHeapImpl
        for (int i = listaVuelos.size() - 1; i >= 0; i--) {
            listaVuelos.add(heap.delete()); // Delete el maximum del heap and guardarlo en el array
        }
        return listaVuelos;

    }

    public Boolean buscarVuelo(String numeroVuelo){
        return vuelos.contains(numeroVuelo);
    }

    private Pasajero buscarOAgregarPasajero(String cedula, String nombre){
        if(pasajeros.contains(cedula)){
            return(pasajeros.find(cedula));
        }else{
            Pasajero pasajeroNuevo = new Pasajero(cedula, nombre);
            pasajeros.add(cedula,pasajeroNuevo);
            return pasajeroNuevo;
        }
    }

    //Devuelve True si Existe
    private Boolean buscarPasajero(String cedula){
        return(pasajeros.contains(cedula));
    }

}
