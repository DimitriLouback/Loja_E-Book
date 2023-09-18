package br.edu.iff.bsi.LojaEBook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.iff.bsi.LojaEBook.model.Permissao;
import br.edu.iff.bsi.LojaEBook.model.Usuario;
import br.edu.iff.bsi.LojaEBook.repository.PermissaoRepository;
import br.edu.iff.bsi.LojaEBook.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private PermissaoRepository PermRep;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(usuario.getLogin(), usuario.getSenha(), authorities(usuario.getPermissoes()));
	}
	
	public List<? extends GrantedAuthority> authorities(List<Permissao> lista){
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Permissao permissao : lista) {
			auths.add(new SimpleGrantedAuthority("ROLE_"+permissao.getNome()));
		}
		return auths;
	}
	
	public Usuario salvar(String login, String senha, String permissao) {
		Usuario usuario = new Usuario(login, senha);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		Permissao perm = PermRep.getByNome("Cliente");
		if(perm == null) {
			perm = new Permissao(permissao);
			PermRep.save(perm);
		}
		usuario.addPermissao(perm);
		Usuario u = repo.save(usuario);
		return u;
	}
	
	public void atualizarSenha(Usuario usuario, String senha) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		repo.flush();
	}
	
}
