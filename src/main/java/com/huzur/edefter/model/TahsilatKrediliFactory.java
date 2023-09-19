package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKrediliFactory {

	public static Tahsilat getTahsilatKredili(CSVRecord csvRecord) {
		Tahsilat obj = null;
		String tip = csvRecord.get(0);
		switch (tip) {
		case "TahsilatKrediliNakit":
			obj = new TahsilatKrediliNakit(csvRecord);
			break;
		case "TahsilatKrediliIMF":
			obj = new TahsilatKrediliIMF(csvRecord);
			break;
		case "TahsilatKrediliKapora":
			obj = new TahsilatKrediliKapora(csvRecord);
			break;
		case "TahsilatKrediliMerkez":
			obj = new TahsilatKrediliMerkez(csvRecord);
		}
		return obj;
	}

}
