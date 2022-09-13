package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Stampa {
	
	private River river;
	private LocalDate firstDate;
	private LocalDate lastDate;
	private int nMisure;
	private float flussoMedio;
	
	public Stampa(River river, LocalDate firstDate, LocalDate lastDate, int nMisure, float flussoMedio) {
		super();
		this.river = river;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.nMisure = nMisure;
		this.flussoMedio = flussoMedio;
	}

	public River getRiver() {
		return river;
	}

	public void setRiver(River river) {
		this.river = river;
	}

	public LocalDate getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(LocalDate firstDate) {
		this.firstDate = firstDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public int getnMisure() {
		return nMisure;
	}

	public void setnMisure(int nMisure) {
		this.nMisure = nMisure;
	}

	public float getFlussoMedio() {
		return flussoMedio;
	}

	public void setFlussoMedio(float flussoMedio) {
		this.flussoMedio = flussoMedio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((river == null) ? 0 : river.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stampa other = (Stampa) obj;
		if (river == null) {
			if (other.river != null)
				return false;
		} else if (!river.equals(other.river))
			return false;
		return true;
	}
	
	
	

}
