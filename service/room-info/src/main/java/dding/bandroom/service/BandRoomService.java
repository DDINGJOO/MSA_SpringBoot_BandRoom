package dding.bandroom.service;

import dding.bandroom.dto.BandRoomRequestDto;
import dding.bandroom.dto.BandRoomResponse;
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

    public BandRoomResponse readBandRoom(Long bandId)
    {
        BandRoom bandRoom = bandRoomRepository.findById(bandId).orElseThrow();
        return  new BandRoomResponse(bandRoom.getBandRoomName(),bandRoom.getBandRoomNumber(),bandRoom.getAdminId()
                ,bandRoom.getBandRoomEmail(), bandRoom.getBandRoomEmailDomain());
    }

    @Transactional
    public void deleteBandRoom(Long bandId)
    {
        bandRoomRepository.deleteById(bandId);
    }



}
