package Colecciones;

import java.io.Serializable;
import Entidad.*;
public class Nodo implements Serializable{
    private Comparable<Palabra> info;
    private Nodo siguiente;
    
    public Nodo(){
        
    }
    
    public Nodo(Comparable<Palabra> info){
        this.info = info;
    }
    
    public Nodo(Comparable<Palabra> info, Nodo siguiente){
        this.info = info;
        this.siguiente = siguiente;
    }
    
    public Comparable<Palabra> getInfo(){
        return info;
    }
    
    public void setInfor(Comparable<Palabra> info){
        this.info = info;
    }
    
    public Nodo getSiguiente(){
        return this.siguiente;
    }
    
    public void setSiguiente(Nodo siguiente){
        this.siguiente = siguiente;
    }
}
