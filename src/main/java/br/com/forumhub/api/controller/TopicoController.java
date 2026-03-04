package br.com.forumhub.api.controller;

import br.com.forumhub.api.domain.curso.CursoRepository;
import br.com.forumhub.api.domain.topico.*;
import br.com.forumhub.api.domain.usuario.Usuario;
import br.com.forumhub.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriBuilder){
        var curso = cursoRepository.getReferenceById(dados.cursoId());

        var topico = new Topico(dados, usuarioLogado, curso);
        topicoRepository.save(topico);
        var topicoDetalhado = topicoRepository.buscarDetalhado(topico.getId()).get();

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topicoDetalhado));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(sort = {"curso"}, size = 10) Pageable paginacao){
        var page =  topicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacaoTopico){
        var topico = topicoRepository.getReferenceById(dadosAtualizacaoTopico.id());
        topico.atualizarInformacoes(dadosAtualizacaoTopico);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var topico = topicoRepository.getReferenceById(id);
        topico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }
}
