/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Service.AdministradorService;
import Service.LoginService;
import Service.*; // Add this import
import Model.*;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Vista del Administrador con funcionalidades de gestión de usuarios
 * @author Osvaldo
 */
public class Administrador extends javax.swing.JFrame {
    
    private AdministradorService administradorService;
    private LoginService loginService;
    private String idAdministradorActual; // ID del administrador logueado
    private boolean editandoDatosProveedor = false; // Control de estado de edición
    // Estados de edición: 0 = Edición Vigente, 1 = Edición Aprobada, 2 = Edición Cancelada
    private int estadoEdicionRecepcionista = EDICION_CANCELADA; // Inicialmente cancelado (no hay edición activa)
    private int estadoEdicionCliente = EDICION_CANCELADA; // Estado de edición para clientes
    
    // Constantes para los estados de edición
    private static final int EDICION_VIGENTE = 0;
    private static final int EDICION_APROBADA = 1;
    private static final int EDICION_CANCELADA = 2;

    /**
     * Creates new form Administrador
     */
    public Administrador() {
        initComponents();
        inicializarServicios();
        // No se precargan datos sin ID específico - usar constructor con parámetro
        // precargarDatosAdministrador();
        configurarEventos();
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor que recibe el ID del administrador para precargar datos
     * @param idAdministrador ID del administrador logueado
     */
    public Administrador(String idAdministrador) {
        initComponents();
        inicializarServicios();
        System.out.println("🏁 CONSTRUCTOR - Estado inicial Recepcionista: " + estadoEdicionRecepcionista);
        System.out.println("🏁 CONSTRUCTOR - Estado inicial Cliente: " + estadoEdicionCliente);
        precargarDatosAdministradorPorId(idAdministrador);
        configurarEventos();
        System.out.println("🏁 CONSTRUCTOR - Estado final Recepcionista: " + estadoEdicionRecepcionista);
        System.out.println("🏁 CONSTRUCTOR - Estado final Cliente: " + estadoEdicionCliente);
        setLocationRelativeTo(null);
    }
    
    private void inicializarServicios() {
        loginService = new LoginService();
        administradorService = new AdministradorService(loginService);
        System.out.println("Servicios inicializados para Administrador");
    }
    
    private void precargarDatosAdministrador() {
        // Este método ya no se usa, mantenido por compatibilidad
        System.out.println("Método precargarDatosAdministrador() llamado sin ID");
    }
    
    private void precargarDatosAdministradorPorId(String idAdministrador) {
        if (idAdministrador == null || idAdministrador.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Error: ID de administrador no válido.", 
                "Error de Datos", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        
        // Buscar administrador por ID usando el servicio
        Model.Administrador adminModel = administradorService.buscarAdministradorPorIdEnLista(idAdministrador);
        
        if (adminModel != null) {
            // Guardar el ID para uso posterior
            this.idAdministradorActual = idAdministrador;
            
            // Precargar datos en los campos del panel de inicio
            txtIdNombresDelAdministrador.setText(adminModel.getPrimerNombre() + " " + 
                (adminModel.getSegundoNombre() != null && !adminModel.getSegundoNombre().isEmpty() 
                 ? adminModel.getSegundoNombre() : ""));
            
            txtApellidosAdministrador1.setText(adminModel.getPrimerApellido() + " " + 
                (adminModel.getSegundoApellido() != null && !adminModel.getSegundoApellido().isEmpty() 
                 ? adminModel.getSegundoApellido() : ""));
            
            txtCedulaAdministrador.setText(adminModel.getCedula());
            txtIdTelefonoAdministrador.setText(adminModel.getTelefono());
            txtCorreoAdministrador.setText(adminModel.getCorreo());
            txtGenero.setText(adminModel.getGenero());
            
            System.out.println("Datos precargados para administrador: " + adminModel.getPrimerNombre() + 
                             " (ID: " + idAdministrador + ")");
        } else {
            System.out.println("No se encontró administrador con ID: " + idAdministrador);
            JOptionPane.showMessageDialog(this, 
                "Error: No se pudo encontrar la información del administrador.\n" +
                "ID: " + idAdministrador, 
                "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }
    
    private void configurarEventos() {
        // Configurar eventos para los botones de edición
        BtnEditarDatos.addActionListener(evt -> BtnEditarDatosActionPerformed(evt));
        GuardarDatosEditados.addActionListener(evt -> GuardarDatosEditadosActionPerformed(evt));
        BtnCancelarEdicion.addActionListener(evt -> BtnCancelarEdicionActionPerformed(evt));
        
        // Configurar eventos para proveedores
        configurarEventosProveedores();
        
        // Configurar eventos para recepcionistas
        configurarEventosRecepcionistas();
        
        // Configurar eventos para clientes
        configurarEventosClientes();
        
        // Inicialmente ocultar botones de guardado y cancelación
        GuardarDatosEditados.setVisible(false);
        BtnCancelarEdicion.setVisible(false);
        
        // Inicialmente ocultar botón de cancelar edición recepcionista
        if (BtnCancelarEdicionRecepcionistas != null) {
            BtnCancelarEdicionRecepcionistas.setVisible(false);
        }
        
        System.out.println("Eventos configurados para Administrador");
    }
    
    // ========================================
    // MÉTODOS PARA GESTIÓN DE PROVEEDORES
    // ========================================
    
    private void configurarEventosProveedores() {
        // Configurar selección de tabla de proveedores
        jTableProveedores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = jTableProveedores.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    cargarDatosProveedorSeleccionado();
                    // Cuando hay selección, campos no editables
                    establecerCamposProveedorNoEditables();
                }
            }
        });
        
        // Agregar listener para doble clic en área vacía (limpiar campos)
        jTableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int fila = jTableProveedores.rowAtPoint(evt.getPoint());
                    if (fila == -1) {
                        // Doble clic en área vacía
                        limpiarCamposProveedor();
                    }
                }
            }
        });
        
        // Configurar botones de proveedores
        btnAgregarProveedor.addActionListener(evt -> agregarProveedor());
        BtnEditarDatosProveedor.addActionListener(evt -> habilitarEdicionProveedor());
        btnEliminarProveedor.addActionListener(evt -> eliminarProveedor());
        btnLimpiarDatos.addActionListener(evt -> limpiarDatosProveedor());
        
        // Cargar datos iniciales
        cargarProveedoresEnTabla();
        limpiarCamposProveedor();
        
        // Configurar estado inicial de campos (editables por defecto)
        establecerCamposProveedorEditables();
        
        System.out.println("Eventos de proveedores configurados");
    }
    
    private void cargarProveedoresEnTabla() {
        try {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableProveedores.getModel();
            model.setRowCount(0); // Limpiar tabla
            
            listaDoble.Lista listaProveedores = administradorService.obtenerTodosLosProveedores();
            
            if (listaProveedores != null && listaProveedores.getPrimero() != null) {
                listaDoble.Nodo actual = listaProveedores.getPrimero();
                
                while (actual != null) {
                    Model.Proveedor proveedor = (Model.Proveedor) actual.getDato();
                    
                    // Combinar nombres
                    String nombres = proveedor.getPrimerNombre();
                    if (proveedor.getSegundoNombre() != null && !proveedor.getSegundoNombre().trim().isEmpty()) {
                        nombres += " " + proveedor.getSegundoNombre();
                    }
                    
                    // Combinar apellidos
                    String apellidos = proveedor.getPrimerApellido();
                    if (proveedor.getSegundoApellido() != null && !proveedor.getSegundoApellido().trim().isEmpty()) {
                        apellidos += " " + proveedor.getSegundoApellido();
                    }
                    
                    model.addRow(new Object[]{
                        proveedor.getIdProveedor(),
                        nombres,
                        apellidos,
                        proveedor.getCedula(),
                        proveedor.getTelefono(),
                        proveedor.getCorreo()
                    });
                    
                    actual = actual.getSiguiente();
                }
                
                System.out.println("Tabla de proveedores cargada con datos");
            } else {
                System.out.println("No hay proveedores para mostrar");
            }
            
        } catch (Exception e) {
            System.out.println("Error al cargar proveedores en tabla: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al cargar la lista de proveedores: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosProveedorSeleccionado() {
        int filaSeleccionada = jTableProveedores.getSelectedRow();
        
        if (filaSeleccionada >= 0) {
            String idProveedor = jTableProveedores.getValueAt(filaSeleccionada, 0).toString();
            
            Model.Proveedor proveedor = administradorService.buscarProveedorPorId(idProveedor);
            
            if (proveedor != null) {
                // Cargar datos en los campos
                txtNombreProveedor.setText(
                    proveedor.getPrimerNombre() + 
                    (proveedor.getSegundoNombre() != null && !proveedor.getSegundoNombre().trim().isEmpty() 
                     ? " " + proveedor.getSegundoNombre() : "")
                );
                
                txtApellidosProveedor.setText(
                    proveedor.getPrimerApellido() + 
                    (proveedor.getSegundoApellido() != null && !proveedor.getSegundoApellido().trim().isEmpty() 
                     ? " " + proveedor.getSegundoApellido() : "")
                );
                
                txtUsuarioProveedor.setText(proveedor.getUsuario());
                txtContraseñaProveedor.setText(proveedor.getContraseña());
                txtCorreoProveedor.setText(proveedor.getCorreo());
                txtTelefonoProveedor.setText(proveedor.getTelefono());
                txtCedulaProveedor.setText(proveedor.getCedula());
                txtAniosExperienciaProveedor.setText(String.valueOf(proveedor.getAniosExperiencia()));
                
                // Seleccionar género en el combo
                String genero = proveedor.getGenero();
                if (genero != null) {
                    cboGeneroProveedor.setSelectedItem(genero);
                }
                
                System.out.println("Datos del proveedor cargados: " + proveedor.getPrimerNombre());
            }
        }
    }
    
    private void limpiarCamposProveedor() {
        txtNombreProveedor.setText("");
        txtApellidosProveedor.setText("");
        txtUsuarioProveedor.setText("");
        txtContraseñaProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtCedulaProveedor.setText("");
        txtAniosExperienciaProveedor.setText("");
        cboGeneroProveedor.setSelectedIndex(0); // "Sin seleccionar"
        
        // Limpiar selección de tabla
        jTableProveedores.clearSelection();
        
        // Habilitar campos para nuevo proveedor
        establecerCamposProveedorEditables();
    }
    
    /**
     * Método para el botón "Limpiar Datos" - limpia todos los campos y resetea la tabla
     */
    private void limpiarDatosProveedor() {
        // Si se está editando, mostrar mensaje de confirmación
        if (editandoDatosProveedor) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "Actualmente está editando un proveedor.\n" +
                "¿Está seguro que desea cancelar la edición y limpiar todos los datos?",
                "Cancelar Edición",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        // Limpiar todos los campos de texto
        txtNombreProveedor.setText("");
        txtApellidosProveedor.setText("");
        txtUsuarioProveedor.setText("");
        txtContraseñaProveedor.setText("");
        txtCorreoProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtCedulaProveedor.setText("");
        txtAniosExperienciaProveedor.setText("");
        
        // Resetear combo box a "Sin seleccionar"
        cboGeneroProveedor.setSelectedIndex(0);
        
        // Limpiar selección de la tabla
        jTableProveedores.clearSelection();
        
        // Resetear estado de edición
        editandoDatosProveedor = false;
        
        // Habilitar campos para nueva entrada
        establecerCamposProveedorEditables();
        
        String mensaje = editandoDatosProveedor ? 
            "Edición cancelada y datos limpiados completamente" : 
            "Datos de proveedor limpiados completamente";
        System.out.println(mensaje);
    }
    
    private void agregarProveedor() {
        try {
            // Validar campos obligatorios
            if (txtNombreProveedor.getText().trim().isEmpty() ||
                txtApellidosProveedor.getText().trim().isEmpty() ||
                txtUsuarioProveedor.getText().trim().isEmpty() ||
                txtContraseñaProveedor.getText().trim().isEmpty() ||
                txtCorreoProveedor.getText().trim().isEmpty() ||
                txtTelefonoProveedor.getText().trim().isEmpty() ||
                txtCedulaProveedor.getText().trim().isEmpty() ||
                txtAniosExperienciaProveedor.getText().trim().isEmpty() ||
                cboGeneroProveedor.getSelectedIndex() == 0) {
                
                JOptionPane.showMessageDialog(this, 
                    "Por favor, complete todos los campos obligatorios.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Verificar si hay un proveedor seleccionado en la tabla
            int filaSeleccionada = jTableProveedores.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String nombreSeleccionado = jTableProveedores.getValueAt(filaSeleccionada, 1).toString();
                String apellidoSeleccionado = jTableProveedores.getValueAt(filaSeleccionada, 2).toString();
                
                JOptionPane.showMessageDialog(this, 
                    "No se puede agregar un proveedor mientras hay uno seleccionado.\n" +
                    "Actualmente tiene seleccionado: " + nombreSeleccionado + " " + apellidoSeleccionado + "\n\n" +
                    "Use 'Limpiar Datos' para deseleccionar y agregar un nuevo proveedor.", 
                    "Proveedor Seleccionado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar duplicados
            if (!validarProveedor()) {
                return;
            }
            
            // Separar nombres correctamente
            String[] nombres = txtNombreProveedor.getText().trim().split("\\s+", 2);
            String primerNombre = nombres[0];
            String segundoNombre = nombres.length > 1 ? nombres[1].trim() : "";
            
            // Separar apellidos correctamente
            String[] apellidos = txtApellidosProveedor.getText().trim().split("\\s+", 2);
            String primerApellido = apellidos[0];
            String segundoApellido = apellidos.length > 1 ? apellidos[1].trim() : "";
            
            // Validar años de experiencia
            int aniosExperiencia;
            try {
                aniosExperiencia = Integer.parseInt(txtAniosExperienciaProveedor.getText().trim());
                if (aniosExperiencia < 0) {
                    throw new NumberFormatException("Años de experiencia no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Los años de experiencia deben ser un número válido.", 
                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Generar ID único
            String nuevoId = administradorService.generarSiguienteId("proveedor");
            
            // Crear nuevo proveedor
            Model.Proveedor nuevoProveedor = new Model.Proveedor(
                nuevoId,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                cboGeneroProveedor.getSelectedItem().toString(),
                txtCedulaProveedor.getText().trim(),
                txtTelefonoProveedor.getText().trim(),
                txtCorreoProveedor.getText().trim(),
                aniosExperiencia,
                txtUsuarioProveedor.getText().trim(),
                txtContraseñaProveedor.getText().trim(),
                "Proveedor"
            );
            
            // Agregar proveedor
            administradorService.agregarProveedor(nuevoProveedor);
            
            // Actualizar tabla
            cargarProveedoresEnTabla();
            
            // Limpiar campos
            limpiarCamposProveedor();
            
            JOptionPane.showMessageDialog(this, 
                "Proveedor agregado exitosamente.\nID: " + nuevoId, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al agregar proveedor: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========================================
    // MÉTODOS PARA VALIDACIÓN DE DUPLICADOS
    // ========================================
    
    private boolean validarProveedor() {
        String usuario = txtUsuarioProveedor.getText().trim();
        String correo = txtCorreoProveedor.getText().trim();
        String telefono = txtTelefonoProveedor.getText().trim();
        String cedula = txtCedulaProveedor.getText().trim();
        
        // Obtener ID del proveedor seleccionado (para edición)
        int filaSeleccionada = jTableProveedores.getSelectedRow();
        String idProveedorSeleccionado = null;
        if (filaSeleccionada >= 0) {
            idProveedorSeleccionado = jTableProveedores.getValueAt(filaSeleccionada, 0).toString();
        }
        
        // Usar el nuevo método de persistencia
        if (idProveedorSeleccionado != null) {
            // Caso de edición: excluir el proveedor actual
            return administradorService.validarProveedorParaEdicion(usuario, correo, telefono, cedula, idProveedorSeleccionado);
        } else {
            // Caso de nuevo proveedor: validar sin exclusiones
            return administradorService.validarProveedorSinDuplicados(usuario, correo, telefono, cedula);
        }
    }
    
    // ========================================
    // MÉTODOS PARA EDICIÓN DE PROVEEDORES
    // ========================================
    
    private void establecerCamposProveedorNoEditables() {
        txtNombreProveedor.setEditable(false);
        txtApellidosProveedor.setEditable(false);
        txtUsuarioProveedor.setEditable(false);
        txtContraseñaProveedor.setEditable(false);
        txtCorreoProveedor.setEditable(false);
        txtTelefonoProveedor.setEditable(false);
        txtCedulaProveedor.setEditable(false);
        txtAniosExperienciaProveedor.setEditable(false);
        cboGeneroProveedor.setEnabled(false);
    }
    
    private void establecerCamposProveedorEditables() {
        txtNombreProveedor.setEditable(true);
        txtApellidosProveedor.setEditable(true);
        txtUsuarioProveedor.setEditable(true);
        txtContraseñaProveedor.setEditable(true);
        txtCorreoProveedor.setEditable(true);
        txtTelefonoProveedor.setEditable(true);
        txtCedulaProveedor.setEditable(true);
        txtAniosExperienciaProveedor.setEditable(true);
        cboGeneroProveedor.setEnabled(true);
    }
    
    private void habilitarEdicionProveedor() {
        // Verificar si ya se está editando
        if (editandoDatosProveedor) {
            JOptionPane.showMessageDialog(this, 
                "Ya está editando los datos de un proveedor.\n" +
                "Por favor, guarde los cambios o cancele la edición para continuar.", 
                "Modo Edición Activo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int filaSeleccionada = jTableProveedores.getSelectedRow();
        
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un proveedor de la tabla para editar.", 
                "Seleccionar Proveedor", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Activar modo edición
        editandoDatosProveedor = true;
        
        // Habilitar campos para edición
        establecerCamposProveedorEditables();
        
        JOptionPane.showMessageDialog(this, 
            "Modo de edición habilitado.\nPuede modificar los datos y usar 'Guardar Cambios'.", 
            "Edición Habilitada", JOptionPane.INFORMATION_MESSAGE);
        
        System.out.println("Edición de proveedor habilitada");
    }
    
    private void guardarEdicionProveedor() {
        int filaSeleccionada = jTableProveedores.getSelectedRow();
        
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un proveedor de la tabla.", 
                "Seleccionar Proveedor", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validar campos obligatorios
        if (txtNombreProveedor.getText().trim().isEmpty() ||
            txtApellidosProveedor.getText().trim().isEmpty() ||
            txtUsuarioProveedor.getText().trim().isEmpty() ||
            txtContraseñaProveedor.getText().trim().isEmpty() ||
            txtCorreoProveedor.getText().trim().isEmpty() ||
            txtTelefonoProveedor.getText().trim().isEmpty() ||
            txtCedulaProveedor.getText().trim().isEmpty() ||
            txtAniosExperienciaProveedor.getText().trim().isEmpty() ||
            cboGeneroProveedor.getSelectedIndex() == 0) {
            
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos obligatorios.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String idProveedor = jTableProveedores.getValueAt(filaSeleccionada, 0).toString();
            String usuarioSeleccionado = txtUsuarioProveedor.getText().trim();
            
            // Separar nombres y apellidos correctamente
            String[] nombres = txtNombreProveedor.getText().trim().split("\\s+", 2);
            String primerNombre = nombres[0];
            String segundoNombre = nombres.length > 1 ? nombres[1].trim() : "";
            
            String[] apellidos = txtApellidosProveedor.getText().trim().split("\\s+", 2);
            String primerApellido = apellidos[0];
            String segundoApellido = apellidos.length > 1 ? apellidos[1].trim() : "";
            
            // Validar años de experiencia
            int aniosExperiencia;
            try {
                aniosExperiencia = Integer.parseInt(txtAniosExperienciaProveedor.getText().trim());
                if (aniosExperiencia < 0) {
                    throw new NumberFormatException("Años de experiencia no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Los años de experiencia deben ser un número válido.", 
                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear proveedor actualizado
            Model.Proveedor proveedorActualizado = new Model.Proveedor(
                idProveedor,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                cboGeneroProveedor.getSelectedItem().toString(),
                txtCedulaProveedor.getText().trim(),
                txtTelefonoProveedor.getText().trim(),
                txtCorreoProveedor.getText().trim(),
                aniosExperiencia,
                txtUsuarioProveedor.getText().trim(),
                txtContraseñaProveedor.getText().trim(),
                "Proveedor"
            );
            
            // Usar el método de persistencia con validación y confirmación
            boolean guardado = administradorService.guardarEdicionProveedorConConfirmacion(idProveedor, proveedorActualizado, usuarioSeleccionado);
            
            if (guardado) {
                // Actualizar tabla y resetear estado solo si se guardó exitosamente
                cargarProveedoresEnTabla();
                
                // Resetear estado de edición
                editandoDatosProveedor = false;
                
                // Deshabilitar edición
                establecerCamposProveedorNoEditables();
            }
            
        } catch (Exception e) {
            System.out.println("Error al procesar edición de proveedor: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al procesar la edición: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarProveedor() {
        int filaSeleccionada = jTableProveedores.getSelectedRow();
        
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un proveedor de la tabla para eliminar.", 
                "Seleccionar Proveedor", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String idProveedor = jTableProveedores.getValueAt(filaSeleccionada, 0).toString();
        String usuarioSeleccionado = txtUsuarioProveedor.getText().trim();
        
        // Usar el método de persistencia con confirmación
        boolean eliminado = administradorService.eliminarProveedorConConfirmacion(idProveedor, usuarioSeleccionado);
        
        if (eliminado) {
            // Actualizar tabla y limpiar campos solo si se eliminó exitosamente
            cargarProveedoresEnTabla();
            limpiarCamposProveedor();
        }
    }

    // ========================================
    // MÉTODOS PARA GESTIÓN DE RECEPCIONISTAS
    // ========================================
    
    private void configurarEventosRecepcionistas() {
        // Configurar selección de tabla de recepcionistas
        jTableRecepcionista.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = jTableRecepcionista.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    System.out.println("🔍 SELECCIÓN DE TABLA - Estado ANTES de cargar datos: " + estadoEdicionRecepcionista);
                    cargarDatosRecepcionistaSeleccionado();
                    // Cuando hay selección, campos no editables
                    establecerCamposRecepcionistaNoEditables();
                    System.out.println("🔍 SELECCIÓN DE TABLA - Estado DESPUÉS de cargar datos: " + estadoEdicionRecepcionista);
                }
            }
        });
        
        // Agregar listener para doble clic en área vacía (limpiar campos)
        jTableRecepcionista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int fila = jTableRecepcionista.rowAtPoint(evt.getPoint());
                    if (fila == -1) {
                        // Doble clic en área vacía
                        limpiarCamposRecepcionista();
                    }
                }
            }
        });
        
        // Configurar botones de recepcionistas
        btnAgregarRecepcionista.addActionListener(evt -> agregarRecepcionista());
        // BtnEditarDatosRecepcionista.addActionListener(evt -> habilitarEdicionRecepcionista()); // ELIMINADO - Duplicado, usa el listener del NetBeans
        btnEliminarRecepcionista.addActionListener(evt -> eliminarRecepcionista());
        btnLimpiarDatosRecepcionista.addActionListener(evt -> limpiarDatosRecepcionista());
        
        // Configurar el nuevo botón de cancelar edición
        // NOTA: Asegúrate de que BtnCancelarEdicionRecepcionista esté agregado al diseño
        if (BtnCancelarEdicionRecepcionistas != null) {
            BtnCancelarEdicionRecepcionistas.addActionListener(evt -> BtnCancelarEdicionRecepcionistaActionPerformed(evt));
        }
        
        // Cargar datos iniciales
        cargarRecepcionistasEnTabla();
        limpiarCamposRecepcionista();
        
        // Configurar estado inicial de campos (editables por defecto)
        establecerCamposRecepcionistaEditables();
        
        System.out.println("Eventos de recepcionistas configurados");
    }

    // ========================================
    // MÉTODOS PARA GESTIÓN DE CLIENTES
    // ========================================
    
    private void configurarEventosClientes() {
        // Configurar selección de tabla de clientes
        jTableRecepcionista1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    // VALIDACIÓN: No permitir seleccionar otro cliente mientras hay uno en edición
                    if (estadoEdicionCliente == EDICION_VIGENTE) {
                        JOptionPane.showMessageDialog(this, 
                            "⚠️ No puede seleccionar otro cliente mientras está editando uno.\n" +
                            "Por favor, termine la edición actual primero.", 
                            "Edición en Proceso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    System.out.println("🔍 SELECCIÓN DE TABLA CLIENTES - Estado ANTES de cargar datos: " + estadoEdicionCliente);
                    cargarDatosClienteSeleccionado();
                    // Cuando hay selección, campos no editables
                    establecerCamposClienteNoEditables();
                    System.out.println("🔍 SELECCIÓN DE TABLA CLIENTES - Estado DESPUÉS de cargar datos: " + estadoEdicionCliente);
                }
            }
        });
        
        // Agregar listener para doble clic en área vacía (limpiar campos)
        jTableRecepcionista1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
                    if (filaSeleccionada < 0) {
                        limpiarCamposCliente();
                    }
                }
            }
        });
        
        // Configurar botones de clientes
        btnAgregarCliente.addActionListener(evt -> agregarCliente());
        
        // NOTA: BtnEditarDatosCliente usa ActionListener de NetBeans para evitar duplicación
        /*
        BtnEditarDatosCliente.addActionListener(evt -> {
            System.out.println("� ¡ActionListener de BtnEditarDatosCliente ejecutado!");
            
            if (estadoEdicionCliente == EDICION_VIGENTE) {
                // Si está en modo edición, el botón actúa como "Volver"
                cancelarEdicionCliente();
            } else {
                // Si no está en modo edición, habilita la edición
                habilitarEdicionCliente();
            }
        });
        */
        
        System.out.println("✅ BtnEditarDatosCliente usa ActionListener de NetBeans (sin duplicación)");
        
        // Configurar el botón de cancelar edición cliente
        BtnCancelarEdicionCliente.addActionListener(evt -> {
            System.out.println("🚀 ¡ActionListener de BtnCancelarEdicionCliente ejecutado!");
            
            // Confirmar cancelación con el usuario
            int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea cancelar la edición?\n" +
                "Se perderán todos los cambios realizados.",
                "Confirmar Cancelación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                cancelarEdicionCliente();
            }
        });
        
        System.out.println("✅ BtnCancelarEdicionCliente ActionListener configurado correctamente");
        
        btnEliminarCliente.addActionListener(evt -> eliminarCliente());
        btnLimpiarDatosCliente.addActionListener(evt -> limpiarDatosCliente());
        
        // Cargar datos iniciales
        cargarClientesEnTabla();
        limpiarCamposCliente();
        
        // Configurar estado inicial de campos (editables por defecto)
        establecerCamposClienteEditables();
        
        System.out.println("Eventos de clientes configurados");
    }
    
    private void cargarRecepcionistasEnTabla() {
        try {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableRecepcionista.getModel();
            model.setRowCount(0); // Limpiar tabla
            
            listaDoble.Lista listaRecepcionistas = administradorService.obtenerTodosLosRecepcionistas();
            
            if (listaRecepcionistas != null && listaRecepcionistas.getPrimero() != null) {
                listaDoble.Nodo actual = listaRecepcionistas.getPrimero();
                while (actual != null) {
                    Model.Recepcionista recepcionista = (Model.Recepcionista) actual.getDato();
                    Object[] fila = {
                        recepcionista.getIdRecepcionista(),
                        recepcionista.getPrimerNombre() + " " + 
                            (recepcionista.getSegundoNombre() != null ? recepcionista.getSegundoNombre() + " " : ""),
                        recepcionista.getPrimerApellido() + " " + 
                            (recepcionista.getSegundoApellido() != null ? recepcionista.getSegundoApellido() : ""),
                        recepcionista.getCedula(),
                        recepcionista.getTelefono(),
                        recepcionista.getCorreo()
                    };
                    model.addRow(fila);
                    actual = actual.getSiguiente();
                }
                System.out.println("Tabla de recepcionistas cargada correctamente");
            } else {
                System.out.println("No hay recepcionistas para mostrar");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar recepcionistas: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al cargar la tabla de recepcionistas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarDatosRecepcionistaSeleccionado() {
        try {
            int filaSeleccionada = jTableRecepcionista.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String idRecepcionista = (String) jTableRecepcionista.getValueAt(filaSeleccionada, 0);
                
                Model.Recepcionista recepcionista = administradorService.buscarRecepcionistaPorId(idRecepcionista);
                if (recepcionista != null) {
                    // Cargar datos en los campos
                    txtNombreRecepcionista.setText(recepcionista.getPrimerNombre() + " " + 
                        (recepcionista.getSegundoNombre() != null ? recepcionista.getSegundoNombre() + " " : ""));
                    txtApellidosRecepcionista.setText(recepcionista.getPrimerApellido() + " " + 
                        (recepcionista.getSegundoApellido() != null ? recepcionista.getSegundoApellido() : ""));
                    txtUsuarioRecepcionista.setText(recepcionista.getUsuario());
                    txtContraseñaRecepcionista.setText(recepcionista.getContraseña());
                    txtCorreoRecepcionista.setText(recepcionista.getCorreo());
                    txtTelefonoRecepcionista.setText(recepcionista.getTelefono());
                    txtCedulaRecepcionista.setText(recepcionista.getCedula());
                    txtAniosExperienciaRecepcionista.setText("0"); // Campo por defecto
                    cboGeneroRecepcionista.setSelectedItem(recepcionista.getGenero());
                    
                    System.out.println("Datos del recepcionista cargados: " + recepcionista.getUsuario());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos del recepcionista: " + e.getMessage());
        }
    }
    
    private void limpiarCamposRecepcionista() {
        txtNombreRecepcionista.setText("");
        txtApellidosRecepcionista.setText("");
        txtUsuarioRecepcionista.setText("");
        txtContraseñaRecepcionista.setText("");
        txtCorreoRecepcionista.setText("");
        txtTelefonoRecepcionista.setText("");
        txtCedulaRecepcionista.setText("");
        txtAniosExperienciaRecepcionista.setText("");
        cboGeneroRecepcionista.setSelectedIndex(0);
        
        // Limpiar selección de la tabla
        jTableRecepcionista.clearSelection();
        System.out.println("Campos de recepcionista limpiados");
    }
    
    /**
     * Método para el botón "Limpiar Datos" - limpia todos los campos y resetea la tabla
     */
    private void limpiarDatosRecepcionista() {
        // Verificar si está en modo edición vigente
        if (estadoEdicionRecepcionista == EDICION_VIGENTE) {
            JOptionPane.showMessageDialog(this, 
                "No se pueden limpiar los datos mientras está editando un recepcionista.\n" +
                "Por favor, termine la edición primero.", 
                "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea limpiar todos los datos?\n" +
            "Esta acción limpiará todos los campos del formulario.",
            "Confirmar Limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            limpiarCamposRecepcionista();
            
            // Establecer campos como editables
            establecerCamposRecepcionistaEditables();
            
            // Recargar la tabla para refrescar datos
            cargarRecepcionistasEnTabla();
            
            JOptionPane.showMessageDialog(this, 
                "Datos limpiados correctamente.\n" +
                "Ahora puede agregar un nuevo recepcionista.", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Datos de recepcionista limpiados por el usuario");
        }
    }
    
    private void agregarRecepcionista() {
        try {
            // Verificar si está en modo edición vigente
            if (estadoEdicionRecepcionista == EDICION_VIGENTE) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede agregar un nuevo recepcionista mientras está editando otro.\n" +
                    "Por favor, termine la edición primero.", 
                    "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que todos los campos estén llenos
            if (!validarRecepcionista()) {
                return;
            }
            
            // Procesar nombres y apellidos
            String[] nombres = txtNombreRecepcionista.getText().trim().split("\\s+");
            String[] apellidos = txtApellidosRecepcionista.getText().trim().split("\\s+");
            
            String primerNombre = nombres.length > 0 ? nombres[0] : "";
            String segundoNombre = nombres.length > 1 ? nombres[1] : "";
            String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
            String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";
            
            // Generar ID automático
            String idRecepcionista = administradorService.generarSiguienteId("recepcionista");
            
            // Crear objeto Recepcionista
            Model.Recepcionista nuevoRecepcionista = new Model.Recepcionista(
                idRecepcionista,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                (String) cboGeneroRecepcionista.getSelectedItem(),
                txtCedulaRecepcionista.getText().trim(),
                txtTelefonoRecepcionista.getText().trim(),
                txtCorreoRecepcionista.getText().trim(),
                txtUsuarioRecepcionista.getText().trim(),
                txtContraseñaRecepcionista.getText().trim(),
                "Recepcionista"
            );
            
            // Agregar a la lista
            administradorService.agregarRecepcionista(nuevoRecepcionista);
            
            // Actualizar tabla
            cargarRecepcionistasEnTabla();
            
            // Limpiar campos
            limpiarCamposRecepcionista();
            
            JOptionPane.showMessageDialog(this, 
                "Recepcionista agregado exitosamente.\n" +
                "ID asignado: " + idRecepcionista, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Recepcionista agregado: " + nuevoRecepcionista.getUsuario());
            
        } catch (Exception e) {
            System.out.println("Error al agregar recepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al agregar recepcionista: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ========================================
    // MÉTODOS PARA VALIDACIÓN DE DUPLICADOS
    // ========================================
    
    private boolean validarRecepcionista() {
        // Validar campos obligatorios
        if (txtNombreRecepcionista.getText().trim().isEmpty() ||
            txtApellidosRecepcionista.getText().trim().isEmpty() ||
            txtUsuarioRecepcionista.getText().trim().isEmpty() ||
            txtContraseñaRecepcionista.getText().trim().isEmpty() ||
            txtCorreoRecepcionista.getText().trim().isEmpty() ||
            txtTelefonoRecepcionista.getText().trim().isEmpty() ||
            txtCedulaRecepcionista.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.\n" +
                "Por favor, complete toda la información.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validar formatos usando persistencia
        if (!administradorService.validarFormatosRecepcionista(
                txtNombreRecepcionista.getText().trim(),
                txtApellidosRecepcionista.getText().trim(),
                txtCorreoRecepcionista.getText().trim(),
                txtTelefonoRecepcionista.getText().trim(),
                txtCedulaRecepcionista.getText().trim(),
                txtAniosExperienciaRecepcionista.getText().trim())) {
            return false;
        }
        
        // Validar duplicados usando persistencia
        return administradorService.validarRecepcionistaSinDuplicados(
            txtUsuarioRecepcionista.getText().trim(),
            txtCorreoRecepcionista.getText().trim(),
            txtTelefonoRecepcionista.getText().trim(),
            txtCedulaRecepcionista.getText().trim()
        );
    }
    
    /**
     * Validar recepcionista para EDICIÓN - excluye al recepcionista actual de la validación de duplicados
     */
    private boolean validarRecepcionistaParaEdicion(String idRecepcionistaEditando) {
        // Validar campos obligatorios
        if (txtNombreRecepcionista.getText().trim().isEmpty() ||
            txtApellidosRecepcionista.getText().trim().isEmpty() ||
            txtUsuarioRecepcionista.getText().trim().isEmpty() ||
            txtContraseñaRecepcionista.getText().trim().isEmpty() ||
            txtCorreoRecepcionista.getText().trim().isEmpty() ||
            txtTelefonoRecepcionista.getText().trim().isEmpty() ||
            txtCedulaRecepcionista.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.\n" +
                "Por favor, complete toda la información.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Validar formatos usando persistencia
        if (!administradorService.validarFormatosRecepcionista(
                txtNombreRecepcionista.getText().trim(),
                txtApellidosRecepcionista.getText().trim(),
                txtCorreoRecepcionista.getText().trim(),
                txtTelefonoRecepcionista.getText().trim(),
                txtCedulaRecepcionista.getText().trim(),
                txtAniosExperienciaRecepcionista.getText().trim())) {
            return false;
        }
        
        // Validar duplicados para edición (excluyendo el recepcionista actual)
        return administradorService.validarRecepcionistaParaEdicion(
            txtUsuarioRecepcionista.getText().trim(),
            txtCorreoRecepcionista.getText().trim(),
            txtTelefonoRecepcionista.getText().trim(),
            txtCedulaRecepcionista.getText().trim(),
            idRecepcionistaEditando
        );
    }
    
    // ========================================
    // MÉTODOS DE VALIDACIÓN DE FORMATOS
    // ========================================
    
    private boolean validarFormatosCamposRecepcionista() {
        String nombres = txtNombreRecepcionista.getText().trim();
        String apellidos = txtApellidosRecepcionista.getText().trim();
        String correo = txtCorreoRecepcionista.getText().trim();
        String telefono = txtTelefonoRecepcionista.getText().trim();
        String cedula = txtCedulaRecepcionista.getText().trim();
        String aniosExperiencia = txtAniosExperienciaRecepcionista.getText().trim();
        
        // Validar que nombres y apellidos no contengan números
        if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            JOptionPane.showMessageDialog(this, 
                "Los nombres solo pueden contener letras y espacios.\n" +
                "No se permiten números ni caracteres especiales.", 
                "Formato de Nombre Inválido", JOptionPane.ERROR_MESSAGE);
            txtNombreRecepcionista.requestFocus();
            return false;
        }
        
        if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            JOptionPane.showMessageDialog(this, 
                "Los apellidos solo pueden contener letras y espacios.\n" +
                "No se permiten números ni caracteres especiales.", 
                "Formato de Apellido Inválido", JOptionPane.ERROR_MESSAGE);
            txtApellidosRecepcionista.requestFocus();
            return false;
        }
        
        // Validar que el correo contenga @
        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(this, 
                "El correo electrónico debe contener el símbolo '@'.\n" +
                "Ejemplo: usuario@dominio.com", 
                "Formato de Correo Inválido", JOptionPane.ERROR_MESSAGE);
            txtCorreoRecepcionista.requestFocus();
            return false;
        }
        
        // Validar formato más completo del correo
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, 
                "El formato del correo electrónico no es válido.\n" +
                "Ejemplo: usuario@dominio.com", 
                "Formato de Correo Inválido", JOptionPane.ERROR_MESSAGE);
            txtCorreoRecepcionista.requestFocus();
            return false;
        }
        
        // Validar que teléfono solo contenga números
        if (!telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, 
                "El teléfono solo puede contener números.\n" +
                "No se permiten letras, espacios ni caracteres especiales.", 
                "Formato de Teléfono Inválido", JOptionPane.ERROR_MESSAGE);
            txtTelefonoRecepcionista.requestFocus();
            return false;
        }

        // Validar longitud del teléfono ( 10 dígitos)
        if (telefono.length() != 10) {
            JOptionPane.showMessageDialog(this, 
                "El teléfono debe tener 10 dígitos.", 
                "Longitud de Teléfono Inválida", JOptionPane.ERROR_MESSAGE);
            txtTelefonoRecepcionista.requestFocus();
            return false;
        }
        
        // Validar que cédula solo contenga números
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, 
                "La cédula solo puede contener números.\n" +
                "No se permiten letras, espacios ni caracteres especiales.", 
                "Formato de Cédula Inválido", JOptionPane.ERROR_MESSAGE);
            txtCedulaRecepcionista.requestFocus();
            return false;
        }
        
        // Validar longitud de la cédula (entre 6 y 15 dígitos)
        if (cedula.length() < 6 || cedula.length() > 15) {
            JOptionPane.showMessageDialog(this, 
                "La cédula debe tener entre 6 y 15 dígitos.", 
                "Longitud de Cédula Inválida", JOptionPane.ERROR_MESSAGE);
            txtCedulaRecepcionista.requestFocus();
            return false;
        }
        
        // Validar años de experiencia (si no está vacío, debe ser un número)
        if (!aniosExperiencia.isEmpty() && !aniosExperiencia.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, 
                "Los años de experiencia solo pueden contener números.\n" +
                "Deje el campo vacío si no tiene experiencia.", 
                "Formato de Años Inválido", JOptionPane.ERROR_MESSAGE);
            txtAniosExperienciaRecepcionista.requestFocus();
            return false;
        }
        
        // Validar rango de años de experiencia (0-50)
        if (!aniosExperiencia.isEmpty()) {
            int anos = Integer.parseInt(aniosExperiencia);
            if (anos < 0 || anos > 50) {
                JOptionPane.showMessageDialog(this, 
                    "Los años de experiencia deben estar entre 0 y 50.", 
                    "Rango de Años Inválido", JOptionPane.ERROR_MESSAGE);
                txtAniosExperienciaRecepcionista.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    // ========================================
    // MÉTODOS PARA EDICIÓN DE RECEPCIONISTAS
    // ========================================
    
    private void establecerCamposRecepcionistaNoEditables() {
        txtNombreRecepcionista.setEditable(false);
        txtApellidosRecepcionista.setEditable(false);
        txtUsuarioRecepcionista.setEditable(false);
        txtContraseñaRecepcionista.setEditable(false);
        txtCorreoRecepcionista.setEditable(false);
        txtTelefonoRecepcionista.setEditable(false);
        txtCedulaRecepcionista.setEditable(false);
        txtAniosExperienciaRecepcionista.setEditable(false);
        cboGeneroRecepcionista.setEnabled(false);
    }
    
    private void establecerCamposRecepcionistaEditables() {
        txtNombreRecepcionista.setEditable(true);
        txtApellidosRecepcionista.setEditable(true);
        txtUsuarioRecepcionista.setEditable(true);
        txtContraseñaRecepcionista.setEditable(true);
        txtCorreoRecepcionista.setEditable(true);
        txtTelefonoRecepcionista.setEditable(true);
        txtCedulaRecepcionista.setEditable(true);
        txtAniosExperienciaRecepcionista.setEditable(true);
        cboGeneroRecepcionista.setEnabled(true);
    }
    
    private void habilitarEdicionRecepcionista() {
        int filaSeleccionada = jTableRecepcionista.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un recepcionista de la tabla para editarlo.", 
                "Ningún Recepcionista Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Verificar si ya está en modo edición vigente
        if (estadoEdicionRecepcionista == EDICION_VIGENTE) {
            JOptionPane.showMessageDialog(this, 
                "Ya se encuentra en modo de edición.\n" +
                "Use 'Guardar Cambios' para confirmar cambios o 'Cancelar Edición' para descartar.", 
                "Modo Edición Activo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Habilitar campos para edición
        establecerCamposRecepcionistaEditables();
        
        // Cambiar botón editar a modo "Volver"
        BtnEditarDatosRecepcionista.setText("Volver");
        BtnEditarDatosRecepcionista.setBackground(new java.awt.Color(102, 102, 102)); // Color gris
        
        // Mostrar botón de cancelar edición
        if (BtnCancelarEdicionRecepcionistas != null) {
            BtnCancelarEdicionRecepcionistas.setVisible(true);
        }
        
        estadoEdicionRecepcionista = EDICION_VIGENTE; // Estado: Edición Vigente
        System.out.println("*** ESTADO CAMBIADO A VIGENTE EN habilitarEdicionRecepcionista() ***");
        
        System.out.println("ANTES del diálogo - Estado: " + estadoEdicionRecepcionista);
        
        JOptionPane.showMessageDialog(this, 
            "Modo de edición activado.\n" +
            "Modifique los datos necesarios y haga clic en 'Guardar Cambios'.\n" +
            "Para cancelar, use el botón 'Cancelar Edición'.\n\n" +
            "Estado actual: EDICIÓN VIGENTE", 
            "Modo Edición", JOptionPane.INFORMATION_MESSAGE);
        
        System.out.println("DESPUÉS del diálogo - Estado: " + estadoEdicionRecepcionista);
        System.out.println("habilitarEdicionRecepcionista() TERMINANDO - Estado final: " + estadoEdicionRecepcionista);
    }
    
    private void cancelarEdicionRecepcionista() {
        System.out.println("=== INICIO cancelarEdicionRecepcionista() ===");
        System.out.println("Estado ANTES de cancelar: " + estadoEdicionRecepcionista);
        System.out.println("STACK TRACE - ¿Quién llamó a cancelarEdicionRecepcionista?:");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < Math.min(5, stackTrace.length); i++) {
            System.out.println("  [" + i + "] " + stackTrace[i].toString());
        }
        
        // Restaurar campos a no editables
        establecerCamposRecepcionistaNoEditables();
        
        // Restaurar el texto y color original del botón
        BtnEditarDatosRecepcionista.setText("Editar datos Recepcionista");
        BtnEditarDatosRecepcionista.setBackground(new java.awt.Color(255, 153, 51)); // Color original
        
        // Ocultar botón de cancelar edición
        if (BtnCancelarEdicionRecepcionistas != null) {
            BtnCancelarEdicionRecepcionistas.setVisible(false);
        }
        
        // Recargar los datos originales del recepcionista seleccionado
        cargarDatosRecepcionistaSeleccionado();
        
        estadoEdicionRecepcionista = EDICION_CANCELADA; // Estado: Edición Cancelada
        System.out.println("*** ESTADO CAMBIADO A CANCELADA EN cancelarEdicionRecepcionista() ***");
        System.out.println("Estado DESPUÉS de cambiar a CANCELADA: " + estadoEdicionRecepcionista);
        
        System.out.println("ANTES de mostrar diálogo de cancelación...");
        JOptionPane.showMessageDialog(this, 
            "Edición cancelada. Los datos han sido restaurados.\n\n" +
            "Estado actual: EDICIÓN CANCELADA", 
            "Edición Cancelada", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("DESPUÉS de mostrar diálogo de cancelación...");
        System.out.println("=== FIN cancelarEdicionRecepcionista() ===");
        
        System.out.println("Edición de recepcionista cancelada - Estado: " + estadoEdicionRecepcionista);
    }
    
    private void guardarEdicionRecepcionista() {
        try {
            int filaSeleccionada = jTableRecepcionista.getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Error: No hay recepcionista seleccionado.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener datos para la confirmación
            String idRecepcionista = (String) jTableRecepcionista.getValueAt(filaSeleccionada, 0);
            String usuarioRecepcionista = txtUsuarioRecepcionista.getText().trim();
            
            // Mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea guardar los cambios del recepcionista?\n\n" +
                "ID: " + idRecepcionista + "\n" +
                "Usuario: " + usuarioRecepcionista + "\n\n" +
                "Esta acción actualizará los datos existentes.",
                "Confirmar Edición",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Usuario canceló
            }
            
            // Validar los datos antes de guardar (usando validación especial para edición)
            if (!validarRecepcionistaParaEdicion(idRecepcionista)) {
                return;
            }
            
            // Buscar recepcionista existente
            Model.Recepcionista recepcionistaExistente = administradorService.buscarRecepcionistaPorId(idRecepcionista);
            if (recepcionistaExistente == null) {
                JOptionPane.showMessageDialog(this, 
                    "Error: Recepcionista no encontrado.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Procesar nombres y apellidos
            String[] nombres = txtNombreRecepcionista.getText().trim().split("\\s+");
            String[] apellidos = txtApellidosRecepcionista.getText().trim().split("\\s+");
            
            String primerNombre = nombres.length > 0 ? nombres[0] : "";
            String segundoNombre = nombres.length > 1 ? nombres[1] : "";
            String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
            String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";
            
            // Actualizar datos
            recepcionistaExistente.setPrimerNombre(primerNombre);
            recepcionistaExistente.setSegundoNombre(segundoNombre);
            recepcionistaExistente.setPrimerApellido(primerApellido);
            recepcionistaExistente.setSegundoApellido(segundoApellido);
            recepcionistaExistente.setGenero((String) cboGeneroRecepcionista.getSelectedItem());
            recepcionistaExistente.setCedula(txtCedulaRecepcionista.getText().trim());
            recepcionistaExistente.setTelefono(txtTelefonoRecepcionista.getText().trim());
            recepcionistaExistente.setCorreo(txtCorreoRecepcionista.getText().trim());
            recepcionistaExistente.setUsuario(txtUsuarioRecepcionista.getText().trim());
            recepcionistaExistente.setContraseña(txtContraseñaRecepcionista.getText().trim());
            
            // Guardar en el servicio
            boolean actualizado = administradorService.actualizarRecepcionista(recepcionistaExistente);
            
            if (actualizado) {
                // Actualizar tabla
                cargarRecepcionistasEnTabla();
                
                // Establecer campos como no editables
                establecerCamposRecepcionistaNoEditables();
                
                // Restaurar estado normal de los botones
                BtnEditarDatosRecepcionista.setText("Editar datos Recepcionista");
                BtnEditarDatosRecepcionista.setBackground(new java.awt.Color(255, 153, 51));
                
                // Ocultar botón de cancelar edición
                if (BtnCancelarEdicionRecepcionistas != null) {
                    BtnCancelarEdicionRecepcionistas.setVisible(false);
                }
                
                estadoEdicionRecepcionista = EDICION_APROBADA; // Estado: Edición Aprobada
                
                JOptionPane.showMessageDialog(this, 
                    "Recepcionista actualizado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Después del mensaje, reiniciar estado para permitir nuevas ediciones
                estadoEdicionRecepcionista = EDICION_CANCELADA;
                
                System.out.println("Recepcionista actualizado: " + recepcionistaExistente.getUsuario());
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar el recepcionista.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            System.out.println("Error al guardar edición del recepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al guardar la edición: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarRecepcionista() {
        try {
            // Verificar si está en modo edición vigente
            if (estadoEdicionRecepcionista == EDICION_VIGENTE) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede eliminar un recepcionista mientras lo está editando.\n" +
                    "Por favor, termine la edición primero.", 
                    "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int filaSeleccionada = jTableRecepcionista.getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, seleccione un recepcionista de la tabla para eliminarlo.", 
                    "Ningún Recepcionista Seleccionado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String idRecepcionista = (String) jTableRecepcionista.getValueAt(filaSeleccionada, 0);
            String usuarioSeleccionado = txtUsuarioRecepcionista.getText().trim();
            
            // Usar el método de persistencia con confirmación
            boolean eliminado = administradorService.eliminarRecepcionistaConConfirmacion(idRecepcionista, usuarioSeleccionado);
            
            if (eliminado) {
                // Actualizar tabla y limpiar campos solo si se eliminó exitosamente
                cargarRecepcionistasEnTabla();
                limpiarCamposRecepcionista();
                System.out.println("Recepcionista eliminado: " + idRecepcionista);
            }
            
        } catch (Exception e) {
            System.out.println("Error al procesar eliminación de recepcionista: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error al procesar la eliminación: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ========================================
    // MÉTODOS BÁSICOS PARA CLIENTES
    // ========================================
    
    private void cargarClientesEnTabla() {
        try {
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableRecepcionista1.getModel();
            model.setRowCount(0); // Limpiar tabla
            
            listaDoble.Lista listaClientes = administradorService.obtenerTodosLosClientes();
            
            if (listaClientes != null && listaClientes.getPrimero() != null) {
                listaDoble.Nodo actual = listaClientes.getPrimero();
                while (actual != null) {
                    Model.Cliente cliente = (Model.Cliente) actual.getDato();
                    model.addRow(new Object[]{
                        cliente.getIdCliente(),
                        cliente.getPrimerNombre() + " " + (cliente.getSegundoNombre() != null ? cliente.getSegundoNombre() : ""),
                        cliente.getPrimerApellido() + " " + (cliente.getSegundoApellido() != null ? cliente.getSegundoApellido() : ""),
                        cliente.getCedula(),
                        cliente.getTelefono(),
                        cliente.getCorreo()
                    });
                    actual = actual.getSiguiente();
                }
                System.out.println("Tabla de clientes cargada correctamente");
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
    
    private void cargarDatosClienteSeleccionado() {
        try {
            int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String idCliente = (String) jTableRecepcionista1.getValueAt(filaSeleccionada, 0);
                
                Model.Cliente cliente = administradorService.buscarClientePorId(idCliente);
                if (cliente != null) {
                    txtNombreCliente.setText(cliente.getPrimerNombre() + " " + (cliente.getSegundoNombre() != null ? cliente.getSegundoNombre() : "").trim());
                    txtApellidosCliente.setText(cliente.getPrimerApellido() + " " + (cliente.getSegundoApellido() != null ? cliente.getSegundoApellido() : "").trim());
                    txtUsuarioCliente.setText(cliente.getUsuario());
                    txtContraseñaCliente.setText(cliente.getContraseña());
                    txtCorreoCliente.setText(cliente.getCorreo());
                    txtTelefonoCliente.setText(cliente.getTelefono());
                    txtCedulaCliente.setText(cliente.getCedula());
                    txtDireccionCliente.setText(cliente.getDireccion());
                    cboGeneroCliente.setSelectedItem(cliente.getGenero());
                    System.out.println("Datos del cliente cargados: " + cliente.getPrimerNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al cargar datos del cliente: " + e.getMessage());
        }
    }
    
    private void limpiarCamposCliente() {
        txtNombreCliente.setText("");
        txtApellidosCliente.setText("");
        txtUsuarioCliente.setText("");
        txtContraseñaCliente.setText("");
        txtCorreoCliente.setText("");
        txtTelefonoCliente.setText("");
        txtCedulaCliente.setText("");
        txtDireccionCliente.setText("");
        cboGeneroCliente.setSelectedIndex(0);
        
        // Limpiar selección de la tabla
        jTableRecepcionista1.clearSelection();
        System.out.println("Campos de cliente limpiados");
    }

    // ========================================
    // MÉTODOS DE EDICIÓN PARA CLIENTES
    // ========================================
    
    private void establecerCamposClienteNoEditables() {
        txtNombreCliente.setEditable(false);
        txtApellidosCliente.setEditable(false);
        txtUsuarioCliente.setEditable(false);
        txtContraseñaCliente.setEditable(false);
        txtCorreoCliente.setEditable(false);
        txtTelefonoCliente.setEditable(false);
        txtCedulaCliente.setEditable(false);
        txtDireccionCliente.setEditable(false);
        cboGeneroCliente.setEnabled(false);
    }
    
    private void establecerCamposClienteEditables() {
        txtNombreCliente.setEditable(true);
        txtApellidosCliente.setEditable(true);
        txtUsuarioCliente.setEditable(true);
        txtContraseñaCliente.setEditable(true);
        txtCorreoCliente.setEditable(true);
        txtTelefonoCliente.setEditable(true);
        txtCedulaCliente.setEditable(true);
        txtDireccionCliente.setEditable(true);
        cboGeneroCliente.setEnabled(true);
    }

    // ========================================
    // MÉTODOS CRUD PARA CLIENTES
    // ========================================
    
    private void agregarCliente() {
        try {
            // Verificar si está en modo edición vigente
            if (estadoEdicionCliente == EDICION_VIGENTE) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede agregar un nuevo cliente mientras está editando otro.\n" +
                    "Por favor, termine la edición primero.", 
                    "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar que todos los campos estén llenos
            if (!validarCliente()) {
                return;
            }
            
            // Procesar nombres y apellidos
            String[] nombres = txtNombreCliente.getText().trim().split("\\s+");
            String[] apellidos = txtApellidosCliente.getText().trim().split("\\s+");
            
            String primerNombre = nombres.length > 0 ? nombres[0] : "";
            String segundoNombre = nombres.length > 1 ? nombres[1] : "";
            String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
            String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";
            
            // Generar ID automático
            String idCliente = administradorService.generarSiguienteId("cliente");
            
            // Crear objeto Cliente
            Model.Cliente nuevoCliente = new Model.Cliente(
                idCliente,
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                (String) cboGeneroCliente.getSelectedItem(),
                txtCedulaCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                txtCorreoCliente.getText().trim(),
                txtUsuarioCliente.getText().trim(),
                txtContraseñaCliente.getText().trim(),
                txtDireccionCliente.getText().trim(),
                "Cliente"
            );
            
            // Agregar a la lista
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
    }
    
    private void eliminarCliente() {
        try {
            // Verificar si está en modo edición vigente
            if (estadoEdicionCliente == EDICION_VIGENTE) {
                JOptionPane.showMessageDialog(this, 
                    "No se puede eliminar un cliente mientras lo está editando.\n" +
                    "Por favor, termine la edición primero.", 
                    "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, seleccione un cliente de la tabla para eliminarlo.", 
                    "Ningún Cliente Seleccionado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String idCliente = (String) jTableRecepcionista1.getValueAt(filaSeleccionada, 0);
            String nombreCliente = (String) jTableRecepcionista1.getValueAt(filaSeleccionada, 1);
            
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar al cliente?\n" +
                "ID: " + idCliente + "\n" +
                "Nombre: " + nombreCliente + "\n\n" +
                "Esta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = administradorService.eliminarCliente(idCliente);
                
                if (eliminado) {
                    cargarClientesEnTabla();
                    limpiarCamposCliente();
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
    
    private void limpiarDatosCliente() {
        // Verificar si está en modo edición vigente
        if (estadoEdicionCliente == EDICION_VIGENTE) {
            JOptionPane.showMessageDialog(this, 
                "No se pueden limpiar los datos mientras está editando un cliente.\n" +
                "Por favor, termine la edición primero.", 
                "Operación No Permitida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea limpiar todos los datos?\n" +
            "Esta acción limpiará todos los campos del formulario.",
            "Confirmar Limpieza",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            limpiarCamposCliente();
            
            // Establecer campos como editables
            establecerCamposClienteEditables();
            
            // Recargar la tabla para refrescar datos
            cargarClientesEnTabla();
            
            JOptionPane.showMessageDialog(this, 
                "Datos limpiados correctamente.\n" +
                "Ahora puede agregar un nuevo cliente.", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("Datos de cliente limpiados por el usuario");
        }
    }

    // Método temporal para validarCliente - necesita implementación completa
    private boolean validarCliente() {
        // Validar campos obligatorios
        if (txtNombreCliente.getText().trim().isEmpty() ||
            txtApellidosCliente.getText().trim().isEmpty() ||
            txtUsuarioCliente.getText().trim().isEmpty() ||
            txtContraseñaCliente.getText().trim().isEmpty() ||
            txtCorreoCliente.getText().trim().isEmpty() ||
            txtTelefonoCliente.getText().trim().isEmpty() ||
            txtCedulaCliente.getText().trim().isEmpty() ||
            txtDireccionCliente.getText().trim().isEmpty() ||
            cboGeneroCliente.getSelectedItem() == null) {
            
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.\n" +
                "Por favor, complete toda la información.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar formatos usando persistencia
        if (!administradorService.validarFormatosCliente(
                txtNombreCliente.getText().trim(),
                txtApellidosCliente.getText().trim(),
                txtCorreoCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                txtCedulaCliente.getText().trim())) {
            return false;
        }

        // Validar duplicados usando persistencia
        return administradorService.validarClienteSinDuplicados(
            txtUsuarioCliente.getText().trim(),
            txtCorreoCliente.getText().trim(),
            txtTelefonoCliente.getText().trim(),
            txtCedulaCliente.getText().trim()
        );
    }

    private void guardarEdicionCliente() {
        try {
            int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Error: No hay cliente seleccionado.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos para la confirmación
            String idCliente = (String) jTableRecepcionista1.getValueAt(filaSeleccionada, 0);
            String usuarioCliente = txtUsuarioCliente.getText().trim();
            
            // Mensaje de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea guardar los cambios del cliente?\n\n" +
                "ID: " + idCliente + "\n" +
                "Usuario: " + usuarioCliente + "\n\n" +
                "Esta acción actualizará los datos existentes.",
                "Confirmar Edición",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            
            if (confirmacion != JOptionPane.YES_OPTION) {
                return; // Usuario canceló
            }

            // Validar los datos antes de guardar (para edición, excluye el cliente actual)
            if (!validarClienteParaEdicion()) {
                return;
            }

            // Buscar cliente existente
            Model.Cliente clienteExistente = administradorService.buscarClientePorId(idCliente);
            if (clienteExistente == null) {
                JOptionPane.showMessageDialog(this, 
                    "Error: Cliente no encontrado.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Procesar nombres y apellidos
            String[] nombres = txtNombreCliente.getText().trim().split("\\s+");
            String[] apellidos = txtApellidosCliente.getText().trim().split("\\s+");
            
            String primerNombre = nombres.length > 0 ? nombres[0] : "";
            String segundoNombre = nombres.length > 1 ? nombres[1] : "";
            String primerApellido = apellidos.length > 0 ? apellidos[0] : "";
            String segundoApellido = apellidos.length > 1 ? apellidos[1] : "";

            // Actualizar datos
            clienteExistente.setPrimerNombre(primerNombre);
            clienteExistente.setSegundoNombre(segundoNombre);
            clienteExistente.setPrimerApellido(primerApellido);
            clienteExistente.setSegundoApellido(segundoApellido);
            clienteExistente.setGenero((String) cboGeneroCliente.getSelectedItem());
            clienteExistente.setCedula(txtCedulaCliente.getText().trim());
            clienteExistente.setTelefono(txtTelefonoCliente.getText().trim());
            clienteExistente.setCorreo(txtCorreoCliente.getText().trim());
            clienteExistente.setUsuario(txtUsuarioCliente.getText().trim());
            clienteExistente.setContraseña(txtContraseñaCliente.getText().trim());
            clienteExistente.setDireccion(txtDireccionCliente.getText().trim());

            // Guardar en el servicio
            boolean actualizado = administradorService.actualizarCliente(clienteExistente);

            if (actualizado) {
                // Actualizar tabla
                cargarClientesEnTabla();
                
                // Establecer campos como no editables
                establecerCamposClienteNoEditables();
                
                // Restaurar estado normal de los botones
                BtnEditarDatosCliente.setText("Editar datos Cliente");
                BtnEditarDatosCliente.setBackground(new java.awt.Color(255, 153, 51));

                estadoEdicionCliente = EDICION_APROBADA; // Estado: Edición Aprobada

                JOptionPane.showMessageDialog(this, 
                    "Cliente actualizado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Después del mensaje, reiniciar estado para permitir nuevas ediciones
                estadoEdicionCliente = EDICION_CANCELADA;

                System.out.println("Cliente actualizado: " + clienteExistente.getUsuario());
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar el cliente.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("=== ERROR COMPLETO EN guardarEdicionCliente() ===");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Clase de excepción: " + e.getClass().getName());
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(this, 
                "Error al guardar la edición:\n" + 
                "Mensaje: " + e.getMessage() + "\n" +
                "Tipo: " + e.getClass().getSimpleName(), 
                "Error Detallado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void habilitarEdicionCliente() {
        // Verificar si ya está en modo edición vigente
        if (estadoEdicionCliente == EDICION_VIGENTE) {
            JOptionPane.showMessageDialog(this, 
                "Ya se encuentra en modo de edición.\n" +
                "Use 'Guardar Cambios' para confirmar cambios o 'Cancelar Edición' para descartar.", 
                "Modo Edición Activo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, 
                "⚠️ Por favor, seleccione un cliente de la tabla para editarlo.\n\n" +
                "Pasos:\n" +
                "1. Haga clic en una fila de la tabla de clientes\n" +
                "2. Luego presione el botón 'Editar datos Cliente'", 
                "Ningún Cliente Seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Habilitar campos para edición
        establecerCamposClienteEditables();
        
        // Cambiar botón editar a modo "Volver"
        BtnEditarDatosCliente.setText("Volver");
        BtnEditarDatosCliente.setBackground(new java.awt.Color(102, 102, 102)); // Color gris
        
        estadoEdicionCliente = EDICION_VIGENTE; // Estado: Edición Vigente
        System.out.println("*** ESTADO CAMBIADO A VIGENTE EN habilitarEdicionCliente() ***");
        
        System.out.println("ANTES del diálogo - Estado: " + estadoEdicionCliente);
        
        JOptionPane.showMessageDialog(this, 
            "✅ Modo de edición activado exitosamente.\n\n" +
            "📝 Modifique los datos necesarios y haga clic en 'Guardar Cambios'.\n" +
            "❌ Para cancelar, use el botón 'Cancelar Edición'.\n\n" +
            "🔄 Estado actual: EDICIÓN VIGENTE\n" +
            "📋 Los demás botones están bloqueados durante la edición.", 
            "Modo Edición Activo", JOptionPane.INFORMATION_MESSAGE);
        
        System.out.println("DESPUÉS del diálogo - Estado: " + estadoEdicionCliente);
        System.out.println("habilitarEdicionCliente() TERMINANDO - Estado final: " + estadoEdicionCliente);
    }
    
    private void cancelarEdicionCliente() {
        System.out.println("=== INICIO cancelarEdicionCliente() ===");
        System.out.println("Estado ANTES de cancelar: " + estadoEdicionCliente);
        System.out.println("STACK TRACE - ¿Quién llamó a cancelarEdicionCliente?:");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < Math.min(5, stackTrace.length); i++) {
            System.out.println("  [" + i + "] " + stackTrace[i].toString());
        }
        
        // Restaurar campos a no editables
        establecerCamposClienteNoEditables();
        
        // Restaurar el texto y color original del botón
        BtnEditarDatosCliente.setText("Editar datos Cliente");
        BtnEditarDatosCliente.setBackground(new java.awt.Color(255, 153, 51)); // Color original
        
        // Recargar los datos originales del cliente seleccionado
        cargarDatosClienteSeleccionado();
        
        estadoEdicionCliente = EDICION_CANCELADA; // Estado: Edición Cancelada
        System.out.println("*** ESTADO CAMBIADO A CANCELADA EN cancelarEdicionCliente() ***");
        System.out.println("Estado DESPUÉS de cambiar a CANCELADA: " + estadoEdicionCliente);
        
        System.out.println("ANTES de mostrar diálogo de cancelación...");
        JOptionPane.showMessageDialog(this, 
            "Edición cancelada. Los datos han sido restaurados.\n\n" +
            "Estado actual: EDICIÓN CANCELADA", 
            "Edición Cancelada", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("DESPUÉS de mostrar diálogo de cancelación...");
        System.out.println("=== FIN cancelarEdicionCliente() ===");
        
        System.out.println("Edición de cliente cancelada - Estado: " + estadoEdicionCliente);
    }

    private boolean validarClienteParaEdicion() {
        System.out.println("=== INICIO validarClienteParaEdicion() ===");
        
        // Validar campos vacíos
        if (txtNombreCliente.getText().trim().isEmpty() || 
            txtApellidosCliente.getText().trim().isEmpty() || 
            txtCedulaCliente.getText().trim().isEmpty() || 
            txtUsuarioCliente.getText().trim().isEmpty() ||
            txtContraseñaCliente.getText().trim().isEmpty() ||
            txtCorreoCliente.getText().trim().isEmpty() ||
            txtTelefonoCliente.getText().trim().isEmpty() ||
            txtDireccionCliente.getText().trim().isEmpty() ||
            cboGeneroCliente.getSelectedItem() == null) {
            
            System.out.println("Error: Campos vacíos detectados");
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios.", 
                "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        System.out.println("Campos obligatorios: OK");
        
        // Obtener ID del cliente actual para exclusión
        String idClienteActual = null;
        int filaSeleccionada = jTableRecepcionista1.getSelectedRow();
        if (filaSeleccionada >= 0) {
            idClienteActual = (String) jTableRecepcionista1.getValueAt(filaSeleccionada, 0); // Columna ID
            System.out.println("ID Cliente actual: " + idClienteActual);
        }
        
        // Validar formatos usando persistencia
        if (!administradorService.validarFormatosCliente(
                txtNombreCliente.getText().trim(),
                txtApellidosCliente.getText().trim(),
                txtCorreoCliente.getText().trim(),
                txtTelefonoCliente.getText().trim(),
                txtCedulaCliente.getText().trim())) {
            return false;
        }
        
        // Validar duplicados para edición (excluyendo el cliente actual)
        boolean validacion = administradorService.validarClienteParaEdicion(
            txtUsuarioCliente.getText().trim(),
            txtCorreoCliente.getText().trim(),
            txtTelefonoCliente.getText().trim(),
            txtCedulaCliente.getText().trim(),
            idClienteActual
        );
        
        System.out.println("=== FIN validarClienteParaEdicion() - Resultado: " + validacion + " ===");
        return validacion;
    }

    /**
     * Método auxiliar para obtener el nombre legible del estado de edición
     */
    private String obtenerNombreEstadoEdicion(int estado) {
        switch (estado) {
            case EDICION_VIGENTE:
                return "EDICIÓN VIGENTE (puede guardar cambios)";
            case EDICION_APROBADA:
                return "EDICIÓN APROBADA (cambios guardados)";
            case EDICION_CANCELADA:
                return "EDICIÓN CANCELADA (sin cambios activos)";
            default:
                return "ESTADO DESCONOCIDO";
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelAdministradorInicio = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelDecoración3 = new javax.swing.JPanel();
        panelDecoración4 = new javax.swing.JPanel();
        panelDecoración5 = new javax.swing.JPanel();
        lblRepuesto = new javax.swing.JLabel();
        txtCedulaAdministrador = new javax.swing.JTextField();
        lblMarcaRepuesto = new javax.swing.JLabel();
        lblDescripcionRepuesto = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        lblStock = new javax.swing.JLabel();
        lblPrecioRepuesto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNombreRepuesto = new javax.swing.JLabel();
        BtnEditarDatos = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        GuardarDatosEditados = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtIdTelefonoAdministrador = new javax.swing.JTextField();
        txtCorreoAdministrador = new javax.swing.JTextField();
        txtIdNombresDelAdministrador = new javax.swing.JTextField();
        txtApellidosAdministrador1 = new javax.swing.JTextField();
        BtnCancelarEdicion = new javax.swing.JButton();
        PanelAdministrarProveedor = new javax.swing.JPanel();
        panelDecoración9 = new javax.swing.JPanel();
        panelDecoración10 = new javax.swing.JPanel();
        panelDecoración11 = new javax.swing.JPanel();
        cboGeneroProveedor = new javax.swing.JComboBox<>();
        lblMarcaRepuesto2 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        lblNombreRepuesto2 = new javax.swing.JLabel();
        btnEliminarProveedor = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btnGuardarEdicionProveedor = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtAniosExperienciaProveedor = new javax.swing.JTextField();
        lblMarcaRepuesto5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProveedores = new javax.swing.JTable();
        lblNombreRepuesto5 = new javax.swing.JLabel();
        lblNombreRepuesto6 = new javax.swing.JLabel();
        lblNombreRepuesto7 = new javax.swing.JLabel();
        txtApellidosProveedor = new javax.swing.JTextField();
        txtUsuarioProveedor = new javax.swing.JTextField();
        txtContraseñaProveedor = new javax.swing.JTextField();
        BtnEditarDatosProveedor = new javax.swing.JButton();
        btnAgregarProveedor = new javax.swing.JButton();
        txtCorreoProveedor = new javax.swing.JTextField();
        lblNombreRepuesto8 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        lblNombreRepuesto9 = new javax.swing.JLabel();
        txtCedulaProveedor = new javax.swing.JTextField();
        lblNombreRepuesto10 = new javax.swing.JLabel();
        btnLimpiarDatos = new javax.swing.JButton();
        PanelAdministradorRecepcionista = new javax.swing.JPanel();
        panelDecoración18 = new javax.swing.JPanel();
        panelDecoración19 = new javax.swing.JPanel();
        panelDecoración20 = new javax.swing.JPanel();
        cboGeneroRecepcionista = new javax.swing.JComboBox<>();
        lblMarcaRepuesto3 = new javax.swing.JLabel();
        txtNombreRecepcionista = new javax.swing.JTextField();
        lblNombreRepuesto3 = new javax.swing.JLabel();
        btnEliminarRecepcionista = new javax.swing.JButton();
        btnGuardarEdicionRecepcionista = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtAniosExperienciaRecepcionista = new javax.swing.JTextField();
        lblMarcaRepuesto6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRecepcionista = new javax.swing.JTable();
        lblNombreRepuesto11 = new javax.swing.JLabel();
        lblNombreRepuesto12 = new javax.swing.JLabel();
        lblNombreRepuesto13 = new javax.swing.JLabel();
        txtApellidosRecepcionista = new javax.swing.JTextField();
        txtUsuarioRecepcionista = new javax.swing.JTextField();
        txtContraseñaRecepcionista = new javax.swing.JTextField();
        BtnEditarDatosRecepcionista = new javax.swing.JButton();
        btnAgregarRecepcionista = new javax.swing.JButton();
        txtCorreoRecepcionista = new javax.swing.JTextField();
        lblNombreRepuesto14 = new javax.swing.JLabel();
        txtTelefonoRecepcionista = new javax.swing.JTextField();
        lblNombreRepuesto15 = new javax.swing.JLabel();
        txtCedulaRecepcionista = new javax.swing.JTextField();
        lblNombreRepuesto16 = new javax.swing.JLabel();
        btnLimpiarDatosRecepcionista = new javax.swing.JButton();
        BtnCancelarEdicionRecepcionistas = new javax.swing.JButton();
        PanelAdministradorCliente = new javax.swing.JPanel();
        panelDecoración15 = new javax.swing.JPanel();
        panelDecoración16 = new javax.swing.JPanel();
        panelDecoración17 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        cboGeneroCliente = new javax.swing.JComboBox<>();
        lblMarcaRepuesto4 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        lblNombreRepuesto4 = new javax.swing.JLabel();
        btnEliminarCliente = new javax.swing.JButton();
        btnGuardarEdicionCliente = new javax.swing.JButton();
        txtDireccionCliente = new javax.swing.JTextField();
        lblMarcaRepuesto7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRecepcionista1 = new javax.swing.JTable();
        lblNombreRepuesto17 = new javax.swing.JLabel();
        lblNombreRepuesto19 = new javax.swing.JLabel();
        txtApellidosCliente = new javax.swing.JTextField();
        txtUsuarioCliente = new javax.swing.JTextField();
        txtContraseñaCliente = new javax.swing.JTextField();
        BtnEditarDatosCliente = new javax.swing.JButton();
        btnAgregarCliente = new javax.swing.JButton();
        txtCorreoCliente = new javax.swing.JTextField();
        lblNombreRepuesto20 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        lblNombreRepuesto21 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        lblNombreRepuesto22 = new javax.swing.JLabel();
        btnLimpiarDatosCliente = new javax.swing.JButton();
        lblNombreRepuesto23 = new javax.swing.JLabel();
        BtnCancelarEdicionCliente = new javax.swing.JButton();
        PanelNegro5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelNegro.setBackground(new java.awt.Color(0, 0, 0));

        panelDecoración.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoraciónLayout = new javax.swing.GroupLayout(panelDecoración);
        panelDecoración.setLayout(panelDecoraciónLayout);
        panelDecoraciónLayout.setHorizontalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoraciónLayout.setVerticalGroup(
            panelDecoraciónLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
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
                .addGroup(panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(panelBtnMenuPLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnMenuPLayout.setVerticalGroup(
            panelBtnMenuPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBtnMenuPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        labelAgregarPaciente.setText("CLIENTES");

        javax.swing.GroupLayout panelBtnAgregarLayout = new javax.swing.GroupLayout(panelBtnAgregar);
        panelBtnAgregar.setLayout(panelBtnAgregarLayout);
        panelBtnAgregarLayout.setHorizontalGroup(
            panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgregarPaciente)
                .addGroup(panelBtnAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconRepuesto))
                    .addGroup(panelBtnAgregarLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(labelAgregarPaciente)))
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
        labelEditarPaciente.setText("PROVEEDORES");

        javax.swing.GroupLayout panelBtnActualizarLayout = new javax.swing.GroupLayout(panelBtnActualizar);
        panelBtnActualizar.setLayout(panelBtnActualizarLayout);
        panelBtnActualizarLayout.setHorizontalGroup(
            panelBtnActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnActualizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconEditarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(87, 87, 87)
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
        labelEditarPaciente1.setText("RECEPCIONISTAS");

        javax.swing.GroupLayout panelBtnEliminarLayout = new javax.swing.GroupLayout(panelBtnEliminar);
        panelBtnEliminar.setLayout(panelBtnEliminarLayout);
        panelBtnEliminarLayout.setHorizontalGroup(
            panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAgendarCita)
                .addGroup(panelBtnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(panelBtnEliminarLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(labelEditarPaciente1)))
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

        panelBtnCerrarSesion.setBackground(new java.awt.Color(255, 51, 51));
        panelBtnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBtnCerrarSesionMouseClicked(evt);
            }
        });

        labelCerrarSesion.setBackground(new java.awt.Color(204, 0, 0));
        labelCerrarSesion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 23)); // NOI18N
        labelCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        labelCerrarSesion.setText("CERRAR SESSION");

        javax.swing.GroupLayout panelBtnCerrarSesionLayout = new javax.swing.GroupLayout(panelBtnCerrarSesion);
        panelBtnCerrarSesion.setLayout(panelBtnCerrarSesionLayout);
        panelBtnCerrarSesionLayout.setHorizontalGroup(
            panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconCerrarSesion)
                .addGroup(panelBtnCerrarSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(panelBtnCerrarSesionLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(labelCerrarSesion)))
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

        jLabel9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("¡SOMOS JAO-WORKSHOP!");

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
                .addGap(0, 35, Short.MAX_VALUE))
        );

        panelDecoración1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración1Layout = new javax.swing.GroupLayout(panelDecoración1);
        panelDecoración1.setLayout(panelDecoración1Layout);
        panelDecoración1Layout.setHorizontalGroup(
            panelDecoración1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración1Layout.setVerticalGroup(
            panelDecoración1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        panelDecoración2.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración2Layout = new javax.swing.GroupLayout(panelDecoración2);
        panelDecoración2.setLayout(panelDecoración2Layout);
        panelDecoración2Layout.setHorizontalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración2Layout.setVerticalGroup(
            panelDecoración2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SOMOSJAOWORKSHOP");

        jLabel14.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("JAO_WORKSHOP_COL");

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("¡ENCUÉNTRANOS!");

        javax.swing.GroupLayout panelNegroLayout = new javax.swing.GroupLayout(panelNegro);
        panelNegro.setLayout(panelNegroLayout);
        panelNegroLayout.setHorizontalGroup(
            panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBtnMenuP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNegroLayout.createSequentialGroup()
                        .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNegroLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(iconRecepcinista))
                            .addGroup(panelNegroLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel15))
                            .addGroup(panelNegroLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNegroLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelMenúOpciones)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNegroLayout.createSequentialGroup()
                .addGroup(panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDecoración2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDecoración1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDecoración, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNegroLayout.createSequentialGroup()
                .addComponent(panelBtnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelNegroLayout.setVerticalGroup(
            panelNegroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNegroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(panelDecoración, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDecoración1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDecoración2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconRecepcinista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(labelMenúOpciones)
                .addGap(18, 18, 18)
                .addComponent(panelBtnMenuP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(panelBtnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelGris.setBackground(new java.awt.Color(51, 51, 51));

        labelRecepcionisa1.setBackground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 75)); // NOI18N
        labelRecepcionisa1.setForeground(new java.awt.Color(255, 255, 255));
        labelRecepcionisa1.setText("-Administrador-");

        javax.swing.GroupLayout panelGrisLayout = new javax.swing.GroupLayout(panelGris);
        panelGris.setLayout(panelGrisLayout);
        panelGrisLayout.setHorizontalGroup(
            panelGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGrisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRecepcionisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGrisLayout.setVerticalGroup(
            panelGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelRecepcionisa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, Short.MAX_VALUE)
        );

        PanelAdministradorInicio.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdministradorInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("- ELIMINA!");
        PanelAdministradorInicio.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

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

        PanelAdministradorInicio.add(panelDecoración3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 690, 10));

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

        PanelAdministradorInicio.add(panelDecoración4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 10));

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

        PanelAdministradorInicio.add(panelDecoración5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 690, 10));

        lblRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblRepuesto.setText("1-Nombres:");
        PanelAdministradorInicio.add(lblRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        txtCedulaAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaAdministrador.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCedulaAdministrador.setForeground(new java.awt.Color(0, 0, 0));
        txtCedulaAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaAdministradorActionPerformed(evt);
            }
        });
        PanelAdministradorInicio.add(txtCedulaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 180, -1));

        lblMarcaRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblMarcaRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto.setText("3-Genero:");
        PanelAdministradorInicio.add(lblMarcaRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        lblDescripcionRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblDescripcionRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblDescripcionRepuesto.setText("4-Telefono: ");
        PanelAdministradorInicio.add(lblDescripcionRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 190, -1));

        txtGenero.setEditable(false);
        txtGenero.setBackground(new java.awt.Color(204, 204, 204));
        txtGenero.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtGenero.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorInicio.add(txtGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 180, -1));

        lblStock.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblStock.setForeground(new java.awt.Color(0, 0, 0));
        lblStock.setText("6-Correo Electronico:");
        PanelAdministradorInicio.add(lblStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        lblPrecioRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblPrecioRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblPrecioRepuesto.setText("5-Cedula:");
        PanelAdministradorInicio.add(lblPrecioRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

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

        PanelAdministradorInicio.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 10, 150));

        lblNombreRepuesto.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        lblNombreRepuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto.setText("2-Apellidos:");
        PanelAdministradorInicio.add(lblNombreRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, 30));

        BtnEditarDatos.setBackground(new java.awt.Color(255, 153, 51));
        BtnEditarDatos.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnEditarDatos.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditarDatos.setText("Editar datos");
        PanelAdministradorInicio.add(BtnEditarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 150, 30));
        PanelAdministradorInicio.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        GuardarDatosEditados.setBackground(new java.awt.Color(0, 153, 0));
        GuardarDatosEditados.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        GuardarDatosEditados.setForeground(new java.awt.Color(255, 255, 255));
        GuardarDatosEditados.setText("Guardar");
        PanelAdministradorInicio.add(GuardarDatosEditados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 150, 30));

        jLabel16.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Bienvenido Administrador !!!");
        PanelAdministradorInicio.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 440, 60));

        jLabel18.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("- AGREGA!");
        PanelAdministradorInicio.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, -1, -1));

        jLabel19.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("- GESTIONA!");
        PanelAdministradorInicio.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, -10, 160, 60));

        jLabel50.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("- ACTUALIZA DATOS!!");
        PanelAdministradorInicio.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        txtIdTelefonoAdministrador.setEditable(false);
        txtIdTelefonoAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtIdTelefonoAdministrador.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdTelefonoAdministrador.setForeground(new java.awt.Color(0, 0, 0));
        txtIdTelefonoAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTelefonoAdministradorActionPerformed(evt);
            }
        });
        PanelAdministradorInicio.add(txtIdTelefonoAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 180, -1));

        txtCorreoAdministrador.setEditable(false);
        txtCorreoAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoAdministrador.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtCorreoAdministrador.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorInicio.add(txtCorreoAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 270, 30));

        txtIdNombresDelAdministrador.setEditable(false);
        txtIdNombresDelAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtIdNombresDelAdministrador.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtIdNombresDelAdministrador.setForeground(new java.awt.Color(0, 0, 0));
        txtIdNombresDelAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNombresDelAdministradorActionPerformed(evt);
            }
        });
        PanelAdministradorInicio.add(txtIdNombresDelAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 170, -1));

        txtApellidosAdministrador1.setEditable(false);
        txtApellidosAdministrador1.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidosAdministrador1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        txtApellidosAdministrador1.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorInicio.add(txtApellidosAdministrador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 180, -1));

        BtnCancelarEdicion.setBackground(new java.awt.Color(204, 0, 0));
        BtnCancelarEdicion.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnCancelarEdicion.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelarEdicion.setText("Cancelar");
        PanelAdministradorInicio.add(BtnCancelarEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 150, 30));

        jTabbedPane1.addTab("PANEL INICIO", PanelAdministradorInicio);

        PanelAdministrarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdministrarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        PanelAdministrarProveedor.add(panelDecoración9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 690, 10));

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

        PanelAdministrarProveedor.add(panelDecoración10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 690, 10));

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

        PanelAdministrarProveedor.add(panelDecoración11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 690, 10));

        cboGeneroProveedor.setBackground(new java.awt.Color(204, 204, 204));
        cboGeneroProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        cboGeneroProveedor.setForeground(new java.awt.Color(0, 0, 0));
        cboGeneroProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Masculino", "Femenino", "Otro" }));
        PanelAdministrarProveedor.add(cboGeneroProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 210, -1));

        lblMarcaRepuesto2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto2.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto2.setText("-Genero Proveedor :");
        PanelAdministrarProveedor.add(lblMarcaRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 480, 180, 20));

        txtNombreProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtNombreProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 200, 30));

        lblNombreRepuesto2.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto2.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto2.setText("-Contraseña:");
        PanelAdministrarProveedor.add(lblNombreRepuesto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 100, 20));

        btnEliminarProveedor.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminarProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnEliminarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarProveedor.setText("Eliminar Proveedor");
        PanelAdministrarProveedor.add(btnEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 200, 30));
        PanelAdministrarProveedor.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnGuardarEdicionProveedor.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardarEdicionProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnGuardarEdicionProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEdicionProveedor.setText("Guardar Cambios");
        btnGuardarEdicionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionProveedorActionPerformed(evt);
            }
        });
        PanelAdministrarProveedor.add(btnGuardarEdicionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 200, 30));

        jLabel31.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("ADMINISTRA, AÑADE, EDITA Y ELIMINA LOS PROVEEDORES");
        PanelAdministrarProveedor.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 30));

        txtAniosExperienciaProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtAniosExperienciaProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtAniosExperienciaProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtAniosExperienciaProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 50, -1));

        lblMarcaRepuesto5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto5.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto5.setText("-Nombres:");
        PanelAdministrarProveedor.add(lblMarcaRepuesto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, 20));

        jTableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Cedula", "Celular", "CorreoElectronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProveedores);

        PanelAdministrarProveedor.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 460, 280));

        lblNombreRepuesto5.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto5.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto5.setText("-Apellidos:");
        PanelAdministrarProveedor.add(lblNombreRepuesto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        lblNombreRepuesto6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto6.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto6.setText("-Años Experiencia:");
        PanelAdministrarProveedor.add(lblNombreRepuesto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, -1, -1));

        lblNombreRepuesto7.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto7.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto7.setText("-Usuario:");
        PanelAdministrarProveedor.add(lblNombreRepuesto7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        txtApellidosProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidosProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtApellidosProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtApellidosProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 200, 30));

        txtUsuarioProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuarioProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtUsuarioProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtUsuarioProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 200, 30));

        txtContraseñaProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtContraseñaProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtContraseñaProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtContraseñaProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 200, 30));

        BtnEditarDatosProveedor.setBackground(new java.awt.Color(255, 153, 51));
        BtnEditarDatosProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnEditarDatosProveedor.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditarDatosProveedor.setText("Editar datos Proveedor");
        PanelAdministrarProveedor.add(BtnEditarDatosProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 230, 30));

        btnAgregarProveedor.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnAgregarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedor.setText("Agregar Proveedor");
        PanelAdministrarProveedor.add(btnAgregarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 200, 30));

        txtCorreoProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCorreoProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtCorreoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 200, 30));

        lblNombreRepuesto8.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto8.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto8.setText("-Correo:");
        PanelAdministrarProveedor.add(lblNombreRepuesto8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        txtTelefonoProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtTelefonoProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 200, 30));

        lblNombreRepuesto9.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto9.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto9.setText("-Telefono:");
        PanelAdministrarProveedor.add(lblNombreRepuesto9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        txtCedulaProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaProveedor.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCedulaProveedor.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministrarProveedor.add(txtCedulaProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 200, 30));

        lblNombreRepuesto10.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto10.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto10.setText("-Cedula:");
        PanelAdministrarProveedor.add(lblNombreRepuesto10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, -1, -1));

        btnLimpiarDatos.setBackground(new java.awt.Color(204, 0, 0));
        btnLimpiarDatos.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnLimpiarDatos.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarDatos.setText("Limpiar Datos");
        PanelAdministrarProveedor.add(btnLimpiarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 200, 30));

        jTabbedPane1.addTab("PROVEEDOR", PanelAdministrarProveedor);

        PanelAdministradorRecepcionista.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdministradorRecepcionista.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDecoración18.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración18Layout = new javax.swing.GroupLayout(panelDecoración18);
        panelDecoración18.setLayout(panelDecoración18Layout);
        panelDecoración18Layout.setHorizontalGroup(
            panelDecoración18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración18Layout.setVerticalGroup(
            panelDecoración18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorRecepcionista.add(panelDecoración18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 690, 10));

        panelDecoración19.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración19Layout = new javax.swing.GroupLayout(panelDecoración19);
        panelDecoración19.setLayout(panelDecoración19Layout);
        panelDecoración19Layout.setHorizontalGroup(
            panelDecoración19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración19Layout.setVerticalGroup(
            panelDecoración19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorRecepcionista.add(panelDecoración19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 690, 10));

        panelDecoración20.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración20Layout = new javax.swing.GroupLayout(panelDecoración20);
        panelDecoración20.setLayout(panelDecoración20Layout);
        panelDecoración20Layout.setHorizontalGroup(
            panelDecoración20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración20Layout.setVerticalGroup(
            panelDecoración20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorRecepcionista.add(panelDecoración20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 690, 10));

        cboGeneroRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        cboGeneroRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        cboGeneroRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        cboGeneroRecepcionista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Masculino", "Femenino", "Otro" }));
        cboGeneroRecepcionista.setToolTipText("");
        PanelAdministradorRecepcionista.add(cboGeneroRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 470, 210, -1));

        lblMarcaRepuesto3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto3.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto3.setText("-Genero Proveedor :");
        PanelAdministradorRecepcionista.add(lblMarcaRepuesto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, 180, 20));

        txtNombreRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtNombreRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtNombreRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 200, 30));

        lblNombreRepuesto3.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto3.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto3.setText("-Contraseña:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 100, 20));

        btnEliminarRecepcionista.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminarRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnEliminarRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarRecepcionista.setText("Eliminar Recepcionista");
        PanelAdministradorRecepcionista.add(btnEliminarRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, -1, 30));

        btnGuardarEdicionRecepcionista.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardarEdicionRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnGuardarEdicionRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEdicionRecepcionista.setText("Guardar Cambios");
        btnGuardarEdicionRecepcionista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionRecepcionistaActionPerformed(evt);
            }
        });
        PanelAdministradorRecepcionista.add(btnGuardarEdicionRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 170, 30));

        jLabel32.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("ADMINISTRA, AÑADE, EDITA Y ELIMINA LOS RECEPCIONISTAS");
        PanelAdministradorRecepcionista.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 30));

        txtAniosExperienciaRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtAniosExperienciaRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtAniosExperienciaRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtAniosExperienciaRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 50, -1));

        lblMarcaRepuesto6.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto6.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto6.setText("-Nombres:");
        PanelAdministradorRecepcionista.add(lblMarcaRepuesto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, 20));

        jTableRecepcionista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Cedula", "Celular", "CorreoElectronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableRecepcionista);

        PanelAdministradorRecepcionista.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 460, 280));

        lblNombreRepuesto11.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto11.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto11.setText("-Apellidos:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        lblNombreRepuesto12.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto12.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto12.setText("-Años Experiencia:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, -1, -1));

        lblNombreRepuesto13.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto13.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto13.setText("-Usuario:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        txtApellidosRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidosRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtApellidosRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtApellidosRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 200, 30));

        txtUsuarioRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuarioRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtUsuarioRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtUsuarioRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 200, 30));

        txtContraseñaRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtContraseñaRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtContraseñaRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtContraseñaRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 200, 30));

        BtnEditarDatosRecepcionista.setBackground(new java.awt.Color(255, 153, 51));
        BtnEditarDatosRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnEditarDatosRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditarDatosRecepcionista.setText("Editar datos Recepcionista");
        BtnEditarDatosRecepcionista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarDatosRecepcionistaActionPerformed(evt);
            }
        });
        PanelAdministradorRecepcionista.add(BtnEditarDatosRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 270, 30));

        btnAgregarRecepcionista.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnAgregarRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarRecepcionista.setText("Agregar Recepcionista");
        PanelAdministradorRecepcionista.add(btnAgregarRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 220, 30));

        txtCorreoRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCorreoRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtCorreoRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 200, 30));

        lblNombreRepuesto14.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto14.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto14.setText("-Correo:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        txtTelefonoRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtTelefonoRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtTelefonoRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 200, 30));

        lblNombreRepuesto15.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto15.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto15.setText("-Telefono:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, -1, -1));

        txtCedulaRecepcionista.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCedulaRecepcionista.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorRecepcionista.add(txtCedulaRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 210, 30));

        lblNombreRepuesto16.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto16.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto16.setText("-Cedula:");
        PanelAdministradorRecepcionista.add(lblNombreRepuesto16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, -1, -1));

        btnLimpiarDatosRecepcionista.setBackground(new java.awt.Color(204, 0, 0));
        btnLimpiarDatosRecepcionista.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnLimpiarDatosRecepcionista.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarDatosRecepcionista.setText("Limpiar Datos");
        PanelAdministradorRecepcionista.add(btnLimpiarDatosRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 170, 30));

        BtnCancelarEdicionRecepcionistas.setBackground(new java.awt.Color(255, 153, 51));
        BtnCancelarEdicionRecepcionistas.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnCancelarEdicionRecepcionistas.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelarEdicionRecepcionistas.setText("Cancelar");
        BtnCancelarEdicionRecepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarEdicionRecepcionistasActionPerformed(evt);
            }
        });
        PanelAdministradorRecepcionista.add(BtnCancelarEdicionRecepcionistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 130, 30));

        jTabbedPane1.addTab("RECEPCIONISTA", PanelAdministradorRecepcionista);

        PanelAdministradorCliente.setBackground(new java.awt.Color(255, 255, 255));
        PanelAdministradorCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelDecoración15.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout panelDecoración15Layout = new javax.swing.GroupLayout(panelDecoración15);
        panelDecoración15.setLayout(panelDecoración15Layout);
        panelDecoración15Layout.setHorizontalGroup(
            panelDecoración15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración15Layout.setVerticalGroup(
            panelDecoración15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorCliente.add(panelDecoración15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 690, 10));

        panelDecoración16.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelDecoración16Layout = new javax.swing.GroupLayout(panelDecoración16);
        panelDecoración16.setLayout(panelDecoración16Layout);
        panelDecoración16Layout.setHorizontalGroup(
            panelDecoración16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración16Layout.setVerticalGroup(
            panelDecoración16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorCliente.add(panelDecoración16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 690, 10));

        panelDecoración17.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout panelDecoración17Layout = new javax.swing.GroupLayout(panelDecoración17);
        panelDecoración17.setLayout(panelDecoración17Layout);
        panelDecoración17Layout.setHorizontalGroup(
            panelDecoración17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDecoración17Layout.setVerticalGroup(
            panelDecoración17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        PanelAdministradorCliente.add(panelDecoración17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 690, 10));
        PanelAdministradorCliente.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel33.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("ADMINISTRA, AÑADE, EDITA Y ELIMINA LOS CLIENTES");
        PanelAdministradorCliente.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 30));

        cboGeneroCliente.setBackground(new java.awt.Color(204, 204, 204));
        cboGeneroCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        cboGeneroCliente.setForeground(new java.awt.Color(0, 0, 0));
        cboGeneroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin seleccionar", "Masculino", "Femenino", "Otro" }));
        PanelAdministradorCliente.add(cboGeneroCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 210, -1));

        lblMarcaRepuesto4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto4.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto4.setText("-Genero Proveedor :");
        PanelAdministradorCliente.add(lblMarcaRepuesto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, 180, 20));

        txtNombreCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtNombreCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 200, 30));

        lblNombreRepuesto4.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto4.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto4.setText("-Contraseña:");
        PanelAdministradorCliente.add(lblNombreRepuesto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 100, 20));

        btnEliminarCliente.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminarCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setText("Eliminar Cliente");
        PanelAdministradorCliente.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, 30));

        btnGuardarEdicionCliente.setBackground(new java.awt.Color(0, 153, 0));
        btnGuardarEdicionCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnGuardarEdicionCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarEdicionCliente.setText("Guardar Cambios");
        btnGuardarEdicionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEdicionClienteActionPerformed(evt);
            }
        });
        PanelAdministradorCliente.add(btnGuardarEdicionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 170, 30));

        txtDireccionCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccionCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtDireccionCliente.setForeground(new java.awt.Color(0, 0, 0));
        txtDireccionCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        PanelAdministradorCliente.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 220, 70));

        lblMarcaRepuesto7.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblMarcaRepuesto7.setForeground(new java.awt.Color(0, 0, 0));
        lblMarcaRepuesto7.setText("-Nombres:");
        PanelAdministradorCliente.add(lblMarcaRepuesto7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, 20));

        jTableRecepcionista1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Cedula", "Celular", "CorreoElectronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableRecepcionista1);

        PanelAdministradorCliente.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 460, 290));

        lblNombreRepuesto17.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto17.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto17.setText("-Apellidos:");
        PanelAdministradorCliente.add(lblNombreRepuesto17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        lblNombreRepuesto19.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto19.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto19.setText("-Usuario:");
        PanelAdministradorCliente.add(lblNombreRepuesto19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, -1, -1));

        txtApellidosCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidosCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtApellidosCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtApellidosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 200, 30));

        txtUsuarioCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuarioCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtUsuarioCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtUsuarioCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 200, 30));

        txtContraseñaCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtContraseñaCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtContraseñaCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtContraseñaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 200, 30));

        BtnEditarDatosCliente.setBackground(new java.awt.Color(255, 153, 51));
        BtnEditarDatosCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnEditarDatosCliente.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditarDatosCliente.setText("Editar datos Cliente");
        BtnEditarDatosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarDatosClienteActionPerformed(evt);
            }
        });
        PanelAdministradorCliente.add(BtnEditarDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 220, -1));

        btnAgregarCliente.setBackground(new java.awt.Color(0, 153, 0));
        btnAgregarCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCliente.setText("Agregar Cliente");
        PanelAdministradorCliente.add(btnAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 170, 30));

        txtCorreoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCorreoCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtCorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 200, 30));

        lblNombreRepuesto20.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto20.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto20.setText("-Correo:");
        PanelAdministradorCliente.add(lblNombreRepuesto20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        txtTelefonoCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefonoCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtTelefonoCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 200, 30));

        lblNombreRepuesto21.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto21.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto21.setText("-Telefono:");
        PanelAdministradorCliente.add(lblNombreRepuesto21, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));

        txtCedulaCliente.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        txtCedulaCliente.setForeground(new java.awt.Color(0, 0, 0));
        PanelAdministradorCliente.add(txtCedulaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 200, 30));

        lblNombreRepuesto22.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto22.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto22.setText("-Direccion:");
        PanelAdministradorCliente.add(lblNombreRepuesto22, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, -1, 20));

        btnLimpiarDatosCliente.setBackground(new java.awt.Color(204, 0, 0));
        btnLimpiarDatosCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        btnLimpiarDatosCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarDatosCliente.setText("Limpiar Datos");
        PanelAdministradorCliente.add(btnLimpiarDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 170, 30));

        lblNombreRepuesto23.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        lblNombreRepuesto23.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreRepuesto23.setText("-Cedula:");
        PanelAdministradorCliente.add(lblNombreRepuesto23, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, -1, -1));

        BtnCancelarEdicionCliente.setBackground(new java.awt.Color(204, 0, 0));
        BtnCancelarEdicionCliente.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 0, 14)); // NOI18N
        BtnCancelarEdicionCliente.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelarEdicionCliente.setText("Cancelar Edicion");
        BtnCancelarEdicionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEdicionClienteActionPerformed(evt);
            }
        });
        PanelAdministradorCliente.add(BtnCancelarEdicionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 220, 30));

        jTabbedPane1.addTab("CLIENTE", PanelAdministradorCliente);

        PanelNegro5.setBackground(new java.awt.Color(15, 15, 15));

        javax.swing.GroupLayout PanelNegro5Layout = new javax.swing.GroupLayout(PanelNegro5);
        PanelNegro5.setLayout(PanelNegro5Layout);
        PanelNegro5Layout.setHorizontalGroup(
            PanelNegro5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
        );
        PanelNegro5Layout.setVerticalGroup(
            PanelNegro5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(panelNegro, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelNegro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 357, Short.MAX_VALUE)
                    .addComponent(panelGris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelNegro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelNegro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 20, Short.MAX_VALUE)
                    .addComponent(panelGris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 695, Short.MAX_VALUE)))
        );

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
        // ⭐ Usar LoginService para cerrar sesión (más limpio y organizado)
        loginService.cerrarSesionYVolverLogin(this);
    }//GEN-LAST:event_panelBtnCerrarSesionMouseClicked

    private void panelBtnEliminar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBtnEliminar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_panelBtnEliminar1MouseClicked

    private void txtIdNombresDelAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNombresDelAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNombresDelAdministradorActionPerformed

    private void txtIdTelefonoAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTelefonoAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTelefonoAdministradorActionPerformed

    private void txtCedulaAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaAdministradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaAdministradorActionPerformed

    private void btnGuardarEdicionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionProveedorActionPerformed
        guardarEdicionProveedor();
    }//GEN-LAST:event_btnGuardarEdicionProveedorActionPerformed
    
    private void btnGuardarEdicionRecepcionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionRecepcionistaActionPerformed
        guardarEdicionRecepcionista();
    }//GEN-LAST:event_btnGuardarEdicionRecepcionistaActionPerformed
    
    private void btnGuardarEdicionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEdicionClienteActionPerformed
        // Verificar que esté en modo edición antes de permitir guardar
        if (estadoEdicionCliente != EDICION_VIGENTE) {
            JOptionPane.showMessageDialog(this, 
                "⚠️ Este botón solo funciona cuando está editando un cliente.\n\n" +
                "📋 Pasos para editar un cliente:\n" +
                "1. Seleccione un cliente de la tabla\n" +
                "2. Haga clic en 'Editar datos Cliente'\n" +
                "3. Modifique los datos necesarios\n" +
                "4. Luego use este botón para 'Guardar Cambios'\n\n" +
                "🔄 Estado actual: " + obtenerNombreEstadoEdicion(estadoEdicionCliente), 
                "Botón No Disponible", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Si está en modo edición, proceder normalmente
        guardarEdicionCliente();
    }//GEN-LAST:event_btnGuardarEdicionClienteActionPerformed

    private void btnCancelarEdicionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEdicionClienteActionPerformed
        // Confirmar cancelación con el usuario
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea cancelar la edición?\n" +
            "Se perderán todos los cambios realizados.",
            "Confirmar Cancelación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            cancelarEdicionCliente();
        }
    }//GEN-LAST:event_btnCancelarEdicionClienteActionPerformed

    private void BtnEditarDatosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarDatosClienteActionPerformed
        // Este método fue generado por NetBeans pero ahora usamos el ActionListener configurado manualmente
        // en configurarEventosClientes() para mayor control
        System.out.println("⚠️ Método NetBeans BtnEditarDatosClienteActionPerformed llamado - redirigiendo...");
        
        if (estadoEdicionCliente == EDICION_VIGENTE) {
            cancelarEdicionCliente();
        } else {
            habilitarEdicionCliente();
        }
    }//GEN-LAST:event_BtnEditarDatosClienteActionPerformed
    
    private void BtnCancelarEdicionRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarEdicionRecepcionistasActionPerformed
        // Confirmar cancelación con el usuario
        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea cancelar la edición?\n" +
            "Se perderán todos los cambios realizados.",
            "Confirmar Cancelación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            cancelarEdicionRecepcionista();
        }
    }//GEN-LAST:event_BtnCancelarEdicionRecepcionistasActionPerformed

    private void BtnCancelarEdicionRecepcionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarDatosRecepcionista1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditarDatosRecepcionista1ActionPerformed

    private void BtnEditarDatosRecepcionistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarDatosRecepcionistaActionPerformed
        System.out.println("=== BtnEditarDatosRecepcionistaActionPerformed INICIO ===");
        System.out.println("Estado al inicio del método: " + estadoEdicionRecepcionista);
        
        // CAPTURAR EL ESTADO INICIAL ANTES DE CUALQUIER CAMBIO
        int estadoInicial = estadoEdicionRecepcionista;
        System.out.println("Estado inicial capturado: " + estadoInicial);
        
        if (estadoInicial == EDICION_VIGENTE) {
            // Si está en modo edición, el botón actúa como "Volver"
            System.out.println("Modo detectado: CANCELAR (estadoInicial == EDICION_VIGENTE)");
            cancelarEdicionRecepcionista();
        } else {
            // Si no está en modo edición, habilita la edición
            System.out.println("Modo detectado: EDITAR (estadoInicial != EDICION_VIGENTE)");
            habilitarEdicionRecepcionista();
        }
        
        System.out.println("BtnEditarDatosRecepcionistaActionPerformed ejecutado - Estado final: " + estadoEdicionRecepcionista);
    }//GEN-LAST:event_BtnEditarDatosRecepcionistaActionPerformed

    // ========================================
    // MÉTODOS PARA EDICIÓN DE ADMINISTRADOR
    // ========================================
    
    private void BtnEditarDatosActionPerformed(java.awt.event.ActionEvent evt) {
        // Habilitar campos para edición
        txtIdNombresDelAdministrador.setEditable(true);
        txtApellidosAdministrador1.setEditable(true);
        txtIdTelefonoAdministrador.setEditable(true);  
        txtCorreoAdministrador.setEditable(true);
        txtCedulaAdministrador.setEditable(true);
        txtGenero.setEditable(true);
        
        // Cambiar colores para indicar modo edición
        txtIdNombresDelAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosAdministrador1.setBackground(new java.awt.Color(255, 255, 255));
        txtIdTelefonoAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        txtCedulaAdministrador.setBackground(new java.awt.Color(255, 255, 255));
        txtGenero.setBackground(new java.awt.Color(255, 255, 255));
        
        // Mostrar botones de guardado y cancelación
        GuardarDatosEditados.setVisible(true);
        BtnCancelarEdicion.setVisible(true);
        BtnEditarDatos.setVisible(false);
        
        JOptionPane.showMessageDialog(this, 
            "Modo edición activado.\nModifique los datos y presione 'Guardar' para confirmar.", 
            "Edición Habilitada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void GuardarDatosEditadosActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Validar que tenemos un ID de administrador
            if (idAdministradorActual == null || idAdministradorActual.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Error: No hay un administrador identificado.", 
                    "Error de Identificación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Buscar el administrador actual por ID
            Model.Administrador adminActual = administradorService.buscarAdministradorPorIdEnLista(idAdministradorActual);
            if (adminActual == null) {
                JOptionPane.showMessageDialog(this, 
                    "Error: No se pudo encontrar el administrador con ID: " + idAdministradorActual, 
                    "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar campos obligatorios
            String nombres = txtIdNombresDelAdministrador.getText().trim();
            String apellidos = txtApellidosAdministrador1.getText().trim();
            String telefono = txtIdTelefonoAdministrador.getText().trim();
            String correo = txtCorreoAdministrador.getText().trim();
            String cedula = txtCedulaAdministrador.getText().trim();
            String genero = txtGenero.getText().trim();
            
            if (nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || 
                correo.isEmpty() || cedula.isEmpty() || genero.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Todos los campos son obligatorios.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Parsear nombres y apellidos
            String[] partesNombres = nombres.split(" ", 2);
            String primerNombre = partesNombres[0];
            String segundoNombre = partesNombres.length > 1 ? partesNombres[1] : "";
            
            String[] partesApellidos = apellidos.split(" ", 2);
            String primerApellido = partesApellidos[0];
            String segundoApellido = partesApellidos.length > 1 ? partesApellidos[1] : "";
            
            // Crear administrador actualizado
            Model.Administrador adminActualizado = new Model.Administrador(
                adminActual.getIdAdministrador(),
                primerNombre,
                segundoNombre,
                primerApellido,
                segundoApellido,
                genero,
                cedula,
                telefono,
                correo,
                adminActual.getUsuario(),
                adminActual.getContraseña(),
                adminActual.getRol()
            );
            
            // Actualizar en la lista
            if (administradorService.actualizarAdministradorEnLista(adminActualizado)) {
                JOptionPane.showMessageDialog(this, 
                    "Datos actualizados correctamente.", 
                    "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                
                // Salir del modo edición
                cancelarEdicion();
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar los datos.", 
                    "Error de Actualización", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error inesperado: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void BtnCancelarEdicionActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de cancelar la edición?\nSe perderán los cambios no guardados.", 
            "Confirmar Cancelación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            cancelarEdicion();
            precargarDatosAdministradorPorId(idAdministradorActual); // Recargar datos originales usando ID
        }
    }
    
    private void cancelarEdicion() {
        // Deshabilitar campos de edición
        txtIdNombresDelAdministrador.setEditable(false);
        txtApellidosAdministrador1.setEditable(false);
        txtIdTelefonoAdministrador.setEditable(false);
        txtCorreoAdministrador.setEditable(false);
        txtCedulaAdministrador.setEditable(false);
        txtGenero.setEditable(false);
        
        // Restaurar colores originales
        txtIdNombresDelAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidosAdministrador1.setBackground(new java.awt.Color(204, 204, 204));
        txtIdTelefonoAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreoAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtCedulaAdministrador.setBackground(new java.awt.Color(204, 204, 204));
        txtGenero.setBackground(new java.awt.Color(204, 204, 204));
        
        // Restaurar visibilidad de botones
        GuardarDatosEditados.setVisible(false);
        BtnCancelarEdicion.setVisible(false);
        BtnEditarDatos.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelarEdicion;
    private javax.swing.JButton BtnCancelarEdicionCliente;
    private javax.swing.JButton BtnCancelarEdicionRecepcionistas;
    private javax.swing.JButton BtnEditarDatos;
    private javax.swing.JButton BtnEditarDatosCliente;
    private javax.swing.JButton BtnEditarDatosProveedor;
    private javax.swing.JButton BtnEditarDatosRecepcionista;
    private javax.swing.JButton GuardarDatosEditados;
    private javax.swing.JPanel PanelAdministradorCliente;
    private javax.swing.JPanel PanelAdministradorInicio;
    private javax.swing.JPanel PanelAdministradorRecepcionista;
    private javax.swing.JPanel PanelAdministrarProveedor;
    private javax.swing.JPanel PanelNegro2;
    private javax.swing.JPanel PanelNegro3;
    private javax.swing.JPanel PanelNegro4;
    private javax.swing.JPanel PanelNegro5;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarProveedor;
    private javax.swing.JButton btnAgregarRecepcionista;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarRecepcionista;
    private javax.swing.JButton btnGuardarEdicionCliente;
    private javax.swing.JButton btnGuardarEdicionProveedor;
    private javax.swing.JButton btnGuardarEdicionRecepcionista;
    private javax.swing.JButton btnLimpiarDatos;
    private javax.swing.JButton btnLimpiarDatosCliente;
    private javax.swing.JButton btnLimpiarDatosRecepcionista;
    private javax.swing.JComboBox<String> cboGeneroCliente;
    private javax.swing.JComboBox<String> cboGeneroProveedor;
    private javax.swing.JComboBox<String> cboGeneroRecepcionista;
    private javax.swing.JLabel iconAgendarCita;
    private javax.swing.JLabel iconAgregarPaciente;
    private javax.swing.JLabel iconCerrarSesion;
    private javax.swing.JLabel iconEditarPaciente;
    private javax.swing.JLabel iconInicio;
    private javax.swing.JLabel iconRecepcinista;
    private javax.swing.JLabel iconRepuesto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableProveedores;
    private javax.swing.JTable jTableRecepcionista;
    private javax.swing.JTable jTableRecepcionista1;
    private javax.swing.JLabel labelAgregarPaciente;
    private javax.swing.JLabel labelCerrarSesion;
    private javax.swing.JLabel labelEditarPaciente;
    private javax.swing.JLabel labelEditarPaciente1;
    private javax.swing.JLabel labelMenúOpciones;
    private javax.swing.JLabel labelRecepcionisa1;
    private javax.swing.JLabel lblDescripcionRepuesto;
    private javax.swing.JLabel lblMarcaRepuesto;
    private javax.swing.JLabel lblMarcaRepuesto2;
    private javax.swing.JLabel lblMarcaRepuesto3;
    private javax.swing.JLabel lblMarcaRepuesto4;
    private javax.swing.JLabel lblMarcaRepuesto5;
    private javax.swing.JLabel lblMarcaRepuesto6;
    private javax.swing.JLabel lblMarcaRepuesto7;
    private javax.swing.JLabel lblNombreRepuesto;
    private javax.swing.JLabel lblNombreRepuesto10;
    private javax.swing.JLabel lblNombreRepuesto11;
    private javax.swing.JLabel lblNombreRepuesto12;
    private javax.swing.JLabel lblNombreRepuesto13;
    private javax.swing.JLabel lblNombreRepuesto14;
    private javax.swing.JLabel lblNombreRepuesto15;
    private javax.swing.JLabel lblNombreRepuesto16;
    private javax.swing.JLabel lblNombreRepuesto17;
    private javax.swing.JLabel lblNombreRepuesto19;
    private javax.swing.JLabel lblNombreRepuesto2;
    private javax.swing.JLabel lblNombreRepuesto20;
    private javax.swing.JLabel lblNombreRepuesto21;
    private javax.swing.JLabel lblNombreRepuesto22;
    private javax.swing.JLabel lblNombreRepuesto23;
    private javax.swing.JLabel lblNombreRepuesto3;
    private javax.swing.JLabel lblNombreRepuesto4;
    private javax.swing.JLabel lblNombreRepuesto5;
    private javax.swing.JLabel lblNombreRepuesto6;
    private javax.swing.JLabel lblNombreRepuesto7;
    private javax.swing.JLabel lblNombreRepuesto8;
    private javax.swing.JLabel lblNombreRepuesto9;
    private javax.swing.JLabel lblPrecioRepuesto;
    private javax.swing.JLabel lblRepuesto;
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
    private javax.swing.JPanel panelDecoración15;
    private javax.swing.JPanel panelDecoración16;
    private javax.swing.JPanel panelDecoración17;
    private javax.swing.JPanel panelDecoración18;
    private javax.swing.JPanel panelDecoración19;
    private javax.swing.JPanel panelDecoración2;
    private javax.swing.JPanel panelDecoración20;
    private javax.swing.JPanel panelDecoración3;
    private javax.swing.JPanel panelDecoración4;
    private javax.swing.JPanel panelDecoración5;
    private javax.swing.JPanel panelDecoración9;
    private javax.swing.JPanel panelGris;
    private javax.swing.JPanel panelNegro;
    private javax.swing.JTextField txtAniosExperienciaProveedor;
    private javax.swing.JTextField txtAniosExperienciaRecepcionista;
    private javax.swing.JTextField txtApellidosAdministrador1;
    private javax.swing.JTextField txtApellidosCliente;
    private javax.swing.JTextField txtApellidosProveedor;
    private javax.swing.JTextField txtApellidosRecepcionista;
    private javax.swing.JTextField txtCedulaAdministrador;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaProveedor;
    private javax.swing.JTextField txtCedulaRecepcionista;
    private javax.swing.JTextField txtContraseñaCliente;
    private javax.swing.JTextField txtContraseñaProveedor;
    private javax.swing.JTextField txtContraseñaRecepcionista;
    private javax.swing.JTextField txtCorreoAdministrador;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoProveedor;
    private javax.swing.JTextField txtCorreoRecepcionista;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtIdNombresDelAdministrador;
    private javax.swing.JTextField txtIdTelefonoAdministrador;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreRecepcionista;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTelefonoRecepcionista;
    private javax.swing.JTextField txtUsuarioCliente;
    private javax.swing.JTextField txtUsuarioProveedor;
    private javax.swing.JTextField txtUsuarioRecepcionista;
    // End of variables declaration//GEN-END:variables
}
