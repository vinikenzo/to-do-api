ALTER TABLE tarefas
ADD CONSTRAINT fk_tarefas_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id);