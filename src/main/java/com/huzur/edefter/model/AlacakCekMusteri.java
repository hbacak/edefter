package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class AlacakCekMusteri extends EDefterAbstract {
	
	private int cekNo;
	private String kreno1;
	private String krediCari;
	private String cekSenet;

	public AlacakCekMusteri(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo, String krediCari, String kreno1, 
			int cekNo, String cekSenet) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
		this.cekNo = cekNo;
		this.kreno1 = kreno1;
		this.krediCari = krediCari;
		this.cekSenet = cekSenet;
		
	}

	public AlacakCekMusteri(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(6).replace(',', '.')),
				Integer.parseInt(csvRecord.get(11)),
				csvRecord.get(7),
				csvRecord.get(8),
				Integer.parseInt(csvRecord.get(9)),
				csvRecord.get(10));
	}
	
	private boolean isCek(){
		return "C".equals(cekSenet);
	}
	
	private boolean isSenet(){
		return "S".equals(cekSenet);
	}
	
	private boolean isKredili() {
		return !isCari() && "K".equals(kreno1);
	}
	
	private boolean isHatirli() {
		return !isCari() && "H".equals(kreno1);
	}
	
	private boolean isCari() {
		return "C".equals(krediCari);
	}

	@Override
	public String getBorcHesap() {
		String borcHesap = "";
		if(isSenet()){
			borcHesap = "12101001";
		} else if (isCek()){
			borcHesap = "10101001";
		}
		return borcHesap;
	}

	@Override
	public String getAlacakHesap() {
		String alacakHesap = "";
		if(isKredili()) {
			alacakHesap = "12001001";
		} else if (isHatirli()) {
			alacakHesap = "12001002";
		} else if (isCari()) {
			alacakHesap = "12003001";
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
		String odemeYontemi = "";
		if(isSenet()) {
			odemeYontemi = "SENET";
		} else if (isCek()) {
			odemeYontemi = "ÇEK";
		}
		return odemeYontemi;
	}

	@Override
	public String getDokumanTipi() {
		String dokumanTipi = "";
		if(isSenet()) {
			dokumanTipi = DOKUMAN_VOUCHER;
		} else if (isCek()) {
			dokumanTipi = DOKUMAN_CHECK;
		}
		return dokumanTipi;
	}

	@Override
	public String getBaslikAciklama() {
		String aciklama = "";
		if(isSenet()) {
			aciklama = "MÜŞTERİDEN ALINAN SENET";
		} else if (isCek()) {
			aciklama = "MÜŞTERİDEN ALINAN ÇEK";
		}
		return aciklama;
	}

	@Override
	public String getDetayAciklama() {
		return getBaslikAciklama();
	}

}
