
drop table patient_survey_logs;
drop table patient_survey;
drop table question_answers;
drop table survey_status;
drop table survey;
drop table patient;
drop table questions;
drop table input_types;
drop table answers;
drop table audit_table;

create table audit_table(audit_id int not null auto_increment,
created_date datetime not null,
modified_date datetime not null,
primary key(audit_id))ENGINE=INNODB;

create table patient(patient_id int not null auto_increment,
first_name varchar(64) not null,
last_name varchar(64) not null,
gender set('male','female'),
age tinyint(2) not null,
date_of_birth date,
email varchar(64) not null,
mobile varchar(32) not null,
address_1 varchar(256) not null,
address_2 varchar(256) not null, 
city varchar(64) not null,
state varchar(64) not null,
country varchar(64) not null,
pincode varchar(32) not null,
audit_id int not null,
primary key(patient_id),
foreign key(audit_id) references audit_table(audit_id))ENGINE=INNODB;

create table survey(survey_id int not null auto_increment,
survey_name varchar(64) not null,
survey_description text not null,
survey_min_age_group tinyint(2) not null,
survey_max_age_group tinyint(2) not null,
audit_id int not null,
primary key(survey_id),
foreign key(audit_id) references audit_table(audit_id))ENGINE=INNODB;

create table survey_status(survey_status_id int not null auto_increment,
survey_status_name varchar(64) not null,
survey_status_description varchar(128) not null,
primary key(survey_status_id))ENGINE=INNODB;

create table input_types (input_type_id int not null auto_increment,
input_type_name enum('single','multiple','text') default 'single',
input_type_description varchar(128) not null,
audit_id int not null,
primary key(input_type_id),
foreign key(audit_id) references audit_table(audit_id))ENGINE=INNODB;

create table questions (question_id int not null auto_increment,
question_text text not null,
input_type_id int unsigned not null,
audit_id int not null,
primary key(question_id),
foreign key(audit_id) references audit_table(audit_id))ENGINE=INNODB;

create table answers (answer_id int not null auto_increment,
answer_text text not null,
audit_id int not null,
primary key(answer_id),
foreign key(audit_id) references audit_table(audit_id))ENGINE=INNODB;

create table question_answers (question_answer_id int not null auto_increment,
survey_id int not null,
question_id int not null,
answer_id int not null,
score_value smallint(3) not null,
relative_question_id int null,
condition_question_id int null,
primary key (question_answer_id),
foreign key (survey_id) references survey(survey_id),
foreign key (question_id) references questions(question_id),
foreign key (answer_id) references answers(answer_id),
foreign key (relative_question_id) references questions(question_id),
foreign key (condition_question_id) references questions(question_id))ENGINE=INNODB;

create table patient_survey (patient_survey_id int not null auto_increment,
survey_status_id int not null,
survey_id int not null,
patient_id int not null,
question_id int not null,
answer_id int not null,
answer_text text default '',
date_taken timestamp,
date_completed timestamp,
primary key(patient_survey_id),
foreign key(survey_status_id) references survey_status(survey_status_id),
foreign key(survey_id) references survey(survey_id),
foreign key(patient_id) references patient(patient_id),
foreign key(question_id) references questions(question_id),
foreign key(answer_id) references answers(answer_id))ENGINE=INNODB;

alter table patient_survey modify patient_survey_id int AUTO_INCREMENT;

create table patient_survey_logs (patient_survey_log_id int not null auto_increment,
patient_survey_id int not null,
survey_status_id int not null,
survey_id int not null,
patient_id int not null,
question_id int not null,
answer_id int not null,
answer_text text default '',
date_taken timestamp,
date_completed timestamp,
primary key(patient_survey_log_id),
foreign key(patient_survey_id) references patient_survey(patient_survey_id),
foreign key(survey_status_id) references survey_status(survey_status_id),
foreign key(survey_id) references survey(survey_id),
foreign key(patient_id) references patient(patient_id),
foreign key(question_id) references questions(question_id),
foreign key(answer_id) references answers(answer_id))ENGINE=INNODB;

insert into audit_table(created_date,modified_date) values('2014-09-30 10:46:05','2014-09-30 10:46:05');

insert into patient(first_name,last_name,gender,age,date_of_birth,email,mobile,address_1,address_2,city,state,country,pincode,audit_id) values('Naresh','Karri','male',25,'1988-12-19','naresh.karri@tekzenit.com','9014491965','KRM Colony','Maddilapalem','Visakhapatnam','Andhra Pradesh','India','530013',1);

insert into survey(survey_name,survey_description,survey_min_age_group,survey_max_age_group,audit_id) values('Asthma','Tests ashtma condition',5,10,1);

insert into survey_status (survey_status_name,survey_status_description) values ('opened','survey opened for asthma patient');

insert into input_types (input_type_name,input_type_description,audit_id) values('single','single choice answer',1);
insert into input_types (input_type_name,input_type_description,audit_id) values('multiple','multiple choice answer',1);
insert into input_types (input_type_name,input_type_description,audit_id) values('text','fill in the blank',1);

insert into questions (question_text,input_type_id,audit_id) values ('Are you aware of your right to 48-hour access to general medical services under the GMs contract?',2,1);
insert into questions (question_text,input_type_id,audit_id) values ('In the past 6 months, have you tried to see a GP or healthcare professional fairly quickly?',1,1);
insert into questions (question_text,input_type_id,audit_id) values ('Were you able to see a GP or healthcare professional on the same day or in the next 2 days the GP surgery or health centre was open?',1,1);
insert into questions (question_text,input_type_id,audit_id) values ('If you couldnt be seen within the next 2 days the GP surgery or health center was open, Why was that?',2,1);
insert into questions (question_text,input_type_id,audit_id) values ('If you have not seen a GP or healthcare professional in the past 6 months, why is that?',2,1);

insert into answers (answer_text,audit_id) values ('I have not needed to go',1);
insert into answers (answer_text,audit_id) values ('I could not be seen at a convenient time',1);
insert into answers (answer_text,audit_id) values ('I could not get to the GP surgery or health centre easily',1);
insert into answers (answer_text,audit_id) values ('I did not like or trust the doctors',1);
insert into answers (answer_text,audit_id) values ('Yes',1);
insert into answers (answer_text,audit_id) values ('No',1);
insert into answers (answer_text,audit_id) values ('Cant remember',1);
insert into answers (answer_text,audit_id) values ('There werent any appointments',1);
insert into answers (answer_text,audit_id) values ('The times offered didnt suit me',1);
insert into answers (answer_text,audit_id) values ('The appointment was with a doctor I didnt want to see',1);
insert into answers (answer_text,audit_id) values ('I could have seen a nurse but I wanted to see a doctor',1);
insert into answers (answer_text,audit_id) values ('In the past 3 months',1);
insert into answers (answer_text,audit_id) values ('In the past 6 months',1);
insert into answers (answer_text,audit_id) values ('More than 6 months ago',1);
insert into answers (answer_text,audit_id) values ('Between 3 and 6 months ago',1);
insert into answers (answer_text,audit_id) values ('I have never seen health care centre',1);

insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,1,1,0,null,2);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,1,2,1,null,3);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,1,3,2,3,5);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,1,4,3,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,2,5,0,null,4);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,2,6,1,5,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,3,5,0,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,3,6,1,null,5);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,4,12,0,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,4,13,1,3,5);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,4,14,2,2,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,4,15,3,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,4,16,4,null,5);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,1,0,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,2,1,2,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,3,2,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,4,3,2,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,5,4,null,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,6,5,1,null);
insert into question_answers (survey_id,question_id,answer_id,score_value,relative_question_id,condition_question_id) values (1,5,7,6,null,null);
