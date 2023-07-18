package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.repository.Colecao_E_BookRepository;
import br.edu.iff.bsi.LojaEBook.repository.E_BookRepository;

@RestController
@RequestMapping("colecao-e-book")
public class Colecao_E_BookController {

	@Autowired
	private Colecao_E_BookRepository res;
	@Autowired
	private E_BookRepository EBookRep;
	
	@PostMapping("/")
	public String addColecao_E_Book(Colecao_E_Book colecao_e_book) throws Exception {
		Colecao_E_Book ce = res.save(colecao_e_book);
		return "Coleção de E-Book added -->"+ce.getId()+"-->";
	}
	
	@PostMapping("/listarColecao_E_Books")
	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return res.findAll();
	}
	
	@PostMapping("/buscaSerie")
	public String buscarColecao_E_Books(String serie) throws Exception {
		Colecao_E_Book c = res.buscarPelaSerie(serie);
		if(c!=null) {			
			return "Id da Coleão de E-Book: "+c.getId();
		}else {
			return "Coleção de E-Book não encontrada";
		}
	}
	
	@PostMapping("/listarE_Books")
	public List<E_Book> listarE_Books(String serie) throws Exception {
		return EBookRep.ListarEBookPeloIdColecao(res.buscarPelaSerie(serie).getId());
	}
	
	@PostMapping("/addE_Book")
	public String addE_Book(String serie, String titulo) throws Exception {
		Colecao_E_Book c = res.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = EBookRep.buscarPeloTitulo(res.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e!=null) {
				return "E-Book já cadastrado";
			}else {
				c.adicionarEBook(EBookRep.buscarPeloTitulo(titulo));
				res.flush();
				return "E-Book adicionado";
			}
		}
	}
	
	@PostMapping("/removeE_Book")
	public String removeE_Book(String serie, String titulo) throws Exception {
		Colecao_E_Book c = res.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = EBookRep.buscarPeloTitulo(res.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e==null) {
				return "E-Book não cadastrado";
			}else {
				c.removerEBook(EBookRep.buscarPeloTitulo(titulo));
				res.flush();
				return "E-Book removido";
			}
		}
	}
}
