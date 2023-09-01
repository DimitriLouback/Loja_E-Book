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

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/colecao_e_book")
public class Colecao_E_BookRestController {

	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar uma coleção de E-Book em expecifíco")
	public String addColecao_E_Book(double preco, String serie) throws Exception {		
		return Colecao_E_BookServ.addColecao_E_Book(new Colecao_E_Book(preco, serie));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar uma coleção de E-Book em expecifíco")
	public String atualizarE_Book(@PathVariable("id") Long id, double preco) throws Exception {
		Colecao_E_Book ceBusca = Colecao_E_BookServ.getColecaoEBookById(id);
		if(ceBusca==null) {				
			return "Coleção de E-Book não achado";
		}else {
			return Colecao_E_BookServ.atualizarE_Book(ceBusca.getSerie(), preco);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar uma coleção de E-Book em expecifíco")
	public String deletarE_BookSerie(@PathVariable("id") Long id) throws Exception {
		Colecao_E_Book ceBusca = Colecao_E_BookServ.getColecaoEBookById(id);
		if(ceBusca==null) {				
			return "Coleção de E-Book não achado";
		}else {
			return Colecao_E_BookServ.deletarE_BookSerie(ceBusca.getSerie());
		}
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Listar todas as coleções de E-Book")
	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return Colecao_E_BookServ.listarColecao_E_Books();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar uma coleção de E-Book em expecifíco")
	public Colecao_E_Book buscarColecao_E_Books(@PathVariable("id") Long id) throws Exception {
		return Colecao_E_BookServ.getColecaoEBookById(id);
	}
	
	@GetMapping("/{id}/e_books")
	@ResponseBody
	@Operation(summary = "Listar os E-Books em uma coleção de E-Book em expecifíco")
	public List<E_Book> listarE_Books(@PathVariable("id") Long id) throws Exception {
		return EBookServ.ListarEBookPeloIdColecao(id);
	}
	
	@PostMapping("/{id}/e_books")
	@ResponseBody
	@Operation(summary = "Adicionar um E-Book em uma coleção de E-Book em expecifíco")
	public String addE_Book(@PathVariable("id") Long id, String titulo) throws Exception {
		Colecao_E_Book ceBusca = Colecao_E_BookServ.getColecaoEBookById(id);
		if(ceBusca==null) {				
			return "Coleção de E-Book não achado";
		}else {
			return Colecao_E_BookServ.addE_Book(ceBusca.getSerie(), titulo);
		}
	}
	
	@DeleteMapping("/{id}/e_books")
	@ResponseBody
	@Operation(summary = "Deletar um E-Book uma coleção de E-Book em expecifíco")
	public String removeE_Book(@PathVariable("id") Long id, String titulo) throws Exception {
		Colecao_E_Book ceBusca = Colecao_E_BookServ.getColecaoEBookById(id);
		if(ceBusca==null) {				
			return "Coleção de E-Book não achado";
		}else {
			return Colecao_E_BookServ.removeE_Book(ceBusca.getSerie(), titulo);
		}
	}
}
