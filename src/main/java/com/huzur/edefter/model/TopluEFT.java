package com.huzur.edefter.model;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class TopluEFT extends MuhasebeHareket {

	private static final String ODEME_YONTEMI = "HAVALE/EFT";
	private static final String BASLIK_ACIKLAMA = "FİRMAYA HAVALE/EFT";
	public TopluEFT(String magaza, int yil, int ay, int gun, int fisNo,
			double fisTutar, int fisIslem, String fisMasrafYeri,
			String fisAciklama, String fisAltHesap) {
		super(magaza, yil, ay, gun, fisNo, fisTutar, fisIslem, fisMasrafYeri,
				fisAciklama, fisAltHesap);
		setRef((getYil() * 10000 + getAy() * 100 + getGun()) + "_" + fisNo);
	}

	public TopluEFT(CSVRecord csvRecord) {
		super(csvRecord);
		setRef((getYil() * 10000 + getAy() * 100 + getGun()) + "_" + getFisNo());
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
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}
	
	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = super.toEDefterDetay();
		EDefterDetay detay = new EDefterDetay(
				"10201001",					//alt hesap kodu
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
	public String getDokumanTipiAciklama() {
		return "HAVALE/EFT";
	}

}
