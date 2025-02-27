package dding.bandroom.service;

import dding.bandroom.dto.BandRoomRequestDto;
import dding.bandroom.entity.BandRoom;
import dding.bandroom.repository.BandRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BandRoomService {

    private final BandRoomRepository bandRoomRepository;

    @Transactional
    public BandRoom createBandRoom(BandRoomRequestDto dto) {
        BandRoom bandRoom = BandRoom.create(dto.getBandRoomId(), dto.getAdminId(),
                dto.getBandRoomName(), dto.getBandNumber(), dto.getRoomEmail(), dto.getRoomEmailDomain());
        return bandRoomRepository.save(bandRoom);
    }
}
