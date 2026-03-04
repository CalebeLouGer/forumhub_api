package br.com.forumhub.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(
        String mensagem,
        String autor,
        LocalDateTime dataCriacao
) {
    public DadosListagemResposta(Resposta resposta) {
        this(resposta.getMensagem(),
                resposta.getAutor().getNome(),
                resposta.getDataCriacao()
        );
    }
}

