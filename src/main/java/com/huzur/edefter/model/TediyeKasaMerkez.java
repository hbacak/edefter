package com.huzur.edefter.model;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class TediyeKasaMerkez extends TediyeCariMerkezAbstract {
	private int musno;

	public TediyeKasaMerkez(int ref, int yil, int ay, int gun,
			double brutTutar, String hesap, int fisNo, int mahno, int musno, 
			String aciklama) {
		super(ref, yil, ay, gun, brutTutar, hesap, fisNo);
		setKdvOrani(bulKdvOrani(mahno, musno));
		this.musno = musno;
	}
	
	public TediyeKasaMerkez(CSVRecord csvRecord) {
		this(Integer.parseInt(csvRecord.get(1)),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Double.parseDouble(csvRecord.get(8).replace(',', '.')),
				csvRecord.get(5),
				Integer.parseInt(csvRecord.get(12)),
				Integer.parseInt(csvRecord.get(6)),
				Integer.parseInt(csvRecord.get(7)),
				csvRecord.get(11)
		);
	}
	
	private int bulKdvOrani(int mahno, int musno) {
		int kdvOrani = 0;
		if(mahno != 0 && musno != 0){
			kdvOrani = musno % 100;
		}
		return kdvOrani;
	}

	@Override
	public String getBorcHesapNet() {
		return getHesap();
	}

	@Override
	public String getBorcHesapKDV() {
		return "" + musno;
	}
	
	
	
	@Override
	public double getNetTutar(int kdvOrani) {
		return HuzurNumberUtils.round(getBrutTutar() * 100 / (100 + kdvOrani), 2);
	}
	
	@Override
	public double getKdv(int kdvOrani) {
		return HuzurNumberUtils.round(getBrutTutar() - getNetTutar(kdvOrani), 2);
	}

	@Override
	public String getBorcHesap() {
		return getHesap();
	}
	
	@Override
	public String getDokumanTipi() {
		if( musno / 100000 == 191){
			return DOKUMAN_INVOICE;
		}
		return super.getDokumanTipi();
	}

}
