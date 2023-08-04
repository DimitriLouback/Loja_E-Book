package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;

@RestController
@RequestMapping(path= "/api/v1/funcionario")
public class FuncionarioRestController {

	@Autowired
	public FuncionarioService FuncionarioServ;
	
	@PostMapping("/{id}")
	@ResponseBody
	public String addFuncionario(@PathVariable("id") Long id, @ModelAttribute Funcionario funcionario, String funcao) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {				
			return FuncionarioServ.addFuncionario(funcionario, funcao);
		}else {
			return "Funcionario já adicionado";
		}
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public String atualizarFuncionario(@PathVariable("id") Long id, String nome, String email, String senha, String funcao) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não achado";
		}else {
			return FuncionarioServ.atualizarFuncionario(fBusca.getCpf(), nome, email, senha, funcao);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletarFuncionarioCPF(@PathVariable("id") Long id) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não achado";
		}else {
			return FuncionarioServ.deletarFuncionarioCPF(fBusca.getCpf());
		}
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioServ.listarFuncionarios();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Funcionario buscarFuncionarioId(@PathVariable("id") Long id) throws Exception {
		return FuncionarioServ.getFuncionarioById(id);
	}
	
	@GetMapping("/{id}/telefones")
	@ResponseBody
	public List<String> listarTelefones(@PathVariable("id") Long id) throws Exception {
		return FuncionarioServ.ListarTelefonePeloCPF(FuncionarioServ.getFuncionarioById(id).getCpf());
	}
	
	@PostMapping("/{id}/telefones")
	@ResponseBody
	public String addTelefone(@PathVariable("id") Long id, String telefone) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não achado";
		}else {
			return FuncionarioServ.addTelefone(fBusca.getCpf(), telefone);
		}
	}
	
	@DeleteMapping("/{id}/telefones")
	@ResponseBody
	public String removeTelefone(@PathVariable("id") Long id, String telefone) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não achado";
		}else {
			return FuncionarioServ.removeTelefone(fBusca.getCpf(), telefone);
		}
	}
}
