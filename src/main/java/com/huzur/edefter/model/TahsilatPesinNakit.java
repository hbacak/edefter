package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatPesinNakit extends TahsilatPesin {
	
	private static final String ODEME_YONTEMI = "NAKİT";

	public TahsilatPesinNakit(String magaza, int ref, int yil, int ay, int gun,
			double tutar1, double tutar8, double tutar18, double eksiTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, tutar1, tutar8, tutar18, tutar1 + tutar8 + tutar18 - eksiTutar, fisNo);
	}

	public TahsilatPesinNakit(CSVRecord csvRecord) {
		this(csvRecord.get(1), 
				Integer.parseInt(csvRecord.get(2)), 
				Integer.parseInt(csvRecord.get(3)), 
				Integer.parseInt(csvRecord.get(4)), 
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(7).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(8).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(9).replace(',', '.')), 
				Integer.parseInt(csvRecord.get(13)));
	}

	@Override
	public String getBorcHesap() {
		return "100020" + getMagaza();
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

}
