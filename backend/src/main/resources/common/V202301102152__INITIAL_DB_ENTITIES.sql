-- #######################################################################################################################
-- Category
-- #######################################################################################################################

CREATE TABLE LLS_CATEGORY
(
    ID              BIGSERIAL not null,
    VERSION         INTEGER not null,
    CREATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY      VARCHAR(36),
    MODIFIED_AT     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY     VARCHAR(36),
    T_NAME          VARCHAR(255) not null,
    DESCRIPTION     TEXT,
    PRIMARY KEY (ID)
);


-- #######################################################################################################################
-- User
-- #######################################################################################################################

CREATE TABLE LLS_USER
(
    ID                  BIGSERIAL not null,
    VERSION             INTEGER not null,
    CREATED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY          VARCHAR(36),
    MODIFIED_AT         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY         VARCHAR(36),
    FIRSTNAME           VARCHAR(255) not null,
    LASTNAME            VARCHAR(255) not null,
    STATUS              TEXT,
    PHONE_NUMBER        VARCHAR(30) not null,
    LOCATION            VARCHAR(150),
    IBAN                VARCHAR(20),
    PAYPAL_LINK         TEXT,
    KEYCLOAK_REFERENCE  VARCHAR(20),
    EMAIL               VARCHAR(255),
    PRIMARY KEY (ID),
    CONSTRAINT UK_LLS_USER_UNIQUE_KEYCLOAK_REFERENCE UNIQUE (KEYCLOAK_REFERENCE),
    CONSTRAINT UK_LLS_USER_UNIQUE_EMAIL UNIQUE (EMAIL)
);


-- #######################################################################################################################
-- Product
-- #######################################################################################################################

CREATE TABLE LLS_PRODUCT
(
    ID              BIGSERIAL not null,
    VERSION         INTEGER not null,
    CREATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY      VARCHAR(36),
    MODIFIED_AT     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY     VARCHAR(36),
    P_NAME          VARCHAR(255) not null,
    PRODUCER        VARCHAR(255) not null,
    QUANTITY        VARCHAR(20) not null,
    PRICE           NUMERIC(10, 2) not null,
    IS_ACTIVE       BOOLEAN not null,
    IS_CHECKED      BOOLEAN not null,
    CATEGORY_ID     BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT FK_LLS_PRODUCT_FK_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES LLS_CATEGORY
);


-- #######################################################################################################################
-- GroceryWorkingDay
-- #######################################################################################################################

CREATE TABLE LLS_GROCERY_WORKING_DAY
(
    ID                  BIGSERIAL not null,
    VERSION             INTEGER not null,
    CREATED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY          VARCHAR(36),
    MODIFIED_AT         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY         VARCHAR(36),
    WORKING_DAY_DATE    DATE not null,
    PAYING_USER_ID      BIGINT,
    PRIMARY KEY (ID),
    CONSTRAINT UK_LLS_GROCERY_WORKING_DAY_UNIQUE_DATE UNIQUE (WORKING_DAY_DATE),
    CONSTRAINT FK_LLS_GROCERY_WORKING_DAY_FK_USER FOREIGN KEY (PAYING_USER_ID) REFERENCES LLS_USER
);

-- #######################################################################################################################
-- GroceryWorkingDayGoingUser
-- #######################################################################################################################

CREATE TABLE LLS_GROCERY_WORKING_DAY_GOING_USER
(
    GROCERY_WORKING_DAY_ID  BIGINT not null,
    USER_ID                 BIGINT not null,
    CONSTRAINT FK_LLS_GROCERY_WORKING_DAY_GOING_USER_FK_GROCERY_WORKING_DAY FOREIGN KEY (GROCERY_WORKING_DAY_ID) REFERENCES LLS_GROCERY_WORKING_DAY,
    CONSTRAINT FK_LLS_GROCERY_WORKING_DAY_GOING_USER_FK_USER FOREIGN KEY (USER_ID) REFERENCES LLS_USER
);


-- #######################################################################################################################
-- OrderItem
-- #######################################################################################################################

CREATE TABLE LLS_ORDER_ITEM
(
    ID              BIGSERIAL not null,
    VERSION         INTEGER not null,
    CREATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY      VARCHAR(36),
    MODIFIED_AT     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY     VARCHAR(36),
    PRODUCT_ID      BIGINT,
    NOTE            TEXT,
    PRICE           NUMERIC(10, 2) not null,
    PRIMARY KEY (ID),
    CONSTRAINT FK_LLS_ORDER_ITEM_FK_PRODUCT FOREIGN KEY (PRODUCT_ID) REFERENCES LLS_PRODUCT
);


-- #######################################################################################################################
-- Order
-- #######################################################################################################################

CREATE TABLE LLS_ORDER
(
    ID              BIGSERIAL not null,
    VERSION         INTEGER not null,
    CREATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY      VARCHAR(36),
    MODIFIED_AT     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_BY     VARCHAR(36),
    USER_ID         BIGINT,
    IS_PAYED        BOOLEAN not null,
    PAYMENT_TYPE    VARCHAR(15),
    PRIMARY KEY (ID),
    CONSTRAINT FK_LLS_ORDER_FK_USER FOREIGN KEY (USER_ID) REFERENCES LLS_USER
);


-- #######################################################################################################################
-- OrderOrderItem
-- #######################################################################################################################

CREATE TABLE LLS_ORDER_ORDER_ITEM
(
    ORDER_ID        BIGINT not null,
    ORDER_ITEM_ID   BIGINT not null,
    CONSTRAINT FK_LLS_ORDER_ORDER_ITEM_FK_ORDER FOREIGN KEY (ORDER_ID) REFERENCES LLS_ORDER,
    CONSTRAINT FK_LLS_ORDER_ORDER_ITEM_FK_ORDER_ITEM FOREIGN KEY (ORDER_ITEM_ID) REFERENCES LLS_ORDER_ITEM
);
