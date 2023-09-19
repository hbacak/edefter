package com.huzur.edefter.model;

import java.util.Calendar;
import java.util.List;

import com.huzur.utils.genel.HuzurDateUtils;
import com.huzur.utils.genel.HuzurNumberUtils;

public abstract class EDefterAbstract {

	public static final String BOS_STRING = "";
	private String magaza;
	private String ref;
	private int yil;
	private int ay;
	private int gun;
	private double brutTutar;
	private double oran1;
	private double oran8;
	private double oran18;
	private int fisNo;
	public static String kaydeden = "YALÇIN KAYA";
	public static final String DOKUMAN_VOUCHER = "voucher";
	public static final String DOKUMAN_CHECK = "check";
	public static final String DOKUMAN_INVOICE = "invoice";
	public static final String DOKUMAN_RECEIPT = "receipt";
	public static final String DOKUMAN_OTHER = "other";
	
	

	public EDefterAbstract(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo) {
		this.magaza = magaza;
		this.ref = Integer.toString(ref);
		this.yil = yil;
		this.ay = ay;
		this.gun = gun;
		this.brutTutar = brutTutar;
		this.fisNo = fisNo;
	}

	public EDefterAbstract() {
		super();
	}

	public String getMagaza() {
		return magaza;
	}

	public void setMagaza(String magaza) {
		this.magaza = magaza;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public void setRef(int ref) {
		this.ref = Integer.toString(ref);
	}

	public int getYil() {
		return yil;
	}

	public void setYil(int yil) {
		this.yil = yil;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getGun() {
		return gun;
	}

	public void setGun(int gun) {
		this.gun = gun;
	}

	public int getFisNo() {
		return fisNo;
	}

	public void setFisNo(int fisNo) {
		this.fisNo = fisNo;
	}
	
	public double getOran1() {
		return oran1;
	}

	public void setOran1(double oran1) {
		this.oran1 = oran1;
	}
	
	public void setOran1(double tutar1, double brutTutar) {
		if(brutTutar == 0)
			this.oran1 = 0;
		else
			this.oran1 = tutar1 / brutTutar;
	}

	public double getOran8() {
		return oran8;
	}

	public void setOran8(double oran8) {
		this.oran8 = oran8;
	}
	
	public void setOran8(double tutar8, double brutTutar) {
		if(brutTutar == 0)
			this.oran8 = 0;
		else
			this.oran8 = tutar8 / brutTutar;
	}

	public double getOran18() {
		return oran18;
	}

	public void setOran18(double oran18) {
		this.oran18 = oran18;
	}
	
	public void setOran18(double tutar18, double brutTutar) {
		if(brutTutar == 0)
			this.oran18 = 0;
		else
			this.oran18 = tutar18 / brutTutar;
	}

	public double getBrutTutar() {
		return brutTutar;
	}

	public void setBrutTutar(double brutTutar) {
		this.brutTutar = brutTutar;
	}
	
	private double getBrutTutar1(){
		return HuzurNumberUtils.round(getBrutTutar() * getOran1(), 2);
	}

	public double getNetTutar1() {
		return HuzurNumberUtils.round(getBrutTutar1() / 1.01, 2);
	}

	public double getKdv1() {
		return HuzurNumberUtils.round(getBrutTutar1() - getNetTutar1(), 2);
	}
	
	private double getBrutTutar8(){
		return HuzurNumberUtils.round(getBrutTutar() * getOran8(), 2);
	}

	public double getNetTutar8() {
		return HuzurNumberUtils.round(getBrutTutar8() / 1.08, 2);
	}

	public double getKdv8() {
		return HuzurNumberUtils.round(getBrutTutar8() - getNetTutar8(), 2);
	}

	private double getBrutTutar18(){
		return HuzurNumberUtils.round(getBrutTutar() - getBrutTutar8() - getBrutTutar1(), 2);
	}	
	
	public double getNetTutar18() {
		return HuzurNumberUtils.round(getBrutTutar18() / 1.18, 2);
	}

	public double getKdv18() {
		return HuzurNumberUtils.round(getBrutTutar18() - getNetTutar18(), 2);
	}

	public Calendar getTarih() {
		return HuzurDateUtils.toCalendar(yil, ay, gun);
	}

	public boolean isAyniFis(EDefterAbstract diger) {
		return equals(diger);
	}

	public EDefterBaslik toEDefterBaslik() {
		EDefterBaslik baslik = new EDefterBaslik();
		baslik.setFisNo(yil, ay, gun, fisNo, ref);
		baslik.setKaydeden(kaydeden);
		try{
			baslik.setSubeNo(Integer.parseInt(magaza));
			baslik.setYevmiyeTarihi(HuzurDateUtils.toCalendar(yil, ay, gun));
		} catch(Exception e){
			
			System.err.println("edefter baslik olustururken hata, ref= " + ref);
			System.err.printf("magaza: %s, yil: %d, ay: %d, gun: %d", magaza, yil, ay, gun);
			throw new NumberFormatException();
		}
		baslik.setAciklama(getBaslikAciklama());
		return baslik;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + yil;
		result = prime * result + ay;
		result = prime * result + gun;
		result = prime * result + fisNo;
		result = prime * result + ((magaza == null) ? 0 : magaza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		/*if (getClass() != obj.getClass())
			return false;*/
		EDefterAbstract other = (EDefterAbstract) obj;
		if (yil != other.yil)
			return false;
		if (ay != other.ay)
			return false;
		if (gun != other.gun)
			return false;
		if (fisNo != other.fisNo)
			return false;
		if (magaza == null) {
			if (other.magaza != null)
				return false;
		} else if (!magaza.equals(other.magaza))
			return false;
		if(!ref.equals(other.ref))
			return false;
		return true;
	}
	
	public abstract String getBorcHesap();
	
	public abstract String getAlacakHesap();

	public abstract String getAlacakHesapNet(int kdvOrani);

	public abstract String getAlacakHesapKDV(int kdvOrani);

	public abstract List<EDefterDetay> toEDefterDetay();

	public abstract String getOdemeYontemi();
	
	public abstract String getDokumanTipi();
	
	public abstract String getBaslikAciklama();
	
	public abstract String getDetayAciklama();

}