package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@RestController
@RequestMapping("colecao-e-book")
public class Colecao_E_BookController {

	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/")
	public String addColecao_E_Book(Colecao_E_Book colecao_e_book) throws Exception {
		if(Colecao_E_BookServ.buscarPelaSerie(colecao_e_book.getSerie())!=null) {
			return "Coleção de E-Book já cadastrado";
		}else{
			Colecao_E_Book ce = Colecao_E_BookServ.salvarColecao_E_Booko(colecao_e_book);
			return "Registrado no id "+ce.getId();
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarE_Book(String serie, String preco) throws Exception {
		Colecao_E_Book ce = Colecao_E_BookServ.buscarPelaSerie(serie);
		if(ce==null) {
			return "Coleçao de E-Book não achado";
		}else {		
			if(preco!=null&&Double.parseDouble(preco)>0) {
				ce.setPreco(Double.parseDouble(preco));
			}
			Colecao_E_BookServ.flush();
			return "Atualizado no id "+ce.getId();
		}
	}
	
	@PostMapping("/deletarPorSerie")
	public String deletarE_BookSerie(String serie) throws Exception {
		Colecao_E_Book ce = Colecao_E_BookServ.buscarPelaSerie(serie);
		if(ce!=null) {	
			Colecao_E_BookServ.deletarColecaoEBook(ce);
			return "Coleçao de E-Book deletado no id "+ce.getId();
		}else {
			return "Coleçao de E-Book não encontrado";
		}
	}
	
	@PostMapping("/listarColecao_E_Books")
	public List<Colecao_E_Book> listarColecao_E_Books() throws Exception {
		return Colecao_E_BookServ.listarColecao_E_Books();
	}
	
	@PostMapping("/buscaSerie")
	public String buscarColecao_E_Books(String serie) throws Exception {
		Colecao_E_Book c = Colecao_E_BookServ.buscarPelaSerie(serie);
		if(c!=null) {			
			return "Id da Coleão de E-Book: "+c.getId();
		}else {
			return "Coleção de E-Book não encontrada";
		}
	}
	
	@PostMapping("/listarE_Books")
	public List<E_Book> listarE_Books(String serie) throws Exception {
		return EBookServ.ListarEBookPeloIdColecao(Colecao_E_BookServ.buscarPelaSerie(serie).getId());
	}
	
	@PostMapping("/addE_Book")
	public String addE_Book(String serie, String titulo) throws Exception {
		Colecao_E_Book c = Colecao_E_BookServ.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = EBookServ.buscarPeloTitulo(Colecao_E_BookServ.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e!=null) {
				return "E-Book já cadastrado";
			}else {
				c.adicionarEBook(EBookServ.buscarPeloTitulo(titulo));
				Colecao_E_BookServ.flush();
				return "E-Book adicionado";
			}
		}
	}
	
	@PostMapping("/removeE_Book")
	public String removeE_Book(String serie, String titulo) throws Exception {
		Colecao_E_Book c = Colecao_E_BookServ.buscarPelaSerie(serie);
		if(c==null) {
			return "Coleção não encontrada";
		}else {			
			E_Book e = EBookServ.buscarPeloTitulo(Colecao_E_BookServ.buscarTituloPeloIdColecao(c.getId(), titulo));
			if(e==null) {
				return "E-Book não cadastrado";
			}else {
				c.removerEBook(EBookServ.buscarPeloTitulo(titulo));
				Colecao_E_BookServ.flush();
				return "E-Book removido";
			}
		}
	}
}
