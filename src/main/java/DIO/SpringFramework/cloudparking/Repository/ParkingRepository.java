package DIO.SpringFramework.cloudparking.Repository;

import DIO.SpringFramework.cloudparking.Model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,String> {
}
