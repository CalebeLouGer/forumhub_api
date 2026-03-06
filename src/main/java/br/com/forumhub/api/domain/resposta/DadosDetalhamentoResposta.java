package br.com.forumhub.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        LocalDateTime dataCriacao,
        String autor
) {
    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getAutor().getNome()
        );
    }
}
