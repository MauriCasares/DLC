package Persistencia;

import java.io.*;
import Colecciones.TablaHash;

public class PersistenciaTablaHash {

    public static void write(TablaHash th) {
        try (FileOutputStream fos = new FileOutputStream("hashTable.dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(th);
            oos.flush();
        } catch (IOException e) {
            System.out.println("IOExc NO se pudo grabar la Tabla por " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Exc" + ex.getMessage());
        }
    }
    
    public static TablaHash read() 
      {
           TablaHash retorno = null;
           
           try (FileInputStream fis = new FileInputStream("hashTable.dat"))
           {
                 ObjectInputStream ois = new ObjectInputStream(fis);
          
                 retorno = ( TablaHash ) ois.readObject();
                 
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
