package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class BorcCekFirma extends EDefterAbstract {
	private static final String ODEME_YONTEMI = "ÇEK";
	private static final String BASLIK_ACIKLAMA = "ÇEK ÖDEMESİ(FİRMA)";
	private static final String FIS_DETAY_ACIKLAMA = "FİRMAYA VERİLEN BORÇ ÇEKİ";
	private String borcHesap;
	private int cekNo;

	public BorcCekFirma(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo, String cariNo, int cekNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		setBorcHesap(cariNo);
		this.cekNo = cekNo;
	}

	private void setBorcHesap(String cariNo) {
		if(cariNo != null){
			if(cariNo.startsWith("1")) {
				this.borcHesap = "32001001";
			} else if (cariNo.startsWith("4")){
				this.borcHesap = "33601001";
			}
		} else {
			this.borcHesap = "";
		}
	}

	public BorcCekFirma(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				Integer.parseInt(csvRecord.get(9)),
				csvRecord.get(7),
				Integer.parseInt(csvRecord.get(8))
		);
	}

	@Override
	public String getBorcHesap() {
		return borcHesap;
	}

	@Override
	public String getAlacakHesap() {
		return "10301002";
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
				cekNo,						//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",							//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		detay = new EDefterDetay(
				getAlacakHesap(),			//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				cekNo,						//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",							//dokuman tip açıklama
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
		return DOKUMAN_CHECK;
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
