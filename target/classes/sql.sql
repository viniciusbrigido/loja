CREATE SEQUENCE bairro_id_seq;
CREATE TABLE bairro (
    id INTEGER NOT NULL DEFAULT nextval('bairro_id_seq'),
    nom_bairro VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE cor_id_seq;
CREATE TABLE cor (
    id INTEGER NOT NULL DEFAULT nextval('cor_id_seq'),
    nom_cor VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE marca_id_seq;
CREATE TABLE marca (
    id INTEGER NOT NULL DEFAULT nextval('marca_id_seq'),
    nom_marca VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE tamanho_id_seq;
CREATE TABLE tamanho (
    id INTEGER NOT NULL DEFAULT nextval('tamanho_id_seq'),
    nom_tamanho VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE tipo_id_seq;
CREATE TABLE tipo (
    id INTEGER NOT NULL DEFAULT nextval('tipo_id_seq'),
    nom_tipo VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE cidade_id_seq;
CREATE TABLE cidade (
    id INTEGER NOT NULL DEFAULT nextval('cidade_id_seq'),
    nom_cidade VARCHAR(40) NOT NULL,
    nom_uf VARCHAR(2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE endereco_id_seq;
CREATE TABLE endereco (
     id INTEGER NOT NULL DEFAULT nextval('endereco_id_seq'),
     nom_cep VARCHAR(10) NOT NULL,
     nom_logradouro VARCHAR(100) NOT NULL,
     id_bairro INTEGER,
     id_cidade INTEGER,
     PRIMARY KEY (id),
     CONSTRAINT bairro_fkey FOREIGN KEY(id_bairro) REFERENCES bairro(id),
     CONSTRAINT cidade_fkey FOREIGN KEY(id_cidade) REFERENCES cidade(id)
);

CREATE SEQUENCE cliente_id_seq;
CREATE TABLE cliente (
     id INTEGER NOT NULL DEFAULT nextval('cliente_id_seq'),
     nom_cliente VARCHAR(100) NOT NULL,
     nom_complemento VARCHAR(100),
     nom_email VARCHAR(100),
     num_cpf VARCHAR(14) NOT NULL,
     num_rg VARCHAR(12) NOT NULL,
     num_fone VARCHAR(14) NOT NULL,
     num_fone2 VARCHAR(14),
     dat_nascimento DATE NOT NULL,
     id_endereco INTEGER,
     PRIMARY KEY (id),
     CONSTRAINT endereco_fkey FOREIGN KEY(id_endereco) REFERENCES endereco(id)
);

CREATE SEQUENCE fornecedor_id_seq;
CREATE TABLE fornecedor (
     id INTEGER NOT NULL DEFAULT nextval('fornecedor_id_seq'),
     nom_fornecedor VARCHAR(100) NOT NULL,
     nom_razao_social VARCHAR(100) NOT NULL,
     nom_complemento VARCHAR(100),
     nom_email VARCHAR(100),
     num_cnpj VARCHAR(18) NOT NULL,
     nom_inscricao_estadual VARCHAR(14) NOT NULL,
     id_endereco INTEGER,
     PRIMARY KEY (id),
     CONSTRAINT endereco_fkey FOREIGN KEY(id_endereco) REFERENCES endereco(id)
);

CREATE SEQUENCE vendedor_id_seq;
CREATE TABLE vendedor (
     id INTEGER NOT NULL DEFAULT nextval('vendedor_id_seq'),
     nom_vendedor VARCHAR(100) NOT NULL,
     nom_complemento VARCHAR(100),
     nom_email VARCHAR(100),
     num_cpf VARCHAR(14) NOT NULL,
     num_fone VARCHAR(14) NOT NULL,
     num_fone2 VARCHAR(14),
     prc_comissao_venda NUMERIC,
     prc_comissao_recebimento NUMERIC,
     id_endereco INTEGER,
     PRIMARY KEY (id),
     CONSTRAINT endereco_fkey FOREIGN KEY(id_endereco) REFERENCES endereco(id)
);

CREATE SEQUENCE produto_id_seq;
CREATE TABLE produto (
    id INTEGER NOT NULL DEFAULT nextval('produto_id_seq'),
    nom_produto VARCHAR(100) NOT NULL,
    vlr_produto NUMERIC,
    id_marca INTEGER,
    id_tipo INTEGER,
    id_tamanho INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT marca_fkey FOREIGN KEY(id_marca) REFERENCES marca(id),
    CONSTRAINT tipo_fkey FOREIGN KEY(id_tipo) REFERENCES tipo(id),
    CONSTRAINT tamanho_fkey FOREIGN KEY(id_tamanho) REFERENCES tamanho(id)
);

CREATE SEQUENCE condicao_pagamento_id_seq;
CREATE TABLE condicao_pagamento (
    id INTEGER NOT NULL DEFAULT nextval('condicao_pagamento_id_seq'),
    nom_condicao_pagamento VARCHAR(100) NOT NULL,
    num_dias_ate_primeira_parcela INTEGER,
    num_dias_entre_parcela INTEGER,
    PRIMARY KEY (id)
);

CREATE SEQUENCE venda_id_seq;
CREATE TABLE venda (
    id INTEGER NOT NULL DEFAULT nextval('venda_id_seq'),
    num_serie VARCHAR(5) NOT NULL,
    dat_emissao DATE NOT NULL,
    vlr_total NUMERIC NOT NULL,
    dia_vencimento_parcela INT NOT NULL,
    id_cliente INT NOT NULL,
    id_condicao_pagamento INT NOT NULL,
    id_vendedor INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT cliente_fkey FOREIGN KEY(id_cliente) REFERENCES cliente(id),
    CONSTRAINT condicao_pagamento_fkey FOREIGN KEY(id_condicao_pagamento) REFERENCES condicao_pagamento(id),
    CONSTRAINT vendedor_fkey FOREIGN KEY(id_vendedor) REFERENCES vendedor(id)
);

CREATE TABLE fone_fornecedor (
    num_fone VARCHAR(14) NOT NULL,
    id_fornecedor INT NOT NULL,
    PRIMARY KEY (num_fone, id_fornecedor),
    CONSTRAINT fornecedor_fkey FOREIGN KEY(id_fornecedor) REFERENCES fornecedor(id)
);

CREATE SEQUENCE caracteristica_produto_id_seq;
CREATE TABLE caracteristica_produto (
    id INTEGER NOT NULL DEFAULT nextval('caracteristica_produto_id_seq'),
    num_tamanho VARCHAR(3) NOT NULL,
    cod_barras VARCHAR(13) NOT NULL,
    qtd_estoque NUMERIC NOT NULL,
    id_cor INT NOT NULL,
    id_produto INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT cor_fkey FOREIGN KEY(id_cor) REFERENCES cor(id),
    CONSTRAINT produto_fkey FOREIGN KEY(id_produto) REFERENCES produto(id)
);

CREATE SEQUENCE item_venda_id_seq;
CREATE TABLE item_venda (
    id INTEGER NOT NULL DEFAULT nextval('item_venda_id_seq'),
    qtd_produto NUMERIC NOT NULL,
    vlr_unitario NUMERIC NOT NULL,
    prc_desconto NUMERIC,
    id_caracteristica_produto INT NOT NULL,
    id_venda INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT caracteristica_produto_fkey FOREIGN KEY(id_caracteristica_produto) REFERENCES caracteristica_produto(id),
    CONSTRAINT venda_fkey FOREIGN KEY(id_venda) REFERENCES venda(id)
);

CREATE SEQUENCE receber_id_seq;
CREATE TABLE receber (
    id INTEGER NOT NULL DEFAULT nextval('receber_id_seq'),
    nom_status VARCHAR(1),
    vlr_emissao NUMERIC,
    vlr_acrescimo NUMERIC,
    vlr_desconto NUMERIC,
    vlr_pago NUMERIC,
    dat_emissao DATE,
    dat_vencimento DATE,
    dat_pagamento DATE,
    id_venda INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT venda_fkey FOREIGN KEY(id_venda) REFERENCES venda(id)
);

CREATE SEQUENCE compra_id_seq;
CREATE TABLE compra (
    id INTEGER NOT NULL DEFAULT nextval('compra_id_seq'),
    num_nf INT,
    num_serie_nf VARCHAR(5),
    vlr_total NUMERIC,
    dat_compra DATE,
    id_fornecedor INT,
    id_condicao_pagamento INT,
    PRIMARY KEY (id),
    CONSTRAINT fornecedor_fkey FOREIGN KEY(id_fornecedor) REFERENCES fornecedor(id),
    CONSTRAINT condicao_pagamento_fkey FOREIGN KEY(id_condicao_pagamento) REFERENCES condicao_pagamento(id)
);

CREATE SEQUENCE item_compra_id_seq;
CREATE TABLE item_compra (
   id INTEGER NOT NULL DEFAULT nextval('item_compra_id_seq'),
   qtd_produto NUMERIC NOT NULL,
   vlr_unitario NUMERIC NOT NULL,
   prc_desconto NUMERIC,
   id_caracteristica_produto INT NOT NULL,
   id_compra INT NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT caracteristica_produto_fkey FOREIGN KEY(id_caracteristica_produto) REFERENCES caracteristica_produto(id),
   CONSTRAINT compra_fkey FOREIGN KEY(id_compra) REFERENCES compra(id)
);

CREATE SEQUENCE pagar_id_seq;
CREATE TABLE pagar (
   id INTEGER NOT NULL DEFAULT nextval('pagar_id_seq'),
   vlr_emitido NUMERIC,
   vlr_acrescimo NUMERIC,
   vlr_desconto NUMERIC NOT NULL,
   vlr_pago NUMERIC,
   nom_status VARCHAR(1),
   dat_vencimeto DATE,
   dat_pagamento DATE,
   dat_emissao DATE,
   id_compra INT,
   PRIMARY KEY (id),
   CONSTRAINT compra_fkey FOREIGN KEY(id_compra) REFERENCES compra(id)
);