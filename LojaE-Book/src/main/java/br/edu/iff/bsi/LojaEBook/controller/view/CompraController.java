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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.bsi.LojaEBook.model.Compra;
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
	
	@GetMapping("")
	public String page() throws Exception {
		return "redirect:/compra/listarCompras";
	}

	@GetMapping("/addForm")
	public String addCompraForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("compra_add", new Compra());
		model.addAttribute("cliente_lista", clienteServ.listarClientes());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Compra";
	}
	
	@PostMapping("/add")
	public String addCompra(@Valid @ModelAttribute Compra compra, BindingResult resultado, Model model) {
		String clienteEscolhido = compra.getCpfCliente();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "CRUD_Main";
		}else {			
			String resposta = CompraServ.addCompra(clienteEscolhido);
			try {
				return "redirect:/compra/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "CRUD_Main";
			}
		}
	}

	@GetMapping("/listarCompras")
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
	
	@PostMapping("/buscaCompra")
	public String buscarCompra(String cpf) throws Exception {
		return "redirect:/compra/listarCompras?cpf="+URLEncoder.encode(cpf, "UTF-8");
	}
	
	@GetMapping("/editar")
	public String formEditar(Long id, Model model) throws Exception {
		model.addAttribute("compra_edit", CompraServ.getCompraById(id));
		model.addAttribute("cliente_lista", clienteServ.listarClientes());
		model.addAttribute("e_book_lista", CompraServ.ListarEBookPeloIdCompra(id));
		model.addAttribute("colecao_e_book_lista", CompraServ.ListarColecaoEBookPeloIdCompra(id));
		return "CRUD_Compra";
	}
	
	@PostMapping("/atualizar")
	public String atualizarCompra(Long id, @Valid @ModelAttribute Compra compra, BindingResult resultado, Model model) {
		String clienteEscolhido = compra.getCpfCliente();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "CRUD_Main";
		}else {						
			CompraServ.atualizarCompra(id, clienteEscolhido);
			return "redirect:/compra/editar?id="+id;
		}
	}
	
	@GetMapping("/deletar")
	public String deletarCompra(@RequestParam Long id) throws Exception {
		CompraServ.deletarCompra(id);
		return "redirect:/compra/listarCompras";
	}
	
	@PostMapping("/addE_Book")
	public String addE_Book(String id, String titulo) throws Exception {
		CompraServ.addE_Book(id, titulo);
		return "redirect:/compra/editar?id="+id;
	}
	
	@GetMapping("/removeE_Book")
	public String removeE_Book(String id, String titulo) throws Exception {
		CompraServ.removeE_Book(id, titulo);
		return "redirect:/compra/editar?id="+id;
	}
	
	@PostMapping("/addColecaoE_Book")
	public String addColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.addColecaoE_Book(id, serie);
		return "redirect:/compra/editar?id="+id;
	}
	
	@GetMapping("/removeColecaoE_Book")
	public String removeColecaoE_Book(String id, String serie) throws Exception {
		CompraServ.removeColecaoE_Book(id, serie);
		return "redirect:/compra/editar?id="+id;
	}
	
	@GetMapping("/finalizar")
	public String finalizarCompra(Long id) throws Exception {
		String resposta = CompraServ.finalizarCompraPeloId(id);
		return "redirect:/compra/listarCompras?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
}
