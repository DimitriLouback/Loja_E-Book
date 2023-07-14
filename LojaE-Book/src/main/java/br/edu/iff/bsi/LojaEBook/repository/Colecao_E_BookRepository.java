package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;

@Repository
public interface Colecao_E_BookRepository extends JpaRepository<Colecao_E_Book, Long> {

	@Query(value="SELECT * FROM COLECAO_E_BOOK WHERE SERIE = ?1", nativeQuery = true)
	Colecao_E_Book buscarPelaSerie(String serie);
	
	@Query(value="SELECT TITULO FROM E_BOOK WHERE ID_COLECAO_E_BOOK = ?1 AND TITULO = ?2", nativeQuery = true)
	String buscarTituloPeloIdColecao(Long id_colecao_e_book, String titulo);
	
	
}
