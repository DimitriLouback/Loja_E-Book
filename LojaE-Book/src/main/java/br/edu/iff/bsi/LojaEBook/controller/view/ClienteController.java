package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.repository.CarteiraRepository;
import br.edu.iff.bsi.LojaEBook.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository res;
	@Autowired
	private CarteiraRepository CarteiraRep;
	
	@PostMapping("/")
	public String addCliente(Cliente cliente) throws Exception {
		Carteira carteira = CarteiraRep.save(new Carteira());
		cliente.setCarteira(carteira);
		Cliente c = res.save(cliente);
		return "Cliente added -->"+c.getId()+"-->";
	}
	
	@PostMapping("/listarClientes")
	public List<Cliente> listarClientes() throws Exception {
		return res.findAll();
	}
	
	@PostMapping("/buscaCPF")
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		Cliente c = res.buscarPeloCPF(cpf);
		if(c!=null) {			
			return "Id do funcionário: "+c.getId();
		}else {
			return "Funcionário não encontrado";
		}
	}
	
	@PostMapping("/listarTelefones")
	public List<String> listarTelefones(String cpf) throws Exception {
		return res.ListarTelefonePeloCPF(cpf);
	}
	
	@PostMapping("/addTelefone")
	public String addTelefone(String cpf, String telefone) throws Exception {
		Cliente c = res.buscarPeloCPF(cpf);
		if(c==null) {
			return "Funcionário não encontrado";
		}else {			
			String t = res.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				c.adicionarTelefone(telefone);
				res.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	@PostMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		Cliente c = res.buscarPeloCPF(cpf);
		if(c==null) {
			return "Funcionário não encontrado";
		}else {				
			String t = res.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				c.removerTelefone(telefone);
				res.flush();
				return "Telefone removido";
			}
		}
	}
	
}