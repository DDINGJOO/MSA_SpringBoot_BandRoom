package dding.BandRoom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BandRoomApplication {
    public static void  main(String[] args) {
        SpringApplication.run(BandRoomApplication.class, args);
    }
}

