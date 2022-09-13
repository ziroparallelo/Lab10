package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow {
	private LocalDate day;
	private float flow;
	private River river;

	public Flow(LocalDate day, float flow, River river) {
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public float getFlow() {
		return flow;
	}

	public void setFlow(float flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

	
}
