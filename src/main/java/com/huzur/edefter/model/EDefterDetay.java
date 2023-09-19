package com.huzur.edefter.model;


import java.util.Calendar;
import java.util.Date;
import com.huzur.utils.excel.ExcelExportable;
import com.huzur.utils.excel.FormatType;
import com.huzur.utils.excel.ReportColumn;
import com.huzur.utils.genel.HuzurNumberUtils;

public class EDefterDetay implements ExcelExportable {

	public static int detaySayisi = 1;
	private int satirMaddeNo;
	private String fisNo;
	private String anaHesapKodu;
	private String anaHesapAciklama;
	private String altHesapKodu;
	private String altHesapAciklama;
	private double borc;
	private double alacak;
	private int fisSatirNo;
	private String dokumanNo;
	private String dokumanTipi;
	private String dokumanTipiAciklama;
	private Calendar dokumanTarihi;
	private String odemeYontemi;
	private String fisDetayAciklamasi;
	private int firmaNo;
	private int subeNo;
	private String kaynakReferans;

	public EDefterDetay() {
		super();
		this.satirMaddeNo = detaySayisi++;
	}

	public EDefterDetay(int satirMaddeNo, String fisNo,
			String anaHesapAciklama, String altHesapKodu,
			String altHesapAciklama, double borc, double alacak,
			int fisSatirNo, String dokumanNo, String dokumanTipi,
			String dokumanTipiAciklama, Calendar dokumanTarihi,
			String odemeYontemi, String fisDetayAciklamasi, int firmaNo,
			int subeNo, String kaynakReferans) {
		this();
		this.satirMaddeNo = satirMaddeNo;
		this.fisNo = fisNo;
		setAltHesapKodu(altHesapKodu);
		this.anaHesapAciklama = anaHesapAciklama;
		this.altHesapAciklama = altHesapAciklama;
		setBorc(borc);
		setAlacak(alacak);
		this.fisSatirNo = fisSatirNo;
		this.dokumanNo = dokumanNo;
		this.dokumanTipi = dokumanTipi;
		this.dokumanTipiAciklama = dokumanTipiAciklama;
		this.dokumanTarihi = dokumanTarihi;
		this.odemeYontemi = odemeYontemi;
		this.fisDetayAciklamasi = fisDetayAciklamasi;
		this.firmaNo = firmaNo;
		this.subeNo = subeNo;
		this.kaynakReferans = kaynakReferans;
	}

	public EDefterDetay(String altHesapKodu, double borc, double alacak,
			String dokumanNo, String dokumanTipi, String dokumanTipiAciklama,
			Calendar dokumanTarihi, String odemeYontemi,
			String fisDetayAciklamasi) {
		this();
		setAltHesapKodu(altHesapKodu);
		setBorc(borc);
		setAlacak(alacak);
		this.dokumanNo = dokumanNo;
		this.dokumanTipi = dokumanTipi;
		this.dokumanTipiAciklama = dokumanTipiAciklama;
		this.dokumanTarihi = dokumanTarihi;
		this.odemeYontemi = odemeYontemi;
		this.fisDetayAciklamasi = fisDetayAciklamasi;
	}

	public EDefterDetay(String altHesapKodu, double borc, double alacak,
			int dokumanNo, String dokumanTipi, String dokumanTipiAciklama,
			Calendar dokumanTarihi, String odemeYontemi,
			String fisDetayAciklamasi) {
		this(altHesapKodu, borc, alacak, Integer.toString(dokumanNo),
				dokumanTipi, dokumanTipiAciklama, dokumanTarihi, odemeYontemi,
				fisDetayAciklamasi);
	}

	public int getSatirMaddeNo() {
		return satirMaddeNo;
	}

	public void setSatirMaddeNo(int satirMaddeNo) {
		this.satirMaddeNo = satirMaddeNo;
	}

	public String getFisNo() {
		return fisNo;
	}

	public void setFisNo(String fisNo) {
		this.fisNo = fisNo;
	}

	public String getAnaHesapKodu() {
		return anaHesapKodu;
	}

	public String getAnaHesapAciklama() {
		return anaHesapAciklama;
	}

	public void setAnaHesapAciklama(String anaHesapAciklama) {
		this.anaHesapAciklama = anaHesapAciklama;
	}

	public String getAltHesapKodu() {
		return altHesapKodu;
	}

	public void setAltHesapKodu(String altHesapKodu) {
		if (altHesapKodu != null && altHesapKodu.length() >= 3) {
			this.anaHesapKodu = altHesapKodu.substring(0, 3);
			this.altHesapKodu = altHesapKodu;
		}
	}

	public String getAltHesapAciklama() {
		return altHesapAciklama;
	}

	public void setAltHesapAciklama(String altHesapAciklama) {
		this.altHesapAciklama = altHesapAciklama;
	}

	public double getBorc() {
		return borc;
	}

	public void setBorc(double borc) {
		this.borc = HuzurNumberUtils.round(borc, 2);
	}

	public double getAlacak() {
		return alacak;
	}

	public void setAlacak(double alacak) {
		this.alacak = HuzurNumberUtils.round(alacak, 2);
	}

	public int getFisSatirNo() {
		return fisSatirNo;
	}

	public void setFisSatirNo(int fisSatirNo) {
		this.fisSatirNo = fisSatirNo;
	}

	public String getDokumanNo() {
		return dokumanNo;
	}

	public void setDokumanNo(String dokumanNo) {
		this.dokumanNo = dokumanNo;
	}

	public String getDokumanTipi() {
		return dokumanTipi;
	}

	public void setDokumanTipi(String dokumanTipi) {
		this.dokumanTipi = dokumanTipi;
	}

	public String getDokumanTipiAciklama() {
		return dokumanTipiAciklama;
	}

	public void setDokumanTipiAciklama(String dokumanTipiAciklama) {
		this.dokumanTipiAciklama = dokumanTipiAciklama;
	}

	public Calendar getDokumanTarihi() {
		return dokumanTarihi;
	}

	public Date getDokumanTarihiDate() {
		return dokumanTarihi.getTime();
	}

	public void setDokumanTarihi(Calendar dokumanTarihi) {
		this.dokumanTarihi = dokumanTarihi;
	}

	public String getOdemeYontemi() {
		return odemeYontemi;
	}

	public void setOdemeYontemi(String odemeYontemi) {
		this.odemeYontemi = odemeYontemi;
	}

	public String getFisDetayAciklamasi() {
		return fisDetayAciklamasi;
	}

	public void setFisDetayAciklamasi(String fisDetayAciklamasi) {
		this.fisDetayAciklamasi = fisDetayAciklamasi;
	}

	public int getFirmaNo() {
		return firmaNo;
	}

	public void setFirmaNo(int firmaNo) {
		this.firmaNo = firmaNo;
	}

	public int getSubeNo() {
		return subeNo;
	}

	public void setSubeNo(int subeNo) {
		this.subeNo = subeNo;
	}

	public String getKaynakReferans() {
		return kaynakReferans;
	}

	public void setKaynakReferans(String kaynakReferans) {
		this.kaynakReferans = kaynakReferans;
	}

	@Override
	public ReportColumn[] getReportColumns() {
		return new ReportColumn[] {
				new ReportColumn("satirMaddeNo", "Satır_Madde_No",
						FormatType.INTEGER),
				new ReportColumn("fisNo", "Fiş_Numarası", FormatType.TEXT),
				new ReportColumn("anaHesapKodu", "Ana_Hesap_Kodu",
						FormatType.TEXT),
				new ReportColumn("anaHesapAciklama", "Ana_Hesap_Açıklaması",
						FormatType.TEXT),
				new ReportColumn("altHesapKodu", "Alt_Hesap_Kodu",
						FormatType.TEXT),
				new ReportColumn("altHesapAciklama", "Alt_Hesap_Açıklaması",
						FormatType.TEXT),
				new ReportColumn("borc", "Borç", FormatType.FLOAT),
				new ReportColumn("alacak", "Alacak", FormatType.FLOAT),
				new ReportColumn("fisSatirNo", "Fiş_Satır_No",
						FormatType.INTEGER),
				new ReportColumn("dokumanNo", "Doküman_No", FormatType.TEXT),
				new ReportColumn("dokumanTipi", "Doküman_Tipi", FormatType.TEXT),
				new ReportColumn("dokumanTipiAciklama",
						"Doküman_Tipi_Açıklaması", FormatType.TEXT),
				new ReportColumn("dokumanTarihiDate", "Doküman_Tarihi",
						FormatType.DATE),
				new ReportColumn("odemeYontemi", "Ödeme_Yöntemi",
						FormatType.TEXT),
				new ReportColumn("fisDetayAciklamasi", "Fiş_Detay_Açıklaması",
						FormatType.TEXT),
				new ReportColumn("firmaNo", "Firma_No", FormatType.INTEGER),
				new ReportColumn("subeNo", "Şube_No", FormatType.INTEGER),
				new ReportColumn("kaynakReferans", "Kaynak_Referansı",
						FormatType.TEXT) };
	}

}
