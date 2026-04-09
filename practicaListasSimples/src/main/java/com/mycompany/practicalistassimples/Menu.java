package com.mycompany.practicalistassimples;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Interfaz gráfica básica para trabajar con la lista simple.
 * La ventana no manipula nodos directamente; solo usa los métodos
 * de la clase ListaSimple.
 */
public class Menu extends JFrame {

    private final ListaSimple lista;
    private final JTextField txtDato;
    private final JTextArea areaResultado;

    public Menu() {
        lista = new ListaSimple();

        setTitle("Lista Simple");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblDato = new JLabel("Ingrese un número:");
        txtDato = new JTextField();

        JPanel panelSuperior = new JPanel(new GridLayout(2, 1, 5, 5));
        panelSuperior.add(lblDato);
        panelSuperior.add(txtDato);

        JButton btnInsertarInicio = new JButton("Insertar al inicio");
        JButton btnInsertarFinal = new JButton("Insertar al final");
        JButton btnMostrar = new JButton("Mostrar lista");
        JButton btnContar = new JButton("Contar nodos");
        JButton btnBuscar = new JButton("Buscar dato");
        JButton btnLimpiar = new JButton("Limpiar caja");

        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 8, 8));
        panelBotones.add(btnInsertarInicio);
        panelBotones.add(btnInsertarFinal);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnContar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultado);

        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        panelNorte.add(panelSuperior, BorderLayout.NORTH);
        panelNorte.add(panelBotones, BorderLayout.CENTER);

        add(panelNorte, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Inserta el número al inicio de la lista.
        // Si la caja está vacía o tiene letras, muestra error.
        btnInsertarInicio.addActionListener(e -> {
            Integer dato = leerDato();
            if (dato != null) {
                lista.insertarAlInicio(dato);
                areaResultado.setText("Se insertó " + dato + " al inicio.\n\n" + lista.mostrarLista());
                txtDato.setText("");
            }
        });

        // Inserta el número al final de la lista.
        btnInsertarFinal.addActionListener(e -> {
            Integer dato = leerDato();
            if (dato != null) {
                lista.insertarAlFinal(dato);
                areaResultado.setText("Se insertó " + dato + " al final.\n\n" + lista.mostrarLista());
                txtDato.setText("");
            }
        });

        // Muestra toda la lista tal como esté en ese momento.
        btnMostrar.addActionListener(e -> areaResultado.setText("Lista actual:\n\n" + lista.mostrarLista()));

        // Cuenta cuántos nodos tiene la lista y lo muestra en pantalla.
        btnContar.addActionListener(e -> areaResultado.setText(
                "Lista actual:\n\n" + lista.mostrarLista()
                + "\n\nCantidad de nodos: " + lista.contarNodos()));

        // Busca el número escrito por el usuario dentro de la lista.
        btnBuscar.addActionListener(e -> {
            Integer dato = leerDato();
            if (dato != null) {
                boolean encontrado = lista.buscar(dato);
                if (encontrado) {
                    areaResultado.setText("El dato " + dato + " sí existe en la lista.\n\n" + lista.mostrarLista());
                } else {
                    areaResultado.setText("El dato " + dato + " no existe en la lista.\n\n" + lista.mostrarLista());
                }
            }
        });

        // Limpia la caja de texto para escribir otro número.
        btnLimpiar.addActionListener(e -> {
            txtDato.setText("");
            txtDato.requestFocus();
        });
    }

    /**
     * Lee el valor escrito por el usuario.
     * Devuelve un Integer si todo está bien.
     * Si la caja está vacía o el valor no es numérico, muestra un mensaje y devuelve null.
     */
    private Integer leerDato() {
        String texto = txtDato.getText().trim();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe un número primero.");
            return null;
        }

        try {
            return Integer.valueOf(texto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Solo se permiten números enteros.");
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}
