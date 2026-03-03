package br.com.forumhub.api.controller;

import br.com.forumhub.api.domain.curso.Curso;
import br.com.forumhub.api.domain.curso.DadosDetalhamentoCurso;
import br.com.forumhub.api.domain.curso.CursoRepository;
import br.com.forumhub.api.domain.curso.DadosCadastroCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
