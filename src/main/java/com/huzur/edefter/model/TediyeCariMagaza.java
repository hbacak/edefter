package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TediyeCariMagaza extends TediyeCariMagazaAbstract {
	
	private String firmaAdi;

	public TediyeCariMagaza(int ref, String yymmgg, int kdvOrani,
			double brutTutar, String hesap, String firmaAdi, String aciklama, int fisNo) {
		super(ref, yymmgg, kdvOrani, brutTutar, hesap, fisNo);
		this.firmaAdi = firmaAdi + aciklama;
	}

	public TediyeCariMagaza(CSVRecord csvRecord) {
		this(Integer.parseInt(csvRecord.get(1)), 
				csvRecord.get(2), 
				Integer.parseInt(csvRecord.get(3)), 
				Double.parseDouble(csvRecord.get(4).replace(',', '.')),
				csvRecord.get(5), 
				csvRecord.get(6), 
				csvRecord.get(7),
				Integer.parseInt(csvRecord.get(13)));
	}

	@Override
	public String getAlacakHesapKDV(int kdvOrani) {
		String alacakHesap = "";
		if (kdvOrani == 8) {
			alacakHesap = "19101008";
		} else if (kdvOrani == 1) {
			alacakHesap = "19101001";
		} else {
			alacakHesap = "??????????";
		}
		return alacakHesap;
	}

	public String getAlacakHesapKDV(String kdvOrani) {
		return getAlacakHesapKDV(Integer.parseInt(kdvOrani));
	}

	@Override
	public String getDetayAciklama() {
		return firmaAdi;
	}

}
