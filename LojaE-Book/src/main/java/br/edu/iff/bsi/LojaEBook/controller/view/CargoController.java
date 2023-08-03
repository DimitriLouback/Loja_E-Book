package br.edu.iff.bsi.LojaEBook.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.service.CargoService;

@Controller
@RequestMapping("cargo")
public class CargoController {

	@Autowired
	public CargoService CargoServ;
	
	@PostMapping("/")
	@ResponseBody
	public String addCargo(Cargo cargo) throws Exception {
		return CargoServ.addCargo(cargo);
	}
	
	@PostMapping("/atualizar")
	@ResponseBody
	public String atualizarCargo(String funcao, String salario) throws Exception {
		return CargoServ.atualizarCargo(funcao, salario);
	}
	
	@PostMapping("/deletePorFuncao")
	@ResponseBody
	public String deletarCargoFuncao(String funcao) throws Exception {
		return CargoServ.deletarCargoFuncao(funcao);
	}
	
	@PostMapping("/listarCargos")
	@ResponseBody
	public List<Cargo> listarCargos() throws Exception {
		return CargoServ.listarCargos();
	}
}
