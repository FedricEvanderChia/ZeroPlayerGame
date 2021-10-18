
abstract class Quest {
	public abstract String gettarget();
	public abstract void newquest(String mons, int jumlah);
	protected abstract void add();
	public abstract int getdone();
	public abstract void wash();
}
interface killing{
	String complete();
}
interface taking{
	String complete();
}


class Subjugate extends Quest implements killing{
	String target1;
	int kil;
	int done;
public void newquest(String mon,int kill) {
	target1=mon;kil=kill;done=0;
}
public void wash() {
	target1=null;kil=0;done=0;
}
public void add() {done++;}
public String gettarget(){return target1;}
public String complete() {return "Subjugation quest has been completed";}
public int getdone() {return done;}
}



class Collect extends Quest implements taking{
	String target;
	int take,done;

public void newquest(String item,int collect) {
	target=item;take=collect;done=0;
}
public void wash() {
	target=null;take=0;done=0;
}
public String complete() {return "Quest has been completed";}
public String gettarget() {return target;}
public int getdone() {return done;}
protected void add() {done++;}



}
