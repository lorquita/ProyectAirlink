INSERT INTO terminal (nombreTerminal, codigo, ciudad, idTipoTerminal, activo) VALUES
('Terminal Coquimbo', 'COQ-BUS', 'Coquimbo', 3, 1),
('Terminal Iquique', 'IQQ-BUS', 'Iquique', 3, 1),
('Terminal Puerto Montt', 'PMC-BUS', 'Puerto Montt', 3, 1),
('Terminal Antofagasta', 'ANF-BUS', 'Antofagasta', 3, 1),
('Terminal Punta Arenas', 'PUQ-BUS', 'Punta Arenas', 3, 1),
('Terminal Viña del Mar', 'VDM-BUS', 'Viña del Mar', 3, 1),
('Terminal Rancagua', 'RAN-BUS', 'Rancagua', 3, 1),
('Terminal Talca', 'TAL-BUS', 'Talca', 3, 1),
('Terminal Chillán', 'CHI-BUS', 'Chillán', 3, 1),
('Terminal Los Ángeles', 'LOS-BUS', 'Los Ángeles', 3, 1),
('Terminal Osorno', 'OSO-BUS', 'Osorno', 3, 1),
('Terminal Valdivia', 'VAL-BUS', 'Valdivia', 3, 1),
('Terminal Castro', 'CAS-BUS', 'Castro', 3, 1),
('Terminal Calama', 'CAL-BUS', 'Calama', 3, 1),
('Terminal San Pedro de Atacama', 'SPA-BUS', 'San Pedro de Atacama', 3, 1),
('Terminal Ovalle', 'OVA-BUS', 'Ovalle', 3, 1),
('Terminal Copiapó', 'COP-BUS', 'Copiapó', 3, 1);

-- =====================================================
-- 2. CREAR RUTAS DE BUS
-- =====================================================

-- CONEXIÓN CLAVE: La Serena <-> Coquimbo
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'COQ-BUS'),
    15, 30, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'COQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    15, 30, 1;

-- La Serena <-> Ovalle
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'OVA-BUS'),
    90, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'OVA-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    90, 90, 1;

-- Antofagasta <-> Calama
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAL-BUS'),
    215, 180, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    215, 180, 1;

-- Calama <-> San Pedro de Atacama
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'SPA-BUS'),
    100, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'SPA-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAL-BUS'),
    100, 90, 1;

-- Iquique <-> Antofagasta
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'IQQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    560, 480, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'IQQ-BUS'),
    560, 480, 1;

-- Valparaíso <-> Viña del Mar
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'VLP-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VDM-BUS'),
    10, 20, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'VDM-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VLP-BUS'),
    10, 20, 1;

-- Santiago <-> Rancagua
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'SCL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'RAN-BUS'),
    85, 75, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'RAN-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'SCL-BUS'),
    85, 75, 1;

-- Santiago <-> Talca
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'SCL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TAL-BUS'),
    255, 210, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'TAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'SCL-BUS'),
    255, 210, 1;

-- Talca <-> Chillán
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'TAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CHI-BUS'),
    110, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CHI-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TAL-BUS'),
    110, 90, 1;

-- Chillán <-> Los Ángeles
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CHI-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'LOS-BUS'),
    95, 75, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LOS-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CHI-BUS'),
    95, 75, 1;

-- Los Ángeles <-> Concepción
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LOS-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CCP-BUS'),
    110, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CCP-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'LOS-BUS'),
    110, 90, 1;

-- Temuco <-> Valdivia
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'TMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VAL-BUS'),
    180, 150, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'VAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TMC-BUS'),
    180, 150, 1;

-- Valdivia <-> Osorno
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'VAL-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'OSO-BUS'),
    110, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'OSO-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VAL-BUS'),
    110, 90, 1;

-- Osorno <-> Puerto Montt
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'OSO-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    110, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'OSO-BUS'),
    110, 90, 1;

-- Puerto Montt <-> Castro
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAS-BUS'),
    120, 150, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAS-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    120, 150, 1;

-- Puerto Montt <-> Punta Arenas
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PUQ-BUS'),
    970, 960, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PUQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    970, 960, 1;