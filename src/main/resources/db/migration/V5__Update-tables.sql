UPDATE tbEquipment
    SET eqt_str_equipmentType = (
        CASE
            WHEN eq_str_name IN (
                 'Luvas descartáveis', 'Máscaras faciais', 'Aventais descartáveis',
                 'Toucas descartáveis', 'Seringas', 'agulhas', 'Gaze', 'Algodão',
                 'Esparadrapos', 'Fitas adesivas médicas', 'Cateteres intravenosos'
                ) THEN 'Materiais de Consumo Geral'

            WHEN eq_str_name IN (
                 'Óculos de proteção', 'Protetores faciais', 'Respiradores', 'máscaras N95',
                 'Capotes cirúrgicos', 'Sapatos ', 'coberturas de sapatos descartáveis '
                ) THEN 'Equipamentos de Proteção Individual'

            WHEN eq_str_name IN (
                     'Medicamentos diversos', 'Soro fisiológico', 'Soluções intravenosas'
                ) THEN 'Suprimentos para Farmácia'
            END
        );