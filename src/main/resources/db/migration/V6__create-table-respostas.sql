CREATE TABLE respostas (
    id SERIAL PRIMARY KEY,
    mensagem TEXT NOT NULL,
    topico_id INTEGER REFERENCES topicos(id) ON DELETE CASCADE,
    dataCriacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    autor_id INTEGER REFERENCES usuarios(id),
    solucao BOOLEAN DEFAULT FALSE
);