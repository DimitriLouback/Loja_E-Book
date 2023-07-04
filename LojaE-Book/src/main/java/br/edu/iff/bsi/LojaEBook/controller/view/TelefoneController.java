package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Telefone;
import br.edu.iff.bsi.LojaEBook.repository.TelefoneRepository;

@RestController
@RequestMapping("telefone")
public class TelefoneController {

	@Autowired
	private TelefoneRepository res;
	
	@PostMapping("/")
	public String addTelefone(Telefone telefone) throws Exception {
		Telefone t = res.save(telefone);
		return "Telefone added -->"+t.getId()+"-->";
	}
}
