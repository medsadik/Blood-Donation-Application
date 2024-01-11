package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Center;
import com.blooddonation.appointementservice.repositories.CenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterManagementServiceImpl implements CenterManagementService{
    @Autowired
    private CenterRepository centerRepository;

    @Override
    public List<Center> getAllCenters(){
        List<Center> centers = new ArrayList<>();
        centerRepository.findAll().forEach(center -> centers.add(center));
        return centers;
    }

    @Override
    public Center getCenter(Long id){
        return centerRepository.findById(id).get();
    }

    @Override
    public Center save(Center center) {
        return centerRepository.save(center);
    }

    @Override
    public Center update(Center center, Long centerId) {
        Center centerEx = centerRepository.findById(centerId).get();
        centerEx.setNom(center.getNom());
        centerEx.setCapacity(center.getCapacity());
        centerEx.setH_ouverture(center.getH_ouverture());
        centerEx.setH_fermeture(center.getH_fermeture());
        return centerRepository.save(centerEx);
    }

    @Override
    public void deleteById(Long id) {
        centerRepository.deleteById(id);
    }
}
