DROP TABLE IF EXISTS MESSAGE;
DROP TABLE IF EXISTS CHATROOM_USER;
DROP TABLE IF EXISTS CHATROOM;
DROP TABLE IF EXISTS USER;

CREATE TABLE USER
(
    ID          SERIAL PRIMARY KEY AUTO_INCREMENT,
    USER_ID     INT                                    NOT NULL,
    CREATE_TIME DATETIME(3) DEFAULT (UTC_TIMESTAMP(3)) NOT NULL
);

CREATE TABLE CHATROOM
(
    ID          SERIAL PRIMARY KEY AUTO_INCREMENT,
    STATUS      INT                                    NOT NULL,
    CREATOR_ID  INT                                    NOT NULL,
    CREATE_TIME DATETIME(3) DEFAULT (UTC_TIMESTAMP(3)) NOT NULL
);

CREATE TABLE CHATROOM_USER
(
    ID          SERIAL PRIMARY KEY AUTO_INCREMENT,
    CHATROOM_ID BIGINT                                 NOT NULL,
    USER_ID     INT                                    NOT NULL,
    ROLE        INT                                    NOT NULL,
    CREATE_TIME DATETIME(3) DEFAULT (UTC_TIMESTAMP(3)) NOT NULL,
    UNIQUE KEY (CHATROOM_ID, USER_ID)
);

CREATE TABLE MESSAGE
(
    ID          SERIAL PRIMARY KEY AUTO_INCREMENT,
    CHATROOM_ID BIGINT                                 NOT NULL,
    CONTENT     VARCHAR(255),
    STATUS      INT                                    NOT NULL,
    CREATOR_ID  INT                                    NOT NULL,
    CREATE_TIME DATETIME(3) DEFAULT (UTC_TIMESTAMP(3)) NOT NULL
);
