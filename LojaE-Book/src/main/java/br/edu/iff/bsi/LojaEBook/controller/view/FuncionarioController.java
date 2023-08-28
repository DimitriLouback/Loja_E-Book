package br.edu.iff.bsi.LojaEBook.controller.view;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	public FuncionarioService FuncionarioServ;
	@Autowired
	public CargoService CargoServ;
	
	@GetMapping("")
	public String page() {
		return "CRUD_Funcionario";
	}
	
	@GetMapping("/addForm")
	public String addFuncionarioForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cargo_lista", CargoServ.listarCargos());
		model.addAttribute("funcionario_add", new Funcionario());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Funcionario";
	}
	
	@PostMapping("/add")
	public String addFuncionario(@ModelAttribute Funcionario funcionario, @RequestParam String cargoEscolhido) throws Exception {
		String resposta = FuncionarioServ.addFuncionario(funcionario, cargoEscolhido);
		return  "redirect:/funcionario/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
	
	@GetMapping("/listarFuncionarios")
	public String listarFuncionarios(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		if(cpf==null) {					
			model.addAttribute("funcionario_lista", FuncionarioServ.listarFuncionarios());
		}else {
			Funcionario busca = FuncionarioServ.buscarFuncionarioCPF(cpf);
			if(busca==null) {
				busca = new Funcionario();
				busca.setCargo(new Cargo());
			}
			model.addAttribute("funcionario_lista", busca);
		}
		return "CRUD_Funcionario";
	}
	
	@PostMapping("/buscaCPF")
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		return "redirect:/funcionario/listarFuncionarios?cpf="+URLEncoder.encode(cpf, "UTF-8");
	}
	
	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		Funcionario funcionario = FuncionarioServ.getFuncionarioById(id);
		model.addAttribute("funcionario_edit", funcionario);
		model.addAttribute("telefone_lista", FuncionarioServ.ListarTelefonePeloCPF(funcionario.getCpf()));
		model.addAttribute("cargo_lista", CargoServ.listarCargos());
		return "CRUD_Funcionario";
	}
	
	@PostMapping("/atualizar")
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String cargoEscolhido) throws Exception {
		FuncionarioServ.atualizarFuncionario(cpf, nome, email, senha, cargoEscolhido);
		return "redirect:/funcionario/editar?id="+FuncionarioServ.buscarFuncionarioCPF(cpf).getId();
	}
	
	@GetMapping("/deletePorCPF")
	public String deletarFuncionarioCPF(String cpf) throws Exception {
		FuncionarioServ.deletarFuncionarioCPF(cpf);
		return "redirect:/funcionario/listarFuncionarios";
	}
	
	@GetMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		if(FuncionarioServ.buscarFuncionarioCPF(cpf).getQtdTelefones()>1) {			
			FuncionarioServ.removeTelefone(cpf, telefone);
		}
		return "redirect:/funcionario/editar?id="+FuncionarioServ.buscarFuncionarioCPF(cpf).getId();
	}
	
	@PostMapping("/addTelefone")
	public String addTelefone(String cpf, String telefone) throws Exception {
		FuncionarioServ.addTelefone(cpf, telefone);
		return "redirect:/funcionario/editar?id="+FuncionarioServ.buscarFuncionarioCPF(cpf).getId();
	}
}
