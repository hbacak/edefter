package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;


public class TahsilatKrediliNakit extends TahsilatKredili {
	
	private static final String ODEME_YONTEMI = "NAKİT";
	
	public TahsilatKrediliNakit(String magaza, int ref, int yil, int ay, int gun,
			 double brutTutar, String kreno1, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, kreno1, fisNo);
	}

	public TahsilatKrediliNakit(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getBorcHesap() {
		return "100020"+getMagaza();
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_RECEIPT;
	}

}
