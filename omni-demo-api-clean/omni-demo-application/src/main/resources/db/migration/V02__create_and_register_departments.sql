CREATE TABLE department (
	id SERIAL PRIMARY KEY,
	code INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	local VARCHAR(200) NOT NULL,
	city VARCHAR(50) NOT NULL,
	board VARCHAR(20) NOT NULL,
	state_id INTEGER NOT NULL, 
	FOREIGN KEY (state_id) REFERENCES state(id)
);

INSERT INTO department (code, name, local, city, board, state_id) values ('1', 'Recursos Humanos', 'Rua Francisco, 20', 'São Paulo', 'EIS', 25);
INSERT INTO department (code, name, local, city, board, state_id) values ('2', 'Financeiro', 'Rua Francisco, 20', 'São Paulo', 'RECOVERY', 25);
INSERT INTO department (code, name, local, city, board, state_id) values ('3', 'Administração', 'Av. Copacabana, 100', 'Rio de Janeiro', 'BUSINESS', 19);