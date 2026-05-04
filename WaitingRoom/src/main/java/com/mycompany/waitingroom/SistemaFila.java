package com.mycompany.waitingroom;

import java.util.List;
import java.util.LinkedList;

/**
 * Motor del Sistema de Fila Virtual.
 *
 * Coordina:
 *   • Inserción de usuarios (Lista Principal – doble)
 *   • Proceso de compra (elimina de principal → agrega a Compras)
 *   • Expiración automática  (elimina de principal → agrega a Expirados)
 *   • Actualización de estados (primeros 10 EN ATENCIÓN, resto EN ESPERA)
 *
 * Esta clase es THREAD-SAFE: todos los métodos públicos están
 * sincronizados para poder ser llamados desde el Timer de Swing o el GC.
 */
public class SistemaFila {

    // ── Listas ───────────────────────────────────────────────────────
    private final ListaPrincipal filaPrincipal;
    private final LinkedList<NodoDoble> comprasExitosas;
    private final LinkedList<NodoDoble> usuariosExpirados;

    // ── Config ───────────────────────────────────────────────────────
    private long   ttlMs;            // TTL global para nuevos usuarios
    private int    contadorId;       // Generador de IDs autoincremental

    // ── Estadísticas ─────────────────────────────────────────────────
    private int totalInsertados;
    private int totalCompras;
    private int totalExpirados;

    // ────────────────────────────────────────────────────────────────
    public SistemaFila(long ttlMs) {
        this.filaPrincipal      = new ListaPrincipal();
        this.comprasExitosas    = new LinkedList<>();
        this.usuariosExpirados  = new LinkedList<>();
        this.ttlMs              = ttlMs;
        this.contadorId         = 100;   // IDs comienzan en 101
        this.totalInsertados    = 0;
        this.totalCompras       = 0;
        this.totalExpirados     = 0;
        iniciarGarbageCollector();
    }

    // ── Monitor de Tiempo (Garbage Collector) ────────────────────────
    private void iniciarGarbageCollector() {
        Thread gc = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Revisa cada segundo
                    verificarExpiraciones();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        gc.setDaemon(true);
        gc.start();
    }

    // ── Getters de listas (para la vista) ────────────────────────────
    public synchronized List<NodoDoble>  getFilaPrincipal()     { return filaPrincipal.obtenerTodos(); }
    public synchronized List<NodoDoble> getComprasExitosas()    { return new LinkedList<>(comprasExitosas); }
    public synchronized List<NodoDoble> getUsuariosExpirados()  { return new LinkedList<>(usuariosExpirados); }

    // ── Estadísticas ─────────────────────────────────────────────────
    public synchronized int getTamanioFila()      { return filaPrincipal.getTamanio(); }
    public synchronized int getTotalInsertados()  { return totalInsertados; }
    public synchronized int getTotalCompras()     { return totalCompras; }
    public synchronized int getTotalExpirados()   { return totalExpirados; }
    public synchronized long getTtlMs()           { return ttlMs; }

    // ── Configuración ────────────────────────────────────────────────
    public synchronized void setTtlMs(long ttlMs) { this.ttlMs = ttlMs; }

    // ────────────────────────────────────────────────────────────────
    // OPERACIÓN 1: Insertar usuario
    // ────────────────────────────────────────────────────────────────
    /**
     * Agrega un nuevo usuario al final de la fila con un ID autoincremental.
     * @return El ID asignado al nuevo usuario.
     */
    public synchronized int insertarUsuario() {
        int id = ++contadorId;
        filaPrincipal.insertar(id, ttlMs);
        totalInsertados++;
        return id;
    }

    /**
     * Agrega un nuevo usuario con un ID específico.
     */
    public synchronized void insertarUsuarioConId(int id) {
        filaPrincipal.insertar(id, ttlMs);
        totalInsertados++;
    }

    // ────────────────────────────────────────────────────────────────
    // OPERACIÓN 2: Procesar compra
    // ────────────────────────────────────────────────────────────────
    /**
     * Marca al usuario con el ID dado como comprador:
     *   1. Lo elimina de la Lista Principal.
     *   2. Lo registra en Compras Exitosas.
     *
     * @param idUsuario ID del usuario que realizó la compra.
     * @return true si el usuario fue encontrado y procesado.
     */
    public synchronized boolean procesarCompra(int idUsuario) {
        NodoDoble nodo = filaPrincipal.buscarPorId(idUsuario);
        if (nodo == null) return false;

        filaPrincipal.eliminar(nodo);
        nodo.destruirEnlaces(); // Rompe enlaces y se destruye conceptualmente
        comprasExitosas.add(nodo);
        totalCompras++;
        return true;
    }

    // ────────────────────────────────────────────────────────────────
    // OPERACIÓN 3: Verificar y eliminar usuarios expirados
    // ────────────────────────────────────────────────────────────────
    /**
     * Recorre la fila, detecta los nodos cuyo TTL fue superado,
     * los elimina de la Lista Principal y los pasa a Expirados.
     *
     * @return Cantidad de usuarios expirados en este ciclo.
     */
    public synchronized int verificarExpiraciones() {
        List<NodoDoble> expirados = filaPrincipal.obtenerExpirados();
        for (NodoDoble nodo : expirados) {
            filaPrincipal.eliminar(nodo);
            nodo.destruirEnlaces(); // Rompe sus enlaces
            nodo.setEstado(NodoDoble.EXPIRADO);
            usuariosExpirados.add(nodo);
            totalExpirados++;
            
            // Regla: en los expirados solo almacena los ultimos 10
            while (usuariosExpirados.size() > 10) {
                usuariosExpirados.removeFirst();
            }
        }
        return expirados.size();
    }

    // ────────────────────────────────────────────────────────────────
    // OPERACIÓN 4: Ciclo completo del sistema (un tick)
    // ────────────────────────────────────────────────────────────────
    /**
     * Ejecuta un tick de simulación automático:
     *   1. Inserta 1–3 usuarios nuevos aleatoriamente.
     *   2. El usuario #1 EN ATENCIÓN compra con probabilidad 40%.
     *   3. Verifica expiraciones.
     *
     * @return Resumen del tick para el log.
     */
    public synchronized String ejecutarTick() {
        StringBuilder sb = new StringBuilder();

        // 1. Llegan 1–2 usuarios nuevos
        int nuevos = 1 + (int)(Math.random() * 2);
        sb.append("✚ Nuevos: ");
        for (int i = 0; i < nuevos; i++) {
            int id = insertarUsuario();
            sb.append(id).append(" ");
        }

        // 2. El primer usuario en atención compra con prob. 20%
        NodoDoble primero = filaPrincipal.getCabeza();
        if (primero != null && Math.random() < 0.20) {
            int id = primero.getIdUsuario();
            procesarCompra(id);
            sb.append("| ✔ Compra: ").append(id).append(" ");
        }

        // El GC asíncrono se encarga de verificar las expiraciones
        // No necesitamos hacerlo aquí explícitamente en el tick de simulación

        return sb.toString().trim();
    }

    // ────────────────────────────────────────────────────────────────
    // OPERACIÓN 5: Reiniciar sistema
    // ────────────────────────────────────────────────────────────────
    public synchronized void reiniciar() {
        // Vaciamos la lista principal recorriendo sus nodos
        while (!filaPrincipal.isEmpty()) {
            filaPrincipal.eliminar(filaPrincipal.getCabeza());
        }
        comprasExitosas.clear();
        usuariosExpirados.clear();
        contadorId      = 100;
        totalInsertados = 0;
        totalCompras    = 0;
        totalExpirados  = 0;
    }
}
