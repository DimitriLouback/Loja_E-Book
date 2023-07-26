package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.repository.CarteiraRepository;
import br.edu.iff.bsi.LojaEBook.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository ClienteRep;
	@Autowired
	private CarteiraRepository CarteiraRep;
	
	
	public Carteira salvarCarteira(Carteira carteira) {
		return CarteiraRep.save(carteira);
	}
	
	public String addCliente(Cliente cliente) {
		if(ClienteRep.buscarPeloCPF(cliente.getCpf())!=null) {
			return "Cliente já cadastrado";
		}else{
			Carteira carteira = CarteiraRep.save(new Carteira());
			cliente.setCarteira(carteira);
			Cliente c = ClienteRep.save(cliente);
			return "Registrado no id "+c.getId();
		}
	}
	
	public String atualizarCliente(String cpf, String nome, String email, String senha){
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
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
			ClienteRep.flush();
			return "Atualizado no id "+c.getId();
		}
	}
	
	public String deletarCliente(String cpf) {
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c!=null) {	
			ClienteRep.delete(c);
			return "Cliente deletado no id "+c.getId();
		}else {
			return "Cliente não encontrado";
		}
	}

	public List<Cliente> listarClientes(){
		return ClienteRep.findAll();
	}
	
	public String buscarClienteCPF(String cpf) throws Exception {
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c!=null) {			
			return "Id do cliente: "+c.getId();
		}else {
			return "Cliente não encontrado";
		}
	}
	
	public List<String> ListarTelefonePeloCPF(String cpf){
		return ClienteRep.ListarTelefonePeloCPF(cpf);
	}
	
	public String buscarTelefonePeloCPF(String cpf, String telefone) {
		return ClienteRep.buscarTelefonePeloCPF(cpf, telefone);
	}
	
	public String addTelefone(String cpf, String telefone){
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {		
			if(c.getQtdTelefones()==3) {
				return "Quatidade máxima de telefones já cadastrados";
			}
			String t = ClienteRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				c.adicionarTelefone(telefone);
				ClienteRep.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	public String removeTelefone(String cpf, String telefone) {
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {				
			String t = ClienteRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				c.removerTelefone(telefone);
				ClienteRep.flush();
				return "Telefone removido";
			}
		}
	}
	
	public Cliente buscarPeloID(Long id) {
		return ClienteRep.BuscarPeloId(id);
	}
	
	public String adcionarSaldo(String cpf, String saldo) {
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {		
			c.adicionarSaldo(Double.parseDouble(saldo));
			ClienteRep.flush();
			CarteiraRep.flush();
			return "Saldo adicionado";
		}
	}
	
	public String removerSaldo(String cpf, String saldo) {
		Cliente c = ClienteRep.buscarPeloCPF(cpf);
		if(c==null) {
			return "Cliente não encontrado";
		}else {		
			c.adicionarSaldo(Double.parseDouble(saldo));
			ClienteRep.flush();
			CarteiraRep.flush();
			return "Saldo adicionado";
		}
	}
	
}
