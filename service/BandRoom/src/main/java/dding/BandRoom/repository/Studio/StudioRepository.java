package dding.BandRoom.repository.Studio;


import dding.BandRoom.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface StudioRepository extends JpaRepository<Studio, String> {
    boolean existsReservationDuring(String id, LocalDate date, LocalTime startTime, LocalTime endTime);
}
