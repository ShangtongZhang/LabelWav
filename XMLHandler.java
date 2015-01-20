import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;


public class XMLHandler {
	
	private Description description = null;	
	private XStream stream;
	
	public XMLHandler() {
		stream = new XStream();
		stream.alias("speaker", Speaker.class);
		stream.alias("microphone", Microphone.class);
		stream.alias("hardware", Hardware.class);
		stream.alias("software", Software.class);
		stream.alias("description", Description.class);
	}
	
	public void readXML(String xml_file) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(xml_file));
			String raw_xml = "";
			String line;
			while ((line = bf.readLine()) != null) {
				raw_xml += line + '\n';
			}
			raw_xml = raw_xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
			bf.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(xml_file));
			bw.write(raw_xml);
			bw.close();
			InputStreamReader inXml = new InputStreamReader(new FileInputStream(xml_file), "UTF-8");
			description = (Description)stream.fromXML(inXml);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public Description getDescription() {
		return description;
	}
	
	public String toXML(Description d) {
		return stream.toXML(d);
	}
}

class Speaker {
	public String surname = null;
	public String firstname = null;
	public String gender = null;
	public String age = null;
	public String birthplace = null;
	public String dialect = null;
	public String education = null;
}

class Microphone {
	public String brand = null;
	public String model = null;
	public String type = null;
	public String directivity = null;
	public String sensitivity = null;
	public String range = null;
}

class Hardware {
	public String device = null;
	public Microphone microphone = null;
}

class Software {
	public String tool = null;
	public String version = null;
}

class Description {
	public String file = null;
	public String sentence = null;
	public String pronunciation = null;
	public String tag = null;
	public String segment_poses = null;
	public Speaker speaker = null;
	public String datetime = null;
	public String location = null;
	public String surrounding = null;
	public Hardware hardware = null;
	public Software software = null;
	public String frequency = null;
	public String lowfrequency = null;
	public String highfrequency = null;
	public String encoding = null;
	public String bit = null;
	public String channel = null;
	public String noise = null;
	public String format = null;
	
}