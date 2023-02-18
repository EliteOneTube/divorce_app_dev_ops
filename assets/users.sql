CREATE TABLE IF NOT exists users (
     username VARCHAR(50) PRIMARY KEY,
     password VARCHAR(100) not null,
     enabled boolean not null
);

CREATE TABLE IF NOT EXISTS authorities (
    id serial not null,
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    primary key (id),
   CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO users(username, password, enabled) VALUES
('lawyer1', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer2', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer3', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer4', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer5', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer6', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer7', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer8', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer9', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('lawyer10', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse1', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse2', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse3', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse4', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse5', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse6', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse7', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse8', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse9', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('spouse10', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('notary1', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('notary2', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('notary3', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('notary4', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('notary5', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true),
('admin', '$2a$10$1Hzd.gTzQc/KaguC3BzNO.fkLTRpoVs5eP1M7xbw0PEDcu5LVJGAS', true);

INSERT INTO authorities(username, authority) VALUES
('lawyer1', 'ROLE_LAWYER'),
('lawyer2', 'ROLE_LAWYER'),
('lawyer3', 'ROLE_LAWYER'),
('lawyer4', 'ROLE_LAWYER'),
('lawyer5', 'ROLE_LAWYER'),
('lawyer6', 'ROLE_LAWYER'),
('lawyer7', 'ROLE_LAWYER'),
('lawyer8', 'ROLE_LAWYER'),
('lawyer9', 'ROLE_LAWYER'),
('lawyer10', 'ROLE_LAWYER'),
('spouse1', 'ROLE_SPOUSE'),
('spouse2', 'ROLE_SPOUSE'),
('spouse3', 'ROLE_SPOUSE'),
('spouse4', 'ROLE_SPOUSE'),
('spouse5', 'ROLE_SPOUSE'),
('spouse6', 'ROLE_SPOUSE'),
('spouse7', 'ROLE_SPOUSE'),
('spouse8', 'ROLE_SPOUSE'),
('spouse9', 'ROLE_SPOUSE'),
('spouse10', 'ROLE_SPOUSE'),
('notary1', 'ROLE_NOTARY'),
('notary2', 'ROLE_NOTARY'),
('notary3', 'ROLE_NOTARY'),
('notary4', 'ROLE_NOTARY'),
('notary5', 'ROLE_NOTARY'),
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_LAWYER'),
('admin', 'ROLE_SPOUSE'),
('admin', 'ROLE_NOTARY');






