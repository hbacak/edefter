package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class BankaliSatis extends EDefterAbstract {
	
	private static final String ODEME_YONTEMI = "BANKA KREDİ KARTI";
	private static final String FIS_DETAY_ACIKLAMA = "BANKA KREDİ KARTI SLİP";
	private static final String BASLIK_ACIKLAMA = "BANKA KARTLI SATIŞ";
	private int binkod;
	public BankaliSatis(String magaza, int ref, int yil, int ay, int gun,
			int binkod, double brutTutar, double tutar1, double tutar8, double tutar18,
			int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		double yalinTutar = tutar1 + tutar8 + tutar18;
		setOran1(tutar1, yalinTutar);
		setOran8(tutar8, yalinTutar);
		setOran18(tutar18, yalinTutar);
		this.binkod = binkod;
	}

	public BankaliSatis(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Integer.parseInt(csvRecord.get(6)),
				Double.parseDouble(csvRecord.get(7).replace(',', '.')),
				Double.parseDouble(csvRecord.get(8).replace(',', '.')),
				Double.parseDouble(csvRecord.get(9).replace(',', '.')),
				Double.parseDouble(csvRecord.get(10).replace(',', '.')),
				Integer.parseInt(csvRecord.get(11)));
	}

	public int getBinkod() {
		return binkod;
	}
	
	public void setBinkod(int binkod) {
		this.binkod = binkod;
	}
	
	@Override
	public String getBorcHesap() {
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
		case 490805:	
			hesapKodu = "12303001";
			break;
		//Akbank	
		case 557113:
		case 557829:
			hesapKodu = "12304001";
			break;
		//Bankasya	
		case 402276:
		case 402275:
			hesapKodu = "12307001";
			break;
		//FinansBank
		case 402278:
		case 415956:
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
		//Ziraat	
		case 676124:
			hesapKodu = "12314001";
			break;
		default:
			hesapKodu = "???" + binkod;
			break;
		}
		return hesapKodu;
	}
	
	@Override
	public String getAlacakHesap() {
		return null;
	}

	@Override
	public String getAlacakHesapNet(int kdvOrani){
		return "60005" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}
	
	@Override
	public String getAlacakHesapKDV(int kdvOrani){
		return "39101" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
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
				BOS_STRING,					//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		if(getOran8() == 0 && getOran18() == 0 && getOran1() == 0){
			detay = new EDefterDetay(
					"12001001", 			//alt hesap kodu
					0,						//borc
					getBrutTutar(),			//alacak
					getRef(),				//dokuman no
					DOKUMAN_RECEIPT,		//dokumantipi
					BOS_STRING,				//dokuman tip açıklama
					getTarih(),				//dokuman tarihi
					getOdemeYontemi(),		//odeme yontemi
					getDetayAciklama());	//fiş detay açıklama
			detayList.add(detay);
		} else {
		
			if(getNetTutar8() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapNet(8), 	//alt hesap kodu
						0,						//borc
						getNetTutar8(),			//alacak
						getRef(),				//dokuman no
						getDokumanTipi(),		//dokumantipi
						BOS_STRING,				//dokuman tip açıklama
						getTarih(),				//dokuman tarihi
						getOdemeYontemi(),		//odeme yontemi
						getDetayAciklama());	//fiş detay açıklama
				detayList.add(detay);
				if(getKdv8() > 0) {
					detay = new EDefterDetay(
							getAlacakHesapKDV(8), 	//alt hesap kodu
							0,						//borc
							getKdv8(),				//alacak
							getRef(),				//dokuman no
							getDokumanTipi(),		//dokumantipi
							BOS_STRING,				//dokuman tip açıklama
							getTarih(),				//dokuman tarihi
							getOdemeYontemi(),		//odeme yontemi
							getDetayAciklama());	//fiş detay açıklama
					detayList.add(detay);
				}
			}
			
			if(getNetTutar18() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapNet(18), 	//alt hesap kodu
						0,						//borc
						getNetTutar18(),		//alacak
						getRef(),				//dokuman no
						getDokumanTipi(),		//dokumantipi
						BOS_STRING,				//dokuman tip açıklama
						getTarih(),				//dokuman tarihi
						getOdemeYontemi(),		//odeme yontemi
						getDetayAciklama());	//fiş detay açıklama
				detayList.add(detay);
				if(getKdv18() > 0) {
					detay = new EDefterDetay(
							getAlacakHesapKDV(18), 	//alt hesap kodu
							0,						//borc
							getKdv18(),				//alacak
							getRef(),				//dokuman no
							getDokumanTipi(),		//dokumantipi
							BOS_STRING,				//dokuman tip açıklama
							getTarih(),				//dokuman tarihi
							getOdemeYontemi(),		//odeme yontemi
							getDetayAciklama());	//fiş detay açıklama
					detayList.add(detay);
				}
			}
			
			if(getNetTutar1() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapNet(1), 	//alt hesap kodu
						0,						//borc
						getNetTutar1(),			//alacak
						getRef(),				//dokuman no
						getDokumanTipi(),		//dokumantipi
						BOS_STRING,				//dokuman tip açıklama
						getTarih(),				//dokuman tarihi
						getOdemeYontemi(),		//odeme yontemi
						getDetayAciklama());	//fiş detay açıklama
				detayList.add(detay);
				if(getKdv1() > 0) {
					detay = new EDefterDetay(
							getAlacakHesapKDV(1), 	//alt hesap kodu
							0,						//borc
							getKdv1(),				//alacak
							getRef(),				//dokuman no
							getDokumanTipi(),		//dokumantipi
							BOS_STRING,				//dokuman tip açıklama
							getTarih(),				//dokuman tarihi
							getOdemeYontemi(),		//odeme yontemi
							getDetayAciklama());	//fiş detay açıklama
					detayList.add(detay);
				}
			}
		}
		return detayList;
	}

	@Override
	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
	}
	
	@Override
	public String getDokumanTipi() {
		return DOKUMAN_INVOICE;
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
		
}
