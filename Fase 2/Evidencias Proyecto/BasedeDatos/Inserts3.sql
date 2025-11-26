INSERT INTO terminal (nombreTerminal, codigo, ciudad, idTipoTerminal, activo) VALUES
-- Región de Los Lagos (cerca de Puerto Montt)
('Terminal Frutillar', 'FRU-BUS', 'Frutillar', 3, 1),
('Terminal Puerto Varas', 'PVA-BUS', 'Puerto Varas', 3, 1),
('Terminal Ancud', 'ANC-BUS', 'Ancud', 3, 1),
('Terminal Quellón', 'QUE-BUS', 'Quellón', 3, 1),

-- Región de Iquique (cerca del aeropuerto)
('Terminal Alto Hospicio', 'AHO-BUS', 'Alto Hospicio', 3, 1),
('Terminal Pica', 'PIC-BUS', 'Pica', 3, 1),
('Terminal Huara', 'HUA-BUS', 'Huara', 3, 1),

-- Región de La Serena/Coquimbo (cerca del aeropuerto)
('Terminal Vicuña', 'VIC-BUS', 'Vicuña', 3, 1),
('Terminal Tongoy', 'TON-BUS', 'Tongoy', 3, 1),
('Terminal Andacollo', 'AND-BUS', 'Andacollo', 3, 1),

-- Región de Antofagasta
('Terminal Mejillones', 'MEJ-BUS', 'Mejillones', 3, 1),
('Terminal Tocopilla', 'TOC-BUS', 'Tocopilla', 3, 1),
('Terminal María Elena', 'MAR-BUS', 'María Elena', 3, 1),

-- Región de Cusco (Perú)
('Terminal Urubamba', 'URU-BUS', 'Urubamba', 3, 1),
('Terminal Ollantaytambo', 'OLL-BUS', 'Ollantaytambo', 3, 1),
('Terminal Pisac', 'PIS-BUS', 'Pisac', 3, 1),

-- Región de Buenos Aires
('Terminal Tigre', 'TIG-BUS', 'Tigre', 3, 1),
('Terminal San Isidro', 'SIS-BUS', 'San Isidro', 3, 1),
('Terminal La Plata', 'LPL-BUS', 'La Plata', 3, 1);

-- =====================================================
-- PASO 2: CREAR RUTAS DE BUS LOCALES (CONEXIONES)
-- =====================================================

-- Puerto Montt → Destinos cercanos
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'FRU-BUS'),
    45, 60, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PVA-BUS'),
    20, 30, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANC-BUS'),
    90, 120, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PMC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'CAS-BUS'),
    90, 120, 1;

-- Puerto Varas → Frutillar
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'PVA-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'FRU-BUS'),
    30, 40, 1;

-- Iquique → Destinos cercanos
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'IQQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'AHO-BUS'),
    15, 25, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'IQQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PIC-BUS'),
    115, 120, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'IQQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'HUA-BUS'),
    70, 90, 1;

-- Coquimbo/La Serena → Destinos cercanos
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'COQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VIC-BUS'),
    60, 75, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'COQ-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TON-BUS'),
    40, 50, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'VIC-BUS'),
    62, 75, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'LSC-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'AND-BUS'),
    55, 70, 1;

-- Antofagasta → Destinos cercanos
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'MEJ-BUS'),
    65, 75, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TOC-BUS'),
    185, 180, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'ANF-BUS'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'MAR-BUS'),
    235, 210, 1;

-- Cusco → Valle Sagrado
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CUZ'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'URU-BUS'),
    78, 90, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CUZ'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'OLL-BUS'),
    90, 105, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'CUZ'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'PIS-BUS'),
    32, 45, 1;

-- Buenos Aires → Zona norte
INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'EZE'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'TIG-BUS'),
    45, 60, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'EZE'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'SIS-BUS'),
    35, 50, 1;

INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT 
    (SELECT idTerminal FROM terminal WHERE codigo = 'EZE'),
    (SELECT idTerminal FROM terminal WHERE codigo = 'LPL-BUS'),
    60, 75, 1;

-- =====================================================
-- PASO 3: CREAR VIAJES DE BUS PARA ESTAS RUTAS
-- =====================================================

-- Puerto Montt → Frutillar (IMPORTANTE: fechas alineadas con vuelos)
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 11:00:00',
    '2025-11-06 12:00:00',
    8,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'FRU-BUS';

INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 14:30:00',
    '2025-11-06 15:30:00',
    9,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'FRU-BUS';

INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 17:00:00',
    '2025-11-06 18:00:00',
    10,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'FRU-BUS';

-- Puerto Montt → Puerto Varas
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 10:30:00',
    '2025-11-06 11:00:00',
    8,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'PVA-BUS';

INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 13:00:00',
    '2025-11-06 13:30:00',
    9,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'PVA-BUS';

INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 16:00:00',
    '2025-11-06 16:30:00',
    10,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'PMC-BUS' AND tDestino.codigo = 'PVA-BUS';

-- Iquique → Pica
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-06 13:00:00',
    '2025-11-06 15:00:00',
    8,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'IQQ-BUS' AND tDestino.codigo = 'PIC-BUS';

-- Coquimbo → Vicuña
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-08 09:30:00',
    '2025-11-08 10:45:00',
    9,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'COQ-BUS' AND tDestino.codigo = 'VIC-BUS';

-- Coquimbo → Tongoy
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado)
SELECT 
    r.idRuta,
    '2025-11-08 10:00:00',
    '2025-11-08 10:50:00',
    10,
    'programado'
FROM ruta r
INNER JOIN terminal tOrigen ON r.idTerminalOrigen = tOrigen.idTerminal
INNER JOIN terminal tDestino ON r.idTerminalDestino = tDestino.idTerminal
WHERE tOrigen.codigo = 'COQ-BUS' AND tDestino.codigo = 'TON-BUS';

-- =====================================================
-- PASO 4: ASIGNAR TARIFAS A LOS NUEVOS BUSES
-- =====================================================

-- Obtener los IDs de los últimos viajes creados y asignarles tarifas
INSERT INTO viaje_tarifa (idViaje, idTarifa, precio, moneda, cupos)
SELECT 
    v.idViaje,
    5, -- Tarifa de bus económico
    CASE 
        WHEN r.distanciaKm < 30 THEN 3000
        WHEN r.distanciaKm < 60 THEN 5000
        WHEN r.distanciaKm < 100 THEN 8000
        ELSE 12000
    END,
    'CLP',
    CASE 
        WHEN e.nombreEmpresa = 'Turbus' THEN 45
        WHEN e.nombreEmpresa = 'Pullman Bus' THEN 42
        ELSE 40
    END
FROM viaje v
INNER JOIN ruta r ON v.idRuta = r.idRuta
INNER JOIN empresa_equipo eq ON v.idEquipo = eq.idEquipo
INNER JOIN empresa e ON eq.idEmpresa = e.idEmpresa
WHERE e.tipoEmpresa = 'bus'
  AND NOT EXISTS (
      SELECT 1 FROM viaje_tarifa vt WHERE vt.idViaje = v.idViaje
  );