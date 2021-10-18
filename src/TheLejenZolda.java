import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheLejenZolda {

	static String name;
	static String job;
	Scanner scan = new Scanner(System.in);
	static Random ran=new Random();
	static int gold = 1000;
	static int exp =0,limit=200;	
	static Vector<Monster> mon = new Vector<>();
	private static Item bag = new Bag();
	static Vector<Equipment> equip = new Vector<>();	
	public static Status stat = new Status();
	public static Quest sub = new Subjugate();
	public static Quest col = new Collect();
	static Lock lock = new ReentrantLock();   
	static Condition killcount = lock.newCondition();
	
	public TheLejenZolda () {
		
		listmon();
		listeqp();
		welcomeMenu();

	}

	public String work(int nm){
		String st = null;
		if(nm==1)st="Mage";
		if(nm==2)st="Knight";
		if(nm==3)st="Thief";
		if(nm==4)st="Archer";
		return st;
	}
	private void welcomeMenu() {
		System.out.println("Welcome adventurer! Please let me know your name :");
		name=scan.nextLine();
		int jb;
		do {
			System.out.println("Mr. "+name+" may i know what is your job?");
			System.out.println("1. Mage");
			System.out.println("2. Knight");
			System.out.println("3. Thief");
			System.out.println("4. Archer");
			jb=scan.nextInt();
		}while(jb<1&&jb>4);
		job=work(jb);
		System.out.println("Please place your hand on the magic orb so we can view your status...");
		stat.CreateStat();
		stat.DisplayStat();
		System.out.println("You have been registered to the Adventurer guild as an E rank adventurer.\r\nComplete more quest to rank up and unlock more quest with better reward");		
		System.out.println("\r\nPress enter to continue...");
		String u=scan.nextLine();
		u=scan.nextLine();
		System.out.println("And so your journey begins...");
	}
	
	public static void main(String[] args) {
		new GUI();
		new TheLejenZolda();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new questing());
		executor.execute(new adventuring());
		executor.shutdown();	
		
	}
	public static class questing implements Runnable {
		public void run(){

				while(true) {
					int questype=ran.nextInt(2);
					int jumlah=ran.nextInt(10)+1;
					int p=ran.nextInt(mon.size());
					String mons=mon.get(p).getmon();
					String obj=mon.get(p).getdrp();
					System.out.println("\r\nYou've picked a quest");
					if(questype==0) {
						System.out.println("Subjugate "+jumlah+" "+mons);
						sub.newquest(mons,jumlah);
					while(sub.getdone()<jumlah) {
						lock.lock();
						try {
							
							killcount.await();
							System.out.println("Quest Progress : "+sub.getdone()+"/"+jumlah+" "+mons);
							System.out.println("");
							
							
							if(sub.getdone()==jumlah)break;
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							lock.unlock();
						}
						}
						System.out.println("Quest Complete !");
						int gain=stat.getlv()*jumlah*100;
						exp=exp+gain;
						System.out.println("You recieved "+gain+" EXP");
						sub.wash();
					}
					if(questype==1) {
						System.out.println("Collect "+jumlah+" "+obj);
						col.newquest(obj,jumlah);
					while(col.getdone()<jumlah) {
						lock.lock();
						try {
								killcount.await();
						
							System.out.println("Quest Progress : "+col.getdone()+"/"+jumlah+" "+obj);
							System.out.println("");
							if(col.getdone()==jumlah)break;
						
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							lock.unlock();
						}
						}	
						
						System.out.println("Quest Complete !");
						int gain=stat.getlv()*jumlah*100;
						gold=gold+gain;
						System.out.println("You recieved "+gain+"G");
						col.wash();
					}
				}	
		}
	}
	public static class adventuring implements Runnable {
		public void run(){
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				
			}
			while(true) {
			if(exp>=limit) {	
			stat.lvlup();
			System.out.println("LEVEL UP !");
			System.out.println(name+"/"+job);
			exp=exp-limit;
			limit=limit+(limit/2);
			System.out.println(exp+"/"+limit+" EXP");
			stat.DisplayStat();
			}		
			
			int y=ran.nextInt(mon.size());
			String ham=mon.get(y).getmon();
			System.out.println("You encounter a/n "+ham);
			int OverPowerness=stat.getBP()-mon.get(y).getBP(stat.getlv());
			if(OverPowerness>100) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(OverPowerness>50) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(OverPowerness>20) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("You get a/n "+mon.get(y).getdrp());
			int hg=bag.calculate(mon.get(y).getdrp(),0);
			
			System.out.println("You gain "+OverPowerness+" EXP\r\n");
			exp=exp+OverPowerness;
			if(col.gettarget()!=null) {
				if(col.gettarget().equals(mon.get(y).getdrp())){
					col.add();
				signal();
				}
			}
			else if(sub.gettarget()!=null){
				if(sub.gettarget().equals(mon.get(y).getmon())){
					sub.add();
					signal();
				}
			}
				
			if(hg==1) {
				System.out.println("Your bag is full");
				gold=gold+bag.sell(stat.getlv());
				System.out.println("Gold : "+gold);
				System.out.println("Buying a better equipment . . .");
				int iai,ctr;
				ctr=7;
				for(int qw=0;qw<7;qw++) {
				if(gold > equip.get(qw).getprice(stat.getlv())) {
				do {
					iai=ran.nextInt(7);
				} while (gold < equip.get(iai).getprice(stat.getlv()));
				String say1=equip.get(iai).gettype();
				String say2=equip.get(iai).getname();
				int say3=equip.get(iai).getprice(stat.getlv());
				gold=gold-say3;
				System.out.println("You bought a/n "+say1+ "("+say2+"lv"+stat.getlv()+") for "+say3+"G");
				bag.wear(say1,say2);
				bag.fullbodyEquip();
				break;
				}
				else {ctr--;}
				} if(ctr==0){System.out.println("Not enough gold . . .");}
				
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			

		}

		private void signal() {
			lock.lock();
			try {
					killcount.signalAll();
			} finally {
				lock.unlock();
			}
			
		}
	}
	
	private void listmon() {
		int initial=20+(stat.getlv()*5);
		mon.add(new Monster("Slime", "Sticky goo", ran.nextInt(initial)));
		mon.add(new Monster("Goblin", "Goblin ear", ran.nextInt(initial)));
		mon.add(new Monster("Serpent", "Poison essence", ran.nextInt(initial)));
		mon.add(new Monster("Bandit", "Old knive", ran.nextInt(initial)));
		mon.add(new Monster("Ghost", "Dark soul", ran.nextInt(initial)));
		mon.add(new Monster("Dullahan", "Bloody helmet", ran.nextInt(initial)));
		mon.add(new Monster("Death Knight", "Rusty sword", ran.nextInt(initial)));
		mon.add(new Monster("Wyvern", "Wyvern scale", ran.nextInt(initial)));
		mon.add(new Monster("Jack'o Lantern", "Pumpkin", ran.nextInt(initial)));
	}
	private void listeqp() {
		equip.add(new Equipment("Staff*","Weapon",250));
		equip.add(new Equipment("Sword*","Weapon",250));
		equip.add(new Equipment("Dagger*","Weapon",250));
		equip.add(new Equipment("Bow*","Weapon",250));
		equip.add(new Equipment("Armor*","Garb",800));
		equip.add(new Equipment("Ring*","Accessory",500));
		equip.add(new Equipment("Necklace*","Accessory",450));
	}



}














