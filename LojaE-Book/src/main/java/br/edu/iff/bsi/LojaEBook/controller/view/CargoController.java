package br.edu.iff.bsi.LojaEBook.controller.view;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.E_Book;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cargo")
public class CargoController {

	@Autowired
	public CargoService CargoServ;
	
	@GetMapping("")
	public String page(Model model) throws Exception {
		model.addAttribute("cargos", CargoServ.listarCargos());
		return "CRUD_Cargo";
	}
	
	@GetMapping("/addForm")
	public String addCargoForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("cargo_add", new Cargo());
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
	    }
		return "CRUD_Cargo";
	}
	
	@PostMapping("/add")
	public String addCargo(@ModelAttribute Cargo cargo) throws Exception {
		String resposta = CargoServ.addCargo(cargo);
		return "redirect:/cargo/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}

	@GetMapping("/listarCargos")
	public String listarCargos(Model model, HttpServletRequest request) throws Exception {
		String funcao = request.getParameter("funcao");
		if(funcao==null) {
			model.addAttribute("cargo_lista", CargoServ.listarCargos());			
		} else {
			Cargo busca = CargoServ.buscarPelaFuncao(funcao);
			if(busca==null) {
				busca = new Cargo();
			}
			model.addAttribute("cargo_lista", busca);
		}
		return "CRUD_Cargo";
	}
	
	@PostMapping("/buscaFuncao")
	public String buscarCargos(String funcao, Model model) throws Exception {
		return "redirect:/cargo/listarCargos?funcao="+URLEncoder.encode(funcao, "UTF-8");
	}
	
	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		model.addAttribute("cargo_edit", CargoServ.getCargoById(id));
		return "CRUD_Cargo";
	}
	
	@PostMapping("/atualizar")
	public String atualizarCargo(String funcao, String salario) throws Exception {
		CargoServ.atualizarCargo(funcao, salario);
		return "redirect:/cargo/listarCargos";
	}
	
	@GetMapping("/deletaPorFuncao")
	public String deletarCargoFuncao(String funcao) throws Exception {
		CargoServ.deletarCargoFuncao(funcao);
		return "redirect:/cargo/listarCargos";
	}
	
}
