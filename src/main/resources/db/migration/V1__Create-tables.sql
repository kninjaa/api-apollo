CREATE TABLE tbClient (
    ct_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    ct_bool_situation VARCHAR2(4) NOT NULL,
    ct_str_name VARCHAR2(255) NOT NULL,
    ct_str_cpf VARCHAR2(11),
    ct_str_cnpj VARCHAR2(14),
    ct_str_birth DATE NOT NULL,
    ct_bool_deficient VARCHAR2(4) NOT NULL
);

CREATE TABLE tbUser (
    us_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    us_str_login VARCHAR2(255) NOT NULL,
    us_str_email VARCHAR(255) NOT NULL,
    us_str_password VARCHAR2(4000) NOT NULL,
    us_dat_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    us_dat_end TIMESTAMP,
    ct_int_id INTEGER
);