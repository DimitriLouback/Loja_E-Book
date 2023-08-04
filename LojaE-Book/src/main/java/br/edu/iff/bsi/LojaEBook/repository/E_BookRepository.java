package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.E_Book;

@Repository
public interface E_BookRepository extends JpaRepository<E_Book, Long> {

	@Query(value="SELECT * FROM E_BOOK WHERE TITULO = ?1", nativeQuery = true)
	E_Book buscarPeloTitulo(String Titulo);
	
	@Query(value="SELECT * FROM E_BOOK WHERE ID = ?1", nativeQuery = true)
	E_Book BuscarPeloId(Long id);
	
	@Query(value="SELECT * FROM E_BOOK WHERE ID_COLECAO_E_BOOK = ?1", nativeQuery = true)
	List<E_Book> ListarEBookPeloIdColecao(Long id_colecao);
	
	@Query(value="SELECT E.* FROM E_BOOK E, ASSOCIACAO_COMPRA_PRODUTO CP WHERE E.ID = CP.FK_PRODUTO AND CP.FK_COMPRA = ?1", nativeQuery = true)
	List<E_Book> ListarEBookPeloIdCompra(Long id);
	
}
