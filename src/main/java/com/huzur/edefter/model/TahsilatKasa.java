package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKasa extends TahsilatCariAbstract {
	
	private static final String FIS_DETAY_ACIKLAMA = "BANKAYA GÖNDERİLEN";

	public TahsilatKasa(int ref, String yymmgg, int kdvOrani,
			double brutTutar, String hesap, int fisNo) {
		super(ref, yymmgg, kdvOrani, brutTutar, hesap, fisNo);
	}
	
	public TahsilatKasa(CSVRecord csvRecord) {
		this(Integer.parseInt(csvRecord.get(1)), 
				csvRecord.get(2), 
				Integer.parseInt(csvRecord.get(3)),
				Double.parseDouble(csvRecord.get(4).replace(',', '.')),
				csvRecord.get(5),
				Integer.parseInt(csvRecord.get(13))
		);
	}

	@Override
	public String getAlacakHesapKDV(int kdvOrani) {
		String alacakHesap = "";
		if(kdvOrani == 8) {
			alacakHesap = "39101008";
		} else {
			alacakHesap = "39101001";
		}
		return alacakHesap;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
	
	@Override
	public String getDokumanTipi() {
		// TODO Auto-generated method stub
		return DOKUMAN_RECEIPT;
	}
	
}
