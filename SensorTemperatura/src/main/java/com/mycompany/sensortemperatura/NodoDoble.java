package com.mycompany.sensortemperatura;

public class NodoDoble {

    public double dato;

    public NodoDoble siguiente;

    public NodoDoble anterior;

    public double getDato() {
        return dato;
    }

    public void setDato(double dato) {
        this.dato = dato;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble(double dato) {
        this.dato      = dato;
        this.siguiente = null;
        this.anterior  = null;
    }
}
