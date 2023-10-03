package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="SELECT * FROM USUARIO WHERE LOGIN = ?1", nativeQuery = true)
	public Usuario findByLogin(String login);
	
	@Query(value="SELECT * FROM USUARIO WHERE ID = ?1", nativeQuery = true)
	public Usuario buscarPorId(Long id);
	
	@Query(value="SELECT * FROM USUARIO", nativeQuery = true)
	public List<Usuario> listarTodos();

}
