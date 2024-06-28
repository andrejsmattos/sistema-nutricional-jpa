-- ENDEREÇO

CREATE TABLE IF NOT EXISTS endereco (
    id SERIAL PRIMARY KEY,
    logradouro VARCHAR(100),
    estado VARCHAR(50),
    cidade VARCHAR(100),
    cep VARCHAR(20),
    numero VARCHAR(6)
);

INSERT INTO endereco(logradouro, estado, cidade, cep, numero) VALUES
    ('Rua Augusta', 'SP', 'São Paulo', '01000-000', '123'),
    ('Avenida Atlântica', 'RJ', 'Rio de Janeiro', '02000-000', '456'),
    ('Rua da Paz', 'MG', 'Belo Horizonte', '03000-000', '789'),
    ('Rua Oscar Freire', 'SP', 'São Paulo', '04000-000', '101'),
    ('Avenida Paulista', 'SP', 'São Paulo', '05000-000', '202'),
    ('Rua Barão de Itapetininga', 'SP', 'São Paulo', '06000-000', '303'),
    ('Avenida Sete de Setembro', 'PR', 'Curitiba', '07000-000', '404'),
    ('Rua XV de Novembro', 'SC', 'Florianópolis', '08000-000', '505'),
    ('Avenida Beira Mar', 'CE', 'Fortaleza', '09000-000', '606'),
    ('Rua do Comércio', 'BA', 'Salvador', '10000-000', '707');

SELECT * FROM endereco;

---------------------------------------------------------------------
-- FUNCIONÁRIO

CREATE TABLE IF NOT EXISTS funcionario (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(20),
    tempoExperiencia SMALLINT,
    endereco_id BIGINT NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);

INSERT INTO funcionario (matricula, tempoExperiencia, endereco_id) VALUES
	('FUNC1234', 5, 1),
    ('FUNC1235', 2, 2),
    ('FUNC1236', 7, 3);

SELECT * FROM funcionario;


-------------------------------------------------------------------------
-- NUTRICIONISTA

CREATE TABLE IF NOT EXISTS nutricionista (
    id SERIAL PRIMARY KEY,
    crn VARCHAR(20),
    especialidade VARCHAR(100)
);

INSERT INTO nutricionista (crn, especialidade) VALUES
	('CRN-12345', 'Nutrição Esportiva'),
    ('CRN-54321', 'Nutrição Clínica'),
    ('CRN-98765', 'Nutrição Infantil');

SELECT * FROM nutricionista;


---------------------------------------------------------------------------
-- PACIENTE

CREATE TABLE IF NOT EXISTS paciente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200),
    dataNascimento DATE,
    cpf VARCHAR(20),
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco_id BIGINT NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);

INSERT INTO paciente (nome, dataNascimento, cpf, telefone, email, endereco_id) VALUES
    ('Maria Silva', '1990-05-15', '123.456.789-00', '(11) 98765-4321', 'maria.silva@example.com', 4),
    ('João Oliveira', '1985-10-25', '987.654.321-00', '(21) 99876-5432', 'joao.oliveira@example.com', 5),
    ('Pedro Santos', '1978-03-12', '456.789.123-00', '(31) 98765-1234', 'pedro.santos@example.com', 6),
    ('Ana Pereira', '1995-08-20', '789.123.456-00', '(41) 98765-9876', 'ana.pereira@example.com', 7),
    ('José Souza', '1980-12-05', '321.654.987-00', '(51) 99876-5432', 'jose.souza@example.com', 8);

SELECT * FROM paciente;

UPDATE paciente
SET telefone = '(48) 98765-4321'
WHERE id = 1;

SELECT * FROM paciente
WHERE id = 1;

--------------------------------------------------------------------------------------------
-- CONSULTA

CREATE TABLE IF NOT EXISTS consulta (
    id SERIAL PRIMARY KEY,
    nutricionista_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    dataConsulta DATE,
    observacoes TEXT,
    FOREIGN KEY (nutricionista_id) REFERENCES nutricionista(id),
    FOREIGN KEY (paciente_id) REFERENCES paciente (id)
);

INSERT INTO consulta (nutricionista_id, paciente_id, dataConsulta, observacoes) VALUES
    (1, 1, '2024-06-01', 'Consulta de acompanhamento nutricional'),
    (2, 2, '2024-06-02', 'Consulta para avaliação clínica'),
    (3, 3, '2024-06-03', 'Consulta inicial de diagnóstico');

SELECT * FROM consulta;
