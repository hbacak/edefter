package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class TediyeNakitIadeMagaza extends Tediye {
	
	private static final String ODEME_YONTEMI = "NAKİT";
	private static final String FIS_DETAY_ACIKLAMA = "MÜŞTERİ NAKİT İADESİ";

	public TediyeNakitIadeMagaza(String magaza, int ref, int yil, int ay, int gun,
			double tutar1, double tutar8, double tutar18, double brutTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		double yalinTutar = tutar1 + tutar8 + tutar18;
		setOran1(tutar1, yalinTutar);
		setOran8(tutar8, yalinTutar);
		setOran18(tutar18, yalinTutar);
	}

	public TediyeNakitIadeMagaza(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				Double.parseDouble(csvRecord.get(7).replace(',', '.')),
				Double.parseDouble(csvRecord.get(8).replace(',', '.')),
				Double.parseDouble(csvRecord.get(9).replace(',', '.')),
				Integer.parseInt(csvRecord.get(13)));
	}

	@Override
	public String getBorcHesap() {
		return null;
	}
	
	public String getBorcHesapNet(int kdvOrani){
		return "61005" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}
	
	public String getBorcHesapKDV(int kdvOrani){
		return "19102" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}

	@Override
	public String getAlacakHesap() {
		return "100020" + getMagaza();
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
		if(getNetTutar8() > 0){
			detay = new EDefterDetay(
					getBorcHesapNet(8),			//alt hesap kodu
					getNetTutar8(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",					//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv8() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(8),			//alt hesap kodu
						getKdv8(),		 			//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						"",							//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama());		//fiş detay açıklama
				detayList.add(detay);
			}
			
		}
		if(getNetTutar18() > 0){
			detay = new EDefterDetay(
					getBorcHesapNet(18),		//alt hesap kodu
					getNetTutar18(),			//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",					//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv18() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(18),		//alt hesap kodu
						getKdv18(),		 			//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						"",					//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama());		//fiş detay açıklama
				detayList.add(detay);
			}
		}
		if(getNetTutar1() > 0){
			detay = new EDefterDetay(
					getBorcHesapNet(1),			//alt hesap kodu
					getNetTutar1(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",					//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv1() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(1),			//alt hesap kodu
						getKdv1(),		 			//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						"",							//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama());		//fiş detay açıklama
				detayList.add(detay);
			}
			
		}
		detay = new EDefterDetay(
				getAlacakHesap(),			//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",					//dokuman tip açıklama
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
		return DOKUMAN_RECEIPT;
	}

	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}

}
