package com.mycompany.waitingroom;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada principal.
 * Lanza la interfaz gráfica del Sistema de Fila Virtual.
 */
public class WaitingRoom {

    public static void main(String[] args) {
        // Configura Look & Feel del sistema antes de crear la ventana
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            MenuApp ventana = new MenuApp();
            ventana.setVisible(true);
        });
    }
}
