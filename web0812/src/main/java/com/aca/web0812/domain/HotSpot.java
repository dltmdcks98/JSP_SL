package com.aca.web0812.domain;

//로직 작성용이 아닌 오직 데이터를 담아서 전달하기 위한 객체
public class HotSpot {
	private int hotspot;
	private float lati;
	private float longi;
	private String icon;
	private String content;
	public int getHotspot_id() {
		return hotspot;
	}
	public void setHotspot_id(int hotspot_id) {
		this.hotspot = hotspot;
	}
	public float getLati() {
		return lati;
	}
	public void setLati(float lati) {
		this.lati = lati;
	}
	public float getLongi() {
		return longi;
	}
	public void setLongi(float longi) {
		this.longi = longi;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
