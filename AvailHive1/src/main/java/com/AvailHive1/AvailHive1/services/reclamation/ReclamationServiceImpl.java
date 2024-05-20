package com.AvailHive1.AvailHive1.services.reclamation;

import com.AvailHive1.AvailHive1.entity.Reclamation;
import com.AvailHive1.AvailHive1.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationServiceImpl implements ReclamationService{

    @Autowired
    private ReclamationRepository reclamationRepository;


    public Reclamation saveReclamation(Reclamation reclamation){
        return reclamationRepository.save(reclamation);
    }

    public List<Reclamation> getAllReclamations(){
        return reclamationRepository.findAll();
    }

    public Optional<Reclamation> getReclamationById(Long id) {
        return reclamationRepository.findById(id);
    }

    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }



}
