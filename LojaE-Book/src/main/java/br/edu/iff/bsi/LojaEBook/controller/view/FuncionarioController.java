package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService FuncionarioServ;
	@Autowired
	public CargoService CargoServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addFuncionario(Funcionario funcionario, String funcao) throws Exception {
		return FuncionarioServ.addFuncionario(funcionario, funcao);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String funcao) throws Exception {
		return FuncionarioServ.atualizarFuncionario(cpf, nome, email, senha, funcao);
	}
	
	@PostMapping("/deletePorCPF")
	@ResponseBody
	public String deletarFuncionarioCPF(String cpf) throws Exception {
		return FuncionarioServ.deletarFuncionarioCPF(cpf);
	}
	
	@PostMapping("/listarFuncionarios")
	@ResponseBody
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioServ.listarFuncionarios();
	}
	
	@PostMapping("/buscaCPF")
	@ResponseBody
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		return FuncionarioServ.buscarFuncionarioCPF(cpf);
	}
	
	@PostMapping("/listarTelefones")
	@ResponseBody
	public List<String> listarTelefones(String cpf) throws Exception {
		return FuncionarioServ.ListarTelefonePeloCPF(cpf);
	}
	
	@PostMapping("/addTelefone")
	@ResponseBody
	public String addTelefone(String cpf, String telefone) throws Exception {
		return FuncionarioServ.addTelefone(cpf, telefone);
	}
	
	@PostMapping("/removeTelefone")
	@ResponseBody
	public String removeTelefone(String cpf, String telefone) throws Exception {
		return FuncionarioServ.removeTelefone(cpf, telefone);
	}
}
