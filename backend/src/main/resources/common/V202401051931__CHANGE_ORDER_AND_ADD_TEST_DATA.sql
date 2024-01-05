-- #######################################################################################################################
-- ORDER
-- #######################################################################################################################

ALTER TABLE LLS_ORDER ADD ORDER_STATUS VARCHAR(30) NOT NULL;


-- #######################################################################################################################
-- GROCERY_WORKING_DAY
-- #######################################################################################################################

ALTER TABLE LLS_GROCERY_WORKING_DAY ADD DAY_STATUS VARCHAR(30) NOT NULL;


-- #######################################################################################################################
-- TEST DATA
-- #######################################################################################################################

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), '622cb777-6037-43d3-a93a-47a0bbaa2692', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Konserven', 'Thunfisch, Mais,...');

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), 'ff429f4f-599d-43da-8f03-1ae44f942686', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Backbox', 'Semmel, Laugenstangerl,...');

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), '5bf3c8ee-3f0d-49a6-a6c3-02dea6775590', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Wurst', '... von der Frischetheke');

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), '9a97cca5-2d44-4c10-8195-b589143a7301', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Getränke', 'PET, Dosen,...');

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), '596f1eaa-cea9-49fd-8dac-3fbc69948332', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Fleisch', '... von der Frischetheke');

INSERT INTO LLS_CATEGORY (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, T_NAME, DESCRIPTION)
VALUES (nextval('lls_category_id_seq'::regclass), '122435d5-f9da-4147-aa4b-a106f788bf60', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Obst', '... frisches Obst');

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), 'b31ee24f-006a-4884-b7e7-147cd28a4bd8', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Apfel', 'Billa', 'Stück', 0.59, true, true, (select id from lls_category where t_name = 'Obst'));

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), '0d0daa89-ec4d-446a-a67b-15fb294c8121', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Thunfisch', 'Clever', 'Dose', 1.47, true, true, (select id from lls_category where t_name = 'Konserven'));

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), 'cf0b5915-002f-47a4-9089-88831b3681f4', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Putenschinken', 'Billa', '100g', 1.99, true, true, (select id from lls_category where t_name = 'Wurst'));

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), '4c398dbc-deaa-450c-86db-4b32a3136625', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'halbes Hänchen', 'Billa', 'Stück', 2.99, true, true, (select id from lls_category where t_name = 'Fleisch'));

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), 'f4f0198b-4389-4a6b-aa02-a3cc7f913577', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'weißes Monster', 'Monster', 'Dose (250ml)', 1.35, true, true, (select id from lls_category where t_name = 'Getränke'));

INSERT INTO LLS_PRODUCT (ID, UUID, VERSION, CREATED_AT, CREATED_BY, MODIFIED_AT, MODIFIED_BY, P_NAME, PRODUCER, QUANTITY, PRICE, IS_ACTIVE, IS_CHECKED, CATEGORY_ID)
VALUES (nextval('lls_product_id_seq'::regclass), 'aa52d75d-ef13-4107-ba0b-327666a327d0', 0, current_timestamp, 'SYSTEM', current_timestamp, 'SYSTEM', 'Kaisersemmel', 'Billa', 'Stück', 0.49, true, true, (select id from lls_category where t_name = 'Backbox'));
