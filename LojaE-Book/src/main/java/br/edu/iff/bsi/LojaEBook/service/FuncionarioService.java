package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository FuncionarioRep;
	
	public Funcionario salvarFuncionario(Funcionario funcionario) {
		return FuncionarioRep.save(funcionario);
	}
	
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioRep.findAll();
	}
	
	public Funcionario buscarPeloCPF(String cpf) {
		return FuncionarioRep.buscarPeloCPF(cpf);
	}
	
	public List<String> ListarTelefonePeloCPF(String cpf) throws Exception {
		return FuncionarioRep.ListarTelefonePeloCPF(cpf);
	}
	
	public String buscarTelefonePeloCPF(String cpf, String telefone) {
		return FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
	}
	
	public void flush() {
		FuncionarioRep.flush();
	}
	
	public void deletarFuncionario(Funcionario funcionario) {
		FuncionarioRep.delete(funcionario);
	}
}
