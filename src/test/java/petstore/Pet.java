package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


// 3 - Classe
public class Pet {
//3.1 - Atributos
    String uri ="https://petstore.swagger.io/v2/pet"; //endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post



    @Test //Identifica o médoto ou função como um teste para o TesteNG
    public void incluirPet() throws IOException {
String jsonBody = lerJson("db/pet1.json");

   // Sintaxe Gherkin
   // Dado - Quanto - Então
   // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas "text/xml"
                .log().all()
                .body(jsonBody)
        .when() // Quanto
                .post(uri)
        .then() // Então
                .log().all()
                .statusCode(200)
                .body("name", is("Ventania"))
                .body("status", is("available"))
                .body("category.name", is ("dog"))
                .body("tags.name", contains("sta"))
        ;


    }



}
