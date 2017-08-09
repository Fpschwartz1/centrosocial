CREATE TABLE agenda (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_agendamento DATE NOT NULL,
	codigo_paciente BIGINT(20) NOT NULL,
    codigo_usuarioprofissional BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
    FOREIGN KEY (codigo_usuarioprofissional) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO agenda (data_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2017-05-12', 3, 2);
INSERT INTO agenda (data_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2017-05-02', 3, 3);
INSERT INTO agenda (data_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2017-05-02', 4, 3);
