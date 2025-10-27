# 🔑 GUÍA DE IMPLEMENTACIÓN - SISTEMA DE ID PARA FRAMES

## 📋 ¿Qué es esto?
Esta guía explica cómo implementar el sistema de **ID directo** para que cada frame (Cliente, Recepcionista, Proveedor) reciba y use el ID del usuario logueado, sin usar `SesionUsuario` ni `instanceof`.

---

## 🎯 PASOS PARA CADA COMPAÑERO

### 1️⃣ **MODIFICAR EL FRAME (Cliente.java, Recepcionista.java, Proveedor.java)**

#### A) Agregar campo para almacenar el ID:
```java
public class Cliente extends javax.swing.JFrame {
    // ⭐ AGREGAR ESTA LÍNEA - Campo para almacenar el ID del usuario actual
    private String idClienteActual;
    
    // ... resto del código
}
```

#### B) Crear constructor que recibe ID:
```java
/**
 * Constructor que recibe el ID del cliente para precargar datos
 * @param idCliente ID del cliente logueado
 */
public Cliente(String idCliente) {
    initComponents();
    inicializarServicios();
    
    // ⭐ GUARDAR EL ID RECIBIDO
    this.idClienteActual = idCliente;
    
    // ⭐ PRECARGAR DATOS USANDO EL ID
    precargarDatosClientePorId(idCliente);
    
    configurarEventos();
    setLocationRelativeTo(null);
}
```

#### C) Crear método para precargar datos por ID:
```java
/**
 * Precarga los datos del cliente usando su ID
 * @param idCliente ID del cliente a buscar
 */
private void precargarDatosClientePorId(String idCliente) {
    System.out.println("Precargando datos para cliente con ID: " + idCliente);
    
    try {
        // ⭐ BUSCAR EL CLIENTE POR ID USANDO ADMINISTRADOR SERVICE
        AdministradorService adminService = new AdministradorService(new LoginService());
        Cliente cliente = adminService.buscarClientePorId(idCliente);
        
        if (cliente != null) {
            // ⭐ LLENAR LOS CAMPOS DEL FORMULARIO
            txtNombresCliente.setText(cliente.getPrimerNombre() + " " + cliente.getSegundoNombre());
            txtApellidosCliente.setText(cliente.getPrimerApellido() + " " + cliente.getSegundoApellido());
            txtTelefonoCliente.setText(cliente.getTelefono());
            txtCorreoCliente.setText(cliente.getCorreo());
            txtCedulaCliente.setText(cliente.getCedula());
            txtGeneroCliente.setText(cliente.getGenero());
            txtDireccionCliente.setText(cliente.getDireccion());
            
            System.out.println("Datos cargados exitosamente para: " + cliente.getPrimerNombre());
        } else {
            System.out.println("No se encontró cliente con ID: " + idCliente);
            JOptionPane.showMessageDialog(this, 
                "No se pudieron cargar los datos del cliente", 
                "Error de Carga", JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception e) {
        System.out.println("Error al precargar datos del cliente: " + e.getMessage());
        e.printStackTrace();
    }
}
```

#### D) Actualizar métodos de guardar/actualizar datos:
```java
private void GuardarDatosEditadosActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        // ⭐ VALIDAR QUE TENEMOS UN ID
        if (idClienteActual == null || idClienteActual.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Error: No hay un cliente identificado.", 
                "Error de Identificación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // ⭐ BUSCAR EL CLIENTE ACTUAL POR ID
        AdministradorService adminService = new AdministradorService(new LoginService());
        Cliente clienteActual = adminService.buscarClientePorId(idClienteActual);
        
        if (clienteActual == null) {
            JOptionPane.showMessageDialog(this, 
                "Error: No se pudo encontrar el cliente con ID: " + idClienteActual, 
                "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // ⭐ CONTINUAR CON LA LÓGICA DE ACTUALIZACIÓN...
        // Obtener datos de los campos, validar, crear objeto actualizado, etc.
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error inesperado: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
```

---

### 2️⃣ **MODIFICAR Login.java PARA CADA TIPO DE USUARIO**

En el método `redirigirSegunRol()`, agregar las líneas para cada tipo:

```java
private void redirigirSegunRol(String rol, String usuario) {
    System.out.println("Redirigiendo usuario con rol: " + rol);
    
    switch (rol.toLowerCase()) {
        case "administrador":
            // ⭐ YA IMPLEMENTADO
            String idAdministrador = administradorService.obtenerIdAdministradorPorUsuario(usuario);
            if (idAdministrador != null) {
                System.out.println("ID del administrador obtenido: " + idAdministrador);
                new Administrador(idAdministrador).setVisible(true);
            } else {
                System.out.println("Error: No se pudo obtener el ID del administrador");
                JOptionPane.showMessageDialog(this, "Error al obtener datos del administrador");
            }
            break;
            
        case "cliente":
            // ⭐ AGREGAR ESTO PARA CLIENTE
            String idCliente = obtenerIdClientePorUsuario(usuario);
            if (idCliente != null) {
                System.out.println("ID del cliente obtenido: " + idCliente);
                new Cliente(idCliente).setVisible(true);
            } else {
                System.out.println("Error: No se pudo obtener el ID del cliente");
                JOptionPane.showMessageDialog(this, "Error al obtener datos del cliente");
            }
            break;
            
        case "recepcionista":
            // ⭐ AGREGAR ESTO PARA RECEPCIONISTA
            String idRecepcionista = obtenerIdRecepcionistaPorUsuario(usuario);
            if (idRecepcionista != null) {
                System.out.println("ID del recepcionista obtenido: " + idRecepcionista);
                new Recepcionista(idRecepcionista).setVisible(true);
            } else {
                System.out.println("Error: No se pudo obtener el ID del recepcionista");
                JOptionPane.showMessageDialog(this, "Error al obtener datos del recepcionista");
            }
            break;
            
        case "proveedor":
            // ⭐ AGREGAR ESTO PARA PROVEEDOR
            String idProveedor = obtenerIdProveedorPorUsuario(usuario);
            if (idProveedor != null) {
                System.out.println("ID del proveedor obtenido: " + idProveedor);
                new Proveedor(idProveedor).setVisible(true);
            } else {
                System.out.println("Error: No se pudo obtener el ID del proveedor");
                JOptionPane.showMessageDialog(this, "Error al obtener datos del proveedor");
            }
            break;
    }
    
    this.dispose();
}
```

#### Y agregar estos métodos en Login.java:
```java
// ⭐ MÉTODO PARA OBTENER ID DE CLIENTE
private String obtenerIdClientePorUsuario(String usuario) {
    try {
        Lista listaClientes = loginService.getListaClientes();
        if (listaClientes == null) return null;
        
        listaDoble.Nodo actual = listaClientes.getPrimero();
        while (actual != null) {
            Cliente cliente = (Cliente) actual.getDato();
            if (cliente.getUsuario().equals(usuario)) {
                return cliente.getIdCliente();
            }
            actual = actual.getSiguiente();
        }
        return null;
    } catch (Exception e) {
        System.out.println("Error al obtener ID de cliente: " + e.getMessage());
        return null;
    }
}

// ⭐ MÉTODO PARA OBTENER ID DE RECEPCIONISTA
private String obtenerIdRecepcionistaPorUsuario(String usuario) {
    try {
        Lista listaRecepcionistas = loginService.getListaRecepcionistas();
        if (listaRecepcionistas == null) return null;
        
        listaDoble.Nodo actual = listaRecepcionistas.getPrimero();
        while (actual != null) {
            Recepcionista recepcionista = (Recepcionista) actual.getDato();
            if (recepcionista.getUsuario().equals(usuario)) {
                return recepcionista.getIdRecepcionista();
            }
            actual = actual.getSiguiente();
        }
        return null;
    } catch (Exception e) {
        System.out.println("Error al obtener ID de recepcionista: " + e.getMessage());
        return null;
    }
}

// ⭐ MÉTODO PARA OBTENER ID DE PROVEEDOR
private String obtenerIdProveedorPorUsuario(String usuario) {
    try {
        Lista listaProveedores = loginService.getListaProveedores();
        if (listaProveedores == null) return null;
        
        listaDoble.Nodo actual = listaProveedores.getPrimero();
        while (actual != null) {
            Proveedor proveedor = (Proveedor) actual.getDato();
            if (proveedor.getUsuario().equals(usuario)) {
                return proveedor.getIdProveedor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    } catch (Exception e) {
        System.out.println("Error al obtener ID de proveedor: " + e.getMessage());
        return null;
    }
}
```

---

### 3️⃣ **SERVICIOS DISPONIBLES PARA CADA TIPO**

El `AdministradorService` ya tiene todos los métodos que necesitan:

#### 📁 **Para Cliente:**
- `buscarClientePorId(String idCliente)`
- `actualizarCliente(Cliente clienteActualizado)`
- `obtenerTodosLosClientes()`

#### 📁 **Para Recepcionista:**
- `buscarRecepcionistaPorId(String idRecepcionista)`
- `actualizarRecepcionista(Recepcionista recepcionistaActualizado)`
- `obtenerTodosLosRecepcionistas()`

#### 📁 **Para Proveedor:**
- `buscarProveedorPorId(String idProveedor)`
- `actualizarProveedor(Proveedor proveedorActualizado)`
- `obtenerTodosLosProveedores()`

---

## 🔄 **FLUJO COMPLETO**

```
1. Usuario ingresa credenciales en Login
2. Login autentica usuario con LoginService
3. Login obtiene ID usando obtenerId[Tipo]PorUsuario()
4. Login abre frame correspondiente pasando el ID: new Cliente(idCliente)
5. Frame recibe ID en constructor y lo guarda en campo idClienteActual
6. Frame llama precargarDatos[Tipo]PorId() para llenar formulario
7. Usuario puede editar y guardar usando el ID almacenado
```

---

## ⚠️ **COSAS IMPORTANTES A RECORDAR**

1. **NO usar `SesionUsuario`** - Todo se maneja con ID directo
2. **NO usar `instanceof`** - Buscar directamente por ID
3. **Siempre validar** que el ID no sea null antes de usarlo
4. **Usar try-catch** para manejar errores
5. **Agregar System.out.println** para debug
6. **Importar las clases** necesarias: `AdministradorService`, `LoginService`, modelos

---

## 📝 **IMPORTS NECESARIOS**

Agregar estos imports en cada frame:
```java
import Service.AdministradorService;
import Service.LoginService;
import Model.Cliente; // o Recepcionista, Proveedor según corresponda
import javax.swing.JOptionPane;
```

---

## 🎉 **¡LISTO!**

Con estos cambios, cada frame recibirá directamente el ID del usuario logueado y podrá:
- ✅ Cargar datos automáticamente al abrir
- ✅ Guardar cambios usando el ID
- ✅ Recargar datos si cancela edición
- ✅ Funcionar independientemente sin sesiones complejas

**¿Dudas?** Busca los ejemplos en `Administrador.java` y `Login.java` que ya están implementados.