package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;

@RestController
@RequestMapping("compra")
public class CompraContoller {

	@Autowired
	private CompraService CompraServ;
	@Autowired
	private ClienteService ClienteServ;
	
	@PostMapping("/")
	public String addCompra(String idCliente) throws Exception {
		Cliente cl = ClienteServ.buscarPeloID(Long.parseLong(idCliente));
		if(cl==null) {
			return "Cliente não achado";
		}else {
			Compra compra = new Compra();
			cl.adicionarCompra(compra);
			Compra c = CompraServ.salvarCompra(compra);
			ClienteServ.flush();
			return "Registrado no id "+c.getId();
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarCompra(String idCompra, String idCliente) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não achada";
		}else {		
			if(idCliente!=null&&Long.parseLong(idCliente)>=0) {				
				Cliente cl = ClienteServ.buscarPeloID(Long.parseLong(idCliente));
				if(cl==null) {
					return "Cliente não achado";
				}else {
					cl.adicionarCompra(c);
					ClienteServ.flush();
				}
			}
			CompraServ.flush();
			return "Atualizado no id "+c.getId();
		}
	}
	
	@PostMapping("/deletar")
	public String deletarCompra(String idCompra, String idCliente) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não achada";
		}else {		
			if(idCliente!=null&&Long.parseLong(idCliente)>=0) {				
				Cliente cl = ClienteServ.buscarPeloID(Long.parseLong(idCliente));
				if(cl==null) {
					return "Cliente não achado";
				}else {
					cl.removerCompra(c);
					ClienteServ.flush();
				}
			}
			CompraServ.deletarCompra(c);
			return "Deletado no id "+c.getId();
		}
	}
	
	@PostMapping("/listarCompras")
	public List<Compra> listarCompras() throws Exception {
		return CompraServ.listarCompras();
	}
	
}
