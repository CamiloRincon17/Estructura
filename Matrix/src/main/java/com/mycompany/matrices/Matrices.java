package com.mycompany.matrices;

import javax.swing.JOptionPane;

public class Matrices {

    int[][] m;
    String[] nombres; // nombre de cada candidato
    int numMesas; // número de columnas (mesas)

    public void crearMatriz(int f, int c) {
        numMesas = c;
        m = new int[0][]; // empieza sin candidatos
        nombres = new String[0];
        JOptionPane.showMessageDialog(null,
                "Estructura lista: " + c + " mesa(s).\nUsa 'Agregar candidato' para añadir candidatos.");
    }

    public void agregarCandidato() {
        if (nombres == null) {
            JOptionPane.showMessageDialog(null, "Primero debes crear la estructura (botón Crear).");
            return;
        }

        // Pedir nombre del nuevo candidato
        String nombre = JOptionPane.showInputDialog(
                "Nombre del candidato " + (nombres.length + 1) + ":");
        if (nombre == null)
            return;

        // Pedir votos por mesa
        int[] votos = new int[numMesas];
        for (int ci = 0; ci < numMesas; ci++) {
            String input = JOptionPane.showInputDialog(
                    "Candidato: " + nombre + " - Mesa " + (ci + 1) + ":\nIngrese los votos:");
            if (input == null)
                return;
            votos[ci] = Integer.parseInt(input);
        }

        // Expandir arreglos
        int n = nombres.length;
        String[] nuevosNombres = new String[n + 1];
        int[][] nuevaMatriz = new int[n + 1][];

        for (int i = 0; i < n; i++) {
            nuevosNombres[i] = nombres[i];
            nuevaMatriz[i] = m[i];
        }
        nuevosNombres[n] = nombre;
        nuevaMatriz[n] = votos;

        nombres = nuevosNombres;
        m = nuevaMatriz;

        JOptionPane.showMessageDialog(null,
                "Candidato \"" + nombre + "\" agregado.\nTotal candidatos: " + nombres.length);
    }

    public void consultarMatriz() {
        if (nombres == null || nombres.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay candidatos registrados.");
            return;
        }
        for (int fi = 0; fi < m.length; fi++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Candidato ").append(fi + 1).append(": ").append(nombres[fi]).append("\n");
            for (int ci = 0; ci < m[fi].length; ci++) {
                sb.append("  Mesa ").append(ci + 1).append(": ").append(m[fi][ci]).append(" votos\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }
}
