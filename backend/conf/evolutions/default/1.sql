-- !Ups

CREATE TABLE "user"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"       VARCHAR   NOT NULL,
    "password"   VARCHAR   NOT NULL,
    "email"      VARCHAR   NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL
);

CREATE TABLE "category"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"       VARCHAR   NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL
);

CREATE TABLE "product"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "name"       VARCHAR   NOT NULL,
    "description" VARCHAR   NOT NULL,
    "category_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE "promotion"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "product_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product(id)
);

CREATE TABLE "discount"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "product_id" INTEGER NOT NULL,
    "user_id"    INTEGER NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE "order"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user_id"   INTEGER NOT NULL,
    "amount"    REAL NOT NULL,
    "date"      VARCHAR NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE "order_element"
(
    "id"         INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,
    "product_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    FOREIGN KEY(product_id) REFERENCES product(id)
);

-- !Downs

DROP TABLE "user"
DROP TABLE "product"
DROP TABLE "category"
DROP TABLE "promotion"
DROP TABLE "discount"
DROP TABLE "order_element"
DROP TABLE "order"