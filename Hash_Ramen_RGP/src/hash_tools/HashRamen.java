/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_tools;

import hash_tools.TablaHash;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collections;

/**
 *
 * @author RICARDO
 */
public class HashRamen {
    
    private TablaHash[] ramen;  //grupo de tablas del ramen
    private int     densidad,   //densidad: número de tablas en el mapa = número de campos por registro
                    capacidad;  //capacidad: espac´o o campos disponibles de la tabla
    private String  separador;  //caracter de separacion de cada registro
    
    public HashRamen(int densidad, int capacidad, String separador_registros) 
    {
        this.capacidad = capacidad;
        this.densidad = densidad;
        this.separador = separador_registros;
        ramen = new TablaHash[densidad];    //numero de tablas
        for (int i = 0; i < densidad; i++) {
            ramen[i] = new TablaHash(capacidad,this);
        }
    }
    
    public void print()
    {
        for (TablaHash tablaHash : ramen) {
            System.out.println("__________________________Ramen");
            tablaHash.printTable();
        }
    }

    public void insert(String reg)
    {
        insert(separar(reg));
    }
    
    public void insert(CampoRegistro reg) 
    {
        for (TablaHash tablaHash : ramen) 
        {
            tablaHash.add(reg);
            reg = reg.getDer();
        }
    }
    
    public void generalFieldSearch(String campo) 
    {
        System.out.println("Busqueda: "+campo);
        /*
        for (TablaHash tablaHash : ramen)
        {
        System.out.println(tablaHash.exactSearchResults(campo));
        }
        */
        for (TablaHash tablaHash : ramen)
        {
            for (CampoRegistro exactSearchResult : tablaHash.exactSearchResults(campo))
            {
                System.out.println(exactSearchResult);
            }
        }
        System.out.println("______busqueda terminada_______");
    }
    
    public void specificFieldSearch(String campo, int index) 
    {
        System.out.println("Busqueda de: "+campo+" en el campo: "+(index+1));
        
        for (CampoRegistro exactSearchResult : ramen[index].exactSearchResults(campo)) 
        {
            System.out.println(exactSearchResult);
        }
        System.out.println("______busqueda terminada_______");
    }
    
    public void generalFieldDelete(String campo) 
    {
        System.out.println("Borrado de "+campo+" en cualquier campo");
        
        for (TablaHash tablaHash : ramen) 
        {
            tablaHash.delete(campo);
        }
        
    }
    
    public void specificFieldDelete(String campo, int index) 
    {
        System.out.println("Borrado de "+campo+" en el campo "+index);
        ramen[index].delete(campo);
    }
    
    public void delOne(String campo, int tab_index)
    {
        TablaHash t = ramen[tab_index];
        int i = t.hash(campo);
        if (t.tabla[i]!=null)
            t.tabla[i] = t.tabla[i].getSig();
        if (t.tabla[i]!=null) {
            t.tabla[i].getUp().setSig(null);
            t.tabla[i].setUp(null);
        }
        
    }
    
    public BigInteger min(int campo)
    {
        java.util.ArrayList<String> lista_campo = listaOrdenCampo(campo);
        return new BigInteger(lista_campo.get(0));
    }
    
    public BigInteger max(int campo)
    {
        java.util.ArrayList<String> lista_campo = listaOrdenCampo(campo);
        return new BigInteger(lista_campo.get(lista_campo.size()-1));
    }
    
    public BigDecimal promedio(int campo)
    {
        BigDecimal sum = BigDecimal.ZERO;
        java.util.ArrayList<String> lista_campo = listaOrdenCampo(campo);
        //System.out.println(lista_campo);
        for (String string : lista_campo) {
            sum = sum.add(new BigDecimal(string+".0"));
        }
        //return sum.divideAndRemainder(new BigInteger(String.valueOf(lista_campo.size())));
        return sum.divide(new BigDecimal(String.valueOf(lista_campo.size())+".0"),MathContext.DECIMAL128);
    }
    
    public int moda(int campo) throws NumberFormatException
    {
        java.util.ArrayList<String> lista_campo = listaOrdenCampo(campo);
        java.util.ArrayList<String> remobibles = new java.util.ArrayList<>();
        int     ant_long = lista_campo.size(),
                dif = -1,temp;
        
        String res = "-1";
        
        while (lista_campo.size()>1) 
        {
            remobibles.add(lista_campo.get(0));
            lista_campo.removeAll(remobibles);
            temp = ant_long-lista_campo.size();
            if (temp>dif && temp!=1) 
            {
                res=remobibles.get(0);
                dif=temp;
            }
            remobibles = new java.util.ArrayList<>();
            ant_long = lista_campo.size();
        }
        return Integer.parseInt(res);
    }
    
    public java.util.ArrayList<String> listaOrdenCampo(int index)
    {
        CampoRegistro cr;
        java.util.ArrayList<String> resultados = new java.util.ArrayList<>();
        for (CampoRegistro campo : ramen[index].tabla) {
            cr = campo;
            while (cr!=null) 
            {
                resultados.add(cr.getCampo());
                cr = cr.getSig();
            }
        }
        Collections.sort(resultados);
        return resultados;
    }
    
    public CampoRegistro separar(String reg)
    {
        CampoRegistro   cpa = null, //campo de registro actual
                        cpc = null; //campo de cabecera
        
        for (String campo : reg.split(this.separador)) 
        {
            if (cpa != null)
            {
                cpa.addChain(new CampoRegistro(campo));
                cpa = cpa.getDer();
            }
            else
            {
                cpa = new CampoRegistro(campo);
                cpc = cpa;
            }
        }
        return cpc;
    }
    
    public int getDensidad() {
        return densidad;
    }

    public void setDensidad(int densidad) {
        this.densidad = densidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    
    
    
}
