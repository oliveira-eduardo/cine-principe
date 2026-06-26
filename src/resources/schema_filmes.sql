CREATE TABLE IF NOT EXISTS Criticas (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    filme_id INTEGER, 
    nome_critica TEXT, 
    origem TEXT, 
    comentario TEXT, 
    nota REAL
);

CREATE TABLE IF NOT EXISTS FilmeData (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    nome TEXT UNIQUE, 
    duracao TEXT, 
    sinopse TEXT, 
    valor FLOAT, 
    nomeImagem TEXT
);

CREATE TABLE IF NOT EXISTS MovieData (
    id INTEGER PRIMARY KEY AUTOINCREMENT, 
    nome TEXT, 
    duracao TEXT, 
    sinopse TEXT, 
    valor FLOAT
);