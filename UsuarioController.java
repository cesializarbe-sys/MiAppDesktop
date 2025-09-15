// Definición del paquete donde se encuentra esta clase
package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Definición de la clase UsuarioController que maneja la lógica de usuarios
public class UsuarioController {

    // Lista privada que almacena todos los usuarios
    private final List<Usuario> usuarios;

    // Constructor de la clase
    public UsuarioController() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Valery Chumpitaz", "valery@correo.com", "Administrador"));
        usuarios.add(new Usuario("Juan Pérez", "juan@correo.com", "Docente"));
        usuarios.add(new Usuario("María López", "maria@correo.com", "Estudiante"));
    }

    // Retorna la lista completa de usuarios
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Agregar un nuevo usuario
    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Eliminar un usuario según índice
    public void deleteUsuario(int index) {
        if (index >= 0 && index < usuarios.size()) {
            usuarios.remove(index);
        }
    }

    // Actualizar un usuario según índice
    public void updateUsuario(int index, Usuario usuario) {
        if (index >= 0 && index < usuarios.size()) {
            usuarios.set(index, usuario);
        }
    }

    // Método de búsqueda (por nombre o correo)
    public List<Usuario> buscarUsuarios(String criterio) {
        return usuarios.stream()
                .filter(u -> u.getNombre().toLowerCase().contains(criterio.toLowerCase())
                        || u.getCorreo().toLowerCase().contains(criterio.toLowerCase()))
                .collect(Collectors.toList());
    }
}