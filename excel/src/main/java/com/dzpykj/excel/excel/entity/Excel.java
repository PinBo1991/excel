package com.dzpykj.excel.excel.entity;

public class Excel {
	
	int feiyong;
	String xiangxi;
	int jine;
	
	
	
	public Excel() {
		super();
	}
	public int getFeiyong() {
		return feiyong;
	}
	public void setFeiyong(int feiyong) {
		this.feiyong = feiyong;
	}
	public String getXiangxi() {
		return xiangxi;
	}
	public void setXiangxi(String xiangxi) {
		this.xiangxi = xiangxi;
	}
	public int getJine() {
		return jine;
	}
	public void setJine(int jine) {
		this.jine = jine;
	}
	@Override
	public String toString() {
		return "Excel [feiyong=" + feiyong + ", xiangxi=" + xiangxi + ", jine="
				+ jine + "]";
	}
}
