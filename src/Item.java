import java.util.UUID;

public abstract class Item {

	protected UUID itemId;
	protected String item;
	protected int amount; 

	public abstract int calculate(String item,int in);

	public abstract int sell(int lv);

	protected abstract int wear(String say1, String say2);

	public abstract void fullbodyEquip();
	
}