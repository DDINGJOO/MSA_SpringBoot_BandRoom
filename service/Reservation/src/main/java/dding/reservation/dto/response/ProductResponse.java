package dding.reservation.dto.response;


import dding.reservation.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {

    String productId;
    String name;
    Long price;
    String description;
    String thumbnailUrl;

    public static ProductResponse toDto(Product product)
    {

        return  new ProductResponse(
          product.getProductId(), product.getName(), product.getPrice(), product.getDescription(), product.getThumbnailUrl()
        );

    }
}
