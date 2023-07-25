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
	
	public String addE_Book(E_Book e_book) {
		if(EBookRep.buscarPeloTitulo(e_book.getTitulo())!=null) {
			return "E-Book já cadastrado";
		}else{
			E_Book e = EBookRep.save(e_book);
			return "Registrado no id "+e.getId();
		}
	}
	
	public String atualizarE_Book(String titulo, String preco, String genero, String autor, String editora, String qtdPaginas) {
		E_Book e = EBookRep.buscarPeloTitulo(titulo);
		if(e==null) {
			return "E-Book não achado";
		}else {		
			if(preco!=null&&Double.parseDouble(preco)>0) {
				e.setPreco(Double.parseDouble(preco));
			}
			if(qtdPaginas!=null&&Integer.parseInt(qtdPaginas)>0) {				
				e.setQtdPaginas(Integer.parseInt(qtdPaginas));
			}
			if(genero!=null) {				
				e.setGenero(genero);
			}
			if(autor!=null) {				
				e.setAutor(autor);
			}
			if(editora!=null) {				
				e.setEditora(editora);
			}
			EBookRep.flush();
			return "Atualizado no id "+e.getId();
		}
	}
	
	public String deletarE_BookTitulo(String titulo) {
		E_Book e = EBookRep.buscarPeloTitulo(titulo);
		if(e!=null) {	
			EBookRep.delete(e);
			return "E-Book deletado no id "+e.getId();
		}else {
			return "E-Book não encontrado";
		}
	}

	public List<E_Book> listarE_Books() throws Exception {
		return EBookRep.findAll();
	}
	
	public String buscarE_Books(String titulo) {
		E_Book e = EBookRep.buscarPeloTitulo(titulo);
		if(e!=null) {			
			return "Id do E-Book: "+e.getId();
		}else {
			return "E-Book não encontrado";
		}
	}

	public List<E_Book> ListarEBookPeloIdColecao(Long id) {
		return EBookRep.ListarEBookPeloIdColecao(id);
	}
	
}
