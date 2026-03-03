CREATE TABLE usuario_perfil (
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE CASCADE,
    perfil_id INTEGER REFERENCES perfis(id) ON DELETE CASCADE,
    PRIMARY KEY (usuario_id, perfil_id)
);