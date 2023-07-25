package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.repository.CargoRepository;
import br.edu.iff.bsi.LojaEBook.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository FuncionarioRep;
	@Autowired
	private CargoRepository CargoRep;
	
	public String addFuncionario(Funcionario funcionario, String funcao) {
		if(FuncionarioRep.buscarPeloCPF(funcionario.getCpf())!=null) {
			return "Funcionario já cadastrado";
		}else{			
			Cargo cargoBusca = CargoRep.buscarPelaFuncao(funcao);
			if(cargoBusca == null) {
				return "Cargo não existe";
			}else {
				funcionario.setCargo(cargoBusca);
				Funcionario f = FuncionarioRep.save(funcionario);
				return "Registrado no id "+f.getId();
			}		
		}
	}
	
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String funcao) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionario não achado";
		}else {		
			if(nome!=null) {
				f.setNome(nome);
			}
			if(email!=null) {				
				f.setEmail(email);
			}
			if(senha!=null) {				
				f.setSenha(senha);
			}
			if(funcao!=null) {
				Cargo cargoBusca = CargoRep.buscarPelaFuncao(funcao);
				if(cargoBusca == null) {
					return "Cargo não existe";
				}else {
					f.setCargo(cargoBusca);
				}
			}
			FuncionarioRep.flush();
			return "Atualizado no id "+f.getId();
		}
	}
	
	public String deletarFuncionarioCPF(String cpf) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f!=null) {	
			FuncionarioRep.delete(f);
			return "Funcionario deletado no id "+f.getId();
		}else {
			return "Funcionario não encontrado";
		}
	}
	
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioRep.findAll();
	}
	
	public String buscarFuncionarioCPF(String cpf) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f!=null) {			
			return "Id do funcionário: "+f.getId();
		}else {
			return "Funcionário não encontrado";
		}
	}
	
	public String addTelefone(String cpf, String telefone) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {		
			if(f.getQtdTelefones()==3) {
				return "Quatidade máxima de telefones já cadastrados";
			}
			String t = FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				f.adicionarTelefone(telefone);
				FuncionarioRep.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	public String removeTelefone(String cpf, String telefone) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {				
			String t = FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				f.removerTelefone(telefone);
				FuncionarioRep.flush();
				return "Telefone removido";
			}
		}
	}
	
	public List<String> ListarTelefonePeloCPF(String cpf) throws Exception {
		return FuncionarioRep.ListarTelefonePeloCPF(cpf);
	}
	
	public String buscarTelefonePeloCPF(String cpf, String telefone) {
		return FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
	}
	
}
