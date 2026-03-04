package br.com.forumhub.api.domain.curso;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private Boolean ativo;

    public Curso(DadosCadastroCurso dadosCadastroCurso){
        this.nome = dadosCadastroCurso.nome();
        this.categoria = dadosCadastroCurso.categoria();
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoCurso dadosAtualizacaoCurso) {
        if (dadosAtualizacaoCurso.nome() != null){
            this.nome = dadosAtualizacaoCurso.nome();
        }
        if (dadosAtualizacaoCurso.categoria() != null){
            this.categoria = dadosAtualizacaoCurso.categoria();
        }
    }
}
