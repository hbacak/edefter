package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class EDefterFactory {

	public static EDefterAbstract getEDefter(CSVRecord csvRecord){
		EDefterAbstract obj = null;
		String tip = csvRecord.get(0);
		if(tip.equals("KrediliSatis")){
			obj = new KrediliSatis(csvRecord);
		} else if (tip.equals("BankaliSatis")){
			obj = new BankaliSatis(csvRecord);
		} else if (tip.startsWith("Tahsilat")){
			obj = TahsilatFactory.getTahsilat(csvRecord);
		} else if (tip.startsWith("Tediye")) {
			obj = TediyeFactory.getTediye(csvRecord);
		} else if(tip.equals("SatilanHediyeCeki")) {
			obj = new SatilanHediyeCeki(csvRecord);
		} else if(tip.equals("BankaKartiIade")) {
			obj = new BankaKartiIade(csvRecord);
		} else if(tip.equals("VerilenIMF")) {
			obj = new VerilenIMF(csvRecord);
		} else if(tip.startsWith("Muhhar")){
			obj = MuhasebeHareketFactory.getMuhasebeHareket(csvRecord);
		} else if (tip.equals("BorcCekFirma")){
			obj = new BorcCekFirma(csvRecord);
		} else if (tip.equals("AlacakCekMusteri")) {
			obj = new AlacakCekMusteri(csvRecord);
		} else if (tip.equals("AlacakCekTahsil")) {
			obj = new AlacakCekTahsil(csvRecord);
		} else if (tip.equals("FirmaAlisFatura")) {
			obj = new FirmaAlisFatura(csvRecord);
		}
		return obj;
	}
}
