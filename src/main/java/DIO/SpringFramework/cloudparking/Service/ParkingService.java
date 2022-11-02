package DIO.SpringFramework.cloudparking.Service;

import DIO.SpringFramework.cloudparking.Model.Parking;
import DIO.SpringFramework.cloudparking.exception.ParkingNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();
    private static BillService billService;

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking(id1, "WAS-1111", "SP", "GOL", "AMARELO");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);
    }

    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }
        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking byId = findById(id);
        byId.setColor(parkingCreate.getColor());
        byId.setState(parkingCreate.getState());
        byId.setModel(parkingCreate.getModel());
        byId.setLicense(parkingCreate.getLicense());
        parkingMap.replace(id, byId);
        return byId;
    }

    public Parking exit(String id) {
        Parking byId = findById(id);
        byId.setExitDate(LocalDateTime.now());
       LocalDateTime parkingEntry = byId.getEntryDate();
       LocalDateTime parkingExit = byId.getExitDate();
       double bill = billService.valueBill(parkingEntry,parkingExit);
       byId.setBill(bill);
       parkingMap.replace(id,byId);
        return byId;
    }
}

