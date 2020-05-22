package fit.se.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class KhamBenhPK implements Serializable{
	private String bn;
	private String bs;
	public KhamBenhPK(String bn, String bs) {
		this.bn = bn;
		this.bs = bs;
	}
	
	public KhamBenhPK() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bn == null) ? 0 : bn.hashCode());
		result = prime * result + ((bs == null) ? 0 : bs.hashCode());
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
		KhamBenhPK other = (KhamBenhPK) obj;
		if (bn == null) {
			if (other.bn != null)
				return false;
		} else if (!bn.equals(other.bn))
			return false;
		if (bs == null) {
			if (other.bs != null)
				return false;
		} else if (!bs.equals(other.bs))
			return false;
		return true;
	}
	public String getBn() {
		return bn;
	}
	public void setBn(String bn) {
		this.bn = bn;
	}
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	@Override
	public String toString() {
		return "KhamBenhPK [bn=" + bn + ", bs=" + bs + "]";
	}
	
	
}
