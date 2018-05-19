/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public class Compra {
    private int id;
    private int prodcutoId;
    private int clienteId;
    private int cantidadProducto;

    public Compra(int id, int prodcutoId, int clienteId, int cantidadProducto) {
        this.id = id;
        this.prodcutoId = prodcutoId;
        this.clienteId = clienteId;
        this.cantidadProducto = cantidadProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdcutoId() {
        return prodcutoId;
    }

    public void setProdcutoId(int prodcutoId) {
        this.prodcutoId = prodcutoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
