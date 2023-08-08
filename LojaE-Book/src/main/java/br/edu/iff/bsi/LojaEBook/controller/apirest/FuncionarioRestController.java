package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/funcionario")
public class FuncionarioRestController {

	@Autowired
	public FuncionarioService FuncionarioServ;
	
	@PostMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Adicionar um funcionário em expecifíco")
	public String addFuncionario(@PathVariable("id") Long id, String nome, String email, String cpf, String senha, String telefone, String funcao) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {				
			return FuncionarioServ.addFuncionario(new Funcionario(id, nome, email, cpf, senha, telefone), funcao);
		}else {
			return "Funcionario já adicionado";
		}
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um funcionário em expecifíco")
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
	@Operation(summary = "Deletar um funcionário em expecifíco")
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
	@Operation(summary = "Listar todos os funcionários")
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioServ.listarFuncionarios();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um funcionário em expecifíco")
	public Funcionario buscarFuncionarioId(@PathVariable("id") Long id) throws Exception {
		return FuncionarioServ.getFuncionarioById(id);
	}
	
	@GetMapping("/{id}/telefones")
	@ResponseBody
	@Operation(summary = "Listar os telefones de um funcionário em expecifíco")
	public List<String> listarTelefones(@PathVariable("id") Long id) throws Exception {
		return FuncionarioServ.ListarTelefonePeloCPF(FuncionarioServ.getFuncionarioById(id).getCpf());
	}
	
	@PostMapping("/{id}/telefones")
	@ResponseBody
	@Operation(summary = "Adicionar um telefone em um funcionário em expecifíco")
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
	@Operation(summary = "Deletar um telefone um funcionário em expecifíco")
	public String removeTelefone(@PathVariable("id") Long id, String telefone) throws Exception {
		Funcionario fBusca = FuncionarioServ.getFuncionarioById(id);
		if(fBusca==null) {			
			return "Funcionario não achado";
		}else {
			return FuncionarioServ.removeTelefone(fBusca.getCpf(), telefone);
		}
	}
}
