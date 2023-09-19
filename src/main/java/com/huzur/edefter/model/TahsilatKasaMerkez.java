package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class TahsilatKasaMerkez extends Tahsilat {

	private String hesap;
	private String aciklama;
	
	public TahsilatKasaMerkez(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo, String hesap, String aciklama) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		this.hesap = hesap;
		this.aciklama = aciklama;
	}

	public TahsilatKasaMerkez(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				Integer.parseInt(csvRecord.get(9)),
				csvRecord.get(7),
				csvRecord.get(8));
	}

	@Override
	public String getBorcHesap() {
		return "10001010";
	}

	@Override
	public String getAlacakHesap() {
		return hesap;
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
				BOS_STRING,					//dokuman tip açıklama
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
				BOS_STRING,					//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		return detayList;
	}

	@Override
	public String getOdemeYontemi() {
		return null;
	}

	@Override
	public String getDokumanTipi() {
		if(aciklama != null && aciklama.contains("(AV")) {
			return DOKUMAN_RECEIPT;
		}
		return "";
	}

	@Override
	public String getDetayAciklama() {
		return aciklama;
	}

}
