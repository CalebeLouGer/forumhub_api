package br.com.forumhub.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        String mensagem,
        LocalDateTime dataCriacao,
        String autor
) {
    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome()
        );
    }
}
