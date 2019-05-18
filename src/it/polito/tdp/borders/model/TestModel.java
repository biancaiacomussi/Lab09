package it.polito.tdp.borders.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
	System.out.println("Creo il grafo relativo al 2000");
		model.creaGrafo(2000);
		
		System.out.println(model.getGrafo());
		System.out.println("Vertici: "+model.getGrafo().vertexSet().size());
		System.out.println("Archi: "+model.getGrafo().edgeSet().size());
		System.out.print(model.elencoVertici());
	
		
		
//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

		System.out.format("Numero componenti connesse: %d\n", model.componentiConnesse());
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));
		
		Country c = new Country(20, "CAN", "Canada");
		System.out.println(model.trovaVicini(c));
	//	System.out.println(model.trovaVicini1(c));
		System.out.println(model.trovaVicini3(c));
		
	}

}
