import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FiveFrame extends JFrame{
	public static final int WIDTH=300;
	public static final int HEIGH=400;
	
	private JPanel toolBar;  //�����������������ť
	private JButton startButton,backButton,exitButton;
	private DrawPanel drawPanel; //���̵����
	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem,backMenuItem,exitMenuItem;
	
	private MyListener listener;
	 
	public FiveFrame(){
		
	}
	public void init(){
		listener=new MyListener();		
		this.setTitle("��������������Ϸ");
		toolBar=new JPanel();
		sysMenu=new JMenu("ϵͳ");
		startButton=new JButton("��ʼ");
		startButton.addActionListener(listener);
		
		backButton=new JButton("����");
		backButton.addActionListener(listener);
		
		exitButton=new JButton("�˳�");
exitButton.addActionListener(listener);
		
		drawPanel=new DrawPanel();
		 
		menuBar=new JMenuBar();
		sysMenu=new JMenu("ϵͳ");
		startMenuItem=new JMenuItem("��ʼ");
		startMenuItem.addActionListener(listener);
		
		backMenuItem=new JMenuItem("����");
		backMenuItem.addActionListener(listener);exitMenuItem=new JMenuItem("�˳�");
		exitMenuItem.addActionListener(listener);
		
		this.setJMenuBar(menuBar);  //���ô��ڲ˵���
		menuBar.add(sysMenu);
		sysMenu.add(startMenuItem);
		sysMenu.add(backMenuItem);
		sysMenu.add(exitMenuItem);
		
		toolBar.add(startButton);
		toolBar.add(backButton);
		toolBar.add(exitButton);
		
		this.setLayout(new BorderLayout());
		this.add(toolBar,BorderLayout.NORTH);
		this.add(drawPanel,BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		
		this.setSize(WIDTH,HEIGH);
		//���ڳ����ڽ�����м�
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-400,Toolkit.getDefaultToolkit().getScreenSize().height/2-340);
		pack();  //�̶����̴�С�Ĺ���
		this.setVisible(true);
	}
	private class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==startButton||e.getSource()==startMenuItem){
				drawPanel.restartGame();
			}
			if(e.getSource()==backButton||e.getSource()==backMenuItem){
				drawPanel.goback();
			}
			if(e.getSource()==exitButton||e.getSource()==exitMenuItem){
				System.exit(0);
			}
		}
	}
}