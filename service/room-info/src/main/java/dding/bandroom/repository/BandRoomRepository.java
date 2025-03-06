package dding.bandroom.repository;

import dding.bandroom.entity.BandRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRoomRepository extends JpaRepository<BandRoom, Long> {

}
