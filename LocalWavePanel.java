import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class LocalWavePanel extends JPanel implements MouseListener{
	
	private int[] global_data = null;
	private int[] local_data = null;
	private int cur_x = 0;
	private int start_pos = 0;
	private int local_data_size = 0;
	private LabelWavUI ui = null;
	
	public int get_cur_x() {
		return cur_x;
	}
	
	public int get_start_data_pos() {
		return start_pos;
	}
	
	public int get_end_data_pos() {
		return start_pos + local_data_size;
	}
	
	public int get_local_data_length() {
		return local_data_size;
	}
	
	public LocalWavePanel(LabelWavUI ui) {
		this.ui = ui;
		this.addMouseListener(this);
	}
	
	public void setVideo(int[] video_data) {
		global_data = video_data;
		local_data_size = this.getWidth() * Constant.DATAPOINT_PER_PIXEL;
		local_data = new int[local_data_size];
	}
	
	public void setX(int cur_x) {
		this.cur_x = cur_x;
		this.start_pos = cur_x - local_data_size/2;
		if (cur_x <= local_data_size/2) {
			start_pos = 0;
		} else if (cur_x >= global_data.length - local_data_size/2) {
			start_pos = global_data.length - local_data_size;
		}
		
		for (int i = 0; i < local_data_size; i++) {
			local_data[i] = global_data[start_pos + i];
		}
		ui.changeLocalIndiLine((int)( (float)(cur_x-start_pos) / local_data_size * Constant.WAVEPANEL_WIDTH) );
		repaint();
		validate();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (local_data == null) {
			return;
		}
		int ww = this.getWidth();
		int hh = this.getHeight();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, ww, hh);

		float step = Constant.DATAPOINT_PER_PIXEL;
		float max_a = 0;
		for (int i=0 ; i<local_data_size ; ++i) {
			if (Math.abs(local_data[i]) > max_a) {
				max_a = Math.abs(local_data[i]);
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
			
			y = hh/2+(int)(local_data[(int)(i*step)]*k);
			
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
			
		} else {
			ui.addSegmentLine(new SegmentLine(e.getX()*Constant.DATAPOINT_PER_PIXEL + start_pos, global_data.length, ui));
			ui.localWaveRepaint();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

}
