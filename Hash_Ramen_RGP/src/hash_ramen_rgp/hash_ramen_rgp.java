/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_ramen_rgp;

import hash_tools.HashRamen;

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
        
    }
    
    public static void test_ramen_structure()
    {
        HashRamen ramen = new HashRamen(3,10,";");
        ramen.insert("aaa;bbb;ccc");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("ggg;hhh;fff");
        ramen.insert("bbb;ste;otro");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("ggg;hhh;bbb");
        ramen.insert("111;222;333");
        
        ramen.print();
        
        ramen.generalFieldSearch("bbb");
        ramen.specificFieldSearch("bbb", 1);
        ramen.generalFieldDelete("bbb");
        ramen.generalFieldSearch("bbb");
        ramen.generalFieldSearch("fff");
        
        System.out.println("\nverificacion de eliminaciones:");
        ramen.print();
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
}
