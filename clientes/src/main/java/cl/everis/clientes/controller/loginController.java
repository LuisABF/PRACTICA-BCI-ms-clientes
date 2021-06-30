package cl.everis.clientes.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cl.everis.clientes.dto.UsuarioLoginDTO;
import cl.everis.clientes.exception.ErrorException;
import cl.everis.clientes.model.Usuario;
import cl.everis.clientes.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class loginController {

    private final UsuarioRepo usuarioRespository;

    public loginController(UsuarioRepo usuarioRespository) {
        this.usuarioRespository = usuarioRespository;
    }

    @Value("${mySecretKey}")
    private String mySecretKey;

    @PostMapping("login")
    public UsuarioLoginDTO login(@RequestParam("rut") String rut, @RequestParam("password") String password) {

        if(existeUsuario(Long.valueOf(rut))) {
            String token = getJWTToken(rut);
            UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
            usuarioLoginDTO.setRut(rut);
            usuarioLoginDTO.setToken(token);
            return usuarioLoginDTO;
        }else {
            throw new ErrorException(HttpStatus.UNAUTHORIZED, "Usuario no existe. No se puedo realizar la autenticación");
        }
    }

    public boolean existeUsuario(Long rut){
        Optional<Usuario> usuarioRepository = usuarioRespository.findById(rut);

        if(usuarioRepository.isPresent()){
            return true;
        }else {
            return false;
        }
    }

    private String getJWTToken(String username) {

        String secretKey = this.mySecretKey;
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
