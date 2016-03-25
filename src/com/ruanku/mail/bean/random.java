package  com.ruanku.mail.bean;

import java.util.Random;

public class random {
	public int getInt(){
		
		
		Random rnd=new Random();
		return rnd.nextInt(999999);
	}
	


}
