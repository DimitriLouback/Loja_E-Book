package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository CargoRep;
	
	public Cargo buscarPelaFuncao(String funcao) {
		return CargoRep.buscarPelaFuncao(funcao);
	}
	
	public Cargo salvarCargo(Cargo cargo) {
		return CargoRep.save(cargo);
	}
	
	public void flush() {
		CargoRep.flush();
	}
	
	public void deletarCargo(Cargo cargo) {
		CargoRep.delete(cargo);
	}
	
	public List<Cargo> listarCargos() throws Exception {
		return CargoRep.findAll();
	}
}
