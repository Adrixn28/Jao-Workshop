/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Repuesto;
import Model.ItemCarrito;
import Model.Carrito;
import Service.GestorCarrito;
import Service.ClienteService;
import Service.LoginService;
import Service.GestorStock;
import Service.GeneradorFacturaPDF;
import Service.GeneradorCodigos;
import Service.AdministradorService;
import Model.Venta;
import Model.Factura;
import Model.ItemVenta;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Frame de ventas para recepcionista - Sistema de punto de venta
 * Permite buscar clientes registrados o ingresar datos manualmente
 * Diseño: 1200x810 píxeles
 * @author Osvaldo
 */
public class VentaRecepcionista extends javax.swing.JFrame {
    
    // Datos de ejemplo (en el futuro se conectará a un servicio real)
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnVerCarrito;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboOrdenamiento;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCedulaCliente;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblOrden;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuarioLogueado;
    private javax.swing.JPanel panelBusquedaCliente;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRepuestos;
    private javax.swing.JScrollPane scrollRepuestos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
    
    // Datos
    private java.util.List<Repuesto> repuestosOriginales;
    private java.util.List<Repuesto> repuestosFiltrados;
    
    // Servicios
    private ClienteService clienteService;
    private LoginService loginService;
    private GestorStock gestorStock;
    private GeneradorFacturaPDF generadorPDF;
    
    // Variables del carrito
    private GestorCarrito gestorCarrito;
    private String cedulaClienteVenta; // Cédula del cliente para la venta actual
    private String nombreClienteVenta; // Nombre del cliente para la venta actual
    private boolean clienteRegistrado; // Si el cliente está registrado en el sistema
    private Model.Cliente clienteActual; // Información completa si el cliente está registrado
    private AdministradorService administradorService; // Para buscar clientes
    private JDialog dialogCarrito; // Ventana flotante para el carrito
    private JPanel panelCarrito;
    private JScrollPane scrollCarrito;
    private JLabel lblTotalCarrito;
    private JButton btnProcederPago;
    private JButton btnLimpiarCarrito;
    private boolean carritoVisible = false;
    
    // Categorías disponibles
    private final String[] CATEGORIAS = {
        "Todas las categorias",
        "Motor",
        "Transmision", 
        "Frenos",
        "Electrico",
        "Suspension",
        "Carroceria",
        "Lubricantes"
    };
    
    // Opciones de ordenamiento
    private final String[] ORDENAMIENTO = {
        "Nombre (A-Z)",
        "Nombre (Z-A)",
        "Precio (Menor a Mayor)",
        "Precio (Mayor a Menor)",
        "Stock (Menor a Mayor)",
        "Stock (Mayor a Menor)"
    };
    
    /**
     * Constructor principal
     */
    public VentaRecepcionista() {
        initComponents();
        inicializarServicios();
        cargarUsuarioLogueado();
        cargarRepuestos();
        configurarEventos();
        configurarEventosCliente();
    }
    
    /**
     * Inicializar datos necesarios
     */
    private void inicializarServicios() {
        repuestosOriginales = new ArrayList<>();
        repuestosFiltrados = new ArrayList<>();
        
        // Inicializar servicios
        clienteService = new ClienteService();
        loginService = new LoginService();
        gestorStock = new GestorStock();
        generadorPDF = new GeneradorFacturaPDF();
        administradorService = new AdministradorService(loginService);
        
        // Inicializar carrito
        gestorCarrito = new GestorCarrito();
        
        // Inicializar variables de cliente (vacías hasta que se busque)
        cedulaClienteVenta = "";
        nombreClienteVenta = "";
        clienteRegistrado = false;
        clienteActual = null;
        
        // Inicializar componentes del carrito
        inicializarCarrito();
    }
    
    /**
     * Cargar el nombre del usuario logueado desde SesionUsuarioActual
     */
    private void cargarUsuarioLogueado() {
        try {
            // Usar SesionUsuarioActual para obtener información de la sesión
            Percistencia.SesionUsuarioActual sesion = Percistencia.SesionUsuarioActual.getInstance();
            
            if (sesion.haySesionActiva()) {
                // Crear LoginService para obtener información completa del usuario
                Service.LoginService loginService = new Service.LoginService();
                String nombreCompleto = loginService.getNombreCompletoUsuarioActual();
                String rolUsuario = sesion.getRolUsuarioActual();
                
                establecerUsuarioLogueado(nombreCompleto + " (" + rolUsuario + ")");
            } else {
                establecerUsuarioLogueado("Cliente Demo");
            }
        } catch (Exception e) {
            // Si hay algún error, usar valor por defecto
            System.err.println("Error al cargar usuario logueado: " + e.getMessage());
            establecerUsuarioLogueado("Cliente Demo");
        }
    }
    
    /**
     * Establecer el nombre del usuario en el label
     */
    private void establecerUsuarioLogueado(String nombreUsuario) {
        if (nombreUsuario != null && !nombreUsuario.trim().isEmpty()) {
            lblUsuarioLogueado.setText("Usuario: " + nombreUsuario);
        } else {
            lblUsuarioLogueado.setText("👤 Usuario: No identificado");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblUsuarioLogueado = new javax.swing.JLabel();
        panelBusquedaCliente = new javax.swing.JPanel();
        lblCedulaCliente = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        lblNombreCliente = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        panelFiltros = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblOrden = new javax.swing.JLabel();
        cboOrdenamiento = new javax.swing.JComboBox<>();
        btnLimpiarFiltros = new javax.swing.JButton();
        btnVerCarrito = new javax.swing.JButton();
        lblResultados = new javax.swing.JLabel();
        scrollRepuestos = new javax.swing.JScrollPane();
        panelRepuestos = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();

        panelPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1200, 810));

        lblTitulo.setBackground(new java.awt.Color(0, 153, 0));
        lblTitulo.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("SISTEMA DE VENTAS - RECEPCIONISTA - JAO WORKSHOP");
        lblTitulo.setOpaque(true);

        lblUsuarioLogueado.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        lblUsuarioLogueado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogueado.setText("Usuario: Cargando...");

        panelBusquedaCliente.setBackground(new java.awt.Color(0, 0, 0));
        panelBusquedaCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 153, 0), 2), "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("JetBrains Mono ExtraBold", 1, 14), new java.awt.Color(204, 153, 0))); // NOI18N

        lblCedulaCliente.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblCedulaCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaCliente.setText("Cédula Cliente:");

        txtCedulaCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtCedulaCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        txtCedulaCliente.setForeground(new java.awt.Color(0, 0, 0));

        btnBuscarCliente.setBackground(new java.awt.Color(204, 153, 0));
        btnBuscarCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 13)); // NOI18N
        btnBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.setBorder(null);
        btnBuscarCliente.setFocusPainted(false);

        lblNombreCliente.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreCliente.setText("Nombre Cliente:");

        txtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelBusquedaClienteLayout = new javax.swing.GroupLayout(panelBusquedaCliente);
        panelBusquedaCliente.setLayout(panelBusquedaClienteLayout);
        panelBusquedaClienteLayout.setHorizontalGroup(
            panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaClienteLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        panelBusquedaClienteLayout.setVerticalGroup(
            panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaClienteLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBusquedaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        panelFiltros.setBackground(new java.awt.Color(0, 0, 0));
        panelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "FILTROS Y BUSQUEDA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("JetBrains Mono ExtraBold", 1, 14), new java.awt.Color(0, 153, 0))); // NOI18N

        lblBuscar.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscar.setText("Buscar:");

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(0, 0, 0));

        lblCategoria.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria.setText("Categoria:");

        cboCategoria.setBackground(new java.awt.Color(255, 255, 255));
        cboCategoria.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las categorias", "Motor", "Transmision", "Frenos", "Electrico", "Suspension", "Carroceria", "Lubricantes" }));

        lblOrden.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblOrden.setForeground(new java.awt.Color(255, 255, 255));
        lblOrden.setText("Ordenar:");

        cboOrdenamiento.setBackground(new java.awt.Color(255, 255, 255));
        cboOrdenamiento.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboOrdenamiento.setForeground(new java.awt.Color(0, 0, 0));
        cboOrdenamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre (A-Z)", "Nombre (Z-A)", "Precio (Menor a Mayor)", "Precio (Mayor a Menor)", "Stock (Menor a Mayor)", "Stock (Mayor a Menor)" }));

        btnLimpiarFiltros.setBackground(new java.awt.Color(0, 153, 0));
        btnLimpiarFiltros.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        btnLimpiarFiltros.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltros.setText("Limpiar");
        btnLimpiarFiltros.setBorder(null);
        btnLimpiarFiltros.setFocusPainted(false);

        btnVerCarrito.setBackground(new java.awt.Color(204, 51, 0));
        btnVerCarrito.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        btnVerCarrito.setForeground(new java.awt.Color(255, 255, 255));
        btnVerCarrito.setText("Carrito (0)");
        btnVerCarrito.setBorder(null);
        btnVerCarrito.setFocusPainted(false);

        lblResultados.setFont(new java.awt.Font("JetBrains Mono", 1, 13)); // NOI18N
        lblResultados.setForeground(new java.awt.Color(0, 153, 0));
        lblResultados.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResultados.setText("0 repuestos");

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addComponent(cboOrdenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnVerCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboOrdenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        scrollRepuestos.setBackground(new java.awt.Color(0, 0, 0));
        scrollRepuestos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "REPUESTOS DISPONIBLES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("JetBrains Mono ExtraBold", 1, 14), new java.awt.Color(0, 153, 0))); // NOI18N

        panelRepuestos.setBackground(new java.awt.Color(0, 0, 0));
        panelRepuestos.setLayout(new java.awt.GridBagLayout());
        scrollRepuestos.setViewportView(panelRepuestos);

        btnCerrarSesion.setBackground(new java.awt.Color(220, 53, 69));
        btnCerrarSesion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Volver");
        btnCerrarSesion.setFocusPainted(false);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblUsuarioLogueado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(scrollRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAO Workshop - Sistema de Ventas - Recepcionista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    /**
     * Cargar repuestos desde el servicio
     */
    private void cargarRepuestos() {
        repuestosOriginales.clear();
        
        // Cargar repuestos desde el servicio
        repuestosOriginales = clienteService.obtenerTodosLosRepuestos();
        
        aplicarFiltrosYMostrar();
    }
    

    
    /**
     * Configurar eventos del panel de búsqueda de cliente
     */
    private void configurarEventosCliente() {
        // Evento del botón buscar
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        
        // Búsqueda con Enter en campo cédula
        txtCedulaCliente.addActionListener(e -> buscarCliente());
    }
    
    /**
     * Buscar cliente por cédula
     */
    private void buscarCliente() {
        String cedula = txtCedulaCliente.getText().trim();
        
        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese una cédula", 
                "Búsqueda de Cliente", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Buscar cliente en el sistema
        Model.Cliente cliente = administradorService.buscarClientePorCedula(cedula);
        
        if (cliente != null) {
            // Cliente encontrado - cargar datos automáticamente
            clienteRegistrado = true;
            cedulaClienteVenta = cliente.getCedula();
            nombreClienteVenta = cliente.getPrimerNombre() + " " + cliente.getPrimerApellido();
            txtNombreCliente.setText(nombreClienteVenta);
            txtNombreCliente.setEditable(false);
            txtNombreCliente.setBackground(new Color(200, 255, 200)); // Verde claro
            
            JOptionPane.showMessageDialog(this, 
                "✅ Cliente encontrado:\n" + nombreClienteVenta, 
                "Cliente Registrado", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Cliente NO encontrado - permitir ingreso manual
            clienteRegistrado = false;
            cedulaClienteVenta = cedula;
            txtNombreCliente.setText("");
            txtNombreCliente.setEditable(true);
            txtNombreCliente.setBackground(Color.WHITE);
            txtNombreCliente.requestFocus();
            
            JOptionPane.showMessageDialog(this, 
                "⚠ Cliente no registrado.\nPor favor ingrese el nombre del cliente manualmente.", 
                "Cliente Nuevo", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Validar datos del cliente antes de procesar venta
     */
    private boolean validarDatosCliente() {
        if (cedulaClienteVenta == null || cedulaClienteVenta.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor busque o ingrese la cédula del cliente", 
                "Datos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            txtCedulaCliente.requestFocus();
            return false;
        }
        
        nombreClienteVenta = txtNombreCliente.getText().trim();
        if (nombreClienteVenta.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese el nombre del cliente", 
                "Datos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            txtNombreCliente.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Aplicar filtros y mostrar repuestos
     */
    private void aplicarFiltrosYMostrar() {
        // Limpiar lista filtrada
        repuestosFiltrados.clear();
        
        String busqueda = txtBuscar.getText().toLowerCase().trim();
        String categoriaSeleccionada = (String) cboCategoria.getSelectedItem();
        
        // Aplicar filtros
        for (Repuesto repuesto : repuestosOriginales) {
            boolean cumpleFiltros = true;
            
            // Filtro por búsqueda de nombre
            if (!busqueda.isEmpty() && !repuesto.getNombre().toLowerCase().contains(busqueda)) {
                cumpleFiltros = false;
            }
            
            // Filtro por categoría
            if (!categoriaSeleccionada.equals("Todas las categorias") && 
                !repuesto.getCategoria().equals(categoriaSeleccionada)) {
                cumpleFiltros = false;
            }
            
            if (cumpleFiltros) {
                repuestosFiltrados.add(repuesto);
            }
        }
        
        // Aplicar ordenamiento
        aplicarOrdenamiento();
        
        // Mostrar repuestos
        mostrarRepuestos();
        
        // Actualizar contador - Más compacto
        lblResultados.setText(repuestosFiltrados.size() + " repuestos");
    }
    
    /**
     * Aplicar ordenamiento a la lista filtrada
     */
    private void aplicarOrdenamiento() {
        String ordenSeleccionado = (String) cboOrdenamiento.getSelectedItem();
        
        switch (ordenSeleccionado) {
            case "Nombre (A-Z)":
                repuestosFiltrados.sort((r1, r2) -> r1.getNombre().compareToIgnoreCase(r2.getNombre()));
                break;
            case "Nombre (Z-A)":
                repuestosFiltrados.sort((r1, r2) -> r2.getNombre().compareToIgnoreCase(r1.getNombre()));
                break;
            case "Precio (Menor a Mayor)":
                repuestosFiltrados.sort((r1, r2) -> Double.compare(r1.getPrecio(), r2.getPrecio()));
                break;
            case "Precio (Mayor a Menor)":
                repuestosFiltrados.sort((r1, r2) -> Double.compare(r2.getPrecio(), r1.getPrecio()));
                break;
            case "Stock (Menor a Mayor)":
                repuestosFiltrados.sort((r1, r2) -> Integer.compare(r1.getStock(), r2.getStock()));
                break;
            case "Stock (Mayor a Menor)":
                repuestosFiltrados.sort((r1, r2) -> Integer.compare(r2.getStock(), r1.getStock()));
                break;
        }
    }
    
    /**
     * Mostrar repuestos en el panel con diseño de cards
     */
    private void mostrarRepuestos() {
        panelRepuestos.removeAll();
        
        if (repuestosFiltrados.isEmpty()) {
            // Mostrar mensaje cuando no hay resultados
            JLabel lblSinResultados = new JLabel("No se encontraron repuestos con los filtros aplicados");
            lblSinResultados.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
            lblSinResultados.setForeground(new Color(200, 200, 200));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(50, 20, 50, 20);
            panelRepuestos.add(lblSinResultados, gbc);
        } else {
            // Configurar layout para cards en grid con scroll vertical
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.anchor = GridBagConstraints.NORTHWEST;
            
            int col = 0;
            int row = 0;
            int maxCols = 4; // 4 cards por fila para aprovechar el ancho
            
            for (Repuesto repuesto : repuestosFiltrados) {
                JPanel cardRepuesto = crearCardRepuesto(repuesto);
                
                gbc.gridx = col;
                gbc.gridy = row;
                gbc.weightx = 0.25; // Distribuir equitativamente el ancho
                gbc.weighty = 0.0;
                panelRepuestos.add(cardRepuesto, gbc);
                
                col++;
                if (col >= maxCols) {
                    col = 0;
                    row++;
                }
            }
            
            // Agregar componente invisible para permitir scroll vertical
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            gbc.gridwidth = maxCols;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            panelRepuestos.add(new JLabel(), gbc);
        }
        
        panelRepuestos.revalidate();
        panelRepuestos.repaint();
    }
    
    /**
     * Crear una card estética para mostrar un repuesto - Optimizada para pantalla más pequeña
     */
    private JPanel crearCardRepuesto(Repuesto repuesto) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setPreferredSize(new Dimension(230, 170)); // Ajustado para 980px
        card.setBackground(new Color(0, 0, 0)); // Fondo negro Kawasaki
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        // Nombre del repuesto - Más compacto
        JLabel lblNombre = new JLabel(repuesto.getNombre());
        lblNombre.setBounds(6, 6, 215, 20); // Ajustado para 230px
        lblNombre.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 13));
        lblNombre.setForeground(new Color(255, 255, 255)); // Texto blanco
        card.add(lblNombre);
        
        // Categoría con color
        JLabel lblCategoria = new JLabel(repuesto.getCategoria());
        lblCategoria.setBounds(6, 26, 120, 16); // Ajustado
        lblCategoria.setFont(new Font("JetBrains Mono ExtraBold", Font.PLAIN, 10));
        lblCategoria.setForeground(new Color(0, 153, 0));
        card.add(lblCategoria);
        
        // Marca
        JLabel lblMarca = new JLabel("Marca: " + repuesto.getMarca());
        lblMarca.setBounds(130, 26, 95, 16); // Ajustado para 230px
        lblMarca.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
        lblMarca.setForeground(new Color(200, 200, 200)); // Gris claro
        card.add(lblMarca);
        
        // Descripción - Más compacta
        JTextArea txtDescripcion = new JTextArea(repuesto.getDescripcion());
        txtDescripcion.setBounds(6, 46, 215, 30); // Ajustado para 230px
        txtDescripcion.setFont(new Font("JetBrains Mono", Font.PLAIN, 10));
        txtDescripcion.setForeground(new Color(180, 180, 180)); // Gris más claro
        txtDescripcion.setBackground(new Color(0, 0, 0)); // Fondo negro
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null); // Sin borde
        card.add(txtDescripcion);
        
        // Precio - Ajustado para mejor visibilidad
        NumberFormat formatoPrecio = NumberFormat.getCurrencyInstance();
        JLabel lblPrecio = new JLabel("Precio: " + formatoPrecio.format(repuesto.getPrecio()));
        lblPrecio.setBounds(6, 82, 180, 22); // Ancho aumentado para el precio
        lblPrecio.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 12)); // Fuente más pequeña
        lblPrecio.setForeground(new Color(0, 153, 0));
        card.add(lblPrecio);
        
        // Stock
        JLabel lblStock = new JLabel("Stock: " + repuesto.getStock() + " unidades");
        lblStock.setBounds(6, 108, 160, 16); // Ajustado
        lblStock.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
        Color colorStock = repuesto.getStock() > 10 ? new Color(40, 167, 69) : 
                          repuesto.getStock() > 5 ? new Color(255, 193, 7) : new Color(220, 53, 69);
        lblStock.setForeground(colorStock);
        card.add(lblStock);
        
        // ID del repuesto
        JLabel lblId = new JLabel("ID: " + repuesto.getIdRepuesto());
        lblId.setBounds(170, 108, 55, 16); // Ajustado para 230px
        lblId.setFont(new Font("JetBrains Mono", Font.PLAIN, 10));
        lblId.setForeground(new Color(150, 150, 150)); // Gris medio
        card.add(lblId);
        
        // Disponibilidad
        JLabel lblDisponible = new JLabel(repuesto.getStock() > 0 ? "DISPONIBLE" : "AGOTADO");
        lblDisponible.setBounds(6, 128, 100, 16);
        lblDisponible.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 9));
        lblDisponible.setForeground(repuesto.getStock() > 0 ? new Color(0, 153, 0) : new Color(220, 53, 69));
        card.add(lblDisponible);
        
        // Controles del carrito (solo si hay stock disponible)
        if (repuesto.getStock() > 0) {
            // Selector de cantidad
            JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, repuesto.getStock(), 1));
            spinnerCantidad.setBounds(115, 128, 50, 20);
            spinnerCantidad.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
            
            // Configurar colores del spinner para mejor visibilidad
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerCantidad.getEditor();
            editor.getTextField().setBackground(Color.BLACK);
            editor.getTextField().setForeground(Color.WHITE);
            editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
            editor.getTextField().setBorder(BorderFactory.createLineBorder(new Color(0, 153, 0), 1));
            editor.getTextField().setCaretColor(Color.WHITE);
            
            // Configurar los botones del spinner
            for (Component comp : spinnerCantidad.getComponents()) {
                if (comp instanceof JButton) {
                    comp.setBackground(new Color(0, 153, 0));
                    comp.setForeground(Color.WHITE);
                }
            }
            
            card.add(spinnerCantidad);
            
            // Botón Agregar al carrito
            JButton btnAgregar = new JButton("+");
            btnAgregar.setBounds(170, 128, 30, 20);
            btnAgregar.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 12));
            btnAgregar.setBackground(new Color(0, 153, 0));
            btnAgregar.setForeground(Color.WHITE);
            btnAgregar.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 1));
            btnAgregar.setFocusPainted(false);
            btnAgregar.setToolTipText("Agregar al carrito");
            
            // Acción del botón agregar
            btnAgregar.addActionListener(e -> {
                int cantidad = (Integer) spinnerCantidad.getValue();
                if (gestorCarrito.agregarAlCarrito(cedulaClienteVenta, repuesto, cantidad)) {
                    actualizarVistaCarrito();
                    JOptionPane.showMessageDialog(card, 
                        repuesto.getNombre() + " agregado al carrito\nCantidad: " + cantidad, 
                        "Agregado", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Actualizar el stock máximo del spinner si es necesario
                    Carrito carrito = gestorCarrito.obtenerCarritoCliente(cedulaClienteVenta);
                    ItemCarrito[] items = carrito.obtenerItemsArray();
                    for (ItemCarrito item : items) {
                        if (item.getRepuesto().getIdRepuesto() == repuesto.getIdRepuesto()) {
                            int stockDisponible = repuesto.getStock() - item.getCantidad();
                            if (stockDisponible > 0) {
                                spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, stockDisponible, 1));
                            } else {
                                btnAgregar.setEnabled(false);
                                btnAgregar.setText("MAX");
                                btnAgregar.setBackground(Color.GRAY);
                            }
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(card, 
                        "No se pudo agregar al carrito.\nStock insuficiente.", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            });
            
            card.add(btnAgregar);
        }
        
        // Efectos hover con colores Kawasaki
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 200, 0), 3), // Verde más brillante en hover
                    BorderFactory.createEmptyBorder(13, 13, 13, 13)
                ));
                card.setBackground(new Color(20, 20, 20)); // Negro más claro en hover
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
                    BorderFactory.createEmptyBorder(8, 8, 8, 8)
                ));
                card.setBackground(new Color(0, 0, 0)); // Volver al negro original
            }
        });
        
        return card;
    }
    

    

    
    /**
     * Configurar eventos de los componentes
     */
    private void configurarEventos() {
        // Evento de búsqueda en tiempo real
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aplicarFiltrosYMostrar();
            }
        });
        
        // Evento de cambio de categoría
        cboCategoria.addActionListener(e -> aplicarFiltrosYMostrar());
        
        // Evento de cambio de ordenamiento
        cboOrdenamiento.addActionListener(e -> aplicarFiltrosYMostrar());
        
        // Evento limpiar filtros
        btnLimpiarFiltros.addActionListener(e -> {
            txtBuscar.setText("");
            cboCategoria.setSelectedIndex(0);
            cboOrdenamiento.setSelectedIndex(0);
            aplicarFiltrosYMostrar();
        });
        
        // Evento volver (cerrar este frame)
        btnCerrarSesion.addActionListener(e -> {
            this.dispose();
        });
    }
    
    /**
     * Clase para crear bordes redondeados
     */
    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int radius;
        
        public RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }
        
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);
            g2d.dispose();
        }
        
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius/2, radius/2, radius/2, radius/2);
        }
    }
    
    /**
     * Crear un botón con estilo redondeado Kawasaki
     */
    private JButton crearBotonRedondeado(String texto, Color colorFondo, Color colorTexto, Color colorBorde) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo redondeado
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                // Llamar al paintComponent original para el texto
                super.paintComponent(g2d);
                g2d.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(colorBorde);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 15, 15);
                g2d.dispose();
            }
        };
        
        boton.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        
        return boton;
    }
    
    /**
     * Aplicar estilo redondeado a un botón existente
     */
    private void aplicarEstiloRedondeadoABoton(JButton boton, Color colorFondo, Color colorTexto, Color colorBorde) {
        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fondo redondeado
                g2d.setColor(boton.getBackground());
                g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 15, 15);
                
                // Borde redondeado
                g2d.setColor(colorBorde);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawRoundRect(1, 1, c.getWidth() - 3, c.getHeight() - 3, 15, 15);
                
                g2d.dispose();
                super.paint(g, c);
            }
        });
        
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
    }

    /**
     * Inicializar los componentes del carrito
     */
    private void inicializarCarrito() {
        // El botón del carrito ya está definido en el .form
        // Solo actualizamos su texto inicial sin emoji
        btnVerCarrito.setText("Carrito (0)");
        
        // Aplicar estilo redondeado al botón del carrito
        aplicarEstiloRedondeadoABoton(btnVerCarrito, new Color(0, 153, 0), Color.WHITE, new Color(0, 100, 0));
        
        // Crear dialog para el carrito
        dialogCarrito = new JDialog(this, "Carrito de Compras", false);
        dialogCarrito.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialogCarrito.setSize(350, 400);
        dialogCarrito.setLocationRelativeTo(this);
        
        // Crear panel del carrito
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BorderLayout());
        panelCarrito.setBackground(new Color(0, 0, 0));
        panelCarrito.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Panel para contenido del carrito con scroll
        JPanel contenidoCarrito = new JPanel();
        contenidoCarrito.setLayout(new BoxLayout(contenidoCarrito, BoxLayout.Y_AXIS));
        contenidoCarrito.setBackground(new Color(0, 0, 0));
        
        scrollCarrito = new JScrollPane(contenidoCarrito);
        scrollCarrito.setPreferredSize(new Dimension(300, 200));
        scrollCarrito.setBackground(new Color(0, 0, 0));
        scrollCarrito.setBorder(null);
        
        // Panel para botones del carrito
        JPanel panelBotonesCarrito = new JPanel(new FlowLayout());
        panelBotonesCarrito.setBackground(new Color(0, 0, 0));
        
        btnLimpiarCarrito = crearBotonRedondeado("Limpiar Carrito", 
                                               new Color(220, 53, 69), 
                                               Color.WHITE, 
                                               new Color(180, 40, 50));
        btnLimpiarCarrito.setPreferredSize(new Dimension(140, 35));
        btnLimpiarCarrito.addActionListener(e -> limpiarCarrito());
        
        btnProcederPago = crearBotonRedondeado("Proceder al Pago", 
                                             new Color(0, 153, 0), 
                                             Color.WHITE, 
                                             new Color(0, 100, 0));
        btnProcederPago.setPreferredSize(new Dimension(140, 35));
        btnProcederPago.addActionListener(e -> procederAlPago());
        
        panelBotonesCarrito.add(btnLimpiarCarrito);
        panelBotonesCarrito.add(btnProcederPago);
        
        // Total del carrito
        lblTotalCarrito = new JLabel("Total: $0.00");
        lblTotalCarrito.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 14));
        lblTotalCarrito.setForeground(new Color(0, 153, 0));
        lblTotalCarrito.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Agregar componentes al panel carrito
        panelCarrito.add(scrollCarrito, BorderLayout.CENTER);
        panelCarrito.add(lblTotalCarrito, BorderLayout.NORTH);
        panelCarrito.add(panelBotonesCarrito, BorderLayout.SOUTH);
        
        // Agregar panel al dialog
        dialogCarrito.add(panelCarrito);
        dialogCarrito.getContentPane().setBackground(new Color(0, 0, 0));
        
        // Agregar botón y panel carrito al frame principal
        agregarCarritoAlFrame();
    }
    
    /**
     * Agregar componentes del carrito al frame principal
     */
    private void agregarCarritoAlFrame() {
        // El botón del carrito ya está en el .form, solo necesitamos configurar el evento
        btnVerCarrito.addActionListener(e -> toggleCarrito());
        
        // NO agregamos el panel aquí, se mostrará como ventana flotante
    }
    
    /**
     * Mostrar/ocultar carrito
     */
    private void toggleCarrito() {
        carritoVisible = !carritoVisible;
        
        if (carritoVisible) {
            actualizarVistaCarrito();
            btnVerCarrito.setText("X Cerrar");
            dialogCarrito.setVisible(true);
        } else {
            btnVerCarrito.setText("Carrito (" + gestorCarrito.obtenerCarritoCliente(cedulaClienteVenta).getNumeroItems() + ")");
            dialogCarrito.setVisible(false);
        }
    }
    
    /**
     * Limpiar carrito completamente
     */
    private void limpiarCarrito() {
        gestorCarrito.limpiarCarrito(cedulaClienteVenta);
        actualizarVistaCarrito();
        JOptionPane.showMessageDialog(dialogCarrito, "Carrito limpiado correctamente", "Carrito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Proceder al pago (placeholder)
     */
    private void procederAlPago() {
        Carrito carrito = gestorCarrito.obtenerCarritoCliente(cedulaClienteVenta);
        if (carrito.estaVacio()) {
            JOptionPane.showMessageDialog(dialogCarrito, "El carrito está vacío", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Mostrar diálogo de venta con opciones de pago
        mostrarDialogoVenta(carrito);
    }
    
    /**
     * Mostrar diálogo completo de venta con opciones de pago y prefactura
     */
    private void mostrarDialogoVenta(Carrito carrito) {
        // Crear diálogo principal
        JDialog dialogoVenta = new JDialog(this, "Procesar Venta - JAO Workshop", true);
        dialogoVenta.setSize(600, 700);
        dialogoVenta.setLocationRelativeTo(this);
        dialogoVenta.setBackground(new Color(0, 0, 0));
        
        // Panel principal con fondo negro
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(0, 0, 0));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // === TÍTULO ===
        JLabel lblTitulo = new JLabel("FINALIZAR COMPRA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 153, 0)); // Verde Kawasaki
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        // === PANEL CENTRAL ===
        JPanel panelCentral = new JPanel(new BorderLayout(0, 15));
        panelCentral.setBackground(new Color(0, 0, 0));
        
        // --- SECCIÓN: OPCIONES DE PAGO ---
        JPanel panelOpciones = crearPanelOpcionesPago();
        panelCentral.add(panelOpciones, BorderLayout.NORTH);
        
        // --- SECCIÓN: PREFACTURA ---
        JPanel panelPrefactura = crearPanelPrefactura(carrito);
        panelCentral.add(panelPrefactura, BorderLayout.CENTER);
        
        // --- SECCIÓN: DIRECCIÓN (inicialmente oculta) ---
        JPanel panelDireccion = crearPanelDireccion();
        panelDireccion.setVisible(false);
        panelCentral.add(panelDireccion, BorderLayout.SOUTH);
        
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        // === BOTONES ===
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setBackground(new Color(0, 0, 0));
        
        JButton btnCancelar = crearBotonRedondeado("Cancelar", 
                                                   new Color(128, 128, 128), 
                                                   Color.WHITE, 
                                                   new Color(100, 100, 100));
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.addActionListener(e -> dialogoVenta.dispose());
        
        JButton btnConfirmar = crearBotonRedondeado("Confirmar Compra", 
                                                    new Color(0, 153, 0), 
                                                    Color.WHITE, 
                                                    new Color(0, 100, 0));
        btnConfirmar.setPreferredSize(new Dimension(140, 35));
        
        panelBotones.add(btnCancelar);
        panelBotones.add(btnConfirmar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        // Configurar eventos dinámicos
        configurarEventosVenta(panelOpciones, panelDireccion, btnConfirmar, carrito, dialogoVenta);
        
        dialogoVenta.add(panelPrincipal);
        dialogoVenta.setVisible(true);
    }
    
    /**
     * Crear panel con opciones de pago
     */
    private JPanel crearPanelOpcionesPago() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(new Color(0, 0, 0));
        
        // Título de la sección
        JLabel lblTitulo = new JLabel("OPCIONES DE PAGO");
        lblTitulo.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // ComboBox con opciones
        String[] opcionesPago = {
            "Seleccionar opción...",
            "Pagar Online + Domicilio → Pagada",
            "Pagar Online + Recoger en Local → Pagada", 
            "Domicilio Contraentrega → Falta por pagar",
            "Recoger en Local → Falta por pagar"
        };
        
        JComboBox<String> comboOpciones = new JComboBox<>(opcionesPago);
        comboOpciones.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        comboOpciones.setBackground(new Color(40, 40, 40));
        comboOpciones.setForeground(Color.WHITE);
        comboOpciones.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        panel.add(comboOpciones, BorderLayout.CENTER);
        
        // Guardar referencia del combo para eventos
        panel.putClientProperty("comboOpciones", comboOpciones);
        
        return panel;
    }
    
    /**
     * Crear panel con prefactura (resumen de items)
     */
    private JPanel crearPanelPrefactura(Carrito carrito) {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(new Color(0, 0, 0));
        
        // Título de la sección
        JLabel lblTitulo = new JLabel("RESUMEN DE COMPRA");
        lblTitulo.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel con scroll para los items
        JPanel panelItems = new JPanel();
        panelItems.setLayout(new BoxLayout(panelItems, BoxLayout.Y_AXIS));
        panelItems.setBackground(new Color(20, 20, 20));
        panelItems.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Agregar cada item del carrito
        for (ItemCarrito item : carrito.obtenerItemsArray()) {
            JPanel itemPanel = crearPanelItemPrefactura(item);
            panelItems.add(itemPanel);
            panelItems.add(Box.createVerticalStrut(8));
        }
        
        // Total general
        JPanel panelTotal = new JPanel(new BorderLayout());
        panelTotal.setBackground(new Color(20, 20, 20));
        panelTotal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(0, 153, 0)),
            BorderFactory.createEmptyBorder(10, 0, 5, 0)
        ));
        
        JLabel lblTotal = new JLabel("TOTAL: $" + String.format("%.2f", carrito.getTotalCarrito()));
        lblTotal.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 153, 0));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        panelTotal.add(lblTotal, BorderLayout.EAST);
        
        panelItems.add(panelTotal);
        
        // Scroll pane
        JScrollPane scroll = new JScrollPane(panelItems);
        scroll.setPreferredSize(new Dimension(0, 250));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 0), 2));
        scroll.getViewport().setBackground(new Color(20, 20, 20));
        
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crear panel individual para cada item en la prefactura
     */
    private JPanel crearPanelItemPrefactura(ItemCarrito item) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(20, 20, 20));
        
        // Información del producto
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(20, 20, 20));
        
        JLabel lblNombre = new JLabel(item.getRepuesto().getNombre());
        lblNombre.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblDetalles = new JLabel(item.getRepuesto().getMarca() + " • " + 
                                       item.getRepuesto().getCategoria());
        lblDetalles.setFont(new Font("JetBrains Mono", Font.PLAIN, 10));
        lblDetalles.setForeground(new Color(180, 180, 180));
        lblDetalles.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelInfo.add(lblNombre);
        panelInfo.add(Box.createVerticalStrut(2)); // Reducir espacio entre líneas
        panelInfo.add(lblDetalles);
        
        // Cantidad y precios
        JPanel panelPrecio = new JPanel(new GridLayout(2, 1, 0, 2));
        panelPrecio.setBackground(new Color(20, 20, 20));
        
        JLabel lblCantidad = new JLabel("x" + item.getCantidad() + 
                                       " • $" + String.format("%.2f", item.getPrecioUnitario()));
        lblCantidad.setFont(new Font("JetBrains Mono", Font.PLAIN, 10));
        lblCantidad.setForeground(new Color(180, 180, 180));
        lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
        
        JLabel lblSubtotal = new JLabel("$" + String.format("%.2f", item.getSubtotal()));
        lblSubtotal.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        lblSubtotal.setForeground(new Color(0, 153, 0));
        lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
        
        panelPrecio.add(lblCantidad);
        panelPrecio.add(lblSubtotal);
        
        panel.add(panelInfo, BorderLayout.CENTER);
        panel.add(panelPrecio, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Crear panel de dirección (se muestra solo para opciones con domicilio)
     */
    private JPanel crearPanelDireccion() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(new Color(0, 0, 0));
        
        // Título de la sección
        JLabel lblTitulo = new JLabel("DIRECCIÓN DE ENTREGA");
        lblTitulo.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Campo de texto para dirección
        JTextArea txtDireccion = new JTextArea(2, 30);
        txtDireccion.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        txtDireccion.setBackground(new Color(40, 40, 40));
        txtDireccion.setForeground(Color.WHITE);
        txtDireccion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        txtDireccion.setLineWrap(true);
        txtDireccion.setWrapStyleWord(true);
        
        // Dirección del cliente logueado
        txtDireccion.setText(clienteActual.getDireccion());
        
        JScrollPane scrollDireccion = new JScrollPane(txtDireccion);
        scrollDireccion.setBorder(null);
        panel.add(scrollDireccion, BorderLayout.CENTER);
        
        // Guardar referencia del campo para eventos
        panel.putClientProperty("txtDireccion", txtDireccion);
        
        return panel;
    }
    
    /**
     * Configurar eventos dinámicos del diálogo de venta
     */
    private void configurarEventosVenta(JPanel panelOpciones, JPanel panelDireccion, 
                                      JButton btnConfirmar, Carrito carrito, JDialog dialogoVenta) {
        
        JComboBox<String> comboOpciones = (JComboBox<String>) panelOpciones.getClientProperty("comboOpciones");
        JTextArea txtDireccion = (JTextArea) panelDireccion.getClientProperty("txtDireccion");
        
        // Evento para mostrar/ocultar panel de dirección según la opción elegida
        comboOpciones.addActionListener(e -> {
            String opcionSeleccionada = (String) comboOpciones.getSelectedItem();
            boolean requiereDomicilio = opcionSeleccionada != null && 
                                       opcionSeleccionada.contains("Domicilio");
            
            panelDireccion.setVisible(requiereDomicilio);
            dialogoVenta.revalidate();
            dialogoVenta.repaint();
        });
        
        // Evento para confirmar venta
        btnConfirmar.addActionListener(e -> {
            String opcionSeleccionada = (String) comboOpciones.getSelectedItem();
            
            if (opcionSeleccionada == null || opcionSeleccionada.contains("Seleccionar")) {
                JOptionPane.showMessageDialog(dialogoVenta, 
                    "Por favor selecciona una opción de pago", 
                    "Opción requerida", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verificar dirección si es domicilio
            if (opcionSeleccionada.contains("Domicilio")) {
                String direccion = txtDireccion.getText().trim();
                if (direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(dialogoVenta, 
                        "Por favor ingresa la dirección de entrega", 
                        "Dirección requerida", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            // Mostrar confirmación antes de procesar la compra
            String mensajeConfirmacion = String.format(
                "🛒 ¿Confirmar Compra?\n\n" +
                "• Items: %d\n" +
                "• Total: $%.2f\n" +
                "• Modalidad: %s%s",
                carrito.getNumeroItems(),
                carrito.getTotalCarrito(),
                opcionSeleccionada,
                opcionSeleccionada.contains("Domicilio") ? 
                    "\n• Dirección: " + txtDireccion.getText().trim() : ""
            );
            
            int confirmarCompra = JOptionPane.showConfirmDialog(dialogoVenta,
                mensajeConfirmacion,
                "Confirmar Compra - JAO Workshop",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (confirmarCompra != JOptionPane.YES_OPTION) {
                return; // El usuario canceló
            }
            
            // Procesar la venta
            procesarVenta(opcionSeleccionada, txtDireccion.getText(), carrito, dialogoVenta);
        });
    }
    
    /**
     * Procesar la venta según la opción seleccionada
     */
    private void procesarVenta(String opcionPago, String direccion, Carrito carrito, JDialog dialogoVenta) {
        try {
            // 1. Verificar disponibilidad antes de procesar
            if (!gestorStock.verificarDisponibilidad(carrito)) {
                JOptionPane.showMessageDialog(dialogoVenta, 
                    "❌ Algunos productos no tienen stock suficiente.\nPor favor revisa tu carrito.", 
                    "Stock Insuficiente", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // 2. Generar códigos únicos
            String codigoVenta = GeneradorCodigos.generarCodigoVenta();
            String codigoFactura = GeneradorCodigos.generarCodigoFactura();
            
            // 3. Procesar actualización de stock
            java.util.List<Model.Repuesto> repuestosSinStock = gestorStock.procesarVentaStock(carrito, opcionPago);
            
            // 4. Crear objetos Venta y Factura usando ClienteService (SOLO LÓGICA DE NEGOCIO)
            java.util.List<ItemVenta> itemsVenta = clienteService.crearItemsVenta(carrito);
            Venta venta = clienteService.crearVenta(codigoVenta, clienteActual, itemsVenta, 
                                                    carrito.getTotalCarrito(), opcionPago);
            Factura factura = clienteService.crearFactura(venta, codigoFactura, opcionPago);
            
            // 5. Generar factura PDF
            String rutaFactura = generadorPDF.generarFacturaPDF(factura);
            
            // 6. Actualizar vista si hay repuestos sin stock (UI)
            if (!repuestosSinStock.isEmpty()) {
                cargarRepuestos(); // UI: Recargar repuestos
                
                // Construir mensaje usando ClienteService (SOLO TEXTO)
                String mensajeRemovidos = clienteService.construirMensajeProductosRemovidos(repuestosSinStock);
                
                JOptionPane.showMessageDialog(this, 
                    mensajeRemovidos, 
                    "Inventario Actualizado", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            // 7. Mostrar confirmación de venta exitosa (UI)
            String reporteStock = gestorStock.generarReporteStock(carrito, opcionPago);
            String mensajeConfirmacion = clienteService.construirMensajeConfirmacion(
                codigoVenta, codigoFactura, carrito, opcionPago, direccion, rutaFactura, reporteStock
            );
            
            JOptionPane.showMessageDialog(dialogoVenta, 
                mensajeConfirmacion, 
                "Venta Completada - JAO Workshop", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // 8. Actualizar cards después de la venta para reflejar cambios de stock (UI)
            cargarRepuestos();
            
            // 9. Limpiar carrito y cerrar diálogos (UI)
            gestorCarrito.limpiarCarrito(cedulaClienteVenta);
            actualizarVistaCarrito();
            dialogoVenta.dispose();
            if (dialogCarrito != null) {
                dialogCarrito.dispose();
            }
            
            System.out.println("✅ Venta completada exitosamente: " + codigoVenta + " | Factura: " + codigoFactura);
            
        } catch (Exception e) {
            System.err.println("❌ Error al procesar venta: " + e.getMessage());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(dialogoVenta, 
                "❌ Error al procesar la venta:\n" + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    /**
     * Actualizar la vista del carrito
     */
    private void actualizarVistaCarrito() {
        Carrito carrito = gestorCarrito.obtenerCarritoCliente(cedulaClienteVenta);
        
        // Actualizar texto del botón
        btnVerCarrito.setText("Carrito (" + carrito.getNumeroItems() + ")");
        
        // Actualizar total
        lblTotalCarrito.setText("Total: $" + String.format("%.2f", carrito.getTotalCarrito()));
        
        // Actualizar contenido del carrito
        JPanel contenido = (JPanel) scrollCarrito.getViewport().getView();
        contenido.removeAll();
        
        if (carrito.estaVacio()) {
            JLabel lblVacio = new JLabel("Carrito vacío");
            lblVacio.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            lblVacio.setForeground(Color.GRAY);
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenido.add(lblVacio);
        } else {
            ItemCarrito[] items = carrito.obtenerItemsArray();
            for (ItemCarrito item : items) {
                contenido.add(crearItemCarritoUI(item));
            }
        }
        
        contenido.revalidate();
        contenido.repaint();
    }
    
    /**
     * Crear UI para un item del carrito
     */
    private JPanel crearItemCarritoUI(ItemCarrito item) {
        JPanel panelItem = new JPanel();
        panelItem.setLayout(new BorderLayout());
        panelItem.setBackground(new Color(20, 20, 20));
        panelItem.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 0), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        panelItem.setMaximumSize(new Dimension(280, 60));
        
        // Información del producto
        JLabel lblNombre = new JLabel(item.getRepuesto().getNombre());
        lblNombre.setFont(new Font("JetBrains Mono", Font.BOLD, 10));
        lblNombre.setForeground(Color.WHITE);
        
        JLabel lblPrecio = new JLabel("$" + String.format("%.2f", item.getSubtotal()));
        lblPrecio.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
        lblPrecio.setForeground(new Color(0, 153, 0));
        
        // Panel de cantidad con botones
        JPanel panelCantidad = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        panelCantidad.setBackground(new Color(20, 20, 20));
        
        JButton btnMenos = crearBotonRedondeado("-", new Color(220, 53, 69), Color.WHITE, new Color(180, 40, 50));
        btnMenos.setPreferredSize(new Dimension(25, 20));
        btnMenos.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
        btnMenos.addActionListener(e -> cambiarCantidad(item, -1));
        
        JLabel lblCantidad = new JLabel(String.valueOf(item.getCantidad()));
        lblCantidad.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
        lblCantidad.setPreferredSize(new Dimension(20, 20));
        
        JButton btnMas = crearBotonRedondeado("+", new Color(0, 153, 0), Color.WHITE, new Color(0, 100, 0));
        btnMas.setPreferredSize(new Dimension(25, 20));
        btnMas.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 10));
        btnMas.addActionListener(e -> cambiarCantidad(item, 1));
        
        panelCantidad.add(btnMenos);
        panelCantidad.add(lblCantidad);
        panelCantidad.add(btnMas);
        
        // Organizar layout
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(new Color(20, 20, 20));
        panelInfo.add(lblNombre, BorderLayout.NORTH);
        panelInfo.add(lblPrecio, BorderLayout.SOUTH);
        
        panelItem.add(panelInfo, BorderLayout.CENTER);
        panelItem.add(panelCantidad, BorderLayout.EAST);
        
        return panelItem;
    }
    
    /**
     * Cambiar cantidad de un item en el carrito
     */
    private void cambiarCantidad(ItemCarrito item, int delta) {
        int nuevaCantidad = item.getCantidad() + delta;
        
        if (nuevaCantidad <= 0) {
            // Eliminar item del carrito
            gestorCarrito.eliminarDelCarrito(cedulaClienteVenta, item.getRepuesto().getIdRepuesto());
        } else {
            // Actualizar cantidad
            if (!gestorCarrito.actualizarCantidadCarrito(cedulaClienteVenta, item.getRepuesto().getIdRepuesto(), nuevaCantidad)) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente. Stock disponible: " + item.getRepuesto().getStock(), 
                                            "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        actualizarVistaCarrito();
    }
    
    /**
     * Método main para testing
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Cliente().setVisible(true);
        });
    }
}