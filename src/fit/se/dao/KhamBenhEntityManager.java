package fit.se.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class KhamBenhEntityManager {
	private static KhamBenhEntityManager instance = null;
	private EntityManager em;
	public KhamBenhEntityManager() {
		em = Persistence.createEntityManagerFactory("KTTKPM_DHKTPM13A_TUAN02_DINHDANGKHOA_BAI1").createEntityManager();
	}
	
	public synchronized static KhamBenhEntityManager getInstance() {
		if(instance == null)
			instance = new KhamBenhEntityManager();
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	public static void main(String[] args) {
		new KhamBenhEntityManager();
	}
}
