package tracer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tracer {

    private static final ArrayList<String>arrayDirecciones = new ArrayList<>();
    private static final ArrayList<String>arraySaltos = new ArrayList<>();
    public static int lista[] = new int[3];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String pingResult = "";
        String pingCmd = "tracert -d  www.facebook.com" ;
            
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String inputLine=" ";
                while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                arrayDirecciones.add(inputLine);
                arraySaltos.add(inputLine);
                }
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
        filtrar();
        filtrarSaltos();
        encadenar();
        txt();
    }
   
    public static void filtrar(){
        int cont = 0;
        for(int i = 4; i < arrayDirecciones.size() - 2; i++){          
            int j = arrayDirecciones.get(i).length()-2;
                    while(arrayDirecciones.get(i).charAt(j) != ' '){
                        cont++;
                        j--;
                }
                arrayDirecciones.set(i,arrayDirecciones.get(i).substring(arrayDirecciones.get(i).length() - 1 - cont));
                cont = 0;
            }
        System.out.println("Direcciones:----> "+arrayDirecciones);
    }
    
    public static void filtrarSaltos(){
        for(int i = 4; i < arraySaltos.size() - 2; i++){
            arraySaltos.set(i,arraySaltos.get(i).substring(0, 30));
        }
    }
    
    public static void encadenar(){
        for(int i = 4; i <= arrayDirecciones.size() - 1; i++){
            if(i + 1 < arrayDirecciones.size() - 3)
                arrayDirecciones.set(i,arrayDirecciones.get(i) + "," + arrayDirecciones.get(i+1));     
            }
        System.out.println("Encadenado:----> "+arrayDirecciones);
    }

    public static void txt() throws IOException{
        
        String ruta = "web/traza.csv";
        String ruta1 = "web/traza1.csv";
        File archivo = new File(ruta);
        File archivo1 = new File(ruta1);
        BufferedWriter bw, bw1;
            
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw1 = new BufferedWriter(new FileWriter(archivo1));
            bw.write("source,target");
            bw.newLine();
            bw1.write("source,target");
            bw1.newLine();            
            for(int i=4; i<=arrayDirecciones.size()-3; i++){
                bw.write(arrayDirecciones.get(i) + "");
                bw.newLine();
                
                bw1.write(arraySaltos.get(i) + "");
                bw1.newLine();
            }
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw1 = new BufferedWriter(new FileWriter(archivo1));
            bw.write("Acabo de crear el fichero de texto.");
            System.out.println("No Existe");
        }
        bw.close();
        bw1.close();
        
        System.out.println("COMPLETO:----> "+arrayDirecciones);
        System.out.println("COMPLETO:----> "+arraySaltos);
        
    }

    public ArrayList<String> getArray(){
        return arraySaltos;
    }
}