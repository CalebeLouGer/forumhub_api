package br.com.forumhub.api.domain.topico;

import br.com.forumhub.api.domain.resposta.DadosListagemResposta;
import br.com.forumhub.api.domain.resposta.Resposta;

import java.time.LocalDateTime;
import java.util.List;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        String autor,
        String curso,
        List<DadosListagemResposta> respostas
) {
    public DadosListagemTopico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome(),
                topico.getRespostas()
                        .stream()
                        .filter(Resposta::getAtivo)
                        .map(DadosListagemResposta::new)
                        .toList()
        );
    }
}
