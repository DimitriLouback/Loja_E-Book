package br.edu.iff.bsi.LojaEBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Cargo;
import br.edu.iff.bsi.LojaEBook.model.Funcionario;
import br.edu.iff.bsi.LojaEBook.model.Usuario;
import br.edu.iff.bsi.LojaEBook.repository.CargoRepository;
import br.edu.iff.bsi.LojaEBook.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository FuncionarioRep;
	@Autowired
	private CargoRepository CargoRep;

	@Autowired
	private UsuarioDetailsService UsuarioServ;
	
	public String addFuncionario(Funcionario funcionario, String funcao) {
		if(FuncionarioRep.buscarPeloCPF(funcionario.getCpf())!=null) {
			return "Funcionario já cadastrado";
		}else{			
			Cargo cargoBusca = CargoRep.buscarPelaFuncao(funcao);
			if(cargoBusca == null) {
				return "Cargo não definido";
			}else {
				funcionario.setCargo(cargoBusca);
				String permissao;
				if(cargoBusca.getNivelAcesso()>=1&&cargoBusca.getNivelAcesso()<=5) {
					permissao = "FuncionarioNv"+cargoBusca.getNivelAcesso();
				}else {
					throw new RuntimeException("Nível de acesso fora dos limites");
				}
				Usuario usuario = UsuarioServ.salvar(funcionario.getCpf(), funcionario.getSenha(), permissao);
				funcionario.setUsuario(usuario);
				Funcionario f = FuncionarioRep.save(funcionario);
				return "Registrado no id "+f.getId();
			}		
		}
	}
	
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String funcao) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionario não achado";
		}else {		
			if(nome!=null) {
				f.setNome(nome);
			}
			if(email!=null) {				
				f.setEmail(email);
			}
			if(senha!=null) {				
				f.setSenha(senha);
				UsuarioServ.atualizarSenha(f.getUsuario(), senha);
			}
			if(funcao!=null) {
				Cargo cargoBusca = CargoRep.buscarPelaFuncao(funcao);
				if(cargoBusca == null) {
					return "Cargo não existe";
				}else {
					f.setCargo(cargoBusca);
					f.getUsuario().getPermissoes().get(0).setNome("FuncionarioNv"+cargoBusca.getNivelAcesso());
				}
			}
			FuncionarioRep.flush();
			return "Atualizado no id "+f.getId();
		}
	}
	
	public String deletarFuncionarioCPF(String cpf) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f!=null) {	
			FuncionarioRep.delete(f);
			return "Funcionario deletado no id "+f.getId();
		}else {
			return "Funcionario não encontrado";
		}
	}
	
	public List<Funcionario> listarFuncionarios() throws Exception {
		return FuncionarioRep.findAll();
	}
	
	public Funcionario buscarFuncionarioCPF(String cpf) {
		return FuncionarioRep.buscarPeloCPF(cpf);
	}
	
	public String addTelefone(String cpf, String telefone) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {		
			if(f.getQtdTelefones()==3) {
				return "Quatidade máxima de telefones já cadastrados";
			}
			String t = FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t!=null) {
				return "Telefone já cadastrado";
			}else {
				f.adicionarTelefone(telefone);
				FuncionarioRep.flush();
				return "Telefone adicionado";
			}
		}
	}
	
	public String removeTelefone(String cpf, String telefone) {
		Funcionario f = FuncionarioRep.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionário não encontrado";
		}else {				
			String t = FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
			if(t==null) {
				return "Telefone não cadastrado";
			}else {
				f.removerTelefone(telefone);
				FuncionarioRep.flush();
				return "Telefone removido";
			}
		}
	}
	
	public List<String> ListarTelefonePeloCPF(String cpf) throws Exception {
		return FuncionarioRep.ListarTelefonePeloCPF(cpf);
	}
	
	public String buscarTelefonePeloCPF(String cpf, String telefone) {
		return FuncionarioRep.buscarTelefonePeloCPF(cpf, telefone);
	}
	
	public Funcionario getFuncionarioById(Long id) {
		return FuncionarioRep.BuscarPeloId(id);
	}
	
	public void garantirFuncionarioADM() {
		if((FuncionarioRep.maiorNivelAcesso()==null)||(Integer.parseInt(FuncionarioRep.maiorNivelAcesso())>1)) {
			Cargo cargo = CargoRep.buscarPelaFuncao("ADM");
			if(cargo==null) {
				CargoRep.save(new Cargo("ADM",100,1));
				cargo = CargoRep.buscarPelaFuncao("ADM");
			}else if(cargo.getNivelAcesso()>1) {
				cargo.setNivelAcesso(1);
			}
			Funcionario func = FuncionarioRep.buscarPeloCPF("000.000.000-00");
			if(func==null) {
				func = new Funcionario("ADM","Administrador@adm","000.000.000-00","123","(00) 00000-0000");
				func.setCargo(cargo);
				Usuario usuario = UsuarioServ.salvar(func.getNome(), func.getSenha(), "FuncionarioNv1");
				func.setUsuario(usuario);
				FuncionarioRep.save(func);
			}else if(func.getCargo().getNivelAcesso()>1){
				func.setCargo(cargo);
			}
			CargoRep.flush();
			FuncionarioRep.flush();
		}
	}
	
}
