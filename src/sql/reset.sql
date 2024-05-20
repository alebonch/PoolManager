DROP TABLE IF EXISTS PaymentMethods cascade;
DROP TABLE IF EXISTS Position cascade;
DROP TABLE IF EXISTS Typology cascade;
DROP TABLE IF EXISTS Reservation cascade;
DROP TABLE IF EXISTS Users cascade;

-- Creazione della tabella Utenti
CREATE TABLE Users (
    mail VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
	telephone VARCHAR(10)
);
-- Creazione della Tipologia
CREATE TABLE Typology (
    code INT PRIMARY KEY,
    n_sunbeds INT,
    n_chairs INT,
    n_deckchairs INT,
    m_sunbeds VARCHAR(20),
    gazebo BOOLEAN
);
-- Creazione della tabella Postazione
CREATE TABLE Postation (
    number INT PRIMARY KEY NOT NULL,
    type INT NOT NULL REFERENCES Typology(code),
    zone VARCHAR(20) NOT NULL,
    availability BOOLEAN NOT NULL
);
-- Creazione della tabella Metodi di Pagamento
CREATE TABLE PaymentMethods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);
-- Creazione della tabella Prenotazione
CREATE TABLE Reservation (
    userId VARCHAR(50) REFERENCES Users(mail),
    postation INT REFERENCES Postation(number),
    date TIMESTAMP,
    pagamento INT DEFAULT 0 REFERENCES PaymentMethods(id),
	PRIMARY KEY(postazione, date)
);

INSERT INTO Users (mail, password, name, surname, telephone) VALUES('admin@admin.com', 'admin', 'Alessandro', 'Bonciani');