CREATE TABLE IF NOT EXISTS Criticos (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    user TEXT UNIQUE, 
    cpf TEXT UNIQUE, 
    senha TEXT, 
    idade INTEGER, 
    sexo TEXT, 
    email TEXT, 
    nome_do_cartao TEXT, 
    numero_do_cartao TEXT, 
    codigo_verificador_do_cartao TEXT, 
    origem TEXT
);

CREATE TABLE IF NOT EXISTS Administradores (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    nome TEXT, 
    idade INTEGER, 
    email TEXT, 
    salario REAL, 
    senha TEXT
);

CREATE TABLE IF NOT EXISTS Funcionarios (
    nome TEXT, 
    idade INTEGER, 
    email TEXT, 
    salario REAL, 
    senha TEXT
);

CREATE TABLE IF NOT EXISTS UsuariosData (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    user TEXT UNIQUE, 
    cpf TEXT UNIQUE, 
    senha TEXT, 
    idade INTEGER, 
    sexo TEXT, 
    email TEXT UNIQUE, 
    nome_do_cartao TEXT, 
    numero_do_cartao TEXT, 
    codigo_verificador_do_cartao TEXT
);