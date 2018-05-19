import com.sun.deploy.util.SessionState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public  class Simulador {
    private  ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Cliente> clientesActivos = new ArrayList<>();
    private ArrayList<Cliente> clienteTiendero = new ArrayList<>();
    private ArrayList<Integer> calor = new ArrayList<>();
    private int numeracionCompra = 0;

    public Simulador(ArrayList<Cliente> clientes, ArrayList<Producto> productos) {
        this.clientes = clientes;
        this.productos = productos;
    }

    //El porcentaje lo unico que indica es la cota superior de la cantidad de clientes que pueden entrar a la tienda
    // es decir, si tenes 100 clientes y pones 0.20, entras la posibilidad que maximo entren 20 clientes a la tienda y
    // minimo 0. Ahora, como ya tenes 80 clientes, si igual pones 20 y si no ha regresado ningun cliente, entonces
    // tienes la posibilidad que maximo entren 16 clientes a la tienda y minimo 0.

    private void  productosParaNuevosClientes(float porcentaje){
        Random rand = new Random();
        ArrayList<Integer> numerosSeleccionados = new ArrayList<>();

        //Se define cuantos clientes del total que existe sin visitar la tienda entraran a la tienda
        int numeroClientes = nuevosClientes(clientes.size(), porcentaje);
        for(int i = 0; i < numeroClientes; i++){
            int number = rand.nextInt(clientes.size());
            if(!numerosSeleccionados.contains(number)){
                numerosSeleccionados.add(number);
            }
        }
        for(Integer i: numerosSeleccionados){
            clientesActivos.add(clientes.get(i));
        }
        //Se da a cada cliente la cantidad de productos que desea comprar
        //Crea, para cada clienteActivo, un checklist con una cantidad random de items y una cantidad X de elementos
        // por producto que debe de comprar la persona
        for(Cliente c: clientesActivos){

            //Cantidad de prodcutos diferentes que comprara el cliente
            int numeroProducto = rand.nextInt((int)Math.floor(productos.size() /2 ));
            for(int i = 0 ; i < numeroProducto; i++){

                //indice del producto que tomara el usuario
                int eleccion = rand.nextInt(productos.size()-1);
                if(!(c.getCheckList().contains(productos.get(eleccion)))){

                    //Cantidad de elementos por producto que tomara la persona
                    int cantidad = rand.nextInt(10) +1 ;
                    Producto producto = productos.get(eleccion);
                    c.getCheckList().add(producto);
                    c.getCantidadProducto().add(cantidad);
                }
            }
        }
        //Posicionar cada cliente en el pasillo donde exista al menos uno de sus productos.
        for (Cliente c: clientesActivos){
            c.asignarsePasillo();
        }
    }

    private void simulacionClientesActivos(){
        //Metodo para pasar a todos los nuevos clientes junto con el conjunto de personas que ya estaban comprando en
        //la tienda.
        ArrayList<Cliente> deVueltaACasa = new ArrayList<>();

        hardCopy(clientesActivos, clienteTiendero);
        for (Cliente c: clienteTiendero){
            //Si ya compro todos los items que necesitaba
            if(c.getCheckList().size() <= 0){
                c.setNumeroFactura(0);
                c.setPasilloActual(0);
                c.getCantidadProducto().clear();
                c.getCheckList().clear();
                deVueltaACasa.add(c);
            }
            //Si no, entonces tiene items que necesita comprar
            else{
                //Aumentar en 1 valor el calor del pasillo donde se encuentra el cliente.
                int numeroPasillo = c.getPasilloActual();
                int numerazo =  calor.get(numeroPasillo -1) +1 ;
                calor.set(numeroPasillo-1, numerazo);

                //Verificar si se puede tronar algun producto que tenga
                ArrayList<Producto> productosTomados = new ArrayList<>();
                for(Producto p: c.getCheckList()){
                    if(p.getPasillo() == c.getPasilloActual()){
                        productosTomados.add(p);
                    }
                }
                //REVISAR PARA DONDE SE VA A MOVER LA PERSONA DESPUES DE ESTAR EN ESTE PASILLO
                if(c.isDireccion() && c.getPasilloActual() >= 9){
                    //Si es ascendente pero ya llego hasta un lado del local
                    c.setDireccion(false);
                    c.setPasilloActual(8);
                }
                else if(c.isDireccion() && c.getPasilloActual() < 9){
                    //Si es ascendente y no ha llegado hasta un lado del local
                    int numero = c.getPasilloActual() + 1;
                    c.setPasilloActual(numero);
                }
                else if(!(c.isDireccion()) && c.getPasilloActual() <= 0 ){
                    //Si es descendente pero ya llego hasta un lado del local
                    c.setDireccion(true);
                    c.setPasilloActual(1);
                }
                else{
                    //Si es descendente y no ha llegado hasta un lado del local
                    int numero = c.getPasilloActual() -1 ;
                    c.setPasilloActual(numero);
                }

                //CREAR LA LISTA DE COMPRAS PARA METER A LA FACTURA DEL MEN
                ArrayList<Compra> comprasCliente = new ArrayList<>();
                for(Producto p: productosTomados){
                    int indiceProducto = c.getCheckList().indexOf(p);
                    Compra compra = new Compra(numeracionCompra, p.getId(),c.getId(),
                            c.getCantidadProducto().get(indiceProducto));
                    comprasCliente.add(compra);
                    numeracionCompra++;
                    //POR CADA PRODUCTO QUE AGARRE, AQUI SE HACE LA COMPRA
                    c.getCheckList().remove(p);
                    //------------IMPORTANTE----------------------------------------------------------------------------
                    //-----------AQUI SE CREARON COMPRAS, LAS CUALES SE DEBEN DE IR METIENDO EN LA DB-------------------
                }
                //CUANDO TERMINA DE TENER UN MONTON DE COMPRAS, SE REALIZA LA FACTURA CON CADA COMPRA.
                for(Compra buy: comprasCliente){
                    Factura factura = new Factura(c.getNumeroFactura(), c.getId(), buy.getId());
                    //-----------------------------------IMPORTANTE-----------------------------------------------------
                    //---------------------------------AQUI SE CREO LA FACTURA QUE SE DEBE DE INGRESAR EN LA DB---------
                }

            }
        }
        for(Cliente c: deVueltaACasa){
            clientes.add(c);
            clienteTiendero.remove(c);
        }
    }

    private void hardCopy(ArrayList<Cliente> original, ArrayList<Cliente> pegado){
        for (Cliente c: original){
            pegado.add(c);
        }
        original.clear();
    }

    private  int nuevosClientes(int total, float porcentaje){
        Random rand = new Random();
        double numero = rand.nextGaussian();
        while (numero > 1.0 || numero < 0.0){
            numero = rand.nextGaussian();
        }
        int activo = (int )Math.floor(numero* total * porcentaje);
        total = total  - activo;
        return activo;
    }

    public ArrayList<Integer> getCalor() {
        return calor;
    }
}
