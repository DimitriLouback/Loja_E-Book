package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

	@Query(value="SELECT * FROM COMPRA WHERE ID = ?1", nativeQuery = true)
	Compra BuscarPeloId(Long id);
	
}
