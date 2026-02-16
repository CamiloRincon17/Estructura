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

    public static double suma(double a, double b) {
        return a + b;
    }

    public static double resta(double a, double b) {
        return a - b;
    }

    public static double multiplicacion(double a, double b) {
        return a * b;
    }

    public static double divicion(double a, double b) {
        if (b == 0) {
            System.out.println("tienes que ingresar un numero valido");
            b = b;
        }
        return a / b;

    }

    public static double porcentaje(double a, double b) {
        // Calcula el b% de a
        return (a * b) / 100;
    }

    public static double potencia(double a) {
        // Eleva al cuadrado
        return a * a;
    }

    public static double raizCuadrada(double a) {
        // Calcula la raíz cuadrada
        if (a < 0) {
            System.out.println("No se puede calcular raíz de número negativo");
            return 0;
        }
        return Math.sqrt(a);
    }

    public static double cambiarSigno(double a) {
        // Cambia el signo del número
        return -a;
    }
    public static double calcularSen(double a, double b){
        double suma= a + b;
        double resultado= Math.sin(suma);
        
        return resultado;
    }
    public static double calcularCos(double a, double b){
        double suma= a + b;
        double resultado= Math.cos(suma);
        
        return resultado;
    }
    public static double calcularLog(double a, double b){
       double suma= a + b;

        
    return Math.log10(suma);
    }
}
