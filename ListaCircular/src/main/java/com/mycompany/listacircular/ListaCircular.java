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
            inicio = nuevo;

            nuevo.setEnlace(inicio);
            inicio = nuevo;
            ultimo.setEnlace(inicio);
            JOptionPane.showInternalMessageDialog(null, "se ha agregado un nuevo valor" + valor);
        }
    }

  public void consultar() {
    if (inicio == null) {
        JOptionPane.showMessageDialog(null, "Lista vacía");
        return;
    }

    Nodo temporal = inicio;

    do {
        JOptionPane.showMessageDialog(null, "Valor: " + temporal.getValor());
        temporal = temporal.getEnlace(); // 🔥 ESTA LÍNEA ES CLAVE
    } while (temporal != inicio);
}
   public void mostrarPrimeroYUltimo() {
    if (inicio == null) {
        JOptionPane.showMessageDialog(null, "Lista vacía");
    } else {
        JOptionPane.showMessageDialog(null, 
            "Primer valor: " + inicio.getValor() +
            "\nÚltimo valor: " + ultimo.getValor()
        );
    }
}
}
