/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_tools;

import gsi.CampoRegistro;

/**
 *
 * @author rpayan
 */
public class TablaHash {
    
    private CampoRegistro tabla[];

    public TablaHash(int capacidad_tabla) {
        tabla = new CampoRegistro[capacidad_tabla];
    }
    
    public void add(CampoRegistro registro)
    {
        int hash = hash(registro);
        if (this.tabla[hash]==null)
            this.tabla[hash]=registro;
        else
            this.tabla[hash].addBlok(registro);
    }
    
    public CampoRegistro search(String clave)
    {
        return this.tabla[hash(clave)];
    }
    
    private int hash(CampoRegistro cr)
    {
        return hash(cr.getCampo());
    }
    
    private int hash(String key)
    {
        int sum=0;
        for (char let : key.toCharArray()) {
            sum += (int)let;
        }
        return (sum<tabla.length)?tabla.length % sum:sum%tabla.length;
    }
    
    public void printTable()
    {
        for (CampoRegistro registro : tabla) {
            //System.out.println(registro);
            if (registro==null)
                System.out.println("null");
            else
                //registro.printRegistChain();
                registro.printBlok();
            System.out.println("_____");
        }
    }
    
}
