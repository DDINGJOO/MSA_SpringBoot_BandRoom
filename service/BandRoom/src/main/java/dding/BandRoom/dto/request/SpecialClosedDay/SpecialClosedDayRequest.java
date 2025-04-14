package dding.BandRoom.dto.request.SpecialClosedDay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialClosedDayRequest {
    private String date; // "yyyy-MM-dd"
    private String reason;
}
