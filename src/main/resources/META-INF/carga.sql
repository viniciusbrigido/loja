INSERT INTO bairro (nom_bairro) VALUES 
('CENTRO'), 
('DEHON'),
('MORROTES'),
('SÃO CLEMENTE'),
('COMASA'),
('RECIFE'),
('HUMAITA'),
('REVOREDO'),
('OFICINAS'),
('PASSAGEM');

INSERT INTO cidade (nom_cidade, nom_uf) VALUES 
('TUBARÃO', 'SC'),
('CAPIVA', 'SC'),
('CAMACHO', 'SC'),
('JAGUARUNA', 'SC'),
('PORTO ALEGRE', 'RS'),
('VITORIA', 'ES'),
('CURITIBA', 'PR'),
('PASSA TEMPO', 'MG'),
('NATAL', 'RN'),
('SAO PAULO', 'SP');

INSERT INTO endereco (nom_cep, nom_logradouro, id_bairro, id_cidade) VALUES
('12345678', 'PROXIMO A NADA', 1, 1),
('6548794', 'LONGE', 2, 2),
('88701610', 'CASA DA ESQUINA', 3, 3),
('9987231', 'PERTO DO MERCADO', 4, 4),
('6546549', 'RUA TAL', 5, 5),
('2544694', 'ACABOU A CRIA', 6, 6),
('5465446', 'SLA', 7, 7),
('6544948', 'TA', 8, 8),
('5132498', 'ONDE JUDAS', 9, 9),
('4984989', 'PERTO DAI', 10, 10);

INSERT INTO tipo (nom_tipo) VALUES 
('ROUPA'), 
('ACESSORIO'),
('ELETRONICO'),
('FISICO'),
('ELETRODOMESTICO'),
('CONSOLE'),
('TESTE'),
('ORGANICO'),
('LIQUIDO'),
('SOLIDO');

INSERT INTO cor (nom_cor) VALUES 
('AZUL'), 
('AMARELO'),
('VERDE'),
('VERMELHO'),
('PRETO'),
('BRANCO'),
('ROSA'),
('ROXO'),
('VIOLETA'),
('TURQUESA');

INSERT INTO tamanho (nom_tamanho) VALUES 
('G'), 
('GG'),
('GGG'),
('P'),
('PP'),
('PPP'),
('M'),
('MM'),
('MMM'),
('S');

INSERT INTO marca (nom_marca) VALUES 
('NIKE'), 
('ADIDAS'),
('PUMA'),
('BALENCIAGA'),
('TYGOR'),
('LILICA REPILICA'),
('LACOSTE'),
('CALVIN KLEIN'),
('MORMAII'),
('GUCCI');

INSERT INTO condicao_pagamento (nom_condicao_pagamento, num_dias_ate_primeira_parcela, num_dias_entre_parcela) VALUES 
('DINHEIRO', 0, 0), 
('PIX', 0, 0),
('CARTAO CREDITO', 25, 45),
('CARTAO DEBITO', 25, 45),
('CREDIARIO', 30, 60),
('CONVENIO', 40, 15),
('CHEQUE', 60, 80),
('BOLETO', 30, 25),
('PIC PAY', 35, 10),
('AGIOTA', 40, 0);

INSERT INTO produto (nom_produto, vlr_produto, id_marca, id_tipo, id_tamanho) VALUES 
('CELULAR', 2500.0, 1, 1, 1),
('TECLADO', 125.0, 2, 2, 2),
('MOUSE', 50.0, 3, 3, 3),
('CABO', 25.0, 4, 4, 4),
('COMPUTADOR', 5500.0, 5, 5, 5),
('MOCHILA', 80.0, 6, 6, 6),
('LAPIS', 0.25, 7, 7, 7),
('MONITOR', 500.0, 8, 8, 8),
('AR CONDICIONADO', 1900.0, 9, 9, 9),
('CADEIRA', 25.0, 10, 10, 10);

INSERT INTO caracteristica_produto (num_tamanho, cod_barras, qtd_estoque, id_produto, id_cor) VALUES
('1', '7891182026710', 2500.0, 1, 1),
('2', '7891182026734', 125.0, 1, 2),
('3', '7896422504225', 50.0, 1, 3),
('4', '7898040320508', 25.0, 1, 4),
('5', '7896112169900', 5500.0, 1, 5),
('6', '7891000089798', 80.0, 1, 6),
('7', '7891000089981', 25.0, 1, 7),
('8', '7895465464644', 500.0, 1, 8),
('9', '7896112165539', 1900.0, 1, 9),
('10', '7891268402022', 25.0, 1, 10);


INSERT INTO vendedor (nom_vendedor, nom_complemento, nom_email, num_cpf, prc_comissao_venda, prc_comissao_recebimento, id_endereco, num_fone) VALUES
('JORGIN', 'PERTO', 'jorgin@tub.com', '12345678901', 25.0, 1.0, 1, '48769242104'),
('PEDRIN', 'LONGE', 'pedrin@tub.com', '20818530600', 25.0, 11.0, 2, '69351814451'),
('CARLIN', 'ATA', 'carlin@tub.com', '84605171363', 5.0, 16.0, 3, '71692854937'),
('ANINHA', 'LAA', 'aninha@tub.com', '08138084138', 50.0, 21.0, 4, '72518188818'),
('MARIAZINHA', 'ALI', 'mariaziha@tub.com', '74273828898', 51.0, 31.0, 5, '11466261391'),
('CARLINHA', 'ACOLA', 'carlinha@tub.com', '87166887400', 28.0, 54.0, 6, '13604250884'),
('JOAOZIN', 'AQUI', 'joaozin@tub.com', '28122137105', 23.0, 23.0, 7, '36603184577'),
('MARCIN', 'AMAS', 'marcin@tub.com', '37227562271', 90.0, 21.0, 8, '68122554998'),
('MARCINHA', 'CERITN', 'marcinha@tub.com', '62484855265', 65.0, 11.0, 9, '35150593982'),
('ALEX', 'SEINAO', 'alex@tub.com', '43645006583', 15.0, 35.0, 10, '50328112612');

INSERT INTO cliente (nom_cliente, nom_complemento, num_cpf, num_rg, num_fone, dat_nascimento, id_endereco) VALUES
('JORGIN', 'PERTO', '12345678901', '496996666', '48769242104', '2000-06-25', 1),
('PEDRIN', 'LONGE', '20818530600', '430354964', '69351814451', '1983-07-11', 2),
('CARLIN', 'ATA', '84605171363', '334585351', '71692854937', '1999-10-15', 3),
('ANINHA', 'LAA', '08138084138', '145081941', '72518188818', '2001-12-06', 4),
('MARIAZINHA', 'ALI', '74273828898', '263526379', '11466261391', '2008-01-17', 5),
('CARLINHA', 'ACOLA', '87166887400', '349016069', '13604250884', '2010-12-09', 6),
('JOAOZIN', 'AQUI', '28122137105', '224112272', '36603184577', '2003-11-16', 7),
('MARCIN', 'AMAS', '37227562271', '106816895', '68122554998', '1996-08-01', 8),
('MARCINHA', 'CERITN', '62484855265', '441670623', '35150593982', '1997-07-25', 9),
('ALEX', 'SEINAO', '43645006583', '164089834', '50328112612', '1958-12-26', 10);

INSERT INTO fornecedor (nom_fornecedor, nom_complemento, nom_razao_social, num_cnpj, nom_inscricao_estadual, id_endereco) VALUES
('PODIUM INFORMATICA', 'PERTO', 'SLAVEI', '73607509000170', '0962382779', 1),
('PANARELLO DIST FARM', 'LONGE', 'PANAAAAA', '02678694000227', '0962382779', 1),
('RECMED COM DE MAT. HOSP', 'ATA', 'RECCC', '02827863000162', '718040933111', 1),
('UTILDROGAS DIS PROD', 'LAA', 'UTILLL', '03826417000104', '101619170', 1),
('MILENIO DIST DE PROD', 'ALI', 'MILEEEE', '06098319000188', '103276963', 1),
('FARMALINK', 'ACOLA', 'FARMAAAA', '02642356000154', '103725822', 1),
('MILENIO DIST DE PROD', 'AQUI', 'MILLAE', '04406262000101', '103057676', 1),
('KADOCHE COSMETICOS', 'AMAS', 'KADOO', '33265018000174', '260140130118', 1),
('FOCCUS', 'CERITN', 'FOCCU', '03669416000195', '102036497', 1),
('SOL PRIMA DISTRIBUIDORA', 'SEINAO', 'SOLL', '04259314000598', '102036497', 1);