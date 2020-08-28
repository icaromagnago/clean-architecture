CREATE TABLE state (
	id SERIAL PRIMARY KEY,
	uf CHAR(2) NOT NULL,
	name VARCHAR(25) NOT NULL
);

INSERT INTO state (name, uf) values ('Acre', 'AC');
INSERT INTO state (name, uf) values ('Alagoas', 'AL');
INSERT INTO state (name, uf) values ('Amapá', 'AP');
INSERT INTO state (name, uf) values ('Amazonas', 'AM');
INSERT INTO state (name, uf) values ('Bahia', 'BA');
INSERT INTO state (name, uf) values ('Ceará', 'CE');
INSERT INTO state (name, uf) values ('Distrito Federal', 'DF');
INSERT INTO state (name, uf) values ('Espírito Santo', 'ES');
INSERT INTO state (name, uf) values ('Goiás', 'GO');
INSERT INTO state (name, uf) values ('Maranhão', 'MA');
INSERT INTO state (name, uf) values ('Mato Grosso', 'MT');
INSERT INTO state (name, uf) values ('Mato Grosso do Sul', 'MS');
INSERT INTO state (name, uf) values ('Minas Gerais', 'MG');
INSERT INTO state (name, uf) values ('Pará', 'PA');
INSERT INTO state (name, uf) values ('Paraíba', 'PB');
INSERT INTO state (name, uf) values ('Paraná', 'PR');
INSERT INTO state (name, uf) values ('Pernambuco', 'PE');
INSERT INTO state (name, uf) values ('Piauí', 'PI');
INSERT INTO state (name, uf) values ('Rio de Janeiro', 'RJ');
INSERT INTO state (name, uf) values ('Rio Grande do Norte', 'RN');
INSERT INTO state (name, uf) values ('Rio Grande do Sul', 'RS');
INSERT INTO state (name, uf) values ('Rondônia', 'RO');
INSERT INTO state (name, uf) values ('Roraima', 'RR');
INSERT INTO state (name, uf) values ('Santa Catarina', 'SC');
INSERT INTO state (name, uf) values ('São Paulo', 'SP');
INSERT INTO state (name, uf) values ('Sergipe', 'SE');
INSERT INTO state (name, uf) values ('Tocantins', 'TO');

