package br.com.forumhub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem
) {
}
