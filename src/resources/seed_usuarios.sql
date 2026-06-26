INSERT OR IGNORE INTO UsuariosData (user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao) VALUES
('dudu', '13131121', '123', 25, 'Masculino', 'dudu@email.com', 'EDUARDO', '645217451', '8333');

INSERT OR IGNORE INTO Criticos (user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao, origem) VALUES
('anton', '333.333.333-33', '123', 35, 'Masculino', 'anton@critica.br', 'ANTON A', '9999000011112222', '333', 'Folha de São Paulo'),
('isabela', '222.222.222-22', '123', 32, 'Feminino', 'isabela@critica.br', 'ISABELA I', '5555666677778888', '222', 'Revista Veja'),
('brigite', '111.111.111-11', '123', 28, 'Feminino', 'brigite@critica.br', 'BRIGITE B', '1111222233334444', '111', 'Omelete');

INSERT OR IGNORE INTO Administradores (nome, idade, email, salario, senha) VALUES
('Enzo', 40, 'enzo@cinema.br', 5000.0, '@123$'),
('Juvenal', 42, 'juvenal@cinema.br', 5000.0, '123$'),
('Alberto', 38, 'alberto@cinema.br', 5000.0, '@123');

INSERT OR IGNORE INTO Funcionarios (nome, idade, email, salario, senha) VALUES
('Genaro', 35, 'genaro@cinema.br', 5000.0, '123');