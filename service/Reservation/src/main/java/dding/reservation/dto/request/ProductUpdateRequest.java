package dding.reservation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    String name;
    Long price;
    String description;
    String thumbnailUrl;

}
