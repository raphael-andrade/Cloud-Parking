package DIO.SpringFramework.cloudparking.Service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BillService {

    public double valueBill(LocalDateTime parkingEntry, LocalDateTime parkingExit) {
        Duration timeParking = Duration.between(parkingExit, parkingEntry);
        if (timeParking.get(ChronoUnit.HOURS) <= 12) {
            return (double) (timeParking.get(ChronoUnit.HOURS) * Price.HOUR.ordinal());
        }
        if (timeParking.get(ChronoUnit.HOURS) > 12) {
            return (double) (timeParking.get(ChronoUnit.HOURS) * Price.DAY.ordinal());
        }
        return 0;
    }
}


