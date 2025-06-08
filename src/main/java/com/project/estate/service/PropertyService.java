package com.project.estate.service;

import com.project.estate.model.Property;
import com.project.estate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> updateProperty(Long id, Property propertyDetails) {
        return propertyRepository.findById(id).map(property -> {
            property.setTitle(propertyDetails.getTitle());
            property.setDescription(propertyDetails.getDescription());
            property.setPrice(propertyDetails.getPrice());
            property.setLocation(propertyDetails.getLocation());
            property.setImages(propertyDetails.getImages());
            return propertyRepository.save(property);
        });
    }

    public boolean deleteProperty(Long id) {
        return propertyRepository.findById(id).map(property -> {
            propertyRepository.delete(property);
            return true;
        }).orElse(false);
    }
}
