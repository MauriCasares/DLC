package Entidad;

import java.io.Serializable;

public class Posteo implements Comparable<Posteo>, Serializable
{
    private Documento documento;
    private int frecuenciaPalabra;
    
    public Posteo(Documento documento)
    {
        this.documento = documento;
        this.frecuenciaPalabra = 1;
    }

    public Documento getDocumento()
    {
        return documento;
    }

    public void setDocumento(Documento documento)
    {
        this.documento = documento;
    }

    public int getFrecuenciaPalabra()
    {
        return frecuenciaPalabra;
    }

    public void setFrecuenciaPalabra(int frecuenciaPalabra)
    {
        this.frecuenciaPalabra = frecuenciaPalabra;
    }
    
    public void aumentarFrecuencia()
    {
        frecuenciaPalabra++;
    }
    
 
    public int compareTo(Posteo comparar) {
        return comparar.getDocumento().compareTo(this.documento);
    }
    
    public int compareToIgnoreCase(Posteo comparar) {
        return comparar.getDocumento().compareToIgnoreCase(this.documento);
    }
    
}
