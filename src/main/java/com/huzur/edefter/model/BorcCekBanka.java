package com.huzur.edefter.model;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class BorcCekBanka extends MuhasebeHareket {
	private static final String ODEME_YONTEMI = "NAKİT";
	private static final String BASLIK_ACIKLAMA = "ÇEK ÖDEMESİ(BANKA)";

	public BorcCekBanka(String magaza, int yil, int ay, int gun, int fisNo,
			double fisTutar, int fisIslem, String fisMasrafYeri,
			String fisAciklama, String fisAltHesap) {
		super(magaza, yil, ay, gun, fisNo, fisTutar, fisIslem, fisMasrafYeri,
				fisAciklama, fisAltHesap);
		setRef(fisAciklama.substring(12, 21));
	}

	public BorcCekBanka(CSVRecord csvRecord) {
		super(csvRecord);
		setRef(getFisAciklama().substring(12, 21));
	}

	@Override
	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_OTHER;
	}
	
	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = super.toEDefterDetay();
		EDefterDetay detay = new EDefterDetay(
				"10301002",					//alt hesap kodu
				getBrutTutar(),				//borc
				0,							//alacak
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
	public String getDokumanTipiAciklama() {
		return "HAVALE";
	}

}
