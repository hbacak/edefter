package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class FirmaAlisFatura extends EDefterAbstract {
	
	private static final String BASLIK_ACIKLAMA = "SATINALMA FATURASI";
	private static final String FIS_DETAY_ACIKLAMA = "ÜRÜN SATINALIMI-";

	private String faturaNo;
	private double kdvTutar1;
	private int kdvOran1;
	private int kdvOran2;
	private double kdvTutar2;
	private int kdvOran3;
	private double kdvTutar3;
	private String firmaAdi;
	private double netTutar;

	public FirmaAlisFatura(String magaza, String faturaNo , int yil, int ay, int gun,
			double brutTutar, int fisNo, double kdvTutar1, int kdvOran1, double kdvTutar2,
			int kdvOran2, double kdvTutar3,	int kdvOran3, String firmaAdi) {
		super(magaza, 0, yil, ay, gun, brutTutar, fisNo);
		this.faturaNo = faturaNo;
		this.kdvOran1 = kdvOran1;
		this.kdvTutar1 = kdvTutar1;
		this.kdvOran2 = kdvOran2;
		this.kdvTutar2 = kdvTutar2;
		this.setKdvOran3(kdvOran3);
		this.setKdvTutar3(kdvTutar3);
		this.firmaAdi = firmaAdi;
		this.netTutar = brutTutar - kdvTutar1 - kdvTutar2 - kdvTutar3;
		bulRef();
	}

	public FirmaAlisFatura(CSVRecord csvRecord) {
		this(
			csvRecord.get(1),
			csvRecord.get(2),
			Integer.parseInt(csvRecord.get(3)),
			Integer.parseInt(csvRecord.get(4)),
			Integer.parseInt(csvRecord.get(5)),
			Double.parseDouble(csvRecord.get(6).replace(',', '.')),
			Integer.parseInt(csvRecord.get(14)),
			Double.parseDouble(csvRecord.get(7).replace(',', '.')),
			Integer.parseInt(csvRecord.get(8)),
			Double.parseDouble(csvRecord.get(9).replace(',', '.')),
			Integer.parseInt(csvRecord.get(10)),
			Double.parseDouble(csvRecord.get(11).replace(',', '.')),
			Integer.parseInt(csvRecord.get(12)),
			csvRecord.get(13)
		);
	}
	
	private void bulRef(){
		if(faturaNo != null) {
			setRef(faturaNo);
		}
	}

	public String getFaturaNo() {
		return faturaNo;
	}

	public void setFaturaNo(String faturaNo) {
		this.faturaNo = faturaNo;
	}

	public double getKdvTutar1() {
		return kdvTutar1;
	}

	public void setKdvTutar1(double kdvTutar1) {
		this.kdvTutar1 = kdvTutar1;
	}

	public int getKdvOran1() {
		return kdvOran1;
	}

	public void setKdvOran1(int kdvOran1) {
		this.kdvOran1 = kdvOran1;
	}

	public int getKdvOran2() {
		return kdvOran2;
	}

	public void setKdvOran2(int kdvOran2) {
		this.kdvOran2 = kdvOran2;
	}

	public double getKdvTutar2() {
		return kdvTutar2;
	}

	public void setKdvTutar2(double kdvTutar2) {
		this.kdvTutar2 = kdvTutar2;
	}
	
	public int getKdvOran3() {
		return kdvOran3;
	}
	
	public void setKdvOran3(int kdvOran3) {
		this.kdvOran3 = kdvOran3;
	}
	
	public double getKdvTutar3() {
		return kdvTutar3;
	}
	
	public void setKdvTutar3(double kdvTutar3) {
		this.kdvTutar3 = kdvTutar3;
	}

	public String getFirmaAdi() {
		return firmaAdi;
	}

	public void setFirmaAdi(String firmaAdi) {
		this.firmaAdi = firmaAdi;
	}

	public double getNetTutar() {
		return netTutar;
	}

	public void setNetTutar(double netTutar) {
		this.netTutar = netTutar;
	}

	@Override
	public String getBorcHesap() {
		return null;
	}

	@Override
	public String getAlacakHesap() {
		return "32001001";
	}
	
	public String getBorcHesapNet() {
		return "15301001";
	}
	
	public String getBorcHesapKDV(int kdvOrani) {
		return "19101" + HuzurNumberUtils.nDigit(kdvOrani, 3);
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
				getBorcHesapNet(),			//alt hesap kodu
				getNetTutar(),				//borc
				0,							//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				"",							//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama());		//fiş detay açıklama
		detayList.add(detay);
		if(getKdvOran1() > 0){
			detay = new EDefterDetay(
					getBorcHesapKDV(getKdvOran1()),	//alt hesap kodu
					getKdvTutar1(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",							//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
		}
		if(getKdvOran2() > 0){
			detay = new EDefterDetay(
					getBorcHesapKDV(getKdvOran2()),	//alt hesap kodu
					getKdvTutar2(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",							//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
		}
		if(getKdvOran3() > 0){
			detay = new EDefterDetay(
					getBorcHesapKDV(getKdvOran3()),	//alt hesap kodu
					getKdvTutar3(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					"",							//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama());		//fiş detay açıklama
			detayList.add(detay);
		}
		detay = new EDefterDetay(
				getAlacakHesap(),			//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				getRef(),					//dokuman no
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
		return "VADELİ AÇIK FATURA";
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
		return FIS_DETAY_ACIKLAMA + firmaAdi + "_" + faturaNo;
	}

}
