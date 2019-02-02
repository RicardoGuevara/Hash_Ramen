/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_tools;

import gsi.CampoRegistro;
import hash_tools.TablaHash;

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
            ramen[i] = new TablaHash(capacidad);
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
