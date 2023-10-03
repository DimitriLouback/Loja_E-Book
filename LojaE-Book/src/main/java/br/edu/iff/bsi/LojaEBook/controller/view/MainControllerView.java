package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "")
public class MainControllerView {

	@Autowired
	private E_BookService EBookServ;
	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private CompraService CompraServ;
	@Autowired
	public ClienteService clienteServ;
	@Autowired
	public FuncionarioService FuncionarioServ;
	
	
	@GetMapping("")
	public String page(Model model, HttpServletRequest request, @AuthenticationPrincipal User user) throws Exception{
		if(user!=null) {
			String nome;
			if(user.getAuthorities().toString().compareTo("[ROLE_Cliente]")==0) {
				model.addAttribute("cliente", clienteServ.buscarClienteCPF(user.getUsername()));
				Compra compra = CompraServ.CompraAbertaPeloCPFCliente(user.getUsername());
				model.addAttribute("compraForaArrinho", compra);
				nome = clienteServ.buscarClienteCPF(user.getUsername()).getNome();
			}else {
				if(user.getUsername().compareTo("ADM")==0) {
					nome = "ADM";
				}else {
					nome = FuncionarioServ.buscarFuncionarioCPF(user.getUsername()).getNome();
				}
			}
			model.addAttribute("nome", nome);
			//Como uso o CPF como login, preciso disso acima para pegar o nome do usuario
		}
		
		//Abaixo é para mostrar os produtos na tela principal, se não houver nenhuma buaca, mostra todos
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
	

}
