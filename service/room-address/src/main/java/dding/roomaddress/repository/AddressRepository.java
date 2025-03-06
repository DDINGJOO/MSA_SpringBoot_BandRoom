package dding.roomaddress.repository;

import dding.roomaddress.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


    @Query(value = "SELECT * FROM room_address WHERE district = :district AND town = :town", nativeQuery = true)
    List<Address> findByDistrictAndTown(@Param("district") String district, @Param("town") String town);

    @Query(value = "SELECT * FROM room_address WHERE city = :city", nativeQuery = true)
    List<Address> findByCity(@Param("city") String city);

    @Query(value = "SELECT * FROM room_address WHERE city = :city AND district = :district", nativeQuery = true)
    List<Address> findByCityAndDistrict(@Param("city") String city, @Param("district") String district);

    @Query(value = "SELECT * FROM room_address WHERE city = :city AND district = :district AND town = :town", nativeQuery = true)
    List<Address> findByCityDistrictAndTown(@Param("city") String city, @Param("district") String district, @Param("town") String town);




}

