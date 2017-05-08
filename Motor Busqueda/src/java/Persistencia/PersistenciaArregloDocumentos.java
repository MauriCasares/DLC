
package Persistencia;

import Colecciones.ArregloDocumentos;
import Colecciones.TablaHash;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenciaArregloDocumentos {
    
    public static void write(ArregloDocumentos ad) {
        try (FileOutputStream fos = new FileOutputStream("arregloDocumentos.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ad);
            oos.flush();
        } catch (IOException e) {
            System.out.println("IOExc NO se pudo grabar el arreglo documentos por " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Exc" + ex.getMessage());
        }
    }
    
    public static ArregloDocumentos read() 
      {
           ArregloDocumentos retorno = null;
           
           try (FileInputStream fis = new FileInputStream("arregloDocumentos.dat"))
           {
                 ObjectInputStream ois = new ObjectInputStream(fis);
          
                 retorno = ( ArregloDocumentos ) ois.readObject();
                 
                 ois.close();
           }
           catch (IOException e)
           {
               System.out.println("IOExc"+e.getMessage());
           }
           catch (Exception ex){
               System.out.println("Exc" + ex.getMessage());
           }
           
           return retorno;
       }
}
