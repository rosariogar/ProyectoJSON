package cadenaDeMandos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class DatabaseJSonReader {

	CDM cdm;

	public DatabaseJSonReader(CDM e) {
		cdm = e;
	}

	public StringBuffer readCategory(JsonReader reader, String name)
			throws IOException{
		return cdm.readCategory(reader, name);
	}

	public String parse(String jsonFileName)
			throws IOException{
		InputStream userIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(userIS, "UTF-8"));
		reader.beginObject();
		StringBuffer readData = new StringBuffer();

		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(name.toUpperCase()).append("\n").append(readCategory(reader, name)).append("\n");
		}

		reader.endObject();
		reader.close();
		userIS.close();
		return new String(readData);
	}
}
