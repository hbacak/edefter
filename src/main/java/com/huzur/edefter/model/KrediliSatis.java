package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.huzur.utils.genel.HuzurNumberUtils;

public class KrediliSatis extends EDefterAbstract {

	private static final String ODEME_YONTEMI = "CARİ HESAP(TAKSİTLİ)";
	private static final String FIS_DETAY_ACIKLAMA = "HUZUR KARTLI SATIŞLAR";
	private static final String BASLIK_ACIKLAMA = "KREDİLİ MÜŞTERİLERE SATIŞ";
	private String kreno1;

	public KrediliSatis(String magaza, int ref, int yil, int ay, int gun,
			String kreno1, double tutar1, double tutar8, double tutar18, double iskonto,
			int fisNo) {
		super(magaza, ref, yil, ay, gun, tutar1 + tutar8 + tutar18 - iskonto, fisNo);
		double yalinTutar = tutar1 + tutar8 + tutar18;
		setOran1(tutar1, yalinTutar);
		setOran8(tutar8, yalinTutar);
		setOran18(tutar18, yalinTutar);
		this.kreno1 = kreno1;
	}

	public KrediliSatis(CSVRecord csvRecord) {
		this(csvRecord.get(1), 
				Integer.parseInt(csvRecord.get(2)), 
				Integer.parseInt(csvRecord.get(3)),
				Integer.parseInt(csvRecord.get(4)), 
				Integer.parseInt(csvRecord.get(5)), 
				csvRecord.get(6), 
				Double.parseDouble(csvRecord.get(7).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(8).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(9).replace(',', '.')), 
				Double.parseDouble(csvRecord.get(10).replace(',', '.')), 
				Integer.parseInt(csvRecord.get(11)));
	}

	public String getKreno1() {
		return kreno1;
	}

	public void setKreno1(String kreno1) {
		this.kreno1 = kreno1;
	}

	@Override
	public String getBorcHesap() {
		if (isKredili())
			return "12001001";
		else
			return "12001002";
	}
	
	public boolean isKredili(){
		return "K".equals(kreno1);
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
				getBorcHesap(),		// alt hesap kodu
				getBrutTutar(), 	// borc
				0, 					// alacak
				getRef(), 			// dokuman no
				getDokumanTipi(), 	// dokumantipi
				"", 				// dokuman tip açıklama
				getTarih(), 		// dokuman tarihi
				getOdemeYontemi(), 	// odeme yontemi
				getDetayAciklama());// fiş detay açıklama
		detayList.add(detay);

		if (getNetTutar8() > 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(8), 	// alt hesap kodu
					0, 						// borc
					getNetTutar8(), 		// alacak
					getRef(), 				// dokuman no
					getDokumanTipi(), 		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(), 		// odeme yontemi
					getDetayAciklama()); 	// fiş detay açıklama
			detayList.add(detay);

			if(getKdv8() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapKDV(8), 	// alt hesap kodu
						0, 						// borc
						getKdv8(), 				// alacak
						getRef(), 				// dokuman no
						getDokumanTipi(), 		// dokumantipi
						"", 					// dokuman tip açıklama
						getTarih(), 			// dokuman tarihi
						getOdemeYontemi(), 		// odeme yontemi
						getDetayAciklama()); 	// fiş detay açıklama
				detayList.add(detay);
			}
		}

		if (getNetTutar18() > 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(18), 	// alt hesap kodu
					0, 						// borc
					getNetTutar18(),	 	// alacak
					getRef(), 				// dokuman no
					getDokumanTipi(), 		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(), 		// odeme yontemi
					getDetayAciklama()); 	// fiş detay açıklama
			detayList.add(detay);

			if(getKdv18() > 0 ) {
				detay = new EDefterDetay(
						getAlacakHesapKDV(18), 	// alt hesap kodu
						0, 						// borc
						getKdv18(), 			// alacak
						getRef(), 				// dokuman no
						getDokumanTipi(), 		// dokumantipi
						"", 					// dokuman tip açıklama
						getTarih(), 			// dokuman tarihi
						getOdemeYontemi(), 		// odeme yontemi
						getDetayAciklama()); 	// fiş detay açıklama
				detayList.add(detay);
			}
		}
		if (getNetTutar1() > 0) {
			detay = new EDefterDetay(
					getAlacakHesapNet(1), 	// alt hesap kodu
					0, 						// borc
					getNetTutar1(), 		// alacak
					getRef(), 				// dokuman no
					getDokumanTipi(), 		// dokumantipi
					"", 					// dokuman tip açıklama
					getTarih(), 			// dokuman tarihi
					getOdemeYontemi(), 		// odeme yontemi
					getDetayAciklama()); 	// fiş detay açıklama
			detayList.add(detay);

			if(getKdv1() > 0) {
				detay = new EDefterDetay(
						getAlacakHesapKDV(1), 	// alt hesap kodu
						0, 						// borc
						getKdv1(), 				// alacak
						getRef(), 				// dokuman no
						getDokumanTipi(), 		// dokumantipi
						"", 					// dokuman tip açıklama
						getTarih(), 			// dokuman tarihi
						getOdemeYontemi(), 		// odeme yontemi
						getDetayAciklama()); 	// fiş detay açıklama
				detayList.add(detay);
			}
		}

		return detayList;
	}

	public String getDokumanTipi() {
		return DOKUMAN_INVOICE;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public String getOdemeYontemi() {
		return ODEME_YONTEMI;
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
