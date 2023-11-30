package ar.com.codoacodo.repository;

import ar.com.codoacodo.entity.Orador;

public class MainOradorRepository {

	public static void main(String[] args) {
		//interface i  = new ClaseQueImplementa()..
		
		OradorRepository repository = new MySQLOradorRepository();
		
		Orador orador = repository.getById(1L);
		
		System.out.println(orador);
	}
}
