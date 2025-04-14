package dding.BandRoom.repository.BandRoom;


import dding.BandRoom.entity.SpecialClosedDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialClosedDayRepository  extends JpaRepository<SpecialClosedDay,String> {
    List<SpecialClosedDay> findByBandRoomId(String bandRoomId);
}
