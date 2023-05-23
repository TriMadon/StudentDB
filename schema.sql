USE StudentSystem;

CREATE TABLE students (
	id INT PRIMARY KEY,
	name VARCHAR(20),
	password VARCHAR(4)
);

CREATE TABLE courses (
	id VARCHAR(4) PRIMARY KEY
);

CREATE TABLE grades (
	sID VARCHAR(2),
	cID VARCHAR(4),
	grade INT
);

insert into students values (01, "Mohammad", 1111);
insert into students values (02, "Faris", 2222);
insert into students values (03, "Bob", 3333);
insert into students values (04, "John", 4444);
insert into students values (05, "Tasnim", 5555);
insert into students values (06, "Yara", 6666);

insert into courses values ("CS01");
insert into courses values ("EN01");
insert into courses values ("AR01");
insert into courses values ("DB01");


insert into grades values (01, "CS01", 95);
insert into grades values (02, "CS01", 92);
insert into grades values (03, "CS01", 80);
insert into grades values (04, "CS01", 73);
insert into grades values (05, "CS01", 59);
insert into grades values (06, "CS01", 99);

insert into grades values (01, "EN01", 78);
insert into grades values (02, "EN01", 52);
insert into grades values (03, "EN01", 88);
insert into grades values (04, "EN01", 90);
insert into grades values (05, "EN01", 79);
insert into grades values (06, "EN01", 90);

insert into grades values (01, "AR01", 95);
insert into grades values (02, "AR01", 70);
insert into grades values (03, "AR01", 77);
insert into grades values (04, "AR01", 89);
insert into grades values (05, "AR01", 45);
insert into grades values (06, "AR01", 67);

insert into grades values (01, "DB01", 69);
insert into grades values (02, "DB01", 66);
insert into grades values (03, "DB01", 79);
insert into grades values (04, "DB01", 100);
insert into grades values (05, "DB01", 85);
insert into grades values (06, "DB01", 76);
