CREATE TABLE respostas (
    id BIGINT PRIMARY KEY,
    mensagem VARCHAR(200) NOT NULL,
    topico_id BIGINT REFERENCES topicos(id) ON DELETE CASCADE,
    dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autor_id BIGINT REFERENCES usuarios(id)
);