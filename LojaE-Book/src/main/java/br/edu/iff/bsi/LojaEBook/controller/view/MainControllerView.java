package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;
import br.edu.iff.bsi.LojaEBook.service.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

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
		
		String produto = request.getParameter("produto");
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
		}
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
	
	@GetMapping("/carrinho/{id}")
	public String carrinho(@PathVariable("id") Long id, Model model, HttpServletRequest request) throws Exception{
		Compra compra = CompraServ.CompraAbertaPeloCPFCliente(clienteServ.buscarPeloID(id).getCpf());
		List<E_Book> e_books = CompraServ.ListarEBookPeloIdCompra(compra.getId());
		List<Colecao_E_Book> colecoes_e_book = CompraServ.ListarColecaoEBookPeloIdCompra(compra.getId());
		model.addAttribute("compra", compra);
		model.addAttribute("saldo", clienteServ.buscarPeloID(id).verSaldo());
		model.addAttribute("e_book_lista", e_books);
		model.addAttribute("colecao_e_book_lista", colecoes_e_book);
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
	        model.addAttribute("respostaFinalizar", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "carrinho";
	}

	@GetMapping("/carrinho/addE_BookCarrinho")
	public String addE_Book(String id, String titulo) throws Exception {
		CompraServ.addE_Book(id, titulo);
		return "redirect:/";
	}
	
	@GetMapping("/carrinho/removeE_BookCarrinho")
	public String removeE_Book(String id, String titulo) throws Exception {
		CompraServ.removeE_Book(id, titulo);
		return "redirect:/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(Long.parseLong(id)).getCpfCliente()).getId();
	}
	
	@GetMapping("/carrinho/addColecaoE_BookCarrinho")
	public String addColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.addColecaoE_Book(id, serie);
		return "redirect:/";
	}
	
	@GetMapping("/carrinho/removeColecaoE_BookCarrinho")
	public String removeColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.removeColecaoE_Book(id, serie);
		return "redirect:/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(Long.parseLong(id)).getCpfCliente()).getId();
	}
	
	@GetMapping("/carrinho/fecharCarrinho")
	public String finalizarCompra(Long id) throws Exception {
		String resposta = CompraServ.finalizarCompraPeloId(id);
		System.out.println(resposta);
		if(resposta.compareTo("Compra finalizada com sucesso")==0) {
			return "redirect:/";
		}
		//return "redirect:/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(id).getCpfCliente()).getId();
		return "redirect:/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(id).getCpfCliente()).getId()+"?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
	
	@GetMapping("/editarPerfil/{id}")
	public String editarPerfil(@PathVariable("id") Long id, Model model) throws Exception {
		Cliente cliente = clienteServ.getClienteById(id);
		model.addAttribute("cliente_edit", cliente);
		model.addAttribute("telefone_lista", clienteServ.ListarTelefonePeloCPF(cliente.getCpf()));
		model.addAttribute("compraFechada_lista", CompraServ.ListarFechadasPeloCPFCliente(cliente.getCpf()));
		return "editarPerfil";
	}
	
	@PostMapping("/editarPerfil/atualizarValoresPerfil")
	public String atualizarValoresPerfil(@Valid @ModelAttribute Cliente cliente ,BindingResult resultado, Model model) {
		String cpf = cliente.getCpf();
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		String senha = cliente.getSenha();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			clienteServ.atualizarCliente(cpf, nome, email, senha);
			return "redirect:/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}
	
	@GetMapping("/editarPerfil/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		if(clienteServ.buscarClienteCPF(cpf).getQtdTelefones()>1) {			
			clienteServ.removeTelefone(cpf, telefone);
		}
		return "redirect:/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
	}

	@PostMapping("/editarPerfil/addTelefone")
	public String addTelefone(@Valid @ModelAttribute Cliente cliente ,BindingResult resultado, Model model) {
		String cpf = cliente.getCpf();
		String telefone = cliente.getTelefone().get(0);
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			clienteServ.addTelefone(cpf, telefone);
			return "redirect:/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}
	
	@PostMapping("/editarPerfil/addSaldo")
	public String adicionarSaldo(String cpf, double saldo) {
		clienteServ.adcionarSaldo(cpf, saldo);
		return "redirect:/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
	}
	
	@GetMapping("/cadastro")
	public String cadastroForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cliente_add", new Cliente());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "cadastro";
	}
	
	@PostMapping("/cadastro/add")
	public String addCliente(@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {
			String resposta = clienteServ.addCliente(cliente);
			try {
				return  "redirect:/cadastro?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}			
		}
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
