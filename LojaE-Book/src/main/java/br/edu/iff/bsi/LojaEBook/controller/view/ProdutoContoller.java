package br.edu.iff.bsi.LojaEBook.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Produto;
import br.edu.iff.bsi.LojaEBook.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoContoller {

	@Autowired
	private ProdutoRepository res;
	
	@PostMapping("/")
	public String addProduto(Produto produto) throws Exception {
		Produto p = res.save(produto);
		return "Produto added -->"+p.getId()+"-->";
	}
}
