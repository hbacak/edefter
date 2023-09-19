package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TediyeFactory {

	public static Tediye getTediye(CSVRecord csvRecord) {
		Tediye obj = null;
		String tip = csvRecord.get(0);
		if (tip.equals("TediyeNakitIadeMagaza")) {
			obj = new TediyeNakitIadeMagaza(csvRecord);
		} else if (tip.equals("TediyeCariMagaza")) {
			obj = new TediyeCariMagaza(csvRecord);
		} else if (tip.equals("TediyeKasaMagaza")) {
			obj = new TediyeKasaMagaza(csvRecord);
		} else if (tip.equals("TediyeCariMerkez")) {
			obj = new TediyeCariMerkez(csvRecord);
		} else if (tip.equals("TediyeKasaMerkez")) {
			obj = new TediyeKasaMerkez(csvRecord);
		}
		return obj;
	}

}
