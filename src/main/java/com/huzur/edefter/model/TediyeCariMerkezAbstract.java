package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

public abstract class TediyeCariMerkezAbstract extends Tediye {
	
	private static final String ODEME_YONTEMI = "NAKİT";
	private String hesap = "";
	private int kdvOrani;

	public TediyeCariMerkezAbstract(int ref, int yil, int ay,
			int gun, double brutTutar, String hesap, int fisNo) {
		super("20", ref, yil, ay, gun, brutTutar, fisNo);
		this.hesap = hesap;
	}
	
	public void setKdvOrani(int kdvOrani) {
		this.kdvOrani = kdvOrani;
	}
	
	public String getHesap() {
		return hesap;
	}
	
	public void setHesap(String hesap) {
		this.hesap = hesap;
	}

	@Override
	public String getAlacakHesap() {
		return "10001010";
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
		EDefterDetay detay = null;
		if(kdvOrani == 0) {
			detay = new EDefterDetay(
					getBorcHesap(), 		// alt hesap kodu
					getBrutTutar(), 		// borc
					0, 						// alacak
					getRef(),	 			// dokuman no
					getDokumanTipi(),		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(), 		// odeme yontemi
					getDetayAciklama());	// fiş detay açıklama
			detayList.add(detay);
		}
		
		if(kdvOrani > 0){
			detay = new EDefterDetay(
					getBorcHesapNet(),		// alt hesap kodu
					getNetTutar(kdvOrani), 	// borc
					0,						// alacak
					getRef(), 				// dokuman no
					getDokumanTipi(),		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(),	 	// odeme yontemi
					getDetayAciklama());	// fiş detay açıklama
			detayList.add(detay);

			if(getKdv(kdvOrani) > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(), 		// alt hesap kodu
						getKdv(kdvOrani), 		// borc
						0, 						// alacak
						getRef(), 				// dokuman no
						getDokumanTipi(), 		// dokumantipi
						"", 					// dokuman tip açıklama
						getTarih(), 			// dokuman tarihi
						getOdemeYontemi(), 		// odeme yontemi
						getDetayAciklama()); 	// fiş detay açıklama
				detayList.add(detay);
			}
		}
		
		detay = new EDefterDetay(
				getAlacakHesap(), 		// alt hesap kodu
				0, 						// borc
				getBrutTutar(), 		// alacak
				getRef(),	 			// dokuman no
				getDokumanTipi(),		// dokumantipi
				"", 					// dokuman tip açıklama
				getTarih(), 			// dokuman tarihi
				getOdemeYontemi(), 		// odeme yontemi
				getDetayAciklama());	// fiş detay açıklama
		detayList.add(detay);

		return detayList;
	}

	public abstract String getBorcHesapNet();
	
	public abstract String getBorcHesapKDV();
	
	public abstract double getNetTutar(int kdvOrani2);
	
	public abstract double getKdv(int kdvOrani2);

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
		return null;
	}

}
