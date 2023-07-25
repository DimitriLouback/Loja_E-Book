package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.CompraService;

@Controller
@RequestMapping("compra")
public class CompraController {

	@Autowired
	private CompraService CompraServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addCompra(String cpf) throws Exception {
		return CompraServ.addCompra(cpf);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarCompra(String idCompra, String cpf) throws Exception {
		return CompraServ.atualizarCompra(idCompra, cpf);
	}
	
	@PostMapping("/deletar")
	@ResponseBody
	public String deletarCompra(String idCompra) throws Exception {
		return CompraServ.deletarCompra(idCompra);
	}
	
	@PostMapping("/listarCompras")
	@ResponseBody
	public List<Compra> listarCompras() throws Exception {
		return CompraServ.listarCompras();
	}
	
	@PostMapping("/addE_Book")
	@ResponseBody
	public String addE_Book(String idCompra, String titulo) throws Exception {
		return CompraServ.addE_Book(idCompra, titulo);
	}
	
	@PostMapping("/listarE_Books")
	@ResponseBody
	public List<E_Book> listarE_Books(String idCompra) throws Exception {
		return CompraServ.ListarEBookPeloIdCompra(Long.parseLong(idCompra));
	}
	
	@PostMapping("/removeE_Book")
	@ResponseBody
	public String removeE_Book(String idCompra, String titulo) throws Exception {
		return CompraServ.removeE_Book(idCompra, titulo);
	}
	
	@PostMapping("/addColecaoE_Book")
	@ResponseBody
	public String addColecaoE_Book(String idCompra, String serie) throws Exception {
		return CompraServ.addColecaoE_Book(idCompra, serie);
	}
	
	@PostMapping("/removeColecaoE_Book")
	@ResponseBody
	public String removeColecaoE_Book(String idCompra, String serie) throws Exception {
		return CompraServ.removeColecaoE_Book(idCompra, serie);
	}
	
	@PostMapping("/listarColecoesE_Book")
	@ResponseBody
	public List<Colecao_E_Book> listarColecoesE_Book(String idCompra) throws Exception {
		return CompraServ.ListarColecaoEBookPeloIdCompra(Long.parseLong(idCompra));
	}
}
