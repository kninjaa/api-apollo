ALTER TABLE tbUser ADD CONSTRAINT us_fk_cliente_id
    FOREIGN KEY (ct_int_id) REFERENCES tbClient (ct_int_id);