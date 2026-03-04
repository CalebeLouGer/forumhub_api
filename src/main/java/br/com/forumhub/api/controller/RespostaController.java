package br.com.forumhub.api.controller;

import br.com.forumhub.api.domain.resposta.DadosCadastroResposta;
import br.com.forumhub.api.domain.resposta.DadosDetalhamentoResposta;
import br.com.forumhub.api.domain.resposta.Resposta;
import br.com.forumhub.api.domain.resposta.RespostaRepository;
import br.com.forumhub.api.domain.topico.TopicoRepository;
import br.com.forumhub.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping("/{id}/respostas")
    @Transactional
    public ResponseEntity responder(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados, @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriBuilder) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        var resposta = new Resposta(dados, usuarioLogado,topico);
        respostaRepository.save(resposta);

        var uri = uriBuilder.path("/repostas/{id}").buildAndExpand(resposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

}
