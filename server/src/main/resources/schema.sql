--drop table IF EXISTS valentines_seasonal_help;
--drop table IF EXISTS employee_shift;
--https://www.w3schools.com/sql/sql_datatypes.asp


--create TABLE valentines_seasonal_help (
--  emp_id INT AUTO_INCREMENT  PRIMARY KEY,
--  emp_name VARCHAR(250) NOT NULL,
--  emp_phone VARCHAR(30) NULL,
--  emp_address VARCHAR(250) NULL,
--  emp_email VARCHAR(250) NULL
--);


--create TABLE employee_shift (
--  shift_id INT AUTO_INCREMENT  PRIMARY KEY,
--  emp_id int,
--  shift_date DATE NOT NULL,
--  shift_clockIn VARCHAR(30) NULL,
--  shift_clockOut VARCHAR(250) NULL,
--  FOREIGN KEY (emp_id) REFERENCES valentines_seasonal_help(emp_id)
-- );

--insert into valentines_seasonal_help
--(emp_name, emp_phone, emp_address, emp_email)
--values
--('Braden Borman', '5738261903', '3601 West Broadway Apt 21102 Columbia Mo', 'bradenborman00@gmail.com'),
--('Carter Atkinson', '3216549877', '', ''),
--('Sarah Smith', '5738268879', '7452 North Sreet Rd Columbia Mo', 'sarahhhhsimth@aol.com'),
--('Michelle Knox', '5769843156', '69 South Tarkiln Hill Ave. Depew, NY 14043', 'man@texasaol.com'),
--('Dominic Hamilton', '7892345611', '7807 Sycamore Drive Trenton, NJ 08610', 'dmaha.li.733@reicono.gq'),
--('Luke Johnston', '6549873215', '563 East Brook Ave. Gaithersburg, MD 20877', 'bvishnu.radhakrie@chatur21bate.com'),
--('Alber Wilson', '5735551733', '2523 Chappel Hill Apt 23452 Columbia Mo', 'wilson234@gmail.com');

--insert into employee_shift
--(emp_id, shift_date, shift_clockIn, shift_clockOut)
--values
--(2, NOW(), '9:00 AM', '4:20 PM'),
--(1, NOW(), '7:20 AM', '12:20 PM');
--insert into employee_shift
--(emp_id, shift_date, shift_clockIn)
--values (2, NOW(), '10:15 AM');

--insert into employee_shift
--(emp_id, shift_date, shift_clockIn)
--values (2, NOW(), '11:15 PM');


--insert into employee_shift
--(emp_id, shift_date)
--values (1, NOW());