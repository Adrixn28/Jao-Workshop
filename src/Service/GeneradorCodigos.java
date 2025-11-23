package Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generador de códigos únicos para ventas y facturas
 * Maneja la secuencia de códigos VEN y FACT
 */
public class GeneradorCodigos {
    
    private static AtomicInteger contadorVentas = new AtomicInteger(1);
    private static AtomicInteger contadorFacturas = new AtomicInteger(1);
    
    /**
     * Genera un código único para venta
     * Formato: VEN001, VEN002, etc.
     * @return Código de venta único
     */
    public static String generarCodigoVenta() {
        int numero = contadorVentas.getAndIncrement();
        return String.format("VEN%03d", numero);
    }
    
    /**
     * Genera un código único para factura
     * Formato: FACT001, FACT002, etc.
     * @return Código de factura único
     */
    public static String generarCodigoFactura() {
        int numero = contadorFacturas.getAndIncrement();
        return String.format("FACT%03d", numero);
    }
    
    /**
     * Genera un número de venta con timestamp
     * Formato: VEN001_20251107143022
     * @return Número completo de venta
     */
    public static String generarNumeroVentaCompleto() {
        String codigo = generarCodigoVenta();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return codigo + "_" + timestamp;
    }
    
    /**
     * Genera un número de factura con timestamp
     * Formato: FACT001_20251107143022
     * @return Número completo de factura
     */
    public static String generarNumeroFacturaCompleto() {
        String codigo = generarCodigoFactura();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return codigo + "_" + timestamp;
    }
    
    /**
     * Reinicia los contadores (para testing)
     */
    public static void reiniciarContadores() {
        contadorVentas.set(1);
        contadorFacturas.set(1);
    }
    
    /**
     * Obtiene el próximo número de venta sin incrementar
     * @return Próximo número de venta
     */
    public static int getProximoNumeroVenta() {
        return contadorVentas.get();
    }
    
    /**
     * Obtiene el próximo número de factura sin incrementar
     * @return Próximo número de factura
     */
    public static int getProximoNumeroFactura() {
        return contadorFacturas.get();
    }
}