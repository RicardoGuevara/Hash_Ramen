/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsi;

/**
 *
 * @author Labcienciascomputacion
 */
public class FileGenerator {
 
    int     n_registro, //numero de registros
            n_campos;   //numero de campos por registro
            
    java.util.ArrayList<Registro> registros;

    public FileGenerator(int n_registro, int n_campos) {
        this.n_registro = n_registro;
        this.n_campos = n_campos;
        registros = new java.util.ArrayList<>();
    }
    
    public void ask()
    {
        
    }
    
    class Registro
    {
        int     l_campo;    //numero de campos por registro
        boolean numerico;   //campo numerico - alfabetico
        java.util.ArrayList<String> campos = new java.util.ArrayList<>();

        public Registro(int l_campo, boolean numerico) {
            this.l_campo = l_campo;
            this.numerico = numerico;
        }
        
        public String generate()
        {
            return "pendiente";
        }
        
        private String generateCampo()
        {
            //48-57 numericos ASCII
            //97-122 alfabeticos ASCII
            
            int     min = numerico? 48:97,  //minimo de generado random
                    max = numerico? 97:122; //maximo de generado random
            
            char[]  resultado = new char[l_campo];
            
            for (char c : resultado) {
                c = (char)Math.floor(Math.random()*(max-min+1)+min); 
            }
            
            return new String(resultado);
        }
    }
    
}
