package phillykeyspots.frpapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * XmlParser is a general xml parser that parse the input stream for the 
 * keyspots and the events.
 * 
 * @author btopportal
 */
public class XmlParser {
private static final String ns = null;
	/**
	 * instantiates a parser on events that have been gotten back
	 * @param in - the input stream
	 * @see Entry
	 * @return readFeed() - the method that goes through the xml tags getting the text they surround
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public List<Entry> parse(InputStream in) throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			return readFeed(parser);
		} finally {
			in.close();
		}
	}
	
	/**
	 * Goes through the different tags, using "nodes" and the starting point and "<node>" to "</node>" to
	 * as a single event. 
	 * 
	 * @author btopportal 
	 * @param parser - the parser for the input stream that was initiated earlier
	 * @return entries - the parsed xml, with key and value pairs
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private List<Entry> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		List<Entry> entries = new ArrayList<Entry>();
		
		parser.require(XmlPullParser.START_TAG, ns, "nodes");
		while (parser.next() != XmlPullParser.END_TAG){
			if(parser.getEventType() != XmlPullParser.START_TAG){
				continue;
			}
			String name = parser.getName();
			/**
			 * Starts by looking for the node tag which
			 * marks the start of a single event
			 */
			if (name.equals("node")){
				entries.add(readEntry(parser));
			} else {
				skip(parser);
			}
		}
		return entries;
	}
	
	/**
	 * The Entry class's attributes correspond to information about an 
	 * event such as it's location, date, title etc,
	 * 
	 * @author btopportal
	 */
	public static class Entry {
		
		public final String type;
		public final String topics;
		public final String title;
		public final String training_dates;
		public final String street;
		public final String city;
		public final String province;
		public final String postal_code;
		public final String keyspot;
		public final String managing_partner;
		public final String email;
		public final String contact;
		public final String level;
		public final String more_info;
		public final String latitude;
		public final String longitude;
		public final String hours;
		public final String workstations;
		public final String restrictions;
		public final String wi_fi;
		
		private Entry(String type,String topics,String title,String training_dates,String street,String city,String province,String postal_code,String keyspot,String managing_partner,String email,String contact,String level,String more_info,String latitude,String longitude, String hours, String workstations, String restrictions, String wi_fi ){ 
			this.type = type;
			this.topics = topics;
			this.title = title;
			this.training_dates = training_dates;
			this.street = street;
			this.city = city;
			this.province = province;
			this.postal_code = postal_code;
			this.keyspot = keyspot;
			this.managing_partner = managing_partner;
			this.email = email;
			this.contact = contact;
			this.level = level;
			this.more_info = more_info;
			this.latitude = latitude;
			this.longitude = longitude;
			this.restrictions = restrictions;
			this.workstations = workstations;
			this.wi_fi = wi_fi;
			this.hours = hours;
		}
	}
	/**
	 * reads the specified tag and calls the method to get the tags innertext
	 * if a tag is not specified the parser skips it and moves on to the next tag
	 * 
	 * @author btopportal
	 * @param parser - the parser for the input stream
	 * @see XmlParser#readTag(XmlPullParser, String)
	 * @return Entry() - an event object with it's attributes
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "node");
		String type = null;
		String topics = null;
		String title = null;
		String training_dates = null;
		String street = null;
		String city = null;
		String province = null;
		String postal_code = null;
		String keyspot = null;
		String managing_partner = null;
		String email = null;
		String contact = null;
		String level = null;
		String more_info = null;
		String latitude = null;
		String longitude = null;
		String restrictions = null;
		String workstations = null;
		String hours = null;
		String wi_fi = null;
		
		while (parser.next() != XmlPullParser.END_TAG){
			if (parser.getEventType() != XmlPullParser.START_TAG){
				continue;
			}
			String name = parser.getName();
			if (name.equals("type")){
				type = readTag(parser,"type");
			} else if (name.equals("topics")){
				topics = readTag(parser,"topics");
			} else if (name.equals("title")){
				title = readTag(parser,"title");
			} else if (name.equals("training_dates")){
				training_dates = readTag(parser,"training_dates");
			} else if (name.equals("street")){
				street = readTag(parser,"street");
			} else if (name.equals("city")){
				city = readTag(parser,"city");
			} else if (name.equals("province")){
				province = readTag(parser,"province");
			} else if (name.equals("postal_code")){
				postal_code = readTag(parser,"postal_code");
			} else if (name.equals("keyspot")){
				keyspot = readTag(parser,"keyspot");
			} else if (name.equals("managing_partner")){
				managing_partner = readTag(parser,"managing_partner");
			} else if (name.equals("email")){
				email = readTag(parser,"email");
			} else if (name.equals("contact")){
				contact = readTag(parser,"contact");
			} else if (name.equals("level")){
				level = readTag(parser,"level");
			} else if (name.equals("more_info")){
				more_info = readTag(parser,"more_info");
			} else if (name.equals("latitude")){
				latitude = readTag(parser,"latitude");
			} else if (name.equals("longitude")){
				longitude = readTag(parser,"longitude");
			} else if (name.equals("hours")){
				hours = readTag(parser,"hours");
			} else if (name.equals("restrictions")){
				restrictions = readTag(parser,"restrictions");
			} else if (name.equals("workstations")){
				workstations = readTag(parser,"workstations");
			} else if (name.equals("hours")){
				hours = readTag(parser,"hours");
			} else if (name.equals("wi_fi")){
				wi_fi = readTag(parser,"wi_fi");
			} else {
				skip(parser);
			}
		}
		return new Entry(type,topics,title,training_dates,street,city,province,postal_code,keyspot,managing_partner,email,contact,level,more_info,latitude,longitude,hours,restrictions,workstations,wi_fi);
	}
	
	/**
	 * Read the tag then read its inner text
	 * 
	 * @author btopportal
	 * @param parser - the parser for the input stream
	 * @param tag - the current tag that the parser is reading
	 * @see XmlParser#readText(XmlPullParser)
	 * @return tag_text - a string containing the tags's innertext
	 * @throws IOException
	 * @throws XmlPullParserException
	 */

	private String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String tag_text = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return tag_text;
	}
	
	/**
	 * Reads the text between the tags then advances parser to the next tag
	 * 
	 * @author btopportal
	 * @param parser - the input stream parser
	 * @return xmlresult - the innertext
	 * @throws IOException
	 * @throws XmlPullParserException
	 */

	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String xmlresult = "";
		if(parser.next() == XmlPullParser.TEXT){
			xmlresult = parser.getText();
			parser.nextTag();
		}
		return xmlresult;
	}
	
	/**
	 * Skips the un-required tags and advances the parser to the next tag
	 * 
	 * @author btopportal
	 * @param parser - the input stream parser
	 * @throws IOException
	 * @throws XmlPullParserException
	 */

	private void skip(XmlPullParser parser) throws IOException, XmlPullParserException {
		if (parser.getEventType() != XmlPullParser.START_TAG){
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0){
			switch(parser.next()){
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth--;
				break;		
			}
		}
	}
}
