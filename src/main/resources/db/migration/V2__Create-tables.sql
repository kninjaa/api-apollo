CREATE TABLE tbEstablishmentType (
    et_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    et_str_establishmentType VARCHAR2(60) UNIQUE NOT NULL
);

CREATE TABLE tbEquipmentType (
    eqt_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    eqt_str_equipmentType VARCHAR2(255) UNIQUE NOT NULL
);

CREATE TABLE tbClient (
    ct_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    ct_bool_situation VARCHAR2(6) NOT NULL,
    ct_str_nameCorporateReason VARCHAR2(255) NOT NULL,
    ct_str_fantasyName VARCHAR2(255) NOT NULL,
    ct_str_cnpj VARCHAR2(14) UNIQUE NOT NULL,
    ct_str_contact VARCHAR2(20) NOT NULL,
    ct_flo_rating FLOAT NOT NULL,
    et_str_establishmentType VARCHAR2(60)
);

CREATE TABLE tbAddress(
    ad_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    ad_str_street VARCHAR2(255) NOT NULL,
    ad_int_number NUMERIC(6) NOT NULL,
    ad_str_complement VARCHAR2(30) NOT NULL,
    ad_str_city VARCHAR2(100) NOT NULL,
    ad_str_cep VARCHAR2(10) NOT NULL,
    ct_int_id INTEGER
);

CREATE TABLE tbAccount (
    ac_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    ac_str_login VARCHAR2(255) UNIQUE NOT NULL,
    ac_str_emailCorp VARCHAR2(255) UNIQUE NOT NULL,
    ac_str_password VARCHAR2(4000) NOT NULL,
    ac_dat_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ac_dat_end TIMESTAMP,
    ct_int_id INTEGER
);

CREATE TABLE tbEquipment (
    eq_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    eq_str_name VARCHAR2(40) NOT NULL,
    eqt_str_equipmentType VARCHAR2(255)
);

CREATE TABLE tbOrders(
     ord_int_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     ord_str_request VARCHAR2(255),
     ord_dat_requestDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     ct_int_id INTEGER,
     eq_int_id INTEGER,
     ad_int_id INTEGER,
     ord_str_shipping VARCHAR2(255)
);