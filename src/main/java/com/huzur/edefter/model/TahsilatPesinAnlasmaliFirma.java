package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatPesinAnlasmaliFirma extends TahsilatPesin {
	
	private static final String ODEME_YONTEMI = "NAKİT (ANLAŞMALI FİRMA)";

	public TahsilatPesinAnlasmaliFirma(String magaza, int ref, int yil, int ay,
			int gun, double tutar1, double tutar8, double tutar18, double islenecekTutar,
			int fisNo) {
		super(magaza, ref, yil, ay, gun, tutar1, tutar8, tutar18, islenecekTutar, fisNo);
	}
	
	public TahsilatPesinAnlasmaliFirma(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getBorcHesap() {
		return "100020" + getMagaza();
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

}
