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

	@Query(value="SELECT TELEFONE FROM FUNCIONARIO_TELEFONE JOIN FUNCIONARIO WHERE CPF = ?1 and TELEFONE = ?2", nativeQuery = true)
	String buscarTelefonePeloCPF(String CPF, String telefone);
	
	@Query(value="SELECT TELEFONE FROM FUNCIONARIO_TELEFONE JOIN FUNCIONARIO WHERE CPF = ?1", nativeQuery = true)
	List<String> ListarTelefonePeloCPF(String CPF);
}
