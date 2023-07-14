package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.repository.CargoRepository;
import br.edu.iff.bsi.LojaEBook.repository.FuncionarioRepository;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository res;
	@Autowired
	private CargoRepository CargRep;
	
	@PostMapping("/")
	public String addFuncionario(Funcionario funcionario, String funcao) throws Exception {
		Cargo cargoBusca = CargRep.buscarPelaFuncao(funcao);
		if(cargoBusca == null) {
			return "Cargo não existe";
		}else {
			funcionario.setCargo(cargoBusca);
			Funcionario f = res.save(funcionario);
			return "Funcionario adicionado -->"+f.getId()+"-->";
		}		
	}
	
	@PostMapping("/listarFuncionarios")
	public List<Funcionario> listarFuncionarios() throws Exception {
		return res.findAll();
	}
	
	@PostMapping("/buscaCPF")
	public String buscarFuncionarioCPF(String cpf) throws Exception {
		Funcionario f = res.buscarPeloCPF(cpf);
		if(f!=null) {			
			return "Id do funcionário: "+f.getId();
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
		Funcionario f = res.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {			
			String t = res.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				f.adicionarTelefone(telefone);
				res.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	@PostMapping("/removeTelefone")
	public String removeTelefone(String cpf, String telefone) throws Exception {
		Funcionario f = res.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {				
			String t = res.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				f.removerTelefone(telefone);
				res.flush();
				return "Telefone removido";
			}
		}
	}
}
