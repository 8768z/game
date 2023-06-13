import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener{
	public static final int MARGIN=30;  //�߾�
	public static final int GRID_SPAN=35; //������
	
	public static final int ROWS=15;
	public static final int COLS=15;
	
	private int x_index,y_index;
	
	private boolean isBlack=true;
	
	private Chess[] chessList=new Chess[(ROWS+1)*(COLS+1)];//װ���ӵ�����
	
	private int chessCount=0;  //���ӵĸ���
	//��Ϸ�Ƿ�����ı�־
private boolean gameOver=false;
	
	public DrawPanel() {
		super();
		this.setBackground(Color.pink);
		this.addMouseListener(this);
		
	}
	@Override   //ע��������ָ��������д�ģ�ֻ�����η�������ֻ�����ڷ�����д����������������Ԫ�ء� ������ǿ��һ�����������д���෽������ʵ�ֽӿڵķ�����
	public void paint(Graphics g){  //����
		super.paint(g);    //���ø��������������ܰ����õĶ�����ʾ����
		
		//������-����
		for(int i=0;i<=ROWS;i++){
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN,MARGIN+i*GRID_SPAN);
		}
		//������-����
		for(int i=0;i<=COLS;i++){
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
		}
		//������
				for(int i=0;i<chessCount;i++){
				int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;
				int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;
g.setColor(chessList[i].getColor());
					g.fillOval(xPos-Chess.DIAMETER/2,yPos-Chess.DIAMETER/2, Chess.DIAMETER, Chess.DIAMETER);
					//���һ�������ϻ���ɫ��
					if(i==chessCount-1){
						g.setColor(Color.red);
						g.drawRect(xPos-Chess.DIAMETER/2, yPos-Chess.DIAMETER/2, Chess.DIAMETER, Chess.DIAMETER);
					}
				}
				
				
				
			}

			@Override
			public Dimension getPreferredSize(){
				return new Dimension(MARGIN*2+GRID_SPAN*ROWS,MARGIN*2+GRID_SPAN*COLS);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				x_index=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				y_index=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				System.out.print("("+x_index+","+y_index+")");
				//�ǲ���һ�����õ�����
				//1.��Ϸ������������
				if(gameOver){
					return;
				}
				//2.�����������棬������
				if(x_index<0||x_index>COLS||y_index>ROWS){
					return;
				}
				//3.λ�����������Ӵ��ڣ�������
				if(findChess(x_index,y_index)){
					return;
				}
				Chess ch=new Chess(x_index,y_index,isBlack?Color.black:Color.white);
				
				chessList[chessCount++]=ch;
				System.out.print("�����Ӹ���: "+chessCount);
				this.repaint();
				//�ж�Ӯ��
				if(isWin()){
					String msg=String.format("��ϲ����%sӮ��", isBlack?"����":"����");
					JOptionPane.showMessageDialog(this, msg);
					gameOver=true;
				}
				isBlack=!isBlack;
				
			}
	@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			//λ�����Ƿ�������
			private boolean findChess(int x,int y){
				for(Chess c:chessList){
					if(c!=null&&c.getX()==x&&c.getY()==y){
						return true;
					}
				}
				return false;
			}
			
			//�õ������ϵ�����
			private Chess getChess(int x,int y,Color color){
				for(Chess c:chessList){
					if(c!=null&&c.getX()==x&&c.getY()==y&&c.getColor()==color){
						return c;
					}
				}
				return null;
			}
			// Ӯ��ķ�������Ҫ�ĸ�����
			private boolean isWin(){
				return search1()||search2()||search3()||search4();
			}
			//1.�����ϵ���������
			private boolean search1(){
				int continueCount=1; //�������ӵĸ���
				//б����Ѱ��
				for(int x=x_index+1,y=y_index-1;x<=COLS&&y>=0;x++,y--){
					Color c=isBlack?Color.black:Color.white;
					if(getChess(x,y,c)!=null){
						continueCount++;
					}else{
						break;
					}
				}
				//б����Ѱ��
for(int x=x_index-1,y=y_index+1;x<=ROWS&&x>=0;x--,y++){
					Color c=isBlack?Color.black:Color.white;
					if(getChess(x,y,c)!=null){
						continueCount++;
					}else{
						break;
					}
				}
				
				//��������
				if(continueCount>=5){
					return true;
				}else{
					continueCount=1;
				}
				return false;
			}
			
			//2.ˮƽ����-����
			private boolean search2(){
				int continueCount=1; //�������ӵĸ���
				//Ѱ��-������
				for(int x=x_index-1;x>=0;x--){
					Color c=isBlack?Color.black:Color.white;
					if(getChess(x,y_index,c)!=null){
						continueCount++;
					}else{
						break;
					}
		        }
				//Ѱ��-������
				for(int x=x_index+1;x<=COLS;x++){
					Color c=isBlack?Color.black:Color.white;
					if(getChess(x,y_index,c)!=null){
						continueCount++;
					}else{
						break;
					}
		        }
				//��������
				if(continueCount>=5){
					return true;
				}else{
					continueCount=1;
				}
				return false;
	  }
	
	//����������
	private boolean search3(){
		int continueCount=1; //�������ӵĸ���
		//б����Ѱ��
		for(int x=x_index-1,y=y_index-1;x>=0&&y>=0;x--,y--){
			Color c=isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}else{
				break;
			}
		}
		//б����Ѱ��
		for(int x=x_index+1,y=y_index+1;x<=COLS&&y<ROWS;x++,y++){
			Color c=isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}else{
				break;
			}
		}
		
		//��������
if(continueCount>=5){
			return true;
		}else{
			continueCount=1;
		}
		return false;
	}
	//��ֱ����
	private boolean search4(){
		int continueCount=1; //�������ӵĸ���
		//Ѱ��-���ϵķ���
		for(int y=y_index-1;y>=0;y--){
			Color c=isBlack?Color.black:Color.white;
			if(getChess(x_index,y,c)!=null){
				continueCount++;
			}else{
				break;
			}
        }
		//Ѱ��-���µķ���
		for(int y=y_index+1;y<=ROWS;y++){
			Color c=isBlack?Color.black:Color.white;
			if(getChess(x_index,y,c)!=null){
				continueCount++;
			}else{
				break;
			}
        }
		//��������
				if(continueCount>=5){
					return true;
				}else{
					continueCount=1;
				}
				return false;
	  }
	
	
	
	
	//���¿�ʼ�ķ���
	public void restartGame(){
		//�������
		for(int i=0;i<chessList.length;i++){
			chessList[i]=null;
		}
		//�ָ���Ϸ��صı���
		isBlack=true;
		gameOver=false;
		chessCount=0;
		
		this.repaint();
	}
	
	//����ķ���
	void goback(){
		//������û�����ӣ����ܻ���
		if(chessCount==0){
			return;
		}
		
		chessList[chessCount-1]=null;
		chessCount--;
		
		if(chessCount>0){
			x_index=chessList[chessCount-1].getX();
			y_index=chessList[chessCount-1].getY();
		}
		isBlack=!isBlack;
		
		this.repaint();
	}
	
	
}
	
	

