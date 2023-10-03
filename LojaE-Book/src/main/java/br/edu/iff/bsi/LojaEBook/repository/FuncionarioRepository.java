package br.edu.iff.bsi.LojaEBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.bsi.LojaEBook.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE CPF = ?1", nativeQuery = true)
	Funcionario buscarPeloCPF(String CPF);
	
	@Query(value="SELECT * FROM FUNCIONARIO WHERE ID = ?1", nativeQuery = true)
	Funcionario BuscarPeloId(Long id);

	@Query(value="SELECT TELEFONE FROM FUNCIONARIO_TELEFONE JOIN FUNCIONARIO WHERE CPF = ?1 AND TELEFONE = ?2 AND ID = FUNCIONARIO_ID", nativeQuery = true)
	String buscarTelefonePeloCPF(String CPF, String telefone);
	
	@Query(value="SELECT TELEFONE FROM FUNCIONARIO_TELEFONE JOIN FUNCIONARIO WHERE CPF = ?1 AND ID = FUNCIONARIO_ID", nativeQuery = true)
	List<String> ListarTelefonePeloCPF(String CPF);
	
	@Query(value="SELECT MAX(NIVEL_ACESSO) FROM FUNCIONARIO JOIN CARGO ON CARGO.ID = FK_CARGO", nativeQuery = true)
	String maiorNivelAcesso();
	
}
