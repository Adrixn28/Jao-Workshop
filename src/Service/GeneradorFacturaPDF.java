package Service;

import Model.Factura;
import Model.Venta;
import Model.ItemVenta;
import Model.Cliente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Desktop;

/**
 * Servicio para generar facturas en formato PDF (HTML)
 * Usa el modelo Factura.java del sistema
 */
public class GeneradorFacturaPDF {
    
    private String directorioFacturas;
    
    public GeneradorFacturaPDF() {
        // Crear directorio de facturas en el workspace
        this.directorioFacturas = System.getProperty("user.dir") + File.separator + "facturas";
        crearDirectorioFacturas();
    }
    
    /**
     * Crear directorio para las facturas si no existe
     */
    private void crearDirectorioFacturas() {
        File directorio = new File(directorioFacturas);
        if (!directorio.exists()) {
            directorio.mkdirs();
            System.out.println("📁 Directorio de facturas creado: " + directorioFacturas);
        }
    }
    
    /**
     * Genera una factura en formato HTML usando el modelo Factura
     * @param factura Objeto Factura del modelo
     * @return Ruta del archivo generado
     */
    public String generarFacturaPDF(Factura factura) {
        try {
            String nombreArchivo = factura.getCodigoFactura() + "_" + 
                                  new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
            String rutaArchivo = directorioFacturas + File.separator + nombreArchivo;
            
            String contenidoHTML = generarContenidoHTML(factura);
            
            // Escribir archivo HTML
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                writer.write(contenidoHTML);
            }
            
            System.out.println("📄 Factura generada: " + rutaArchivo);
            
            // Abrir automáticamente la factura
            abrirArchivo(rutaArchivo);
            
            return rutaArchivo;
            
        } catch (IOException e) {
            System.err.println("❌ Error al generar factura: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Genera el contenido HTML de la factura con diseño sobrio
     */
    private String generarContenidoHTML(Factura factura) {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang='es'>\n");
        html.append("<head>\n");
        html.append("    <meta charset='UTF-8'>\n");
        html.append("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n");
        html.append("    <title>").append(factura.getCodigoFactura()).append(" - JAO Workshop</title>\n");
        html.append("    <style>\n");
        html.append(generarEstilosCSSSobrio());
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        
        // Encabezado empresarial sobrio
        html.append("    <div class='factura'>\n");
        html.append("        <header class='encabezado'>\n");
        html.append("            <div class='empresa'>\n");
        html.append("                <h1>JAO WORKSHOP</h1>\n");
        html.append("                <p>Repuestos y Accesorios Kawasaki</p>\n");
        html.append("                <p>NIT: 900.123.456-7</p>\n");
        html.append("            </div>\n");
        html.append("            <div class='documento'>\n");
        html.append("                <h2>FACTURA DE VENTA</h2>\n");
        html.append("                <table class='info-factura'>\n");
        html.append("                    <tr><td>Factura No.:</td><td><strong>").append(factura.getCodigoFactura()).append("</strong></td></tr>\n");
        html.append("                    <tr><td>Venta No.:</td><td>").append(factura.getCodigoSerial()).append("</td></tr>\n");
        html.append("                    <tr><td>Fecha:</td><td>").append(new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha())).append("</td></tr>\n");
        html.append("                    <tr><td>Hora:</td><td>").append(new SimpleDateFormat("HH:mm").format(factura.getFecha())).append("</td></tr>\n");
        html.append("                </table>\n");
        html.append("            </div>\n");
        html.append("        </header>\n");
        
        // Información del cliente
        html.append("        <section class='cliente'>\n");
        html.append("            <h3>DATOS DEL CLIENTE</h3>\n");
        html.append("            <table class='datos-cliente'>\n");
        html.append("                <tr><td>Nombre:</td><td>").append(factura.getCliente().getPrimerNombre()).append(" ").append(factura.getCliente().getPrimerApellido()).append("</td></tr>\n");
        html.append("                <tr><td>Documento:</td><td>").append(factura.getCliente().getCedula()).append("</td></tr>\n");
        html.append("                <tr><td>Teléfono:</td><td>").append(factura.getCliente().getTelefono()).append("</td></tr>\n");
        html.append("                <tr><td>Email:</td><td>").append(factura.getCliente().getCorreo()).append("</td></tr>\n");
        if (factura.isEsEnvioADomicilio()) {
            html.append("                <tr><td>Dirección:</td><td>").append(factura.getCliente().getDireccion()).append("</td></tr>\n");
        }
        html.append("            </table>\n");
        html.append("        </section>\n");
        
        // Detalles de productos
        html.append("        <section class='productos'>\n");
        html.append("            <table class='tabla-productos'>\n");
        html.append("                <thead>\n");
        html.append("                    <tr>\n");
        html.append("                        <th>Cant.</th>\n");
        html.append("                        <th>Descripción</th>\n");
        html.append("                        <th>Marca</th>\n");
        html.append("                        <th>Valor Unit.</th>\n");
        html.append("                        <th>Total</th>\n");
        html.append("                    </tr>\n");
        html.append("                </thead>\n");
        html.append("                <tbody>\n");
        
        // Items de la factura
        for (ItemVenta item : factura.getListaDetalle()) {
            html.append("                    <tr>\n");
            html.append("                        <td class='cantidad'>").append(item.getCantidad()).append("</td>\n");
            html.append("                        <td class='descripcion'>").append(item.getRespuesto().getNombre()).append("</td>\n");
            html.append("                        <td class='marca'>").append(item.getRespuesto().getMarca()).append("</td>\n");
            html.append("                        <td class='precio'>$").append(String.format("%,.2f", item.getPrecioUnitario())).append("</td>\n");
            html.append("                        <td class='total'>$").append(String.format("%,.2f", item.getSubtotal())).append("</td>\n");
            html.append("                    </tr>\n");
        }
        
        html.append("                </tbody>\n");
        html.append("            </table>\n");
        html.append("        </section>\n");
        
        // Totales y condiciones
        html.append("        <section class='totales'>\n");
        html.append("            <div class='condiciones'>\n");
        html.append("                <h4>CONDICIONES DE VENTA</h4>\n");
        html.append("                <p><strong>Modalidad:</strong> ").append(factura.isEsEnvioADomicilio() ? "Envío a Domicilio" : "Recoger en Tienda").append("</p>\n");
        html.append("                <p><strong>Método de Pago:</strong> ").append(factura.getMetodoPago()).append("</p>\n");
        html.append("                <p><strong>Estado:</strong> ").append(factura.getEstadoTexto()).append("</p>\n");
        html.append("            </div>\n");
        html.append("            <div class='total-general'>\n");
        html.append("                <table class='tabla-total'>\n");
        html.append("                    <tr><td>Subtotal:</td><td>$").append(String.format("%,.2f", factura.getTotal())).append("</td></tr>\n");
        html.append("                    <tr><td>IVA (0%):</td><td>$0.00</td></tr>\n");
        html.append("                    <tr class='total-final'><td><strong>TOTAL A PAGAR:</strong></td><td><strong>$").append(String.format("%,.2f", factura.getTotal())).append("</strong></td></tr>\n");
        html.append("                </table>\n");
        html.append("            </div>\n");
        html.append("        </section>\n");
        
        // Footer
        html.append("        <footer class='pie'>\n");
        html.append("            <p>Gracias por su compra. JAO Workshop - Tu taller de confianza</p>\n");
        html.append("            <p class='impresion'>Para convertir a PDF: Archivo → Imprimir → Guardar como PDF</p>\n");
        html.append("        </footer>\n");
        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>");
        
        return html.toString();
    }
    
    /**
     * Genera los estilos CSS sobrios para la factura
     */
    private String generarEstilosCSSSobrio() {
        return """
            body {
                font-family: 'Courier New', monospace;
                margin: 0;
                padding: 20px;
                background-color: #ffffff;
                color: #000000;
                font-size: 12px;
                line-height: 1.4;
            }
            
            .factura {
                max-width: 210mm;
                margin: 0 auto;
                background: white;
                padding: 20px;
            }
            
            .encabezado {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                border-bottom: 2px solid #000000;
                padding-bottom: 15px;
                margin-bottom: 20px;
            }
            
            .empresa h1 {
                margin: 0;
                font-size: 18px;
                font-weight: bold;
                letter-spacing: 1px;
            }
            
            .empresa p {
                margin: 2px 0;
                font-size: 10px;
            }
            
            .documento {
                text-align: right;
            }
            
            .documento h2 {
                margin: 0 0 10px 0;
                font-size: 16px;
                font-weight: bold;
            }
            
            .info-factura,
            .datos-cliente {
                border-collapse: collapse;
                font-size: 11px;
            }
            
            .info-factura td,
            .datos-cliente td {
                padding: 2px 5px;
                border: none;
            }
            
            .info-factura td:first-child,
            .datos-cliente td:first-child {
                font-weight: bold;
                width: 80px;
            }
            
            .cliente {
                margin-bottom: 20px;
                border: 1px solid #000000;
                padding: 10px;
            }
            
            .cliente h3 {
                margin: 0 0 10px 0;
                font-size: 12px;
                font-weight: bold;
                text-align: center;
                border-bottom: 1px solid #000000;
                padding-bottom: 5px;
            }
            
            .productos {
                margin-bottom: 20px;
            }
            
            .tabla-productos {
                width: 100%;
                border-collapse: collapse;
                font-size: 10px;
            }
            
            .tabla-productos th,
            .tabla-productos td {
                border: 1px solid #000000;
                padding: 6px 4px;
                text-align: left;
            }
            
            .tabla-productos th {
                background-color: #f0f0f0;
                font-weight: bold;
                text-align: center;
            }
            
            .cantidad,
            .precio,
            .total {
                text-align: right;
            }
            
            .descripcion {
                width: 40%;
            }
            
            .marca {
                width: 20%;
                text-align: center;
            }
            
            .cantidad {
                width: 8%;
            }
            
            .precio,
            .total {
                width: 16%;
            }
            
            .totales {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                margin-top: 20px;
            }
            
            .condiciones {
                width: 45%;
                font-size: 10px;
            }
            
            .condiciones h4 {
                margin: 0 0 8px 0;
                font-size: 11px;
                font-weight: bold;
                border-bottom: 1px solid #000000;
                padding-bottom: 3px;
            }
            
            .condiciones p {
                margin: 3px 0;
            }
            
            .total-general {
                width: 45%;
            }
            
            .tabla-total {
                width: 100%;
                border-collapse: collapse;
                font-size: 11px;
            }
            
            .tabla-total td {
                padding: 4px 8px;
                border: 1px solid #000000;
            }
            
            .tabla-total td:first-child {
                font-weight: bold;
                background-color: #f0f0f0;
            }
            
            .tabla-total td:last-child {
                text-align: right;
            }
            
            .total-final td {
                font-weight: bold;
                font-size: 12px;
                background-color: #e0e0e0;
            }
            
            .pie {
                text-align: center;
                margin-top: 30px;
                padding-top: 15px;
                border-top: 1px solid #000000;
                font-size: 10px;
            }
            
            .impresion {
                color: #666666;
                font-size: 9px;
                margin-top: 10px;
            }
            
            @media print {
                body {
                    margin: 0;
                    padding: 0;
                }
                .impresion {
                    display: none;
                }
            }
            """;
    }
    
    /**
     * Abre el archivo generado en el navegador predeterminado
     */
    private void abrirArchivo(String rutaArchivo) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(rutaArchivo));
                System.out.println("🌐 Factura abierta en el navegador");
            }
        } catch (IOException e) {
            System.err.println("❌ No se pudo abrir la factura automáticamente: " + e.getMessage());
        }
    }
}