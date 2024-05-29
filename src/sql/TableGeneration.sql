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
    type VARCHAR(50) NOT NULL REFERENCES Object(name),
    zone INT NOT NULL REFERENCES Location(id),
    availability BOOLEAN NOT NULL
);
-- Creazione della tabella Prenotazione
CREATE TABLE Reservation (
    userId VARCHAR(50) REFERENCES Users(mail),
    postation INT REFERENCES Postation(number),
    date VARCHAR(50) REFERENCES TimeRecord(date),
	PRIMARY KEY(postazione, date)
);
CREATE TABLE TimeRecord (
    date VARCHAR(50),
    turno VARCHAR(50),
    PRIMARY KEY(date, turno)
)

