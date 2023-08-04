package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	@Query(value="SELECT * FROM CARGO WHERE FUNCAO = ?1", nativeQuery = true)
	Cargo buscarPelaFuncao(String funcao);
	
	@Query(value="SELECT * FROM CARGO WHERE ID = ?1", nativeQuery = true)
	Cargo BuscarPeloId(Long id);
	
}
