package com.yeti.hospital.services;

import com.yeti.hospital.dto.AppointmentResponseDTO;
import com.yeti.hospital.entity.Appointment;
import com.yeti.hospital.entity.Doctor;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.AppointmentRepository;
import com.yeti.hospital.repository.DoctorRepository;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServices {
  @Autowired
  private final AppointmentRepository appointmentRepository;
  @Autowired
  private final DoctorRepository doctorRepository;
  @Autowired
  private final PatientRepo patientRepo;


   private final ModelMapper modelMapper;
  @Transactional
  public Appointment createNewAppointment(Appointment appointment, Long DoctorID, Long PatientID) {
    Doctor doctor = doctorRepository.findById(DoctorID).orElseThrow();
    Patient patient = patientRepo.findById(PatientID).orElseThrow();

    if (appointment.getId() != null)
      throw new IllegalArgumentException("Appointment should not have an id");

    appointment.setDoctor(doctor);
    appointment.setPatient(patient);

    patient.getAppointmentList().add(appointment);
    appointmentRepository.save(appointment);

    return appointment;
  }

  @Transactional
  public Appointment reAssigneAppointment(Long appointmentID, Long doctorID) {
    Appointment appointment = appointmentRepository.findById(appointmentID)
        .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

    Doctor doctor = doctorRepository.findById(doctorID)
        .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

    appointment.setDoctor(doctor); // THIS IS ENOUGH
    doctor.getAppointments().add(appointment);
    return appointment;
  }

  public List<AppointmentResponseDTO> getDoctorsAppointment(Long doctorId){
     Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
   return doctor.getAppointments()
             .stream()
             .map(appointment -> modelMapper.map(appointment, AppointmentResponseDTO.class))
             .collect(Collectors.toList());
  }
}
