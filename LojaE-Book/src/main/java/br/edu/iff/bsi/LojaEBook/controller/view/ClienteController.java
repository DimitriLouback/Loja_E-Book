package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

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

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	public ClienteService clienteServ;
	@Autowired
	private CompraService CompraServ;

	@GetMapping("/CRUD")
	public String page() throws Exception {
		return "redirect:/cliente/CRUD/listarClientes";
	}

	@GetMapping("/CRUD/addForm")
	public String addClienteForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cliente_add", new Cliente());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/add")
	public String addCliente(@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {
			String resposta = clienteServ.addCliente(cliente);
			try {
				return  "redirect:/cliente/CRUD/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}			
		}
	}

	@GetMapping("/CRUD/listarClientes")
	public String listarClientes(Model model, HttpServletRequest request) throws Exception {
		String cpf = request.getParameter("cpf");
		if(cpf==null) {					
			model.addAttribute("cliente_lista", clienteServ.listarClientes());
		}else {
			Cliente busca = clienteServ.buscarClienteCPF(cpf);
			if(busca==null) {
				busca = new Cliente();
				busca.setCarteira(new Carteira());
			}
			model.addAttribute("cliente_lista", busca);
		}
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/buscaCPF")
	public String buscarClienteCPF(String cpf) throws Exception {
		return "redirect:/cliente/CRUD/listarClientes?cpf="+URLEncoder.encode(cpf, "UTF-8");
	}

	@GetMapping("/CRUD/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		Cliente cliente = clienteServ.getClienteById(id);
		model.addAttribute("cliente_edit", cliente);
		model.addAttribute("telefone_lista", clienteServ.ListarTelefonePeloCPF(cliente.getCpf()));
		return "CRUD_Cliente";
	}

	@PostMapping("/CRUD/atualizar")
	public String atualizarCliente(@Valid @ModelAttribute Cliente cliente ,BindingResult resultado, Model model) {
		String cpf = cliente.getCpf();
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		String senha = cliente.getSenha();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			clienteServ.atualizarCliente(cpf, nome, email, senha);
			return "redirect:/cliente/CRUD/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}

	@GetMapping("/CRUD/deletePorCPF")
	public String deletarClienteCPF(String cpf) throws Exception {
		clienteServ.deletarCliente(cpf);
		return "redirect:/cliente/CRUD/listarClientes";
	}

	@GetMapping("/CRUD/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		if(clienteServ.buscarClienteCPF(cpf).getQtdTelefones()>1) {			
			clienteServ.removeTelefone(cpf, telefone);
		}
		return "redirect:/cliente/CRUD/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
	}

	@PostMapping("/CRUD/addTelefone")
	public String addTelefone(@Valid @ModelAttribute Cliente cliente ,BindingResult resultado, Model model) {
		String cpf = cliente.getCpf();
		String telefone = cliente.getTelefone().get(0);
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			clienteServ.addTelefone(cpf, telefone);
			return "redirect:/cliente/CRUD/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}

	@PostMapping("/CRUD/addSaldo")
	public String adicionarSaldo(String cpf, double saldo) {
		clienteServ.adcionarSaldo(cpf, saldo);
		return "redirect:/cliente/CRUD/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
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
			return "redirect:/cliente/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}
	
	@GetMapping("/editarPerfil/removeTelefone")
	public String removeTelefonePerfil(String cpf, String telefone) throws Exception {
		if(clienteServ.buscarClienteCPF(cpf).getQtdTelefones()>1) {			
			clienteServ.removeTelefone(cpf, telefone);
		}
		return "redirect:/cliente/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
	}

	@PostMapping("/editarPerfil/addTelefone")
	public String addTelefonePerfil(@Valid @ModelAttribute Cliente cliente ,BindingResult resultado, Model model) {
		String cpf = cliente.getCpf();
		String telefone = cliente.getTelefone().get(0);
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			clienteServ.addTelefone(cpf, telefone);
			return "redirect:/cliente/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
		}
	}
	
	@PostMapping("/editarPerfil/addSaldo")
	public String adicionarSaldoPerfil(String cpf, double saldo) {
		clienteServ.adcionarSaldo(cpf, saldo);
		return "redirect:/cliente/editarPerfil/"+clienteServ.buscarClienteCPF(cpf).getId();
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
	public String addCadastro(@Valid @ModelAttribute Cliente cliente, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {
			String resposta = clienteServ.addCliente(cliente);
			try {
				return  "redirect:/cliente/cadastro?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}			
		}
	}

}