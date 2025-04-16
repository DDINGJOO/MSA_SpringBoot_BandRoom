package dding.reservation.repository;


import dding.reservation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByBandRoomId(String bandRoomId);

    List<Product> findALLByBandRoomId(String bandRoomId);

    void deleteAllByBandRoomId(String bandRoomId);
}
