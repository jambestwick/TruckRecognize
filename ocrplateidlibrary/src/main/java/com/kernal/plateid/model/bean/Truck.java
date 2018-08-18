package com.kernal.plateid.model.bean;

import java.io.Serializable;

public class Truck implements Serializable{
	private int id;
	private String truk_card;
	private int status;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTruk_card() {
		return truk_card;
}
	public void setTruk_card(String truk_card) {
		this.truk_card = truk_card;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Truck [id=" + id + ", truk_card=" + truk_card + ", status=" + status + ", date=" + date + "]";
	}
	
	
	
	

}
