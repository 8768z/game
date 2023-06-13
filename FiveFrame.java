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
	
	private JPanel toolBar;  //工具栏里面的三个按钮
	private JButton startButton,backButton,exitButton;
	private DrawPanel drawPanel; //棋盘的面板
	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem,backMenuItem,exitMenuItem;
	
	private MyListener listener;
	 
	public FiveFrame(){
		
	}
	public void init(){
		listener=new MyListener();		
		this.setTitle("单机版五子棋游戏");
		toolBar=new JPanel();
		sysMenu=new JMenu("系统");
		startButton=new JButton("开始");
		startButton.addActionListener(listener);
		
		backButton=new JButton("悔棋");
		backButton.addActionListener(listener);
		
		exitButton=new JButton("退出");
exitButton.addActionListener(listener);
		
		drawPanel=new DrawPanel();
		 
		menuBar=new JMenuBar();
		sysMenu=new JMenu("系统");
		startMenuItem=new JMenuItem("开始");
		startMenuItem.addActionListener(listener);
		
		backMenuItem=new JMenuItem("悔棋");
		backMenuItem.addActionListener(listener);exitMenuItem=new JMenuItem("退出");
		exitMenuItem.addActionListener(listener);
		
		this.setJMenuBar(menuBar);  //设置窗口菜单栏
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
		//窗口出现在界面的中间
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-400,Toolkit.getDefaultToolkit().getScreenSize().height/2-340);
		pack();  //固定棋盘大小的功能
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