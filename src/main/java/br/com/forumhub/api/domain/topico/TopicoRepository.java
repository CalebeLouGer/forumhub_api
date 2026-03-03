package br.com.forumhub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
        SELECT t FROM Topico t
        JOIN FETCH t.autor
        JOIN FETCH t.curso
        WHERE t.id = :id
    """)
    Optional<Topico> buscarDetalhado(Long id);

    Page<Topico> findAllByAtivoTrue(Pageable paginacao);
}
