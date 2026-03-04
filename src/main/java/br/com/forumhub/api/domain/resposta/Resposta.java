package br.com.forumhub.api.domain.resposta;

import br.com.forumhub.api.domain.topico.Topico;
import br.com.forumhub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @Column(name = "datacriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    public Resposta(DadosCadastroResposta dadosCadastroResposta, Usuario autor, Topico topico) {
        this.mensagem = dadosCadastroResposta.mensagem();
        this.autor = autor;
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }
}

