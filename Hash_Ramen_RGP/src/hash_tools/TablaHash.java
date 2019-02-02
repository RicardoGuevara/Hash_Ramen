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
                        ca = null;
        while (c!=null) 
        {
            if (campo.equals(c.getCampo()))
            {
                if (ca==null)
                {
                    this.tabla[hash(campo)]=c;
                    ca=c;
                }
                else
                {
                    ca.setSig(c.getSig());
                    if (ca.getSig()!=null)
                    {
                        ca.getSig().setUp(ca);
                        
                    }
                    c.delBlock();
                }
                c=c.getSig();
            }
            else
            {
                ca=c;
                c=c.getSig();
            }
        }
        if (campo.equals(this.tabla[hash(campo)].getCampo())) 
            this.tabla[hash(campo)]=null;
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
