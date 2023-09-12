package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<E_Book, Long> {
	
	public Usuario findByLogin(String login);

}
