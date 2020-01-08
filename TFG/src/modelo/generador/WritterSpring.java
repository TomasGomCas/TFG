package modelo.generador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;

import modelo.Application;
import modelo.Data;
import modelo.DataInput;
import modelo.DataOutput;
import modelo.ProgramRest;
import modelo.Service;

public class WritterSpring implements Writter{

	private int  aux = 0;  
	private File FO = new File("target\\baseCode\\gs-rest-service-complete");
	private File FD = new File("target\\generatedCode");
	private File fichero = new File ("target\\generatedCode\\gs-rest-service-complete\\src\\main\\java\\lanzador\\Controller.java");

	// METHODS
	@Override
	public void write() {
		aux=0;
		copy(FO,FD);
		createControllerFile();
		writeFile(writeController());
	}
	
	private void copy(File FO,File FD){
        //si el origen no es una carpeta
        if(!FO.isDirectory()){
            //Llamo la funcion que lo copia
            copyFile(FO,FD);
        }else{
           //incremento el contador de entradas a esta funci�n 
           aux++; 
           //solo se entra ac� cuando se quiera copiar una carpeta y 
           //sea la primera es decir la carpeta padre
           if(aux==1){
                //Cambio la ruta destino por el nombre que tenia mas el nombre de
                //la carpeta padre
                FD=new File(FD.getAbsolutePath()+"/"+FO.getName()); 
                //si la carpeta no existe la creo
                if(!FD.exists()){
                    FD.mkdir();
                }
           } 
           //obtengo el nombre de todos los archivos y carpetas que 
           //pertenecen a este fichero(FO)
           String []Rutas=FO.list();
           //recorro uno a uno el contenido de la carpeta
           
           /*IMPORTANTE:EL HML SE DESCONFIGURA Y NO ME DEJA 
             PORNE LA LINEA FO SIGUIENTE BIEN, TENGO PROBLEMA 
             CON EL SIGNO MENOR.SI UD LE PASA LO MISMO DESCARGE EL
             PROGRAMA CON EL LINK DE ABAJO Y V�ALO DE FOMA SEGURA           
           */
             for(int i=0;i<Rutas.length;i++){
              //establezco el nombre del nuevo archivo origen 
              File FnueOri=new File(FO.getAbsolutePath()+"/"+Rutas[i]);
              //establezco el nombre del nuevo archivo destino 
              File FnueDest= new File(FD.getAbsolutePath()+"/"+Rutas[i]);
              //si no existe el archivo destino lo creo
              if(FnueOri.isDirectory() && !FnueDest.exists()){
                  FnueDest.mkdir();                        
              }
              //uso recursividad y llamo a esta misma funcion has llegar
              //al ultimo elemento    
              copy(FnueOri,FnueDest); 
           }
        }
        
}
	
	private static void copyFile(File FO,File FD){
        try {
        //Si el archivo a copiar existe
        if(FO.exists()){
            String copiar="S";
            //si el fichero destino ya existe
            if(FD.exists()){
              // System.out.println("El fichero ya existe, Desea Sobre Escribir:S/N ");
               //copiar = ( new BufferedReader(new InputStreamReader(System.in))).readLine();
            }
            //si puedo copiar
            copiar = "S";
            if(copiar.toUpperCase().equals("S")){
                //Flujo de lectura al fichero origen(que se va a copiar)            
                FileInputStream LeeOrigen= new FileInputStream(FO);
                //Flujo de lectura al fichero destino(donde se va a copiar)
                OutputStream Salida = new FileOutputStream(FD);
                //separo un buffer de 1MB de lectura
                byte[] buffer = new byte[1024];
                int tamano;
                //leo el fichero a copiar cada 1MB
                while ((tamano = LeeOrigen.read(buffer)) > 0) {
                //Escribe el MB en el fichero destino
                Salida.write(buffer, 0, tamano);
                }
                System.out.println(FO.getName()+" Copiado con Exito!!");
                //cierra los flujos de lectura y escritura
                Salida.close();
                LeeOrigen.close();
            }
            
        }else{//l fichero a copiar no existe                
            System.out.println("El fichero a copiar no existe..."+FO.getAbsolutePath());
        }
        
    } catch (Exception ex) {
        System.out.println(ex.getMessage());  
   }
}

	private void createControllerFile() {
		
		try {
			  fichero.createNewFile();
			} catch (IOException ioe) {
			  ioe.printStackTrace();
			}
		
	}
	
	private void writeFile(String string) {
		
	    BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(fichero));
		    writer.write(string);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String writeService(Service service) {
		
		String str = "";
		str += writeServiceHeader(service.getName(), service.getData());
		
		str += "\n\t{"
				+ "\n\treturn null;\n\t}\n";
		
		return str;
	}
	
	private String writeServiceHeader(String mapping,LinkedList<Data> data) {
		
		// ESCRIBIMOS LA CABECERA
		String string = "\n\t@RequestMapping(\"/" + mapping + "\")";
		Data dataAux = null;
		for(Data i : data) {
			if(i instanceof DataOutput) dataAux = i;
		}
		string += "\n\tpublic " + getTypeFormula(dataAux.getValue()) + " " + mapping + "(";
		
		// ESCRIBIMOS LOS PARAMETROS
		int j = 0;
		for(Data i : data) {
			if(i instanceof DataInput) {
				dataAux = i;
				string = string + "@RequestParam(value=\"" + dataAux.getName() + "\") " + dataAux.getDataType().toString() + " " + dataAux.getName() +"";
			}
			
			if(j<data.size()-2) string = string + ",";
			j++;
		}		
		
		string += ")";
		return string;
	}
	
	private String writeController() {
		
		String string = "package lanzador;\r\n" +
				"import org.springframework.web.bind.annotation.RequestMapping;\r\n" + 
				"import org.springframework.web.bind.annotation.RequestParam;\r\n" + 
				"import org.springframework.web.bind.annotation.RestController;\r\n" + 
				"\r\n" + 
				"@RestController\r\n" + 
				"public class Controller {\n";
		
		ProgramRest rest = Application.getInstance().getProgramRest();
		
		for(Service service : rest.getServices()) {
			string = string + writeService(service);
		}
		
		string = string + "\n}" ;
		return string;
	}
	
	private String getTypeFormula(String formula) {
		
		if(formula.contains("SUM") || formula.contains("MINUS")) {
			return "Integer";
		} else {
			return "String";
		}
		
	}
	
	
}
