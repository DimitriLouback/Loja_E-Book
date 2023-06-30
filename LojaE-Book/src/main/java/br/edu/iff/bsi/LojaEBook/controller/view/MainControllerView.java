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
