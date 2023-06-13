

import java.awt.Color;

public class Chess {
    private int x; //������x������ֵ
    private int y;
    private Color color;//������ɫ
    
    static final int DIAMETER=30;
    
    public Chess(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
    
}