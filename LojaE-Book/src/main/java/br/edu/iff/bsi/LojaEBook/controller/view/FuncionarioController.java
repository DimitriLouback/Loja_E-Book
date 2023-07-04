package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.repository.FuncionarioRepository;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository res;
	
	@PostMapping("/")
	public String addFuncionario(Funcionario funcionario) throws Exception {
		Funcionario f = res.save(funcionario);
		return "Funcionario added -->"+f.getId()+"-->";
	}
}
