drop table IF EXISTS valentines_seasonal_help;
drop table IF EXISTS employee_shift;
--https://www.w3schools.com/sql/sql_datatypes.asp


create TABLE valentines_seasonal_help (
  emp_id INT AUTO_INCREMENT  PRIMARY KEY,
  emp_name VARCHAR(250) NOT NULL,
  emp_phone VARCHAR(30) NULL,
  emp_address VARCHAR(250) NULL,
  emp_email VARCHAR(250) NULL
);


create TABLE employee_shift (
  shift_id INT AUTO_INCREMENT  PRIMARY KEY,
  emp_id int,
  shift_date DATE NOT NULL,
  shift_clockIn VARCHAR(30) NULL,
  shift_clockOut VARCHAR(250) NULL,
  FOREIGN KEY (emp_id) REFERENCES valentines_seasonal_help(emp_id)
 );

insert into valentines_seasonal_help
(emp_name, emp_phone, emp_address, emp_email)
values
('Braden Borman', '5738261903', '3601 West Broadway Apt 21102 Columbia Mo', 'bradenborman00@gmail.com'),
('Sarah Smith', '5738268879', '7452 North Sreet Rd Columbia Mo', 'sarahhhhsimth@aol.com'),
('Alber Wilson', '5735551733', '2523 Chappel Hill Apt 23452 Columbia Mo', 'wilson234@gmail.com');

insert into employee_shift
(emp_id, shift_date, shift_clockIn)
values (1, NOW(), '11:20 PM');


insert into employee_shift
(emp_id, shift_date, shift_clockIn)
values (2, NOW(), '10:15 PM');

insert into employee_shift
(emp_id, shift_date, shift_clockIn)
values (2, NOW(), '11:15 PM');


--insert into employee_shift
--(emp_id, shift_date)
--values (1, NOW());