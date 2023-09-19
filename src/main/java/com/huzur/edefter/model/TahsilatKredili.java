package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;


public abstract class TahsilatKredili extends Tahsilat {
	
	private static final String FIS_DETAY_ACIKLAMA = "MÜŞTERİ BORÇ ÖDEMESİ";
	private String kreno1;
	
	public TahsilatKredili(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, String kreno1, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		this.kreno1 = kreno1;
	}
	
	public TahsilatKredili(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				csvRecord.get(7),
				Integer.parseInt(csvRecord.get(13))
		);
	}
	
	public String getKreno1() {
		return kreno1;
	}
	
	public void setKreno1(String kreno1) {
		this.kreno1 = kreno1;
	}
	
	public boolean isKredili(){
		return "K".equals(kreno1);
	}

	@Override
	public String getAlacakHesapNet(int kdvOrani) {
		return null;
	}

	@Override
	public String getAlacakHesapKDV(int kdvOrani) {
		return null;
	}
	
	@Override
	public String getAlacakHesap() {
		String alacakHesap = "12001002";
		if(isKredili())
			alacakHesap = "12001001";
		return alacakHesap;
	}

	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = new ArrayList<EDefterDetay>();
		EDefterDetay detay = new EDefterDetay(
				getBorcHesap(),				//alt hesap kodu
				getBrutTutar(),				//borc
				0,							//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				getDokumanTipiAciklama(),	//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		detay = new EDefterDetay(
				getAlacakHesap(), 			//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				getDokumanTipiAciklama(),	//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		return detayList;
	}
	
	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
	
	public String getDokumanTipiAciklama() {
		return "";
	}

}
