INSERT INTO miembro (nombre, apellido, rol, email) VALUES
('Carlos', 'Arroyo', 'adulto', 'carlos@gmail.com'),
('Laura', 'Poza', 'adulto', 'laura@gmail.com'),
('Ignacio', 'Arroyo', 'adulto', 'ignacio@gmail.com');

INSERT INTO categoria (nombre, subcategoria, tipo, descripcion) VALUES
('Vivienda',       'Alquiler',              'gasto',   'Pago mensual del piso'),
('Vivienda',       'Luz',                   'gasto',   'Factura de la luz'),
('Vivienda',       'Agua',                  'gasto',   'Factura del agua'),
('Vivienda',       'Internet',              'gasto',   'Factura del internet'),
('Alimentacion',   'Compra supermercado',   'gasto',   'Compra semanal o mensual'),
('Alimentacion',   'Comida fuera',          'gasto',   'Restaurantes y bares'),
('Transporte',     'Gasolina',              'gasto',   'Repostaje del coche'),
('Transporte',     'Transporte publico',    'gasto',   'Bus, metro, tren'),
('Suscripciones',  'Netflix',               'gasto',   'Suscripcion mensual Netflix'),
('Suscripciones',  'Spotify',               'gasto',   'Suscripcion mensual Spotify'),
('Suscripciones',  'PS5 - PS Plus',         'gasto',   'Suscripcion PlayStation Plus'),
('Ocio',           'Salidas',               'gasto',   'Bares, salidas'),
('Ocio',           'Cine',                  'gasto',   'Entradas de cine'),
('Ocio',           'Deporte',               'gasto',   'Gimnasio, padel, etc'),
('Ingresos',       'Nomina',                'ingreso', 'Sueldo mensual del trabajo');

INSERT INTO movimiento (id_miembro, id_categoria, importe, fecha, descripcion, es_fijo) VALUES
(1, 1,  750.00, '2025-03-01', 'Alquiler marzo', 1),
(2, 2,  60.00,  '2025-03-03', 'Factura luz marzo', 0),
(2, 5,  320.50, '2025-03-10', 'Compra Mercadona', 0),
(1, 7,  85.00,  '2025-03-12', 'Gasolina Repsol', 0),
(3, 11, 9.99,   '2025-03-15', 'PS Plus mensual', 1),
(2, 9,  13.99,  '2025-03-15', 'Netflix mes', 1),
(3, 12, 45.00,  '2025-03-20', 'Salida sabado noche', 0),
(1, 15, 2200.00,'2025-03-05', 'Nomina Carlos marzo', 0),
(2, 15, 1800.00,'2025-03-05', 'Nomina Laura marzo', 0);

INSERT INTO presupuesto (id_categoria, mes, anio, importe_limite) VALUES
(1, 3, 2025, 750.00),
(5, 3, 2025, 400.00),
(12, 3, 2025, 80.00),
(9, 3, 2025, 15.00);
