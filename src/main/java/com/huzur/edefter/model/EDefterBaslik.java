package com.huzur.edefter.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.huzur.utils.excel.ExcelExportable;
import com.huzur.utils.excel.FormatType;
import com.huzur.utils.excel.ReportColumn;
import com.huzur.utils.genel.HuzurNumberUtils;

public class EDefterBaslik implements ExcelExportable {

	private static int baslikSayisi=1;
	private int yevmiyeMaddeNumarasi;
	private String fisNo;
	private Calendar yevmiyeTarihi;
	private String kaydeden;
	private String aciklama;
	private double toplamBorc;
	private double toplamAlacak;
	private int firmaNo=1;
	private int subeNo;
	private String kaynakReferans;
	private List<EDefterDetay> detayList = new ArrayList<EDefterDetay>();
	
	public EDefterBaslik(){
		super();
		this.yevmiyeMaddeNumarasi = baslikSayisi++;
	}
	
	public void setFisNo(int yil, int ay, int gun, int fisNo, String ref){
		this.fisNo = (yil * 10000 + ay * 100 + gun) + "_" + fisNo + "_" + ref;
	}

	public String getFisNo() {
		return fisNo;
	}

	public void setFisNo(String fisNo) {
		this.fisNo = fisNo;
	}
	
	public int getYevmiyeMaddeNumarasi() {
		return yevmiyeMaddeNumarasi;
	}
	
	public void setYevmiyeMaddeNumarasi(int yevmiyeMaddeNumarasi) {
		this.yevmiyeMaddeNumarasi = yevmiyeMaddeNumarasi;
	}

	public Calendar getYevmiyeTarihi() {
		return yevmiyeTarihi;
	}

	public void setYevmiyeTarihi(Calendar yevmiyeTarihi) {
		this.yevmiyeTarihi = yevmiyeTarihi;
	}
	
	public Date getYevmiyeTarihiDate() {
		return yevmiyeTarihi.getTime();
	}

	public String getKaydeden() {
		return kaydeden;
	}

	public void setKaydeden(String kaydeden) {
		this.kaydeden = kaydeden;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public double getToplamBorc() {
		return toplamBorc;
	}

	public void setToplamBorc(double toplamBorc) {
		this.toplamBorc = HuzurNumberUtils.round(toplamBorc,2);
	}

	public double getToplamAlacak() {
		return toplamAlacak;
	}

	public void setToplamAlacak(double toplamAlacak) {
		this.toplamAlacak = HuzurNumberUtils.round(toplamAlacak,2);
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
	
	public List<EDefterDetay> getDetayList() {
		return detayList;
	}
	
	public void setDetayList(List<EDefterDetay> detayList) {
		this.detayList = detayList;
	}
	
	public void ekleDetay(EDefterDetay detay) {
		detay.setFisNo(fisNo);
		detay.setFisSatirNo(detayList.size() + 1);
		detay.setSubeNo(subeNo);
		detay.setFirmaNo(firmaNo);
		detay.setKaynakReferans(kaynakReferans);
		detayList.add(detay);
		setToplamBorc(toplamBorc + detay.getBorc());
		setToplamAlacak(toplamAlacak + detay.getAlacak());
	}

	@Override
	public ReportColumn[] getReportColumns() {
		ReportColumn[] reportColumns = new ReportColumn[] {
				new ReportColumn("yevmiyeMaddeNumarasi", "Yevmiye_Madde_Numarası", FormatType.INTEGER),
				new ReportColumn("fisNo", "Fiş_Numarası", FormatType.TEXT),
				new ReportColumn("yevmiyeTarihiDate", "Yevmiye_Tarihi", FormatType.DATE),
				new ReportColumn("kaydeden", "Fişi_Kaydeden", FormatType.TEXT),
				new ReportColumn("aciklama", "Kayıt_Açıklaması ", FormatType.TEXT),
				new ReportColumn("toplamBorc", "Toplam_Borç", FormatType.FLOAT),
				new ReportColumn("toplamAlacak", "Toplam_Alacak", FormatType.FLOAT),
				new ReportColumn("firmaNo", "Firma_No", FormatType.INTEGER),
				new ReportColumn("subeNo", "Şube_No", FormatType.INTEGER),
				new ReportColumn("kaynakReferans", "Kaynak_Referansı", FormatType.TEXT)
		};
		return reportColumns;
	}
}
