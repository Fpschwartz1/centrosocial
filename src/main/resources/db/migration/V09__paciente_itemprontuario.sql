CREATE TABLE paciente (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_pessoa BIGINT(20) NOT NULL,
    fone_adicional VARCHAR(20),
    fone_recado VARCHAR(20),
    fone_emergencia VARCHAR(20),    
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
    UNIQUE (codigo_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE itemprontuario (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_paciente BIGINT(20) NOT NULL,
    codigo_usuarioprofissional BIGINT(20) NOT NULL,
    nome_grupo VARCHAR(50) NOT NULL, /* redundancia controlada */
    tipo_item VARCHAR(1) NOT NULL,   /* redundancia controlada */
    codigo_tipoitemprontuario BIGINT(20) NOT NULL,
    codigo_opcaocampoitem BIGINT(20),
    valor VARCHAR(255) NOT NULL,
    data_lancamento DATETIME NOT NULL DEFAULT "2017-07-28", /* NOW() */
    FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
    FOREIGN KEY (codigo_usuarioprofissional) REFERENCES usuario(codigo),
    FOREIGN KEY (codigo_tipoitemprontuario) REFERENCES tipoitemprontuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE itemprontuario
	ADD INDEX(nome_grupo);

INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (1, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');
INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (2, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');
INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (3, '(61) 3367-4792', '(61) 3273-5000', '(61) 3273-1558');
INSERT INTO paciente (codigo_pessoa, fone_adicional, fone_recado, fone_emergencia) VALUES (4, '(61) 3333-3333', '(61) 3273-5555', '(61) 3273-4444');

INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 2, 'Fonoaudiologia', 'F', 1, "Saudável");
INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 2, 'Fonoaudiologia', 'F', 3, "Equilibrado, faz amigos, notas boas.");
INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 3, 'Clínica Geral',  'P', 48, "Vacina HPV");
INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 3, 'Clínica Geral',  'P', 47, "Raio X - crescimento");
INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 3, 'Clínica Geral',  'A', 54, "1,70");
INSERT INTO itemprontuario (codigo_paciente, codigo_usuarioprofissional, nome_grupo, tipo_item, codigo_tipoitemprontuario, valor) VALUES (3, 3, 'Clínica Geral',  'A', 55, "63");