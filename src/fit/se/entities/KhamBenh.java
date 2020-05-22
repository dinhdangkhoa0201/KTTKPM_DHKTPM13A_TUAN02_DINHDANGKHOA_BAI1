package fit.se.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@IdClass(KhamBenhPK.class)
@Table(name = "khambenhs")
public class KhamBenh implements Serializable{
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "msBN")
	private BenhNhan bn;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "msBacSy")
	private BacSy bs;
	private LocalDate ngayKham;
	private String ghiChu;
	public KhamBenh(BenhNhan benhNhan, BacSy bacSy, LocalDate ngayKham, String ghiChu) {
		this.bn = benhNhan;
		this.bs = bacSy;
		this.ngayKham = ngayKham;
		this.ghiChu = ghiChu;
	}
	public KhamBenh() {
	}
	public BenhNhan getBenhNhan() {
		return bn;
	}
	public void setBenhNhan(BenhNhan benhNhan) {
		this.bn = benhNhan;
	}
	public BacSy getBacSy() {
		return bs;
	}
	public void setBacSy(BacSy bacSy) {
		this.bs = bacSy;
	}
	public LocalDate getNgayKham() {
		return ngayKham;
	}
	public void setNgayKham(LocalDate ngayKham) {
		this.ngayKham = ngayKham;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bs == null) ? 0 : bs.hashCode());
		result = prime * result + ((bn == null) ? 0 : bn.hashCode());
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
		KhamBenh other = (KhamBenh) obj;
		if (bs == null) {
			if (other.bs != null)
				return false;
		} else if (!bs.equals(other.bs))
			return false;
		if (bn == null) {
			if (other.bn != null)
				return false;
		} else if (!bn.equals(other.bn))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KhamBenh [benhNhan=" + bn + ", bacSy=" + bs + ", ngayKham=" + ngayKham + ", ghiChu=" + ghiChu
				+ "]";
	}
	
}
