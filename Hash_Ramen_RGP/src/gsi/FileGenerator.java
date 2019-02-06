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
     
    java.util.Scanner lectura = new java.util.Scanner(System.in);
    
    java.util.ArrayList<Campo> registros;

    public FileGenerator(int n_registro, int n_campos) {
        this.n_registro = n_registro;
        this.n_campos = n_campos;
        registros = new java.util.ArrayList<>();
        longs = new int[n_campos];
        isnum = new boolean[n_campos];
    }
    
    public void generate()
    {
        ask();
        try {
            java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter("data_base.txt"));
            for (int i = 0; i < n_registro; i++) {
                bw.write(generate_reg());
                bw.newLine();
            }
            bw.close();
        } catch (java.io.IOException e){
            System.out.println("error en la escritura del archivo");
            
        }
    }
    
    private String generate_reg()
    {
        String registro ="";
        for (int i = 0; i < n_campos; i++) 
            registro += new Campo(longs[i],isnum[i]).generateCampo()+";";
        return registro.substring(0, registro.length()-1);
    }
    
    private void ask()
    {
        for (int i = 0; i < n_campos; i++) 
        {
            System.out.println("Longitud del campo: "+(i+1));
            longs[i] = lectura.nextInt();
            System.out.println("digite 1 si el campo es numerico, cualquier otro numero si no");
            isnum[i] = lectura.nextInt() == 1;
        }
    }
    
    class Campo
    {
        int     l_campo;    //longitud de campo
        boolean numerico;   //campo numerico - alfabetico
        
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
