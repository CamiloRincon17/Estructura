/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.array;
import javax.swing.JOptionPane;

/**
 *
 * @author SCIS2-19
 */
public class Array {
    int[] edades;
    public void crearArreglo(int n){
       edades = new int[n];
       for(int i =0; i<n; i++){
       edades[i]=Integer.parseInt(JOptionPane.showInputDialog("ingresar valor para la posicion "+i));
       }
       JOptionPane.showMessageDialog(null,"Arreglo lleno");
       
    }
    public void consultar(int n){
    for(int i =0; i<n; i++){
        if(n>0){
           JOptionPane.showMessageDialog(null,edades[i]);
           
        }else{
               JOptionPane.showMessageDialog(null,"Arreglo llenooo");

        }
       }
    }
}
