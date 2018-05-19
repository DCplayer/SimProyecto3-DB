/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public class Factura {
    private int id;
    private int clienteId;
    private int compraId;

    public Factura(int id, int clienteId, int compraId) {
        this.id = id;
        this.clienteId = clienteId;
        this.compraId = compraId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }
}
