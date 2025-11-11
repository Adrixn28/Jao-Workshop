package View;

import Model.Cliente;
import Model.Sesion;
import javax.swing.JOptionPane;
import listaDoble.Lista;

/**
 *
 * @author Adrian
 */
public class Recepcionista extends javax.swing.JFrame {

    //Creacion de la lista de Clientes
    Lista listaClientes;

    public Recepcionista() {
        initComponents();
        setLocationRelativeTo(null);

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
        labelMenúOpciones = new javax.swing.JLabel();
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
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        panelBtnCerrarSesion1 = new javax.swing.JPanel();
        iconCerrarSesion1 = new javax.swing.JLabel();
        labelCerrarSesion1 = new javax.swing.JLabel();
        panelGris = new javax.swing.JPanel();
        labelRecepcionisa1 = new javax.swing.JLabel();
        PanelNegro2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMenu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelDecoración12 = new javax.swing.JPanel();
        panelDecoración13 = new javax.swing.JPanel();
        panelDecoración14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        PanelActualizar = new javax.swing.JPanel();
        panelDecoración6 = new javax.swing.JPanel();
        panelDecoración7 = new javax.swing.JPanel();
        panelDecoración8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblRepuesto1 = new javax.swing.JLabel();
        txtIdClienteActualizar = new javax.swing.JTextField();
        btnBuscarClienteActualizar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panelEliminar = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelDecoración9 = new javax.swing.JPanel();
        panelDecoración10 = new javax.swing.JPanel();
        panelDecoración11 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblRepuesto2 = new javax.swing.JLabel();
        btnBuscarClienteEliminar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtIdClienteEliminar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
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

        javax.swing.GroupLayout panelBtnMenuPLayout = new javax.swing.GroupLayout(panelBtnMenuP);
        panelBtnMenuP.setLayout(panelBtnMenuPLayout);
        panelBtnMenuPLayout.setHorizontalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconInicio)
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnMenuPLayout.setVerticalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuPLayout.createSequentialGroup()
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
        labelAgregarPaciente.setText("AGREGAR CLIENTE");

        javax.swing.GroupLayout panelBtnAgregarLayout = new javax.swing.GroupLayout(panelBtnAgregar);
        panelBtnAgregar.setLayout(panelBtnAgregarLayout);
        panelBtnAgregarLayout.setHorizontalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgregarPaciente)
                .addGap(44, 44, 44)
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
        labelEditarPaciente.setText("ACTUALIZAR CLIENTE");

        javax.swing.GroupLayout panelBtnActualizarLayout = new javax.swing.GroupLayout(panelBtnActualizar);
        panelBtnActualizar.setLayout(panelBtnActualizarLayout);
        panelBtnActualizarLayout.setHorizontalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarPaciente)
                .addGap(18, 18, 18)
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
                .addGap(33, 33, 33)
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

        panelBtnCerrarSesion1.setBackground(new java.awt.Color(0, 153, 0));
        panelBtnCerrarSesion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCerrarSesion1MouseClicked(evt);
            }
        });

        labelCerrarSesion1.setBackground(new java.awt.Color(204, 0, 0));
        labelCerrarSesion1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelCerrarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        labelCerrarSesion1.setText("PROVEEDORES");
        labelCerrarSesion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelCerrarSesion1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBtnCerrarSesion1Layout = new javax.swing.GroupLayout(panelBtnCerrarSesion1);
        panelBtnCerrarSesion1.setLayout(panelBtnCerrarSesion1Layout);
        panelBtnCerrarSesion1Layout.setHorizontalGroup(
            panelBtnCerrarSesion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesion1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconCerrarSesion1)
                .addGap(60, 60, 60)
                .addComponent(labelCerrarSesion1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnCerrarSesion1Layout.setVerticalGroup(
            panelBtnCerrarSesion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconCerrarSesion1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addGroup(panelBtnCerrarSesion1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCerrarSesion1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addComponent(panelBtnCerrarSesion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelNegroLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel14)
                    .addContainerGap(640, Short.MAX_VALUE)))
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

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ESTE ES EL MENÚ PRINCIPAL DE RECEPCIONISTAS ");
        panelMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        panelDecoración12.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración12Layout = new javax.swing.GroupLayout(panelDecoración12);
        panelDecoración12.setLayout(panelDecoración12Layout);
        panelDecoración12Layout.setHorizontalGroup(
            panelDecoración12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("Menú", panelMenu);

        PanelActualizar.setBackground(new java.awt.Color(255, 255, 255));
        PanelActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDecoración6.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración6Layout = new javax.swing.GroupLayout(panelDecoración6);
        panelDecoración6.setLayout(panelDecoración6Layout);
        panelDecoración6Layout.setHorizontalGroup(
            panelDecoración6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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

        lblRepuesto1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto1.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto1.setText("1. INGRESE EL ID DEL REPUESTO PARA HALLARLO EN EL SISTEMA:");
        PanelActualizar.add(lblRepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtIdClienteActualizar.setBackground(new java.awt.Color(204, 204, 204));
        txtIdClienteActualizar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdClienteActualizar.setForeground(new java.awt.Color(0, 0, 0));
        txtIdClienteActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActualizarActionPerformed(evt);
            }
        });
        PanelActualizar.add(txtIdClienteActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        btnBuscarClienteActualizar.setBackground(new java.awt.Color(0, 153, 0));
        btnBuscarClienteActualizar.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnBuscarClienteActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarClienteActualizar.setText("BUSCAR");
        btnBuscarClienteActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActualizarActionPerformed(evt);
            }
        });
        PanelActualizar.add(btnBuscarClienteActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, 30));

        jButton4.setBackground(new java.awt.Color(153, 0, 0));
        jButton4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("X");
        PanelActualizar.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 80, 30));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        PanelActualizar.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

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

        jTabbedPane1.addTab("Actualizar", PanelActualizar);

        panelEliminar.setBackground(new java.awt.Color(255, 255, 255));
        panelEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("¡REMUEVE ALGÚN REPUESTO DEL SISTEMA!");
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
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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
            .addGap(0, 690, Short.MAX_VALUE)
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
        lblRepuesto2.setText("1. INGRESE EL ID DEL REPUESTO PARA HALLARLO EN EL SISTEMA:");
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
        panelEliminar.add(btnBuscarClienteEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, 30));

        jButton6.setBackground(new java.awt.Color(153, 0, 0));
        jButton6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("X");
        panelEliminar.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 80, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        panelEliminar.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        txtIdClienteEliminar.setBackground(new java.awt.Color(204, 204, 204));
        txtIdClienteEliminar.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdClienteEliminar.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtIdClienteEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

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

        panelEliminar.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 690, 10));

        jTabbedPane1.addTab("Eliminar", panelEliminar);

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
        PanelAgregar.add(btnCancelarProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 170, -1));

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

        jTabbedPane1.addTab("Agregar", PanelAgregar);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 690, 480));

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

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        if (txtPrimerNombreCliente.getText().trim().isEmpty()
                || txtPrimerApellidoCliente.getText().trim().isEmpty()
                || txtSegundoApellidoCliente.getText().trim().isEmpty()
                || txtCedulaCliente.getText().trim().isEmpty()
                || cboGeneroCliente.getSelectedItem().toString().equals("Seleccionar")
                || txtTelefonoCliente.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Hay campos que estan vacios");
        } else {

            //Usar el constructor por defecto (sin argumentos)
            Cliente nuevoCliente = new Cliente();

            // ASIGNAR VALORES UNO POR UNO USANDO SETTERS
            nuevoCliente.setPrimerNombre(txtPrimerNombreCliente.getText().trim());
            nuevoCliente.setSegundoNombre(txtSegundoNombreCliente.getText().trim());
            nuevoCliente.setPrimerApellido(txtPrimerApellidoCliente.getText().trim());
            nuevoCliente.setSegundoApellido(txtSegundoApellidoCliente.getText().trim());

            nuevoCliente.setGenero(cboGeneroCliente.getSelectedItem().toString());

            nuevoCliente.setCedula(txtCedulaCliente.getText().trim());
            nuevoCliente.setTelefono(txtTelefonoCliente.getText().trim());
            nuevoCliente.setCorreo(txtCorreoCliente.getText().trim());
            nuevoCliente.setDireccion(txtDireccionCliente.getText().trim());

            // Asignación de usuario y contraseña 
            nuevoCliente.setUsuario(txtUsuarioCliente.getText().trim());
            nuevoCliente.setContraseña(txtContraseñaCliente.getText().trim());

            // Insertar el cliente al final de la lista
            listaClientes.insertarFinal(nuevoCliente);

            JOptionPane.showMessageDialog(null, "Cliente Agregado Correctamente");

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

    private void btnBuscarClienteActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActualizarActionPerformed
        // TODO add your handling code here:
        if (txtIdClienteActualizar.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La ID es incorrecta o esta vacia");
        }

    }//GEN-LAST:event_btnBuscarClienteActualizarActionPerformed

    private void txtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteActionPerformed

    private void panelBtnCerrarSesion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnCerrarSesion1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBtnCerrarSesion1MouseClicked

    private void labelCerrarSesion1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelCerrarSesion1MouseClicked
        // TODO add your handling code here:
        new Recepcionista2().setVisible(true);
        dispose();
    }//GEN-LAST:event_labelCerrarSesion1MouseClicked

    private void txtIdClienteActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActualizarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtIdClienteActualizarActionPerformed

    private void btnBuscarClienteEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteEliminarActionPerformed
        // TODO add your handling code here:
        if (txtIdClienteActualizar.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La ID es incorrecta o esta vacia");
        }
    }//GEN-LAST:event_btnBuscarClienteEliminarActionPerformed

    //Proveedor p = Sesion.proveedorActual;
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recepcionista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelActualizar;
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelNegro2;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnBuscarClienteActualizar;
    private javax.swing.JButton btnBuscarClienteEliminar;
    private javax.swing.JButton btnCancelarProcess;
    private javax.swing.JComboBox<String> cboGeneroCliente;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconCerrarSesion1;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelCerrarSesion1;
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
    private javax.swing.JPanel panelBtnCerrarSesion1;
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
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtContraseñaCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtIdClienteActualizar;
    private javax.swing.JTextField txtIdClienteEliminar;
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
