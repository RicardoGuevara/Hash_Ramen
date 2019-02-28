/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_ramen_rgp;

import gsi.FileGenerator;
import hash_tools.HashRamen;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

/**
 *
 * @author rpayan
 */
public class hash_ramen_rgp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test_table_structure();
        //test_ramen_structure();
        //test_file_generator();
        //time_compare_bench();
        
        //444210879360890,805
        //50041.59
        
        HashRamen BBDD = new HashRamen(17,120,";");
        //loadFile(BBDD,"Prueba.txt");
        loadFile(BBDD,"archivo.txt");
        
        System.out.println("moda campo 8: "+BBDD.moda(7));
        System.out.println("maximo campo clave: "+BBDD.max(0));
        System.out.println("maximo campo j=8: "+BBDD.max(7));
        System.out.println("promedio campo 5: "+BBDD.promedio(4));
    }
    
    public static void time_compare_bench()
    {
        java.util.Scanner lectura = new java.util.Scanner(System.in);
        
        int     nr,         //número de registros
                nc,         //número de campos
                ant_time,   //tiempo anterior
                n;          //temporal versátil
                
        HashRamen ramen;    //estructura de carga
        
        System.out.println("Digite el número de registros del archivo");
        nr = lectura.nextInt();
        System.out.println("Digite el número de campos para cada registro");
        nc = lectura.nextInt();
        ramen = new HashRamen(nc,100,";");  //asignar dimensiones a la estructura
        
        System.out.println("\nGenerando archivo...______________________________");
        ant_time = (int)System.nanoTime();
        new FileGenerator(nr,nc).generate();    //genera el archivo de nr registros x nc campos
        System.out.println("Tiempo de generado: "+(System.nanoTime()-ant_time));
        
        System.out.println("\ncargando a la estructura..._______________________");
        ant_time = (int)System.nanoTime();
        loadFile(ramen,"data_base.txt");
        System.out.println("Tiempo de carga: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nBusqueda en el campo n..._________________________");
        ant_time = (int)System.nanoTime();
        System.out.println("Digite el número de campo de busqueda (empiezan por 1)");
        n = lectura.nextInt()-1;
        System.out.println("digite el valor que desea buscar:");
        ramen.specificFieldSearch(lectura.next(), n);
        System.out.println("Tiempo de busqueda específica: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nBusqueda en el campo clave..._____________________");
        ant_time = (int)System.nanoTime();
        System.out.println("digite el valor que desea buscar:");
        ramen.specificFieldSearch(lectura.next(), 0);
        System.out.println("Tiempo de busqueda en campo clave: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nBusqueda en todos los campos...___________________");
        ant_time = (int)System.nanoTime();
        System.out.println("digite el valor que desea buscar:");
        ramen.generalFieldSearch(lectura.next());
        System.out.println("Tiempo de busqueda general: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nmínimo..._________________________________________");
        ant_time = (int)System.nanoTime();
        System.out.println("Digite el número de campo de busqueda de mínimo (empiezan por 1)");
        n = lectura.nextInt()-1;
        System.out.println("el mínimo es: "+ramen.min(n));
        System.out.println("Tiempo de valor mínimo: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nmáximo..._________________________________________");
        ant_time = (int)System.nanoTime();
        System.out.println("Digite el número de campo de busqueda de máximo (empiezan por 1)");
        n = lectura.nextInt()-1;
        System.out.println("el máximo es: "+ramen.max(n));
        System.out.println("Tiempo de valor máximo: "+(System.nanoTime()-ant_time));
        
        System.out.println("\npromedio..._______________________________________");
        ant_time = (int)System.nanoTime();
        System.out.println("Digite el número de campo a promediar (empiezan por 1)");
        n = lectura.nextInt()-1;
        System.out.println("el promedio es: "+ramen.promedio(n));
        System.out.println("Tiempo de promediado: "+(System.nanoTime()-ant_time));
        
        System.out.println("\nmoda...___________________________________________");
        ant_time = (int)System.nanoTime();
        System.out.println("Digite el número de campo de busqueda de moda (empiezan por 1)");
        n = lectura.nextInt()-1;
        n = ramen.moda(n);
        System.out.println((n==-1)? "no hay un valor moda ya que ningún campo se repite":"la moda es: "+n);
        System.out.println("Tiempo de moda: "+(System.nanoTime()-ant_time));
        
        
    }
    
    public static void loadFile(HashRamen ramen,String file)
    {
        int cont = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String ln;
            while ((ln=br.readLine())!=null) 
            {
                cont++;
                ramen.insert(ln);
            }
            System.out.println("numero de registros: "+cont);
            br.close();
        } catch (java.io.IOException e) {
            System.out.println("error de carga del archivo: "+file);
        }
    }
    
    // tests____________________________________________________________________
    
    public static void test_ramen_structure()
    {
        HashRamen ramen = new HashRamen(3,10,";");
        ramen.insert("aaa;bbb;ccc");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("bbb;ste;otro");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("ggg;hhh;bbb");
        ramen.insert("111;222;333");
        ramen.insert("111;555;333");
        ramen.insert("111;555;333");
        ramen.insert("111;777;333");
        
        ramen.print();
        
        ramen.generalFieldSearch("bbb");
        ramen.specificFieldSearch("bbb", 1);
        ramen.generalFieldDelete("bbb");
        ramen.generalFieldSearch("bbb");
        ramen.generalFieldSearch("fff");
        
        System.out.println("\nverificacion de eliminaciones:");
        ramen.print();
        
        System.out.println("Busqueda de mínimo:en el campo 2");
        System.out.println(ramen.min(1));
        System.out.println("Busqueda de máximo:en el campo 2");
        System.out.println(ramen.max(1));
        System.out.println("Busqueda de la moda:en el campo 2");
        System.out.println(ramen.moda(1));
        System.out.println("Busqueda del promedio:en el campo 2");
        System.out.println(ramen.promedio(1));
    }
    
    public static void test_table_structure()
    {
        hash_tools.TablaHash th = new hash_tools.TablaHash(10,null);
        
        th.add(new hash_tools.CampoRegistro("aaa;bbb;ccc"));
        th.add(new hash_tools.CampoRegistro("ddd;eee;fff"));
        th.add(new hash_tools.CampoRegistro("ggg;hhh;iii"));
        th.add(new hash_tools.CampoRegistro("aaa;ste;otro"));
        th.add(new hash_tools.CampoRegistro("aaa;ste;ultimo"));
        th.printTable();
        System.out.println("search:\n");
        System.out.println(th.search("aaa;bbb;ccc"));
    }
    
    public static void test_file_generator()
    {
        FileGenerator fg = new FileGenerator(10,4);
        fg.generate();
    }
}
