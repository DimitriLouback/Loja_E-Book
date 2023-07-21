package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.repository.E_BookRepository;

@Service
public class E_BookService {

	@Autowired
	private E_BookRepository EBookRep;
	
	public E_Book salvarE_Book(E_Book e_book) {
		return EBookRep.save(e_book);
	}
	
	public List<E_Book> listarE_Books() throws Exception {
		return EBookRep.findAll();
	}
	
	public E_Book buscarPeloTitulo(String titulo) {
		return EBookRep.buscarPeloTitulo(titulo);
	}

	public List<E_Book> ListarEBookPeloIdColecao(Long id) {
		return EBookRep.ListarEBookPeloIdColecao(id);
	}
	
	public void flush() {
		EBookRep.flush();
	}
	
	public void deletarEBook(E_Book e_book) {
		EBookRep.delete(e_book);
	}
}
