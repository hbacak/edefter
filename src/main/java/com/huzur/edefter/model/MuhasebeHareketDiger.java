package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class MuhasebeHareketDiger extends MuhasebeHareket {

	public MuhasebeHareketDiger(String magaza, int yil, int ay, int gun,
			int fisNo, double fisTutar, int fisIslem, String fisMasrafYeri,
			String fisAciklama, String fisAltHesap) {
		super(magaza, yil, ay, gun, fisNo, fisTutar, fisIslem, fisMasrafYeri,
				fisAciklama, fisAltHesap);
	}

	public MuhasebeHareketDiger(CSVRecord csvRecord) {
		super(csvRecord);
	}

	@Override
	public String getDokumanTipiAciklama() {
		return BOS_STRING;
	}

	@Override
	public String getOdemeYontemi() {
		return BOS_STRING;
	}

	@Override
	public String getDokumanTipi() {
		return BOS_STRING;
	}

	@Override
	public String getBaslikAciklama() {
		return BOS_STRING;
	}

}
