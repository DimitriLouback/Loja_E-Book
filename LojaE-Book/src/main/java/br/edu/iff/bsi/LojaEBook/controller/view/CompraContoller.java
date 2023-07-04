package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.repository.CompraRepository;

@RestController
@RequestMapping("compra")
public class CompraContoller {

	@Autowired
	private CompraRepository res;
	
	@PostMapping("/")
	public String addCompra(Compra Compra) throws Exception {
		Compra c = res.save(Compra);
		return "Compra added -->"+c.getId()+"-->";
	}
}
