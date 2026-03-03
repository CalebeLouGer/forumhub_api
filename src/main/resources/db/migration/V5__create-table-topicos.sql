CREATE TABLE topicos (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensagem TEXT NOT NULL,
    dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'NAO_RESPONDIDO',
    autor_id INTEGER REFERENCES usuarios(id),
    curso_id INTEGER REFERENCES cursos(id)
);