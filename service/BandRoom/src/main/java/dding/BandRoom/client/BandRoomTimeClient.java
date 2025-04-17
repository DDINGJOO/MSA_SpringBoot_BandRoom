package dding.BandRoom.client;


import dding.BandRoom.dto.response.Schedule.BandRoomScheduleRuleResponse;
import dding.BandRoom.dto.response.SpecialClosedDay.SpecialClosedDayResponse;
import lombok.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@FeignClient(name = "time-manager", url = "${client.TimemanagerUrl}") // 너희 환경에 맞춰 수정
public interface BandRoomTimeClient {

    @GetMapping("/api/time-manager/band-rooms/{bandRoomId}/schedule-rules")
    List<BandRoomScheduleRuleResponse> getScheduleRules(@PathVariable String bandRoomId);

    @GetMapping("/api/time-manager/band-rooms/{bandRoomId}/closed-days")
    List<SpecialClosedDayResponse> getSpecialClosedDays(@PathVariable String bandRoomId);

    @GetMapping("/api/time-manager/band-rooms/{bandRoomId}/availability")
    Boolean isBandRoomAvailable(
            @PathVariable String bandRoomId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    );
}

