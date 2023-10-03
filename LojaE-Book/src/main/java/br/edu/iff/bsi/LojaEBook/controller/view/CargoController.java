package br.edu.iff.bsi.LojaEBook.controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.service.CargoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("cargo")
public class CargoController {

	@Autowired
	public CargoService CargoServ;
	
	@GetMapping("")
	public String page() throws Exception {
		return "redirect:/cargo/listarCargos";
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
	public String addCargo(@Valid @ModelAttribute Cargo cargo, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {
			String resposta = CargoServ.addCargo(cargo);
			try {
				return "redirect:/cargo/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}		
		}
	}

	@GetMapping("/listarCargos")
	public String listarCargos(Model model, HttpServletRequest request) throws Exception {
		String funcao = request.getParameter("funcao");
		String resposta = request.getParameter("resposta");
	    if (resposta != null) {
	        model.addAttribute("respostaDelete", URLDecoder.decode(resposta, "UTF-8"));
	    }
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
	public String atualizarCargo(@Valid @ModelAttribute Cargo cargo, BindingResult resultado, Model model) {
		String funcao = cargo.getFuncao();
		double salario = cargo.getSalario();
		int nivelAcesso = cargo.getNivelAcesso();
		if(resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "error";
		}else {			
			CargoServ.atualizarCargo(funcao, salario, nivelAcesso);
			return "redirect:/cargo/listarCargos";
		}
	}
	
	@GetMapping("/deletaPorFuncao")
	public String deletarCargoFuncao(String funcao) throws Exception {
		String resposta = CargoServ.deletarCargoFuncao(funcao);
		return "redirect:/cargo/listarCargos?resposta=" + URLEncoder.encode(resposta, "UTF-8");
	}
	
}
