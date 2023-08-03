package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;

@Controller
@RequestMapping("carteira")
public class CarteiraController {

	@Autowired
	public ClienteService clienteServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addCarteira(Carteira carteira) throws Exception {
		Carteira c = clienteServ.salvarCarteira(carteira);
		return "Carteira added -->"+c.getId()+"-->";
	}
}
