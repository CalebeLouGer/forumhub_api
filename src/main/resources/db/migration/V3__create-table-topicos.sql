CREATE TABLE topicos (
    id BIGINT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensagem VARCHAR(200) NOT NULL,
    dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'NAO_RESPONDIDO',
    autor_id BIGINT REFERENCES usuarios(id),
    curso_id BIGINT REFERENCES cursos(id)
);