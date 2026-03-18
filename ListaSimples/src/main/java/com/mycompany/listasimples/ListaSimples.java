/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.listasimples;

import javax.swing.JOptionPane;

/**
 *
 * @author SCIS2-01
 */
public class ListaSimples {

    Nodo inicio = null;

    public void insetarInicio(int edad, String nombre) {
        Nodo nuevo = new Nodo();
        nuevo.setEdad(edad);
        nuevo.setNombre(nombre);
        nuevo.setEnlace(null);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            nuevo.setEnlace(inicio);
            inicio = nuevo;
        }

    }

    public void imprimir() {
        Nodo temporal = inicio;
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "lista vacia");
        } else {
            while (temporal != null) {
            JOptionPane.showMessageDialog(null,
            "Edad :" + temporal.getEdad() + " Nombre : " + temporal.getNombre());
                temporal = temporal.getEnlace();
            }// ciera el ciclo
        }// ciera el else

        JOptionPane.showMessageDialog(null, "Fin de la lista");
    }// ciera el metodo imprimir
    
    public void insertarFinal(int edad, String nombre) {
        Nodo temporal = inicio;
        Nodo nuevo = new Nodo();
        nuevo.setEdad(edad);
        nuevo.setNombre(nombre);
        nuevo.setEnlace(null);
        if (inicio == null) {
            inicio=nuevo;
        } else {
            while (temporal.getEnlace() != null) {
            temporal = temporal.getEnlace();
            }
            temporal.setEnlace(nuevo);
        }
   }

    public void insertarAntes(int edadRef, int edad, String nombre) {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "Lista vacía, no se puede insertar antes.");
            return;
        }

        if (inicio.getEdad() == edadRef) {
            Nodo nuevo = new Nodo();
            nuevo.setEdad(edad);
            nuevo.setNombre(nombre);
            nuevo.setEnlace(inicio);
            inicio = nuevo;
            JOptionPane.showMessageDialog(null, "Nodo insertado antes del nodo con edad " + edadRef);
            return;
        }

        Nodo anterior = inicio;
        Nodo actual  = inicio.getEnlace();
        while (actual != null) {
            if (actual.getEdad() == edadRef) {
                Nodo nuevo = new Nodo();
                nuevo.setEdad(edad);
                nuevo.setNombre(nombre);
                nuevo.setEnlace(actual);
                anterior.setEnlace(nuevo);
                JOptionPane.showMessageDialog(null, "Nodo insertado antes del nodo con edad " + edadRef);
                return;
            }
            anterior = actual;
            actual   = actual.getEnlace();
        }

        JOptionPane.showMessageDialog(null, "No se encontró un nodo con edad " + edadRef);
    }

   
    public void insertarDespues(int edadRef, int edad, String nombre) {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "Lista vacía, no se puede insertar después.");
            return;
        }

        Nodo temporal = inicio;
        while (temporal != null) {
            if (temporal.getEdad() == edadRef) {
                Nodo nuevo = new Nodo();
                nuevo.setEdad(edad);
                nuevo.setNombre(nombre);
                nuevo.setEnlace(temporal.getEnlace()); // conecta al siguiente existente
                temporal.setEnlace(nuevo);             // el nodo de referencia apunta al nuevo
                JOptionPane.showMessageDialog(null, "Nodo insertado después del nodo con edad " + edadRef);
                return;
            }
            temporal = temporal.getEnlace();
        }

        JOptionPane.showMessageDialog(null, "No se encontró un nodo con edad " + edadRef);
    }

}

