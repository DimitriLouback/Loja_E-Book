package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/cliente")
public class ClienteRestController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um cliente em expecifíco")
	public String addCliente(String nome, String email, String cpf, String senha, String telefone) throws Exception {			
		return clienteServ.addCliente(new Cliente(nome, email, cpf, senha, telefone));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um cliente em expecifíco")
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
	@Operation(summary = "Deletar um cliente em expecifíco")
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
	@Operation(summary = "Listar todos os clientes")
	public List<Cliente> listarClientes() throws Exception {
		return clienteServ.listarClientes();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um cliente em expecifíco")
	public Cliente buscarClienteId(@PathVariable("id") Long id) throws Exception {
		return clienteServ.getClienteById(id);
	}
	
	@GetMapping("/{id}/telefones")
	@ResponseBody
	@Operation(summary = "Listar os telefones de um cliente em expecifíco")
	public List<String> listarTelefones(@PathVariable("id") Long id) throws Exception {
		return clienteServ.ListarTelefonePeloCPF(clienteServ.buscarPeloID(id).getCpf());
	}
	
	@PostMapping("/{id}/telefones")
	@ResponseBody
	@Operation(summary = "Adicionar um telefone em um cliente em expecifíco")
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
	@Operation(summary = "Deletar um telefone em um cliente em expecifíco")
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
	@Operation(summary = "Adicionar saldo em um cliente em expecifíco")
	public String adicionarSaldo(@PathVariable("id") Long id, String saldo) throws Exception {
		return clienteServ.adcionarSaldo(clienteServ.buscarPeloID(id).getCpf(), saldo);
	}
	
	@GetMapping("/{id}/carteira")
	@ResponseBody
	@Operation(summary = "Retornar o saldo de um cliente em expecifíco")
	public double verSaldo(@PathVariable("id") Long id) throws Exception {
		return clienteServ.getSaldo(clienteServ.buscarPeloID(id).getCpf());
	}
	
}