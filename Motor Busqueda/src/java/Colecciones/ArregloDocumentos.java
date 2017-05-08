package Colecciones;

import Entidad.Documento;
import Entidad.Palabra;
import Entidad.Posteo;
import java.io.Serializable;
import java.util.ArrayList;

public class ArregloDocumentos implements Serializable {

    private static ArregloDocumentos instancia;
    private Documento[] documentos;
    private int documentosInsertados;

    public static ArregloDocumentos getInstance() {
        if (instancia == null) {
            instancia = Persistencia.PersistenciaArregloDocumentos.read();

            if (instancia == null) {
                instancia = new ArregloDocumentos();
            }
        }
        return instancia;
    }

    private ArregloDocumentos() {
        this.documentos = new Documento[600];
        this.documentosInsertados = 0;
    }


    public boolean insertarDocumento(Documento documento) {
        boolean retorno = false;
        
        if (!existe(documento)){
            if (documentosInsertados >= documentos.length) {
                extenderArray();
            }
            
            retorno = true;
            documentos[documentosInsertados] = documento;
            documentosInsertados++;
        }
        return retorno;
    }
    
    private boolean existe(Documento documento){
        boolean retorno = false;
        for (int i = 0; i < documentosInsertados; i++) {
            if (documentos[i].compareTo(documento) == 0){
                retorno= true;
                break;
            }
        }
        return retorno;
    }

    private void extenderArray() {
        Documento[] nuevoArray = new Documento[(int) (getDocumentos().length * 1.5)];
        System.arraycopy(getDocumentos(), 0, nuevoArray, 0, getDocumentos().length);
        setDocumentos(nuevoArray);
    }
    
    public void limpiarPesos() {
        for (int i = 0; i < documentosInsertados; i++) {
            getDocumentos()[i].limpiarPeso();

        }
    }
    
    public int tamañoArreglo(){
        return getDocumentos().length;
    }
    
    public int cantidadDocumentos(){
        return documentosInsertados;
    }
    
    
    public Documento[] rankear(TablaHash tablaHash, String consulta){
        Documento [] retorno = new Documento[documentosInsertados];
        String[] palabras = null;
        String delimitadores = "[ _.,;:?!¡«»¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";
        palabras = consulta.split(delimitadores);
        
        for (String p: palabras) {
            p = p.toLowerCase();
            Palabra palabra = ((Palabra)tablaHash.buscar(new Palabra(p)));
            if (palabra == null)continue;
            
            ArrayList<Posteo> posteos = palabra.getPosteos();
            
            for (Posteo posteo : posteos) {
                posteo.getDocumento().rankear(posteo.getFrecuenciaPalabra(),posteos.size(),cantidadDocumentos());
                System.out.println("PESO DE " + posteo.getDocumento().getNombre() + ": " + posteo.getDocumento().getPeso());

                System.out.println("frecuencia " + posteo.getFrecuenciaPalabra());

                for (int i =0; i<documentosInsertados;i++) {
                    if(documentos[i]!=null)
                    if(documentos[i].getNombre().compareToIgnoreCase(posteo.getDocumento().getNombre())==0){
                        documentos[i] = posteo.getDocumento();
                        documentos[i].setRanking(posteo.getDocumento().getPeso());
                        System.out.println("Peso documento "+documentos[i].getPeso());
                        
                    }
                }

            }
        }
        for (int i = 0; i < documentos.length -1; i++) {
            if(documentos[i]!=null)
            System.out.println(i+")"+documentos[i].getNombre()+" peso "+documentos[i].getPeso());
            
        }
        
        System.out.println("" + documentosInsertados);
        
        ordenar();
        System.arraycopy(getDocumentos(), 0, retorno, 0, documentosInsertados);
        limpiarPesos();
        
        return retorno;
    } 
    
    private void ordenar()
    {
        ordenar(0, documentosInsertados - 1 );
    }
    
    //PROBAR 
    private void ordenar(int izq, int der) {
        int i = izq, j = der;
        float x = documentos[(izq + der) / 2].getPeso();
        System.out.println("x "+x+" [i]"+documentos[i].getPeso()+" [j]"+documentos[j].getPeso());
        do {
            while (getDocumentos()[i].getPeso() > x && i < der) {
                i++;
            }
            while (x > getDocumentos()[j].getPeso() && j > izq) {
                j--;
            }
            if (i <= j) {
                Documento y = getDocumentos()[i];
                getDocumentos()[i] = getDocumentos()[j];
                getDocumentos()[j] = y;
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            ordenar(izq, j);
        }
        if (i < der) {
            ordenar(i, der);
        }
    }

    public Documento[] getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documento[] documentos) {
        this.documentos = documentos;
    }
}

    

