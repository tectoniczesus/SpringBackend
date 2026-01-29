INSERT INTO patient (name, gender,birth_date,email,blood_grp) VALUES
('Aarav Sharma', 'Male', '1999-03-15', 'aarav.sharma@gmail.com', 'O+'),
('Priya Verma', 'Female', '2001-07-22', 'priya.verma@gmail.com', 'A+'),
('Rohan Mehta', 'Male', '1998-11-05', 'rohan.mehta@gmail.com', 'B+'),
('Sneha Iyer', 'Female', '2000-01-18', 'sneha.iyer@gmail.com', 'AB+'),
('Kunal Singh', 'Male', '1997-09-30', 'kunal.singh@gmail.com', 'O-'),
('Ananya Gupta', 'Female', '2002-05-12', 'ananya.gupta@gmail.com', 'A-');

INSERT INTO doctor (email, name, specialization) VALUES
('rajesh.kumar@hospital.com', 'Dr. Rajesh Kumar', 'Cardiologist'),

('ananya.sharma@hospital.com', 'Dr. Ananya Sharma', 'Dermatologist'),

('vikram.singh@hospital.com', 'Dr. Vikram Singh', 'Orthopedic'),

('priya.mehta@hospital.com', 'Dr. Priya Mehta', 'Gynecologist'),

('aman.verma@hospital.com', 'Dr. Aman Verma', 'Neurologist'),

('sneha.iyer@hospital.com', 'Dr. Sneha Iyer', 'Pediatrician'),

('rohit.malhotra@hospital.com', 'Dr. Rohit Malhotra', 'ENT Specialist'),

('kavita.nair@hospital.com', 'Dr. Kavita Nair', 'Psychiatrist');

INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
  ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
  ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
  ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
  ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
  ('2025-07-05 16:15:00', 'Consultation', 1, 4),
  ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);