/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listacircular;

/**
 *
 * @author SCIS2-30
 */
public class Nodo {

    /**
     * @return the enlace
     */
    public Nodo getEnlace() {
        return enlace;
    }
    public void setValor(int valor){
    this.valor = valor;
    }

    /**
     * @param enlace the enlace to set
     */
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }
    
    private Nodo enlace;
    private int valor;

   
    
}
