/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_tools;

/**
 *
 * @author RICARDO
 */
public class CampoRegistro {

    private String          campo;  //valor/texto del campo
    private CampoRegistro   der,    //siguiente campo
                            izq,    //campo anterior
                            down,   //siguiente en cadena de hash
                            up;     //anterior en cadena de hash

    public CampoRegistro(String campo) {
        this.campo = campo;
    }
    
    @Override
    public String toString()
    {
        String r="--> ";
        CampoRegistro actual = getStartPoint();
        while (actual!=null) 
        {
            r+=" "+actual.campo;
            actual=actual.der;
        }
        return r;
    }
    
    public void addBlok(CampoRegistro cp)
    {
        if (this.down==null)
        { 
            this.down = cp;
            cp.up=this;
        }
        else
            this.down.addBlok(cp);
    }
    
    public void addChain(CampoRegistro cp)
    {
        if (this.der==null)
        { 
            this.der = cp;
            cp.izq = this;
        }
        else
            this.der.addChain(cp);
    }
    
    public void delBlock(HashRamen ramen) 
    {
        getStartPoint().delBlock(0,ramen);
    }
    
    private void delBlock(int i, HashRamen ramen)
    {
        if (this.up==null) 
            ramen.delOne(this.campo, i);
        else
        {
            this.up.down=this.down;
            if (this.down!=null) 
                this.down.up=this.up;
        }
        
        if (this.der!=null) {
            this.der.delBlock(++i,ramen);
        }
    }
    
    public void printBlok()
    {
        this.printRegistChain();
        if(this.down!=null)
            this.down.printBlok();
    }
    
    public void printRegistChain() 
    {
        System.out.print("--> ");
        getStartPoint().printNexts();
        System.out.println("");
    }
        
    public void printNexts()
    {
        System.out.print(this.campo);
        if (this.der!=null) 
        {
            System.out.print(",");
            this.der.printNexts();
        }
    }
    
    public CampoRegistro getStartPoint()
    {
        if(this.izq==null)
            return this;
        else
            return this.izq.getStartPoint();
    }
    
    public String getCampo() {
        return campo;
    }

    public CampoRegistro getDer() {
        return der;
    }

    public void setDer(CampoRegistro der) {
        this.der = der;
    }

    public CampoRegistro getIzq() {
        return izq;
    }

    public void setIzq(CampoRegistro izq) {
        this.izq = izq;
    }

    public CampoRegistro getSig() {
        return down;
    }

    public void setSig(CampoRegistro sig) {
        this.down = sig;
    }

    public CampoRegistro getUp() {
        return up;
    }

    public void setUp(CampoRegistro up) {
        this.up = up;
    }

    
    
    
    
}
