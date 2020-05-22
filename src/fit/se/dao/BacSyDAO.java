package fit.se.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fit.se.entities.BacSy;

public class BacSyDAO {
	private EntityManager em;
	public BacSyDAO() {
		em = KhamBenhEntityManager.getInstance().getEntityManager();
	}
	public boolean them(BacSy bacSy) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(bacSy);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public BacSy getBacSy(String maBS) {
		return em.find(BacSy.class, maBS);
	}
	
	public List<BacSy> getBacSys(){
		List<BacSy> list = new ArrayList<BacSy>();
		List<?> temp = em.createNativeQuery("db.bacsys.find({})", BacSy.class).getResultList();
		temp.forEach(x -> {
			BacSy bacSy = (BacSy) x;
			list.add(bacSy);
		});
		return list;
	}
}
