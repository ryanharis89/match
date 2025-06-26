package com.ryanharis.app.match.e2e;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.ryanharis.app.match.common.models.MatchDto;
import com.ryanharis.app.match.persistence.dao.MatchDao;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("End to end test using RestAssured for MatchController API")
@Disabled(value = "Needs manual db startup before execution. Please run src/main/docker/docker-compose-db.yml")
@Transactional
public class MatchControllerE2ETest {
  private static final String REQUEST_JSON_PATH = "src/test/resources/create-match-request.json";

  @Autowired
  private MatchDao matchDao;

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  @DisplayName("Test that create match API from JSON request is executed successfully")
  void testCreateMatchApi() throws Exception {
    String requestBody = Files.readString(Paths.get(REQUEST_JSON_PATH));

    MatchDto response = given()
        .port(port)
        .header("Accept", "application/json")
        .contentType(ContentType.JSON)
        .body(requestBody)
        .when()
        .post("/api/v1/matches")
        .then()
        .log().all()
        .statusCode(HttpStatus.CREATED.value())
        .contentType(ContentType.JSON)
        .extract()
        .as(MatchDto.class);

    System.out.println("Created match : " + response.toString());
    assertNotNull(response);
    Long matchId = response.getId();
    assertNotNull(matchId);
    assertNotNull(matchDao.getById(matchId));
    matchDao.delete(matchId);
    System.out.println("Match deleted successfully from db");
  }
}
