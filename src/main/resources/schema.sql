-- API ii
-- CRIANDO TABELAS E RELACIONAMENTOS

-- Descarta tabela Inventario_Produto (se existir)
DROP TABLE IF EXISTS Inventario_Produto;

-- Descarta tabela Produto (se existir)
DROP TABLE IF EXISTS Produto;

-- Descarta tabela Inventario (se existir)
DROP TABLE IF EXISTS Inventario;

-- Descarta tabela Relatorio (se existir)
DROP TABLE IF EXISTS Relatorio;

-- Descarta tabela Rebelde (se existir)
DROP TABLE IF EXISTS Rebelde;

-- Descarta tabela Localizacao (se existir)
DROP TABLE IF EXISTS Localizacao;

-- Cria tabela localizacao
CREATE TABLE Localizacao (
    id SERIAL PRIMARY KEY,
    ip VARCHAR(255)
);

-- cria tabela rebelde
CREATE TABLE Rebelde (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    genero VARCHAR(255),
    idade INTEGER,
    status BOOLEAN,
    localizacao_id BIGINT,
    inventario_id BIGINT,
    relatorio_id BIGINT,
    FOREIGN KEY (localizacao_id) REFERENCES Localizacao(id),
    FOREIGN KEY (inventario_id) REFERENCES Inventario(id),
    FOREIGN KEY (relatorio_id) REFERENCES Relatorio(id)
);

-- cria tabela relatorio
CREATE TABLE Relatorio (
    id SERIAL PRIMARY KEY,
    quantidade_relatorio INTEGER,
    rebelde_id BIGINT,
    FOREIGN KEY (rebelde_id) REFERENCES Rebelde(id)
);

-- cria tabela inventario
CREATE TABLE Inventario (
    id SERIAL PRIMARY KEY,
    rebelde_id BIGINT,
    FOREIGN KEY (rebelde_id) REFERENCES Rebelde(id)
);

-- cria tabela produto
CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    valor DOUBLE PRECISION,
    inventario_id BIGINT,
    FOREIGN KEY (inventario_id) REFERENCES Inventario(id)
);

-- cria relacionamento muitos para muitos
CREATE TABLE Inventario_Produto (
    inventario_id BIGINT,
    produto_id BIGINT,
    FOREIGN KEY (inventario_id) REFERENCES Inventario(id),
    FOREIGN KEY (produto_id) REFERENCES Produto(id),
    PRIMARY KEY (inventario_id, produto_id)
);