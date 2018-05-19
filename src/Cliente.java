import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public class Cliente {
    private int id = 0;
    private ArrayList<Producto> checkList = new ArrayList<>();
    private ArrayList<Integer> cantidadProducto = new ArrayList<>();
    private int pasilloActual = 0;
    private int numeroFactura;
    private boolean direccion = false;

    public Cliente(int id, ArrayList<Producto> checkList, int pasilloActual, int numeroFactura) {
        this.id = id;
        this.checkList = checkList;
        this.pasilloActual = pasilloActual;
        this.numeroFactura = numeroFactura;
        Random rand = new Random();
        this.direccion =  rand.nextBoolean();
    }

    public ArrayList<Integer> getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(ArrayList<Integer> cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Producto> getCheckList() {
        return checkList;
    }

    public void setCheckList(ArrayList<Producto> checkList) {
        this.checkList = checkList;
    }

    public int getPasilloActual() {
        return pasilloActual;
    }

    public void setPasilloActual(int pasilloActual) {
        this.pasilloActual = pasilloActual;
    }

    public boolean isDireccion() {
        return direccion;
    }

    public void setDireccion(boolean direccion) {
        this.direccion = direccion;
    }

    public void asignarsePasillo(){
        if(checkList.size() > 0){
            pasilloActual =  checkList.get(0).getPasillo();
        }
    }


}

