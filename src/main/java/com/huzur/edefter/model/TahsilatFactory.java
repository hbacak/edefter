package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatFactory {

	public static Tahsilat getTahsilat(CSVRecord csvRecord) {
		Tahsilat obj = null;
		String tip = csvRecord.get(0);
		if (tip.startsWith("TahsilatKredili")) {
			obj = TahsilatKrediliFactory.getTahsilatKredili(csvRecord);
		} else if (tip.startsWith("TahsilatPesin")) {
			obj = TahsilatPesinFactory.getTahsilatPesin(csvRecord);
		} else if (tip.equals("TahsilatCari")) {
			obj = new TahsilatCari(csvRecord);
		} else if (tip.equals("TahsilatKasa")) {
			obj = new TahsilatKasa(csvRecord);
		} else if (tip.equals("TahsilatKapora")) {
			obj = new TahsilatKapora(csvRecord);
		} else if (tip.equals("TahsilatKasaMerkez") || tip.equals("TahsilatCariMerkez")) {
			obj = new TahsilatKasaMerkez(csvRecord);
		} 
			
		return obj;
	}
}
