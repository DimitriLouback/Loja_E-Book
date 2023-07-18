package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Compra;
import br.edu.iff.bsi.LojaEBook.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository CompraRep;
	
	public Compra salvarCompra(Compra compra) {
		return CompraRep.save(compra);
	}
	
	public List<Compra> listarCompras() throws Exception {
		return CompraRep.findAll();
	}
	
	public Compra buscarPeloID(Long id) {
		return CompraRep.BuscarPeloId(id);
	}
	
	public void flush() {
		CompraRep.flush();
	}
	
	public void deletarCompra(Compra compra) {
		CompraRep.delete(compra);
	}
}
