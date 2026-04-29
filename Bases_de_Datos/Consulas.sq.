-- 1. Ver todos los movimientos
SELECT * FROM movimiento;

-- 2. Ver todos los gastos con el nombre del miembro
SELECT miembro.nombre, movimiento.importe, movimiento.fecha, movimiento.descripcion
FROM movimiento
JOIN miembro ON movimiento.id_miembro = miembro.id_miembro;

-- 3. Ver todos los gastos con su categoria y subcategoria
SELECT movimiento.descripcion, categoria.nombre, categoria.subcategoria, movimiento.importe
FROM movimiento
JOIN categoria ON movimiento.id_categoria = categoria.id_categoria
WHERE categoria.tipo = 'gasto';

-- 4. Buscar los gastos de Ignacio
SELECT movimiento.descripcion, movimiento.importe, movimiento.fecha
FROM movimiento
JOIN miembro ON movimiento.id_miembro = miembro.id_miembro
WHERE miembro.nombre = 'Ignacio';

-- 5. Ver los gastos ordenados de mayor a menor importe
SELECT movimiento.descripcion, movimiento.importe, movimiento.fecha
FROM movimiento
ORDER BY movimiento.importe DESC;

-- 6. Ver los ahorros del mes
SELECT
    SUM(CASE WHEN categoria.tipo = 'ingreso' THEN movimiento.importe ELSE 0 END) AS total_ingresos,
    SUM(CASE WHEN categoria.tipo = 'gasto' THEN movimiento.importe ELSE 0 END) AS total_gastos,
    SUM(CASE WHEN categoria.tipo = 'ingreso' THEN movimiento.importe ELSE -movimiento.importe END) AS ahorros
FROM movimiento
JOIN categoria ON movimiento.id_categoria = categoria.id_categoria;
