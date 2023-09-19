package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import com.huzur.utils.genel.HuzurNumberUtils;

public abstract class TahsilatCariAbstract extends Tahsilat {

	private static final String ODEME_YONTEMI = "NAKİT";
	private String hesap = "";
	private int kdvOrani;

	public TahsilatCariAbstract(int ref, String yymmgg, int kdvOrani,
			double brutTutar, String hesap, int fisNo) {
		super();
		setRef(ref);
		olusturMagaza(ref);
		setYAG(yymmgg);
		this.kdvOrani = kdvOrani;
		if (kdvOrani == 8) {
			setOran8(1);
			setOran18(0);
		} else if (kdvOrani == 18) {
			setOran8(0);
			setOran18(1);
		}
		this.hesap = hesap;
		setFisNo(fisNo);
		setBrutTutar(brutTutar);
	}

	@Override
	public String getAlacakHesapNet(int kdvOrani) {
		return null;
	}

	public String getAlacakHesapNet() {
		return getHesap();
	}

	@Override
	public String getAlacakHesap() {
		return null;
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

	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = new ArrayList<EDefterDetay>();
		EDefterDetay detay = new EDefterDetay(
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

		if(kdvOrani == 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(), 	// alt hesap kodu
					0, 						// borc
					getBrutTutar(), 		// alacak
					getRef(), 				// dokuman no
					getDokumanTipi(), 		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(), 		// odeme yontemi
					getDetayAciklama()); 	// fiş detay açıklama
			detayList.add(detay);
		} else {
			if (getNetTutar8() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapNet(),// alt hesap kodu
						0, 					// borc
						getNetTutar8(), 	// alacak
						getRef(), 			// dokuman no
						getDokumanTipi(),	// dokumantipi
						"", 				// dokuman tip açıklama
						getTarih(), 		// dokuman tarihi
						getOdemeYontemi(), 	// odeme yontemi
						getDetayAciklama());// fiş detay açıklama
				detayList.add(detay);
	
				if(getKdv8() > 0) {
					detay = new EDefterDetay(
							getAlacakHesapKDV(8), 	// alt hesap kodu
							0, 						// borc
							getKdv8(), 				// alacak
							getRef(), 				// dokuman no
							getDokumanTipi(), 		// dokumantipi
							"", 					// dokuman tip açıklama
							getTarih(), 			// dokuman tarihi
							getOdemeYontemi(), 		// odeme yontemi
							getDetayAciklama()); 	// fiş detay açıklama
					detayList.add(detay);
				}
			}
	
			if (getNetTutar18() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapNet(), 	// alt hesap kodu
						0, 						// borc
						getNetTutar18(), 		// alacak
						getRef(), 				// dokuman no
						getDokumanTipi(),		// dokumantipi
						"", 					// dokuman tip açıklama
						getTarih(), 			// dokuman tarihi
						getOdemeYontemi(), 		// odeme yontemi
						getDetayAciklama()); 	// fiş detay açıklama
				detayList.add(detay);
	
				if(getKdv18() > 0) {
					detay = new EDefterDetay(
							getAlacakHesapKDV(18), 	// alt hesap kodu
							0, 						// borc
							getKdv18(), 			// alacak
							getRef(), 				// dokuman no
							getDokumanTipi(), 		// dokumantipi
							"", 					// dokuman tip açıklama
							getTarih(), 			// dokuman tarihi
							getOdemeYontemi(), 		// odeme yontemi
							getDetayAciklama()); 	// fiş detay açıklama
					detayList.add(detay);
				}
			}
		}
		
		

		return detayList;
	}

	@Override
	public String getBorcHesap() {
		return "100020" + getMagaza();
	}

	@Override
	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}

	@Override
	public String getDokumanTipi() {
		return DOKUMAN_OTHER;
	}

}
