create table ordem_servico(
    id SERIAL CONSTRAINT pk_id_ordem_servico PRIMARY KEY,
    cliente_id int not null,
    descricao varchar(60) not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    data_abertura timestamp not null,
    data_finalizacao timestamp not null
);

alter table ordem_servico add constraint fk_ordem_servico_cliente
foreign key (cliente_id) references cliente(id);
