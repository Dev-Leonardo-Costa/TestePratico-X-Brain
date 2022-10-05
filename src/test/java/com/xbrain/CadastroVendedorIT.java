package com.xbrain;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroVendedorIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void  setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/vendedores";
    }
    @Test
    public void deveRetornarStatus200_QuandoConsultarVendedores(){
            RestAssured.given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.SC_OK);        
    }

    @Test
    public void deveConter5Vendedores_QuandoConsultarVendedores(){
                RestAssured.given()
                .when()
                    .get()
                .then()
                    .body("vendedorId", Matchers.hasSize(1))
                    .body("vendedorNome", Matchers.hasItems("Leonardo","Chico"));
    }

//    @Test
//    public void deveRetornarStatus201_QuandoCadastrarVendedor(){
//                RestAssured.given()
//                        .body("{\"vendedorNome\": \"Leonardo\" }")
//                        .contentType(ContentType.JSON)
//                        .accept(ContentType.JSON)
//                        .when()
//                        .post()
//                        .then()
//                        .statusCode(HttpStatus.SC_CREATED);
//    }
}