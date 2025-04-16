package dding.BandRoom.dto.response.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationPriceResponse {
    private int totalPrice;
    private List<Integer> hourlyPrices;


}

