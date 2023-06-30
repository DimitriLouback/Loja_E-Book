package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Pessoa;

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
	
	@PostMapping("/new")
	@ResponseBody
	public String addItem(@RequestParam Map<String, String> allParam) {
		return "Os parâmetros passados foram "+ allParam.entrySet();
	}

	@GetMapping("/teste/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") String id){
		if(id.equals("123")){
			return ResponseEntity.ok().header("Content-Type","text/html").body("<h1>Sucesso!</h1>");
		}else{
			return ResponseEntity.notFound().header("Content-Type","text/html").build();
		}
	}

	@PostMapping("/new/users")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> registerUser(@RequestParam Map<String, String> userMap) {
		System.out.println("User ID: "+userMap.get("userName"));
		System.out.println("User ID: "+userMap.get("password"));
		return userMap;
	}
	

	@PostMapping("/new/users")
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> registerUser(@RequestParam Map<String, String> userMap) {
		try{
			if(userMap.get("userName")==null||userMap.get("password")==null) {
				throw new NullPointerException();
			}
			System.out.println("User ID: "+userMap.get("userName"));
			System.out.println("User ID: "+userMap.get("password"));
			return userMap;
		}catch(Exception e) {
			System.out.println("Insir aos valores!");
			//userMap.clear();
			Map<String, String> erro = new HashMap<>();
			erro.put("Erro", "Insira os valores!");
			return erro;
		}
	}
	
	@PostMapping("/new/users")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerPessoa(@ModelAttribute Pessoa pessoa){
		System.out.println("ID pessoa: "+pessoa.getId());
		System.out.println("Nome pessoa: "+pessoa.getNome());
		System.out.println("Email pessoa: "+pessoa.getEmail());
		System.out.println("CPF pessoa: "+pessoa.getCpf());
		return"Sucesso";
	}
	*/
	
}
