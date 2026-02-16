/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadoraplus;

/**
 *
 * @author URIEL MAURICIO
 */
public class Operaciones {
    
    public static double suma(double a, double b){
        return a + b;
    }
        public static double resta(double a, double b){
        return a - b;
    }
        public static double multiplicacion(double a, double b){
        return a * b;
    }
        public static double divicion(double a, double b){
            if(b==0){
                System.out.println("tienes que ingresar un numero valido");
                b=b;
            }
        return a / b;

    }
}
