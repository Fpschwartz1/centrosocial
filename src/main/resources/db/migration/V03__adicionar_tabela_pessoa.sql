CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    data_nascimento DATE NOT NULL,
    codigo_naturalidade BIGINT(20),
    nome_pai VARCHAR(80),
    nome_mae VARCHAR(80),
    estado_civil INT(11),
    escolaridade INT(11),
    profissao VARCHAR(80),
    tipo_documento INT(11),
    numero_documento VARCHAR(40),
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(15),
    complemento VARCHAR(20),
    cep VARCHAR(15),
    codigo_cidade BIGINT(20),
    completo BIT(1) NOT NULL DEFAULT FALSE,
    FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo),
    FOREIGN KEY (codigo_naturalidade) REFERENCES cidade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE pessoa
	ADD INDEX(numero_documento);
	
ALTER TABLE pessoa
	ADD INDEX(nome);	
	
INSERT INTO pessoa (nome, sexo, data_nascimento, nome_pai, nome_mae, estado_civil, escolaridade, profissao, tipo_documento, telefone, email) VALUES ('Fabiano Peruzzo Schwartz', 'M', '1970-10-06', '', '', 0, 0, '', 0, '(61) 98411-9128', '');
INSERT INTO pessoa (nome, sexo, data_nascimento, nome_pai, nome_mae, estado_civil, escolaridade, profissao, tipo_documento, telefone, email) VALUES ('Flávia Procaci Godinho Schwartz', 'F', '1973-04-15', '', '', 0, 0, '', 0, '(61) 99816-5454', '');
INSERT INTO pessoa (nome, sexo, data_nascimento, nome_pai, nome_mae, estado_civil, escolaridade, profissao, tipo_documento, telefone, email) VALUES ('Diogo Godinho Schwartz', 'M', '2003-09-18', '', '', 0, 0, '', 0, '(61) 98575-1809', '');
INSERT INTO pessoa (nome, sexo, data_nascimento, nome_pai, nome_mae, estado_civil, escolaridade, profissao, tipo_documento, telefone, email) VALUES ('Felizberto da Silva', 'M', '1980-11-18', '', '', 0, 0, '', 0, '(61) 99975-1979', '');
INSERT INTO pessoa (nome, sexo, data_nascimento, nome_pai, nome_mae, estado_civil, escolaridade, profissao, tipo_documento, telefone, email) VALUES ('Cesarina Benac', 'F', '1967-04-15', '', '', 0, 0, '', 0, '(21) 99816-5454', '');