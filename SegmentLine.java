import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SegmentLine extends JLabel implements MouseListener{
	
	private LabelWavUI ui = null;
	private int x_pos = 0;
	private int data_pos = 0;
	private int data_length = 0;
	
	public SegmentLine(int data_pos, int data_length, LabelWavUI ui) {
		super();
		this.data_length = data_length;
		this.data_pos = data_pos;
		setBackground(Color.RED);
		setOpaque(true);
		x_pos = (int)((float)data_pos/data_length*Constant.WAVEPANEL_WIDTH);
		setBounds(x_pos, 1, Constant.SEGMENTLINE_WIDTH, Constant.SEGMENTLINE_HEIGHT);
		setVisible(true);
		this.ui = ui;
		this.addMouseListener(this);
	}
	
	public SegmentLine(int data_pos, int local_data_length, int start_data_pos, int y_pos, LabelWavUI ui) {
		super();
		this.data_pos = data_pos;
		setBackground(Color.RED);
		setOpaque(true);
		x_pos = (int)((float)(data_pos-start_data_pos)/local_data_length*Constant.WAVEPANEL_WIDTH);
		setBounds(x_pos, y_pos, Constant.SEGMENTLINE_WIDTH, Constant.SEGMENTLINE_HEIGHT);
		setVisible(true);
		this.ui = ui;
		this.addMouseListener(this);
	}

	public Integer getX_pos() {
		return x_pos;
	}
	
	public Integer getDataPos() {
		return data_pos;
	}
	
	public Integer getDataLength() {
		return data_length;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			ui.removeSegmentLine(this);
		} else {
			
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
