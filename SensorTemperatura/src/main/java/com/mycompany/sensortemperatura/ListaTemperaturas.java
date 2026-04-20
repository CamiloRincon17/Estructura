package com.mycompany.sensortemperatura;

import java.util.Random;

/**
 * Lista doblemente enlazada para el registro de temperaturas del sensor.
 *
 * Reglas:
 *  - Capacidad máxima: MAX_LECTURAS (20) nodos.
 *  - Al insertar cuando la lista está llena, se elimina automáticamente
 *    el nodo más antiguo (cabeza) antes de insertar el nuevo (cola).
 *  - La cabeza apunta al nodo más antiguo; la cola al más reciente.
 *  - registrarLectura()  → inserta al final (cola).
 *  - alertaPicos()       → recorre hacia atrás desde la cola y verifica
 *                          si las últimas 3 lecturas superan 40 °C.
 *  - getEstadisticas()   → retorna { min, max, promedio }.
 */
public class ListaTemperaturas {

    // ── Constantes ────────────────────────────────────────────────────────
    public static final int MAX_LECTURAS    = 20;
    private static final double RANGO_MIN   = 30.0;
    private static final double RANGO_MAX   = 50.0;
    private static final double UMBRAL_PICO = 40.0;
    private static final int    LECTURAS_PICO = 3;

    // ── Estructura de lista doblemente enlazada ───────────────────────────
    private NodoDoble cabeza; // nodo más antiguo
    private NodoDoble cola;   // nodo más reciente
    private int size;

    // ── Generador aleatorio ───────────────────────────────────────────────
    private final Random random;

    // ─────────────────────────────────────────────────────────────────────
    public ListaTemperaturas() {
        cabeza = null;
        cola   = null;
        size   = 0;
        random = new Random();
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Generación aleatoria
    // ═════════════════════════════════════════════════════════════════════

    /**
     * Genera una temperatura aleatoria entre RANGO_MIN (30) y RANGO_MAX (50)
     * con un decimal de precisión, simulando una lectura del sensor físico.
     *
     * @return valor aleatorio en [30.0, 50.0]
     */
    public double generarLectura() {
        double valor = RANGO_MIN + (RANGO_MAX - RANGO_MIN) * random.nextDouble();
        return Math.round(valor * 10.0) / 10.0; // un decimal
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Registro (inserción al final)
    // ═════════════════════════════════════════════════════════════════════

    /**
     * Añade una nueva lectura al final de la lista (nodo más reciente).
     * Si la lista ya tiene MAX_LECTURAS nodos, elimina automáticamente
     * el más antiguo (cabeza) antes de insertar el nuevo.
     *
     * @param temperatura valor a registrar
     */
    public void registrarLectura(double temperatura) {
        // Control de memoria: eliminar el más antiguo si la lista está llena
        if (size == MAX_LECTURAS) {
            eliminarMasAntiguo();
        }

        // Crear el nuevo nodo
        NodoDoble nuevo = new NodoDoble(temperatura);

        // Insertar al final (cola)
        if (cola == null) {
            // Lista vacía: el nodo es cabeza y cola a la vez
            cabeza = nuevo;
            cola   = nuevo;
        } else {
            nuevo.anterior = cola; // enlace hacia atrás
            cola.siguiente = nuevo; // enlace hacia adelante
            cola = nuevo;           // actualizar cola
        }
        size++;
    }

    /**
     * Elimina el nodo más antiguo (cabeza) de la lista.
     * Actualiza el enlace del nuevo nodo cabeza para que su anterior
     * quede en null.
     */
    private void eliminarMasAntiguo() {
        if (cabeza == null) return;

        if (cabeza == cola) {
            // Solo había un elemento
            cabeza = null;
            cola   = null;
        } else {
            cabeza = cabeza.siguiente; // avanzar cabeza
            cabeza.anterior = null;    // desconectar el nodo eliminado
        }
        size--;
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Alerta de picos (recorrido hacia atrás)
    // ═════════════════════════════════════════════════════════════════════

    /**
     * Recorre la lista desde la cola (más reciente) hacia la cabeza
     * (más antigua) y verifica si las últimas LECTURAS_PICO (3) lecturas
     * superan UMBRAL_PICO (40 °C).
     *
     * @return true si se detecta pico de temperatura
     */
    public boolean alertaPicos() {
        if (size < LECTURAS_PICO) return false;

        NodoDoble actual = cola; // empezar desde el más reciente
        int conteo = 0;

        while (actual != null && conteo < LECTURAS_PICO) {
            if (actual.dato <= UMBRAL_PICO) return false; // alguna no supera el umbral
            actual = actual.anterior; // avanzar hacia atrás
            conteo++;
        }
        return true; // las últimas LECTURAS_PICO superan el umbral
    }


    //  Estadísticas (recorrido hacia adelante)
   

    /*
     * Recorre la lista desde la cabeza hasta la cola calculando
     * el mínimo, el máximo y el promedio de todas las lecturas.
     *
     * @return double[] { min, max, promedio }, o {0,0,0} si la lista está vacía
     */
    public double[] getEstadisticas() {
        if (size == 0) return new double[]{0, 0, 0};

        double min  = Double.MAX_VALUE;
        double max  = -Double.MAX_VALUE;
        double suma = 0;

        NodoDoble actual = cabeza; // empezar desde el más antiguo
        while (actual != null) {
            if (actual.dato < min) min = actual.dato;
            if (actual.dato > max) max = actual.dato;
            suma  += actual.dato;
            actual = actual.siguiente;
        }

        double prom = Math.round((suma / size) * 100.0) / 100.0;
        return new double[]{min, max, prom};
    }

 
    /** @return cantidad de nodos actualmente en la lista */
    public int getSize() { return size; }

    /**
     * Recorre la lista de cabeza a cola y retorna los valores
     * en un arreglo en orden cronológico (más antiguo → más reciente).
     *
     * @return arreglo con los valores almacenados
     */
    public double[] getLecturas() {
        double[] resultado = new double[size];
        NodoDoble actual   = cabeza;
        int i = 0;
        while (actual != null) {
            resultado[i++] = actual.dato;
            actual = actual.siguiente;
        }
        return resultado;
    }

}
