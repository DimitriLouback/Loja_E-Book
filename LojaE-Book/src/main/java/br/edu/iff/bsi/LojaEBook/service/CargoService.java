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
	
	public String addCargo(Cargo cargo) {
		if(CargoRep.buscarPelaFuncao(cargo.getFuncao())!=null) {
			return "Cargo já cadastrado";
		}else{		
			Cargo c = CargoRep.save(cargo);
			return "Registrado no id "+c.getId();
		}
	}
	
	public String atualizarCargo(String funcao, String salario) {
		Cargo c = CargoRep.buscarPelaFuncao(funcao);
		if(c==null) {
			return "Cargo não achado";
		}else {
			if(salario!=null&&Double.parseDouble(salario)>0) {
				c.setSalario(Double.parseDouble(salario));
			}
			CargoRep.flush();
			return "Atualizado no id "+c.getId();
		}
	}
	
	public String deletarCargoFuncao(String funcao) throws Exception {
		Cargo c = CargoRep.buscarPelaFuncao(funcao);
		if(c!=null) {	
			CargoRep.delete(c);
			return "Cargo deletado no id "+c.getId();
		}else {
			return "Cargo não encontrado";
		}
	}
	
	public List<Cargo> listarCargos() throws Exception {
		return CargoRep.findAll();
	}
	
	public Cargo getCargoById(Long id) {
		return CargoRep.BuscarPeloId(id);
	}
}
