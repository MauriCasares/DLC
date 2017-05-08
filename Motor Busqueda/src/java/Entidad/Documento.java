package Entidad;

import java.io.Serializable;

public class Documento implements Serializable
{
    private String nombre;
    private float ranking;
       
    public Documento(String nombre)
    {
        this.nombre = nombre;
        this.ranking = 0;
    }

    public float getPeso() {
        return ranking;
    }

    public void limpiarPeso(){
        this.ranking = 0;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void rankear(int frecuencia, int cantDocumentos, int totalDocumentos){
        float factorCorrecion = 0;
        if((totalDocumentos/cantDocumentos) <= 0)
            factorCorrecion = (float) 0.0001;
        else
            factorCorrecion = (float)Math.log10((float)totalDocumentos/(float)cantDocumentos);
        float nuevoRanking = frecuencia * factorCorrecion;
        this.ranking = this.ranking + nuevoRanking;
    } 
    
    public int compareTo(Documento comparar)
    {
        return comparar.getNombre().compareTo(this.nombre);                  
    }
    
    public int compareToIgnoreCase(Documento comparar)
    {
        return comparar.getNombre().compareToIgnoreCase(this.nombre);                  
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

}
