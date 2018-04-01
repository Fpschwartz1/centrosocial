CREATE TABLE agenda (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_agendamento DATE NOT NULL,
	hora_agendamento VARCHAR(5) NOT NULL,
	codigo_paciente BIGINT(20),
    codigo_usuarioprofissional BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_paciente) REFERENCES paciente(codigo),
    FOREIGN KEY (codigo_usuarioprofissional) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO agenda (data_agendamento, hora_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2018-03-31', '08:15', 3, 2);
INSERT INTO agenda (data_agendamento, hora_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2018-03-31', '08:15', 3, 3);
INSERT INTO agenda (data_agendamento, hora_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2018-03-31', '08:00', 4, 3);
INSERT INTO agenda (data_agendamento, hora_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2018-03-31', '08:00', NULL, 2);
INSERT INTO agenda (data_agendamento, hora_agendamento, codigo_paciente, codigo_usuarioprofissional) VALUES ('2018-03-31', '08:00', 4, 4);