package com.AvailHive1.AvailHive1.Controller;

import com.AvailHive1.AvailHive1.dto.ReclamationDTO;
import com.AvailHive1.AvailHive1.entity.Reclamation;
import com.AvailHive1.AvailHive1.services.reclamation.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/reclamations")
@RestController
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping
    public ResponseEntity<?> saveReclamation(@RequestBody ReclamationDTO reclamationDTO) {
        boolean success = reclamationService.saveReclamation(reclamationDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public List<Reclamation> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable Long id) {
        Optional<Reclamation> reclamation = reclamationService.getReclamationById(id);
        return reclamation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }


}
