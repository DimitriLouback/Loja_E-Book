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
	
	public Cliente salvarCliente(Cliente cliente) {
		return ClienteRep.save(cliente);
	}
	
	public List<Cliente> listarClientes(){
		return ClienteRep.findAll();
	}
	
	public Cliente buscarPeloCPF(String cpf) {
		return ClienteRep.buscarPeloCPF(cpf);
	}
	
	public List<String> ListarTelefonePeloCPF(String cpf){
		return ClienteRep.ListarTelefonePeloCPF(cpf);
	}
	
	public String buscarTelefonePeloCPF(String cpf, String telefone) {
		return ClienteRep.buscarTelefonePeloCPF(cpf, telefone);
	}
	
	public void flush() {
		ClienteRep.flush();
	}
	
	public void deletarCliente(Cliente cliente) {
		ClienteRep.delete(cliente);
	}
	
	public Cliente buscarPeloID(Long id) {
		return ClienteRep.BuscarPeloId(id);
	}
	
}
