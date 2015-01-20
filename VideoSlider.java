import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class VideoSlider extends JSlider implements ChangeListener{
	
	private LabelWavUI ui = null;
	private Clip current_video = null;
	private long video_length = 0;
	
	public VideoSlider (LabelWavUI ui) {
		this.ui = ui;
		addChangeListener(this);
	}
	
	public boolean setVideo(File videoFile) {
		try {
			if (current_video != null) {
				current_video.stop();
				current_video.close();
			}
			AudioInputStream ais = AudioSystem.getAudioInputStream(videoFile);
			AudioFormat af = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(),
					((int)ais.getFrameLength()*af.getFrameSize()));
			video_length = ais.getFrameLength();
			current_video = (Clip) AudioSystem.getLine(info);
			current_video.open(ais);
			setMaximum((int)ais.getFrameLength());
			return true;
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void play(int data_pos) {
		current_video.loop(0);
		current_video.setFramePosition(data_pos);
		current_video.start();
	}
	
	public void loop(int start, int end) {
		current_video.setFramePosition(start);
		current_video.setLoopPoints(start, end-1);
		current_video.loop(Clip.LOOP_CONTINUOUSLY);
		//current_video.loop(2);
	}
	
	public void play() {
		current_video.loop(0);
		current_video.start();
	}
	
	public void stop() {
		current_video.loop(0);
		current_video.stop();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (current_video == null) {
			return;
		}
		JSlider slider = (JSlider) e.getSource();
		int value = slider.getValue();
		int x_pos = (int)(value/(float)slider.getMaximum()*Constant.WAVEPANEL_WIDTH);
		ui.changeIndicatorLine(x_pos);
		int data_pos = (int)(value/(float)slider.getMaximum()*video_length);
		ui.setLocalWaveX(data_pos);
		ui.localWaveRepaint();
		ui.displayCurrentTag(x_pos);
		ui.displayAllTags();
	}
	
	public void playFromNow() {
		if (current_video == null) {
			return;
		}
		current_video.loop(0);
		current_video.setFramePosition((int)((float)getValue() / getMaximum() * video_length));
		current_video.start();
	}
	
	public int getDataPos() {
		return ((int)((float)getValue() / getMaximum() * video_length));
	}
	
	public int getDataLength() {
		return (int)video_length;
	}

}
