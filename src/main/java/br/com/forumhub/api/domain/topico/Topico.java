package br.com.forumhub.api.domain.topico;

import br.com.forumhub.api.domain.curso.Curso;
import br.com.forumhub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "datacriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private Boolean ativo;

    public Topico(DadosCadastroTopico dadosCadastroTopico,Usuario usuario, Curso curso) {
        this.titulo = dadosCadastroTopico.titulo();
        this.mensagem = dadosCadastroTopico.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = Status.NÃO_RESPONDIDO;
        this.autor = usuario;
        this.curso = curso;
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoTopico dadosAtualizacaoTopico) {
        if (dadosAtualizacaoTopico.titulo() != null){
            this.titulo = dadosAtualizacaoTopico.titulo();
        }
        if (dadosAtualizacaoTopico.mensagem() != null){
            this.mensagem = dadosAtualizacaoTopico.mensagem();
        }
        if (dadosAtualizacaoTopico.status() != null){
            this.status = dadosAtualizacaoTopico.status();
        }
    }
}
