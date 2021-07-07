package minhaLojaDeGames.minhaLojaDeGames.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import minhaLojaDeGames.minhaLojaDeGames.model.Usuario;
import minhaLojaDeGames.minhaLojaDeGames.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = userRepository.findByUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}
	
}
