package dding.BandRoom.dto.response.SpecialClosedDay;

import lombok.*;


@Getter
@Builder
public class SpecialClosedDayResponse {
    private String id;
    private String date;
    private String reason;
}
