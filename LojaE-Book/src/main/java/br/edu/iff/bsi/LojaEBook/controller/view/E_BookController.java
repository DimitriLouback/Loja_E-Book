package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@RestController
@RequestMapping("e-book")
public class E_BookController {

	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/")
	public String addE_Book(E_Book e_book) throws Exception {
		if(EBookServ.buscarPeloTitulo(e_book.getTitulo())!=null) {
			return "E-Book já cadastrado";
		}else{
			E_Book e = EBookServ.salvarE_Book(e_book);
			return "Registrado no id "+e.getId();
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarE_Book(String titulo, String preco, String genero, String autor, String editora, String qtdPaginas) throws Exception {
		E_Book e = EBookServ.buscarPeloTitulo(titulo);
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
			EBookServ.flush();
			return "Atualizado no id "+e.getId();
		}
	}
	
	@PostMapping("/deletaPorTitulo")
	public String deletarE_BookTitulo(String titulo) throws Exception {
		E_Book e = EBookServ.buscarPeloTitulo(titulo);
		if(e!=null) {	
			EBookServ.deletarEBook(e);
			return "E-Book deletado no id "+e.getId();
		}else {
			return "E-Book não encontrado";
		}
	}
	
	@PostMapping("/listarE_Books")
	public List<E_Book> listarE_Books() throws Exception {
		return EBookServ.listarE_Books();
	}
	
	@PostMapping("/buscaTitulo")
	public String buscarE_Books(String titulo) throws Exception {
		E_Book e = EBookServ.buscarPeloTitulo(titulo);
		if(e!=null) {			
			return "Id do E-Book: "+e.getId();
		}else {
			return "E-Book não encontrado";
		}
	}
}
