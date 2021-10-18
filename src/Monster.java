import java.util.Random;


public class Monster {
	public static Status status = new Status();
	Random ran=new Random();
	protected String name;
	protected String drop;
	protected int stat;
	
	public Monster(String nm, String dp, int st) {
		name=nm;
		drop=dp;
		stat=st;
	}
	public String getmon(){
		return name;
	}
	public String getdrp() {
		return drop;
	}
	public int getBP(int lv) {
		return stat+(lv*5);
	}
}
