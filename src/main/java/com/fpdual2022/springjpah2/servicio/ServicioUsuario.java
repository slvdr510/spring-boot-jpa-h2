package com.fpdual2022.springjpah2.servicio;

import java.util.List;

import com.fpdual2022.springjpah2.modelo.Usuario;

public interface ServicioUsuario {

	public void guardarUsuario(Usuario usuario);

	public List<Usuario> obtenerTodosUsuarios();

	public Usuario obtenerUsuarioPorId(Long id);

	public void modificarUsuario(Long id, Usuario usuario);

	public void borrarUsuario(Long id);

	public void borrarUsuarios();

	public List<Usuario> obtenerUsuarioMayorEdad(Boolean mayorDeEdad);

	public List<Usuario> obtenerUsuarioPorNombre(String nombre);

}
