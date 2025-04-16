package dding.BandRoom.repository.BandRoom;


import dding.BandRoom.entity.BandRoomScheduleRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRoomScheduleRuleRepository  extends JpaRepository<BandRoomScheduleRule, String> {
    List<BandRoomScheduleRule> findByBandRoomId(String bandRoomId);

    void deleteAllByBandRoomId(String bandRoomId);

}
