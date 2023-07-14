package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.repository.E_BookRepository;

@RestController
@RequestMapping("e-book")
public class E_BookController {

	@Autowired
	private E_BookRepository res;
	
	@PostMapping("/")
	public String addE_Book(E_Book e_book) throws Exception {
		E_Book e = res.save(e_book);
		return "E-Book added -->"+e.getId()+"-->";
	}
	
	@PostMapping("/listarE_Books")
	public List<E_Book> listarE_Books() throws Exception {
		return res.findAll();
	}
	
	@PostMapping("/buscaTitulo")
	public String buscarE_Books(String titulo) throws Exception {
		E_Book e = res.buscarPeloTitulo(titulo);
		if(e!=null) {			
			return "Id do E-Book: "+e.getId();
		}else {
			return "E-Book não encontrado";
		}
	}
}
