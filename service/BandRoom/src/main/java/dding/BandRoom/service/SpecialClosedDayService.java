package dding.BandRoom.service;

import dding.BandRoom.dto.request.SpecialClosedDay.SpecialClosedDayRequest;
import dding.BandRoom.dto.response.SpecialClosedDay.SpecialClosedDayResponse;
import dding.BandRoom.entity.BandRoom;
import dding.BandRoom.entity.SpecialClosedDay;
import dding.BandRoom.repository.BandRoom.BandRoomRepository;
import dding.BandRoom.repository.BandRoom.SpecialClosedDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialClosedDayService {

    private final BandRoomRepository bandRoomRepository;
    private final SpecialClosedDayRepository closedDayRepository;

    public String create(String bandRoomId, SpecialClosedDayRequest request) {
        BandRoom bandRoom = bandRoomRepository.findById(bandRoomId)
                .orElseThrow(() -> new RuntimeException("BandRoom not found"));

        SpecialClosedDay closedDay = SpecialClosedDay.builder()
                .id(UUID.randomUUID().toString())
                .bandRoom(bandRoom)
                .date(LocalDate.parse(request.getDate()))
                .reason(request.getReason())
                .build();

        closedDayRepository.save(closedDay);
        return closedDay.getId();
    }

    public List<SpecialClosedDayResponse> getByBandRoom(String bandRoomId) {
        return closedDayRepository.findByBandRoomId(bandRoomId).stream()
                .map(cd -> SpecialClosedDayResponse.builder()
                        .id(cd.getId())
                        .date(cd.getDate().toString())
                        .reason(cd.getReason())
                        .build())
                .toList();
    }

    public void delete(String id) {
        closedDayRepository.deleteById(id);
    }
}
