INSERT INTO tbEstablishmentType (et_str_establishmentType)
SELECT 'Geral' FROM dual UNION
SELECT 'Especializado' FROM dual UNION
SELECT 'de Urgência e Emergência' FROM dual UNION
SELECT 'de Clínicas' FROM dual UNION
SELECT 'Psiquiátrico' FROM dual UNION
SELECT 'Reabilitação' FROM dual UNION
SELECT 'Dia' FROM dual UNION
SELECT 'público' FROM dual UNION
SELECT 'privado' FROM dual UNION
SELECT 'filantrópico' FROM dual UNION
SELECT 'conveniado' FROM dual UNION
SELECT 'urbano' FROM dual UNION
SELECT 'rural' FROM dual UNION
SELECT 'Estadual' FROM dual UNION
SELECT 'Federal' FROM dual UNION
SELECT 'Municipal' FROM dual;



INSERT INTO tbEquipmentType (eqt_str_equipmentType)
SELECT 'Materiais de Consumo Geral' FROM dual UNION
SELECT 'Equipamentos de Proteção Individual' FROM dual UNION
SELECT 'Suprimentos para Farmácia' FROM dual;


INSERT INTO tbEquipment (eq_str_name)
-- Materiais de Consumo Geral
SELECT 'Luvas descartáveis' FROM dual UNION
SELECT 'Máscaras faciais' FROM dual UNION
SELECT 'Aventais descartáveis' FROM dual UNION
SELECT 'Toucas descartáveis' FROM dual UNION
SELECT 'Seringas' FROM dual UNION
SELECT 'agulhas' FROM dual UNION
SELECT 'Gaze' FROM dual UNION
SELECT 'Algodão' FROM dual UNION
SELECT 'Esparadrapos' FROM dual UNION
SELECT 'Fitas adesivas médicas' FROM dual UNION
SELECT 'Cateteres intravenosos' FROM dual UNION

-- Equipamentos de Proteção Individual
SELECT 'Óculos de proteção' FROM dual UNION
SELECT 'Protetores faciais' FROM dual UNION
SELECT 'Respiradores' FROM dual UNION
SELECT 'máscaras N95' FROM dual UNION
SELECT 'Capotes cirúrgicos' FROM dual UNION
SELECT 'Sapatos ' FROM dual UNION
SELECT 'coberturas de sapatos descartáveis ' FROM dual UNION

-- Suprimentos para Farmácia
SELECT 'Medicamentos diversos' FROM dual UNION
SELECT 'Soro fisiológico' FROM dual UNION
SELECT 'Soluções intravenosas' FROM dual;