package com.huzur.edefter.model;


public abstract class Tediye extends EDefterAbstract {

	private static final String BASLIK_ACIKLAMA = "TEDİYE";
	public Tediye(String magaza, int ref, int yil, int ay, int gun,
			double brutTutar, int fisNo) {
		super(magaza, ref, yil, ay, gun, brutTutar, fisNo);
	}
	
	public Tediye() {
		super();
	}

	@Override
	public String getBaslikAciklama() {
		return BASLIK_ACIKLAMA;
	}

}
