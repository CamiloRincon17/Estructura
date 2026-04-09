/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.listacircular;

import javax.swing.JOptionPane;

/**
 *
 * @author SCIS2-30
 */
public class ListaCircular {

    Nodo inicio;
    Nodo ultimo;

    ListaCircular() {
        inicio = null;
        ultimo = null;
    }

    public void crearLista(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setEnlace(nuevo);
        nuevo.setValor(valor);
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "Lista Vacia");
            inicio = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setEnlace(inicio); // el nuevo nodo apunta al antiguo inicio
            inicio = nuevo;          // ahora inicio es el nuevo nodo
            ultimo.setEnlace(inicio); // el último apunta al nuevo inicio (mantiene circularidad)
        }
    }//final del metodo crear lista

    public void consultar() {
        Nodo temporal = inicio;
        if (temporal == null) {
            JOptionPane.showMessageDialog(null, "Lista vacia");
        } else {
            do {
                if (inicio == ultimo) {
                    JOptionPane.showMessageDialog(null,
                            "Valor: " + temporal.getValor());
                    break;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Valor: " + temporal.getValor());
                    temporal = temporal.getEnlace();
                }
            } while (temporal != inicio);
        }
    } // finatl del metodo consultar

    public void mostrarPrimeroYUltimo() {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "Lista vacía");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Primer valor: " + inicio.getValor()
                    + "\nÚltimo valor: " + ultimo.getValor()
            );
        }
    } //final del metodo primero y ultimo

    public void AgregarAlFinal(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (inicio == null) {
            // Lista vacía: el nuevo nodo es inicio y ultimo
            inicio = nuevo;
            ultimo = nuevo;
            nuevo.setEnlace(inicio); // apunta a sí mismo (circular)
        } else {
            ultimo.setEnlace(nuevo); // el antiguo último apunta al nuevo nodo
            nuevo.setEnlace(inicio); // el nuevo nodo apunta al inicio (circular)
            ultimo = nuevo;          // actualizamos la referencia de último
        }
    } //final del metodo agregar al final
    public void AgregarDespuesDe(int valor, int valorDeseado) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "Lista Vacía");
        } else {
            Nodo temporal = inicio;
            boolean encontrado = false;
            do {
                if (temporal.getValor() == valorDeseado) {
                    nuevo.setEnlace(temporal.getEnlace());
                    temporal.setEnlace(nuevo);
                    // Si insertamos después del último, actualizar ultimo
                    if (temporal == ultimo) {
                        ultimo = nuevo;
                    }
                    encontrado = true;
                    break;
                } else {
                    temporal = temporal.getEnlace();
                }
            } while (temporal != inicio);
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "El valor " + valorDeseado + " no se encontró en la lista.");
            }
        }
    }

    public void AgregarAlaMitad(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (inicio == null) {
            inicio = nuevo;
            ultimo = nuevo;
            nuevo.setEnlace(inicio);
        } else {
            // 1. Contar cuántos nodos tiene la lista
            int contador = 0;
            Nodo temporal = inicio;
            do {
                contador++;
                temporal = temporal.getEnlace();
            } while (temporal != inicio);

            // 2. Calcular la posición de la mitad
            int mitad = contador / 2;

            // 3. Avanzar hasta el nodo en la posición (mitad - 1)
            //    para insertar el nuevo nodo después de él
            temporal = inicio;
            for (int i = 1; i < mitad; i++) {
                temporal = temporal.getEnlace();
            }

            // 4. Insertar el nuevo nodo después de temporal
            nuevo.setEnlace(temporal.getEnlace());
            temporal.setEnlace(nuevo);

            // Si la lista tenía un solo nodo, actualizar ultimo
            if (inicio == ultimo) {
                ultimo = nuevo;
                ultimo.setEnlace(inicio);
            }
        }
    }//final del metodo agregar a la mitad

    public String mostrarLista() {
        if (inicio == null) {
            return "Lista vacía";
        }
        StringBuilder sb = new StringBuilder();
        Nodo temporal = inicio;
        do {
            sb.append(temporal.getValor());
            sb.append(" → ");
            temporal = temporal.getEnlace();
        } while (temporal != inicio);
        sb.append("(inicio: ").append(inicio.getValor()).append(")");
        return sb.toString();
    }//final del metodo mostrar lista
}
