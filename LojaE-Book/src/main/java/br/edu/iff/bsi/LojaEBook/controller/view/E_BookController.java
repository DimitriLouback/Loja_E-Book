package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
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
}
