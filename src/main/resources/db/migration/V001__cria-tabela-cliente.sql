create table cliente(
    id SERIAL CONSTRAINT pk_id_cliente PRIMARY KEY,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null
);