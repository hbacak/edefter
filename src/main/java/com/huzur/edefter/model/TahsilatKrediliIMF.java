package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKrediliIMF extends TahsilatKredili {
	
	private static final String ODEME_YONTEMI = "GİDER PUSULASI";
	
	public TahsilatKrediliIMF(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, String kreno1, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, kreno1, fisNo);
	}

	public TahsilatKrediliIMF(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getBorcHesap() {
		String borcHesap = "39801002";
		if(isKredili())
			borcHesap = "39801001";
		return borcHesap;
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_OTHER;
	}
	
	@Override
	public String getDokumanTipiAciklama() {
		return "GİDER PUSULASI (İADE MAL FİŞİ)";
	}

}
