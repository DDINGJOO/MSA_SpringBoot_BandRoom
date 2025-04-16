package dding.reservation.service;


import dding.reservation.dto.request.ProductCreateRequest;
import dding.reservation.dto.request.ProductUpdateRequest;
import dding.reservation.dto.response.ProductResponse;
import dding.reservation.entity.Product;
import dding.reservation.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    @Transactional
    public ProductResponse createProduct(String BandRoomId, ProductCreateRequest req)
    {
        Product product = Product.builder()
                .productId(req.getId())
                .name(req.getName())
                .bandRoomId(BandRoomId)
                .price(req.getPrice())
                .description(req.getDescription())
                .thumbnailUrl(req.getThumbnailUrl())
                .build();

        productRepository.save(product);

        return new ProductResponse(product.getProductId(), product.getName(), product.getPrice(), product.getDescription(), product.getThumbnailUrl());

    }

    public ProductResponse getProductByBandRoomId(String ProductId)
    {
        Product product = productRepository.findById(ProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductResponse(product.getProductId(),product.getName(), product.getPrice(), product.getDescription(), product.getThumbnailUrl());
    }

    public List<ProductResponse> getAllProductsByBandRoomId(String BandRoomId)
    {
        List<Product> products = productRepository.findALLByBandRoomId(BandRoomId);
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product : products)
        {
            productResponses.add(new ProductResponse(product.getProductId(),product.getName(),product.getPrice(), product.getDescription(), product.getThumbnailUrl()));
        }
        return productResponses;
    }
    @Transactional
    public void deleteProduct(String ProductId)
    {
        productRepository.deleteById(ProductId);
    }

    @Transactional
    public void deleteAll(String BandRoomId)
    {
        productRepository.deleteAllByBandRoomId(BandRoomId);
    }


    public ProductResponse updateProduct(String productId ,ProductUpdateRequest req) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setThumbnailUrl(req.getThumbnailUrl());

        return new ProductResponse(product.getProductId(),product.getName(), product.getPrice(), product.getDescription(), product.getThumbnailUrl());
    }
}
