package DIO.SpringFramework.cloudparking.Controller;

import DIO.SpringFramework.cloudparking.Controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("user","Dio@123456")
                .when()
                .get("/parking")
                .then()
                .statusCode(200);

    }
    @Test
    void whenCreateThenCheckIsCreated() {

        var createdDTO = new ParkingCreateDTO();
        createdDTO.setColor("Amarelo");
        createdDTO.setLicense("MRT-5555");
        createdDTO.setModel("BRASILIA");
        createdDTO.setState("SP");
        RestAssured.given()
                .auth()
                .basic("user","Dio@123456")
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createdDTO)
                .post("/parking")
                .then()
                .statusCode(201);

    }

}