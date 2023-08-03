package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@Controller
@RequestMapping("colecao-e-book")
public class Colecao_E_BookController {

	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addColecao_E_Book(Colecao_E_Book colecao_e_book) throws Exception {
		return Colecao_E_BookServ.addColecao_E_Book(colecao_e_book);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarE_Book(String serie, String preco) throws Exception {
		return Colecao_E_BookServ.atualizarE_Book(serie, preco);
	}
	
	@PostMapping("/deletarPorSerie")
	@ResponseBody
	public String deletarE_BookSerie(String serie) throws Exception {
		return Colecao_E_BookServ.deletarE_BookSerie(serie);
	}
	
	@PostMapping("/listarColecao_E_Books")
	@ResponseBody
	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return Colecao_E_BookServ.listarColecao_E_Books();
	}
	
	@PostMapping("/buscaSerie")
	@ResponseBody
	public String buscarColecao_E_Books(String serie) throws Exception {
		return Colecao_E_BookServ.buscarColecao_E_Books(serie);
	}
	
	@PostMapping("/listarE_Books")
	@ResponseBody
	public List<E_Book> listarE_Books(String serie) throws Exception {
		return EBookServ.ListarEBookPeloIdColecao(Colecao_E_BookServ.buscarPelaSerie(serie).getId());
	}
	
	@PostMapping("/addE_Book")
	@ResponseBody
	public String addE_Book(String serie, String titulo) throws Exception {
		return Colecao_E_BookServ.addE_Book(serie, titulo);
	}
	
	@PostMapping("/removeE_Book")
	@ResponseBody
	public String removeE_Book(String serie, String titulo) throws Exception {
		return Colecao_E_BookServ.removeE_Book(serie, titulo);
	}
}
