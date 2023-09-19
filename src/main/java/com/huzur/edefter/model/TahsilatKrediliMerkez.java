package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKrediliMerkez extends Tahsilat {

	private static final String FIS_DETAY_ACIKLAMA = "MÜŞTERİ KREDİ TAHSİLAT";
	private static final String ODEME_YONTEMI = "NAKİT";
	private String kreno1;
	public TahsilatKrediliMerkez(String magaza, int ref, int yil, int ay,
			int gun, double brutTutar, int fisNo, String kreno1) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		if(kreno1 != null) {
			this.kreno1 = kreno1.trim();
		}
	}
	
	public TahsilatKrediliMerkez(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				Integer.parseInt(csvRecord.get(9)),
				csvRecord.get(7));
	}
	
	private boolean isKredili(){
		return "K".equals(kreno1);
	}

	@Override
	public String getBorcHesap() {
		return "10001010";
	}

	@Override
	public String getAlacakHesap() {
		String hesap = null;
		if(isKredili()){
			hesap = "12001001";
		} else {
			hesap = "12001002";
		}
		return hesap;
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
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = new ArrayList<EDefterDetay>();
		EDefterDetay detay = new EDefterDetay(
				getBorcHesap(),				//alt hesap kodu
				getBrutTutar(),				//borc
				0,							//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",					//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		detay = new EDefterDetay(
				getAlacakHesap(),			//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",					//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		return detayList;
	}

	@Override
	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_RECEIPT;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}

}
