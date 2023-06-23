package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/api")
public class MainRestController {

	@GetMapping
	@ResponseBody
	public String initial() {
		return "Olá, bem vindo a API do meu APP.";
	}
	/*
	@GetMapping("/{id}")
	public String page (@PathVariable("id") int id) {
		return "Olá mundo -> "+id;
	}
	
	@GetMapping("/{id}")
	public String page (@PathVariable("id") int id, @RequestParam("nome") String nome) {
		return "Olá mundo -> "+id+" --> "+nome;
	}
	*/
	@PostMapping("/new")
	@ResponseBody
	public String addItem(@RequestParam Map<String, String> allParam) {
		return "Os parâmetros passados foram "+ allParam.entrySet();
	}
	
}
