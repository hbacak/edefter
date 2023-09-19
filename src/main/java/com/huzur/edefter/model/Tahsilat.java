package com.huzur.edefter.model;



public abstract class Tahsilat extends EDefterAbstract {
	
	private static final String BASLIK_ACIKLAMA = "TAHSİLAT";
	public Tahsilat(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
	}
	
	public Tahsilat() {
		super();
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}
	
}
