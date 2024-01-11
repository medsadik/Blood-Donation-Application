package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Center;

import java.util.List;

public interface CenterManagementService {

    List<Center> getAllCenters();
    Center getCenter(Long id);
    Center save(Center center);
    Center update(Center center, Long centerId);
    void deleteById(Long id);

}
