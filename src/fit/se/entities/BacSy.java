package fit.se.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bacsys")
public class BacSy implements Serializable{
	@Id
	private String msBacSy;
	private String hotenBacSy;
	private String pwd;
	
	public BacSy(String msBacSy, String hotenBacSy, String pwd) {
		this.msBacSy = msBacSy;
		this.hotenBacSy = hotenBacSy;
		this.pwd = pwd;
	}
	public BacSy(String msBacSy) {
		this.msBacSy = msBacSy;
	}
	public BacSy() {
	}
	public String getMsBacSy() {
		return msBacSy;
	}
	public void setMsBacSy(String msBacSy) {
		this.msBacSy = msBacSy;
	}
	public String getHotenBacSy() {
		return hotenBacSy;
	}
	public void setHotenBacSy(String hotenBacSy) {
		this.hotenBacSy = hotenBacSy;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msBacSy == null) ? 0 : msBacSy.hashCode());
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
		BacSy other = (BacSy) obj;
		if (msBacSy == null) {
			if (other.msBacSy != null)
				return false;
		} else if (!msBacSy.equals(other.msBacSy))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BacSy [msBacSy=" + msBacSy + ", hotenBacSy=" + hotenBacSy + ", pwd=" + pwd + "]";
	}
	
}
