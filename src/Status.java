import java.util.Random;

public class Status {
	protected int Str;
	protected int Vit;
	protected int Int;
	protected int Dex;
	protected int Agi;
	protected int Luk;
	protected int lvl=1;
	Random ran =new Random();
	
	public void CreateStat() {
		Str=ran.nextInt(15)+1;
		Vit=ran.nextInt(15)+1;
		Int=ran.nextInt(15)+1;
		Dex=ran.nextInt(15)+1;
		Agi=ran.nextInt(15)+1;
		Luk=ran.nextInt(15)+1;
	}
	public void DisplayStat() {
		System.out.println("Lv"+lvl);
		System.out.println("Str : "+Str);
		System.out.println("Vit : "+Vit);
		System.out.println("Int : "+Int);
		System.out.println("Dex : "+Dex);
		System.out.println("Agi : "+Agi);
		System.out.println("Luk : "+Luk);
	}
	public int getlv() {
		return lvl;
	}
	public int getBP() {
		int BattlePower=Str+Vit+Int+Dex+Agi+Luk;
		return BattlePower;
	}
	public void lvlup() {
		lvl++;
		for(int q=0;q<10;q++) {
			int x=0;
			x=ran.nextInt(6);
			if(x==0)addstr(1);
			if(x==1)addvit(1);
			if(x==2)addint(1);
			if(x==3)adddex(1);
			if(x==4)addagi(1);
			if(x==5)addluk(1);
		}
	}
	
	private void addstr(int a) {Str+=a;}	
	private void addvit(int a) {Vit+=a;}
	private void addint(int a) {Int+=a;}	
	private void adddex(int a) {Dex+=a;}	
	private void addagi(int a) {Agi+=a;}
	private void addluk(int a) {Luk+=a;}
	

	
	
}
