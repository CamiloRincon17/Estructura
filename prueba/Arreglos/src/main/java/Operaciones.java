
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author UFPSO
 */
public class Operaciones {

    int[] edades;

    public void crear(int n) {
        edades = new int[n];
        for (int i = 0; i < n; i++) {
            String valor = JOptionPane.showInputDialog("Edad para posición " + i);
            if (!valor.equals("")) {
                edades[i] = Integer.parseInt(valor);
            } else {
                JOptionPane.showMessageDialog(null, "El valor no puede ser vacio");
                i--;
            }
        }
        JOptionPane.showMessageDialog(null, "Arreglo lleno");

    }

    public void consultar() {

        for (int i = 0; i < edades.length; i++) {
            JOptionPane.showMessageDialog(null, " Valor en la posicion: " + i + " : " + edades[i]);
        }
        JOptionPane.showMessageDialog(null, "No hay mas valores");
    }

    public void buscar() {
        if (edades == null || edades.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo está vacío. Primero debes crear el arreglo.");
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese la posición a buscar (0 - " + (edades.length - 1) + "):");

        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ninguna posición.");
            return;
        }

        int posicion = Integer.parseInt(input);

        if (posicion < 0 || posicion >= edades.length) {
            JOptionPane.showMessageDialog(null,
                    "Posición inválida. El arreglo tiene posiciones de 0 a " + (edades.length - 1));
        } else {
            JOptionPane.showMessageDialog(null, "El valor en la posición " + posicion + " es: " + edades[posicion]);
        }
    }

    public void buscarValor() {
        if (edades == null || edades.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo está vacío. Primero debes crear el arreglo.");
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese el valor a buscar:");

        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ningún valor.");
            return;
        }

        int valorBuscado = Integer.parseInt(input);
        String posicionesEncontradas = "";
        int cantidadEncontrada = 0;

        for (int i = 0; i < edades.length; i++) {
            if (edades[i] == valorBuscado) {
                posicionesEncontradas += "Posición: " + i + "\n";
                cantidadEncontrada++;
            }
        }

        if (cantidadEncontrada == 0) {
            JOptionPane.showMessageDialog(null, "El valor " + valorBuscado + " no se encontró en el arreglo.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "El valor " + valorBuscado + " se encontró " + cantidadEncontrada + " vez/veces en:\n"
                            + posicionesEncontradas);
        }
    }

    public void modificarPosicion() {
        if (edades == null || edades.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo está vacío. Primero debes crear el arreglo.");
            return;
        }

        String input = JOptionPane
                .showInputDialog("Ingrese la posición a modificar (0 - " + (edades.length - 1) + "):");

        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ninguna posición.");
            return;
        }

        int posicion = Integer.parseInt(input);

        if (posicion < 0 || posicion >= edades.length) {
            JOptionPane.showMessageDialog(null,
                    "Posición inválida. El arreglo tiene posiciones de 0 a " + (edades.length - 1));
        } else {
            JOptionPane.showMessageDialog(null,
                    "El valor actual en la posición " + posicion + " es: " + edades[posicion]);

            String modifica = JOptionPane.showInputDialog("Ingrese el nuevo valor para la posición " + posicion + ":");

            if (modifica == null || modifica.equals("")) {
                JOptionPane.showMessageDialog(null, "No ingresaste ningún valor. No se realizó ningún cambio.");
                return;
            }

            int nuevoValor = Integer.parseInt(modifica);
            edades[posicion] = nuevoValor;

            JOptionPane.showMessageDialog(null,
                    "✅ Posición " + posicion + " modificada exitosamente a: " + edades[posicion]);
        }
    }

    public void modificaValor() {
        if (edades == null || edades.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo está vacío. Primero debes crear el arreglo.");
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese el número que desea modificar:");

        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ningún valor.");
            return;
        }

        int valorBuscado = Integer.parseInt(input);

        String posicionesEncontradas = "";
        int cantidadEncontrada = 0;

        for (int i = 0; i < edades.length; i++) {
            if (edades[i] == valorBuscado) {
                posicionesEncontradas += "  → Posición " + i + "\n";
                cantidadEncontrada++;
            }
        }

        if (cantidadEncontrada == 0) {
            JOptionPane.showMessageDialog(null, "El número " + valorBuscado + " no se encontró en el arreglo.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "El número " + valorBuscado + " se encontró " + cantidadEncontrada + " vez/veces en:\n"
                        + posicionesEncontradas);

        String modifica = JOptionPane.showInputDialog(
                "Ingrese el nuevo número con el que desea reemplazar " + valorBuscado + ":");

        if (modifica == null || modifica.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ningún valor. No se realizó ningún cambio.");
            return;
        }

        int nuevoValor = Integer.parseInt(modifica);

        for (int i = 0; i < edades.length; i++) {
            if (edades[i] == valorBuscado) {
                edades[i] = nuevoValor;
            }
        }

        JOptionPane.showMessageDialog(null,
                " Todas las ocurrencias de " + valorBuscado + " fueron reemplazadas por: " + nuevoValor);
    }

    public void eliminar() {
        if (edades == null || edades.length == 0) {
            JOptionPane.showMessageDialog(null, "El arreglo está vacío. No hay nada que eliminar.");
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese la posición a eliminar (0 - " + (edades.length - 1) + "):");

        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "No ingresaste ninguna posición.");
            return;
        }

        int posicion = Integer.parseInt(input);

        if (posicion < 0 || posicion >= edades.length) {
            JOptionPane.showMessageDialog(null,
                    "Posición inválida. El arreglo tiene posiciones de 0 a " + (edades.length - 1));
            return;
        }

        int valorEliminado = edades[posicion];
        JOptionPane.showMessageDialog(null,
                "El valor en la posición " + posicion + " es: " + valorEliminado + "\nSe procederá a eliminarlo.");
        for (int i = posicion; i < edades.length - 1; i++) {
            edades[i] = edades[i + 1];
        }

        int[] nuevoArreglo = new int[edades.length - 1];
        for (int i = 0; i < nuevoArreglo.length; i++) {
            nuevoArreglo[i] = edades[i];
        }
        edades = nuevoArreglo;

        JOptionPane.showMessageDialog(null,
                " El valor " + valorEliminado + " fue eliminado de la posición " + posicion + " exitosamente.\n"
                        + "El arreglo ahora tiene " + edades.length + " elemento(s).");
    }
}
