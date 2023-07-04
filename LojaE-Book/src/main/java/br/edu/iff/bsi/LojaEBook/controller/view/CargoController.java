package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.repository.CargoRepository;

@RestController
@RequestMapping("cargo")
public class CargoController {

	@Autowired
	private CargoRepository res;
	
	@PostMapping("/")
	public String addCargo(Cargo cargo) throws Exception {
		Cargo c = res.save(cargo);
		return "Cargo added -->"+c.getId()+"-->";
	}
}
