package cadenaDeMandos;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualsPhisioSteps extends CDM{

	private static final String USERMANUALPHISIOSTEPS_TAGNAME = "userManualPhysioSteps";
	private static final String STEPTITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String PHYREF_FIELD_TAGNAME = "physioRef";
	private static final String FIELD_SEPARATOR = ";";

	public UserManualsPhisioSteps(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(USERMANUALPHISIOSTEPS_TAGNAME)) {
			return super.everyPart(reader, name);
		}
		else {
			if (next != null) {
				return super.readCategory(reader, name);
			} else {
				reader.skipValue();
				System.err.println("The category: '" + name + "' is wrong.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) throws IOException {
		String titleStep = null;
		String imageStep = null;
		String textStep = null;
		String refPhisio = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_TAGNAME)) {
				titleStep = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				imageStep = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				textStep = reader.nextString();
			} else if (name.equals(PHYREF_FIELD_TAGNAME)) {
				refPhisio = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return titleStep + FIELD_SEPARATOR + imageStep + FIELD_SEPARATOR + textStep + FIELD_SEPARATOR + refPhisio;
	}

}
