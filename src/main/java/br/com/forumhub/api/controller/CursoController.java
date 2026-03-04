package br.com.forumhub.api.controller;

import br.com.forumhub.api.domain.curso.*;
import br.com.forumhub.api.domain.topico.DadosAtualizacaoTopico;
import br.com.forumhub.api.domain.topico.DadosDetalhamentoTopico;
import br.com.forumhub.api.domain.topico.DadosListagemTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroCurso dadosCadastroCurso, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dadosCadastroCurso);
        cursoRepository.save(curso);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao){
        var page =  cursoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCurso::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCurso dadosAtualizacaoCurso){
        var curso = cursoRepository.getReferenceById(dadosAtualizacaoCurso.id());
        curso.atualizarInformacoes(dadosAtualizacaoCurso);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        var curso = cursoRepository.getReferenceById(id);
        curso.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }
}
