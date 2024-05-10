DROP TABLE IF EXISTS PaymentMethods cascade;
DROP TABLE IF EXISTS Prenotazione cascade;
DROP TABLE IF EXISTS Tipologia cascade;
DROP TABLE IF EXISTS Postazione cascade;
DROP TABLE IF EXISTS Users cascade;

-- Creazione della tabella Utenti
CREATE TABLE Users (
    mail VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
	telephone VARCHAR(10)
);

-- Creazione della tabella Postazione
CREATE TABLE Postazione (
    number INT PRIMARY KEY NOT NULL,
    type INT NOT NULL REFERENCES Tipologia(code),
    zone VARCHAR(20) NOT NULL
);

-- Creazione della Tipologia
CREATE TABLE Tipologia (
    code INT PRIMARY KEY,
    n_sunbeds INT,
    n_chairs INT,
    n_deckchair INT,
    m_sunbeds VARCHAR(20),
    gazebo BOOLEAN
);

-- Creazione della tabella Prenotazione
CREATE TABLE Prenotazione (
    user VARCHAR(50) PRIMARY KEY REFERENCES Users(mail),
    postazione INT PRIMARY KEY REFERENCES Postazione(number),
    date TIMESTAMP PRIMARY KEY
    pagamento INT DEFAULT 0 REFERENCES PaymentMethods(id)
);

-- Creazione della tabella Metodi di Pagamento
CREATE TABLE PaymentMethods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

create view cars_view as
select * from vehicles natural join cars;

create view mopeds_view as
select * from vehicles natural join mopeds;

