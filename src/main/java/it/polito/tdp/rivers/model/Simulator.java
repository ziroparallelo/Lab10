package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {

	//Parametri
	
//	INUPT
	private float k;
	private Stampa river;
	private List<Flow> flow; //Contiene tutti i rilevamenti di quel fiume
//	OUTPUT
	private List<Float> C;
	private List<LocalDate> dateErogMin;
	
	//Parametri sistema
	private float Q; // max capienza
	private float F_out_min;
	private float F_out_max;
	

	//Coda
	
	PriorityQueue<Event> queue;
	
	//Metodi
	
	//int, run, costruttore, riempi coda, crea eventi
	public Simulator(float k, Stampa river, List<Flow> flow) {
		this.k = k;
		this.river = river;
		this.flow = flow;
		
		this.Q = this.k * this.river.getFlussoMedio()*(60*60*24) * 30;
		this.F_out_min = (float) (river.getFlussoMedio() *(60*60*24)* 0.8);
		this.F_out_max = 10 * F_out_min;
	}
	
	public void init() {
		
		this.queue = new PriorityQueue<Event>();
		this.dateErogMin= new ArrayList<LocalDate>();
		this.C = new ArrayList<Float>();
		this.C.add(Q/2);
		
		creaEventi();
		
	}

	private void creaEventi() {
		
		float fout;
		
		for(Flow f : flow) {
			if(Math.random() < 0.05) {
				fout = F_out_max;
			} else {
				fout = F_out_min;
			}
			this.queue.add(new Event( f.getFlow() *(60*60*24), fout,
					f.getDay(), EventType.NUOVO_GIORNO));
		}
		
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			processaEvento(e);
			}
	}

	private void processaEvento(Event e) {
		
		float fin = e.getfIn();
		float fout = e.getfOut();
		
		switch(e.getType()) {
		case NUOVO_GIORNO:
			if(fin > fout) {
				float c = fin - fout;
				float newC = (this.C.get(this.C.size()-1) + c);
				if(newC > this.Q) {
					queue.add(new Event( fin, fout, 
							e.getData(), EventType.TRACIMAZIONE));
				} else {
					this.C.add(newC);
				}
			}
			if(fin < fout) {
				if((fout - fin) > this.C.get(this.C.size()-1)) {
					dateErogMin.add(e.getData());
					this.C.add((float) 0);
				} else {
					this.C.add(this.C.get(this.C.size()-1) - (fout-fin));
				}
			}
			break;
		case TRACIMAZIONE:
			
			float eccesso = this.C.get(this.C.size()-1)
			+ fin - this.Q - fout;
			this.C.add(this.Q);
			
			break;
		}
	}

	public List<Float> getC() {
		return C;
	}

	public List<LocalDate> getDateErogMin() {
		return dateErogMin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
