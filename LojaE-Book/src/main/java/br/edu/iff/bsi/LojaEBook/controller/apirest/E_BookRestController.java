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

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@RestController
@RequestMapping(path= "/api/v1/e_book")
public class E_BookRestController {

	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/{id}")
	@ResponseBody
	public String addE_Book(@PathVariable("id") Long id, @ModelAttribute E_Book e_book) throws Exception {
		E_Book eBusca = EBookServ.getEBookById(id);
		if(eBusca==null) {				
			return EBookServ.addE_Book(e_book);
		}else {
			return "E-Book já adicionado";
		}
	}
	
	@PutMapping("/{id}")
	@ResponseBody
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
	public List<E_Book> listarE_Books() throws Exception {
		return EBookServ.listarE_Books();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public E_Book buscarE_Books(@PathVariable("id") Long id) throws Exception {
		return EBookServ.getEBookById(id);
	}
}
