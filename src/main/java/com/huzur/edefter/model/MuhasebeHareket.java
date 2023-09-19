package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public abstract class MuhasebeHareket extends EDefterAbstract {
	
	private static String BASLIK_ACIKLAMA="TAHAKKUK";

	private int fisIslem;
	private String fisMasrafYeri;
	private String fisAciklama;
	private String fisAltHesap;

	public MuhasebeHareket(String magaza, int yil, int ay, int gun, int fisNo,
			double fisTutar,  int fisIslem,
			String fisMasrafYeri, String fisAciklama, String fisAltHesap) {
		super(magaza, 0, yil, ay, gun, fisTutar, fisNo);
		this.fisIslem = fisIslem;
		this.fisMasrafYeri = fisMasrafYeri;
		this.fisAciklama = fisAciklama;
		this.fisAltHesap = fisAltHesap;
	}
	
	public MuhasebeHareket(CSVRecord csvRecord) {
		this(csvRecord.get(1), 
				Integer.parseInt(csvRecord.get(2)), 
				Integer.parseInt(csvRecord.get(3)), 
				Integer.parseInt(csvRecord.get(4)), 
				Integer.parseInt(csvRecord.get(5)), 
				Double.parseDouble(csvRecord.get(6).replace(',', '.')), 
				Integer.parseInt(csvRecord.get(7)),
				csvRecord.get(8), 
				csvRecord.get(9), 
				csvRecord.get(10)
			);
	}

	public int getFisIslem() {
		return fisIslem;
	}

	public void setFisIslem(int fisIslem) {
		this.fisIslem = fisIslem;
	}
	
	public boolean isBorc(){
		return fisIslem == 1; 
	}

	public String getFisMasrafYeri() {
		return fisMasrafYeri;
	}

	public void setFisMasrafYeri(String fisMasrafYeri) {
		this.fisMasrafYeri = fisMasrafYeri;
	}

	public String getFisAciklama() {
		return fisAciklama;
	}

	public void setFisAciklama(String fisAciklama) {
		this.fisAciklama = fisAciklama;
	}

	public String getFisAltHesap() {
		return fisAltHesap;
	}

	public void setFisAltHesap(String fisAltHesap) {
		this.fisAltHesap = fisAltHesap;
	}

	@Override
	public String getBorcHesap() {
		return null;
	}

	@Override
	public String getAlacakHesap() {
		return null;
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
	public String getDetayAciklama() {
		return fisAciklama;
	}
	
	public double getBorcTutar(){
		double tutar = 0;
		if(isBorc()){
			tutar = getBrutTutar();
		}
		return tutar;
	}
	
	public double getAlacakTutar(){
		double tutar = 0;
		if(!isBorc()){
			tutar = getBrutTutar();
		}
		return tutar;
	}
	
	@Override
	public List<EDefterDetay> toEDefterDetay() {
		List<EDefterDetay> detayList = new ArrayList<EDefterDetay>();
		EDefterDetay detay = new EDefterDetay(
				getFisAltHesap(),			//alt hesap kodu
				getBorcTutar(),				//borc
				getAlacakTutar(),			//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				getDokumanTipiAciklama(),	//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		
		return detayList;
	}
	
	public abstract String getDokumanTipiAciklama();
	
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}
}
