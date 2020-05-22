package fit.se.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fit.se.entities.BenhNhan;

public class BenhNhanDAO {
	private EntityManager em;
	public BenhNhanDAO() {
		em = KhamBenhEntityManager.getInstance().getEntityManager();
	}
	
	public boolean them(BenhNhan benhNhan) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(benhNhan);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false; 
	}
	
	public BenhNhan getBenhNhan(String msbn) {
		return em.find(BenhNhan.class, msbn);
	}
	
	public List<BenhNhan> getBenhNhans(){
		List<BenhNhan> list = new ArrayList<BenhNhan>();
		List<?> temp = em.createNativeQuery("db.benhnhans.find({})", BenhNhan.class).getResultList();
		temp.forEach(x -> {
			BenhNhan benhNhan = (BenhNhan) x;
			list.add(benhNhan);
		});
		return list;
	}
}
