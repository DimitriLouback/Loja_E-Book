package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@Controller
@RequestMapping("e-book")
public class E_BookController {

	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addE_Book(E_Book e_book) throws Exception {
		return EBookServ.addE_Book(e_book);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarE_Book(String titulo, String preco, String genero, String autor, String editora, String qtdPaginas) throws Exception {
		return EBookServ.atualizarE_Book(titulo, preco, genero, autor, editora, qtdPaginas);
	}
	
	@PostMapping("/deletaPorTitulo")
	@ResponseBody
	public String deletarE_BookTitulo(String titulo) throws Exception {
		return EBookServ.deletarE_BookTitulo(titulo);
	}
	
	@PostMapping("/listarE_Books")
	@ResponseBody
	public List<E_Book> listarE_Books() throws Exception {
		return EBookServ.listarE_Books();
	}
	
	@PostMapping("/buscaTitulo")
	@ResponseBody
	public String buscarE_Books(String titulo) throws Exception {
		return EBookServ.buscarE_Books(titulo);
	}
}
