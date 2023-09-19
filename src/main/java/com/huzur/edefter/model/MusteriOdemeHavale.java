package com.huzur.edefter.model;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class MusteriOdemeHavale extends MuhasebeHareket {
	
	private static String BASLIK_ACIKLAMA="MÜŞTERİDEN GELEN HAVALE/EFT";

	public MusteriOdemeHavale(String magaza, int yil, int ay, int gun,
			int fisNo, double fisTutar, int fisIslem, String fisMasrafYeri,
			String fisAciklama, String fisAltHesap) {
		super(magaza, yil, ay, gun, fisNo, fisTutar, fisIslem, fisMasrafYeri,
				fisAciklama, fisAltHesap);
		bulRef();
	}

	public MusteriOdemeHavale(CSVRecord csvRecord) {
		super(csvRecord);
		bulRef();
	}
	
	private void bulRef() {
		try{
			setRef(getFisAciklama().substring(0, 6));
		}catch(Exception e){
			System.err.println("aciklamanın ilk 6 hanesi rakam yapılamadı");
		}
	}

	@Override
	public String getDokumanTipiAciklama() {
		return "BANKA HESABINA HAVALE/EFT";
	}

	@Override
	public String getOdemeYontemi() {
		return "HAVALE/EFT";
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_OTHER;
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}
	
	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = super.toEDefterDetay();
		EDefterDetay detay = new EDefterDetay(
				"12001001",					//alt hesap kodu
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

}
