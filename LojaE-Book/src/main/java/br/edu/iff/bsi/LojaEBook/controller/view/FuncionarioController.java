package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService FuncionarioServ;
	@Autowired
	public CargoService CargoServ;
	
	@PostMapping("/")
	public String addFuncionario(Funcionario funcionario, String funcao) throws Exception {
		if(FuncionarioServ.buscarPeloCPF(funcionario.getCpf())!=null) {
			return "Funcionario já cadastrado";
		}else{			
			Cargo cargoBusca = CargoServ.buscarPelaFuncao(funcao);
			if(cargoBusca == null) {
				return "Cargo não existe";
			}else {
				funcionario.setCargo(cargoBusca);
				Funcionario f = FuncionarioServ.salvarFuncionario(funcionario);
				return "Registrado no id "+f.getId();
			}		
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String funcao) throws Exception {
		Funcionario f = FuncionarioServ.buscarPeloCPF(cpf);
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
				Cargo cargoBusca = CargoServ.buscarPelaFuncao(funcao);
				if(cargoBusca == null) {
					return "Cargo não existe";
				}else {
					f.setCargo(cargoBusca);
				}
			}
			FuncionarioServ.flush();
			return "Atualizado no id "+f.getId();
		}
	}
	
	@PostMapping("/deletePorCPF")
	public String deletarFuncionarioCPF(String cpf) throws Exception {
		Funcionario f = FuncionarioServ.buscarPeloCPF(cpf);
		if(f!=null) {	
			FuncionarioServ.deletarFuncionario(f);
			return "Funcionario deletado no id "+f.getId();
		}else {
			return "Funcionario não encontrado";
		}
	}
	
	@PostMapping("/listarFuncionarios")
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioServ.listarFuncionarios();
	}
	
	@PostMapping("/buscaCPF")
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		Funcionario f = FuncionarioServ.buscarPeloCPF(cpf);
		if(f!=null) {			
			return "Id do funcionário: "+f.getId();
		}else {
			return "Funcionário não encontrado";
		}
	}
	
	@PostMapping("/listarTelefones")
	public List<String> listarTelefones(String cpf) throws Exception {
		return FuncionarioServ.ListarTelefonePeloCPF(cpf);
	}
	
	@PostMapping("/addTelefone")
	public String addTelefone(String cpf, String telefone) throws Exception {
		Funcionario f = FuncionarioServ.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {			
			String t = FuncionarioServ.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				f.adicionarTelefone(telefone);
				FuncionarioServ.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	@PostMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		Funcionario f = FuncionarioServ.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {				
			String t = FuncionarioServ.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				f.removerTelefone(telefone);
				FuncionarioServ.flush();
				return "Telefone removido";
			}
		}
	}
}
