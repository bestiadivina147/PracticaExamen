package edu.badpals;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@QuarkusTest
public class RepoTest {

    @PersistenceContext
	EntityManager em;

	// @Inject
	// Repositorio repo;

	// @Inject
    // ServiceItem servicio;

    /**
	 * Tests sobre los mappings
	 * 
	 * Observa el esquema de la base de datos 
	 * en el fichero:
	 * src/main/resources/schema.sql
	 */
	
	/**
     * Completa la definicion y el mapping
     * de la clase MagicalItem a la tabla t_items.
     * El id de esta clase ha de seguir una estrategia Identity
     */


	@Test
	public void test_mapping_MagicalItem() {
		MagicalItem elixir = em.find(MagicalItem.class, 2L);
		Assertions.assertThat(elixir).isNotNull();
		Assertions.assertThat(elixir.toString()).containsIgnoringCase("Elixir of the Mongoose"); // item_name
		Assertions.assertThat(elixir.toString()).contains("7"); // item_quality
		Assertions.assertThat(elixir.toString()).contains("MagicalItem"); // item_type
		Assertions.assertThat(elixir.getId()).isEqualTo(2L);
	}
    
	/** 
	 * Completa la definicion y el mapping
	 * de la clase Wizards a la tabla t_wizards
	 * 
	 * Los Wizards tienen una propiedad "person" de
	 * un tipo enumerado con los valores:
	 * MUGGLE, SQUIB, NOMAJ, MUDBLOOD
	 * 
	 * La anotacion jakarta.persistence para mapear 
	 * a una tabla una propiedad Enum es
	 * 	@Enumerated(EnumType.STRING)
	 */
	
}
