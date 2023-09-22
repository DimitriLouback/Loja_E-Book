package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("compra")
public class CompraController {

	@Autowired
	private CompraService CompraServ;
	@Autowired
	public ClienteService clienteServ;
	
	@GetMapping("/CRUD")
	public String page() throws Exception {
		return "redirect:/compra/CRUD/listarCompras";
	}

	@GetMapping("/CRUD/addForm")
	public String addCompraForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("compra_add", new Compra());
		model.addAttribute("cliente_lista", clienteServ.listarClientes());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Compra";
	}
	
	@PostMapping("/CRUD/add")
	public String addCompra(@Valid @ModelAttribute Compra compra, BindingResult resultado, Model model) {
		String clienteEscolhido = compra.getCpfCliente();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			String resposta = CompraServ.addCompra(clienteEscolhido);
			try {
				return "redirect:/compra/CRUD/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
		}
	}

	@GetMapping("/CRUD/listarCompras")
	public String listarCompras(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		String resposta = request.getParameter("resposta");
		if(cpf==null) {			
			model.addAttribute("compra_lista", CompraServ.listarCompras());
		}else {
			List<Compra> busca = CompraServ.buscarPeloCPFCliente(URLDecoder.decode(cpf, "UTF-8"));
			if(busca==null) {
				model.addAttribute("compra_lista", new Compra());
			}else {			
				model.addAttribute("compra_lista", busca);
			}
		}
		if (resposta != null) {
	        model.addAttribute("respostaFinalizar", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Compra";
	}
	
	@PostMapping("/CRUD/buscaCompra")
	public String buscarCompra(String cpf) throws Exception {
		return "redirect:/compra/CRUD/listarCompras?cpf="+URLEncoder.encode(cpf, "UTF-8");
	}
	
	@GetMapping("/CRUD/editar")
	public String formEditar(Long id, Model model) throws Exception {
		model.addAttribute("compra_edit", CompraServ.getCompraById(id));
		model.addAttribute("cliente_lista", clienteServ.listarClientes());
		model.addAttribute("e_book_lista", CompraServ.ListarEBookPeloIdCompra(id));
		model.addAttribute("colecao_e_book_lista", CompraServ.ListarColecaoEBookPeloIdCompra(id));
		return "CRUD_Compra";
	}
	
	@PostMapping("/CRUD/atualizar")
	public String atualizarCompra(Long id, @Valid @ModelAttribute Compra compra, BindingResult resultado, Model model) {
		String clienteEscolhido = compra.getCpfCliente();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {						
			CompraServ.atualizarCompra(id, clienteEscolhido);
			return "redirect:/compra/CRUD/editar?id="+id;
		}
	}
	
	@GetMapping("/CRUD/deletar")
	public String deletarCompra(@RequestParam Long id) throws Exception {
		CompraServ.deletarCompra(id);
		return "redirect:/compra/CRUD/listarCompras";
	}
	
	@PostMapping("/CRUD/addE_Book")
	public String addE_Book(String id, String titulo) throws Exception {
		CompraServ.addE_Book(id, titulo);
		return "redirect:/compra/CRUD/editar?id="+id;
	}
	
	@GetMapping("/CRUD/removeE_Book")
	public String removeE_Book(String id, String titulo) throws Exception {
		CompraServ.removeE_Book(id, titulo);
		return "redirect:/compra/CRUD/editar?id="+id;
	}
	
	@PostMapping("/CRUD/addColecaoE_Book")
	public String addColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.addColecaoE_Book(id, serie);
		return "redirect:/compra/CRUD/editar?id="+id;
	}
	
	@GetMapping("/CRUD/removeColecaoE_Book")
	public String removeColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.removeColecaoE_Book(id, serie);
		return "redirect:/compra/CRUD/editar?id="+id;
	}
	
	@GetMapping("/CRUD/finalizar")
	public String finalizarCompra(Long id) throws Exception {
		String resposta = CompraServ.finalizarCompraPeloId(id);
		return "redirect:/compra/CRUD/listarCompras?resposta=" + URLEncoder.encode(resposta, "UTF-8");
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
	public String addE_BookCarrinho(String id, String titulo) throws Exception {
		CompraServ.addE_Book(id, titulo);
		return "redirect:/";
	}
	
	@GetMapping("/carrinho/removeE_BookCarrinho")
	public String removeE_BookCarrinho(String id, String titulo) throws Exception {
		CompraServ.removeE_Book(id, titulo);
		return "redirect:/compra/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(Long.parseLong(id)).getCpfCliente()).getId();
	}
	
	@GetMapping("/carrinho/addColecaoE_BookCarrinho")
	public String addColecaoE_BookCarrinho(String id, String serie) throws Exception {
		CompraServ.addColecaoE_Book(id, serie);
		return "redirect:/";
	}
	
	@GetMapping("/carrinho/removeColecaoE_BookCarrinho")
	public String removeColecaoE_BookCarrinho(String id, String serie) throws Exception {
		CompraServ.removeColecaoE_Book(id, serie);
		return "redirect:/compra/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(Long.parseLong(id)).getCpfCliente()).getId();
	}
	
	@GetMapping("/carrinho/fecharCarrinho")
	public String finalizarCompraCarrinho(Long id) throws Exception {
		String resposta = CompraServ.finalizarCompraPeloId(id);
		System.out.println(resposta);
		if(resposta.compareTo("Compra finalizada com sucesso")==0) {
			return "redirect:/";
		}
		//return "redirect:/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(id).getCpfCliente()).getId();
		return "redirect:/compra/carrinho/"+clienteServ.buscarClienteCPF(CompraServ.getCompraById(id).getCpfCliente()).getId()+"?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
	
}
