package com.AvailHive1.AvailHive1.services.reclamation;

import com.AvailHive1.AvailHive1.dto.ReclamationDTO;
import com.AvailHive1.AvailHive1.entity.Reclamation;

import java.util.List;
import java.util.Optional;

public interface ReclamationService {
    boolean saveReclamation(ReclamationDTO reclamationDTO);
    List<Reclamation> getAllReclamations();
    Optional<Reclamation> getReclamationById(Long id);
    void deleteReclamation(Long id);

}
