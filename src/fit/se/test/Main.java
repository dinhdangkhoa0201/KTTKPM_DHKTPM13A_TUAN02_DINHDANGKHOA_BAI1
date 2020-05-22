package fit.se.test;

import fit.se.dao.BacSyDAO;
import fit.se.entities.BacSy;

public class Main {
	public static void main(String[] args) {
		BacSyDAO bacSyDAO = new BacSyDAO();
//		BacSy bacSy1 = new BacSy("001", "DDK", "001");
//		BacSy bacSy2 = new BacSy("002", "DDK", "002");
//		BacSy bacSy3 = new BacSy("003", "DDK", "003");
//		bacSyDAO.them(bacSy1);
//		bacSyDAO.them(bacSy2);
//		bacSyDAO.them(bacSy3);
		
		System.out.println(bacSyDAO.getBacSys());
	}
}

