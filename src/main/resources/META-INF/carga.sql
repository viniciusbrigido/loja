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