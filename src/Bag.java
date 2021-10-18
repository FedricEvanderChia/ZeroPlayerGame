import java.util.Random;

public class Bag extends Item{
	int space=30;
	String item[]=new String[30];
	int Earn=0;
	Random ran=new Random();
	protected String weapon;
	protected String garb;
	protected String acsy1;
	protected String acsy2;
	
	public int calculate(String stuff,int none) {
		for(int i=0;i<item.length;i++) {
			if(item[i]==null) {
				item[i]=stuff;
				space--;break;
			}
			
		}
		if(space==0) {System.out.println("Your bag is full");return 1;}
		else{return 0;}
	}
	public int sell(int lv) {
		Earn=0;
		for(int i=0;i<30;i++) {
			space++;
			int prc=lv*5;
			System.out.println("Sold "+item[i]+" for "+prc+" G");
			Earn=Earn+prc;
			item[i]=null;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return Earn;
	}
	public int wear(String ty,String name) {
		if(ty.contentEquals("Weapon")) {weapon=name;}
		if(ty.contentEquals("Garb")) {garb=name;}
		if(ty.contentEquals("Accessory"))
		if(acsy1==null) {
			acsy1=name;
		}
		else {
		if(acsy2==null) {
			acsy2=name;
		}
		else {
		int er=ran.nextInt(2);
		if(er==0) {acsy1=name;}
		else {acsy2=name;}
		}
		}
		return 0;
	}
	public void fullbodyEquip() {
		System.out.println("\r\nEquipment");
		System.out.println("Weapon    : "+weapon);
		System.out.println("Garb      : "+garb);
		System.out.println("Accessory : "+acsy1);
		System.out.println("Accessory : "+acsy2);
	}

	

}
