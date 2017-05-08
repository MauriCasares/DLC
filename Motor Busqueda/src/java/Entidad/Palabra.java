package Entidad;

import java.io.Serializable;
import java.util.ArrayList;

public class Palabra implements Comparable<Palabra>, Serializable
{
    private String palabra;
    private ArrayList<Posteo> posteos;
    private int frecuenciaMaxima;
        
    public Palabra(String palabra)
    {
        this.palabra = palabra;
        this.posteos = new ArrayList();
        this.frecuenciaMaxima = 0;
    }
    
    public Palabra(String palabra, Documento documento){
        this.palabra = palabra;
        this.posteos = new ArrayList();
        añadirPosteo (documento);
    }

    public void añadirPosteo(Documento documento)
    {
        if (posteos.add(new Posteo(documento)) && palabra.compareToIgnoreCase("Project")==0){
//            System.out.println("se añadio +" + documento.getNombre());
        }
    }
      
    
    public int cantidadPosteos()
    {
        return posteos.size();
    }
    
    public boolean contieneDocumento(Documento documento)
    {
        boolean retorno = false;
        for (Posteo posteo : posteos)
        {
            if(posteo.getDocumento().compareTo(documento) == 0){
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public String getPalabra()
    {
        return palabra;
    }

    public void setPalabra(String palabra)
    {
        this.palabra = palabra;        
    }

    public int getFrecuenciaMaxima()
    {
        return frecuenciaMaxima;
    }

    public void setFrecuenciaMaxima(int frecuenciaMaxima)
    {
        this.frecuenciaMaxima = frecuenciaMaxima;
    }
    
    public void aumentarFrecuencia(Documento documento)
    {
        for (Posteo posteo : posteos)
        {
            if (posteo.getDocumento().compareTo(documento) == 0)
            {
                posteo.aumentarFrecuencia();
            }
        }
    }
    
    public boolean existePosteo(Documento documento){
        boolean retorno = false;
        Posteo comparar = new Posteo(documento);
        
        for (Posteo posteo : posteos) {
            if (comparar.compareTo(posteo) == 0){
                retorno = true;
                break;
            }
        }
        
        return retorno;
    }
    

    public int compareTo(Palabra palabra)
    {
        return palabra.getPalabra().compareToIgnoreCase(this.palabra);
    }
    

    public ArrayList<Posteo> getPosteos()
    {
        return posteos;
    }

    public void setPosteos(ArrayList<Posteo> posteos)
    {
        this.posteos = posteos;
    }
    
    public int hashCode(){
        
        return Math.abs(this.palabra.hashCode());
    }
}
