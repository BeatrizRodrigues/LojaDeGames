package minhaLojaDeGames.minhaLojaDeGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import minhaLojaDeGames.minhaLojaDeGames.model.Usuario;
import minhaLojaDeGames.minhaLojaDeGames.model.UsuarioLogin;
import minhaLojaDeGames.minhaLojaDeGames.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> usuario){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> user = repository.findByUsuario(usuario.get().getLogin());
			
		if(user.isPresent()) {
			if(encoder.matches(usuario.get().getSenha(), user.get().getSenha())) {
				String auth = usuario.get().getLogin() + ":" + usuario.get().getSenha();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeadert = "Basic " + new String(encoderAuth);
				
				usuario.get().setToken(authHeadert);
				usuario.get().setNome(user.get().getNome());
				
				return usuario;
			}
		}
		
		return null;
	}
}
