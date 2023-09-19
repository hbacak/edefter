package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatPesinIMF extends TahsilatPesin {
	
	private static final String ODEME_YONTEMI = "GİDER PUSULASI";

	public TahsilatPesinIMF(String magaza, int ref, int yil, int ay, int gun,
			double tutar1, double tutar8, double tutar18, double islenecekTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, tutar1, tutar8, tutar18, islenecekTutar, fisNo);
	}

	public TahsilatPesinIMF(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getBorcHesap() {
		return "39802001";
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

}
