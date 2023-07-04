package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.repository.Colecao_E_BookRepository;

@RestController
@RequestMapping("colecao-e-book")
public class Colecao_E_BookController {

	@Autowired
	private Colecao_E_BookRepository res;
	
	@PostMapping("/")
	public String addColecao_E_Book(Colecao_E_Book colecao_e_book) throws Exception {
		Colecao_E_Book ce = res.save(colecao_e_book);
		return "Coleção de E-Book added -->"+ce.getId()+"-->";
	}
}
