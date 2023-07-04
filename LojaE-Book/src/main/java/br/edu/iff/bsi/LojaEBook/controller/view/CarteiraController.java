package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Carteira;
import br.edu.iff.bsi.LojaEBook.repository.CarteiraRepository;

@RestController
@RequestMapping("carteira")
public class CarteiraController {

	@Autowired
	private CarteiraRepository res;
	
	@PostMapping("/")
	public String addCarteira(Carteira carteira) throws Exception {
		Carteira c = res.save(carteira);
		return "Carteira added -->"+c.getId()+"-->";
	}
}
