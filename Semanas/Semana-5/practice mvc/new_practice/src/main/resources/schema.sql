-- ==========================================
-- PostgreSQL Schema
-- ==========================================
-- 1. Crear la base de datos manualmente:
-- CREATE DATABASE appdb;
-- 2. Conectarse a la base de datos (en psql: \c appdb)

CREATE TABLE IF NOT EXISTS usuarios (
    id      SERIAL PRIMARY KEY,
    nombre  VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE
);

CREATE INDEX IF NOT EXISTS idx_nombre ON usuarios(nombre);


-- ==========================================
-- MySQL Schema (Referencia)
-- ==========================================
-- CREATE DATABASE IF NOT EXISTS appdb;
-- USE appdb;

-- CREATE TABLE IF NOT EXISTS usuarios (
--     id      INT AUTO_INCREMENT PRIMARY KEY,
--     nombre  VARCHAR(100) NOT NULL,
--     email   VARCHAR(150) NOT NULL UNIQUE,
--     INDEX idx_nombre (nombre)
-- );