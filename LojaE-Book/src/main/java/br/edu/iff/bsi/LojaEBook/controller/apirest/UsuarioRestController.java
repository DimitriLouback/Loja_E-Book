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

import br.edu.iff.bsi.LojaEBook.model.Usuario;
import br.edu.iff.bsi.LojaEBook.service.UsuarioDetailsService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path= "/api/v1/usuario")
public class UsuarioRestController {

	@Autowired
	public UsuarioDetailsService UsuarioServ;
	
	@PostMapping("")
	@ResponseBody
	@Operation(summary = "Adicionar um usuario em expecifíco")
	public Usuario addUsuario(String login, String senha, String permissao) throws Exception {
		return UsuarioServ.salvar(login, senha, permissao);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um usuario em expecifíco")
	public String atualizarUsuario(@PathVariable("id") Long id, String senha, String permissao) throws Exception {
		Usuario uBusca = UsuarioServ.buscarPorId(id);
		if(uBusca==null) {			
			return "Usuario não achado";
		}else {
			if(senha!=null) {
				UsuarioServ.atualizarSenha(uBusca, senha);
			}
			if(permissao!=null) {
				UsuarioServ.atualizarPermissao(uBusca, permissao);
			}
			return "Usuario atuaizado com sucesso";
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um usuario em expecifíco")
	public String deletarUsuario(@PathVariable("id") Long id) throws Exception {
		Usuario uBusca = UsuarioServ.buscarPorId(id);
		if(uBusca==null) {			
			return "Usuario não achado";
		}else {	
			UsuarioServ.deletarPorId(id);
			return "Usuario deletado";
		}
	}
	
	@GetMapping("")
	@ResponseBody
	@Operation(summary = "Litar todos os usuarios")
	public List<Usuario> listarUsuarios() throws Exception {
		return UsuarioServ.listarTodos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um usuario em expecifíco")
	public Usuario buscarUsuario(@PathVariable("id") Long id) throws Exception {
		return UsuarioServ.buscarPorId(id);
	}
	
}
