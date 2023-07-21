package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.repository.Colecao_E_BookRepository;

@Service
public class Colecao_E_BookService {

	@Autowired
	private Colecao_E_BookRepository Colecao_E_BookRep;
	
	public Colecao_E_Book salvarColecao_E_Booko(Colecao_E_Book colecao_e_book) {
		return Colecao_E_BookRep.save(colecao_e_book);
	}
	
	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return Colecao_E_BookRep.findAll();
	}
	
	public Colecao_E_Book buscarPelaSerie(String serie) {
		return Colecao_E_BookRep.buscarPelaSerie(serie);
	}
	
	public String buscarTituloPeloIdColecao(Long id, String titulo) {
		return Colecao_E_BookRep.buscarTituloPeloIdColecao(id, titulo);
	}
	
	public void flush() {
		Colecao_E_BookRep.flush();
	}
	
	public void deletarColecaoEBook(Colecao_E_Book colecao_e_book) {
		Colecao_E_BookRep.delete(colecao_e_book);
	}
	
}
