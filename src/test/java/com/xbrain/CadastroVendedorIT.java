package com.xbrain;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroVendedorIT {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_QuandoConsultarVendedores(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            RestAssured.given()
                .basePath("/vendedores")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.SC_OK);        
    }

    @Test
    public void deveConter5Vendedores_QuandoConsultarVendedores(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            RestAssured.given()
                .basePath("/vendedores")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("vendedorId", Matchers.hasSize(5))
                .body("vendedorNome", Matchers.hasItems("Leonardo","Chico"));   
    }

}