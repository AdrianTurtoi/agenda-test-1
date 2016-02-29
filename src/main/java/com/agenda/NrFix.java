package com.agenda;

import java.io.Serializable;

public class NrFix extends NrTel implements Comparable<NrFix>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nrTelFix;

	public NrFix() {
		this.nrTelFix = "";
	}

	public NrFix(String nrTel) {
		this.nrTelFix = nrTel;
	}

	@Override
	public String getNrTel() {
		return nrTelFix;
	}

	@Override
	public void setNrTel(String nrTel) {
		this.nrTelFix = nrTel;
	}

	@Override
	public String toString() {
		String returnString = nrTelFix;
		return returnString;
	}

	@Override
	public boolean equals(Object obj) {
		NrFix nr = (NrFix) obj;
		if (this.nrTelFix.equalsIgnoreCase(nr.nrTelFix)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(NrFix o1) {
		return this.nrTelFix.compareTo(o1.nrTelFix);
	}

}
