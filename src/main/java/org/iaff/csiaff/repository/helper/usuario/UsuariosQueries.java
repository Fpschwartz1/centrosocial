package org.iaff.csiaff.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.iaff.csiaff.model.Grupo;
import org.iaff.csiaff.model.Usuario;
import org.iaff.csiaff.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	public Usuario buscarComGrupos(Long codigo);
	
	public List<Usuario> usuariosDoGrupoCujoNome(Grupo grupo, String nome);
}
