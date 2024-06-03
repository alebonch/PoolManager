-- Creazione della tabella Utenti
CREATE TABLE Users (
    mail VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
	telephone VARCHAR(50)
);
-- Creazione degli oggetti
CREATE TABLE Object (
    name VARCHAR(50) PRIMARY KEY,
    totalnumber INT NOT NULL,
    price INT NOT NULL,
    material VARCHAR(50),
    color VARCHAR(50)
);
-- Creazione della tabella Locazione
CREATE TABLE Location(
    id INT PRIMARY KEY,
    description VARCHAR(100) 
)
-- Creazione della tabella Postazione
CREATE TABLE Postation (
    number INT PRIMARY KEY NOT NULL,
    n_chair INT,
    n_deckchair INT,
    n_sunbed INT,
    n_table INT,
    n_umbrella INT,
    zone INT NOT NULL REFERENCES Location(id)
);
-- Creazione della tabella Prenotazione
CREATE TABLE Reservation (
    userId VARCHAR(50) REFERENCES Users(mail),
    postation INT REFERENCES Postation(number),
    date INT REFERENCES TimeRecord(id),
    cost INT,
	PRIMARY KEY(postazione, date)
);
-- Creazione di Time Record
CREATE TABLE TimeRecord (
    id INT SERIAL PRIMARY KEY,
    date VARCHAR(50),
    turno VARCHAR(50),
)

