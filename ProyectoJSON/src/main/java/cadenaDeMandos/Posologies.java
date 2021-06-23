package cadenaDeMandos;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Posologies extends CDM{

	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIP_FIELD_TAGNAME = "description";

	public Posologies(CDM s) {
		super(s);		
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		if (name.equals(POSOLOGIES_TAGNAME)) {
			return super.everyPart(reader, name);
		}
		else {
			if (next != null) {
				return super.readCategory(reader, name);
			}
			else {
				reader.skipValue();
				System.err.println("The category: '" + name + "' is wrong.");
				return new StringBuffer("");
			}
		}
	}

	public String readEntry(JsonReader reader) throws IOException {
		String posoName = null;
		while(reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(DESCRIP_FIELD_TAGNAME)) {
				posoName = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}
		return posoName;
	}

}
