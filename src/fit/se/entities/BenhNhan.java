package fit.se.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "benhnhans")
public class BenhNhan implements Serializable{
	@Id
	private String msBN;
	private String soCMND;
	private String hoTen;
	private String diaChi;
	public BenhNhan(String msBN, String soCMND, String hoTen, String diaChi) {
		this.msBN = msBN;
		this.soCMND = soCMND;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
	}
	public BenhNhan(String msBN) {
		this.msBN = msBN;
	}
	public BenhNhan() {
	}
	public String getMsBN() {
		return msBN;
	}
	public void setMsBN(String msBN) {
		this.msBN = msBN;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msBN == null) ? 0 : msBN.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenhNhan other = (BenhNhan) obj;
		if (msBN == null) {
			if (other.msBN != null)
				return false;
		} else if (!msBN.equals(other.msBN))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return msBN;
	}
	
}
