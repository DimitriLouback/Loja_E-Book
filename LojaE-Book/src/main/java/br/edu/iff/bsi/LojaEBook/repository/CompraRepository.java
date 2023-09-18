package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	@Query(value="SELECT * FROM COMPRA WHERE ID = ?1", nativeQuery = true)
	Compra BuscarPeloId(Long id);
	
	@Query(value="SELECT ID_CLIENTE FROM COMPRA WHERE ID = ?1", nativeQuery = true)
	Long BuscarPeloIdCliente(Long id);
	
	@Query(value="SELECT COUNT(*) FROM ASSOCIACAO_COMPRA_PRODUTO JOIN COMPRA WHERE FK_PRODUTO = ?1 AND FK_COMPRA = ?2", nativeQuery = true)
	int verificarProdutoCompra(Long idProduto, Long idCompra);
	
	@Query(value="SELECT * FROM COMPRA  WHERE CPF_CLIENTE = ?1", nativeQuery = true)
	List<Compra> BuscarPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM COMPRA  WHERE CPF_CLIENTE = ?1 AND FINALIZADO = FALSE", nativeQuery = true)
	List<Compra> BuscarComprasAbertasPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM COMPRA  WHERE CPF_CLIENTE = ?1 AND FINALIZADO = TRUE", nativeQuery = true)
	List<Compra> BuscarComprasFechadasPeloCPF(String cpf);
	
	@Query(value="SELECT * FROM COMPRA WHERE FINALIZADO = FALSE AND ID IN(SELECT FK_COMPRA FROM ASSOCIACAO_COMPRA_PRODUTO WHERE FK_PRODUTO = ?1)", nativeQuery = true)
	List<Compra> BuscarComprasPeloIdProduto(Long id);
}
