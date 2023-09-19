package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKapora extends Tahsilat {

	private static final String ODEME_YONTEMI = "NAKİT";
	private static final String FIS_DETAY_ACIKLAMA = "";

	public TahsilatKapora(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
	}

	public TahsilatKapora(CSVRecord csvRecord) {
		this(csvRecord.get(1), 
				Integer.parseInt(csvRecord.get(2)), 
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)), 
				Integer.parseInt(csvRecord.get(5)), 
				Double.parseDouble(csvRecord.get(6).replace(',', '.')), 
				Integer.parseInt(csvRecord.get(13)));
	}

	@Override
	public String getBorcHesap() {
		return "100020" + getMagaza();
	}

	@Override
	public String getAlacakHesap() {
		return "34001001";
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
				getBorcHesap(), 			// alt hesap kodu
				getBrutTutar(), 			// borc
				0, 							// alacak
				getRef(), 					// dokuman no
				getDokumanTipi(), 			// dokumantipi
				getDokumanTipiAciklama(), 	// dokuman tip açıklama
				getTarih(), 				// dokuman tarihi
				getOdemeYontemi(), 			// odeme yontemi
				getDetayAciklama());		// fiş detay açıklama
		detayList.add(detay);

		detay = new EDefterDetay(
				getAlacakHesap(), 			// alt hesap kodu
				0, 							// borc
				getBrutTutar(), 			// alacak
				getRef(), 					// dokuman no
				getDokumanTipi(), 			// dokumantipi
				getDokumanTipiAciklama(),	// dokuman tip açıklama
				getTarih(), 				// dokuman tarihi
				getOdemeYontemi(),	 		// odeme yontemi
				getDetayAciklama());		// fiş detay açıklama
		detayList.add(detay);

		return detayList;
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_OTHER;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
	
	public String getDokumanTipiAciklama() {
		return "MÜŞTERİDEN ALINAN KAPORA";
	}

}
