/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sensortemperatura;

import javax.swing.JOptionPane;

/**
 *
 * @author URIEL MAURICIO
 */
public class Menu extends javax.swing.JFrame {

    // ---------------------------------------------------------------
    // Lista doblemente enlazada que guarda las temperaturas del sensor
    // ---------------------------------------------------------------
    private final ListaTemperaturas sensor = new ListaTemperaturas();

    // Timer y cuenta regresiva
    private javax.swing.Timer timer;
    private int segundos = 10;

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents(); 


        // --- Configuración inicial de componentes y datos ---
        areaLecturas.setEditable(false);

        for (int i = 0; i < 5; i++) {
            sensor.registrarLectura(sensor.generarLectura());
        }
        actualizarLista();

        // --- Iniciar Timer de simulacion del sensor ---
        timer = new javax.swing.Timer(1000, e -> {
            segundos--;
            lblContador.setText("Próxima lectura en: " + segundos + " s");

            if (segundos <= 0) {
                segundos = 10;
                double nuevaTemp = sensor.generarLectura();
                sensor.registrarLectura(nuevaTemp);
                actualizarLista();
                verificarAlerta();
            }
        });
        timer.start();

        setLocationRelativeTo(null);
    }

    // ---------------------------------------------------------------
    // Muestra todas las lecturas actuales en el area de texto
    // Recorre la lista de cabeza (mas antiguo) a cola (mas reciente)
    // ---------------------------------------------------------------
    private void actualizarLista() {
        double[] lecturas = sensor.getLecturas();
        StringBuilder sb = new StringBuilder();
        sb.append("Lecturas en memoria (")
          .append(lecturas.length)
          .append(" / ")
          .append(ListaTemperaturas.MAX_LECTURAS)
          .append("):\n");
        sb.append("-".repeat(30)).append("\n");
        for (int i = 0; i < lecturas.length; i++) {
            sb.append(String.format("  [%2d]  %.1f grados C%s%n",
                    i + 1,
                    lecturas[i],
                    lecturas[i] > 40 ? "  *** CALIENTE" : ""));
        }
        if (lecturas.length == 0) {
            sb.append("  (sin lecturas todavia)");
        }
        
        // Mostrar las lecturas en el área de texto
        if (areaLecturas != null) {
            areaLecturas.setText(sb.toString());
            areaLecturas.setCaretPosition(areaLecturas.getDocument().getLength());
        }
    }

    // ---------------------------------------------------------------
    // Recorre la lista hacia atras y verifica si las ultimas 3
    // lecturas superan 40 grados para disparar una alerta
    // ---------------------------------------------------------------
    private void verificarAlerta() {
        if (sensor.alertaPicos()) {
            JOptionPane.showMessageDialog(this,
                    "ALERTA: las ultimas 3 lecturas superan los 40 grados.\n"
                    + "Temperatura critica detectada!",
                    "Alerta de Temperatura", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ---------------------------------------------------------------
    // Boton "Alto y bajo": muestra temperatura maxima y minima
    // ---------------------------------------------------------------
    private void mostrarAltoBajo() {
        if (sensor.getSize() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No hay lecturas registradas todavia.",
                    "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double[] stats = sensor.getEstadisticas(); // [0]=min  [1]=max  [2]=promedio
        JOptionPane.showMessageDialog(this,
                "Temperatura mas alta: " + stats[1] + " grados C\n"
                + "Temperatura mas baja: " + stats[0] + " grados C\n"
                + "(" + sensor.getSize() + " lecturas en memoria)",
                "Alto y Bajo", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------------------------------------------------------
    // Boton "Promedio": muestra el promedio de todas las lecturas
    // ---------------------------------------------------------------
    private void mostrarPromedio() {
        if (sensor.getSize() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No hay lecturas registradas todavia.",
                    "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double[] stats = sensor.getEstadisticas(); // [0]=min  [1]=max  [2]=promedio
        JOptionPane.showMessageDialog(this,
                "Promedio de temperaturas: " + stats[2] + " grados C\n"
                + "(calculado sobre " + sensor.getSize() + " lecturas)",
                "Promedio", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AltoBajo = new javax.swing.JButton();
        promedio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaLecturas = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblContador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AltoBajo.setText("Alto y bajo");
        AltoBajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AltoBajoActionPerformed(evt);
            }
        });

        promedio.setText("Promedio");
        promedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promedioActionPerformed(evt);
            }
        });

        areaLecturas.setColumns(20);
        areaLecturas.setRows(5);
        jScrollPane1.setViewportView(areaLecturas);

        lblContador.setText("\"Próxima lectura en: 10 s\"");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(promedio)
                            .addComponent(AltoBajo)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblContador, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(AltoBajo)
                .addGap(18, 18, 18)
                .addComponent(promedio)
                .addGap(15, 15, 15)
                .addComponent(lblContador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void promedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promedioActionPerformed
        // Llamar a la lógica que ya tenemos programada
        mostrarPromedio();
    }//GEN-LAST:event_promedioActionPerformed

    private void AltoBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AltoBajoActionPerformed
        // Llamar a la lógica que ya tenemos programada
        mostrarAltoBajo();
    }//GEN-LAST:event_AltoBajoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AltoBajo;
    private javax.swing.JTextArea areaLecturas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblContador;
    private javax.swing.JButton promedio;
    // End of variables declaration//GEN-END:variables
}
