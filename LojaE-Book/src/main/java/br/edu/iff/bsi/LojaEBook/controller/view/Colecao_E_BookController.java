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

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("colecao-e-book")
public class Colecao_E_BookController {

	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private E_BookService EBookServ;
	
	@GetMapping("")
	public String page() throws Exception {
		return "redirect:/colecao-e-book/listarColecao_E_Books";
	}
	
	@GetMapping("/addForm")
	public String addColecao_E_BookForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("colecao_e_book_add", new Colecao_E_Book());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Colecao_E_Book";
	}
	
	@PostMapping("/add")
	public String addColecao_E_Book(@Valid @ModelAttribute Colecao_E_Book colecao_e_book, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			String resposta = Colecao_E_BookServ.addColecao_E_Book(colecao_e_book);
			try {
				return "redirect:/colecao-e-book/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
		}
	}
	
	@GetMapping("/listarColecao_E_Books")
	public String listarColecao_E_Books(Model model, HttpServletRequest request) throws Exception {
		String serie = request.getParameter("serie");
		if(serie==null) {			
			model.addAttribute("colecao_e_book_lista", Colecao_E_BookServ.listarColecao_E_Books());
		}else {
			Colecao_E_Book busca = Colecao_E_BookServ.buscarColecao_E_Books(URLDecoder.decode(serie, "UTF-8"));
			if(busca==null) {
				busca = new Colecao_E_Book();
			}
			model.addAttribute("colecao_e_book_lista", busca);
		}
		return "CRUD_Colecao_E_Book";
	}
	
	@PostMapping("/buscaSerie")
	public String buscarColecao_E_Books(String serie) throws Exception {
		return "redirect:/colecao-e-book/listarColecao_E_Books?serie="+URLEncoder.encode(serie, "UTF-8");
	}

	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		model.addAttribute("colecao_e_book_edit", Colecao_E_BookServ.getColecaoEBookById(id));
		model.addAttribute("e_book_lista", EBookServ.ListarEBookPeloIdColecao(id));
		return "CRUD_Colecao_E_Book";
	}
	
	@PostMapping("/atualizar")
	public String atualizarE_Book(@Valid @ModelAttribute Colecao_E_Book colecao_e_book, BindingResult resultado, Model model) {
		String serie = colecao_e_book.getSerie();
		double preco = colecao_e_book.getPreco();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {						
			Colecao_E_BookServ.atualizarE_Book(serie, preco);
			return "redirect:/colecao-e-book/editar?id="+Colecao_E_BookServ.buscarColecao_E_Books(serie).getId();
		}
	}
	
	@GetMapping("/deletarPorSerie")
	public String deletarE_BookSerie(String serie) throws Exception {
		Colecao_E_BookServ.deletarE_BookSerie(serie);
		return "redirect:/colecao-e-book/listarColecao_E_Books";
	}
	
	@PostMapping("/addE_Book")
	public String addE_Book(String serie, String titulo) throws Exception {
		Colecao_E_BookServ.addE_Book(serie, titulo);
		return "redirect:/colecao-e-book/editar?id="+Colecao_E_BookServ.buscarColecao_E_Books(serie).getId();
	}
	
	@GetMapping("/removeE_Book")
	public String removeE_Book(String serie, String titulo) throws Exception {
		Colecao_E_BookServ.removeE_Book(serie, titulo);
		return "redirect:/colecao-e-book/editar?id="+Colecao_E_BookServ.buscarColecao_E_Books(serie).getId();
	}
}
