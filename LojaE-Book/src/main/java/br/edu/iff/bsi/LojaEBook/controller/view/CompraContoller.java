package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cliente;
import br.edu.iff.bsi.LojaEBook.model.Colecao_E_Book;
import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.ClienteService;
import br.edu.iff.bsi.LojaEBook.service.Colecao_E_BookService;
import br.edu.iff.bsi.LojaEBook.service.CompraService;
import br.edu.iff.bsi.LojaEBook.service.E_BookService;

@RestController
@RequestMapping("compra")
public class CompraContoller {

	@Autowired
	private CompraService CompraServ;
	@Autowired
	private ClienteService ClienteServ;
	@Autowired
	private Colecao_E_BookService Colecao_E_BookServ;
	@Autowired
	private E_BookService EBookServ;
	
	@PostMapping("/")
	public String addCompra(String cpf) throws Exception {
		Cliente cl = ClienteServ.buscarPeloCPF(cpf);
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
	public String atualizarCompra(String idCompra, String cpf) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não achada";
		}else {		
			if(cpf!=null) {				
				Cliente cl = ClienteServ.buscarPeloCPF(cpf);
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
	public String deletarCompra(String idCompra) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não achada";
		}else {		
			Long idCliente = CompraServ.buscarPeloIDCliente(Long.parseLong(idCompra));
			Cliente cl = ClienteServ.buscarPeloID(idCliente);
			if(cl==null) {
				return "Cliente não achado";
			}else {
				cl.removerCompra(c);
				ClienteServ.flush();
			}

			CompraServ.deletarCompra(c);
			return "Deletado no id "+c.getId();
		}
	}
	
	@PostMapping("/listarCompras")
	public List<Compra> listarCompras() throws Exception {
		return CompraServ.listarCompras();
	}
	
	@PostMapping("/addE_Book")
	public String addE_Book(String idCompra, String titulo) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não encontrada";
		}else {			
			E_Book e = EBookServ.buscarPeloTitulo(titulo);
			if(e==null) {
				return "E-Book não encontrado";
			}else {
				if(CompraServ.verificarProdutoCompra(e.getId())!=0) {
					return "E-Book já cadastrado";
				}else {					
					c.adicionarProduto(e);
					CompraServ.flush();
					return "E-Book adicionado";
				}
			}
		}
	}
	
	@PostMapping("/listarE_Books")
	public List<Object> listarE_Books(String idCompra) throws Exception {
		return CompraServ.ListarEBookPeloIdCompra(Long.parseLong(idCompra));
	}
	
	@PostMapping("/removeE_Book")
	public String removeE_Book(String idCompra, String titulo) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não encontrada";
		}else {			
			E_Book e = EBookServ.buscarPeloTitulo(titulo);
			if(e==null) {
				return "E-Book não encontrado";
			}else {
				if(CompraServ.verificarProdutoCompra(e.getId())==0) {
					return "E-Book não consta na compra";
				}else {
					c.removerProduto(e);
					Colecao_E_BookServ.flush();
					return "E-Book removido";
				}
			}
		}
	}
	
	@PostMapping("/addColecaoE_Book")
	public String addColecaoE_Book(String idCompra, String serie) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não encontrada";
		}else {			
			Colecao_E_Book ce = Colecao_E_BookServ.buscarPelaSerie(serie);
			if(ce==null) {
				return "Coleção de E-Book não encontado";
			}else {
				if(CompraServ.verificarProdutoCompra(ce.getId())!=0) {
					return "Coleção de E-Book já cadastrado";
				}else {
					c.adicionarProduto(ce);
					Colecao_E_BookServ.flush();
					return "Coleção de E-Book adicionada";
				}
			}
		}
	}
	
	@PostMapping("/removeColecaoE_Book")
	public String removeColecaoE_Book(String idCompra, String serie) throws Exception {
		Compra c = CompraServ.buscarPeloID(Long.parseLong(idCompra));
		if(c==null) {
			return "Compra não encontrada";
		}else {			
			Colecao_E_Book ce = Colecao_E_BookServ.buscarPelaSerie(serie);;
			if(ce==null) {
				return "Coleção de E-Book não encontrado";
			}else {
				if(CompraServ.verificarProdutoCompra(ce.getId())==0) {
					return "Coleção de E-Book não consta na compra";
				}else {
					c.removerProduto(ce);
					Colecao_E_BookServ.flush();
					return "Coleção de E-Book removida";
				}
			}
		}
	}
	
	@PostMapping("/listarColecoesE_Book")
	public List<Object> listarColecoesE_Book(String idCompra) throws Exception {
		return CompraServ.ListarColecaoEBookPeloIdCompra(Long.parseLong(idCompra));
	}
}
