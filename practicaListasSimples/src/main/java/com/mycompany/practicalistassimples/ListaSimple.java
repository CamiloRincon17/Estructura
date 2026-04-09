package com.mycompany.practicalistassimples;

/**
 * Clase que maneja una lista simplemente enlazada.
 * Aquí vive la lógica del programa, para que luego la interfaz
 * solo tenga que llamar métodos y no manipular nodos directamente.
 */
public class ListaSimple {

    private Nodo cabeza;
    private Nodo cola;

    /**
     * Inserta un nuevo nodo al inicio.
     * 
     * Pasos:
     * 1. Crear el nodo nuevo.
     * 2. Hacer que el nuevo apunte a la cabeza actual.
     * 3. Mover la cabeza al nuevo nodo.
     * 4. Si la lista estaba vacía, la cola también será ese nodo.
     */
    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;

        if (cola == null) {
            cola = nuevo;
        }
    }

    /**
     * Inserta un nuevo nodo al final.
     * 
     * Si la lista está vacía, cabeza y cola apuntan al nuevo nodo.
     * Si no, la cola actual apunta al nuevo nodo y luego la cola se actualiza.
     */
    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    /**
     * Cuenta cuántos nodos hay en la lista recorriéndola desde la cabeza.
     */
    public int contarNodos() {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        return contador;
    }

    /**
     * Busca un número en la lista.
     * Devuelve true si lo encuentra y false si no.
     */
    public boolean buscar(int dato) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }
    public

    /**
     * Devuelve la lista como texto.
     * Ejemplo: 5 -> 10 -> 20 -> null
     */
    public String mostrarLista() {
        if (cabeza == null) {
            return "La lista está vacía.";
        }

        StringBuilder resultado = new StringBuilder();
        Nodo actual = cabeza;

        while (actual != null) {
            resultado.append(actual.dato).append(" -> ");
            actual = actual.siguiente;
        }

        resultado.append("null");
        return resultado.toString();
    }
}
