package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

public class TediyeCariMerkez extends TediyeCariMerkezAbstract {

	private String borcHesap;

	public TediyeCariMerkez(int ref, int yil, int ay, int gun,
			double brutTutar, String hesap, int fisNo, int mahno, int kren3, 
			String cariHesap, String firmaAdi, String aciklama) {
		super(ref, yil, ay, gun, brutTutar, hesap, fisNo);
		this.borcHesap = setBorcHesap(hesap, mahno, kren3, cariHesap);
	}
	
	public TediyeCariMerkez(CSVRecord csvRecord) {
		this(Integer.parseInt(csvRecord.get(1)),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Double.parseDouble(csvRecord.get(8).replace(',', '.')),
				csvRecord.get(5),
				Integer.parseInt(csvRecord.get(12)),
				Integer.parseInt(csvRecord.get(6)),
				Integer.parseInt(csvRecord.get(7)),
				csvRecord.get(9),
				csvRecord.get(10),
				csvRecord.get(11)
		);
	}

	private String setBorcHesap(String hesap, int mahno, int kren3,
			String cariHesap) {
		String borcHesap = cariHesap;
		if(hesap != null && hesap.charAt(0) == '5' && mahno > 0 && kren3 > 0 ){
			borcHesap = Integer.toString(mahno * 10 + kren3);
		}
		return borcHesap;
	}

	@Override
	public String getBorcHesapNet() {
		return null;
	}

	@Override
	public String getBorcHesapKDV() {
		return null;
	}

	@Override
	public double getNetTutar(int kdvOrani2) {
		return 0;
	}

	@Override
	public double getKdv(int kdvOrani2) {
		return 0;
	}

	@Override
	public String getBorcHesap() {
		return borcHesap;
	}

}
