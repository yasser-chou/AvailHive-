package com.AvailHive1.AvailHive1.services.reclamation;

import com.AvailHive1.AvailHive1.dto.ReclamationDTO;
import com.AvailHive1.AvailHive1.entity.Employer;
import com.AvailHive1.AvailHive1.entity.Reclamation;
import com.AvailHive1.AvailHive1.entity.Reservation;
import com.AvailHive1.AvailHive1.repository.EmployerRepository;
import com.AvailHive1.AvailHive1.repository.ReclamationRepository;
import com.AvailHive1.AvailHive1.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public boolean saveReclamation(ReclamationDTO reclamationDTO) {
        Optional<Employer> optionalEmployer = employerRepository.findById(reclamationDTO.getEmployerId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reclamationDTO.getReservationId());

        if (optionalEmployer.isPresent() && optionalReservation.isPresent()) {
            Reclamation reclamation = new Reclamation();

            reclamation.setDescription(reclamationDTO.getDescription());
            reclamation.setReclamationDate(new Date());

            reclamation.setEmployer(optionalEmployer.get());
            reclamation.setReservation(optionalReservation.get());

            reclamationRepository.save(reclamation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }


}
