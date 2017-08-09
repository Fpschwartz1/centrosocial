CREATE TABLE tipoitemprontuario (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_grupo BIGINT(20), /* NOT NULL */
    tipo_item VARCHAR(1) NOT NULL, /* F - Ficha; P - Procedimento; A - Anamnese */
    tipo_campo VARCHAR(1) NOT NULL, /* T - Text; C - Combo */
    indicador_sigilo BIT(1) NOT NULL DEFAULT FALSE,
    nome VARCHAR(80) NOT NULL,
    FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE opcoescampoitem (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    codigo_tipoitemprontuario BIGINT(20) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    FOREIGN KEY (codigo_tipoitemprontuario) REFERENCES tipoitemprontuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Fonoaudiologia - Ficha */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Alimentação');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'C', 1, 'Amamentação');
	INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (2, 'Sólida');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (2, 'Líquida');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (2, 'Pastosa');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (2, 'Variada');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Ambiente Escolar');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Ambiente Familiar');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Controle Esfíncters');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Desenvolvimento da Linguagem');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'C', 1, 'Desenvolvimento Neuropsicomotor');
	INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (7, 'Sustentação da Cabeça');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (7, 'Rolar');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (7, 'Sentar');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (7, 'Engatinhar');
	INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (7, 'Andar');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Gestação');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'C', 1, 'Hábitos Viciosos');
    INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (9, 'Sucção Digital');
	INSERT INTO opcoescampoitem (codigo_tipoitemprontuario, nome) VALUES (9, 'Chupeta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Queixa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Relatório Reunião');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Relatório Técnico');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'F', 'T', 1, 'Doenças');
/* Fonoaudiologia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'P', 'T', 1, 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'P', 'T', 1, 'Fonoterapia');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (8, 'P', 'T', 1, 'Acelerador de Partículas');

/* Fisioterapia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Cataplasma de Barro');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Avaliação');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Ultrasom');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Esteira');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Escada/Rampa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Balanço');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Barras');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Bola Suíça');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Infravermelho');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Cinesioter RM');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Alongamento');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Tens');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Espaldar');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Alongamento Panturrilha');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'RPG');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Pilates');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Adutor');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'FES');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Roda de Ombro');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Bicicleta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Estepe');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Alta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (7, 'P', 'T', 'Acelerador de Partículas');

/* Acupuntura - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (2, 'P', 'T', 'Espiral');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (2, 'P', 'T', 'Mush Bustão');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (2, 'P', 'T', 'Sessão de Acupuntura');

/* Clínica Geral - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Prescrição Medicamentosa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Encaminhamento');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Amostra Grátis');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Pedido de Exame');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Preventivo');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Teste de Glicose');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Aferição de Pressão Arterial');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'P', 'T', 'Acelerador de Partículas');

/* Clínica Geral - Anamnese */
/*
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'C', 'Aborto');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Alergia a medicamentos');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Altura');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Peso');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'R', 'Diabetes');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'R', 'Hipertensão');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'História familiar');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'História pregressa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Medicamentos');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Parto');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'C', 'Filhos vivos');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'C', 'Filhos mortos');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (4, 'A', 'T', 'Vacinas');
*/
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'C', 'Aborto');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Alergia a medicamentos');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Altura');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Peso');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Diabetes');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Hipertensão');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'História familiar');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'História pregressa');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Medicamentos');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Parto');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'C', 'Filhos vivos');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'C', 'Filhos mortos');
INSERT INTO tipoitemprontuario (tipo_item, tipo_campo, nome) VALUES ('A', 'T', 'Vacinas');


/* Neuropediatria - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Prescrição Medicamentosa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Encaminhamento');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Amostra Grátis');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Pedido de Exame');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (9, 'P', 'T', 'Acelerador de Partículas');

/* Odontologia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Exame Clínico');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Urgência');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Remoção Tártaro');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Aplicação Flúor');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Restauração');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Extração');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Tratamento Canal');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Molde');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (11, 'P', 'T', 'Prótese');

/* Psicologia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, indicador_sigilo, nome) VALUES (14, 'P', 'T', 1, 'Psicoterapia');

/* Enfermagem - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Preservativos');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Anticoncepcional');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Aferição de Pressão Arterial');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Teste de Glicose');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Medicação Injetável');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Medicação Oral');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Curativo');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Nebulização');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (6, 'P', 'T', 'Fornecimento de Medicamento');

/* Ortopedia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (12, 'P', 'T', 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (12, 'P', 'T', 'Prescrição Medicamentosa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (12, 'P', 'T', 'Encaminhamento');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (12, 'P', 'T', 'Amostra Grátis');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (12, 'P', 'T', 'Pedido de Exame');

/* Pediatria - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Prescrição Medicamentosa');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Encaminhamento');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Amostra Grátis');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Pedido de Exame');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (13, 'P', 'T', 'Acelerador de Partículas');

/* Nutrição - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Consulta');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Retorno');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Cloro');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Multimistura');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Miojo');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Leite');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (15, 'P', 'T', 'Cálcio');

/* Advocacia - Procedimentos */
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Família');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Civil');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Juizado Cível');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Trabalhista');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Previdenciário');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Indenizações');
INSERT INTO tipoitemprontuario (codigo_grupo, tipo_item, tipo_campo, nome) VALUES (3, 'P', 'T', 'Consulta');










