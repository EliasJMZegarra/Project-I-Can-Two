package upc.com.pe.trabajo_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upc.com.pe.trabajo_v1.entities.Usuario;
import upc.com.pe.trabajo_v1.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findByNombreUsuario(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        user.getRoles().forEach(rol -> {
            //System.out.println(rol.getRol());
            roles.add(new SimpleGrantedAuthority(rol.getTipoRol()));
        });

        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), user.getContrasenia(), user.getEnabled(), true, true, true, roles);

        return ud;
    }
}
