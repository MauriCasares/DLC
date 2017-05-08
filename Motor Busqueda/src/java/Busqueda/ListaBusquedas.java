
package Busqueda;
import java.util.ArrayList;

public class ListaBusquedas
{
    private ArrayList<Busqueda> listaBusquedas;
    private static ListaBusquedas instancia;
    
    public ListaBusquedas()
    {
        listaBusquedas = new ArrayList<>();
    }
    
    public ArrayList<Busqueda> getLista()
    {
        if(listaBusquedas != null)
            return listaBusquedas;
        else
            return null;
    }
    
    public static ListaBusquedas getInstancia()
    {
        if (instancia == null) instancia = new ListaBusquedas();
        return instancia;
    }
    
}
