-- Inserimento degli Utenti
INSERT INTO Users (mail, password, name, surname, telephone) VALUES
('user1@example.com', 'password1', 'Mario', 'Rossi', '1234567890'),
('user2@example.com', 'password2', 'Luca', 'Bianchi', '1234567891'),
('user3@example.com', 'password3', 'Giulia', 'Verdi', '1234567892'),
('user4@example.com', 'password4', 'Anna', 'Neri', '1234567893'),
('user5@example.com', 'password5', 'Sara', 'Russo', '1234567894'),
('user6@example.com', 'password6', 'Marco', 'Ferrari', '1234567895'),
('user7@example.com', 'password7', 'Elena', 'Costa', '1234567896'),
('user8@example.com', 'password8', 'Andrea', 'Gallo', '1234567897');

-- Inserimento degli Oggetti
INSERT INTO Object (name, totalnumber, price, material, color) VALUES
('umbrella', 50, 8, 'Tessuto', 'Blu'),
('chair', 100, 2, 'Plastica', 'Bianco'),
('sunbed', 75, 3, 'Metallo', 'Verde'),
('table', 20, 4, 'Legno', 'Marrone'),
('deckchair', 30, 3, 'Alluminio', 'Rosso');

-- Inserimento dei Record Temporali
INSERT INTO TimeRecord (date, turno) VALUES
('2024-06-19', 'Mattina'),
('2024-06-19', 'Pomeriggio');

-- Inserimento delle Locazioni
INSERT INTO Location (id, description) VALUES
(1, 'Solarium'),
(2, 'Boschetto'),
(3, 'Bordo vasca'),
(4, 'Giardino');

-- Inserimento delle Postazioni
INSERT INTO Postation (number, n_chair, n_deckchair, n_sunbed, n_table, n_umbrella, zone) VALUES
(1, 2, 1, 1, 1, 1, 1),
(2, 4, 2, 2, 1, 2, 2),
(3, 3, 1, 1, 1, 1, 3),
(4, 2, 1, 1, 1, 1, 4),  
(5, 4, 2, 2, 1, 2, 1), 
(6, 3, 1, 1, 1, 1, 2),  
(7, 2, 1, 1, 1, 1, 3);  

-- Inserimento delle Prenotazioni
INSERT INTO Reservation (userId, postation, date, cost) VALUES
('user1@example.com', 1, 1, 22),
('user2@example.com', 2, 2, 40),
('user3@example.com', 3, 1, 24),
('user4@example.com', 4, 2, 28), 
('user5@example.com', 5, 1, 42), 
('user6@example.com', 6, 2, 34),
('user7@example.com', 7, 1, 50); 