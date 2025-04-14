package dding.BandRoom.repository.BandRoom;


import dding.BandRoom.entity.BandRoom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRoomRepository extends JpaRepository<BandRoom, String>, JpaSpecificationExecutor<BandRoom> {
}
