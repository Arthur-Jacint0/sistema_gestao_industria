create database industria;
use industria;

create table setor 
(
    id_setor int primary key auto_increment,
    nome_setor varchar(40) not null,
    responsavel text
);

create table funcionario 
(
    id_funcionario int primary key auto_increment,
    nome varchar(40) not null,
    sobrenome varchar(40) not null,
    id_setor int not null,
    foreign key(id_setor) references setor(id_setor)
        on delete cascade 
        on update cascade
);

create table produtos
(
    id_produtos int primary key auto_increment,
    nome_produto varchar(40) not null,
    descricao text
);

create table producao
(
    id_producao int primary key auto_increment,
    id_produtos int not null,
    id_funcionario int not null,
    data_producao varchar(10) not null,
    quantidade int not null,
    foreign key(id_produtos) references produtos(id_produtos)
        on delete cascade 
        on update cascade,
    foreign key(id_funcionario) references funcionario(id_funcionario)
        on delete cascade 
        on update cascade
);


insert into setor(nome_setor, responsavel)
values("Logística", "Roberto"),
       ("Recursos Humanos", "Fernanda"),
       ("Tecnologia", "Carlos"),
       ("Financeiro", "Juliana"),
       ("Marketing", "Lucas");

insert into funcionario(nome, sobrenome, id_setor)
values("Lucas", "Dos Santos Crispim", 1),
       ("Ana", "Silva Souza", 2),
       ("Carlos", "Oliveira Santos", 3),
       ("Mariana", "Ferreira Gomes", 4),
       ("João", "Pereira Costa", 5),
       ("Fernanda", "Lima Rocha", 1),
       ("Pedro", "Martins Oliveira", 2),
       ("Juliana", "Ribeiro Alves", 3),
       ("Ricardo", "Almeida Pereira", 4),
       ("Patrícia", "Sousa Ferreira", 5),
       ("Gabriel", "Dias Silva", 1),
       ("Cláudia", "Barbosa Pinto", 2),
       ("Felipe", "Mendes Carvalho", 3),
       ("Isabela", "Nascimento Ferreira", 4),
       ("Vinícius", "Cavalcanti Ribeiro", 5),
       ("Aline", "Cardoso Costa", 1),
       ("Tiago", "Gomes Martins", 2),
       ("Luciana", "Pinto Silva", 3),
       ("Ricardo", "Fernandes Lima", 4),
       ("Sofia", "Costa Almeida", 5);
       
insert into produtos(nome_produto, descricao)
values("Caderno", "Caderno espiral de 100 folhas, capa dura"),
       ("Caneta", "Caneta esferográfica azul com tinta de alta qualidade"),
       ("Lápis", "Lápis grafite 2B, ideal para escrita e desenhos"),
       ("Mochila", "Mochila de nylon com compartimentos para laptop"),
       ("Camiseta", "Camiseta de algodão, disponível em várias cores"),
       ("Fones de ouvido", "Fones de ouvido sem fio, com cancelamento de ruído"),
       ("Computador", "Desktop com processador i7, 16GB de RAM e 512GB SSD"),
       ("Monitor", "Monitor LED de 24 polegadas, resolução 1080p"),
       ("Impressora", "Impressora jato de tinta, multifuncional com Wi-Fi"),
       ("Mouse", "Mouse ergonômico com cabo e botão extra"),
       ("Teclado", "Teclado mecânico, retroiluminado, com switches táteis"),
       ("Cafeteira", "Cafeteira elétrica, capacidade para 10 xícaras"),
       ("Batedeira", "Batedeira elétrica com 5 velocidades e tigela de 3L"),
       ("Ar condicionado", "Ar condicionado Split, 12000 BTUs, econômico"),
       ("Geladeira", "Geladeira duplex, frost free, capacidade de 380L"),
       ("Micro-ondas", "Micro-ondas 20L, com 10 funções preprogramadas"),
       ("Secador de cabelo", "Secador de cabelo com 2 velocidades e 3 temperaturas"),
       ("Luminária", "Luminária de mesa LED com ajuste de intensidade"),
       ("Tênis", "Tênis de corrida, confortável e leve, ideal para atividades físicas"),
       ("Relógio", "Relógio de pulso digital, resistente à água, com cronômetro");
       
insert into producao(id_produtos, id_funcionario, data_producao, quantidade)
values(1, 1, '2025-06-01', 100),
       (2, 2, '2025-06-02', 150),
       (3, 3, '2025-06-03', 200),
       (4, 4, '2025-06-04', 120),
       (5, 5, '2025-06-05', 80),
       (1, 6, '2025-06-06', 90),
       (2, 7, '2025-06-07', 110),
       (3, 8, '2025-06-08', 130),
       (4, 9, '2025-06-09', 140),
       (5, 10, '2025-06-10', 170),
       (1, 11, '2025-06-11', 160),
       (2, 12, '2025-06-12', 180),
       (3, 13, '2025-06-13', 150),
       (4, 14, '2025-06-14', 200),
       (5, 15, '2025-06-15', 220),
       (1, 16, '2025-06-16', 250),
       (2, 17, '2025-06-17', 230),
       (3, 18, '2025-06-18', 190),
       (4, 19, '2025-06-19', 170),
       (5, 20, '2025-06-20', 210);

select * from funcionario where id_setor = 1;
select * from producao where data_producao = "2025-06-20";
select * from producao inner join produtos on producao.id_produtos = produtos.id_produtos where id_funcionario = 1;
select * from funcionario inner join setor on funcionario.id_setor = setor.id_setor inner join producao on funcionario.id_funcionario = producao.id_funcionario; 
select * from producao inner join funcionario on producao.id_funcionario = funcionario.id_funcionario inner join setor on funcionario.id_setor = setor.id_setor where data_producao = "2025-06-20";


