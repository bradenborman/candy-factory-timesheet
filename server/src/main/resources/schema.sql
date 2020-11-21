DROP TABLE IF EXISTS valentines_seasonal_help;
DROP TABLE IF EXISTS employee_shift;
--https://www.w3schools.com/sql/sql_datatypes.asp


CREATE TABLE valentines_seasonal_help (
  emp_id INT AUTO_INCREMENT  PRIMARY KEY,
  emp_name VARCHAR(250) NOT NULL,
  emp_phone VARCHAR(30) NULL,
  emp_address VARCHAR(250) NULL,
  emp_email VARCHAR(250) NULL
);


CREATE TABLE employee_shift (
  shift_id INT AUTO_INCREMENT  PRIMARY KEY,
  emp_id int,
  shift_date DATE NOT NULL,
  shift_clockIn VARCHAR(30) NULL,
  shift_clockOut VARCHAR(250) NULL,
  FOREIGN KEY (emp_id) REFERENCES valentines_seasonal_help(emp_id)
 );

INSERT INTO valentines_seasonal_help
(emp_name, emp_phone, emp_address, emp_email)
VALUES
('Braden Borman', '5738261903', '3601 West Broadway Apt 21102 Columbia Mo', 'bradenborman00@gmail.com'),
('Alber Wilson', '5735551733', '2523 Chappel Hill Apt 23452 Columbia Mo', 'wilson234@gmail.com');


INSERT INTO employee_shift
(emp_id, shift_date, shift_clockIn)
VALUES
(1, NOW(), '11:15 PM');