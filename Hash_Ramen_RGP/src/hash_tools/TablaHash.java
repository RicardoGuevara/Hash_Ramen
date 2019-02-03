/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_tools;

/**
 *
 * @author rpayan
 */
public class TablaHash {
    
    public CampoRegistro tabla[];
    public HashRamen ramen;

    public TablaHash(int capacidad_tabla,HashRamen ramen) {
        tabla = new CampoRegistro[capacidad_tabla];
        this.ramen = ramen;
    }
    
    public void add(CampoRegistro registro)
    {
        int hash = hash(registro);
        if (this.tabla[hash]==null)
            this.tabla[hash]=registro;
        else
            this.tabla[hash].addBlok(registro);
    }
    
    public java.util.ArrayList<CampoRegistro> exactSearchResults(String campo)
    {
        java.util.ArrayList<CampoRegistro> resultados = new java.util.ArrayList<>();
        CampoRegistro actual = search(campo);
        
        while (actual!=null) 
        {
            if (campo.equals(actual.getCampo())) 
            {
                resultados.add(actual.getStartPoint());
            }
            actual = actual.getSig();
        }
        
        return resultados;
    }
    
    public CampoRegistro search(String clave)
    {
        return this.tabla[hash(clave)];
    }
    
    
    public void delete(String campo)
    {
        CampoRegistro   c = search(campo),
                        resp;
        while (c!=null) 
        {
            resp=c.getSig();
            if (campo.equals(c.getCampo())) 
            {
                c.delBlock(this.ramen);
            }
            c=resp;
        }
    }
    
    public int hash(CampoRegistro cr)
    {
        return hash(cr.getCampo());
    }
    
    public int hash(String key)
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
