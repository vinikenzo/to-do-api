create table tarefas(
    id bigint primary key auto_increment,
    titulo varchar(255) not null,
    descricao varchar(255),
    concluida boolean not null,
    data_criacao datetime not null
);