package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.iff.bsi.LojaEBook.model.Pessoa;

@Controller
@RequestMapping(path = "/home")
public class MainControllerView {

	@GetMapping()
	public String page() {
		return "index";
	}
	
	@GetMapping("/new/cliente")
	public String formCliente() {
		return "formCliente";
	}
	
	@GetMapping("/new/funcionario")
	public String formFuncionario() {
		return "formFuncionario";
	}
		
	@GetMapping("/new/cargo")
	public String formCargo() {
		return "formCargo";
	}
	
	@GetMapping("/new/e-book")
	public String formE_Book() {
		return "formE_Book";
	}
	
	@GetMapping("/new/colecao-e-book")
	public String formColecao_E_Book() {
		return "formColecao_E_Book";
	}
	
	@GetMapping("/new/compra")
	public String formCompra() {
		return "formCompra";
	}
	
	@PostMapping("/new/pessoa")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerPessoa(@ModelAttribute Pessoa pessoa){
		System.out.println("ID pessoa: "+pessoa.getId());
		System.out.println("Nome pessoa: "+pessoa.getNome());
		System.out.println("Email pessoa: "+pessoa.getEmail());
		System.out.println("CPF pessoa: "+pessoa.getCpf());
		return"sucesso";
	}
}
