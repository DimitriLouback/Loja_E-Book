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
public class Colecao_E_BookService {

	@Autowired
	private Colecao_E_BookRepository Colecao_E_BookRep;
	@Autowired
	private E_BookRepository E_BookRep;
	@Autowired
	private CompraRepository CompraRep;

	public String addColecao_E_Book(Colecao_E_Book colecao_e_book) {
		if(Colecao_E_BookRep.buscarPelaSerie(colecao_e_book.getSerie())!=null) {
			return "Coleção de E-Book já cadastrado";
		}else{
			Colecao_E_Book ce = Colecao_E_BookRep.save(colecao_e_book);
			return "Registrado no id "+ce.getId();
		}
	}

	public String atualizarE_Book(String serie, double preco) {
		Colecao_E_Book ce = Colecao_E_BookRep.buscarPelaSerie(serie);
		if(ce==null) {
			return "Coleçao de E-Book não achado";
		}else {		
			if(preco>=0) {
				double diferencaPreco = ce.getPreco() - preco;
				List<Compra> compras = CompraRep.BuscarComprasPeloIdProduto(ce.getId());
				for(int i=0;i<compras.size();i++) {
					compras.get(i).setPrecoFinal(compras.get(i).getPrecoFinal()-diferencaPreco);
				}
				ce.setPreco(preco);
			}
			Colecao_E_BookRep.flush();
			return "Atualizado no id "+ce.getId();
		}
	}

	public String deletarE_BookSerie(String serie) {
		Colecao_E_Book ce = Colecao_E_BookRep.buscarPelaSerie(serie);
		if(ce!=null) {
			List<Compra> compras = CompraRep.BuscarComprasPeloIdProduto(ce.getId());
			for(int i=0;i<compras.size();i++) {
				compras.get(i).removerProduto(ce);
			}
			Colecao_E_BookRep.delete(ce);
			return "Coleçao de E-Book deletado no id "+ce.getId();				
		}else {
			return "Coleçao de E-Book não encontrado";
		}
	}

	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return Colecao_E_BookRep.findAll();
	}

	public Colecao_E_Book buscarColecao_E_Books(String serie) {
		return Colecao_E_BookRep.buscarPelaSerie(serie);
	}

	public Colecao_E_Book buscarPelaSerie(String serie) {
		return Colecao_E_BookRep.buscarPelaSerie(serie);
	}


	public String addE_Book(String serie, String titulo) {
		Colecao_E_Book c = Colecao_E_BookRep.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = E_BookRep.buscarPeloTitulo(Colecao_E_BookRep.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e!=null) {
				return "E-Book já cadastrado";
			}else {
				c.adicionarEBook(E_BookRep.buscarPeloTitulo(titulo));
				Colecao_E_BookRep.flush();
				return "E-Book adicionado";
			}
		}
	}

	public String removeE_Book(String serie, String titulo) {
		Colecao_E_Book c = Colecao_E_BookRep.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = E_BookRep.buscarPeloTitulo(Colecao_E_BookRep.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e==null) {
				return "E-Book não cadastrado";
			}else {
				c.removerEBook(E_BookRep.buscarPeloTitulo(titulo));
				Colecao_E_BookRep.flush();
				return "E-Book removido";
			}
		}
	}

	public Colecao_E_Book getColecaoEBookById(Long id) {
		return Colecao_E_BookRep.BuscarPeloId(id);
	}

}
