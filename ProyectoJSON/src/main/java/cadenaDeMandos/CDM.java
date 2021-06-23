package cadenaDeMandos;

import java.io.*;
import com.google.gson.stream.JsonReader;

public abstract class CDM {

	protected CDM next;

	public CDM(CDM n) {
		next = n;
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		return next.readCategory(reader, name);
	}

	// Se implementa el metodo plantilla:
	public StringBuffer everyPart(JsonReader reader, String name)
			throws IOException{
		StringBuffer data = new StringBuffer();
		reader.beginArray();
		while(reader.hasNext()) {
			reader.beginObject();
			data.append(readEntry(reader)).append("\n");
			reader.endObject();
		}

		data.append("\n");
		reader.endArray();
		return data;
	}

	public abstract String readEntry(JsonReader reader) throws IOException;

}
