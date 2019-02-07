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
        test_ramen_structure();
        //test_file_generator();
        /*
        for (int i = 0; i < 300; i++) {
        System.out.println((char)i+" "+i);
        }*/
        
    }
    
    public static void loadFile(HashRamen ramen,String file)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String ln;
            while ((ln=br.readLine())!=null) 
            {
                ramen.insert(ln);
            }
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
