package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@PostMapping("/")
	public String addCliente(Cliente cliente) throws Exception {
		if(clienteServ.buscarPeloCPF(cliente.getCpf())!=null) {
			return "Cliente já cadastrado";
		}else{
			Carteira carteira = clienteServ.salvarCarteira(new Carteira());
			cliente.setCarteira(carteira);
			Cliente c = clienteServ.salvarCliente(cliente);
			return "Registrado no id "+c.getId();
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarCliente(String cpf, String nome, String email, String senha) throws Exception {
		Cliente c = clienteServ.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não achado";
		}else {		
			if(nome!=null) {
				c.setNome(nome);
			}
			if(email!=null) {				
				c.setEmail(email);
			}
			if(senha!=null) {				
				c.setSenha(senha);
			}
			clienteServ.flush();
			return "Atualizado no id "+c.getId();
		}
	}
	
	@PostMapping("/deletePorCPF")
	public String deletarClienteCPF(String cpf) throws Exception {
		Cliente c = clienteServ.buscarPeloCPF(cpf);
		if(c!=null) {	
			clienteServ.deletarCliente(c);
			return "Cliente deletado no id "+c.getId();
		}else {
			return "Cliente não encontrado";
		}
	}
	
	@PostMapping("/listarClientes")
	public List<Cliente> listarClientes() throws Exception {
		return clienteServ.listarClientes();
	}
	
	@PostMapping("/buscaCPF")
	public String buscarClienteCPF(String cpf) throws Exception {
		Cliente c = clienteServ.buscarPeloCPF(cpf);
		if(c!=null) {			
			return "Id do cliente: "+c.getId();
		}else {
			return "Cliente não encontrado";
		}
	}
	
	@PostMapping("/listarTelefones")
	public List<String> listarTelefones(String cpf) throws Exception {
		return clienteServ.ListarTelefonePeloCPF(cpf);
	}
	
	@PostMapping("/addTelefone")
	public String addTelefone(String cpf, String telefone) throws Exception {
		Cliente c = clienteServ.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {			
			String t = clienteServ.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				c.adicionarTelefone(telefone);
				clienteServ.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	@PostMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		Cliente c = clienteServ.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {				
			String t = clienteServ.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				c.removerTelefone(telefone);
				clienteServ.flush();
				return "Telefone removido";
			}
		}
	}
	
}