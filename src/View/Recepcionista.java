package View;

import Model.Cliente;
import Model.Sesion;
import Model.Factura;
import Service.LoginService;
import Service.AdministradorService;
import Service.GestorCarrito;
import Percistencia.RedireccionPorRol;
import javax.swing.JOptionPane;
import listaDoble.Lista;

/**
 *
 * @author Adrian
 */
public class Recepcionista extends javax.swing.JFrame {

    //Creacion de la lista de Clientes
    Lista listaClientes = new Lista();
    
    // Servicios
    private LoginService loginService;
    private AdministradorService administradorService;
    private GestorCarrito gestorCarrito;
    private RedireccionPorRol redireccionRol;
    private String idRecepcionistaActual; // ID del recepcionista logueado
    
    // Variable para almacenar la factura actual encontrada
    private Factura facturaActual;

    /**
     * Constructor por defecto
     */
    public Recepcionista() {
        initComponents();
        setLocationRelativeTo(null);
        listaClientes = new Lista();
        inicializarServicios();
    }
    
    /**
     * Constructor que recibe el ID del recepcionista para precargar datos
     * @param idRecepcionista ID del recepcionista logueado
     */
    public Recepcionista(String idRecepcionista) {
        initComponents();
        setLocationRelativeTo(null);
        listaClientes = new Lista();
        inicializarServicios();
        
        if (idRecepcionista != null && !idRecepcionista.isEmpty()) {
            this.idRecepcionistaActual = idRecepcionista;
            System.out.println("Recepcionista inicializado con ID: " + idRecepcionista);
            // Aquí puedes precargar datos del recepcionista si es necesario
            // precargarDatosRecepcionistaPorId(idRecepcionista);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error: ID de recepcionista no válido.", 
                "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Inicializar servicios necesarios
     */
    private void inicializarServicios() {
        loginService = new LoginService();
        administradorService = new AdministradorService(loginService);
        gestorCarrito = new GestorCarrito();
        redireccionRol = new RedireccionPorRol();
        facturaActual = null;
        System.out.println("Servicios inicializados para Recepcionista");
        
        // Sincronizar facturas de GestorCarrito a LoginService (para compatibilidad)
        sincronizarFacturas();
    }
    
    /**
     * Sincroniza las facturas de GestorCarrito a LoginService para acceso centralizado
     */
    private void sincronizarFacturas() {
        try {
            java.util.List<Factura> facturasGestor = gestorCarrito.obtenerTodasLasFacturas();
            for (Factura factura : facturasGestor) {
                loginService.agregarFactura(factura);
            }
        } catch (Exception e) {
            System.out.println("Error al sincronizar facturas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNegro = new javax.swing.JPanel();
        panelDecoración = new javax.swing.JPanel();
        panelBtnMenuP = new javax.swing.JPanel();
        iconInicio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelBtnAgregar = new javax.swing.JPanel();
        iconAgregarPaciente = new javax.swing.JLabel();
        labelAgregarPaciente = new javax.swing.JLabel();
        panelBtnActualizar = new javax.swing.JPanel();
        iconEditarPaciente = new javax.swing.JLabel();
        labelEditarPaciente = new javax.swing.JLabel();
        panelBtnEliminar = new javax.swing.JPanel();
        iconAgendarCita = new javax.swing.JLabel();
        labelEditarPaciente1 = new javax.swing.JLabel();
        panelBtnCerrarSesion = new javax.swing.JPanel();
        iconCerrarSesion = new javax.swing.JLabel();
        labelCerrarSesion = new javax.swing.JLabel();
        iconRecepcinista = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelDecoración1 = new javax.swing.JPanel();
        panelDecoración2 = new javax.swing.JPanel();
        labelMenúOpciones = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnCerrarSesion2 = new javax.swing.JPanel();
        iconCerrarSesion2 = new javax.swing.JLabel();
        etiVentas = new javax.swing.JLabel();
        panelGris = new javax.swing.JPanel();
        labelRecepcionisa1 = new javax.swing.JLabel();
        PanelNegro2 = new javax.swing.JPanel();
        panelVentaRecepcionista = new javax.swing.JTabbedPane();
        PanelFacturas = new javax.swing.JPanel();
        panelDecoración6 = new javax.swing.JPanel();
        panelDecoración7 = new javax.swing.JPanel();
        panelDecoración8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblRepuesto1 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JTextField();
        btnBuscarFactura = new javax.swing.JButton();
        btnCancelarBusquedaFactura = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtIdClienteActualizar1 = new javax.swing.JTextField();
        btnLimpiarFactura = new javax.swing.JButton();
        btnAprobarFactura = new javax.swing.JButton();
        PanelAgregar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelDecoración3 = new javax.swing.JPanel();
        panelDecoración4 = new javax.swing.JPanel();
        panelDecoración5 = new javax.swing.JPanel();
        lblRepuesto = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblMarcaRepuesto = new javax.swing.JLabel();
        lblDescripcionRepuesto = new javax.swing.JLabel();
        txtPrimerApellidoCliente = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        lblPrecioRepuesto = new javax.swing.JLabel();
        lblNombreRepuesto = new javax.swing.JLabel();
        btnCancelarProcess = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtPrimerNombreCliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnAgregarCliente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSegundoNombreCliente = new javax.swing.JTextField();
        txtSegundoApellidoCliente = new javax.swing.JTextField();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        cboGeneroCliente = new javax.swing.JComboBox<>();
        txtCorreoCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuarioCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContraseñaCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        panelEliminar = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelDecoración9 = new javax.swing.JPanel();
        panelDecoración10 = new javax.swing.JPanel();
        panelDecoración11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblRepuesto2 = new javax.swing.JLabel();
        btnBuscarClienteEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtIdClienteEliminar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        PrimerNombreELiminar = new javax.swing.JTextField();
        SegundoNombreEliminar = new javax.swing.JTextField();
        PrimerApellidoEliminar = new javax.swing.JTextField();
        SegundoApellidoEliminar = new javax.swing.JTextField();
        GeneroEliminar = new javax.swing.JTextField();
        CedulaEliminar = new javax.swing.JTextField();
        TelefonoEliminar = new javax.swing.JTextField();
        CorreoEliminar = new javax.swing.JTextField();
        DireccionELiminar = new javax.swing.JTextField();
        UsuarioEliminar = new javax.swing.JTextField();
        ContraseñaEliminar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btnEliminarCliente = new javax.swing.JButton();
        btnCancelarEliminacion = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        btnAbrirVentaRecepcionista = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelNegro.setBackground(new java.awt.Color(0, 0, 0));

        panelDecoración.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoraciónLayout = new javax.swing.GroupLayout(panelDecoración);
        panelDecoración.setLayout(panelDecoraciónLayout);
        panelDecoraciónLayout.setHorizontalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        panelDecoraciónLayout.setVerticalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        panelBtnMenuP.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnMenuP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnMenuPMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PANEL DE FACTURAS");

        javax.swing.GroupLayout panelBtnMenuPLayout = new javax.swing.GroupLayout(panelBtnMenuP);
        panelBtnMenuP.setLayout(panelBtnMenuPLayout);
        panelBtnMenuPLayout.setHorizontalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconInicio)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnMenuPLayout.setVerticalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuPLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap())))
        );

        panelBtnAgregar.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnAgregarMouseClicked(evt);
            }
        });

        labelAgregarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelAgregarPaciente.setText("VENTAS");

        javax.swing.GroupLayout panelBtnAgregarLayout = new javax.swing.GroupLayout(panelBtnAgregar);
        panelBtnAgregar.setLayout(panelBtnAgregarLayout);
        panelBtnAgregarLayout.setHorizontalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgregarPaciente)
                .addGap(101, 101, 101)
                .addComponent(labelAgregarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnAgregarLayout.setVerticalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnActualizarMouseClicked(evt);
            }
        });

        labelEditarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente.setText("AGREGAR CLIENTE");

        javax.swing.GroupLayout panelBtnActualizarLayout = new javax.swing.GroupLayout(panelBtnActualizar);
        panelBtnActualizar.setLayout(panelBtnActualizarLayout);
        panelBtnActualizarLayout.setHorizontalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarPaciente)
                .addGap(42, 42, 42)
                .addComponent(labelEditarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnActualizarLayout.setVerticalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panelBtnEliminar.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEliminarMouseClicked(evt);
            }
        });

        labelEditarPaciente1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente1.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente1.setText("ELIMINAR CLIENTE");

        javax.swing.GroupLayout panelBtnEliminarLayout = new javax.swing.GroupLayout(panelBtnEliminar);
        panelBtnEliminar.setLayout(panelBtnEliminarLayout);
        panelBtnEliminarLayout.setHorizontalGroup(
            panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgendarCita)
                .addGap(30, 30, 30)
                .addComponent(labelEditarPaciente1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnEliminarLayout.setVerticalGroup(
            panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(iconAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelEditarPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnCerrarSesion.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCerrarSesionMouseClicked(evt);
            }
        });

        labelCerrarSesion.setBackground(new java.awt.Color(204, 0, 0));
        labelCerrarSesion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        labelCerrarSesion.setText("CERRAR SESIÓN");
        labelCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarSesionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBtnCerrarSesionLayout = new javax.swing.GroupLayout(panelBtnCerrarSesion);
        panelBtnCerrarSesion.setLayout(panelBtnCerrarSesionLayout);
        panelBtnCerrarSesionLayout.setHorizontalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconCerrarSesion)
                .addGap(50, 50, 50)
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesionLayout.setVerticalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(15, 15, 15));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/JaoWorkshopL.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("¡SOMOS JAO-WORKSHOP!");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/colombia.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(83, 83, 83)
                .addComponent(jLabel10)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        panelDecoración1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración1Layout = new javax.swing.GroupLayout(panelDecoración1);
        panelDecoración1.setLayout(panelDecoración1Layout);
        panelDecoración1Layout.setHorizontalGroup(
            panelDecoración1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        panelDecoración1Layout.setVerticalGroup(
            panelDecoración1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        panelDecoración2.setBackground(new java.awt.Color(15, 15, 15));

        labelMenúOpciones.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        labelMenúOpciones.setForeground(new java.awt.Color(255, 255, 255));
        labelMenúOpciones.setText("OPCIONES EN EL MENÚ:");

        javax.swing.GroupLayout panelDecoración2Layout = new javax.swing.GroupLayout(panelDecoración2);
        panelDecoración2.setLayout(panelDecoración2Layout);
        panelDecoración2Layout.setHorizontalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDecoración2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelMenúOpciones)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelDecoración2Layout.setVerticalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDecoración2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenúOpciones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/social.png"))); // NOI18N
        jLabel13.setText("SOMOSJAOWORKSHOP");

        jLabel14.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/facebook.png"))); // NOI18N
        jLabel14.setText("JAO_WORKSHOP_COL");

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("¡ENCUÉNTRANOS!");

        panelBtnCerrarSesion2.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnCerrarSesion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCerrarSesion2MouseClicked(evt);
            }
        });

        etiVentas.setBackground(new java.awt.Color(204, 0, 0));
        etiVentas.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        etiVentas.setForeground(new java.awt.Color(255, 255, 255));
        etiVentas.setText(" ACCEDER VENTAS");
        etiVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etiVentasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBtnCerrarSesion2Layout = new javax.swing.GroupLayout(panelBtnCerrarSesion2);
        panelBtnCerrarSesion2.setLayout(panelBtnCerrarSesion2Layout);
        panelBtnCerrarSesion2Layout.setHorizontalGroup(
            panelBtnCerrarSesion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesion2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconCerrarSesion2)
                .addGap(29, 29, 29)
                .addComponent(etiVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesion2Layout.setVerticalGroup(
            panelBtnCerrarSesion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addGroup(panelBtnCerrarSesion2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelNegroLayout = new javax.swing.GroupLayout(panelNegro);
        panelNegro.setLayout(panelNegroLayout);
        panelNegroLayout.setHorizontalGroup(
            panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBtnCerrarSesion2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBtnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNegroLayout.createSequentialGroup()
                        .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBtnMenuP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconRecepcinista))
                    .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNegroLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel13))
                            .addGroup(panelNegroLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel15)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelNegroLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jLabel14)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );
        panelNegroLayout.setVerticalGroup(
            panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addGap(39, 39, 39)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDecoración1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDecoración2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(iconRecepcinista))
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBtnMenuP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(panelBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(panelBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBtnCerrarSesion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelNegroLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel14)
                    .addContainerGap(620, Short.MAX_VALUE)))
        );

        getContentPane().add(panelNegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 660));

        panelGris.setBackground(new java.awt.Color(51, 51, 51));

        labelRecepcionisa1.setBackground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 85)); // NOI18N
        labelRecepcionisa1.setForeground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setText("RECEPCIONISTA");

        javax.swing.GroupLayout panelGrisLayout = new javax.swing.GroupLayout(panelGris);
        panelGris.setLayout(panelGrisLayout);
        panelGrisLayout.setHorizontalGroup(
            panelGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGrisLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRecepcionisa1)
                .addGap(48, 48, 48))
        );
        panelGrisLayout.setVerticalGroup(
            panelGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGrisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRecepcionisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE))
        );

        getContentPane().add(panelGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 690, 80));

        PanelNegro2.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout PanelNegro2Layout = new javax.swing.GroupLayout(PanelNegro2);
        PanelNegro2.setLayout(PanelNegro2Layout);
        PanelNegro2Layout.setHorizontalGroup(
            PanelNegro2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        PanelNegro2Layout.setVerticalGroup(
            PanelNegro2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(PanelNegro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 690, 100));

        PanelFacturas.setBackground(new java.awt.Color(255, 255, 255));
        PanelFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDecoración6.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración6Layout = new javax.swing.GroupLayout(panelDecoración6);
        panelDecoración6.setLayout(panelDecoración6Layout);
        panelDecoración6Layout.setHorizontalGroup(
            panelDecoración6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración6Layout.setVerticalGroup(
            panelDecoración6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelFacturas.add(panelDecoración6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

        panelDecoración7.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración7Layout = new javax.swing.GroupLayout(panelDecoración7);
        panelDecoración7.setLayout(panelDecoración7Layout);
        panelDecoración7Layout.setHorizontalGroup(
            panelDecoración7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración7Layout.setVerticalGroup(
            panelDecoración7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelFacturas.add(panelDecoración7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

        panelDecoración8.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración8Layout = new javax.swing.GroupLayout(panelDecoración8);
        panelDecoración8.setLayout(panelDecoración8Layout);
        panelDecoración8Layout.setHorizontalGroup(
            panelDecoración8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración8Layout.setVerticalGroup(
            panelDecoración8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelFacturas.add(panelDecoración8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/actualizar.png"))); // NOI18N
        PanelFacturas.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("PUEDES VISUALIZAR LAS FACTURAS");
        PanelFacturas.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel23.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("- BÚSQUEDA RÁPIDA Y EFICIENTE EN EL SISTEMA.");
        PanelFacturas.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblRepuesto1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto1.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto1.setText("1. INGRESE EL ID DE LA FACTURA PARA HALLARLO EN EL SISTEMA:");
        PanelFacturas.add(lblRepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 590, -1));

        txtIdFactura.setBackground(new java.awt.Color(204, 204, 204));
        txtIdFactura.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdFactura.setForeground(new java.awt.Color(0, 0, 0));
        txtIdFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdFacturaActionPerformed(evt);
            }
        });
        PanelFacturas.add(txtIdFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        btnBuscarFactura.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscarFactura.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnBuscarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarFactura.setText("BUSCAR");
        btnBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFacturaActionPerformed(evt);
            }
        });
        PanelFacturas.add(btnBuscarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 130, 30));

        btnCancelarBusquedaFactura.setBackground(new java.awt.Color(153, 0, 0));
        btnCancelarBusquedaFactura.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnCancelarBusquedaFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarBusquedaFactura.setText("X");
        btnCancelarBusquedaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarBusquedaFacturaActionPerformed(evt);
            }
        });
        PanelFacturas.add(btnCancelarBusquedaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 80, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        PanelFacturas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, -1, -1));

        jPanel4.setBackground(new java.awt.Color(216, 216, 217));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelFacturas.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 10));

        jPanel6.setBackground(new java.awt.Color(216, 216, 217));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelFacturas.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 10));

        txtIdClienteActualizar1.setBackground(new java.awt.Color(204, 204, 204));
        txtIdClienteActualizar1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdClienteActualizar1.setForeground(new java.awt.Color(0, 0, 0));
        txtIdClienteActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActualizar1ActionPerformed(evt);
            }
        });
        PanelFacturas.add(txtIdClienteActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        btnLimpiarFactura.setBackground(new java.awt.Color(153, 0, 0));
        btnLimpiarFactura.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnLimpiarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarFactura.setText("LIMPIAR DATOS FACTURA");
        PanelFacturas.add(btnLimpiarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 280, 70));

        btnAprobarFactura.setBackground(new java.awt.Color(0, 153, 0));
        btnAprobarFactura.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnAprobarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnAprobarFactura.setText("APROBAR FACTURA");
        btnAprobarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobarFacturaActionPerformed(evt);
            }
        });
        PanelFacturas.add(btnAprobarFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 70));

        panelVentaRecepcionista.addTab("Facturas", PanelFacturas);

        PanelAgregar.setBackground(new java.awt.Color(255, 255, 255));
        PanelAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("- AL MEJOR PRECIO.");
        PanelAgregar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        panelDecoración3.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración3Layout = new javax.swing.GroupLayout(panelDecoración3);
        panelDecoración3.setLayout(panelDecoración3Layout);
        panelDecoración3Layout.setHorizontalGroup(
            panelDecoración3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        panelDecoración3Layout.setVerticalGroup(
            panelDecoración3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAgregar.add(panelDecoración3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

        panelDecoración4.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración4Layout = new javax.swing.GroupLayout(panelDecoración4);
        panelDecoración4.setLayout(panelDecoración4Layout);
        panelDecoración4Layout.setHorizontalGroup(
            panelDecoración4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        panelDecoración4Layout.setVerticalGroup(
            panelDecoración4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAgregar.add(panelDecoración4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

        panelDecoración5.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración5Layout = new javax.swing.GroupLayout(panelDecoración5);
        panelDecoración5.setLayout(panelDecoración5Layout);
        panelDecoración5Layout.setHorizontalGroup(
            panelDecoración5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        panelDecoración5Layout.setVerticalGroup(
            panelDecoración5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAgregar.add(panelDecoración5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        lblRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto.setText("1. NOMBRE:");
        PanelAgregar.add(lblRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        lblCategoria.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(0, 0, 0));
        lblCategoria.setText("8. CORREO:");
        PanelAgregar.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 90, -1));

        lblMarcaRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblMarcaRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto.setText("3. PRIMER APELLIDO:");
        PanelAgregar.add(lblMarcaRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        lblDescripcionRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblDescripcionRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcionRepuesto.setText("4.SEGUNDO APELLIDO");
        PanelAgregar.add(lblDescripcionRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

        txtPrimerApellidoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtPrimerApellidoCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrimerApellidoCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtPrimerApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 160, -1));

        lblStock.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(0, 0, 0));
        lblStock.setText("6. CEDULA:");
        PanelAgregar.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        lblPrecioRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblPrecioRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecioRepuesto.setText("5. GENERO:");
        PanelAgregar.add(lblPrecioRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        lblNombreRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblNombreRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto.setText("2. SEGUNDO NOMBRE:");
        PanelAgregar.add(lblNombreRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        btnCancelarProcess.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelarProcess.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnCancelarProcess.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarProcess.setText("CANCELAR PROCESO");
        btnCancelarProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProcessActionPerformed(evt);
            }
        });
        PanelAgregar.add(btnCancelarProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 220, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/piezas-de-repuesto.png"))); // NOI18N
        PanelAgregar.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtPrimerNombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtPrimerNombreCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrimerNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtPrimerNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        PanelAgregar.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 690, 30));

        btnAgregarCliente.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCliente.setText("AGREGAR CLIENTE");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });
        PanelAgregar.add(btnAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 200, 30));

        jLabel16.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LLENA LOS CAMPOS PARA AGREGAR UN CLIENTE AL INVENTARIO");
        PanelAgregar.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel17.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("- EFICIENTES.");
        PanelAgregar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel18.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("- DURABLES.");
        PanelAgregar.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel19.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("- ALTA CALIDAD.");
        PanelAgregar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        txtSegundoNombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtSegundoNombreCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtSegundoNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtSegundoNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 160, -1));

        txtSegundoApellidoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtSegundoApellidoCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtSegundoApellidoCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtSegundoApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 160, -1));

        txtCedulaCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCedulaCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtCedulaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 140, -1));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("7. TELEFONO");
        PanelAgregar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, 20));

        txtTelefonoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtTelefonoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoClienteActionPerformed(evt);
            }
        });
        PanelAgregar.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 140, -1));

        cboGeneroCliente.setBackground(new java.awt.Color(204, 204, 204));
        cboGeneroCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        cboGeneroCliente.setForeground(new java.awt.Color(0, 0, 0));
        cboGeneroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));
        PanelAgregar.add(cboGeneroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, -1));

        txtCorreoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCorreoCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtCorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 180, -1));

        jLabel2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("9. USUARIO:");
        PanelAgregar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        txtUsuarioCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuarioCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtUsuarioCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtUsuarioCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 120, -1));

        jLabel4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("10. CONTRASEÑA: ");
        PanelAgregar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        txtContraseñaCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtContraseñaCliente.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtContraseñaCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtContraseñaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 140, -1));

        jLabel6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("11. DIRECCION:");
        PanelAgregar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        txtDireccionCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccionCliente.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        txtDireccionCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 180, -1));

        panelVentaRecepcionista.addTab("Agregar", PanelAgregar);

        panelEliminar.setBackground(new java.awt.Color(255, 255, 255));
        panelEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("¡REMUEVE ALGÚN CLIENTE DEL SISTEMA!");
        panelEliminar.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("- DE MANERA MUY EFICIENTE EN EL SISTEMA, PERO TEN CUIDADO DE ELIMINAR EL INCORRECTO.");
        panelEliminar.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        panelDecoración9.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración9Layout = new javax.swing.GroupLayout(panelDecoración9);
        panelDecoración9.setLayout(panelDecoración9Layout);
        panelDecoración9Layout.setHorizontalGroup(
            panelDecoración9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración9Layout.setVerticalGroup(
            panelDecoración9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelEliminar.add(panelDecoración9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

        panelDecoración10.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración10Layout = new javax.swing.GroupLayout(panelDecoración10);
        panelDecoración10.setLayout(panelDecoración10Layout);
        panelDecoración10Layout.setHorizontalGroup(
            panelDecoración10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración10Layout.setVerticalGroup(
            panelDecoración10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelEliminar.add(panelDecoración10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

        panelDecoración11.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración11Layout = new javax.swing.GroupLayout(panelDecoración11);
        panelDecoración11.setLayout(panelDecoración11Layout);
        panelDecoración11Layout.setHorizontalGroup(
            panelDecoración11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración11Layout.setVerticalGroup(
            panelDecoración11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelEliminar.add(panelDecoración11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/borrar.png"))); // NOI18N
        panelEliminar.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblRepuesto2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto2.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto2.setText("1. INGRESE EL ID DEL CLIENTE PARA HALLARLO EN EL SISTEMA:");
        panelEliminar.add(lblRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        btnBuscarClienteEliminar.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscarClienteEliminar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnBuscarClienteEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarClienteEliminar.setText("BUSCAR");
        btnBuscarClienteEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteEliminarActionPerformed(evt);
            }
        });
        panelEliminar.add(btnBuscarClienteEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 130, 30));

        btnCancelar.setBackground(new java.awt.Color(153, 0, 0));
        btnCancelar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("X");
        panelEliminar.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 160, 80, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        panelEliminar.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        txtIdClienteEliminar.setBackground(new java.awt.Color(204, 204, 204));
        txtIdClienteEliminar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdClienteEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtIdClienteEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        jPanel5.setBackground(new java.awt.Color(216, 216, 217));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelEliminar.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 10));

        PrimerNombreELiminar.setEditable(false);
        PrimerNombreELiminar.setBackground(new java.awt.Color(204, 204, 204));
        PrimerNombreELiminar.setForeground(new java.awt.Color(0, 0, 0));
        PrimerNombreELiminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrimerNombreELiminarActionPerformed(evt);
            }
        });
        panelEliminar.add(PrimerNombreELiminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, -1));

        SegundoNombreEliminar.setEditable(false);
        SegundoNombreEliminar.setBackground(new java.awt.Color(204, 204, 204));
        SegundoNombreEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(SegundoNombreEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 110, -1));

        PrimerApellidoEliminar.setEditable(false);
        PrimerApellidoEliminar.setBackground(new java.awt.Color(204, 204, 204));
        PrimerApellidoEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(PrimerApellidoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 100, -1));

        SegundoApellidoEliminar.setEditable(false);
        SegundoApellidoEliminar.setBackground(new java.awt.Color(204, 204, 204));
        SegundoApellidoEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(SegundoApellidoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 110, -1));

        GeneroEliminar.setEditable(false);
        GeneroEliminar.setBackground(new java.awt.Color(204, 204, 204));
        GeneroEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(GeneroEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 90, -1));

        CedulaEliminar.setEditable(false);
        CedulaEliminar.setBackground(new java.awt.Color(204, 204, 204));
        CedulaEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(CedulaEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 90, -1));

        TelefonoEliminar.setEditable(false);
        TelefonoEliminar.setBackground(new java.awt.Color(204, 204, 204));
        panelEliminar.add(TelefonoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 100, -1));

        CorreoEliminar.setEditable(false);
        CorreoEliminar.setBackground(new java.awt.Color(204, 204, 204));
        CorreoEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(CorreoEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 100, -1));

        DireccionELiminar.setEditable(false);
        DireccionELiminar.setBackground(new java.awt.Color(204, 204, 204));
        DireccionELiminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(DireccionELiminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 110, -1));

        UsuarioEliminar.setEditable(false);
        UsuarioEliminar.setBackground(new java.awt.Color(204, 204, 204));
        UsuarioEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(UsuarioEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 100, -1));

        ContraseñaEliminar.setEditable(false);
        ContraseñaEliminar.setBackground(new java.awt.Color(204, 204, 204));
        ContraseñaEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(ContraseñaEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 90, -1));

        jLabel8.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("NOMBRE");
        panelEliminar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel12.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("SEGUNDO NOMBRE");
        panelEliminar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, -1));

        jLabel31.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("PRIMER APELLIDO");
        panelEliminar.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, 20));

        jLabel32.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("SEGUNDO APELLIDO");
        panelEliminar.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel33.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("GENERO");
        panelEliminar.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

        jLabel34.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("CEDULA");
        panelEliminar.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, -1, -1));

        jLabel35.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("TELEFONO");
        panelEliminar.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        jLabel36.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("CORREO");
        panelEliminar.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        jLabel37.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("DIRECCION");
        panelEliminar.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel38.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("USUARIO ");
        panelEliminar.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, -1));

        jLabel39.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("CONTRASEÑA");
        panelEliminar.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, -1, -1));

        btnEliminarCliente.setBackground(new java.awt.Color(0, 153, 51));
        btnEliminarCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        btnEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setText("ELIMINAR CLIENTE");
        panelEliminar.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 160, 30));

        btnCancelarEliminacion.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelarEliminacion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        btnCancelarEliminacion.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarEliminacion.setText("CANCELAR ELIMINACION\n\n");
        panelEliminar.add(btnCancelarEliminacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        panelVentaRecepcionista.addTab("Eliminar", panelEliminar);

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAbrirVentaRecepcionista.setBackground(new java.awt.Color(0, 153, 0));
        btnAbrirVentaRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 36)); // NOI18N
        btnAbrirVentaRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirVentaRecepcionista.setText("ABRIR MENU VENTAS");
        btnAbrirVentaRecepcionista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirVentaRecepcionistaActionPerformed(evt);
            }
        });
        panelMenu.add(btnAbrirVentaRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 450, 210));

        panelVentaRecepcionista.addTab("Ventas", panelMenu);

        getContentPane().add(panelVentaRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 690, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBtnMenuPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuPMouseClicked
        panelVentaRecepcionista.setSelectedIndex(0);
    }//GEN-LAST:event_panelBtnMenuPMouseClicked

    private void panelBtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAgregarMouseClicked
        panelVentaRecepcionista.setSelectedIndex(3);
    }//GEN-LAST:event_panelBtnAgregarMouseClicked

    private void panelBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnActualizarMouseClicked
        panelVentaRecepcionista.setSelectedIndex(1);
    }//GEN-LAST:event_panelBtnActualizarMouseClicked

    private void panelBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEliminarMouseClicked
        panelVentaRecepcionista.setSelectedIndex(2);
    }//GEN-LAST:event_panelBtnEliminarMouseClicked

    private void panelBtnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesionMouseClicked
        // Usar RedireccionPorRol para cerrar sesión correctamente
        if (redireccionRol == null) {
            redireccionRol = new RedireccionPorRol();
        }
        redireccionRol.cerrarSesionYVolverLogin(this);
    }//GEN-LAST:event_panelBtnCerrarSesionMouseClicked

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        try {
            // Validar campos obligatorios
            if (txtPrimerNombreCliente.getText().trim().isEmpty()
                    || txtPrimerApellidoCliente.getText().trim().isEmpty()
                    || txtSegundoApellidoCliente.getText().trim().isEmpty()
                    || txtCedulaCliente.getText().trim().isEmpty()
                    || cboGeneroCliente.getSelectedItem().toString().equals("Seleccionar")
                    || txtTelefonoCliente.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Hay campos que están vacíos", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Generar ID automático
            String idCliente = administradorService.generarSiguienteId("cliente");
            
            // Crear objeto Cliente usando el constructor completo (igual que Administrador)
            Cliente nuevoCliente = new Cliente(
                idCliente,
                txtPrimerNombreCliente.getText().trim(),
                txtSegundoNombreCliente.getText().trim(),
                txtPrimerApellidoCliente.getText().trim(),
                txtSegundoApellidoCliente.getText().trim(),
                cboGeneroCliente.getSelectedItem().toString(),
                txtCedulaCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                txtCorreoCliente.getText().trim(),
                txtUsuarioCliente.getText().trim(),
                txtContraseñaCliente.getText().trim(),
                txtDireccionCliente.getText().trim(),
                "Cliente"
            );
            
            // Agregar a la lista usando el servicio (igual que Administrador)
            administradorService.agregarCliente(nuevoCliente);
            
            // Actualizar tabla
            cargarClientesEnTabla();
            
            // Limpiar campos
            limpiarCamposCliente();
            
            JOptionPane.showMessageDialog(this, 
                "Cliente agregado exitosamente.\n" +
                "ID asignado: " + idCliente, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Cliente agregado: " + nuevoCliente.getUsuario());
            
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al agregar cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnCancelarProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProcessActionPerformed
        
        JOptionPane.showMessageDialog(this, "PROCESO CANCELADO");
        // 1. Limpiar campos de texto (JTextField)
        txtPrimerNombreCliente.setText("");
        txtSegundoNombreCliente.setText("");
        txtPrimerApellidoCliente.setText("");
        txtSegundoApellidoCliente.setText("");
        txtCedulaCliente.setText("");
        txtTelefonoCliente.setText("");
        txtCorreoCliente.setText("");
        txtDireccionCliente.setText("");
        txtUsuarioCliente.setText("");
        txtContraseñaCliente.setText("");

        // 2. Restablecer ComboBox
        if (cboGeneroCliente.getItemCount() > 0) {
            cboGeneroCliente.setSelectedIndex(0);
        }


    }//GEN-LAST:event_btnCancelarProcessActionPerformed

    private void btnBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturaActionPerformed
        try {
            // Validar que el código no esté vacío
            String codigo = txtIdFactura.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese el ID de venta, código serial o código de factura.", 
                    "Código Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar la factura usando LoginService (busca por ID, código serial o código factura)
            // LoginService tiene acceso centralizado a todas las facturas
            facturaActual = loginService.buscarFacturaPorCualquierCodigo(codigo);
            
            // Si no se encuentra en LoginService, intentar en GestorCarrito local
            if (facturaActual == null) {
                facturaActual = gestorCarrito.buscarFacturaPorCualquierCodigo(codigo);
                // Si se encuentra en GestorCarrito, agregarla a LoginService para futuras búsquedas
                if (facturaActual != null) {
                    loginService.agregarFactura(facturaActual);
                }
            }
            
            if (facturaActual != null) {
                // Cargar los datos de la factura en los campos
                cargarDatosFactura(facturaActual);
                JOptionPane.showMessageDialog(this, 
                    "Factura encontrada exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Limpiar campos si no se encuentra
                limpiarCamposFactura();
                JOptionPane.showMessageDialog(this, 
                    "No se encontró una factura con el código: " + codigo, 
                    "Factura No Encontrada", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e) {
            System.out.println("Error al buscar factura: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al buscar factura: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarFacturaActionPerformed

    private void txtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteActionPerformed

    private void txtIdFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdFacturaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdFacturaActionPerformed

    private void btnBuscarClienteEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteEliminarActionPerformed
        try {
            // Validar que la cédula no esté vacía
            String cedula = txtIdClienteEliminar.getText().trim();
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese la cédula del cliente a buscar.", 
                    "Cédula Vacía", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar el cliente por cédula usando el servicio (reutilizando AdministradorService)
            Model.Cliente cliente = administradorService.buscarClientePorCedula(cedula);
            
            if (cliente != null) {
                // Cargar los datos del cliente en los campos
                cargarDatosClienteEliminar(cliente);
                JOptionPane.showMessageDialog(this, 
                    "Cliente encontrado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Limpiar campos si no se encuentra
                limpiarCamposClienteEliminar();
                JOptionPane.showMessageDialog(this, 
                    "No se encontró un cliente con la cédula: " + cedula, 
                    "Cliente No Encontrado", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al buscar cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarClienteEliminarActionPerformed

    private void panelBtnCerrarSesion2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesion2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBtnCerrarSesion2MouseClicked

    private void etiVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etiVentasMouseClicked
        // TODO add your handling code here:
        //new VentaRecepcionista(id).setVisible(true);
         try {
            // Abrir el frame VentaRecepcionista
            View.VentaRecepcionista ventaRecepcionista = new View.VentaRecepcionista();
            ventaRecepcionista.setVisible(true);
            System.out.println("VentaRecepcionista abierto desde Recepcionista");
        } catch (Exception e) {
            System.out.println("Error al abrir VentaRecepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de ventas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_etiVentasMouseClicked

    private void labelCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarSesionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_labelCerrarSesionMouseClicked

    private void PrimerNombreELiminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrimerNombreELiminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrimerNombreELiminarActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        eliminarCliente();
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnCancelarEliminacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEliminacionActionPerformed
        limpiarCamposClienteEliminar();
        JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
    }//GEN-LAST:event_btnCancelarEliminacionActionPerformed

    private void txtIdClienteActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActualizar1ActionPerformed
        // Este campo parece estar duplicado, no se usa
    }//GEN-LAST:event_txtIdClienteActualizar1ActionPerformed
    
    private void btnLimpiarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFacturaActionPerformed
        limpiarCamposFactura();
        JOptionPane.showMessageDialog(this, "Datos de factura limpiados.");
    }//GEN-LAST:event_btnLimpiarFacturaActionPerformed

    private void btnAprobarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarFacturaActionPerformed
        try {
            // Validar que haya una factura encontrada
            if (facturaActual == null) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, busque una factura primero.", 
                    "Factura No Seleccionada", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verificar si la factura puede ser despachada
            if (!facturaActual.puedeSerDespachada()) {
                JOptionPane.showMessageDialog(this, 
                    "Esta factura no puede ser despachada.\n" +
                    "Estado: " + facturaActual.getEstadoTexto() + "\n" +
                    "Despachada: " + (facturaActual.isDespachada() ? "Sí" : "No"), 
                    "No Se Puede Despachar", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Confirmar aprobación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea aprobar esta factura?\n\n" +
                "Código Factura: " + facturaActual.getCodigoFactura() + "\n" +
                "Código Venta: " + facturaActual.getCodigoSerial() + "\n" +
                "Cliente: " + facturaActual.getCliente().getPrimerNombre() + " " + 
                           facturaActual.getCliente().getPrimerApellido() + "\n" +
                "Total: $" + String.format("%.2f", facturaActual.getTotal()) + "\n\n" +
                "Al aprobar, la factura será marcada como despachada.",
                "Confirmar Aprobación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Marcar la factura como despachada
                facturaActual.marcarComoDespachada();
                
                JOptionPane.showMessageDialog(this, 
                    "Factura aprobada y marcada como despachada exitosamente.\n" +
                    "Código: " + facturaActual.getCodigoFactura(), 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                System.out.println("Factura aprobada: " + facturaActual.getCodigoFactura());
                
                // Actualizar los campos para reflejar el cambio
                cargarDatosFactura(facturaActual);
            }
            
        } catch (Exception e) {
            System.out.println("Error al aprobar factura: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al aprobar factura: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAprobarFacturaActionPerformed

    private void btnCancelarBusquedaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarBusquedaFacturaActionPerformed
        limpiarCamposFactura();
        JOptionPane.showMessageDialog(this, "Búsqueda cancelada.");
    }//GEN-LAST:event_btnCancelarBusquedaFacturaActionPerformed

    private void btnAbrirVentaRecepcionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirVentaRecepcionistaActionPerformed
        try {
            // Abrir el frame VentaRecepcionista
            View.VentaRecepcionista ventaRecepcionista = new View.VentaRecepcionista();
            ventaRecepcionista.setVisible(true);
            System.out.println("VentaRecepcionista abierto desde Recepcionista");
        } catch (Exception e) {
            System.out.println("Error al abrir VentaRecepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de ventas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAbrirVentaRecepcionistaActionPerformed

    // ========================================
    // MÉTODOS PARA GESTIONAR CLIENTES
    // ========================================
    
    /**
     * Carga los clientes en la tabla (método mantenido para compatibilidad)
     * Nota: La tabla jTable1 fue eliminada del panelMenu, este método ahora solo valida
     */
    private void cargarClientesEnTabla() {
        try {
            listaDoble.Lista listaClientes = administradorService.obtenerTodosLosClientes();
            
            if (listaClientes != null && listaClientes.getPrimero() != null) {
                System.out.println("Clientes disponibles en el sistema");
            } else {
                System.out.println("No hay clientes para mostrar");
            }
            
        } catch (Exception e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al cargar la tabla de clientes: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Limpia los campos del formulario de cliente
     */
    private void limpiarCamposCliente() {
        txtPrimerNombreCliente.setText("");
        txtSegundoNombreCliente.setText("");
        txtPrimerApellidoCliente.setText("");
        txtSegundoApellidoCliente.setText("");
        txtCedulaCliente.setText("");
        txtTelefonoCliente.setText("");
        txtCorreoCliente.setText("");
        txtDireccionCliente.setText("");
        txtUsuarioCliente.setText("");
        txtContraseñaCliente.setText("");
        
        // Restablecer ComboBox
        if (cboGeneroCliente.getItemCount() > 0) {
            cboGeneroCliente.setSelectedIndex(0);
        }
        
        System.out.println("Campos de cliente limpiados");
    }
    
    /**
     * Carga los datos del cliente en los campos del panel de eliminar
     */
    private void cargarDatosClienteEliminar(Model.Cliente cliente) {
        if (cliente == null) {
            return;
        }
        
        PrimerNombreELiminar.setText(cliente.getPrimerNombre() != null ? cliente.getPrimerNombre() : "");
        SegundoNombreEliminar.setText(cliente.getSegundoNombre() != null ? cliente.getSegundoNombre() : "");
        PrimerApellidoEliminar.setText(cliente.getPrimerApellido() != null ? cliente.getPrimerApellido() : "");
        SegundoApellidoEliminar.setText(cliente.getSegundoApellido() != null ? cliente.getSegundoApellido() : "");
        GeneroEliminar.setText(cliente.getGenero() != null ? cliente.getGenero() : "");
        CedulaEliminar.setText(cliente.getCedula() != null ? cliente.getCedula() : "");
        TelefonoEliminar.setText(cliente.getTelefono() != null ? cliente.getTelefono() : "");
        CorreoEliminar.setText(cliente.getCorreo() != null ? cliente.getCorreo() : "");
        DireccionELiminar.setText(cliente.getDireccion() != null ? cliente.getDireccion() : "");
        UsuarioEliminar.setText(cliente.getUsuario() != null ? cliente.getUsuario() : "");
        ContraseñaEliminar.setText(cliente.getContraseña() != null ? cliente.getContraseña() : "");
        
        System.out.println("Datos del cliente cargados para eliminar: " + cliente.getPrimerNombre());
    }
    
    /**
     * Limpia los campos del panel de eliminar
     */
    private void limpiarCamposClienteEliminar() {
        txtIdClienteEliminar.setText("");
        PrimerNombreELiminar.setText("");
        SegundoNombreEliminar.setText("");
        PrimerApellidoEliminar.setText("");
        SegundoApellidoEliminar.setText("");
        GeneroEliminar.setText("");
        CedulaEliminar.setText("");
        TelefonoEliminar.setText("");
        CorreoEliminar.setText("");
        DireccionELiminar.setText("");
        UsuarioEliminar.setText("");
        ContraseñaEliminar.setText("");
        System.out.println("Campos de eliminar cliente limpiados");
    }
    
    // ========================================
    // MÉTODOS PARA GESTIONAR FACTURAS
    // ========================================
    
    /**
     * Carga los datos de la factura en los campos del panel
     */
    private void cargarDatosFactura(Factura factura) {
        if (factura == null) {
            return;
        }
        
        // Cargar datos en txtIdClienteActualizar1 (parece ser el campo para mostrar datos)
        if (factura.getCliente() != null) {
            txtIdClienteActualizar1.setText(
                "Factura: " + factura.getCodigoFactura() + 
                " | Venta: " + factura.getCodigoSerial() +
                " | Cliente: " + factura.getCliente().getPrimerNombre() + " " + 
                               factura.getCliente().getPrimerApellido() +
                " | Total: $" + String.format("%.2f", factura.getTotal()) +
                " | Estado: " + factura.getEstadoTexto() +
                " | Despachada: " + (factura.isDespachada() ? "Sí" : "No")
            );
        }
        
        System.out.println("Datos de factura cargados: " + factura.getCodigoFactura());
    }
    
    /**
     * Limpia los campos del panel de facturas
     */
    private void limpiarCamposFactura() {
        txtIdFactura.setText("");
        txtIdClienteActualizar1.setText("");
        facturaActual = null;
        System.out.println("Campos de factura limpiados");
    }
    
    /**
     * Elimina el cliente del sistema
     */
    private void eliminarCliente() {
        try {
            // Validar que haya una cédula ingresada
            String cedula = txtIdClienteEliminar.getText().trim();
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, busque un cliente primero ingresando su cédula.", 
                    "Cédula Vacía", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Buscar el cliente por cédula para obtener su ID y mostrar sus datos en la confirmación
            Model.Cliente cliente = administradorService.buscarClientePorCedula(cedula);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontró un cliente con la cédula: " + cedula, 
                    "Cliente No Encontrado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Obtener el ID del cliente encontrado para eliminarlo
            String idCliente = cliente.getIdCliente();
            
            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar al cliente?\n\n" +
                "ID: " + cliente.getIdCliente() + "\n" +
                "Nombre: " + cliente.getPrimerNombre() + " " + cliente.getPrimerApellido() + "\n" +
                "Cédula: " + cliente.getCedula() + "\n\n" +
                "Esta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Eliminar el cliente usando el ID (reutilizando AdministradorService)
                boolean eliminado = administradorService.eliminarCliente(idCliente);
                
                if (eliminado) {
                    // Actualizar tabla
                    cargarClientesEnTabla();
                    // Limpiar campos
                    limpiarCamposClienteEliminar();
                    
                    JOptionPane.showMessageDialog(this, 
                        "Cliente eliminado exitosamente.", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Cliente eliminado: " + idCliente);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Error al eliminar el cliente.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al eliminar cliente: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Proveedor p = Sesion.proveedorActual;
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recepcionista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CedulaEliminar;
    private javax.swing.JTextField ContraseñaEliminar;
    private javax.swing.JTextField CorreoEliminar;
    private javax.swing.JTextField DireccionELiminar;
    private javax.swing.JTextField GeneroEliminar;
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelFacturas;
    private javax.swing.JPanel PanelNegro2;
    private javax.swing.JTextField PrimerApellidoEliminar;
    private javax.swing.JTextField PrimerNombreELiminar;
    private javax.swing.JTextField SegundoApellidoEliminar;
    private javax.swing.JTextField SegundoNombreEliminar;
    private javax.swing.JTextField TelefonoEliminar;
    private javax.swing.JTextField UsuarioEliminar;
    private javax.swing.JButton btnAbrirVentaRecepcionista;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAprobarFactura;
    private javax.swing.JButton btnBuscarClienteEliminar;
    private javax.swing.JButton btnBuscarFactura;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarBusquedaFactura;
    private javax.swing.JButton btnCancelarEliminacion;
    private javax.swing.JButton btnCancelarProcess;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnLimpiarFactura;
    private javax.swing.JComboBox<String> cboGeneroCliente;
    private javax.swing.JLabel etiVentas;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconCerrarSesion2;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelRecepcionisa1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcionRepuesto;
    private javax.swing.JLabel lblMarcaRepuesto;
    private javax.swing.JLabel lblNombreRepuesto;
    private javax.swing.JLabel lblPrecioRepuesto;
    private javax.swing.JLabel lblRepuesto;
    private javax.swing.JLabel lblRepuesto1;
    private javax.swing.JLabel lblRepuesto2;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel panelBtnActualizar;
    private javax.swing.JPanel panelBtnAgregar;
    private javax.swing.JPanel panelBtnCerrarSesion;
    private javax.swing.JPanel panelBtnCerrarSesion2;
    private javax.swing.JPanel panelBtnEliminar;
    private javax.swing.JPanel panelBtnMenuP;
    private javax.swing.JPanel panelDecoración;
    private javax.swing.JPanel panelDecoración1;
    private javax.swing.JPanel panelDecoración10;
    private javax.swing.JPanel panelDecoración11;
    private javax.swing.JPanel panelDecoración2;
    private javax.swing.JPanel panelDecoración3;
    private javax.swing.JPanel panelDecoración4;
    private javax.swing.JPanel panelDecoración5;
    private javax.swing.JPanel panelDecoración6;
    private javax.swing.JPanel panelDecoración7;
    private javax.swing.JPanel panelDecoración8;
    private javax.swing.JPanel panelDecoración9;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelGris;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNegro;
    private javax.swing.JTabbedPane panelVentaRecepcionista;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtContraseñaCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtIdClienteActualizar1;
    private javax.swing.JTextField txtIdClienteEliminar;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtPrimerApellidoCliente;
    private javax.swing.JTextField txtPrimerNombreCliente;
    private javax.swing.JTextField txtSegundoApellidoCliente;
    private javax.swing.JTextField txtSegundoNombreCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtUsuarioCliente;
    // End of variables declaration//GEN-END:variables

    //Verificar que un String solo tenga números.
    public boolean soloNumeros(String texto) {
        return texto.matches("\\d+");
    }
}
