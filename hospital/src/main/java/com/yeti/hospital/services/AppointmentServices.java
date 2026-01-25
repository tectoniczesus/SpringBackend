package com.yeti.hospital.services;

import com.yeti.hospital.entity.Appointment;
import com.yeti.hospital.entity.Doctor;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.AppointmentRepository;
import com.yeti.hospital.repository.DoctorRepository;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServices {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final DoctorRepository doctorRepository;
    @Autowired
    private final PatientRepo patientRepo;
  @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long DoctorID, Long PatientID){
      Doctor doctor =  doctorRepository.findById(DoctorID).orElseThrow();
      Patient patient = patientRepo.findById(PatientID).orElseThrow();

      if (appointment.getId()!=null) throw new IllegalArgumentException("Appointment should not have an id");

      appointment.setDoctor(doctor);
      appointment.setPatient(patient);

      patient.getAppointmentList().add(appointment);
      appointmentRepository.save(appointment);

      return appointment;
  }
}
