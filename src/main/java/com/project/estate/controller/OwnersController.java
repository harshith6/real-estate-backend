package com.project.estate.controller;

import com.project.estate.model.Owner;
import com.project.estate.service.OwnersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/owners")
public class OwnersController {
    private final OwnersService ownersService;

    @Autowired
    public OwnersController(OwnersService ownersService) {
        this.ownersService = ownersService;
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        log.info("Fetching all owners");
        return ownersService.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        log.info("Fetching owner with id: {}", id);
        Optional<Owner> owner = ownersService.getOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        log.info("Creating owner: {}", owner);
        Owner created = ownersService.createOwner(owner);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        log.info("Updating owner with id: {}", id);
        Optional<Owner> updated = ownersService.updateOwner(id, ownerDetails);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        log.info("Deleting owner with id: {}", id);
        boolean deleted = ownersService.deleteOwner(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
