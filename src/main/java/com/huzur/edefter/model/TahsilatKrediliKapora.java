package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKrediliKapora extends TahsilatKredili {
	
	private static final String ODEME_YONTEMI = "NAKİT";

	public TahsilatKrediliKapora(String magaza, int ref, int yil, int ay,
			int gun, double brutTutar, String kreno1, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, kreno1, fisNo);
	}

	public TahsilatKrediliKapora(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getBorcHesap() {
		return "34001001";
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
		return "MÜŞTERİDEN ALINAN KAPORA";
	}
	
	@Override
	public String getAlacakHesap() {
		return "12301001";
	}

}
