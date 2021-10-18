

public class Equipment extends Item{

	protected String equipment;
	protected String type;
	protected int Price;
	
	public Equipment(String nm, String tp, int pr) {
		equipment=nm;
		type=tp;
		Price=pr;
	}
	
	public int calculate(String name,int index) {
		return 0;
		}
		
	public void use(int n) {
		
	}
	
	public int getprice(int lv) {
		int y=(Price*lv)/2;
		return y;
	}
	public String getname() {return equipment;}
	public String gettype() {return type;}
	
	
	
	public int sell(int lv) {
		return 0;
	}

	@Override
	protected int wear(String say1, String say2) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void fullbodyEquip() {	
	}
}
