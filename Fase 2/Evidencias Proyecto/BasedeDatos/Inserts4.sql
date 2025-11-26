-- ============================================================================
-- 1. INSERTAR TIPOS Y ESTADOS NECESARIOS (SOLO SI NO EXISTEN)
-- ============================================================================

-- Tipos de terminal
INSERT IGNORE INTO tipo_terminal (nombreTipoTerminal) VALUES
('Aeropuerto'),
('Terminal de buses'),
('Puerto');

-- Tipos de equipo
INSERT IGNORE INTO tipo_equipo (nombreTipoEquipo) VALUES
('Avión'),
('Bus');

-- Clase de cabina
INSERT IGNORE INTO cabina_clase (nombreCabinaClase, prioridad, descripcion) VALUES
('Económica', 1, 'Clase económica estándar'),
('Premium Economy', 2, 'Clase económica premium con más espacio'),
('Business', 3, 'Clase ejecutiva'),
('First Class', 4, 'Primera clase');

-- Obtener IDs de los tipos
SET @idTipoAeropuerto = (SELECT idTipoTerminal FROM tipo_terminal WHERE nombreTipoTerminal = 'Aeropuerto' LIMIT 1);
SET @idTipoAvion = (SELECT idTipoEquipo FROM tipo_equipo WHERE nombreTipoEquipo = 'Avión' LIMIT 1);
SET @idCabinaEconomica = (SELECT idCabinaClase FROM cabina_clase WHERE nombreCabinaClase = 'Económica' LIMIT 1);
SET @idCabinaBusiness = (SELECT idCabinaClase FROM cabina_clase WHERE nombreCabinaClase = 'Business' LIMIT 1);

-- ============================================================================
-- 2. INSERTAR TARIFAS (SOLO SI NO EXISTEN)
-- ============================================================================

INSERT IGNORE INTO tarifa (codigoTarifa, nombreTarifa, idCabinaClase, equipaje_incl_kg, cambios, reembolsable, activo) VALUES
('ECO-LIGHT', 'Light', @idCabinaEconomica, 0, 0, 0, 1),
('ECO-STD', 'Standard', @idCabinaEconomica, 23, 0, 0, 1),
('ECO-FULL', 'Full', @idCabinaEconomica, 23, 1, 0, 1),
('PREMIUM', 'Premium', @idCabinaBusiness, 46, 1, 1, 1);

-- Obtener IDs de tarifas
SET @idTarifaLight = (SELECT idTarifa FROM tarifa WHERE codigoTarifa = 'ECO-LIGHT' LIMIT 1);
SET @idTarifaStandard = (SELECT idTarifa FROM tarifa WHERE codigoTarifa = 'ECO-STD' LIMIT 1);
SET @idTarifaFull = (SELECT idTarifa FROM tarifa WHERE codigoTarifa = 'ECO-FULL' LIMIT 1);
SET @idTarifaPremium = (SELECT idTarifa FROM tarifa WHERE codigoTarifa = 'PREMIUM' LIMIT 1);

-- ============================================================================
-- 3. INSERTAR EMPRESAS (SOLO SI NO EXISTEN)
-- ============================================================================

INSERT IGNORE INTO empresa (nombreEmpresa, tipoEmpresa, logo, activo) VALUES
('LATAM Airlines', 'vuelo', '/uploads/latam-logo.png', 1),
('Sky Airline', 'vuelo', '/uploads/sky-logo.png', 1),
('JetSmart', 'vuelo', '/uploads/jetsmart-logo.png', 1);

-- Obtener IDs de empresas
SET @idLatam = (SELECT idEmpresa FROM empresa WHERE nombreEmpresa = 'LATAM Airlines' LIMIT 1);
SET @idSky = (SELECT idEmpresa FROM empresa WHERE nombreEmpresa = 'Sky Airline' LIMIT 1);
SET @idJetSmart = (SELECT idEmpresa FROM empresa WHERE nombreEmpresa = 'JetSmart' LIMIT 1);

-- ============================================================================
-- 4. INSERTAR TERMINALES (SOLO SI NO EXISTEN)
-- ============================================================================

INSERT IGNORE INTO terminal (nombreTerminal, codigo, ciudad, direccion, idTipoTerminal, activo) VALUES
('Aeropuerto Internacional Arturo Merino Benítez', 'SCL', 'Santiago', 'Av. Armando Cortínez Ote. 1704, Pudahuel', @idTipoAeropuerto, 1),
('Aeropuerto El Tepual', 'PMC', 'Puerto Montt', 'Camino Aeropuerto s/n', @idTipoAeropuerto, 1),
('Aeropuerto La Florida', 'LSC', 'La Serena', 'Ruta 41, Km 7', @idTipoAeropuerto, 1),
('Aeropuerto Diego Aracena', 'IQQ', 'Iquique', 'Camino al Aeropuerto s/n', @idTipoAeropuerto, 1),
('Aeropuerto Andrés Sabella', 'ANF', 'Antofagasta', 'Av. Pedro Aguirre Cerda 4696', @idTipoAeropuerto, 1),
('Aeropuerto Carriel Sur', 'CCP', 'Concepción', 'Ruta 160, Km 8', @idTipoAeropuerto, 1);

-- Obtener IDs de terminales
SET @idSCL = (SELECT idTerminal FROM terminal WHERE codigo = 'SCL' LIMIT 1);
SET @idPMC = (SELECT idTerminal FROM terminal WHERE codigo = 'PMC' LIMIT 1);
SET @idLSC = (SELECT idTerminal FROM terminal WHERE codigo = 'LSC' LIMIT 1);
SET @idCCP = (SELECT idTerminal FROM terminal WHERE codigo = 'CCP' LIMIT 1);

-- ============================================================================
-- 5. INSERTAR EQUIPOS (SOLO SI NO EXISTEN)
-- ============================================================================

INSERT IGNORE INTO empresa_equipo (idEmpresa, modelo, matricula, capacidad, idTipoEquipo, activo) VALUES
(@idLatam, 'Boeing 787-9 Dreamliner', 'CC-BGA', 300, @idTipoAvion, 1),
(@idLatam, 'Airbus A320', 'CC-BAB', 180, @idTipoAvion, 1),
(@idSky, 'Airbus A320neo', 'CC-AZC', 186, @idTipoAvion, 1),
(@idJetSmart, 'Airbus A320', 'CC-AWB', 180, @idTipoAvion, 1),
(@idLatam, 'Boeing 767-300', 'CC-CWF', 252, @idTipoAvion, 1),
(@idSky, 'Airbus A321neo', 'CC-AZD', 220, @idTipoAvion, 1);

-- Obtener IDs de equipos
SET @idEquipo1 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-BGA' LIMIT 1);
SET @idEquipo2 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-BAB' LIMIT 1);
SET @idEquipo3 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-AZC' LIMIT 1);
SET @idEquipo4 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-AWB' LIMIT 1);
SET @idEquipo5 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-CWF' LIMIT 1);
SET @idEquipo6 = (SELECT idEquipo FROM empresa_equipo WHERE matricula = 'CC-AZD' LIMIT 1);

-- ============================================================================
-- 6. INSERTAR RUTAS (SOLO SI NO EXISTEN)
-- ============================================================================

-- Verificar si las rutas ya existen antes de insertarlas
INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idSCL, @idPMC, 1020, 120, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idPMC);

INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idPMC, @idSCL, 1020, 120, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idPMC AND idTerminalDestino = @idSCL);

INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idSCL, @idLSC, 470, 60, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idLSC);

INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idLSC, @idSCL, 470, 60, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idLSC AND idTerminalDestino = @idSCL);

INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idSCL, @idCCP, 500, 70, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idCCP);

INSERT IGNORE INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) 
SELECT @idCCP, @idSCL, 500, 70, 1
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE idTerminalOrigen = @idCCP AND idTerminalDestino = @idSCL);

-- Obtener IDs de rutas
SET @idRutaSCL_PMC = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idPMC LIMIT 1);
SET @idRutaPMC_SCL = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idPMC AND idTerminalDestino = @idSCL LIMIT 1);
SET @idRutaSCL_LSC = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idLSC LIMIT 1);
SET @idRutaLSC_SCL = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idLSC AND idTerminalDestino = @idSCL LIMIT 1);
SET @idRutaSCL_CCP = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idSCL AND idTerminalDestino = @idCCP LIMIT 1);
SET @idRutaCCP_SCL = (SELECT idRuta FROM ruta WHERE idTerminalOrigen = @idCCP AND idTerminalDestino = @idSCL LIMIT 1);

-- ============================================================================
-- 7. INSERTAR VIAJES (SIN ESPECIFICAR IDs - AUTO INCREMENT)
-- ============================================================================

-- SCL → PMC (Santiago → Puerto Montt)
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaSCL_PMC, '2025-11-24 07:00:00', '2025-11-24 09:00:00', @idEquipo1, 'programado'),
(@idRutaSCL_PMC, '2025-11-24 10:30:00', '2025-11-24 12:30:00', @idEquipo2, 'programado'),
(@idRutaSCL_PMC, '2025-11-24 14:00:00', '2025-11-24 16:00:00', @idEquipo3, 'programado'),
(@idRutaSCL_PMC, '2025-11-24 18:30:00', '2025-11-24 20:30:00', @idEquipo4, 'programado'),
(@idRutaSCL_PMC, '2025-11-25 07:00:00', '2025-11-25 09:00:00', @idEquipo1, 'programado'),
(@idRutaSCL_PMC, '2025-11-25 10:30:00', '2025-11-25 12:30:00', @idEquipo2, 'programado'),
(@idRutaSCL_PMC, '2025-11-25 14:00:00', '2025-11-25 16:00:00', @idEquipo3, 'programado'),
(@idRutaSCL_PMC, '2025-11-25 18:30:00', '2025-11-25 20:30:00', @idEquipo4, 'programado'),
(@idRutaSCL_PMC, '2025-11-26 07:00:00', '2025-11-26 09:00:00', @idEquipo1, 'programado'),
(@idRutaSCL_PMC, '2025-11-26 10:30:00', '2025-11-26 12:30:00', @idEquipo2, 'programado'),
(@idRutaSCL_PMC, '2025-11-26 14:00:00', '2025-11-26 16:00:00', @idEquipo3, 'programado');

-- PMC → SCL (Puerto Montt → Santiago) - VUELTA
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaPMC_SCL, '2025-11-24 08:00:00', '2025-11-24 10:00:00', @idEquipo5, 'programado'),
(@idRutaPMC_SCL, '2025-11-24 11:30:00', '2025-11-24 13:30:00', @idEquipo6, 'programado'),
(@idRutaPMC_SCL, '2025-11-24 15:00:00', '2025-11-24 17:00:00', @idEquipo1, 'programado'),
(@idRutaPMC_SCL, '2025-11-24 19:30:00', '2025-11-24 21:30:00', @idEquipo2, 'programado'),
(@idRutaPMC_SCL, '2025-11-25 08:00:00', '2025-11-25 10:00:00', @idEquipo5, 'programado'),
(@idRutaPMC_SCL, '2025-11-25 11:30:00', '2025-11-25 13:30:00', @idEquipo6, 'programado'),
(@idRutaPMC_SCL, '2025-11-25 15:00:00', '2025-11-25 17:00:00', @idEquipo1, 'programado'),
(@idRutaPMC_SCL, '2025-11-26 08:00:00', '2025-11-26 10:00:00', @idEquipo5, 'programado'),
(@idRutaPMC_SCL, '2025-11-26 11:30:00', '2025-11-26 13:30:00', @idEquipo6, 'programado');

-- SCL → LSC (Santiago → La Serena)
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaSCL_LSC, '2025-11-24 08:00:00', '2025-11-24 09:00:00', @idEquipo2, 'programado'),
(@idRutaSCL_LSC, '2025-11-24 12:00:00', '2025-11-24 13:00:00', @idEquipo3, 'programado'),
(@idRutaSCL_LSC, '2025-11-24 17:00:00', '2025-11-24 18:00:00', @idEquipo4, 'programado'),
(@idRutaSCL_LSC, '2025-11-25 08:00:00', '2025-11-25 09:00:00', @idEquipo2, 'programado'),
(@idRutaSCL_LSC, '2025-11-25 12:00:00', '2025-11-25 13:00:00', @idEquipo3, 'programado'),
(@idRutaSCL_LSC, '2025-11-26 08:00:00', '2025-11-26 09:00:00', @idEquipo2, 'programado');

-- LSC → SCL (La Serena → Santiago) - VUELTA
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaLSC_SCL, '2025-11-24 09:30:00', '2025-11-24 10:30:00', @idEquipo2, 'programado'),
(@idRutaLSC_SCL, '2025-11-24 13:30:00', '2025-11-24 14:30:00', @idEquipo3, 'programado'),
(@idRutaLSC_SCL, '2025-11-24 18:30:00', '2025-11-24 19:30:00', @idEquipo4, 'programado'),
(@idRutaLSC_SCL, '2025-11-25 09:30:00', '2025-11-25 10:30:00', @idEquipo2, 'programado'),
(@idRutaLSC_SCL, '2025-11-25 13:30:00', '2025-11-25 14:30:00', @idEquipo3, 'programado');

-- SCL → CCP (Santiago → Concepción)
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaSCL_CCP, '2025-11-24 07:30:00', '2025-11-24 08:40:00', @idEquipo2, 'programado'),
(@idRutaSCL_CCP, '2025-11-24 11:00:00', '2025-11-24 12:10:00', @idEquipo3, 'programado'),
(@idRutaSCL_CCP, '2025-11-24 15:30:00', '2025-11-24 16:40:00', @idEquipo4, 'programado'),
(@idRutaSCL_CCP, '2025-11-25 07:30:00', '2025-11-25 08:40:00', @idEquipo2, 'programado'),
(@idRutaSCL_CCP, '2025-11-25 11:00:00', '2025-11-25 12:10:00', @idEquipo3, 'programado');

-- CCP → SCL (Concepción → Santiago) - VUELTA
INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado) VALUES
(@idRutaCCP_SCL, '2025-11-24 09:00:00', '2025-11-24 10:10:00', @idEquipo2, 'programado'),
(@idRutaCCP_SCL, '2025-11-24 13:00:00', '2025-11-24 14:10:00', @idEquipo3, 'programado'),
(@idRutaCCP_SCL, '2025-11-24 17:00:00', '2025-11-24 18:10:00', @idEquipo4, 'programado'),
(@idRutaCCP_SCL, '2025-11-25 09:00:00', '2025-11-25 10:10:00', @idEquipo2, 'programado');












