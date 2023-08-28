package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;

@Repository
public interface Colecao_E_BookRepository extends JpaRepository<Colecao_E_Book, Long> {

	@Query(value="SELECT * FROM COLECAO_E_BOOK WHERE SERIE = ?1", nativeQuery = true)
	Colecao_E_Book buscarPelaSerie(String serie);
	
	@Query(value="SELECT * FROM COLECAO_E_BOOK WHERE ID = ?1", nativeQuery = true)
	Colecao_E_Book BuscarPeloId(Long id);
	
	@Query(value="SELECT TITULO FROM E_BOOK WHERE ID_COLECAO_E_BOOK = ?1 AND TITULO = ?2", nativeQuery = true)
	String buscarTituloPeloIdColecao(Long id_colecao_e_book, String titulo);
	
	@Query(value="SELECT * FROM COLECAO_E_BOOK WHERE ID IN(SELECT FK_PRODUTO FROM ASSOCIACAO_COMPRA_PRODUTO CP WHERE FK_COMPRA = ?1)", nativeQuery = true)
	List<Colecao_E_Book> ListarColecaoEBookPeloIdCompra(Long id);
	
	@Query(value="SELECT COUNT(*) FROM ASSOCIACAO_COMPRA_PRODUTO JOIN COLECAO_E_BOOK JOIN COMPRA WHERE FK_PRODUTO = COLECAO_E_BOOK.ID AND FK_COMPRA = COMPRA.ID AND COMPRA.FINALIZADO = FALSE AND COLECAO_E_BOOK.ID = ?1", nativeQuery = true)
	int QtdCompraAbertaAssociadaColecaoEBook(Long id);
}
