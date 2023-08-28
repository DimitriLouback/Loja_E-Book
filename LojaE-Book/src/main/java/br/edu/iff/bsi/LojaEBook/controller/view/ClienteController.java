package br.edu.iff.bsi.LojaEBook.controller.view;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@GetMapping("")
	public String page() throws Exception {
		return "CRUD_Cliente";
	}
	
	@GetMapping("/addForm")
	public String addClienteForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cliente_add", new Cliente());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Cliente";
	}
	
	@PostMapping("/add")
	public String addCliente(@ModelAttribute Cliente cliente) throws Exception {
		String resposta = clienteServ.addCliente(cliente);
		return  "redirect:/cliente/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
	
	@GetMapping("/listarClientes")
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
	
	@PostMapping("/buscaCPF")
	public String buscarClienteCPF(String cpf) throws Exception {
		return "redirect:/cliente/listarClientes?cpf="+URLEncoder.encode(cpf, "UTF-8");
	}
	
	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		Cliente cliente = clienteServ.getClienteById(id);
		model.addAttribute("cliente_edit", cliente);
		model.addAttribute("telefone_lista", clienteServ.ListarTelefonePeloCPF(cliente.getCpf()));
		return "CRUD_Cliente";
	}
	
	@PostMapping("/atualizar")
	public String atualizarCliente(String cpf, String nome, String email, String senha) throws Exception {
		clienteServ.atualizarCliente(cpf, nome, email, senha);
		return "redirect:/cliente/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
	}
	
	@GetMapping("/deletePorCPF")
	public String deletarClienteCPF(String cpf) throws Exception {
		clienteServ.deletarCliente(cpf);
		return "redirect:/cliente/listarClientes";
	}
	
	@GetMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		if(clienteServ.buscarClienteCPF(cpf).getQtdTelefones()>1) {			
			clienteServ.removeTelefone(cpf, telefone);
		}
		return "redirect:/cliente/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
	}
	
	@PostMapping("/addTelefone")
	public String addTelefone(String cpf, String telefone) throws Exception {
		clienteServ.addTelefone(cpf, telefone);
		return "redirect:/cliente/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
	}
	
	@PostMapping("/addSaldo")
	public String adicionarSaldo(String cpf, String saldo) throws Exception {
		clienteServ.adcionarSaldo(cpf, saldo);
		return "redirect:/cliente/editar?id="+clienteServ.buscarClienteCPF(cpf).getId();
	}
	
}