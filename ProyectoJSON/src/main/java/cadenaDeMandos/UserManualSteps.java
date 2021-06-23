package cadenaDeMandos;

import java.io.*;
import com.google.gson.stream.JsonReader;

public class UserManualSteps extends CDM{

	private static final String USERMANUAL_TAGNAME = "userManualSteps";
	private static final String STEPITLE_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEXT_FIELD_TAGNAME = "stepText";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String FIELD_SEP = ";";

	public UserManualSteps(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(USERMANUAL_TAGNAME)) {
			return super.everyPart(reader, name);
		}

		else {
			if (next != null) {
				return super.readCategory(reader, name);
			} else {
				reader.skipValue();
				System.err.println("La categoria: '" + name + "' es incorrecta.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) throws IOException {
		String titleStep = null;
		String imageStep = null;
		String textStep = null;
		String refInhaler = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPITLE_FIELD_TAGNAME)) {
				titleStep = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_TAGNAME)) {
				imageStep = reader.nextString();
			} else if (name.equals(STEPTEXT_FIELD_TAGNAME)) {
				textStep = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				refInhaler = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		return titleStep + FIELD_SEP + imageStep + FIELD_SEP + textStep + FIELD_SEP + refInhaler;
	}
}
