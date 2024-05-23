-- Insert 5 Users
INSERT INTO Users (mail, password, name, surname, telephone) VALUES
('john.doe@example.com', 'password123', 'John', 'Doe', '1234567890'),
('jane.smith@example.com', 'password456', 'Jane', 'Smith', '0987654321'),
('alice.jones@example.com', 'password789', 'Alice', 'Jones', '1122334455'),
('bob.brown@example.com', 'passwordabc', 'Bob', 'Brown', '2233445566'),
('charlie.davis@example.com', 'passworddef', 'Charlie', 'Davis', '3344556677');

-- Insert 3 Typology
INSERT INTO Typology (c, n_sunbeds, n_chairs, n_deckchairs, m_sunbeds, gazebo) VALUES
('standard', 2, 4, 1, 'plastica', TRUE),
('luxury', 1, 2, 2, 'legno', FALSE),
('premium', 3, 6, 3, 'tela', TRUE);

-- Insert 15 Postation
INSERT INTO Postation (number, type, zone, availability) VALUES
(1, 'standard', 'North', TRUE),
(2, 'standard', 'North', TRUE),
(3, 'standard', 'North', TRUE),
(4, 'standard', 'South', TRUE),
(5,'luxury' , 'South', TRUE),
(6, 'luxury', 'South', TRUE),
(7,'luxury', 'East', TRUE),
(8, 'premium', 'East', TRUE),
(9, 'premium', 'East', TRUE),
(10, 'luxury', 'West', TRUE),
(11, 'luxury', 'West', TRUE),
(12, 'luxury', 'West', TRUE),
(13, 'standard', 'West', TRUE),
(14, 'standard', 'West', TRUE),
(15, 'standard', 'West', TRUE);
INSERT INTO PaymentMethods(id,name) VALUES
	(0,'Paypal'),
	(1,'Cash');

-- Insert 2 Reservations
INSERT INTO Reservation (userId, postation,date) VALUES
('john.doe@example.com', 1, '2024-11-06'),
('jane.smith@example.com', 5, '2023-06-05');
