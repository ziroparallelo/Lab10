package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	
	public enum EventType{
		NUOVO_GIORNO,
		TRACIMAZIONE
	}

	
	private float fIn;
	private float fOut;
	private LocalDate data;
	private EventType type;
	
	
	
	public Event(float fIn, float fOut, LocalDate data, EventType type) {
		super();
		this.fIn = fIn;
		this.fOut = fOut;
		this.data = data;
		this.type = type;
	}


	
	public float getfIn() {
		return fIn;
	}



	public void setfIn(float fIn) {
		this.fIn = fIn;
	}



	public float getfOut() {
		return fOut;
	}



	public void setfOut(float fOut) {
		this.fOut = fOut;
	}



	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
		this.data = data;
	}



	public EventType getType() {
		return type;
	}



	public void setType(EventType type) {
		this.type = type;
	}



	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.getData());
	}
	
	

}
