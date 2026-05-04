package com.mycompany.waitingroom;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista Doblemente Enlazada – Fila Principal.
 *
 * Garantiza:
 *   • Inserción al final (FIFO).
 *   • Eliminación en cualquier posición en O(1) dado el nodo.
 *   • Actualización automática de estados: los primeros MAX_ACTIVOS
 *     nodos quedan EN ATENCIÓN; el resto EN ESPERA.
 */
public class ListaPrincipal {

    public static final int MAX_ACTIVOS = 10;

    private NodoDoble cabeza;
    private NodoDoble cola;
    private int       tamanio;

    // ────────────────────────────────────────────────────────────────
    public ListaPrincipal() {
        cabeza  = null;
        cola    = null;
        tamanio = 0;
    }

    // ── Tamaño / vacío ───────────────────────────────────────────────
    public int  getTamanio() { return tamanio; }
    public boolean isEmpty() { return tamanio == 0; }

    // ── Cabeza ───────────────────────────────────────────────────────
    public NodoDoble getCabeza() { return cabeza; }

    // ── Inserción al final ───────────────────────────────────────────
    /**
     * Agrega un nuevo usuario al final de la fila.
     */
    public NodoDoble insertar(int idUsuario, long ttlMs) {
        NodoDoble nuevo = new NodoDoble(idUsuario, ttlMs);
        if (cabeza == null) {
            cabeza = nuevo;
            cola   = nuevo;
        } else {
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola           = nuevo;
        }
        tamanio++;
        actualizarEstados();
        return nuevo;
    }

    // ── Eliminación de un nodo ──────────────────────────────────────
    /**
     * Desvincula el nodo dado de la lista en O(1).
     * No requiere recorrido; el nodo guarda sus punteros.
     */
    public void eliminar(NodoDoble nodo) {
        if (nodo == null) return;

        if (nodo.anterior != null) {
            nodo.anterior.siguiente = nodo.siguiente;
        } else {
            // Era la cabeza
            cabeza = nodo.siguiente;
        }

        if (nodo.siguiente != null) {
            nodo.siguiente.anterior = nodo.anterior;
        } else {
            // Era la cola
            cola = nodo.anterior;
        }

        nodo.anterior  = null;
        nodo.siguiente = null;
        tamanio--;
        actualizarEstados();
    }

    // ── Actualización de estados ─────────────────────────────────────
    /**
     * Recorre la lista y asigna:
     *   Posiciones 1–10  → EN ATENCIÓN
     *   Posiciones 11+   → EN ESPERA
     *
     * Se llama automáticamente tras cada inserción o eliminación.
     */
    public void actualizarEstados() {
        NodoDoble actual = cabeza;
        int posicion = 1;
        while (actual != null) {
            if (posicion <= MAX_ACTIVOS) {
                actual.setEstado(NodoDoble.EN_ATENCION);
            } else {
                actual.setEstado(NodoDoble.EN_ESPERA);
            }
            actual = actual.siguiente;
            posicion++;
        }
    }

    // ── Búsqueda por ID ──────────────────────────────────────────────
    /**
     * Devuelve el primer nodo cuyo ID coincida, o null si no existe.
     */
    public NodoDoble buscarPorId(int idUsuario) {
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.getIdUsuario() == idUsuario) return actual;
            actual = actual.siguiente;
        }
        return null;
    }

    // ── Recolección de nodos expirados ───────────────────────────────
    /**
     * Devuelve la lista de nodos que han superado su TTL.
     * No los elimina; la eliminación debe hacerla el caller.
     */
    public List<NodoDoble> obtenerExpirados() {
        List<NodoDoble> expirados = new ArrayList<>();
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.haExpirado()) expirados.add(actual);
            actual = actual.siguiente;
        }
        return expirados;
    }

    // ── Snapshot para la vista ───────────────────────────────────────
    /**
     * Devuelve una lista ordenada de todos los nodos (sin modificar la lista).
     */
    public List<NodoDoble> obtenerTodos() {
        List<NodoDoble> lista = new ArrayList<>();
        NodoDoble actual = cabeza;
        while (actual != null) {
            lista.add(actual);
            actual = actual.siguiente;
        }
        return lista;
    }
}
