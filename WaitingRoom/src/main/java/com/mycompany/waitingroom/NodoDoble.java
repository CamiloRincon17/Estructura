package com.mycompany.waitingroom;

/**
 * Nodo de la Lista Doblemente Enlazada (Fila Principal).
 * Representa un usuario en la sala de espera virtual.
 */
public class NodoDoble {

    // Estados posibles de un usuario
    public static final String EN_ATENCION = "EN ATENCIÓN";
    public static final String EN_ESPERA   = "EN ESPERA";
    public static final String EXPIRADO    = "EXPIRADO";

    // Tiempo de vida máximo por defecto: 30 segundos de simulación
    public static final long TTL_DEFAULT_MS = 30_000L;

    // ── Datos del nodo ──────────────────────────────────────────────
    private final int    idUsuario;
    private final long   timestampIngreso;   // System.currentTimeMillis() al insertar
    private long         timestampAtencion;  // Inicia cuando entra a los primeros 10
    private final long   ttlMs;              // Tiempo máximo de vida en ms
    private       String estado;

    // ── Punteros ────────────────────────────────────────────────────
    NodoDoble anterior;
    NodoDoble siguiente;

    // ────────────────────────────────────────────────────────────────
    public NodoDoble(int idUsuario) {
        this(idUsuario, TTL_DEFAULT_MS);
    }

    public NodoDoble(int idUsuario, long ttlMs) {
        this.idUsuario        = idUsuario;
        this.timestampIngreso = System.currentTimeMillis();
        this.timestampAtencion= 0;
        this.ttlMs            = ttlMs;
        this.estado           = EN_ESPERA;
        this.anterior         = null;
        this.siguiente        = null;
    }

    // ── Getters ─────────────────────────────────────────────────────
    public int    getIdUsuario()        { return idUsuario; }
    public long   getTimestampIngreso() { return timestampIngreso; }
    public long   getTtlMs()            { return ttlMs; }
    public String getEstado()           { return estado; }

    // ── Setters ─────────────────────────────────────────────────────
    public void setEstado(String estado) { 
        this.estado = estado; 
        if (EN_ATENCION.equals(estado) && timestampAtencion == 0) {
            timestampAtencion = System.currentTimeMillis();
        }
    }

    // ── Lógica de expiración ─────────────────────────────────────────
    /**
     * Verifica si el usuario ha superado su TTL en la ventana de decisión.
     */
    public boolean haExpirado() {
        if (!EN_ATENCION.equals(estado)) {
            return false; // Solo expira si está en su ventana de compra (primeros 10)
        }
        return (System.currentTimeMillis() - timestampAtencion) > ttlMs;
    }

    /**
     * Tiempo restante en segundos (puede ser negativo si ya expiró).
     */
    public long segundosRestantes() {
        if (!EN_ATENCION.equals(estado)) {
            return ttlMs / 1000L; // Muestra el total si aún no se activa
        }
        long restMs = ttlMs - (System.currentTimeMillis() - timestampAtencion);
        return restMs / 1000L;
    }

    public void destruirEnlaces() {
        this.anterior = null;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "Usuario " + idUsuario + " [" + estado + "] TTL restante: " + Math.max(0, segundosRestantes()) + "s";
    }
}
