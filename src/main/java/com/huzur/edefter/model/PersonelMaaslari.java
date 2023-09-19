package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class PersonelMaaslari extends MuhasebeHareket {
	
	private static String BASLIK_ACIKLAMA="PERSONEL MAAŞ TAHAKKUKU";

	public PersonelMaaslari(String magaza, int yil, int ay, int gun, int fisNo,
			double fisTutar, int fisIslem, String fisMasrafYeri,
			String fisAciklama, String fisAltHesap) {
		super(magaza, yil, ay, gun, fisNo, fisTutar, fisIslem, fisMasrafYeri,
				fisAciklama, fisAltHesap);
	}

	public PersonelMaaslari(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getOdemeYontemi() {
		return "";
	}

	@Override
	public String getDokumanTipi() {
		return "";
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}

	@Override
	public String getDokumanTipiAciklama() {
		return "";
	}

}
