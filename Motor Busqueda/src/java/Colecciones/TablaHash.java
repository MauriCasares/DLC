package Colecciones;

import java.io.Serializable;
import Entidad.*;

public class TablaHash implements Serializable{

    private int tamaño;
    private int elementosInsertados;
    private Lista[] elementos;
    private static TablaHash instancia;
    
    
    public static TablaHash getInstance(){
        if (instancia == null){
            instancia = Persistencia.PersistenciaTablaHash.read();
            
            if (instancia == null) instancia = new TablaHash();
        }
        return instancia;
    }
    
    private TablaHash() {
        this.tamaño = 200;
        this.elementosInsertados = 0;
        this.elementos = new Lista[this.tamaño];

        for (int i = 0; i < this.tamaño; i++) {
            elementos[i] = new Lista();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public boolean vacia(){
       return this.tamaño == 0;
    }

    
    private int hash(Comparable<Palabra> nuevo) {
        // Funcion de dispersion para la elementos con tamaño 'tamaño'
        return (nuevo.hashCode() % this.tamaño);
    }

    private int hash(Comparable<Palabra> nuevo, int tamaño) {
        // Funcion de dispersion para la elementos con tamaño 'tamaño'
        return (nuevo.hashCode() % tamaño);
    }
    
    public boolean insertar(Comparable<Palabra> nuevo) {
        // Inserta nodos a la lista en la posicion [hash(nuevo)] sin aceptar repetidos
        // si se inserta correctamente devuelve true de lo contrario false
        
        boolean insertado = elementos[hash(nuevo)].insertar(nuevo);
        
        if (insertado){
            elementosInsertados++;
            
            if ((float)(elementosInsertados/tamaño) >= 8 ){
                this.reHash();
            }
        }
        
        return insertado;
    }

    public Comparable<Palabra> buscar(Comparable<Palabra> buscar) {
        // BUSCA dentro de la lista en la posicion [hash(nuevo)] 
        // si se encuentra correctamente devuelve el objeto Comparable de lo contrario null
        return elementos[hash(buscar)].buscar(buscar);
    }
    
    public boolean existePalabra(Comparable<Palabra> buscar){
        return elementos[hash(buscar)].buscar(buscar) != null;
    }
    
    //NO FUNCIONA
    private void reHash(){
        System.out.println("REHASHHHH");
        int nuevoTamaño = ((int)(this.tamaño * 1.5));
        Lista nuevaTabla[] = new Lista[nuevoTamaño];
        
        for (int i = 0; i < nuevoTamaño; i++) {
            nuevaTabla[i] = new Lista();
        }
        
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; elementos[i].get(j) != null; j++) {
                
                
                nuevaTabla[hash(elementos[i].get(j), nuevoTamaño)].insertar(elementos[i].get(j));
                
            }
        }
        
        this.elementos = nuevaTabla;
        this.tamaño = nuevoTamaño;
    }
}
