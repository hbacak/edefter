package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class MuhasebeHareketFactory {

	public static EDefterAbstract getMuhasebeHareket(CSVRecord csvRecord) {
		MuhasebeHareket muhhar = null;
		String tip = csvRecord.get(0);
		switch (tip) {
		case "Muhhar_BorcCekBanka":
			muhhar = new BorcCekBanka(csvRecord);
			break;
		case "Muhhar_TopluEFT":
			muhhar = new TopluEFT(csvRecord);
			break;
		case "Muhhar_PersonelMaaslari":
			muhhar = new PersonelMaaslari(csvRecord);
			break;
		case "Muhhar_MusteriOdemeHavale":
			muhhar = new MusteriOdemeHavale(csvRecord);
			break;	
		case "Muhhar_Diger":
			muhhar = new MuhasebeHareketDiger(csvRecord);
			break;
		default:
			break;
		}
		
		return muhhar;
	}

}
