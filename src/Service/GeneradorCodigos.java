package Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase para generar códigos únicos de ventas y facturas
 * @author Osvaldo Pacheco
 */
public class GeneradorCodigos {
    
    // Contadores para generar números secuenciales únicos
    private static AtomicInteger contadorVentas = new AtomicInteger(1);
    private static AtomicInteger contadorFacturas = new AtomicInteger(1);
    
    /**
     * Genera código de venta en formato VEN001, VEN002, etc.
     */
    public static String generarCodigoVenta() {
        int numero = contadorVentas.getAndIncrement();
        return String.format("VEN%03d", numero);
    }
    
    /**
     * Genera código de factura en formato FACT001, FACT002, etc.
     */
    public static String generarCodigoFactura() {
        int numero = contadorFacturas.getAndIncrement();
        return String.format("FACT%03d", numero);
    }
    
    /**
     * Genera número de venta completo con fecha y hora
     * Ejemplo: VEN001_20251107143022
     */
    public static String generarNumeroVentaCompleto() {
        String codigo = generarCodigoVenta();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return codigo + "_" + timestamp;
    }
    
    /**
     * Genera número de factura completo con fecha y hora
     * Ejemplo: FACT001_20251107143022
     */
    public static String generarNumeroFacturaCompleto() {
        String codigo = generarCodigoFactura();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return codigo + "_" + timestamp;
    }
    
    /**
     * Reinicia los contadores a 1
     * Útil para reiniciar la numeración cuando sea necesario
     */
    public static void reiniciarContadores() {
        contadorVentas.set(1);
        contadorFacturas.set(1);
    }
    
    /**
     * Obtiene el próximo número de venta sin incrementar el contador
     */
    public static int getProximoNumeroVenta() {
        return contadorVentas.get();
    }
    
    /**
     * Obtiene el próximo número de factura sin incrementar el contador
     */
    public static int getProximoNumeroFactura() {
        return contadorFacturas.get();
    }
}