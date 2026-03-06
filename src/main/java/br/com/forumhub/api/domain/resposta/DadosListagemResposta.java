package br.com.forumhub.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(
        Long id,
        String mensagem,
        String autor,
        LocalDateTime dataCriacao
) {
    public DadosListagemResposta(Resposta resposta) {
        this(resposta.getId(),
                resposta.getMensagem(),
                resposta.getAutor().getNome(),
                resposta.getDataCriacao()
        );
    }
}

