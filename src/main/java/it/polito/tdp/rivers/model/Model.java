package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao;
	private List<Float> capacitaGiorn;
	private List<LocalDate> dateirrMin;
	
	public Model() {
		dao = new RiversDAO();
	}
	
	public List<River> getAllivers() {

		return dao.getAllRivers();
	}

	public Stampa getStampa(River r) {
		
		Stampa s = this.dao.getStampa(r);
		return s;
	}
	
	public void simula(float k, Stampa river) {
		List<Flow> flow = dao.getAllFlowByRiver(river.getRiver());
		Simulator sim = new Simulator(k, river, flow);
		sim.init();
		sim.run();
		
		dateirrMin = sim.getDateErogMin();
		capacitaGiorn = sim.getC();
	}

	public float getCapacitaMedia() {
		
    	float somma = 0;
    	
    	for(float n : this.capacitaGiorn)
    		somma +=n;
    	
		return somma/capacitaGiorn.size();
	}

	public int getDateirrMin() {
		return dateirrMin.size();
	}
	
	

}
