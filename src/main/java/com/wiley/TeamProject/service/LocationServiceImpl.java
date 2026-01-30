package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Location;
import com.wiley.TeamProject.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repo;

    @Override
    public Location addLocation(Location l) {
        return repo.save(l);
    }

    @Override
    public List<Location> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteLocation(Long id) {
        repo.deleteById(id);
    }

}
