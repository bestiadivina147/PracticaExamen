package edu.badpals;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.badpals.domain.MagicalItem;
import edu.badpals.domain.Order;
import edu.badpals.domain.Wizard;
import edu.badpals.repositorio.Repositorio;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@QuarkusTest
public class RepoTest {

    @PersistenceContext
	EntityManager em;

	 @Inject
	 Repositorio repo;

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
	@Test
	public void test_mapping_wizard() {
		Wizard squib = em.find(Wizard.class, "Marius Black");
		Assertions.assertThat(squib).isNotNull();
		Assertions.assertThat(squib.toString()).contains("Marius Black");//name
		Assertions.assertThat(squib.toString()).contains("15"); //wizard_dexterity
		Assertions.assertThat(squib.toString()).contains("SQUIB");  //tipo enumerado
	}

	/**
	 * Completa la definicion y el mapping
	 * de la clase Order a la tabla t_orders
	 * El id de esta clase ha de seguir una estrategia
	 * Identity
	 */
	@Test
	public void test_mapping_order() {
		Order pedido = em.find(Order.class, 1L);
		Assertions.assertThat(pedido).isNotNull();
		Assertions.assertThat(pedido.toString()).contains("Marius Black"); //ord_wizard
		Assertions.assertThat(pedido.toString()).containsIgnoringCase("Elixir of the Mongoose"); //ord_item
	}

	
	/**
	 * Crea una clase llamada Repositorio
	 * e inyectala en los casos test
	 * (ha de ser un bean) 
	 * 
	 * Utiliza el código:
	 * @Inject
	 * Repositorio repo;
	 */
	@Test
	public void test_repositorio_existe() {
		Assertions.assertThat(repo).isNotNull();
	}
	/**
	 * Implementa el metodo loadWizard del repositorio
	 * que devuelve un Optional del mago/a con el nombre indicado
	 */
	
	 @Test
	 public void test_load_wizard() {
		 Assertions.assertThat(repo).isNotNull();
		 Wizard squib = repo.loadWizard("Hermione").get();
		 Assertions.assertThat(squib).isNotNull();
		 Assertions.assertThat(squib.toString()).contains("Hermione");
		 Assertions.assertThat(squib.toString()).contains("100");
		 Assertions.assertThat(squib.toString()).contains("MUDBLOOD");
 
		 // test no existe el mago
		 Assertions.assertThat(repo.loadWizard("Severus Snape")).isEmpty();
	 }
	 /**
	 * Implementa el metodo loadItem() del repositorio
	 * que devuelve un Optional del Item con el nombre indicado.
	 * 
	 * Ojo que el nombre del item no es la clave primaria.
	 * 
	 * El metodo devueve el primer item cuyo nombre
	 * coincida con el especificado.
	 */
	@Test
	public void test_load_item() {
		Assertions.assertThat(repo).isNotNull();
		MagicalItem item = repo.loadItem("Aged Brie").get();
		Assertions.assertThat(item).isNotNull();
		Assertions.assertThat(item.getName()).isEqualTo("Aged Brie");
		Assertions.assertThat(item.getQuality()).isEqualTo(10);

		// test no existe el item
		Assertions.assertThat(repo.loadItem("Varita de Sauco")).isEmpty();
	}
	/**
	 * Implementa el metodo loadItems() del repositorio
	 * que devuelve una lista de Items 
	 * con el nombre indicado
	 * 
	 * Ojo que el nombre del item no es la clave primaria.
	 */
	@Test
	public void test_load_items() {
		Assertions.assertThat(repo).isNotNull();

		List<MagicalItem> items = repo.loadItems("Aged Brie");
		Assertions.assertThat(items).isNotEmpty().hasSize(2);
		Assertions.assertThat(items.get(0)).hasFieldOrPropertyWithValue("name", "Aged Brie");
		Assertions.assertThat(items.get(1)).hasFieldOrPropertyWithValue("quality", 0);

		// test no existe el item
		Assertions.assertThat(repo.loadItems("Varita de Sauco")).isEmpty();
	}
	/**
	 * Implementa el metodo loadItem(name, quality, type) 
	 * del repositorio que devuelve un Optional del Item
	 * con el nombre indicado.
	 *  
	 * El item devuelto ha de tener el mismo 
     * name, quality y type que el de la peticion
     * y no cualquier otro item de la base de datos
     * que tenga sólo el mismo nombre.
	 */
	@Test
	public void test_load_item_equal() {
		Assertions.assertThat(repo).isNotNull();
		MagicalItem brie = new MagicalItem("Aged Brie", 0, "MagicalItem");
		MagicalItem item = repo.loadItem(brie).get();
		Assertions.assertThat(item).isNotNull();
		Assertions.assertThat(item.getName()).isEqualTo("Aged Brie");
		Assertions.assertThat(item.getQuality()).isZero();

		// test no existe el item
		brie = new MagicalItem("Aged Brie", 1000, "MagicalItem");
		Assertions.assertThat(repo.loadItem(brie)).isEmpty();
	}
	/**
	 * Implementa el metodo placeOrder(wizard, item) del repositorio 
	 * que genera un pedido de un item para un mago determinado.
	 * El pedido se guarda en la base de datos.
	 *  
	 * Los magos/as mudblood NO pueden comprar un item.
	 */

	@Test
	@Transactional
	public void test_pedido() {
 
		// Hermione no puede comprar items
		Assertions.assertThat(repo).isNotNull();
		Optional<Order> orden = repo.placeOrder("Hermione", "Elixir of the Mongoose");
		Assertions.assertThat(orden).isEmpty();
 
		// Marius Black compra un item
		orden = repo.placeOrder("Marius Black", "Elixir of the Mongoose");
		Assertions.assertThat(orden).isNotEmpty();
 
		Assertions.assertThat(orden.get().getId()).isNotZero();
		Assertions.assertThat(orden.get().getWizard().getName()).isEqualTo("Marius Black");
		Assertions.assertThat(orden.get().getItem().getName()).isEqualTo("Elixir of the Mongoose");
  
		// query para obtener todos los pedidos de Marius
		// de manera agnostica al patron DAO /Active record
		TypedQuery<Order> query = em.createQuery("select orden from Order orden join orden.wizard wizard where wizard.name = 'Marius Black'", Order.class);
		List<Order> pedidos = query.getResultList();
		  
		Assertions.assertThat(pedidos).isNotNull().hasSize(3);
		Assertions.assertThat(pedidos.get(2).getWizard().getName()).isEqualTo("Marius Black");
		Assertions.assertThat(pedidos.get(2).getItem().getName()).isEqualToIgnoringCase("Elixir of the Mongoose");
	}
 
}
