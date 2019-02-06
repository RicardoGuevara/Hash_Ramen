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
            n_campos,   //numero de campos por registro
            longs[];    //longitud de cada campo
    
    boolean[] isnum;    //valor de campo numerico o alfabetico 
            
    java.util.ArrayList<Campo> registros;

    public FileGenerator(int n_registro, int n_campos) {
        this.n_registro = n_registro;
        this.n_campos = n_campos;
        registros = new java.util.ArrayList<>();
    }
    
    public void generate()
    {
        
    }
    
    private void generate_reg()
    {
        
    }
    
    private void ask()
    {
        
    }
    
    class Campo
    {
        int     l_campo;    //longitud de campo
        boolean numerico;   //campo numerico - alfabetico
        java.util.ArrayList<String> campos = new java.util.ArrayList<>();

        public Campo(int l_campo, boolean numerico) {
            this.l_campo = l_campo;
            this.numerico = numerico;
        }
        
        public String generateCampo()
        {
            //48-57 numericos ASCII
            //97-122 alfabeticos ASCII
            
            int     min = numerico? 48:97,  //minimo de generado random
                    max = numerico? 57:122; //maximo de generado random
            
            char[]  resultado = new char[l_campo];
            
            for (int i = 0; i < l_campo; i++) {
                resultado[i] = (char)Math.floor(Math.random()*(max-min+1)+min); 
            }
            
            return new String(resultado);
        }
    }
    
}
