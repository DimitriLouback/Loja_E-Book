package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("e-book")
public class E_BookController {

	@Autowired
	private E_BookService EBookServ;
	
	@GetMapping("")
	public String page(Model model) throws Exception {
		return "CRUD_E_Book";
	}
	
	@GetMapping("/addForm")
	public String addE_BookForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("e_book_add", new E_Book());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_E_Book";
	}
	
	@PostMapping("/add")
	public String addE_Book(@Valid @ModelAttribute E_Book e_book, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "erro";
		}else {			
			String resposta = EBookServ.addE_Book(e_book);
			try {
				return "redirect:/e-book/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "erro";
			}
		}
	}
	
	@GetMapping("/listarE_Books")
	public String listarE_Books(Model model, HttpServletRequest request) throws Exception {
		String titulo = request.getParameter("titulo");
		if(titulo==null) {			
			model.addAttribute("e_book_lista", EBookServ.listarE_Books());
		}else {
			E_Book busca = EBookServ.buscarE_Books(URLDecoder.decode(titulo, "UTF-8"));
			if(busca==null) {
				busca = new E_Book();
			}
			model.addAttribute("e_book_lista", busca);
		}
		return "CRUD_E_Book";
	}
	
	@PostMapping("/buscaTitulo")
	public String buscarE_Books(String titulo) throws Exception {
		return "redirect:/e-book/listarE_Books?titulo="+URLEncoder.encode(titulo, "UTF-8");
	}
	
	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		model.addAttribute("e_book_edit", EBookServ.getEBookById(id));
		return "CRUD_E_Book";
	}

	@PostMapping("/atualizar")
	public String atualizarE_Book(@Valid @ModelAttribute E_Book e_book, BindingResult resultado, Model model) {
		String titulo = e_book.getTitulo();
		double preco = e_book.getPreco();
		String genero = e_book.getGenero();
		String autor = e_book.getAutor();
		String editora = e_book.getEditora();
		int qtdPaginas = e_book.getQtdPaginas();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "erro";
		}else {			
			EBookServ.atualizarE_Book(titulo, preco, genero, autor, editora, qtdPaginas);
			return "redirect:/e-book/listarE_Books"; 
		}
	}
	
	@GetMapping("/deletaPorTitulo")
	public String deletarE_BookTitulo(String titulo) throws Exception {
		EBookServ.deletarE_BookTitulo(titulo);
		return "redirect:/e-book/listarE_Books";	
	}
	
}
