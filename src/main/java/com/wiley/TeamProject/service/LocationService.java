package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Location;
import java.util.List;

public interface LocationService {
    Location addLocation(Location l);
    List<Location> getAll();
    void deleteLocation(Long id);

}
