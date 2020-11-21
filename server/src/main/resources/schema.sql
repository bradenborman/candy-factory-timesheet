DROP TABLE IF EXISTS valentines_seasonal_help;

CREATE TABLE valentines_seasonal_help (
  emp_id INT AUTO_INCREMENT  PRIMARY KEY,
  emp_name VARCHAR(250) NOT NULL,
  emp_phone VARCHAR(30) NULL,
  emp_address VARCHAR(250) NULL,
  emp_email VARCHAR(250) NULL
);

INSERT INTO valentines_seasonal_help
(emp_name, emp_phone, emp_address, emp_email)
VALUES
('Braden Borman', '5738261903', '3601 West Broadway Apt 21102 Columbia Mo', 'bradenborman00@gmail.com'),
('Alber Wilson', '5735551733', '2523 Chappel Hill Apt 23452 Columbia Mo', 'wilson234@gmail.com');