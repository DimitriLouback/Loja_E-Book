package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.CompraService;

@RestController
@RequestMapping(path= "/api/v1/compra")
public class CompraRestController {

	@Autowired
	private CompraService CompraServ;
	
	@PostMapping("/{id}")
	@ResponseBody
	public String addCompra(@PathVariable("id") Long id, String cpf) throws Exception {
		Compra cBusca = CompraServ.getCompraById(id);
		if(cBusca==null) {				
			return CompraServ.addCompra(cpf);
		}else {
			return "Compra já adicionada";
		}	
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public String atualizarCompra(@PathVariable("id") String id, String cpf) throws Exception {
		return CompraServ.atualizarCompra(id, cpf);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletarCompra(@PathVariable("id") String id) throws Exception {
		return CompraServ.deletarCompra(id);
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Compra> listarCompras() throws Exception {
		return CompraServ.listarCompras();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Compra buscarCompras(@PathVariable("id") Long id) throws Exception {
		return CompraServ.getCompraById(id);
	}
	
	@PostMapping("/{id}/e_books")
	@ResponseBody
	public String addE_Book(@PathVariable("id") String id, String titulo) throws Exception {
		return CompraServ.addE_Book(id, titulo);
	}
	
	@GetMapping("/{id}/e_books")
	@ResponseBody
	public List<E_Book> listarE_Books(@PathVariable("id") Long id) throws Exception {
		return CompraServ.ListarEBookPeloIdCompra(id);
	}
	
	@DeleteMapping("/{id}/e_books")
	@ResponseBody
	public String removeE_Book(@PathVariable("id") String id, String titulo) throws Exception {
		return CompraServ.removeE_Book(id, titulo);
	}
	
	@PostMapping("/{id}/colecao_e_books")
	@ResponseBody
	public String addColecaoE_Book(@PathVariable("id") String id, String serie) throws Exception {
		return CompraServ.addColecaoE_Book(id, serie);
	}
	
	@DeleteMapping("/{id}/colecao_e_books")
	@ResponseBody
	public String removeColecaoE_Book(@PathVariable("id") String id, String serie) throws Exception {
		return CompraServ.removeColecaoE_Book(id, serie);
	}
	
	@GetMapping("/{id}/colecao_e_books")
	@ResponseBody
	public List<Colecao_E_Book> listarColecoesE_Book(@PathVariable("id") Long id) throws Exception {
		return CompraServ.ListarColecaoEBookPeloIdCompra(id);
	}
	
	@PatchMapping("/{id}")
	@ResponseBody
	public String finalizarCompra(@PathVariable("id") String id) throws Exception {
		return CompraServ.finalizarCompraPeloId(id);
	}
}
