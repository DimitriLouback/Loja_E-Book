package br.edu.iff.bsi.LojaEBook.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/cargo")
public class CargoRestController {

	@Autowired
	public CargoService CargoServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um cargo em expecifíco")
	public String addCargo(String funcao, double salario) throws Exception {
		return CargoServ.addCargo(new Cargo(funcao, salario));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um cargo em expecifíco")
	public String atualizarCargo(@PathVariable("id") Long id, String salario) throws Exception {
		Cargo cBusca = CargoServ.getCargoById(id);
		if(cBusca==null) {			
			return "Cargo não achado";
		}else {
			return CargoServ.atualizarCargo(cBusca.getFuncao(), salario);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um cargo em expecifíco")
	public String deletarCargoFuncao(@PathVariable("id") Long id) throws Exception {
		Cargo cBusca = CargoServ.getCargoById(id);
		if(cBusca==null) {			
			return "Cargo não achado";
		}else {			
			return CargoServ.deletarCargoFuncao(cBusca.getFuncao());
		}
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Litar todos os cargos")
	public List<Cargo> listarCargos() throws Exception {
		return CargoServ.listarCargos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um cargo em expecifíco")
	public Cargo buscarCargos(@PathVariable("id") Long id) throws Exception {
		return CargoServ.getCargoById(id);
	}
}
