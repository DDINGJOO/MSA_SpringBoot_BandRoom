package dding.reservation.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductCreateRequest {
    String id;
    String name;
    Long price;
    String description;
    String thumbnailUrl;
}
