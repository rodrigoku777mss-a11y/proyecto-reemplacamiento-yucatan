CREATE DATABASE IF NOT EXISTS reemplacamiento;
USE reemplacamiento;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS CITA;
DROP TABLE IF EXISTS TRAMITE;
DROP TABLE IF EXISTS VEHICULO;
DROP TABLE IF EXISTS MODULO;
DROP TABLE IF EXISTS MODELO;
DROP TABLE IF EXISTS PROPIETARIO;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Tabla: PROPIETARIO
CREATE TABLE PROPIETARIO (
    id_propietario INT PRIMARY KEY,
    curp VARCHAR(18) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    telefono VARCHAR(15),
    email VARCHAR(100)
);

-- 2. Tabla: MODELO
CREATE TABLE MODELO (
    id_modelo INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    nombre_modelo VARCHAR(50) NOT NULL
);

-- 3. Tabla: MODULO
CREATE TABLE MODULO (
    id_modulo INT PRIMARY KEY,
    nombre_modulo VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    municipio VARCHAR(100) NOT NULL,
    tel_modulo VARCHAR(15)
);

-- 4. Tabla: VEHICULO
CREATE TABLE VEHICULO (
    num_serie VARCHAR(50) PRIMARY KEY,
    placa_actual VARCHAR(20) NOT NULL,
    num_motor VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    id_propietario INT NOT NULL,
    id_modelo INT NOT NULL,
    FOREIGN KEY (id_propietario) REFERENCES PROPIETARIO(id_propietario),
    FOREIGN KEY (id_modelo) REFERENCES MODELO(id_modelo)
);

-- 5. Tabla: TRAMITE
CREATE TABLE TRAMITE (
    folio_tramite VARCHAR(50) PRIMARY KEY,
    tipo_tramite VARCHAR(50) NOT NULL,
    estado_tramite VARCHAR(50) NOT NULL,
    fecha_inicio DATE NOT NULL,
    num_serie VARCHAR(50) NOT NULL,
    FOREIGN KEY (num_serie) REFERENCES VEHICULO(num_serie)
);

-- 6. Tabla: CITA
CREATE TABLE CITA (
    id_cita INT PRIMARY KEY,
    fecha_programada DATE NOT NULL,
    hora TIME NOT NULL,
    estado_cita VARCHAR(50) NOT NULL,
    folio_tramite VARCHAR(50) NOT NULL,
    id_modulo INT NOT NULL,
    FOREIGN KEY (folio_tramite) REFERENCES TRAMITE(folio_tramite),
    FOREIGN KEY (id_modulo) REFERENCES MODULO(id_modulo)
);

