package Busqueda;

import java.util.Date;

public class Busqueda 
{
    private String busqueda;
    private Date fecha;
    
    public Busqueda(String busqueda, Date fecha)
    {
        this.busqueda = busqueda;
        this.fecha = fecha;
    }

    public String getBusqueda()
    {
        return busqueda;
    }

    public void setBusqueda(String busqueda)
    {
        this.busqueda = busqueda;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        s += "$" + busqueda + "%" + fecha.toString();
        return s;
    }
}
