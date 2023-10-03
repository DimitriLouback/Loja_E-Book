package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.repository.Colecao_E_BookRepository;
import br.edu.iff.bsi.LojaEBook.repository.CompraRepository;
import br.edu.iff.bsi.LojaEBook.repository.E_BookRepository;

@Service
public class E_BookService {

	@Autowired
	private E_BookRepository EBookRep;
	@Autowired
	private Colecao_E_BookRepository Colecao_E_BookRep;
	@Autowired
	private CompraRepository CompraRep;

	public String addE_Book(E_Book e_book) {
		if(EBookRep.buscarPeloTitulo(e_book.getTitulo())!=null) {
			return "E-Book já cadastrado";
		}else{
			E_Book e = EBookRep.save(e_book);
			return "Registrado no id "+e.getId();
		}
	}

	public String atualizarE_Book(String titulo, double preco, String genero, String autor, String editora, int qtdPaginas) {
		E_Book e = EBookRep.buscarPeloTitulo(titulo);
		if(e==null) {
			return "E-Book não achado";
		}else {		
			if(preco>=0) {
				double diferencaPreco = e.getPreco() - preco;
				List<Compra> compras = CompraRep.BuscarComprasPeloIdProduto(e.getId());
				for(int i=0;i<compras.size();i++) {
					compras.get(i).setPrecoFinal(compras.get(i).getPrecoFinal()-diferencaPreco);
				}
				e.setPreco(preco);
			}
			if(qtdPaginas>=0) {				
				e.setQtdPaginas(qtdPaginas);
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
			List<Colecao_E_Book> colebook = Colecao_E_BookRep.ListarColecaoEBookPeloIdEBook(e.getId());
			for(int i=0;i<colebook.size();i++) {
				colebook.get(i).removerEBook(e);
			}
			List<Compra> compras = CompraRep.BuscarComprasPeloIdProduto(e.getId());
			for(int i=0;i<compras.size();i++) {
				compras.get(i).removerProduto(e);
			}			
			EBookRep.delete(e);
			return "E-Book deletado no id "+e.getId();				
		}else {
			return "E-Book não encontrado";
		}
	}

	public List<E_Book> listarE_Books() throws Exception {
		return EBookRep.findAll();
	}

	public E_Book buscarE_Books(String titulo) {
		return EBookRep.buscarPeloTitulo(titulo);
	}

	public List<E_Book> ListarEBookPeloIdColecao(Long id) {
		return EBookRep.ListarEBookPeloIdColecao(id);
	}

	public E_Book getEBookById(Long id) {
		return EBookRep.BuscarPeloId(id);
	}

}
