package com.fpdual2022.springjpah2.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpdual2022.springjpah2.modelo.Usuario;
import com.fpdual2022.springjpah2.servicio.ServicioUsuario;

@RestController
@RequestMapping("/api")
public class ControladorUsuario {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@GetMapping("/usuarios")
	public ResponseEntity<?> obtenerUsuario(@RequestParam(required = false) String nombre) {

		try {

			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (nombre == null) {
				servicioUsuario.obtenerTodosUsuarios().forEach(usuarios::add);
			} else {
				servicioUsuario.obtenerUsuarioPorNombre(nombre).forEach(usuarios::add);
			}
			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id) {

		Usuario usuarioObtenido = servicioUsuario.obtenerUsuarioPorId(id);

		if (usuarioObtenido != null) {
			return new ResponseEntity<>(usuarioObtenido, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario,
			@PathVariable String nombreUsuarioNuevo) {

		try {

			Usuario usuarioAGuardar = new Usuario(usuario.getNombre(), usuario.getPrimerApellido(),
					usuario.getSegundoApellido(), usuario.getEdad(), usuario.isMayorDeEdad());

			this.servicioUsuario.guardarUsuario(usuarioAGuardar);

			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {

		Usuario usuarioObtenido = servicioUsuario.obtenerUsuarioPorId(id);

		if (usuarioObtenido != null) {

			Usuario usuarioModificado = usuarioObtenido;
			usuarioModificado.setNombre(usuario.getNombre());
			usuarioModificado.setPrimerApellido(null);
			usuarioModificado.setPrimerApellido(null);
			usuarioModificado.setEdad(null);
			usuarioModificado.setMayorDeEdad(false);

			servicioUsuario.modificarUsuario(id, usuarioModificado);

			return new ResponseEntity<>(HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") Long id) {
		try {

			servicioUsuario.borrarUsuario(id);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuarios")
	public ResponseEntity<HttpStatus> deleteUsuarios() {
		try {

			servicioUsuario.borrarUsuarios();

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/mayoresDeEdad")
	public ResponseEntity<List<Usuario>> mostrarUsuariosMayoresDeEdad(Boolean mayorDeEdad) {

		try {

			List<Usuario> usuariosMayoresDeEdad = servicioUsuario.obtenerUsuarioMayorEdad(mayorDeEdad);

			if (usuariosMayoresDeEdad.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(usuariosMayoresDeEdad, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}