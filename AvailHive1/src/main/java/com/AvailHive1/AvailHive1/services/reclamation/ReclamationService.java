package com.AvailHive1.AvailHive1.services.reclamation;

import com.AvailHive1.AvailHive1.entity.Reclamation;
import com.AvailHive1.AvailHive1.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ReclamationService {
    Reclamation saveReclamation(Reclamation reclamation);
    List<Reclamation> getAllReclamations();
    Optional<Reclamation> getReclamationById(Long id);
    void deleteReclamation(Long id);






}
