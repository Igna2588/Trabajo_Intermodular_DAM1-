CREATE TABLE miembro (
    id_miembro INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(50) NOT NULL,
    apellido   VARCHAR(50) NOT NULL,
    rol        ENUM('padre','madre','hijo','hija','otro') NOT NULL,
    email      VARCHAR(100) UNIQUE
);

CREATE TABLE categoria (
    id_categoria  INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(50)  NOT NULL,
    subcategoria  VARCHAR(80)  NOT NULL,
    tipo          ENUM('gasto','ingreso') NOT NULL,
    descripcion   VARCHAR(150)
);

CREATE TABLE movimiento (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_miembro    INT          NOT NULL,
    id_categoria  INT          NOT NULL,
    importe       DECIMAL(8,2) NOT NULL CHECK (importe > 0),
    fecha         DATE         NOT NULL,
    descripcion   VARCHAR(200),
    es_fijo       TINYINT DEFAULT 0,
    FOREIGN KEY (id_miembro)   REFERENCES miembro(id_miembro),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE presupuesto (
    id_presupuesto INT AUTO_INCREMENT PRIMARY KEY,
    id_categoria   INT          NOT NULL,
    mes            TINYINT      NOT NULL CHECK (mes BETWEEN 1 AND 12),
    anio           YEAR         NOT NULL,
    importe_limite DECIMAL(8,2) NOT NULL CHECK (importe_limite > 0),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE saldo_acumulado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mes TINYINT NOT NULL,
    anio YEAR NOT NULL,
    ahorros_mes DECIMAL(8,2) NOT NULL,
    saldo_total DECIMAL(8,2) NOT NULL
);
