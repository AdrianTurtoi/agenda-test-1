package com.agenda;

import java.io.Serializable;

public class NrMobil extends NrTel implements Comparable<NrMobil>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nrTelMobil;

	public NrMobil() {
		this.nrTelMobil = "";
	}

	public NrMobil(String nrTelMobil) {
		this.nrTelMobil = nrTelMobil;
	}

	@Override
	public String getNrTel() {
		return nrTelMobil;
	}

	@Override
	public void setNrTel(String nrTel) {
		this.nrTelMobil = nrTel;
	}

	@Override
	public String toString() {
		String returnString = nrTelMobil;
		return returnString;
	}

	@Override
	public boolean equals(Object obj) {
		NrMobil nr = (NrMobil) obj;
		if (this.nrTelMobil.equalsIgnoreCase(nr.nrTelMobil)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(NrMobil o1) {
		return this.nrTelMobil.compareTo(o1.nrTelMobil);
	}
}
