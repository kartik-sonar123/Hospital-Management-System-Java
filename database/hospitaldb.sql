CREATE DATABASE hospital;
USE hospital;
CREATE TABLE patients
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE doctors
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL
);

INSERT INTO doctors(id,name,specialization)
VALUES
	(1,'Dr.Altaf_patel','Cardiology'),
    (2,'Dr.Lalit_patil','Neurology'),
    (3,'Dr.Jain','Orthopedics'),
    (4,'Dr.Khushi','Dermatology');
    
CREATE TABLE appointments
(
	id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id)   ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)   ON DELETE CASCADE
)
    
SELECT * FROM PATIENTS;
SELECT * FROM DOCTORS;
SELECT * FROM APPOINTMENTS;