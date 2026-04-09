/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicalistassimples;

/**
 * Representa un nodo de la lista simple.
 * Cada nodo guarda un dato y una referencia al siguiente nodo.
 * 
 * @author URIEL MAURICIO
 */
public class Nodo {

    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
