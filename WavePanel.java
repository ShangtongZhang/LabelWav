import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class WavePanel extends JPanel implements MouseListener{
	private int[] data = null;
	private LabelWavUI ui = null;
	
	public WavePanel(LabelWavUI ui) {
		this.ui = ui;
		this.addMouseListener(this);
	}
	
	public int getWavSize() {
		if (data != null) {
			return data.length;
		}
		return -1;
	}
	
	public void setVideo(int[] video_data ) {
		data = video_data;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (data == null) {
			return;
		}
		int ww = this.getWidth();
		int hh = this.getHeight();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ww, hh);
		
		int len = data.length;
		float step = (float)len/(float)ww;
		float max_a = 0;
		for (int i=0 ; i<len ; ++i) {
			if (Math.abs(data[i]) > max_a) {
				max_a = Math.abs(data[i]);
			}
		}
		if(step==0)
			step = 1;
		
		int prex = 0, prey = 0;	
		int x = 0, y = 0;
		
		g.setColor(Color.BLUE);
		double k = hh/2.0/max_a;
		for(int i=0; i<ww; ++i){
			x = i;
			
			y = hh/2+(int)(data[(int)(i*step)]*k);
			
			if(i!=0){
				g.drawLine(x, y, prex, prey);
			}
			prex = x;
			prey = y;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			ui.displayCurrentTag(e.getX());
		} else {
			int data_pos = (int)((float)e.getX() / Constant.WAVEPANEL_WIDTH * data.length);
			ui.setLocalWaveX(data_pos);
			ui.setSliderPos(data_pos);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
