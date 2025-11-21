package View;

import Model.Repuesto;
import Percistencia.ActualizarRepuestoId;
import Percistencia.BuscarRepuestoId;
import Percistencia.EliminarRepuestoId;
import Percistencia.ExisteRepuestoId;
import Percistencia.SistemaDatos;
import Service.LoginService;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import listaDoble.Lista;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listaDoble.Nodo;

/**
 *
 * @author Adrian
 */
public class Proveedor extends javax.swing.JFrame {

    //Listas y utilidades
  

    //Variable global
    private Model.Proveedor prov;

    //Métodos
    private boolean repuestosCargados = false;
    ExisteRepuestoId buscar_repuesto = new ExisteRepuestoId();
    BuscarRepuestoId buscador = new BuscarRepuestoId();
    EliminarRepuestoId eliminar = new EliminarRepuestoId();
    ActualizarRepuestoId actualizar = new ActualizarRepuestoId();

    //Servicios
    private LoginService loginService;
    private String idProveedorActual;

    public Proveedor() {
        initComponents();
        ocultarZonaDeActualizar();
        inicializarServicios();
        
        cargarTabla(tablaRepuestos, SistemaDatos.getInstancia().listaRepuestos);

        setLocationRelativeTo(null);
        jspinnerStock.setModel(modelo);
        spiActualizarStock.setModel(modelo2);
        btnLimpiarCampoDelete.setEnabled(false);
        inicializarServicios();
        cargarTabla(tablaRepuestos, SistemaDatos.getInstancia().listaRepuestos
);
    }

    //Constructor con ID
    public Proveedor(String idProveedor) {
        initComponents();
        ocultarZonaDeActualizar();
        inicializarServicios();
        precargarDatosProveedorPorId(idProveedor);
            cargarTabla(tablaRepuestos, SistemaDatos.getInstancia().listaRepuestos);

        jspinnerStock.setModel(modelo);
        spiActualizarStock.setModel(modelo2);
        btnLimpiarCampoDelete.setEnabled(false);
        setLocationRelativeTo(null);
        cargarTabla(tablaRepuestos, SistemaDatos.getInstancia().listaRepuestos
);

    }

    private void inicializarServicios() {
        loginService = new LoginService();
        System.out.println("Servicios inicializados para proveedor");
    }

    private void precargarDatosProveedorPorId(String idProveedor) {
        if (idProveedor == null || idProveedor.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Error: ID de proveedor no válido.",
                    "Error de Datos", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }

        // Buscar proveedor por ID usando el servicio
        prov = loginService.buscarProveedorPorId(idProveedor);

        if (prov != null) {
            // Guardar el ID para uso posterior
            this.idProveedorActual = idProveedor;

            // Precargar datos en los campos del panel de inicio
            lblNombreProv.setText(prov.getPrimerNombre() + " " + (prov.getPrimerApellido()));

        } else {
            System.out.println("No se encontró proveedor con ID: " + idProveedor);
            JOptionPane.showMessageDialog(this,
                    "Error: No se pudo encontrar la información del administrador.\n"
                    + "ID: " + idProveedor,
                    "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 100, 1);
    SpinnerNumberModel modelo2 = new SpinnerNumberModel(1, 0, 100, 1);

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNegro = new javax.swing.JPanel();
        panelDecoración = new javax.swing.JPanel();
        panelBtnMenuP = new javax.swing.JPanel();
        iconInicio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelBtnAgregar = new javax.swing.JPanel();
        iconAgregarPaciente = new javax.swing.JLabel();
        labelAgregarPaciente = new javax.swing.JLabel();
        iconRepuesto = new javax.swing.JLabel();
        labelMenúOpciones = new javax.swing.JLabel();
        panelBtnActualizar = new javax.swing.JPanel();
        iconEditarPaciente = new javax.swing.JLabel();
        labelEditarPaciente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBtnEliminar = new javax.swing.JPanel();
        iconAgendarCita = new javax.swing.JLabel();
        labelEditarPaciente1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBtnCerrarSesion = new javax.swing.JPanel();
        iconCerrarSesion = new javax.swing.JLabel();
        labelCerrarSesion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        iconRecepcinista = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelDecoración1 = new javax.swing.JPanel();
        panelDecoración2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelGris = new javax.swing.JPanel();
        labelRecepcionisa1 = new javax.swing.JLabel();
        PanelNegro2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMenu = new javax.swing.JPanel();
        lblNombreProv = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaRepuestos = new javax.swing.JTable();
        panelDecoración12 = new javax.swing.JPanel();
        panelDecoración13 = new javax.swing.JPanel();
        panelDecoración14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnReiniciarTablaRepuestos = new javax.swing.JButton();
        PanelActualizar = new javax.swing.JPanel();
        panelDecoración6 = new javax.swing.JPanel();
        panelDecoración7 = new javax.swing.JPanel();
        panelDecoración8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblActualizarPrecio = new javax.swing.JLabel();
        txtActualizarRepuesto = new javax.swing.JTextField();
        btnActualizarR = new javax.swing.JButton();
        btnCancelarR = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblRepuesto9 = new javax.swing.JLabel();
        cboSolicitudActualizar = new javax.swing.JComboBox<>();
        btnBuscarParaAct = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lblQuéQuieresActualizar1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtActualizarPrecioR = new javax.swing.JTextField();
        lblDigitaStock = new javax.swing.JLabel();
        spiActualizarStock = new javax.swing.JSpinner();
        lblSelecEstadoN = new javax.swing.JLabel();
        cboActualizarEstado = new javax.swing.JComboBox<>();
        panelEliminar = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelDecoración9 = new javax.swing.JPanel();
        panelDecoración10 = new javax.swing.JPanel();
        panelDecoración11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblRepuesto2 = new javax.swing.JLabel();
        btnEliminarRepuesto = new javax.swing.JButton();
        btnLimpiarCampoDelete = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtCargarMarcaRepuesto = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblRepuesto3 = new javax.swing.JLabel();
        txtIdRepuestoDelete1 = new javax.swing.JTextField();
        lblRepuesto4 = new javax.swing.JLabel();
        lblRepuesto5 = new javax.swing.JLabel();
        txtCargarPrecioRepuesto = new javax.swing.JTextField();
        lblRepuesto6 = new javax.swing.JLabel();
        txtEliminarRepuestoId = new javax.swing.JTextField();
        lblRepuesto7 = new javax.swing.JLabel();
        txtCargarCategoriaRepuesto1 = new javax.swing.JTextField();
        lblRepuesto8 = new javax.swing.JLabel();
        txtCargarNombreRepuesto3 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btnBuscarParaEliminar1 = new javax.swing.JButton();
        PanelAgregar = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelDecoración3 = new javax.swing.JPanel();
        panelDecoración4 = new javax.swing.JPanel();
        panelDecoración5 = new javax.swing.JPanel();
        lblRepuesto = new javax.swing.JLabel();
        cboMarcaRepuesto = new javax.swing.JComboBox<>();
        lblCOP = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtareaDescripcion = new javax.swing.JTextArea();
        lblMarcaRepuesto = new javax.swing.JLabel();
        lblDescripcionRepuesto = new javax.swing.JLabel();
        txtNombreRepuesto = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        lblPrecioRepuesto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jspinnerStock = new javax.swing.JSpinner();
        lblNombreRepuesto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtIdRepuesto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

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
        jLabel3.setText("PANEL DE INICIO");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Home.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnMenuPLayout = new javax.swing.GroupLayout(panelBtnMenuP);
        panelBtnMenuP.setLayout(panelBtnMenuPLayout);
        panelBtnMenuPLayout.setHorizontalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnMenuPLayout.setVerticalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuPLayout.createSequentialGroup()
                        .addGroup(panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))
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
        labelAgregarPaciente.setText("AGREGAR REPUESTO");

        iconRepuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/repuesto.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnAgregarLayout = new javax.swing.GroupLayout(panelBtnAgregar);
        panelBtnAgregar.setLayout(panelBtnAgregarLayout);
        panelBtnAgregarLayout.setHorizontalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgregarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconRepuesto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAgregarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnAgregarLayout.setVerticalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconRepuesto)
                    .addGroup(panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAgregarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelMenúOpciones.setFont(new java.awt.Font("JetBrains Mono", 2, 18)); // NOI18N
        labelMenúOpciones.setForeground(new java.awt.Color(255, 255, 255));
        labelMenúOpciones.setText("OPCIONES EN EL MENÚ:");

        panelBtnActualizar.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnActualizarMouseClicked(evt);
            }
        });

        labelEditarPaciente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente.setText("CAMBIAR REPUESTO");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/actualizarRepuesto.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnActualizarLayout = new javax.swing.GroupLayout(panelBtnActualizar);
        panelBtnActualizar.setLayout(panelBtnActualizarLayout);
        panelBtnActualizarLayout.setHorizontalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEditarPaciente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnActualizarLayout.setVerticalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEditarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBtnEliminar.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnEliminarMouseClicked(evt);
            }
        });

        labelEditarPaciente1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelEditarPaciente1.setForeground(new java.awt.Color(255, 255, 255));
        labelEditarPaciente1.setText("ELIMINAR REPUESTO");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/eliminarRepuesto.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnEliminarLayout = new javax.swing.GroupLayout(panelBtnEliminar);
        panelBtnEliminar.setLayout(panelBtnEliminarLayout);
        panelBtnEliminarLayout.setHorizontalGroup(
            panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgendarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelEditarPaciente1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnEliminarLayout.setVerticalGroup(
            panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconAgendarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEditarPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/salida.png"))); // NOI18N

        javax.swing.GroupLayout panelBtnCerrarSesionLayout = new javax.swing.GroupLayout(panelBtnCerrarSesion);
        panelBtnCerrarSesion.setLayout(panelBtnCerrarSesionLayout);
        panelBtnCerrarSesionLayout.setHorizontalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconCerrarSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesionLayout.setVerticalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(labelCerrarSesion))
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

        javax.swing.GroupLayout panelDecoración2Layout = new javax.swing.GroupLayout(panelDecoración2);
        panelDecoración2.setLayout(panelDecoración2Layout);
        panelDecoración2Layout.setHorizontalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        panelDecoración2Layout.setVerticalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
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

        javax.swing.GroupLayout panelNegroLayout = new javax.swing.GroupLayout(panelNegro);
        panelNegro.setLayout(panelNegroLayout);
        panelNegroLayout.setHorizontalGroup(
            panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnMenuP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(iconRecepcinista))
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(labelMenúOpciones))
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13))
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(44, 44, 44)
                .addComponent(iconRecepcinista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(labelMenúOpciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnMenuP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelNegroLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel14)
                    .addContainerGap(595, Short.MAX_VALUE)))
        );

        getContentPane().add(panelNegro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 660));

        panelGris.setBackground(new java.awt.Color(51, 51, 51));

        labelRecepcionisa1.setBackground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 85)); // NOI18N
        labelRecepcionisa1.setForeground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setText("-PROVEEDORES-");

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
            .addGap(0, 130, Short.MAX_VALUE)
        );

        getContentPane().add(PanelNegro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 690, 130));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreProv.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        lblNombreProv.setForeground(new java.awt.Color(0, 0, 0));
        panelMenu.add(lblNombreProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 260, 20));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        tablaRepuestos.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        tablaRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Categoria", "Nombre", "Precio", "Stock", "Proveedor", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaRepuestos);

        panelMenu.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 660, 280));

        panelDecoración12.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración12Layout = new javax.swing.GroupLayout(panelDecoración12);
        panelDecoración12.setLayout(panelDecoración12Layout);
        panelDecoración12Layout.setHorizontalGroup(
            panelDecoración12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración12Layout.setVerticalGroup(
            panelDecoración12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelMenu.add(panelDecoración12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

        panelDecoración13.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración13Layout = new javax.swing.GroupLayout(panelDecoración13);
        panelDecoración13.setLayout(panelDecoración13Layout);
        panelDecoración13Layout.setHorizontalGroup(
            panelDecoración13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración13Layout.setVerticalGroup(
            panelDecoración13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelMenu.add(panelDecoración13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        panelDecoración14.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración14Layout = new javax.swing.GroupLayout(panelDecoración14);
        panelDecoración14.setLayout(panelDecoración14Layout);
        panelDecoración14Layout.setHorizontalGroup(
            panelDecoración14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración14Layout.setVerticalGroup(
            panelDecoración14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelMenu.add(panelDecoración14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bienvenido.png"))); // NOI18N
        panelMenu.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel29.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("RECUERDA HACER TUS TAREAS CON RESPONSABILIDAD Y BUEN MANEJO DE DATOS.");
        panelMenu.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel32.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("BIENVENIDO/A");
        panelMenu.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 140, -1));

        btnReiniciarTablaRepuestos.setBackground(new java.awt.Color(204, 204, 255));
        btnReiniciarTablaRepuestos.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        btnReiniciarTablaRepuestos.setForeground(new java.awt.Color(0, 0, 0));
        btnReiniciarTablaRepuestos.setText("REINICIAR");
        btnReiniciarTablaRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarTablaRepuestosActionPerformed(evt);
            }
        });
        panelMenu.add(btnReiniciarTablaRepuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jTabbedPane1.addTab("Menú", panelMenu);

        PanelActualizar.setBackground(new java.awt.Color(255, 255, 255));
        PanelActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        PanelActualizar.add(panelDecoración6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

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

        PanelActualizar.add(panelDecoración7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

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

        PanelActualizar.add(panelDecoración8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/actualizar.png"))); // NOI18N
        PanelActualizar.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("¡PUEDES ACTUALIZAR ALGÚN REPUESTO SOLO CON SU ID!");
        PanelActualizar.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel23.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("- BÚSQUEDA RÁPIDA Y EFICIENTE EN EL SISTEMA.");
        PanelActualizar.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblActualizarPrecio.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblActualizarPrecio.setForeground(new java.awt.Color(0, 0, 0));
        lblActualizarPrecio.setText("DIGITA EL PRECIO PARA ACTUALIZAR:");
        PanelActualizar.add(lblActualizarPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, -1));

        txtActualizarRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        txtActualizarRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtActualizarRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        PanelActualizar.add(txtActualizarRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        btnActualizarR.setBackground(new java.awt.Color(0, 153, 0));
        btnActualizarR.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnActualizarR.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarR.setText("ACTUALIZAR");
        btnActualizarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarRActionPerformed(evt);
            }
        });
        PanelActualizar.add(btnActualizarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, 30));

        btnCancelarR.setBackground(new java.awt.Color(153, 0, 0));
        btnCancelarR.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnCancelarR.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarR.setText("X");
        btnCancelarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRActionPerformed(evt);
            }
        });
        PanelActualizar.add(btnCancelarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 80, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        PanelActualizar.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, -1, -1));

        jPanel4.setBackground(new java.awt.Color(216, 216, 217));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        PanelActualizar.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 10));

        lblRepuesto9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto9.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto9.setText("1. INGRESE EL ID DEL REPUESTO PARA HALLARLO EN EL SISTEMA:");
        PanelActualizar.add(lblRepuesto9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        cboSolicitudActualizar.setBackground(new java.awt.Color(204, 204, 204));
        cboSolicitudActualizar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        cboSolicitudActualizar.setForeground(new java.awt.Color(0, 0, 0));
        cboSolicitudActualizar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--------------------------", "Precio", "Stock", "Estado" }));
        cboSolicitudActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSolicitudActualizarActionPerformed(evt);
            }
        });
        PanelActualizar.add(cboSolicitudActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 220, -1));

        btnBuscarParaAct.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscarParaAct.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnBuscarParaAct.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarParaAct.setText("BUSCAR");
        btnBuscarParaAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarParaActActionPerformed(evt);
            }
        });
        PanelActualizar.add(btnBuscarParaAct, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 100, 30));

        jButton6.setBackground(new java.awt.Color(204, 204, 255));
        jButton6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("LIMPIAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        PanelActualizar.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 90, 30));

        lblQuéQuieresActualizar1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblQuéQuieresActualizar1.setForeground(new java.awt.Color(0, 0, 0));
        lblQuéQuieresActualizar1.setText("¿QUÉ DATO QUIERES MODIFICAR?");
        PanelActualizar.add(lblQuéQuieresActualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        PanelActualizar.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 10, 210));

        txtActualizarPrecioR.setBackground(new java.awt.Color(204, 204, 204));
        txtActualizarPrecioR.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtActualizarPrecioR.setForeground(new java.awt.Color(0, 0, 0));
        PanelActualizar.add(txtActualizarPrecioR, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 120, -1));

        lblDigitaStock.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblDigitaStock.setForeground(new java.awt.Color(0, 0, 0));
        lblDigitaStock.setText("DIGITA EL NUEVO STOCK:");
        PanelActualizar.add(lblDigitaStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, -1));

        spiActualizarStock.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        PanelActualizar.add(spiActualizarStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 190, -1));

        lblSelecEstadoN.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblSelecEstadoN.setForeground(new java.awt.Color(0, 0, 0));
        lblSelecEstadoN.setText("SELECCIONA EL NUEVO ESTADO DEL REPUESTO:");
        PanelActualizar.add(lblSelecEstadoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, -1, -1));

        cboActualizarEstado.setBackground(new java.awt.Color(204, 204, 205));
        cboActualizarEstado.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        cboActualizarEstado.setForeground(new java.awt.Color(0, 0, 0));
        cboActualizarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Disponible", "Retirado", "Agotado" }));
        PanelActualizar.add(cboActualizarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 220, -1));

        jTabbedPane1.addTab("Actualizar", PanelActualizar);

        panelEliminar.setBackground(new java.awt.Color(255, 255, 255));
        panelEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("¡REMUEVE ALGÚN REPUESTO DEL SISTEMA!");
        panelEliminar.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 0, 0));
        jLabel26.setText("REVISE BIEN EL NÚMERO DEL ID REPUESTO, YA QUE SÓLO BASTA ESTE NÚMERO PARA PROCEDER A ELIMINAR.");
        panelEliminar.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

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
        lblRepuesto2.setText("MARCA:");
        panelEliminar.add(lblRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        btnEliminarRepuesto.setBackground(new java.awt.Color(102, 0, 0));
        btnEliminarRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnEliminarRepuesto.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarRepuesto.setText("ELIMINAR");
        btnEliminarRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRepuestoActionPerformed(evt);
            }
        });
        panelEliminar.add(btnEliminarRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 150, 30));

        btnLimpiarCampoDelete.setBackground(new java.awt.Color(153, 153, 255));
        btnLimpiarCampoDelete.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnLimpiarCampoDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarCampoDelete.setText("LIMPIAR");
        btnLimpiarCampoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCampoDeleteActionPerformed(evt);
            }
        });
        panelEliminar.add(btnLimpiarCampoDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 110, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        panelEliminar.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        txtCargarMarcaRepuesto.setEditable(false);
        txtCargarMarcaRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        txtCargarMarcaRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCargarMarcaRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtCargarMarcaRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 220, 30));

        jPanel5.setBackground(new java.awt.Color(216, 216, 217));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panelEliminar.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 690, 10));

        lblRepuesto3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto3.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto3.setText("¡AQUÍ PUEDE HACER LA CONSULTA DEL REPUESTO CON SOLO EL ID!");
        panelEliminar.add(lblRepuesto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtIdRepuestoDelete1.setBackground(new java.awt.Color(204, 204, 204));
        txtIdRepuestoDelete1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdRepuestoDelete1.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtIdRepuestoDelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        lblRepuesto4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto4.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto4.setText("DIGITE EL ID PARA ELIMINAR:");
        panelEliminar.add(lblRepuesto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        lblRepuesto5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto5.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto5.setText("PRECIO:");
        panelEliminar.add(lblRepuesto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 60, -1));

        txtCargarPrecioRepuesto.setEditable(false);
        txtCargarPrecioRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        txtCargarPrecioRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCargarPrecioRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtCargarPrecioRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 130, 30));

        lblRepuesto6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto6.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto6.setText("NOMBRE:");
        panelEliminar.add(lblRepuesto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txtEliminarRepuestoId.setBackground(new java.awt.Color(204, 204, 204));
        txtEliminarRepuestoId.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtEliminarRepuestoId.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtEliminarRepuestoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 220, 30));

        lblRepuesto7.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto7.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto7.setText("CATEGORÍA:");
        panelEliminar.add(lblRepuesto7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        txtCargarCategoriaRepuesto1.setEditable(false);
        txtCargarCategoriaRepuesto1.setBackground(new java.awt.Color(204, 204, 204));
        txtCargarCategoriaRepuesto1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCargarCategoriaRepuesto1.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtCargarCategoriaRepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 220, 30));

        lblRepuesto8.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto8.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto8.setText("DATOS DE LA BÚSQUEDA POR ID:");
        panelEliminar.add(lblRepuesto8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txtCargarNombreRepuesto3.setEditable(false);
        txtCargarNombreRepuesto3.setBackground(new java.awt.Color(204, 204, 204));
        txtCargarNombreRepuesto3.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCargarNombreRepuesto3.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtCargarNombreRepuesto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 220, 30));

        jLabel31.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("- DE MANERA MUY EFICIENTE EN EL SISTEMA, PERO TEN CUIDADO DE ELIMINAR EL INCORRECTO.");
        panelEliminar.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        btnBuscarParaEliminar1.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscarParaEliminar1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnBuscarParaEliminar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarParaEliminar1.setText("BUSCAR");
        btnBuscarParaEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarParaEliminar1ActionPerformed(evt);
            }
        });
        panelEliminar.add(btnBuscarParaEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 100, 30));

        jTabbedPane1.addTab("Eliminar", panelEliminar);

        PanelAgregar.setBackground(new java.awt.Color(255, 255, 255));
        PanelAgregar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("- AL MEJOR PRECIO.");
        PanelAgregar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 8)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("* ¡Recuerda colocar las especificaciones del repuesto!");
        PanelAgregar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 270, 10));

        panelDecoración3.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración3Layout = new javax.swing.GroupLayout(panelDecoración3);
        panelDecoración3.setLayout(panelDecoración3Layout);
        panelDecoración3Layout.setHorizontalGroup(
            panelDecoración3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración5Layout.setVerticalGroup(
            panelDecoración5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAgregar.add(panelDecoración5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        lblRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto.setText("1. ID DEL REPUESTO:");
        PanelAgregar.add(lblRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        cboMarcaRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        cboMarcaRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        cboMarcaRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        cboMarcaRepuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Kawasaki", "Yamaha", "BMW", "Ducati", "Bajaj", "Hero", "KTM", "Honda", "Suzuki" }));
        PanelAgregar.add(cboMarcaRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 210, -1));

        lblCOP.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        lblCOP.setForeground(new java.awt.Color(0, 153, 0));
        lblCOP.setText("COP");
        PanelAgregar.add(lblCOP, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 40, -1));

        cboCategoria.setBackground(new java.awt.Color(204, 204, 204));
        cboCategoria.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        cboCategoria.setForeground(new java.awt.Color(0, 0, 0));
        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Motor", "Transmisión", "Frenos", "Eléctrico", "Suspensión", "Carrocería", "Lubricantes" }));
        PanelAgregar.add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 220, -1));

        lblCategoria.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(0, 0, 0));
        lblCategoria.setText("6. CATEGORÍA (USO/ENFOQUE):");
        PanelAgregar.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 220, -1));

        txtPrecio.setBackground(new java.awt.Color(204, 204, 204));
        txtPrecio.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 140, -1));

        jtxtareaDescripcion.setBackground(new java.awt.Color(204, 204, 204));
        jtxtareaDescripcion.setColumns(20);
        jtxtareaDescripcion.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jtxtareaDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        jtxtareaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jtxtareaDescripcion);

        PanelAgregar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 270, -1));

        lblMarcaRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblMarcaRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto.setText("3. MARCA DEL REPUESTO:");
        PanelAgregar.add(lblMarcaRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, -1));

        lblDescripcionRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblDescripcionRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcionRepuesto.setText("4. DESCRIPCIÓN ACERCA DEL REPUESTO");
        PanelAgregar.add(lblDescripcionRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtNombreRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtNombreRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtNombreRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 160, -1));

        lblStock.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblStock.setForeground(new java.awt.Color(0, 0, 0));
        lblStock.setText("6. STOCK/CANTIDAD:");
        PanelAgregar.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

        lblPrecioRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblPrecioRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecioRepuesto.setText("5. PRECIO DEL REPUESTO");
        PanelAgregar.add(lblPrecioRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        jPanel2.setBackground(new java.awt.Color(229, 229, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAgregar.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 10, 150));

        jspinnerStock.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        PanelAgregar.add(jspinnerStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 140, -1));

        lblNombreRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblNombreRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto.setText("2. NOMBRE DEL REPUESTO:");
        PanelAgregar.add(lblNombreRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CANCELAR PROCESO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelAgregar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 200, 30));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/piezas-de-repuesto.png"))); // NOI18N
        PanelAgregar.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtIdRepuesto.setBackground(new java.awt.Color(204, 204, 204));
        txtIdRepuesto.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        PanelAgregar.add(txtIdRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 140, -1));

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
            .addGap(0, 40, Short.MAX_VALUE)
        );

        PanelAgregar.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 690, 40));

        jButton2.setBackground(new java.awt.Color(0, 153, 0));
        jButton2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("AGREGAR REPUESTO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelAgregar.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 200, 30));

        jLabel16.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LLENA LOS CAMPOS PARA AGREGAR UN REPUESTO AL INVENTARIO");
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

        jTabbedPane1.addTab("Agregar", PanelAgregar);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 690, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBtnMenuPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnMenuPMouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_panelBtnMenuPMouseClicked

    private void panelBtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnAgregarMouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_panelBtnAgregarMouseClicked

    private void panelBtnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnActualizarMouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_panelBtnActualizarMouseClicked

    private void panelBtnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEliminarMouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_panelBtnEliminarMouseClicked

    private void panelBtnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesionMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelBtnCerrarSesionMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Aquí empecé el 15 de octubre

        String idRepuesto = txtIdRepuesto.getText(); //Números
        String nombre = txtNombreRepuesto.getText().trim();
        String descripcion = jtxtareaDescripcion.getText().trim();
        String marca = cboMarcaRepuesto.getSelectedItem().toString().trim();
        String categoria = cboCategoria.getSelectedItem().toString().trim();
        String precio = txtPrecio.getText(); //Números
        int stock = (int) jspinnerStock.getValue();
        String estado;

        //Validación de si está nulo
        if (idRepuesto == null || nombre == null || descripcion == null || marca.equalsIgnoreCase("Sin seleccionar") || categoria.equalsIgnoreCase("Sin seleccionar") || precio == null || stock == 0) {
            JOptionPane.showMessageDialog(null, "Hay campos que están vacíos", "Verificación.", JOptionPane.WARNING_MESSAGE);
        } else {

            //Validación de que sean las variables de números tengan números.
            if (!soloNumeros(idRepuesto) || !soloNumeros(precio)) {
                JOptionPane.showMessageDialog(null, "Hay campos que solo deben tener números.", "Verificación.", JOptionPane.ERROR_MESSAGE);
            } else {

                //Validación de longitud
                if (idRepuesto.length() < 6 || nombre.length() < 4 || descripcion.length() < 10 || precio.length() <= 3 || idRepuesto.length() > 12) {
                    JOptionPane.showMessageDialog(null, "Hay campos con longitud inválida.", "Verificación.", JOptionPane.ERROR_MESSAGE);
                } else {

                    //Validación de que el stock no sea 0
                    if (stock == 0) {
                        JOptionPane.showMessageDialog(null, "El stock no puede ser igual 0.", "Verificación.", JOptionPane.ERROR_MESSAGE);

                    } else {

                        //Como el stock no es 0, ya sabemos que el estado automáticamente  es disponible.
                        estado = "Disponible";

                        //Validación que solo exista un repuesto registrado con dicho id
                        int idBuscar = Integer.parseInt(idRepuesto);

                        if (buscar_repuesto.existeRepuestoPorId(SistemaDatos.getInstancia().listaRepuestos, idBuscar)) {
                            JOptionPane.showMessageDialog(null, "Ya existe un registro del repuesto en el inventario con ese ID que digitó.", "Verificación.", JOptionPane.ERROR_MESSAGE);
                        } else {

                            //Casteo para agregar un repuesto!
                            int idRepuestoI = Integer.parseInt(idRepuesto);
                            double precioD = Double.parseDouble(precio);

                            //Agregar repuesto
                            Repuesto nuevo = new Repuesto(idRepuestoI, nombre, descripcion, marca, categoria, precioD, stock, estado, prov);
                            SistemaDatos.getInstancia().listaRepuestos.insertarFinal(nuevo);
                            JOptionPane.showMessageDialog(null, "Repuesto agregado correctamente.", "Completado.", JOptionPane.INFORMATION_MESSAGE);
                            limpiarCampos();
                            //Código para volver al menú principal
                            int resultado = JOptionPane.showConfirmDialog(null, "¿Desea ir al menú principal?", "Confirmación", JOptionPane.YES_NO_OPTION);

                            if (resultado == JOptionPane.YES_OPTION) {
                                jTabbedPane1.setSelectedIndex(0);

                            } else if (resultado == JOptionPane.NO_OPTION) {
                                JOptionPane.showMessageDialog(null, "Permanecerá en esta sección.");
                            } else if (resultado == JOptionPane.CLOSED_OPTION) {
                                JOptionPane.showMessageDialog(null, "Diálogo cerrado sin seleccionar una opción.");
                            }

                        
                    
                
            
        
    //////////////////////////////////////////////////////////////////////                 
                        }

                    }
                }
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cancelarProcesoAgregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEliminarRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRepuestoActionPerformed
        String idRepuesto = txtEliminarRepuestoId.getText().trim();
        btnLimpiarCampoDelete.setEnabled(true);

        if (idRepuesto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo está vacío.", "Verificación.", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!soloNumeros(idRepuesto)) {
            JOptionPane.showMessageDialog(null, "El ID solo debe contener números.", "Verificación.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idRepuesto.length() < 6) {
            JOptionPane.showMessageDialog(null, "La longitud es muy corta.", "Verificación.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Código para volver al menú principal
        int resultado = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar el repuesto del sistema?", "Alerta", JOptionPane.WARNING_MESSAGE);

        if (resultado == JOptionPane.YES_OPTION) {
            //Eliminación
            int id = Integer.parseInt(idRepuesto);
            EliminarRepuestoId eliminador = new EliminarRepuestoId();
            eliminador.eliminarPorId(SistemaDatos.getInstancia().listaRepuestos, id);

        } else if (resultado == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Proceso cancelado.");
        } else if (resultado == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Diálogo cerrado sin seleccionar una opción.");
        }


    }//GEN-LAST:event_btnEliminarRepuestoActionPerformed

    private void btnLimpiarCampoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCampoDeleteActionPerformed
        txtCargarNombreRepuesto3.setText("");
        txtIdRepuestoDelete1.setText("");
        txtEliminarRepuestoId.setText("");
        txtCargarMarcaRepuesto.setText("");
        txtCargarCategoriaRepuesto1.setText("");
        txtCargarPrecioRepuesto.setText("");
        btnLimpiarCampoDelete.setEnabled(false);
    }//GEN-LAST:event_btnLimpiarCampoDeleteActionPerformed

    private void btnBuscarParaEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarParaEliminar1ActionPerformed
        String idRepuesto = txtIdRepuestoDelete1.getText();
        btnLimpiarCampoDelete.setEnabled(true);

        if (idRepuesto.isEmpty() || idRepuesto == null) {
            JOptionPane.showMessageDialog(null, "El campo está vacío.", "Verificación.", JOptionPane.WARNING_MESSAGE);
        } else {

            if (!soloNumeros(idRepuesto)) {
                JOptionPane.showMessageDialog(null, "El ID solo debe contener números.", "Verificación.", JOptionPane.ERROR_MESSAGE);
            } else {

                if (idRepuesto.length() < 6) {
                    JOptionPane.showMessageDialog(null, "La longitud es muy corta.", "Verificación.", JOptionPane.ERROR_MESSAGE);
                } else {

                    int id = Integer.parseInt(idRepuesto);
                    Repuesto encontrado = buscador.buscarPorId(SistemaDatos.getInstancia().listaRepuestos, id);

                    if (encontrado != null) {
                        txtCargarNombreRepuesto3.setText(encontrado.getNombre());
                        txtCargarMarcaRepuesto.setText(encontrado.getMarca());
                        txtCargarCategoriaRepuesto1.setText(encontrado.getCategoria());
                        txtCargarPrecioRepuesto.setText(String.valueOf(encontrado.getPrecio()));
                        JOptionPane.showMessageDialog(null, "¡Repuesto encontrado existosamente!", "Correcto.", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "¡No se encontró el repuesto!", "Correcto.", JOptionPane.ERROR_MESSAGE);
                    }
                } //Else
            }
        }
    }//GEN-LAST:event_btnBuscarParaEliminar1ActionPerformed

    private void btnActualizarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarRActionPerformed
        int idRef = Integer.parseInt(txtActualizarRepuesto.getText());
        String campo = cboSolicitudActualizar.getSelectedItem().toString().trim();
        String nuevoValor = "";

        switch (campo) {

            case "Precio":
                nuevoValor = txtActualizarPrecioR.getText().toString().trim();
                break;

            case "Stock":
                nuevoValor = spiActualizarStock.getValue().toString().trim();
                break;

            case "Estado":
                nuevoValor = cboActualizarEstado.getSelectedItem().toString().trim();
                break;
        }

        boolean actualizado = actualizar.actualizarPorId(SistemaDatos.getInstancia().listaRepuestos, idRef, campo, nuevoValor);

        if (actualizado) {
            JOptionPane.showMessageDialog(null, "Repuesto actualizado correctamente.", "Actualización.", JOptionPane.INFORMATION_MESSAGE);

            ocultarDespuesQueActualice();
        } else {
            JOptionPane.showMessageDialog(null, "El repuesto no se actualizó correctamente.", "Actualización.", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnActualizarRActionPerformed

    private void btnReiniciarTablaRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarTablaRepuestosActionPerformed
        recargarTabla(tablaRepuestos, SistemaDatos.getInstancia().listaRepuestos);
        JOptionPane.showMessageDialog(null, "Tabla refrescada correctamente.", "Actualización.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnReiniciarTablaRepuestosActionPerformed

    private void btnBuscarParaActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarParaActActionPerformed
        //30 de noviembre
        String idRepuesto = txtActualizarRepuesto.getText();
        txtActualizarRepuesto.setEnabled(true);

        if (idRepuesto.isEmpty() || idRepuesto == null) {
            JOptionPane.showMessageDialog(null, "El campo está vacío.", "Verificación.", JOptionPane.WARNING_MESSAGE);
        } else {

            if (!soloNumeros(idRepuesto)) {
                JOptionPane.showMessageDialog(null, "El ID solo debe contener números.", "Verificación.", JOptionPane.ERROR_MESSAGE);
            } else {

                if (idRepuesto.length() < 6) {
                    JOptionPane.showMessageDialog(null, "La longitud es muy corta.", "Verificación.", JOptionPane.ERROR_MESSAGE);
                } else {

                    int id = Integer.parseInt(idRepuesto);
                    Repuesto encontrado = buscador.buscarPorId(SistemaDatos.getInstancia().listaRepuestos, id);

                    if (encontrado != null) {
                        JOptionPane.showMessageDialog(null, "¡Repuesto encontrado existosamente", "Correcto.", JOptionPane.INFORMATION_MESSAGE);
                        mostrarZonaDeActualizar();
                        ocultarDespuesDeTodoExitoso();
                    } else {
                        JOptionPane.showMessageDialog(null, "¡No se encontró el repuesto!", "Correcto.", JOptionPane.ERROR_MESSAGE);
                    }
                } //Else
            }
        }
    }//GEN-LAST:event_btnBuscarParaActActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtActualizarRepuesto.setText("");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnCancelarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRActionPerformed
        ocultarZonaDeActualizar();
        JOptionPane.showMessageDialog(null, "Proceso de actualizar cancelado", "Cancelación.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnCancelarRActionPerformed

    private void cboSolicitudActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSolicitudActualizarActionPerformed

        String solicitud = cboSolicitudActualizar.getSelectedItem().toString().trim();

        if (solicitud.equalsIgnoreCase("--------------------------")) {

            jPanel6.setVisible(false);
            txtActualizarPrecioR.setVisible(false);
            lblActualizarPrecio.setVisible(false);
            lblDigitaStock.setVisible(false);
            spiActualizarStock.setVisible(false);
            lblSelecEstadoN.setVisible(false);
            cboActualizarEstado.setVisible(false);

        } else if (solicitud.equalsIgnoreCase("Precio")) {

            //Cuando la condición es verdadera
            jPanel6.setVisible(true);
            txtActualizarPrecioR.setVisible(true);
            lblActualizarPrecio.setVisible(true);

            //Para evitar inconsistencias 
            lblDigitaStock.setVisible(false);
            spiActualizarStock.setVisible(false);
            lblSelecEstadoN.setVisible(false);
            cboActualizarEstado.setVisible(false);

        } else if (solicitud.equalsIgnoreCase("Stock")) {

            //Cuando la condición es verdadera
            jPanel6.setVisible(true);
            lblDigitaStock.setVisible(true);
            spiActualizarStock.setVisible(true);

            //Para evitar inconsistencias
            txtActualizarPrecioR.setVisible(false);
            lblActualizarPrecio.setVisible(false);
            lblSelecEstadoN.setVisible(false);
            cboActualizarEstado.setVisible(false);

        } else if (solicitud.equalsIgnoreCase("Estado")) {

            //Cuando la condición es verdadera
            jPanel6.setVisible(true);
            lblSelecEstadoN.setVisible(true);
            cboActualizarEstado.setVisible(true);

            //Para evitar inconsistencias
            txtActualizarPrecioR.setVisible(false);
            lblActualizarPrecio.setVisible(false);
            lblDigitaStock.setVisible(false);
            spiActualizarStock.setVisible(false);
        }

    }//GEN-LAST:event_cboSolicitudActualizarActionPerformed

    //Proveedor p = Sesion.proveedorActual;
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelActualizar;
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelNegro2;
    private javax.swing.JButton btnActualizarR;
    private javax.swing.JButton btnBuscarParaAct;
    private javax.swing.JButton btnBuscarParaEliminar1;
    private javax.swing.JButton btnCancelarR;
    private javax.swing.JButton btnEliminarRepuesto;
    private javax.swing.JButton btnLimpiarCampoDelete;
    private javax.swing.JButton btnReiniciarTablaRepuestos;
    private javax.swing.JComboBox<String> cboActualizarEstado;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboMarcaRepuesto;
    private javax.swing.JComboBox<String> cboSolicitudActualizar;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JLabel iconRepuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner jspinnerStock;
    private javax.swing.JTextArea jtxtareaDescripcion;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelRecepcionisa1;
    private javax.swing.JLabel lblActualizarPrecio;
    private javax.swing.JLabel lblCOP;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcionRepuesto;
    private javax.swing.JLabel lblDigitaStock;
    private javax.swing.JLabel lblMarcaRepuesto;
    private javax.swing.JLabel lblNombreProv;
    private javax.swing.JLabel lblNombreRepuesto;
    private javax.swing.JLabel lblPrecioRepuesto;
    private javax.swing.JLabel lblQuéQuieresActualizar1;
    private javax.swing.JLabel lblRepuesto;
    private javax.swing.JLabel lblRepuesto2;
    private javax.swing.JLabel lblRepuesto3;
    private javax.swing.JLabel lblRepuesto4;
    private javax.swing.JLabel lblRepuesto5;
    private javax.swing.JLabel lblRepuesto6;
    private javax.swing.JLabel lblRepuesto7;
    private javax.swing.JLabel lblRepuesto8;
    private javax.swing.JLabel lblRepuesto9;
    private javax.swing.JLabel lblSelecEstadoN;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel panelBtnActualizar;
    private javax.swing.JPanel panelBtnAgregar;
    private javax.swing.JPanel panelBtnCerrarSesion;
    private javax.swing.JPanel panelBtnEliminar;
    private javax.swing.JPanel panelBtnMenuP;
    private javax.swing.JPanel panelDecoración;
    private javax.swing.JPanel panelDecoración1;
    private javax.swing.JPanel panelDecoración10;
    private javax.swing.JPanel panelDecoración11;
    private javax.swing.JPanel panelDecoración12;
    private javax.swing.JPanel panelDecoración13;
    private javax.swing.JPanel panelDecoración14;
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
    private javax.swing.JSpinner spiActualizarStock;
    private javax.swing.JTable tablaRepuestos;
    private javax.swing.JTextField txtActualizarPrecioR;
    private javax.swing.JTextField txtActualizarRepuesto;
    private javax.swing.JTextField txtCargarCategoriaRepuesto1;
    private javax.swing.JTextField txtCargarMarcaRepuesto;
    private javax.swing.JTextField txtCargarNombreRepuesto3;
    private javax.swing.JTextField txtCargarPrecioRepuesto;
    private javax.swing.JTextField txtEliminarRepuestoId;
    private javax.swing.JTextField txtIdRepuesto;
    private javax.swing.JTextField txtIdRepuestoDelete1;
    private javax.swing.JTextField txtNombreRepuesto;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

    //Verificar que un String solo tenga números.
    public boolean soloNumeros(String texto) {
        return texto.matches("\\d+");
    }

    //Método de cancelar proceso de agregar repuesto
    public void cancelarProcesoAgregar() {

        int resultado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar el proceso?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (resultado == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Ha seleccionado SÍ.");
            txtIdRepuesto.setText("");
            txtNombreRepuesto.setText("");
            cboMarcaRepuesto.setSelectedIndex(0);
            jtxtareaDescripcion.setText("");
            txtPrecio.setText("");
            jspinnerStock.setValue(1);
            cboCategoria.setSelectedIndex(0);

            int resultado2 = JOptionPane.showConfirmDialog(null, "¿Desea regresar al menú principal?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (resultado2 == JOptionPane.YES_OPTION) {
                jTabbedPane1.setSelectedIndex(0);
            } else if (resultado2 == JOptionPane.NO_OPTION) {
                System.out.println("");
            } else if (resultado == JOptionPane.CLOSED_OPTION) {
                System.out.println("");
            }
        } else if (resultado == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Ha seleccionado NO.");
        } else if (resultado == JOptionPane.CLOSED_OPTION) {
            JOptionPane.showMessageDialog(null, "Diálogo cerrado sin seleccionar una opción.");
        }
    }

    public void limpiarCampos() {
        txtIdRepuesto.setText("");
        txtNombreRepuesto.setText("");
        cboMarcaRepuesto.setSelectedIndex(0);
        jtxtareaDescripcion.setText("");
        txtPrecio.setText("");
        jspinnerStock.setValue(1);
        cboCategoria.setSelectedIndex(0);
    }

  

    public void cargarTabla(JTable tablaRepuestos, Lista lista) {
        DefaultTableModel model = (DefaultTableModel) tablaRepuestos.getModel();
        model.setRowCount(0);

        Nodo actual = lista.getPrimero();

        while (actual != null) {
            Repuesto repuesto = (Repuesto) actual.getDato();
            Model.Proveedor proveedor = (Model.Proveedor) repuesto.getProveedor();

            Object[] rowData = new Object[]{
                repuesto.getCategoria(),
                repuesto.getNombre(),
                repuesto.getPrecio(),
                repuesto.getStock(),
                proveedor != null ? proveedor.getPrimerNombre() + " " + proveedor.getPrimerApellido() : "Sin proveedor",
                repuesto.getEstado()
            };

            model.addRow(rowData);
            actual = actual.getSiguiente();
        }
    }

    public void recargarTabla(JTable tablaRepuestos, Lista lista) {
        DefaultTableModel model = (DefaultTableModel) tablaRepuestos.getModel();
        model.setRowCount(0);

        Nodo actual = lista.getPrimero();

        while (actual != null) {
            Repuesto repuesto = (Repuesto) actual.getDato(); //Saca el nodo y lo convierte a repuesto
            Model.Proveedor proveedor = (Model.Proveedor) repuesto.getProveedor(); //Saca el proveedor del repuesto y lo castea a proveedor

            Object[] rowData = new Object[]{
                repuesto.getCategoria(),
                repuesto.getNombre(),
                repuesto.getPrecio(),
                repuesto.getStock(),
                proveedor != null ? proveedor.getPrimerNombre() + " " + proveedor.getPrimerApellido() : "Sin proveedor",
                repuesto.getEstado()
            };

            model.addRow(rowData);
            actual = actual.getSiguiente();
        }
    }

    private void ocultarZonaDeActualizar() {
        lblQuéQuieresActualizar1.setVisible(false);
        lblActualizarPrecio.setVisible(false);
        cboSolicitudActualizar.setVisible(false);
        btnActualizarR.setVisible(false);
        btnCancelarR.setVisible(false);
        jPanel6.setVisible(false);
        txtActualizarPrecioR.setVisible(false);
        lblActualizarPrecio.setVisible(false);
        lblDigitaStock.setVisible(false);
        spiActualizarStock.setVisible(false);
        lblSelecEstadoN.setVisible(false);
        cboActualizarEstado.setVisible(false);

    }

    private void mostrarZonaDeActualizar() {
        lblQuéQuieresActualizar1.setVisible(true);
        lblActualizarPrecio.setVisible(true);
        cboSolicitudActualizar.setVisible(true);
        btnActualizarR.setVisible(true);
        btnCancelarR.setVisible(true);
    }

    private void ocultarDespuesDeTodoExitoso() {
        lblActualizarPrecio.setVisible(false);
        txtActualizarPrecioR.setVisible(false);
    }

    private void ocultarDespuesQueActualice() {
        cboSolicitudActualizar.setSelectedIndex(0);
        jPanel6.setVisible(false);
        btnActualizarR.setVisible(false);
        btnCancelarR.setVisible(false);
        lblQuéQuieresActualizar1.setVisible(false);
        lblActualizarPrecio.setVisible(false);
        cboSolicitudActualizar.setVisible(false);
    }

}
