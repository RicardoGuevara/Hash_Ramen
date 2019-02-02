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
        //System.out.println(new Registro("aaa;bbb;ccc"));
        /*
        TablaHash th = new TablaHash(10);
        
        th.add(new CampoRegistro("aaa;bbb;ccc"));
        th.add(new CampoRegistro("ddd;eee;fff"));
        th.add(new CampoRegistro("ggg;hhh;iii"));
        th.add(new CampoRegistro("aaa;ste;otro"));
        th.add(new CampoRegistro("aaa;ste;ultimo"));
        th.printTable();
        System.out.println("search:\n");
        System.out.println(th.search("aaa;bbb;ccc"));
        */
        
        HashRamen ramen = new HashRamen(3,10,";");
        ramen.insert("aaa;bbb;ccc");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("ggg;hhh;fff");
        ramen.insert("bbb;ste;otro");
        ramen.insert("ddd;bbb;fff");
        ramen.insert("ggg;hhh;bbb");
        ramen.print();
        ramen.generalFieldSearch("bbb");
        ramen.specificFieldSearch("bbb", 1);
        ramen.generalFieldDelete("bbb");
        ramen.generalFieldSearch("bbb");
        ramen.generalFieldSearch("fff");
    }
    
    
    
}
