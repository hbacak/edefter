package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class SatilanHediyeCeki extends EDefterAbstract {

	private static final String ODEME_YONTEMI = "NAKİT";
	private static final String FIS_DETAY_ACIKLAMA = "HEDİYE ÇEKİ SATIŞI";
	private static final String BASLIK_ACIKLAMA = "HEDİYE ÇEKİ SATIŞI";

	private String kreno1;
	private double tutar0;
	private String hesapNo;

	public SatilanHediyeCeki(String magaza, String ref, String yymmgg,
			double brutTutar, int fisNo, String hesapNo, String kreno1, double tutar0) {
		super();
		setMagaza(magaza);
		setRef(ref);
		setYAG(yymmgg);
		setBrutTutar(brutTutar);
		setFisNo(fisNo);
		this.kreno1 = kreno1;
		this.tutar0 = tutar0;
		this.hesapNo = hesapNo;
	}

	public SatilanHediyeCeki(CSVRecord csvRecord) {
		this(csvRecord.get(1), 
				csvRecord.get(2), 
				csvRecord.get(3), 
				Double.parseDouble(csvRecord.get(4).replace(',', '.')), 
				Integer.parseInt(csvRecord.get(8)), 
				csvRecord.get(5), 
				csvRecord.get(6), 
				Double.parseDouble(csvRecord.get(7).replace(',', '.')));
	}

	private void setYAG(String yymmgg) {
		setYil(Integer.parseInt(yymmgg.substring(0, 2)));
		setAy(Integer.parseInt(yymmgg.substring(2, 4)));
		setGun(Integer.parseInt(yymmgg.substring(4)));
	}

	private boolean isKredili() {
		return "K".equals(kreno1);
	}

	private boolean isHatirli() {
		return "H".equals(kreno1);
	}

	@Override
	public String getBorcHesap() {
		String borcHesap = "";
		if (isKredili()) {
			borcHesap = "12001001";
		} else if (isHatirli()) {
			borcHesap = "12001002";
		} else if(!hesapNo.trim().equals("")) {
			if(hesapNo.charAt(0) == '7'){
				borcHesap = "12003001";
			} else {
				borcHesap = "12002001";
			}
		} else if(tutar0 == 0) {
			borcHesap = "100020" + getMagaza();
		} 
		return borcHesap;
	}

	@Override
	public String getAlacakHesap() {
		String alacakHesap = null;
		if(isKredili()) {
			alacakHesap = "34002001";
		} else if (isHatirli()) {
			alacakHesap = "34002002";
		} else if ("".equals(hesapNo)){
			alacakHesap = "34002004";
		} else {
			alacakHesap = "34002003";
		}
		return alacakHesap;
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
				getDokumanTipiAciklama(),	//dokuman tip açıklama
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
				getDokumanTipiAciklama(),	//dokuman tip açıklama
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
		return DOKUMAN_OTHER;
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
	
	public String getDokumanTipiAciklama() {
		return "MÜŞTERİYE SATILAN HEDİYE ÇEKİ";
	}

}
