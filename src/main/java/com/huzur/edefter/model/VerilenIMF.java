package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class VerilenIMF extends EDefterAbstract {
	
	private static final String ODEME_YONTEMI = "GİDER PUSULASI";
	private static final String BASLIK_ACIKLAMA = "VERİLEN İADE MAL FİŞİ";
	private int odov;
	private String kreno1;
	private int okodu;
	
	public VerilenIMF(String magaza, int ref, int yil, int ay, int gun,
			double tutar1, double tutar8, double tutar18, int odov, 
			String kreno1, int okodu, int fisNo) {
		super(magaza, ref, yil, ay, gun, tutar1 + tutar8 + tutar18, fisNo);
		double yalinTutar = tutar1 + tutar8 + tutar18;
		setOran1(tutar1, yalinTutar);
		setOran8(tutar8, yalinTutar);
		setOran18(tutar18, yalinTutar);
		this.odov= odov;
		this.kreno1 = kreno1;
		this.okodu = okodu;
	}
	
	public VerilenIMF(CSVRecord csvRecord) {
		this(csvRecord.get(1),
				Integer.parseInt(csvRecord.get(2)),
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)),
				Integer.parseInt(csvRecord.get(5)),
				Double.parseDouble(csvRecord.get(7).replace(',', '.')),
				Double.parseDouble(csvRecord.get(8).replace(',', '.')),
				Double.parseDouble(csvRecord.get(9).replace(',', '.')),
				Integer.parseInt(csvRecord.get(10)),
				csvRecord.get(11),
				Integer.parseInt(csvRecord.get(12)),
				Integer.parseInt(csvRecord.get(13))
		);
	}

	@Override
	public String getBorcHesap() {
		return null;
	}
	
	public String getBorcHesapNet(int kdvOrani) {
		return "61005" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}
	
	public String getBorcHesapKDV(int kdvOrani) {
		return "19102" + HuzurNumberUtils.nDigit(kdvOrani, 3);
	}

	@Override
	public String getAlacakHesap() {
		String alacakHesap = "";
		if(odov == 0 && okodu == 3 && isKredili()) {
			alacakHesap = "12001001";
		} else if (odov == 0 && okodu == 3 && isHatirli()) {
			alacakHesap = "12001002";
		} else if (odov == 0 && okodu == 5) {
			alacakHesap = "39803001";
		} else if (odov == 1 && isKredili()) {
			alacakHesap = "39801001";
		} else if (odov == 1 && isHatirli()) {
			alacakHesap = "39801002";
		} else if (odov == 1) {
			alacakHesap = "39802001";
		}
		return alacakHesap;
	}
	
	private boolean isKredili() {
		return "K".equals(kreno1);
	}
	
	private boolean isHatirli() {
		return "H".equals(kreno1);
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
		String alacakHesap = getAlacakHesap();
		if(getNetTutar8() > 0) {
			detay = new EDefterDetay(
					getBorcHesapNet(8),			//alt hesap kodu
					getNetTutar8(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					getDokumanTipiAciklama(),	//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama(alacakHesap));		//fiş detay açıklama
			detayList.add(detay);
			if(getKdv8() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(8),			//alt hesap kodu
						getKdv8(),					//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						getDokumanTipiAciklama(),	//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama(alacakHesap));		//fiş detay açıklama
				detayList.add(detay);
			}
		}
		
		if(getNetTutar18() > 0) {
			detay = new EDefterDetay(
					getBorcHesapNet(18),		//alt hesap kodu
					getNetTutar18(),			//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					getDokumanTipiAciklama(),	//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama(alacakHesap));		//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv18() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(18),		//alt hesap kodu
						getKdv18(),					//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						getDokumanTipiAciklama(),	//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama(alacakHesap));		//fiş detay açıklama
				detayList.add(detay);
			}
		}
		
		if(getNetTutar1() > 0) {
			detay = new EDefterDetay(
					getBorcHesapNet(1),			//alt hesap kodu
					getNetTutar1(),				//borc
					0,							//alacak
					getRef(),					//dokuman no
					getDokumanTipi(),			//dokumantipi
					getDokumanTipiAciklama(),	//dokuman tip açıklama
					getTarih(),					//dokuman tarihi
					getOdemeYontemi(),			//odeme yontemi
					getDetayAciklama(alacakHesap));		//fiş detay açıklama
			detayList.add(detay);
			
			if(getKdv1() > 0) {
				detay = new EDefterDetay(
						getBorcHesapKDV(1),			//alt hesap kodu
						getKdv1(),					//borc
						0,							//alacak
						getRef(),					//dokuman no
						getDokumanTipi(),			//dokumantipi
						getDokumanTipiAciklama(),	//dokuman tip açıklama
						getTarih(),					//dokuman tarihi
						getOdemeYontemi(),			//odeme yontemi
						getDetayAciklama(alacakHesap));		//fiş detay açıklama
				detayList.add(detay);
			}
		}
		detay = new EDefterDetay(
				alacakHesap,				//alt hesap kodu
				0,							//borc
				getBrutTutar(),				//alacak
				getRef(),					//dokuman no
				getDokumanTipi(),			//dokumantipi
				getDokumanTipiAciklama(),	//dokuman tip açıklama
				getTarih(),					//dokuman tarihi
				getOdemeYontemi(),			//odeme yontemi
				getDetayAciklama(alacakHesap));		//fiş detay açıklama
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
		return null;
	}
	
	public String getDetayAciklama(String alacakHesap) {
		String detayAciklama = "";
		switch (alacakHesap) {
		case "12001001":
			detayAciklama = "KREDİLİ HESAPTAN DÜŞÜLEN İADE (K GRUBU MÜŞTERİ)";
			break;
		case "12001002":
			detayAciklama = "KREDİLİ HESAPTAN DÜŞÜLEN İADE (H GRUBU MÜŞTERİ)";
			break;
		case "39801001":
			detayAciklama = "GİDER PUSULASI (K GRUBU KREDİLİ MÜŞTERİ)";
			break;
		case "39801002":
			detayAciklama = "GİDER PUSULASI (H GRUBU KREDİLİ MÜŞTERİ)";
			break;
		case "39802001":
			detayAciklama = "GİDER PUSULASI (PEŞİN MÜŞTERİ)";
			break;	
		case "39803001":
			detayAciklama = "GİDER PUSULASI (BANKA KARTLI MÜŞTERİ)";
			break;	
		}
		return detayAciklama;
	}
	
	public String getDokumanTipiAciklama(){
		return "GİDER PUSULASI (MÜŞTERİYE VERİLEN İADE MAL FİŞİ)";
	}

}
