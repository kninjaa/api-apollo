ALTER TABLE tbClient ADD CONSTRAINT ct_fk_establishmentType_str
    FOREIGN KEY (et_str_establishmentType) REFERENCES tbEstablishmentType (et_str_establishmentType);

ALTER TABLE tbAccount ADD CONSTRAINT ac_fk_cliente_id
    FOREIGN KEY (ct_int_id) REFERENCES tbClient (ct_int_id);

ALTER TABLE tbAddress ADD CONSTRAINT ad_fk_cliente_id
    FOREIGN KEY (ct_int_id) REFERENCES tbClient (ct_int_id);

ALTER TABLE tbEquipment ADD CONSTRAINT eq_fk_equipmentType_str
    FOREIGN KEY (eqt_str_equipmentType) REFERENCES tbEquipmentType (eqt_str_equipmentType);

ALTER TABLE tbOrders ADD CONSTRAINT ord_fk_client_id
    FOREIGN KEY (ct_int_id) REFERENCES tbClient (ct_int_id);

ALTER TABLE tbOrders ADD CONSTRAINT ord_fk_equipment_id
    FOREIGN KEY (eq_int_id) REFERENCES tbEquipment (eq_int_id);

ALTER TABLE tbOrders ADD CONSTRAINT ord_fk_address_id
    FOREIGN KEY (ad_int_id) REFERENCES tbAddress (ad_int_id);