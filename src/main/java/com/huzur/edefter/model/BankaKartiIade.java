package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class BankaKartiIade extends EDefterAbstract {

	private static final String ODEME_YONTEMI = "BANKA KREDİ KARTI";
	private static final String FIS_DETAY_ACIKLAMA = "BANKA KREDİ KARTI SLİP";
	private static final String BASLIK_ACIKLAMA = "BANKA KARTI İADE";
	private int binkod;
	
	public BankaKartiIade(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo, int binkod) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		this.binkod = binkod;
	}

	public BankaKartiIade(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(7).replace(',', '.')),
				Integer.parseInt(csvRecord.get(8)),
				Integer.parseInt(csvRecord.get(6))
			);
	}

	@Override
	public String getBorcHesap() {
		return "39803001";
	}

	@Override
	public String getAlacakHesap() {
		String hesapKodu = "";
		switch (binkod) {
		//Garanti
		case 520019:
		case 554960:	
			hesapKodu = "12301001";
			break;
		//Yapı Kredi	
		case 540122:
		case 450634:	
			hesapKodu = "12302001";
			break;
		//İşBank	
		case 543771:
		case 454360:
		case 404308:	
			hesapKodu = "12303001";
			break;
		//Akbank	
		case 557113:
			hesapKodu = "12304001";
			break;
		//Bankasya	
		case 402276:
		case 402275:
			hesapKodu = "12307001";
			break;
		//FinansBank
		case 402278:
			hesapKodu = "12308001";
			break;
		//HSBC
		case 405917:
		case 550472:	
			hesapKodu = "12311001";
			break;
		//Türkiye Finans	
		case 435628:
			hesapKodu = "12312001";
			break;
		//HalkBank	
		case 543081:
			hesapKodu = "12313001";
			break;
		default:
			hesapKodu = "???" + binkod;
			break;
		}
		return hesapKodu;
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
		return "VİSA SLİPİ";
	}

}
