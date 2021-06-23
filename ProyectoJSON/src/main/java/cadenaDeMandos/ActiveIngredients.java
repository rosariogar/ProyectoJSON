package cadenaDeMandos;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class ActiveIngredients extends CDM{

	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";

	public ActiveIngredients(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		if(name.equals(ACTINGREF_FIELD_TAGNAME)) {
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
		String actingName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				actingName = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}

		return actingName;
	}
}
