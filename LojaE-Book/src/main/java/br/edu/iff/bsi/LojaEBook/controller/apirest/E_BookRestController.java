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

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/e_book")
public class E_BookRestController {

	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um E-Book em expecifíco")
	public String addE_Book(double preco, String titulo, String genero, String autor, String editora, int qtdPaginas) throws Exception {			
		return EBookServ.addE_Book(new E_Book(preco, titulo, genero, autor, editora, qtdPaginas));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um E-Book em expecifíco")
	public String atualizarE_Book(@PathVariable("id") Long id, String preco, String genero, String autor, String editora, String qtdPaginas) throws Exception {
		E_Book eBusca = EBookServ.getEBookById(id);
		if(eBusca==null) {				
			return "E-Book não achado";
		}else {
			return EBookServ.atualizarE_Book(eBusca.getTitulo(), preco, genero, autor, editora, qtdPaginas);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um E-Book em expecifíco")
	public String deletarE_Book(@PathVariable("id") Long id) throws Exception {
		E_Book eBusca = EBookServ.getEBookById(id);
		if(eBusca==null) {				
			return "E-Book não achado";
		}else {
			return EBookServ.deletarE_BookTitulo(eBusca.getTitulo());
		}
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar todos os E-Books")
	public List<E_Book> listarE_Books() throws Exception {
		return EBookServ.listarE_Books();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um E-Book em expecifíco")
	public E_Book buscarE_Books(@PathVariable("id") Long id) throws Exception {
		return EBookServ.getEBookById(id);
	}
}
