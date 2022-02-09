package com.fpdual2022.springjpah2.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpdual2022.springjpah2.modelo.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

	List<Usuario> findByMayorDeEdad(boolean mayorDeEdad);

	List<Usuario> findByNombre(String nombre);

}
