create table comentario(
    id SERIAL CONSTRAINT pk_id_comentario PRIMARY KEY,
    ordem_servico_id int not null,
    descricao varchar(255) not null,
    data_envio timestamp not null
);

alter table comentario add constraint fk_comentario_ordem_servico
foreign key (ordem_servico_id) references ordem_servico(id);
