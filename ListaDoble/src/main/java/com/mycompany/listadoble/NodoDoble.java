package com.mycompany.listadoble;
public class NodoDoble {

    /**
     * @return the atras
     */
    public NodoDoble getAtras() {
        return atras;
    }

    /**
     * @param atras the atras to set
     */
    public void setAtras(NodoDoble atras) {
        this.atras = atras;
    }

    /**
     * @return the adelante
     */
    public NodoDoble getAdelante() {
        return adelante;
    }

    /**
     * @param adelante the adelante to set
     */
    public void setAdelante(NodoDoble adelante) {
        this.adelante = adelante;
    }

    /**
     * @return the dato
     */
    public int getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(int dato) {
        this.dato = dato;
    }
    private NodoDoble atras;
    private NodoDoble adelante;
    private int dato;
    
    
    
    
    
    
    
}
