package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public abstract class TahsilatPesin extends Tahsilat {
	
	private static final String FIS_DETAY_ACIKLAMA = "PEŞİN SATIŞLAR";

	public TahsilatPesin(String magaza, int ref, int yil, int ay, int gun,
			double tutar1, double tutar8, double tutar18, double islenecekTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, islenecekTutar, fisNo);
		double yalinTutar = tutar1 + tutar8 + tutar18;
		setOran1(tutar1, yalinTutar);
		setOran8(tutar8, yalinTutar);
		setOran18(tutar18, yalinTutar);
	}
	
	public TahsilatPesin(CSVRecord csvRecord) {
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
	public String getAlacakHesap() {
		return null;
	}

	@Override
	public String getAlacakHesapNet(int kdvOrani) {
		return "60005" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}

	@Override
	public String getAlacakHesapKDV(int kdvOrani) {
		return "39101" + HuzurNumberUtils.nDigit(kdvOrani, 3);
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
				"",							//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		if(getNetTutar8() > 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(10), 	//alt hesap kodu
					0,						//borc
					getNetTutar8(),			//alacak
					getRef(),				//dokuman no
					getDokumanTipi(),		//dokumantipi
					"",						//dokuman tip açıklama
					getTarih(),				//dokuman tarihi
					getOdemeYontemi(),		//odeme yontemi
					getDetayAciklama());	//fiş detay açıklama
			detayList.add(detay);
			if(getKdv8() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapKDV(10), 	//alt hesap kodu
						0,						//borc
						getKdv8(),				//alacak
						getRef(),				//dokuman no
						getDokumanTipi(),		//dokumantipi
						"",						//dokuman tip açıklama
						getTarih(),				//dokuman tarihi
						getOdemeYontemi(),		//odeme yontemi
						getDetayAciklama());	//fiş detay açıklama
				detayList.add(detay);
			}
		}
		
		if(getNetTutar18() > 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(20), 	//alt hesap kodu
					0,						//borc
					getNetTutar18(),		//alacak
					getRef(),				//dokuman no
					getDokumanTipi(),		//dokumantipi
					"",						//dokuman tip açıklama
					getTarih(),				//dokuman tarihi
					getOdemeYontemi(),		//odeme yontemi
					getDetayAciklama());	//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv18() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapKDV(20), 	//alt hesap kodu
						0,						//borc
						getKdv18(),				//alacak
						getRef(),				//dokuman no
						getDokumanTipi(),		//dokumantipi
						"",						//dokuman tip açıklama
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
					"",						//dokuman tip açıklama
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
						"",						//dokuman tip açıklama
						getTarih(),				//dokuman tarihi
						getOdemeYontemi(),		//odeme yontemi
						getDetayAciklama());	//fiş detay açıklama
				detayList.add(detay);
			}
		}
		
		return detayList;
	}

	public String getDokumanTipi() {
		return DOKUMAN_INVOICE;
	}
	
	@Override
	public String getDetayAciklama() {
		return FIS_DETAY_ACIKLAMA;
	}
	
}