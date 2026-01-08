package lexicon.dao;

import lexicon.model.ParkingSpot;

import java.util.Collection;
import java.util.Optional;

public interface ParkingSpotDao {

    ParkingSpot persist(ParkingSpot parkingSpot);
    Optional<ParkingSpot> findBySpotNumber(Integer spotNumber);
    Collection<ParkingSpot> finaAll();
    Collection<ParkingSpot> findByAreaCode(Integer areaCode);
    void remove(Integer spotNumber);
}
