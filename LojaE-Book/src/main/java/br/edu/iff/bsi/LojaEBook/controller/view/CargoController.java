package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.service.CargoService;

@RestController
@RequestMapping("cargo")
public class CargoController {

	@Autowired
	public CargoService CargoServ;
	
	@PostMapping("/")
	public String addCargo(Cargo cargo) throws Exception {
		if(CargoServ.buscarPelaFuncao(cargo.getFuncao())!=null) {
			return "Cargo já cadastrado";
		}else{		
			Cargo c = CargoServ.salvarCargo(cargo);
			return "Registrado no id "+c.getId();
		}
	}
	
	@PostMapping("/atualizar")
	public String atualizarCargo(String funcao, String salario) throws Exception {
		Cargo c = CargoServ.buscarPelaFuncao(funcao);
		if(c==null) {
			return "Cargo não achado";
		}else {
			if(salario!=null&&Double.parseDouble(salario)>0) {
				c.setSalario(Double.parseDouble(salario));
			}
			CargoServ.flush();
			return "Atualizado no id "+c.getId();
		}
	}
	
	@PostMapping("/deletePorFuncao")
	public String deletarCargoFuncao(String funcao) throws Exception {
		Cargo c = CargoServ.buscarPelaFuncao(funcao);
		if(c!=null) {	
			CargoServ.deletarCargo(c);
			return "Cargo deletado no id "+c.getId();
		}else {
			return "Cargo não encontrado";
		}
	}
	
	@PostMapping("/listarCargos")
	public List<Cargo> listarCargos() throws Exception {
		return CargoServ.listarCargos();
	}
}
