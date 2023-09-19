package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import com.huzur.utils.genel.HuzurNumberUtils;

public abstract class TediyeCariMagazaAbstract extends Tediye {
	
	private static final String ODEME_YONTEMI = "NAKİT";
	private String hesap = "";
	private int kdvOrani;

	public TediyeCariMagazaAbstract(int ref, String yymmgg, int kdvOrani,
			double brutTutar, String hesap, int fisNo) {
		super();
		setRef(ref);
		olusturMagaza(ref);
		setYAG(yymmgg);
		this.kdvOrani = kdvOrani;
		this.hesap = hesap;
		setFisNo(fisNo);
		setBrutTutar(brutTutar);
	}

	@Override
	public String getAlacakHesapNet(int kdvOrani) {
		return null;
	}

	public String getAlacakHesapNet() {
		return null;
	}

	@Override
	public String getAlacakHesap() {
		return "100020" + getMagaza();
	}

	public String getHesap() {
		return hesap;
	}

	public void setHesap(String hesap) {
		this.hesap = hesap;
	}

	protected void olusturMagaza(int ref) {
		String magaza = "";
		if (HuzurNumberUtils.isBetween(ref, 350000, 399999)) {
			magaza = "35";
		} else if (HuzurNumberUtils.isBetween(ref, 400000, 499999)) {
			magaza = "40";
		} else if (HuzurNumberUtils.isBetween(ref, 500000, 599999)) {
			magaza = "50";
		} else if (HuzurNumberUtils.isBetween(ref, 600000, 699999)) {
			magaza = "60";
		} else if (HuzurNumberUtils.isBetween(ref, 850000, 999999)) {
			magaza = "90";
		}
		setMagaza(magaza);
	}

	protected void setYAG(String yymmgg) {
		setYil(Integer.parseInt(yymmgg.substring(0, 2)));
		setAy(Integer.parseInt(yymmgg.substring(2, 4)));
		setGun(Integer.parseInt(yymmgg.substring(4)));
	}
	
	public double getNetTutar(int kdvOrani) {
		return getBrutTutar() * 100 / (100 + kdvOrani);
	}
	
	public double getKdv(int kdvOrani) {
		return getBrutTutar() * kdvOrani / (100 + kdvOrani);
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
					getBorcHesapNet(kdvOrani),	// alt hesap kodu
					getNetTutar(kdvOrani), 	// borc
					0,					// alacak
					getRef(), 			// dokuman no
					getDokumanTipi(),	// dokumantipi
					"", 				// dokuman tip açıklama
					getTarih(), 		// dokuman tarihi
					getOdemeYontemi(), 	// odeme yontemi
					getDetayAciklama());// fiş detay açıklama
			detayList.add(detay);

			detay = new EDefterDetay(
					getBorcHesapKDV(kdvOrani), 	// alt hesap kodu
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

	@Override
	public String getBorcHesap() {
		return getHesap();
	}
	
	public String getBorcHesapNet(int kdvOrani) {
		return getHesap();
	}
	
	public String getBorcHesapKDV(int kdvOrani){
		String borcHesap = "";
		if (kdvOrani == 1) {
			borcHesap = "19101001";
		} else if(kdvOrani == 8) {
			borcHesap = "19101008";
		} else if(kdvOrani == 18) {
			borcHesap = "19101018";
		}
		return borcHesap;
	}
	
	public String getBorcHesapKDV(String kdvOrani){
		return getBorcHesapKDV(Integer.parseInt(kdvOrani));
	}
	
	@Override
	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		String dokumanTipi = null;
		
		if(getDetayAciklama().matches(".*(FİŞ|FŞ|FT|FAT|FATNO)\\s?(:|\\.).*")){
			dokumanTipi = DOKUMAN_INVOICE;
		} else if( getDetayAciklama().matches(".*(BANKAYA GÖNDERİLEN|NAKTEN YATAN|MASRAF LİSTESİ|ÜNSPED).*")) {
			dokumanTipi = DOKUMAN_RECEIPT;
		} else {
			dokumanTipi = DOKUMAN_OTHER;
		}
		return dokumanTipi;
	}

}
