package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@Controller
@RequestMapping("e-book")
public class E_BookController {

	@Autowired
	private E_BookService EBookServ;
	
	@GetMapping("")
	public String page(Model model) throws Exception {
		model.addAttribute("e_books", EBookServ.listarE_Books());
		return "CRUD_E_Book";
	}
	
	@GetMapping("/editar")
	public String pageEditar(@RequestParam Long id, Model model) throws Exception {
		model.addAttribute("e_book", EBookServ.getEBookById(id));
		return "Atualizar_E_Book";
	}
	
	@PostMapping("/add")
	public String addE_Book(E_Book e_book, Model model) throws Exception {
		model.addAttribute("respostaAdd", EBookServ.addE_Book(e_book));
		model.addAttribute("e_books", EBookServ.listarE_Books());
		return "CRUD_E_Book";
	}
	
	@PostMapping("/atualizar")
	public String atualizarE_Book(String titulo, String preco, String genero, String autor, String editora, String qtdPaginas) throws Exception {
		EBookServ.atualizarE_Book(titulo, preco, genero, autor, editora, qtdPaginas);
		return "redirect:/e-book"; 
	}
	
	@GetMapping("/deletaPorTitulo")
	public String deletarE_BookTitulo(String titulo) throws Exception {
		EBookServ.deletarE_BookTitulo(titulo);
		return "redirect:/e-book";	
	}
	
	@PostMapping("/listarE_Books")
	public List<E_Book> listarE_Books() throws Exception {
		return EBookServ.listarE_Books();
	}
	
	@PostMapping("/buscaTitulo")
	public String buscarE_Books(String titulo, Model model) throws Exception {
		model.addAttribute("e_books", EBookServ.listarE_Books());
		model.addAttribute("e_book_achado", EBookServ.buscarE_Books(titulo));
		return "CRUD_E_Book";
	}
}
