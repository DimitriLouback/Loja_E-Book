package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;

@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addCliente(Cliente cliente) throws Exception {
		return clienteServ.addCliente(cliente);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarCliente(String cpf, String nome, String email, String senha) throws Exception {
		return clienteServ.atualizarCliente(cpf, nome, email, senha);
	}
	
	@PostMapping("/deletePorCPF")
	@ResponseBody
	public String deletarClienteCPF(String cpf) throws Exception {
		return clienteServ.deletarCliente(cpf);
	}
	
	@PostMapping("/listarClientes")
	@ResponseBody
	public List<Cliente> listarClientes() throws Exception {
		return clienteServ.listarClientes();
	}
	
	@PostMapping("/buscaCPF")
	@ResponseBody
	public String buscarClienteCPF(String cpf) throws Exception {
		return clienteServ.buscarClienteCPF(cpf);
	}
	
	@PostMapping("/listarTelefones")
	@ResponseBody
	public List<String> listarTelefones(String cpf) throws Exception {
		return clienteServ.ListarTelefonePeloCPF(cpf);
	}
	
	@PostMapping("/addTelefone")
	@ResponseBody
	public String addTelefone(String cpf, String telefone) throws Exception {
		return clienteServ.addTelefone(cpf, telefone);
	}
	
	@PostMapping("/removeTelefone")
	@ResponseBody
	public String removeTelefone(String cpf, String telefone) throws Exception {
		return clienteServ.removeTelefone(cpf, telefone);
	}
	
	@PostMapping("/addSaldo")
	@ResponseBody
	public String adicionarSaldo(String cpf, String saldo) throws Exception {
		return clienteServ.adcionarSaldo(cpf, saldo);
	}
	
	@PostMapping("/getSaldo")
	@ResponseBody
	public String verSaldo(String cpf) throws Exception {
		double saldo = clienteServ.getSaldo(cpf);
		if(saldo==-1) {
			return "Cliente não encontrado";
		}else {		
			return "Saldo: R$ "+saldo;
		}
	}
	
}