package com.xbrain;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastroVendaIT {


    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_QuandoConsultarVendas(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            RestAssured.given()
                .basePath("/vendas")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.SC_OK);        
    }

}