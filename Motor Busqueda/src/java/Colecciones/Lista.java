package Colecciones;

import java.io.Serializable;
import Entidad.*;

public class Lista implements Serializable {

    private Nodo raiz;
    private int largo;

    public Lista() {
        this.raiz = null;
        this.largo = 0;
    }

    public boolean insertar(Comparable<Palabra> info) {
        // INSERTA sin aceptar nodos repetidos, devuelve true si inserto y false de lo contrario
        Nodo nuevo = new Nodo(info);
        Nodo p = raiz;
        Nodo s = null;
        boolean retorno = true;

        while (p != null) {

            if (((Palabra)p.getInfo()).compareTo((Palabra) info) == 0) {
                retorno = false;
                break;
            }
            s = p;
            p = p.getSiguiente();
        }
        if (retorno) {
            this.largo++;
            p = nuevo;
            if (s != null) {
                s.setSiguiente(p);
            } else {
                raiz = nuevo;
            }
        }

        return retorno;
    }

    public Comparable<Palabra> buscar(Comparable<Palabra> info) {
        Nodo p = raiz;
        Comparable<Palabra> retorno = null;

        while (p != null) {
            if (((Palabra)(p.getInfo())).compareTo((Palabra) info) == 0) {
                retorno = (Palabra)(p.getInfo());
                break;
            }
            p = p.getSiguiente();
        }

        return retorno;
    }

    public int largo() {
        return this.largo;
    }

    public Comparable<Palabra> get(int index) {
        Comparable<Palabra> retorno = null;
        
        
        if( index >= 0 && index < largo() ){
                  Nodo p = this.raiz;
                  
                  for( int i = 0; i < index; i++ ) 
                  { 
                      p = p.getSiguiente();                      
                  }
                  retorno = p.getInfo();
        }
        
        return retorno;        
    }
   
}
