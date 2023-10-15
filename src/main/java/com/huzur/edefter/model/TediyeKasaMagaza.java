package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TediyeKasaMagaza extends TediyeCariMagazaAbstract {
	private String aciklama;

	public TediyeKasaMagaza(int ref, String yymmgg, int kdvOrani, double brutTutar,
			String hesap, String aciklama, int fisNo) {
		super(ref, yymmgg, kdvOrani, brutTutar, hesap, fisNo);
		this.aciklama = aciklama;
	}
	
	public TediyeKasaMagaza(CSVRecord csvRecord) {
		this(Integer.parseInt(csvRecord.get(1)), 
				csvRecord.get(2), 
				Integer.parseInt(csvRecord.get(3)), 
				Double.parseDouble(csvRecord.get(4).replace(',', '.')),
				csvRecord.get(5), 
				csvRecord.get(7),
				Integer.parseInt(csvRecord.get(13)));
	}

	@Override
	public String getAlacakHesapKDV(int kdvOrani) {
		String alacakHesap = "";
		if (kdvOrani == 8 || kdvOrani == 10) {
			alacakHesap = "19101010";
		} else if (kdvOrani == 1) {
			alacakHesap = "19101001";
		} else {
			alacakHesap = "??????????";
		}
		return alacakHesap;
	}

	@Override
	public String getDetayAciklama() {
		return aciklama;
	}

}
