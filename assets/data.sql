create table if not exists member_info (
     taxNumber varchar(255) not null,
     email varchar(255),
     fullName varchar(255),
     username varchar(255),
     primary key (taxNumber)
);

create table if not exists divorce_paper (
   id serial not null,
   childSupport integer,
   created_at timestamp(6),
   notarialActionId varchar(255),
   numberOfChildren integer,
   restoreName boolean,
   status varchar(255),
   primary key (id)
);

create table if not exists divorce_paper_member_info (
   member_info_taxNumber varchar(255) not null,
   divorce_paper_id integer not null
);

alter table if exists divorce_paper_member_info
    add constraint FKogn61wf3a2w7c4isu1q7xdopr
        foreign key (divorce_paper_id)
            references divorce_paper;

alter table if exists divorce_paper_member_info
    add constraint FKs53gytb2o15lx212murbyr7oj
        foreign key (member_info_taxNumber)
            references member_info;

create table acceptance (
    id serial not null,
    tax_number varchar(255),
    acceptance_id integer,
    primary key (id)
);

alter table if exists acceptance
    add constraint FKlehslhnj2kob6tdy0lu1b6d72
        foreign key (tax_number)
            references member_info;

alter table if exists acceptance
    add constraint FK22pu03ttm6d84h0ayb36s87i3
        foreign key (acceptance_id)
            references divorce_paper
            on delete cascade;

INSERT INTO member_info (taxNumber, email, fullName, username) VALUES
('456789011', 'example@gmail.com', 'John Doe', 'lawyer1'),
('456789012', 'example@gmail.com', 'John Doe', 'lawyer2'),
('456789013', 'example@gmail.com', 'John Doe', 'lawyer3'),
('456789014', 'example@gmail.com', 'John Doe', 'lawyer4'),
('456789015', 'example@gmail.com', 'John Doe', 'lawyer5'),
('456789016', 'example@gmail.com', 'John Doe', 'lawyer6'),
('456789017', 'example@gmail.com', 'John Doe', 'lawyer7'),
('456789018', 'example@gmail.com', 'John Doe', 'lawyer8'),
('456789019', 'example@gmail.com', 'John Doe', 'lawyer9'),
('456789010', 'example@gmail.com', 'John Doe', 'lawyer10'),
('556789011', 'example@gmail.com', 'John Doe', 'spouse1'),
('556789012', 'example@gmail.com', 'John Doe', 'spouse2'),
('556789013', 'example@gmail.com', 'John Doe', 'spouse3'),
('556789014', 'example@gmail.com', 'John Doe', 'spouse4'),
('556789015', 'example@gmail.com', 'John Doe', 'spouse5'),
('556789016', 'example@gmail.com', 'John Doe', 'spouse6'),
('556789017', 'example@gmail.com', 'John Doe', 'spouse7'),
('556789018', 'example@gmail.com', 'John Doe', 'spouse8'),
('556789019', 'example@gmail.com', 'John Doe', 'spouse9'),
('556789010', 'example@gmail.com', 'John Doe', 'spouse10'),
('656789011', 'example@gmail.com', 'John Doe', 'notary1'),
('656789012', 'example@gmail.com', 'John Doe', 'notary2'),
('656789013', 'example@gmail.com', 'John Doe', 'notary3'),
('656789014', 'example@gmail.com', 'John Doe', 'notary4'),
('656789015', 'example@gmail.com', 'John Doe', 'notary5'),
('123456789', 'example@gmail.com', 'John Doe', 'user1'),
('123456780', 'example@gmail.com', 'John Doe', 'user2'),
('123456781', 'example@gmail.com', 'John Doe', 'user3'),
('123456782', 'example@gmail.com', 'John Doe', 'user4');

INSERT INTO divorce_paper(childSupport, created_at, notarialActionId, numberOfChildren,restoreName, status) VALUES
(100, '2003/12/13 10:13:18', '123456789', 1, true, 'PENDING'),
(200, '2003/12/13 10:13:18', '123456789', 2, true, 'PENDING'),
(300, '2003/12/13 10:13:18', '123456789', 3, true, 'PENDING'),
(400, '2003/12/13 10:13:18', '123456789', 4, true, 'PENDING'),
(500, '2003/12/13 10:13:18', '123456789', 5, true, 'PENDING');

INSERT INTO divorce_paper_member_info(member_info_taxNumber, divorce_paper_id) VALUES
('456789011', 1),
('456789012', 1),
('556789011', 1),
('556789012', 1),
('656789011', 1),
('456789011', 2),
('456789012', 2),
('556789013', 2),
('556789014', 2),
('656789012', 2),
('456789011', 3),
('456789012', 3),
('556789015', 3),
('556789016', 3),
('656789013', 3),
('456789011', 4),
('456789012', 4),
('556789017', 4),
('556789018', 4),
('656789014', 4),
('456789011', 5),
('456789012', 5),
('556789019', 5),
('556789010', 5),
('656789015', 5);

INSERT INTO acceptance(tax_number, acceptance_id) VALUES
('456789012', 1),
('556789011', 1),
('556789012', 1);
