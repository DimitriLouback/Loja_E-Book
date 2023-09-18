package br.edu.iff.bsi.LojaEBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	@Query(value="SELECT * FROM PERMISSAO WHERE NOME = ?1", nativeQuery = true)
	public Permissao getByNome(String nome);
	
}
