create table if not exists personajes (
       nombre         text not null,
       procedencia    text not null,
       especie        text not null,
       edad           int  not null,
       altura         int,
       peso           int,
       sexo           text not null,
       preferencia_1  text not null,
       preferencia_2  text,
       preferencia_3  text,
       objeto_inicial text,
       personalidad   text,
       habilidades    text,
       fisico         text,
       primary key (nombre)
);

create table if not exists estadisticas_personajes (
       nombre       text not null,
       vitalidad    int not null,
       resistencia  int not null,
       fuerza       int not null,
       velocidad    int not null,
       inteligencia int not null,
       punteria     int not null,
       magia        int not null,
       destreza     int not null,
       primary key (nombre),
       foreign key (nombre) references personajes(nombre)
);

delete from personajes;
delete from estadisticas_personajes;

insert into personajes values
('Marea', 'Los bosques calidos del norte', 'Elfo elemental', 16, 1.6,
50, 'Femenino', 'Arco', 'Daga', 'Hierbas, pociones o vendas', 'Arco',
null, null, null);

insert into estadisticas_personajes values
('Marea', 2, 2, 1, 5, 5, 5, 5, 5);
