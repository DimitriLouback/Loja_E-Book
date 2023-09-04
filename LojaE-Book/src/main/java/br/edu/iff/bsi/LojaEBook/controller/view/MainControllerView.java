package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "")
public class MainControllerView {

	@Autowired
	private E_BookService EBookServ;
	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	
	@GetMapping("")
	public String page(Model model, HttpServletRequest request) throws Exception{
		String produto = request.getParameter("produto");
		if(produto==null) {			
			model.addAttribute("e_book_lista", EBookServ.listarE_Books());
			model.addAttribute("colecao_e_book_lista", Colecao_E_BookServ.listarColecao_E_Books());
		}else {
			Colecao_E_Book cebusca = Colecao_E_BookServ.buscarColecao_E_Books(URLDecoder.decode(produto, "UTF-8"));
			model.addAttribute("colecao_e_book_lista", cebusca);
			E_Book ebusca = EBookServ.buscarE_Books(URLDecoder.decode(produto, "UTF-8"));
			model.addAttribute("e_book_lista", ebusca);
		}
		return "index";
	}
	
	@PostMapping("/buscarProduto")
	public String buscarProduto(String produto) throws UnsupportedEncodingException {
		return "redirect:/?produto="+URLEncoder.encode(produto, "UTF-8");
	}
	
	
	@GetMapping("/CRUDs")
	public String cruds(){
		return "CRUD_Main";
	}

	
	
	/*
	@PostMapping("/new/pessoa")
	@ResponseStatus(HttpStatus.CREATED)
	public String registerPessoa(@ModelAttribute Pessoa pessoa){
		System.out.println("ID pessoa: "+pessoa.getId());
		System.out.println("Nome pessoa: "+pessoa.getNome());
		System.out.println("Email pessoa: "+pessoa.getEmail());
		System.out.println("CPF pessoa: "+pessoa.getCpf());
		return"sucesso";
	}
	*/
}
