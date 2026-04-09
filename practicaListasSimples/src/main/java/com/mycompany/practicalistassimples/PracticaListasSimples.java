/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicalistassimples;

import javax.swing.SwingUtilities;

/**
 * Clase principal del proyecto.
 * Desde aquí abrimos la interfaz gráfica.
 * 
 * @author URIEL MAURICIO
 */
public class PracticaListasSimples {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}
