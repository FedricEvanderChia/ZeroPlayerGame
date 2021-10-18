import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
		JFrame frame= new JFrame();
		JButton button=new JButton("Start");	
		JLabel label= new JLabel("Welcome to the game");
		JPanel panel= new JPanel();
		int ctr;
	public GUI() {
		button.addActionListener(this);
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		frame.setSize(500,500);
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("The Lejen Zolda");
		frame.pack();
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(ctr==0)label.setText("uuuuhh.... actually that's all i know about GUI...\r\nbut hey, at least the console worked ");
		if(ctr==1)label.setText("Right?");
		if(ctr==2)label.setText("Yeah, right.... who am i kidding...");
		if(ctr==3)label.setText("i'm really sorry... i mean... i could do it if \r\ni work harder.. but my time's running out..");
		if(ctr==4)label.setText("seriously.. there's nothing here..");
		if(ctr==6)label.setText("gosh.. i really wish i start doing this earlier...");
		if(ctr==8)label.setText("like i told you..seriously.. there's nothing here..");
		if(ctr==15)label.setText("Okay... you're starting to make me feel bad here...\r\nOkay how bout this, why don't you start playing the real game on the console now?");
		if(ctr==20)label.setText(". . .");
		if(ctr==30)label.setText("Wow... im not trying to be rude here.. but, you're kinda presistent aren't cha..");
		if(ctr==33)label.setText("like i told you..seriously.. there's nothing here..");
		if(ctr==50)label.setText("wow.. 50 clicks.. alright i give up...i won't say anthyding anymore..");
		if(ctr==55)label.setText("uuhh.. no... im not doing a typo... what made you thinking like that?");
		if(ctr==56)label.setText("No im not! Stop it... like seriously...");
		if(ctr==57)label.setText("i said STOP IT !");
		if(ctr==58)label.setText("S");
		if(ctr==59)label.setText("ST");
		if(ctr==60)label.setText("STO");
		if(ctr==61)label.setText("STOP");
		if(ctr==62)label.setText("STOP I");
		if(ctr==63)label.setText("STOP IT");
		if(ctr==64)label.setText("STOP IT!");
		if(ctr==65)label.setText("STOP IT!!!");
		if(ctr==70)label.setText("Admin has left the chat...");
		
		ctr++;
	}

}
