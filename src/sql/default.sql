-- Insert 5 Users
INSERT INTO Users (mail, password, name, surname, telephone) VALUES
('john.doe@example.com', 'password123', 'John', 'Doe', '1234567890'),
('jane.smith@example.com', 'password456', 'Jane', 'Smith', '0987654321'),
('alice.jones@example.com', 'password789', 'Alice', 'Jones', '1122334455'),
('bob.brown@example.com', 'passwordabc', 'Bob', 'Brown', '2233445566'),
('charlie.davis@example.com', 'passworddef', 'Charlie', 'Davis', '3344556677');
('admin@admin.com', 'admin', 'Alessandro', 'Bonciani');

-- Insert 3 Typology
INSERT INTO Typology (code, n_sunbeds, n_chairs, n_deckchairs, m_sunbeds, gazebo) VALUES
(1, 2, 4, 1, 'standard', TRUE),
(2, 1, 2, 2, 'luxury', FALSE),
(3, 3, 6, 3, 'premium', TRUE);

-- Insert 15 Postation
INSERT INTO Postation (number, type, zone, availability) VALUES
(1, 1, 'North', TRUE),
(2, 1, 'North', TRUE),
(3, 1, 'North', TRUE),
(4, 2, 'South', TRUE),
(5, 2, 'South', TRUE),
(6, 2, 'South', TRUE),
(7, 3, 'East', TRUE),
(8, 3, 'East', TRUE),
(9, 3, 'East', TRUE),
(10, 1, 'West', TRUE),
(11, 1, 'West', TRUE),
(12, 2, 'West', TRUE),
(13, 2, 'West', TRUE),
(14, 3, 'West', TRUE),
(15, 3, 'West', TRUE);

-- Insert 2 Reservations
INSERT INTO Reservation (userId, postation, date) VALUES
('john.doe@example.com', 1, '2024-06-01 10:00:00'),
('jane.smith@example.com', 5, '2024-06-01 11:00:00');
