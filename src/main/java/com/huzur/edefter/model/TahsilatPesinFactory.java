package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatPesinFactory {

	public static Tahsilat getTahsilatPesin(CSVRecord csvRecord) {
		Tahsilat obj = null;
		String tip = csvRecord.get(0);
		switch(tip) {
		case "TahsilatPesinAnlasmaliFirma" :
			obj = new TahsilatPesinAnlasmaliFirma(csvRecord);
			break;
		case "TahsilatPesinHediyeCeki" :
			obj = new TahsilatPesinHediyeCeki(csvRecord);
			break;
		case "TahsilatPesinIMF" :
			obj = new TahsilatPesinIMF(csvRecord);
			break;
		case "TahsilatPesinKapora" : 
			obj = new TahsilatPesinKapora(csvRecord);
			break;
		case "TahsilatPesinNakit" :
			obj = new TahsilatPesinNakit(csvRecord);
			break;
		}
		return obj;
	}

}
