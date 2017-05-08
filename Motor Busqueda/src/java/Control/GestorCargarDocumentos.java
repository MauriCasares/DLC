package Control;

import Colecciones.ArregloDocumentos;
import Entidad.Documento;
import Entidad.Posteo;
import java.util.*;
import java.io.*;
import Entidad.*;
import Colecciones.*;

public class GestorCargarDocumentos {

    private ArrayList<Palabra> palabras;
    private ArrayList<Posteo> posteos;
    private TablaHash tablaHash;
    private ArregloDocumentos arregloDocumentos;

    public GestorCargarDocumentos() {
        this.palabras = new ArrayList();
        this.tablaHash = TablaHash.getInstance();
        this.arregloDocumentos = ArregloDocumentos.getInstance();
        this.posteos = new ArrayList();
        this.armarVocabulario();
    }

    public void armarVocabulario() {

        //File f1 = new File("C:\\Users\\LaWebStore\\GlassFish_Server\\glassfish\\domains\\Dominio_TP\\config\\Textos\\1.txt");
        //File f2 = new File("C:\\Users\\LaWebStore\\GlassFish_Server\\glassfish\\domains\\Dominio_TP\\config\\Textos\\2.txt");
        //File f3 = new File("C:\\Users\\LaWebStore\\GlassFish_Server\\glassfish\\domains\\Dominio_TP\\config\\Textos\\3.txt");
        File f1 = new File("Textos\\00ws110.txt");
        File f2 = new File("Textos\\0ddc809a.txt");
        File f3 = new File("Textos\\0ddcc10.txt");
        File f4 = new File("Textos\\0ddcl10.txt");
        File f5 = new File("Textos\\1argn10.txt");
        File f6 = new File("Textos\\1cahe10.txt");
        File f7 = new File("Textos\\1dfre10.txt");
        File f8 = new File("Textos\\1donq10.txt");
        File f9 = new File("Textos\\19rus10.txt");
        File f10 = new File("Textos\\11001108.txt");
        ArrayList<File> archivos = new ArrayList();
        archivos.add(f1);
        archivos.add(f2);
        archivos.add(f3);
        archivos.add(f4);
        archivos.add(f5);
        archivos.add(f6);
        archivos.add(f7);
        archivos.add(f8);
        archivos.add(f9);
        archivos.add(f10);
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        
        try {
            for (File archivo : archivos) {
                Documento nuevoDocumento = new Documento(archivo.getPath());

                if (arregloDocumentos.insertarDocumento(nuevoDocumento)) {
                    fr = new FileReader(archivo);
                    br = new BufferedReader(fr);

                    linea = br.readLine();

                    while (linea != null) {
                        cargarPalabrasEnHash(linea, nuevoDocumento);

                        linea = br.readLine();
                        while (linea != null) {
                            cargarPalabrasEnHash(linea, nuevoDocumento);
                            linea = br.readLine();
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Error en armar vocabulario - " + e.toString());
        }
    }


    private void cargarPalabrasEnHash(String linea, Documento nuevoDocumento) {
        try {
            String[] palabras = null;
            String delimitadores = "[ _.,;:?!¡«»¿\\'\\\"\\\\[\\[]\\]()~#*/-]+";

            if (linea != null) {
                palabras = linea.split(delimitadores);
                for (String p : palabras) {
                    if (p.compareTo("") != 0) {

                        p = p.toLowerCase(); //Convertir la palabra a letra minúscula
                        Palabra palabra = new Palabra(p, nuevoDocumento);

                        
                        if (tablaHash.insertar(palabra)) {
                            if (palabra.getPalabra().compareToIgnoreCase("project") == 0) {
                                    System.out.println(palabra.getPalabra() + "     " + nuevoDocumento.getNombre());
                            }
                        } else {
                            if (((Palabra) tablaHash.buscar(palabra)).existePosteo(nuevoDocumento)) {
                                ((Palabra) tablaHash.buscar(palabra)).aumentarFrecuencia(nuevoDocumento);
                            } else {
                                ((Palabra) tablaHash.buscar(palabra)).añadirPosteo(nuevoDocumento);

                                if (palabra.getPalabra().compareToIgnoreCase("project") == 0) {
                                    //System.out.println(palabra.getPalabra() + "     " + nuevoDocumento.getNombre());

                                    //for (Posteo posted : ((Palabra) tablaHash.buscar(palabra)).getPosteos()) {
                                        //System.out.println("VER SISE CARGA BIEN" + posted.getDocumento().getNombre());
                                    //}
                                }
                                
                                
                                
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void escribirArchivo() throws IOException {
        Persistencia.PersistenciaTablaHash.write(tablaHash);
        Persistencia.PersistenciaArregloDocumentos.write(arregloDocumentos);
    }

}
