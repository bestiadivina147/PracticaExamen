package edu.badpals.servicice;

import edu.badpals.repositorio.Repositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServiceItem {
    @Inject
	 Repositorio repo;
     
}
