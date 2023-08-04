package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;

@RestController
@RequestMapping(path= "/api/v1/cliente")
public class ClienteRestController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@PostMapping("/{id}")
	@ResponseBody
	public String addCliente(@PathVariable("id") Long id, @ModelAttribute Cliente cliente) throws Exception {
		Cliente cBusca = clienteServ.getClienteById(id);
		if(cBusca==null) {				
			return clienteServ.addCliente(cliente);
		}else {
			return "Cliente já adicionado";
		}
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public String atualizarCliente(@PathVariable("id") Long id, String nome, String email, String senha) throws Exception {
		Cliente cBusca = clienteServ.getClienteById(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {
			return clienteServ.atualizarCliente(cBusca.getCpf(), nome, email, senha);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletarClienteCPF(@PathVariable("id") Long id) throws Exception {
		Cliente cBusca = clienteServ.getClienteById(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {			
			return clienteServ.deletarCliente(cBusca.getCpf());
		}
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Cliente> listarClientes() throws Exception {
		return clienteServ.listarClientes();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Cliente buscarClienteId(@PathVariable("id") Long id) throws Exception {
		return clienteServ.getClienteById(id);
	}
	
	@GetMapping("/{id}/telefones")
	@ResponseBody
	public List<String> listarTelefones(@PathVariable("id") Long id) throws Exception {
		return clienteServ.ListarTelefonePeloCPF(clienteServ.buscarPeloID(id).getCpf());
	}
	
	@PostMapping("/{id}/telefones")
	@ResponseBody
	public String addTelefone(@PathVariable("id") Long id, String telefone) throws Exception {
		Cliente cBusca = clienteServ.getClienteById(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {			
			return clienteServ.addTelefone(cBusca.getCpf(), telefone);
		}
	}
	
	@DeleteMapping("/{id}/telefones")
	@ResponseBody
	public String removeTelefone(@PathVariable("id") Long id, String telefone) throws Exception {
		Cliente cBusca = clienteServ.getClienteById(id);
		if(cBusca==null) {			
			return "Cliente não achado";
		}else {			
			return clienteServ.removeTelefone(cBusca.getCpf(), telefone);
		}
	}
	
	@PostMapping("/{id}/carteira")
	@ResponseBody
	public String adicionarSaldo(@PathVariable("id") Long id, String saldo) throws Exception {
		return clienteServ.adcionarSaldo(clienteServ.buscarPeloID(id).getCpf(), saldo);
	}
	
	@GetMapping("/{id}/carteira")
	@ResponseBody
	public double verSaldo(@PathVariable("id") Long id) throws Exception {
		return clienteServ.getSaldo(clienteServ.buscarPeloID(id).getCpf());
	}
	
}