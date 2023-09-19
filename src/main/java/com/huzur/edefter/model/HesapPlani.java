package com.huzur.edefter.model;

public class HesapPlani {

	private String hesapKodu;
	private String hesapTanim;

	public HesapPlani(String hesapKodu, String hesapTanim) {
		this.hesapKodu = hesapKodu;
		this.hesapTanim = hesapTanim;
	}

	public String getHesapKodu() {
		return hesapKodu;
	}

	public void setHesapKodu(String hesapKodu) {
		this.hesapKodu = hesapKodu;
	}

	public String getHesapTanim() {
		return hesapTanim;
	}

	public void setHesapTanim(String hesapTanim) {
		this.hesapTanim = hesapTanim;
	}

}
