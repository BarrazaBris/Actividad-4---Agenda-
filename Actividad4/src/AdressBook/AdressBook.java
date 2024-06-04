package AdressBook;
import javax.sound.midi.SysexMessage;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

public class AdressBook {
    public static void main(String[] args) {
        HashMap<String, String> mapaCon = new HashMap<String, String>();
        Scanner leer = new Scanner(System.in);

        int opc, ban = 0;
        String telefono, nombre;
        System.out.println("Agenda Telefónica");

        do {
            try {
                System.out.println("\n Seleccione alguna opción: \n");
                System.out.println("Cargar datos ------------- [1]");
                System.out.println("Guardar datos ------------ [2]");
                System.out.println("Mostrar contactos -------- [3]");
                System.out.println("Nuevo número ------------- [4]");
                System.out.println("Borrar contacto ---------- [5]");
                System.out.println("Salir -------------------- [6]");

                opc = leer.nextInt();

                switch (opc) {
                    case 1:
                        load(mapaCon);
                        break;

                    case 2:
                        save(mapaCon);
                        break;

                    case 3:
                        list(mapaCon);
                        break;

                    case 4:
                        System.out.println("Inserte el nuevo número de teléfono: ");
                        telefono = leer.next();
                        System.out.println("Inserte el nombre del contacto: ");
                        nombre = leer.next();
                        create(mapaCon, telefono, nombre);
                        break;

                    case 5:
                        System.out.println("Inserte el teléfono a eliminar: ");
                        telefono = leer.next();
                        delete(mapaCon, telefono);
                        break;

                    case 6:
                        System.out.println("Saliendo\n");
                        ban = 1;
                        break;

                    default:
                        System.out.println(" Opción Incorrecta\n");
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("Error!\n");
                break;
            }

        }while (ban == 0) ;
    }

    public static void list(HashMap<String, String>mapaCon)
    {
        Iterator<String> iterator = mapaCon.keySet().iterator();
        System.out.println("Contactos:\n");
        System.out.println("  Teléfono\t|\tNombre   ");
        System.out.println("------------------------");
        while (iterator.hasNext())
        {
            String llave = iterator.next();

            System.out.println(" "+llave+"\t|\t"+mapaCon.get(llave));
        }
    }
public static void create(HashMap<String, String>mapaCon,String tel, String nom)
{
    if (mapaCon.containsKey(tel))
    {
        System.out.println("\nError!\nNo se registra dos veces el mismo telefono\n");
    }
    else
    {
        mapaCon.put(tel, nom);
        System.out.println("\nContacto agregado");
    }
}

public static void delete(HashMap<String, String>mapaCon, String tel)
{
    if(mapaCon.containsKey(tel))
    {
        System.out.println("\nContacto eliminado: "+mapaCon.get(tel)+"\n");
        mapaCon.remove(tel);
    }
    else
        System.out.print("\nEl teléfono no existe\n");
}
public static void load(HashMap<String, String> m)
{
    String inputFilename = "C:\\Users\\Briseida\\IdeaProjects\\Actividad4\\src\\AdressBook\\input.csv";
            String a [];

    BufferedReader bufferedReader = null;
    try{
        bufferedReader = new BufferedReader(new FileReader (inputFilename));
        String line ;
        while ((line = bufferedReader.readLine()) != null ) {
            a = line.split(",");
            m.put (a [0], a[1]);
        }
    }catch (IOException e) {
        System.out.print("IOException catched shile reading: " +e.getMessage());
    }finally {
        try{
            if (bufferedReader != null){
                bufferedReader.close();
                System.out.println("\nContactos cargados");
            }
        }catch (IOException e) {
            System.out.println("IOException catched while closing: "+ e.getMessage());
        }
    }
    }
public static void save(HashMap<String, String> m)
{
    Iterator<String> iterator = m.keySet().iterator();
    String inputFilename = "C:\\Users\\Briseida\\IdeaProjects\\Actividad4\\src\\AdressBook.input.csv";

    BufferedWriter bufferedWriter = null;

    try{
        bufferedWriter = new BufferedWriter (new FileWriter(inputFilename));

        while (iterator.hasNext())
        {
            String llave = iterator.next();

            bufferedWriter.write(llave+","+m.get(llave)+"\n");
        }
    }
    catch (IOException e) {
        System.out.println("IOException catched while writing:" + e.getMessage());
    } finally {
        try{
            if (bufferedWriter != null) {
                bufferedWriter.close();
               System.out.println("\n Cambios guardados");
            }
        }catch (IOException e){
            System.out.println("IOException catched while closing" + e.getMessage());
        }
    }

}
}







