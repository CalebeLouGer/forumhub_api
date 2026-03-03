package br.com.forumhub.api.controller;

import br.com.forumhub.api.domain.usuario.DadosAuthentication;
import br.com.forumhub.api.domain.usuario.Usuario;
import br.com.forumhub.api.infra.security.DadosTokenJWT;
import br.com.forumhub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticantionManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuthentication dadosAuthentication){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAuthentication.login(),dadosAuthentication.senha());
        var authentication = authenticantionManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
