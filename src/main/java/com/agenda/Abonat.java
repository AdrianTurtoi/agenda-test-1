package com.agenda;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Abonat implements Comparable<Abonat>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nume;
	private String prenume;

	@Id
	private Long CNP;
	private NrFix nrTelFix;
	private NrMobil nrTelMobil;

	public Abonat() {
		this.nume = "";
		this.prenume = "";
		this.CNP = null;
		this.nrTelFix = new NrFix();
		this.nrTelMobil = new NrMobil();
	}

	public Abonat(String nume, String prenume, Long CNP, NrFix nrTelFix, NrMobil nrTelMobil) {
		this.nume = nume;
		this.prenume = prenume;
		this.CNP = CNP;
		this.nrTelFix = nrTelFix;
		this.nrTelMobil = nrTelMobil;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public Long getCNP() {
		return CNP;
	}

	public void setCNP(Long cNP) {
		CNP = cNP;
	}

	public NrFix getNrTelFix() {
		return nrTelFix;
	}

	public void setNrTelFix(NrFix nrTelFix) {
		this.nrTelFix = nrTelFix;

	}

	public NrMobil getNrTelMobil() {
		return nrTelMobil;
	}

	public void setNrTelMobil(NrMobil nrTelMobil) {
		this.nrTelMobil = nrTelMobil;
	}

	@Override
	public String toString() {
		return String.format("Abonat [Nume='%s', Prenume='%s', CNP=%s, NrTelFix=%s, NrTelMobil=%s]", this.nume,
				this.prenume, this.CNP.toString(), this.nrTelFix.toString(), this.nrTelMobil.toString());
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof Abonat)) {
			return false;
		} else {
			Abonat abonat = (Abonat) object;

			if (this.nume.equalsIgnoreCase(abonat.getNume()) && this.prenume.equalsIgnoreCase(abonat.getPrenume())
					&& this.CNP == abonat.getCNP().longValue() && this.nrTelFix.equals(abonat.getNrTelFix())
					&& this.nrTelMobil.equals(abonat.getNrTelMobil())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Abonat o) {
		return Comparators.NUME.compare(this, o);
	}

	public static class Comparators {

		public static Comparator<Abonat> NUME = new Comparator<Abonat>() {
			@Override
			public int compare(Abonat o1, Abonat o2) {
				return o1.nume.compareTo(o2.nume);
			}
		};

		public static Comparator<Abonat> PRENUME = new Comparator<Abonat>() {
			@Override
			public int compare(Abonat o1, Abonat o2) {
				return o1.prenume.compareTo(o2.prenume);
			}
		};

		public static Comparator<Abonat> CNP = new Comparator<Abonat>() {
			@Override
			public int compare(Abonat o1, Abonat o2) {
				return Long.compare(o1.CNP, o2.CNP);
			}
		};

		public static Comparator<Abonat> NRFIX = new Comparator<Abonat>() {
			@Override
			public int compare(Abonat o1, Abonat o2) {
				return o1.nrTelFix.compareTo(o2.nrTelFix);
			}
		};

		public static Comparator<Abonat> NRMOBIL = new Comparator<Abonat>() {
			@Override
			public int compare(Abonat o1, Abonat o2) {
				return o1.nrTelMobil.compareTo(o2.nrTelMobil);
			}
		};

	}

}
