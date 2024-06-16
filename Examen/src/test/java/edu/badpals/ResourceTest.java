package edu.badpals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.assertj.core.api.Assertions;

@QuarkusTest
class ResourceTest {
    
    @Inject
    Resources resources;


    /**
	 * Implementa una clase Resources que
	 * añada una API REST a nuestra app.
	 * Injecta el servicio en Resources.
     * 
     * Todas las peticiones http a la API REST
     * ha de pasar por el servicio antes de
     * llegar al repositorio.
     */

    @Test
    public void test_injeccion() {
        Assertions.assertThat(resources.service).isNotNull();
    }

}