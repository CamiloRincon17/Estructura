/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.matrices;

import javax.swing.JOptionPane;

public class Matrices {

    int[][] m;
    String[] nombres;

    public boolean solicitarClave() {
        javax.swing.JPasswordField pwd = new javax.swing.JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Ingrese la clave (1234) para modificar:",
                JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            String input = new String(pwd.getPassword());
            if (input.equals("1234")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Clave incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    public boolean datosValidos() {
        if (m == null || nombres == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay datos registrados. Por favor ingrese candidatos y resultados primero.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void crearCandidatos(int f) {
        nombres = new String[f];
        String valor = "";
        for (int i = 0; i < f; i++) {
            valor = (JOptionPane.showInputDialog("Ingrese el nombre del primer Candidato " + (i + 1)));
            if (valor.equals("")) {
                JOptionPane.showMessageDialog(null, "Valor requerido");
                i--;
            } else {
                nombres[i] = valor;
            }
        }
        JOptionPane.showMessageDialog(null, "Targeton lleno");
    }

    public void crear(int f, int c) {
        if (!solicitarClave())
            return;
        if (nombres == null || nombres.length != f) {
            JOptionPane.showMessageDialog(null,
                    "Primero debe ingresar los nombres de los candidatos o la cantidad no coincide.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (f <= 0 || c <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad de filas y mesas debe ser mayor a 0", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        m = new int[f][c];
        for (int fi = 0; fi < f; fi++) {
            for (int ci = 0; ci < c; ci++) {
                boolean valido = false;
                while (!valido) {
                    try {
                        String input = JOptionPane
                                .showInputDialog("Votos para : " + nombres[fi] + "\n en la mesa : " + (ci + 1));
                        if (input == null)
                            return; // Si presiona Cancelar, permite salir
                        m[fi][ci] = Integer.parseInt(input);
                        valido = true;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Entrada inválida. Debe ingresar un número entero.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Resultados registrados correctamente");
    }

    public void consultar() {
        if (!datosValidos())
            return;
        for (int fi = 0; fi < m.length; fi++) {
            for (int ci = 0; ci < m[fi].length; ci++) {
                JOptionPane.showMessageDialog(null,
                        "Candidato: " + nombres[fi] + "\n Mesa : " + (ci + 1) + "\n votos " + m[fi][ci]);
            }
        }

    }

    public void sumaTotalVotos() {
        if (!datosValidos())
            return;
        int sumaTotalVotos = 0;
        for (int fila = 0; fila < m.length; fila++) {
            for (int columna = 0; columna < m[fila].length; columna++) {
                sumaTotalVotos += m[fila][columna];
            }

        }
        JOptionPane.showMessageDialog(null, "La Cantidad Total de votos es: " + sumaTotalVotos);

    }

    public void ganador() {
        if (!datosValidos())
            return;
        int ganadorIndice = 0;
        int maxVotos = 0;
        for (int fila = 0; fila < m.length; fila++) {
            int sumarCandidato = 0;
            for (int columna = 0; columna < m[fila].length; columna++) {
                sumarCandidato += m[fila][columna];
            }
            if (fila == 0 || sumarCandidato > maxVotos) {
                maxVotos = sumarCandidato;
                ganadorIndice = fila;
            }
        }
        JOptionPane.showMessageDialog(null, "El ganador es: " + nombres[ganadorIndice] + " con " + maxVotos + " votos");
    }

    public void ganadorMesaMax() {
        if (!datosValidos())
            return;
        // encontrar al candidato ganador (más votos en total)
        int ganadorIndice = 0;
        int maxVotosTotal = 0;
        for (int fila = 0; fila < m.length; fila++) {
            int sumarCandidato = 0;
            for (int columna = 0; columna < m[fila].length; columna++) {
                sumarCandidato += m[fila][columna];
            }
            if (fila == 0 || sumarCandidato > maxVotosTotal) {
                maxVotosTotal = sumarCandidato;
                ganadorIndice = fila;
            }
        }

        // buscar el max de votos del ganador en una mesa
        int maxVotosMesa = m[ganadorIndice][0];
        for (int columna = 1; columna < m[ganadorIndice].length; columna++) {
            if (m[ganadorIndice][columna] > maxVotosMesa) {
                maxVotosMesa = m[ganadorIndice][columna];
            }
        }

        // recolectar mesas donde el ganador sacó ese max
        String mesas = "";
        for (int columna = 0; columna < m[ganadorIndice].length; columna++) {
            if (m[ganadorIndice][columna] == maxVotosMesa) {
                if (!mesas.equals(""))
                    mesas += ", ";
                mesas += "Mesa " + (columna + 1);
            }
        }

        JOptionPane.showMessageDialog(null,
                "El ganador es: " + nombres[ganadorIndice] +
                        "\nTotal de votos: " + maxVotosTotal +
                        "\nSaco mas votos en: " + mesas + " con " + maxVotosMesa + " votos");
    }

    public void mesaMaxVotos() {
        if (!datosValidos())
            return;
        // sumar todos los votos por mesa
        int numMesas = m[0].length;
        int[] totalPorMesa = new int[numMesas];
        for (int columna = 0; columna < numMesas; columna++) {
            for (int fila = 0; fila < m.length; fila++) {
                totalPorMesa[columna] += m[fila][columna];
            }
        }

        // encontrar el max
        int maxVotos = totalPorMesa[0];
        for (int columna = 1; columna < numMesas; columna++) {
            if (totalPorMesa[columna] > maxVotos) {
                maxVotos = totalPorMesa[columna];
            }
        }

        // recolectar mesas con ese max
        String mesas = "";
        for (int columna = 0; columna < numMesas; columna++) {
            if (totalPorMesa[columna] == maxVotos) {
                if (!mesas.equals(""))
                    mesas += ", ";
                mesas += "Mesa " + (columna + 1);
            }
        }
        JOptionPane.showMessageDialog(null, "La mesa con mas votos es: " + mesas + " con " + maxVotos + " votos");
    }

    public void mesaMenosVotos() {
        if (!datosValidos())
            return;
        // sumar todos los votos por mesa
        int numMesas = m[0].length;
        int[] totalPorMesa = new int[numMesas];
        for (int columna = 0; columna < numMesas; columna++) {
            for (int fila = 0; fila < m.length; fila++) {
                totalPorMesa[columna] += m[fila][columna];
            }
        }

        // encontrar el min
        int minVotos = totalPorMesa[0];
        for (int columna = 1; columna < numMesas; columna++) {
            if (totalPorMesa[columna] < minVotos) {
                minVotos = totalPorMesa[columna];
            }
        }

        // recolectar mesas con ese min
        String mesas = "";
        for (int columna = 0; columna < numMesas; columna++) {
            if (totalPorMesa[columna] == minVotos) {
                if (!mesas.equals(""))
                    mesas += ", ";
                mesas += "Mesa " + (columna + 1);
            }
        }
        JOptionPane.showMessageDialog(null, "La mesa con menos votos es: " + mesas + " con " + minVotos + " votos");
    }

}
