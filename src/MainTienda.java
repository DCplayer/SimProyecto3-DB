import java.util.ArrayList;

/**
 * Created by Diego Castaneda on 18/05/2018.
 */
public class MainTienda {
    public static void main(String args[]){
        // En estas dos listas se deben de meter los clientes de postgress

        ArrayList<Cliente> clientesTotales = new ArrayList<>();
        ArrayList<Producto> productosTotales = new ArrayList<>();

        //para luego probarlos aqui
        Simulador simi = new Simulador(clientesTotales, productosTotales);


    }
}
