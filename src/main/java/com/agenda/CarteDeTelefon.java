package com.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarteDeTelefon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Abonat> getAllUsers() {
		List<Abonat> listaAbonati = null;
		try {
			File file = new File("Abonati.dat");
			if (!file.exists()) {
				listaAbonati = new ArrayList<Abonat>();
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				listaAbonati = (List<Abonat>) ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listaAbonati;
	}

	public void adaugaAbonat(Abonat abonat) {
		List<Abonat> listaAbonati = getAllUsers();
		if (!listaAbonati.contains(abonat)) {
			listaAbonati.add(abonat);
			saveUserList(listaAbonati);
		} else {
			System.out.println("Abonatul exista!");
		}
	}

	public void stergeAbonat(Abonat abonat) {
		List<Abonat> listaAbonati = getAllUsers();
		for (Abonat elem : listaAbonati) {
			if (elem.equals(abonat)) {
				listaAbonati.remove(abonat);
				saveUserList(listaAbonati);
			}
		}
	}

	public void modificaAbonat(Long CNP, Abonat abn) {
		List<Abonat> listaAbonati = getAllUsers();
		for (Abonat abonat : listaAbonati) {
			// for (int i = 0; i < listaAbonati.size(); i++) {
			if (abonat.getCNP() == CNP.longValue()) {
				// listaAbonati.set(i, abn);
				int index = listaAbonati.indexOf(abonat);
				listaAbonati.set(index, abn);
				saveUserList(listaAbonati);
			}
		}
	}

	public void cautaAbonat(String nume) {
		List<Abonat> listaAbonati = getAllUsers();
		for (Abonat elem : listaAbonati) {
			if (elem.getNume().equalsIgnoreCase(nume)) {
				afiseazaAbonati(elem);
			} else {
				if (elem.getNume().matches("(?i).*" + nume + ".*")) {
					afiseazaAbonati(elem);
				}
			}
		}
	}

	public void cautaAbonat(Long CNP) {
		List<Abonat> listaAbonati = getAllUsers();
		for (Abonat elem : listaAbonati) {
			if (elem.getCNP() == CNP.longValue()) {
				afiseazaAbonati(elem);
			}
		}
	}

	public void cautaAbonat(int pozitie) {
		List<Abonat> listaAbonati = getAllUsers();
		for (int i = 0; i < listaAbonati.size(); i++) {
			afiseazaAbonati(listaAbonati.get(i));
		}
	}

	public void afiseazaAbonati(Abonat abonat) {
		System.out.println(abonat.toString());
	}

	private void saveUserList(List<Abonat> listAbonat) {
		try {
			File file = new File("Abonati.dat");
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listAbonat);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
