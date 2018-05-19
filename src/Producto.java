import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public class Producto {
    private int id;
    private String nombre;
    private int pasillo;
    private int categoria;
    private double precio;


    public Producto(int id, String nombre, int pasillo, int categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.pasillo = pasillo;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPasillo() {
        return pasillo;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
