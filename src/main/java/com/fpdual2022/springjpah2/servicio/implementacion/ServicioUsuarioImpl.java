package com.fpdual2022.springjpah2.servicio.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpdual2022.springjpah2.modelo.Usuario;
import com.fpdual2022.springjpah2.repositorio.RepositorioUsuario;
import com.fpdual2022.springjpah2.servicio.ServicioUsuario;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		Usuario usuarioAGuardar = new Usuario(usuario.getNombre(), usuario.getPrimerApellido(),
				usuario.getSegundoApellido(), usuario.getEdad(), usuario.isMayorDeEdad());
		this.repositorioUsuario.saveAndFlush(usuarioAGuardar);
	}

	@Override
	public List<Usuario> obtenerTodosUsuarios() {
		return this.repositorioUsuario.findAll();
	}

	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		Optional<Usuario> usuarioEncontrado = this.repositorioUsuario.findById(id);
		return usuarioEncontrado.orElse(null);
	}

	@Override
	public void modificarUsuario(Long id, Usuario usuario) {

		Optional<Usuario> usuarioEncontrado = this.repositorioUsuario.findById(id);

		if (usuarioEncontrado.isPresent()) {
			Usuario usuarioAGuardar = new Usuario(usuario.getNombre(), usuario.getPrimerApellido(),
					usuario.getSegundoApellido(), usuario.getEdad(), usuario.isMayorDeEdad());

			this.repositorioUsuario.saveAndFlush(usuarioAGuardar);

		}

	}

	@Override
	public void borrarUsuario(Long id) {
		this.repositorioUsuario.deleteById(id);
	}

	@Override
	public void borrarUsuarios() {
		this.repositorioUsuario.deleteAll();
	}

	@Override
	public List<Usuario> obtenerUsuarioMayorEdad(Boolean mayorDeEdad) {
		return this.repositorioUsuario.findByMayorDeEdad(mayorDeEdad);
	}

	@Override
	public List<Usuario> obtenerUsuarioPorNombre(String nombre) {
		return this.repositorioUsuario.findByNombre(nombre);
	}

}
