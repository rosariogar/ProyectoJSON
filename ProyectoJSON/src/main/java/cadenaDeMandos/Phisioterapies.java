package cadenaDeMandos;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Phisioterapies extends CDM{

	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEP = "name";

	public Phisioterapies (CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		if(name.equals(PHYSIOTHERAPIES_TAGNAME)) {
			return super.everyPart(reader, name);
		}
		else {
			if(next != null) {
				return super.readCategory(reader, name);
			}
			else {
				reader.skipValue();
				System.err.println("The category: '" + name + "' is wrong.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) 
			throws IOException {
		String phisioName = null;
		String phisioImage = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				phisioName = reader.nextString();
			}
			else if (name.equals(IMAGE_FIELD_TAGNAME)){
				phisioImage = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}

		return phisioName + FIELD_SEP + phisioImage;
	}
}
