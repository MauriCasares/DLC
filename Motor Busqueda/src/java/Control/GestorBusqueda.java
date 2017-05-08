package Control;

import Colecciones.ArregloDocumentos;
import Colecciones.TablaHash;
import Entidad.Documento;
import java.util.ArrayList;
import java.util.Arrays;

public class GestorBusqueda {
    private TablaHash tablaHash;
    private ArregloDocumentos arregloDocumentos;
    private ArrayList<Documento> ranking;
    
    public GestorBusqueda() {
        this.tablaHash = TablaHash.getInstance();
        this.arregloDocumentos = ArregloDocumentos.getInstance();
        this.ranking = new ArrayList();
    }
    
    public ArrayList<Documento> obtenerRanking()
    {
        return  this.ranking;
    }
    
    public void generarRanking(String busqueda){
        Documento [] arreglo = arregloDocumentos.rankear(tablaHash, busqueda);
        ranking.addAll(Arrays.asList(arreglo));
    }
    
}
