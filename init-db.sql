create schema if not exists myschema;

CREATE TABLE If NOT EXISTS myschema.customers (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    company_email VARCHAR(255) NOT NULL,
    tax_id VARCHAR(50) NOT NULL
);