package com.project.estate.service;

import com.project.estate.model.Owner;
import com.project.estate.repository.OwnersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OwnersService {
    private final OwnersRepository ownersRepository;

    @Autowired
    public OwnersService(OwnersRepository ownersRepository) {
        this.ownersRepository = ownersRepository;
    }

    public List<Owner> getAllOwners() {
        log.info("Getting all owners from repository");
        return ownersRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        log.info("Getting owner by id: {}", id);
        return ownersRepository.findById(id);
    }

    public Owner createOwner(Owner owner) {
        log.info("Saving new owner: {}", owner);
        return ownersRepository.save(owner);
    }

    public Optional<Owner> updateOwner(Long id, Owner ownerDetails) {
        log.info("Updating owner with id: {}", id);
        return ownersRepository.findById(id).map(owner -> {
            owner.setName(ownerDetails.getName());
            owner.setLocation(ownerDetails.getLocation());
            owner.setPrice(ownerDetails.getPrice());
            owner.setMeasurment(ownerDetails.getMeasurment());
            owner.setFace(ownerDetails.getFace());
            owner.setContact(ownerDetails.getContact());
            log.info("Owner updated: {}", owner);
            return ownersRepository.save(owner);
        });
    }

    public boolean deleteOwner(Long id) {
        log.info("Deleting owner with id: {}", id);
        return ownersRepository.findById(id).map(owner -> {
            ownersRepository.delete(owner);
            log.info("Owner deleted: {}", owner);
            return true;
        }).orElse(false);
    }
}
