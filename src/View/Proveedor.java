package View;

import Model.Sesion;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Adrian
 */
public class Proveedor extends javax.swing.JFrame {

    public Proveedor() {
        initComponents();
        setLocationRelativeTo(null);
        jspinnerStock.setModel(modelo);
    }

    SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, 100, 1);

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
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        txtIdRepuesto1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
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
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtIdRepuesto2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
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
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(PanelNegro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 690, 100));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ESTE ES EL MENÚ PRINCIPAL DE PROVEEDORES DE J-WORKSHOP");
        panelMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));

        jTable1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Categoria", "Nombre", "Precio", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        panelMenu.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 670, 280));

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

        lblRepuesto1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        lblRepuesto1.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto1.setText("1. INGRESE EL ID DEL REPUESTO PARA HALLARLO EN EL SISTEMA:");
        PanelActualizar.add(lblRepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txtIdRepuesto1.setBackground(new java.awt.Color(204, 204, 204));
        txtIdRepuesto1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdRepuesto1.setForeground(new java.awt.Color(0, 0, 0));
        PanelActualizar.add(txtIdRepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

        jButton3.setBackground(new java.awt.Color(0, 153, 0));
        jButton3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("BUSCAR");
        PanelActualizar.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, 30));

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
        lblRepuesto2.setText("1. INGRESE EL ID DEL REPUESTO PARA HALLARLO EN EL SISTEMA:");
        panelEliminar.add(lblRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jButton5.setBackground(new java.awt.Color(0, 153, 0));
        jButton5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("BUSCAR");
        panelEliminar.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 80, 30));

        jButton6.setBackground(new java.awt.Color(153, 0, 0));
        jButton6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("X");
        panelEliminar.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 80, 30));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/buscar.png"))); // NOI18N
        panelEliminar.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        txtIdRepuesto2.setBackground(new java.awt.Color(204, 204, 204));
        txtIdRepuesto2.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdRepuesto2.setForeground(new java.awt.Color(0, 0, 0));
        panelEliminar.add(txtIdRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 240, 30));

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

        //Validación de si está nulo
        if (idRepuesto == null || nombre == null || descripcion == null || marca.equalsIgnoreCase("Sin seleccionar") || categoria.equalsIgnoreCase("Sin seleccionar") || precio == null || stock == 0) {
            JOptionPane.showMessageDialog(null, "Hay campos que están vacíos", "Verificación.", JOptionPane.WARNING_MESSAGE);
        } else{
            
        //Validación de que sean las variables de números tengan números.
        if(!soloNumeros(idRepuesto) || !soloNumeros(precio) ){
             JOptionPane.showMessageDialog(null, "Hay campos que solo deben tener números.", "Verificación.", JOptionPane.ERROR_MESSAGE);
        }
            
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cancelarProcesoAgregar();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JComboBox<String> cboMarcaRepuesto;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JLabel iconRepuesto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner jspinnerStock;
    private javax.swing.JTextArea jtxtareaDescripcion;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelRecepcionisa1;
    private javax.swing.JLabel lblCOP;
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
    private javax.swing.JTextField txtIdRepuesto;
    private javax.swing.JTextField txtIdRepuesto1;
    private javax.swing.JTextField txtIdRepuesto2;
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

}
