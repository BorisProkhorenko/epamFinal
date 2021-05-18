create
database hr_database;

USE hr_database;

SET
GLOBAL time_zone = '+0:00';


create table User
(
    id         bigint primary key NOT NULL AUTO_INCREMENT,
    username   varchar(255),
    password   varchar(255),
    role       enum("Admin", "HR", "Applicant"),
    is_blocked boolean,
    name       varchar(1800),
    gender     enum("male","female") DEFAULT 'male',
    age        int,
    photo      varchar(1800) DEFAULT '',
    contacts   text(7000),
    education  text(7000),
    experience text(7000),
    skills     text(7000)
);

create table Vacancy
(
    id             bigint primary key NOT NULL AUTO_INCREMENT,
    name           varchar(350),
    salary         varchar(350),
    description    text(7000),
    responsibility text(7000),
    requirements   text(7000)
);

create table User_Vacancy
(	id                 bigint primary key NOT NULL AUTO_INCREMENT,
     user_id            bigint,
     vacancy_id         bigint,
     state              enum("New", "Preliminary", "Technical", "Hired", "Rejected" ),
     rating int
);

create table Response
(
    id         bigint primary key NOT NULL AUTO_INCREMENT,
    date       timestamp DEFAULT CURRENT_TIMESTAMP,
    subject    varchar(1800),
    details    varchar(7000),
    user_vacancy_id    bigint
);

alter table user_vacancy
    add foreign key (vacancy_id) references vacancy (id),
add foreign key(user_id) references user(id);

alter table response
    add foreign key (user_vacancy_id) references user_vacancy (id);


insert into user( username, password, role, is_blocked)
values ( 'admin', md5('admin'), 'admin', false);
insert into user( username, password, role, is_blocked)
values ( 'hr', md5('hr'), 'HR', false);
insert into user( username, password, role, is_blocked,name, gender, age, contacts, education, experience,skills)
values ( 'user', md5('user'), 'applicant', false,'Boris Prokhorenko', 'male',25,'contacts','education','experience','skills');
insert into vacancy( name, salary, responsibility, description, requirements)
values ( 'Java Developer', '-', 'responsibility', 'Description',
         'We are looking for engineers who are motivated and can take ownership to drive innovative features with an emphasis on quality. Strong technical expertise in Java;');
insert into vacancy( name, salary, responsibility, description, requirements)
values ( 'Python Developer', '3000-4000$', 'responsibility', 'Description',
         'Good English communication skills. 4+ years of experience in web Back-end development Strong fundamental programming skills such as solid coding standard, design patterns.');

insert into vacancy( name, salary, responsibility, description, requirements)
values ( 'GUI Designer', '1500$', 'responsibility', 'Description',
         'Bachelor with UX/Visual Design, Human-Computer Interaction, Industrial Design or similar degrees preferred A minimum of 3 years of professional experience working in cross-disciplinary teams.');

insert into vacancy( name, salary, responsibility, description, requirements)
values ( 'Salesforce Consultant', '-', 'responsibility', 'Description',
         'excellent communication skills and proficiency in both English and Russian (C1 level)');

insert into vacancy( name, salary, responsibility, description, requirements)
values ( 'HR Coordinator', '1500$', 'responsibility', 'Description',
         'Proven experience as an HR coordinator or relevant human resources position');

insert into user_vacancy(user_id, vacancy_id, state, preliminary_points)
values (3, 1, 'New', 100);

insert into response(subject, details, user_vacancy_id)
values ('Some offer', 'details', 1);