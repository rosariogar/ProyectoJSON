package cadenaDeMandos;

import java.io.*;

import com.google.gson.stream.JsonReader;
public class Medicine extends CDM{

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public Medicine(CDM s) {
		super(s);
	}

	//Leemos el fichero .json 
	public StringBuffer readCategory(JsonReader reader, String name) 
			throws IOException{
		// si el nombre que le pasamos al buffer es igual que el de la categoria devolvemos los nombres encontrados
		if(name.equals(MEDICINES_TAGNAME)) {
			return super.everyPart(reader, name);
		}
		else {
			// si el valor es null significa que ya hemos recorrigo el array y devolvemos los valores leidos
			if(next != null) {
				return super.readCategory(reader, name);

			}
			// si leemos una categoria incorrecta para la variable MEDICINES_TAGNAME devolvemos un error
			else {
				reader.skipValue();
				System.err.println("The category: '" + name + "' is wrong.");
				return new StringBuffer("");
			}
		}
	}
	// Una vez tengamos la categoria encontrada pasamos a los valores
	public String readEntry(JsonReader reader)
			throws IOException{
		String medicineName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			// si el valor corresponde con la etiqueta de NAME_FIELD_TAGNAME
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medicineName = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}
		return medicineName;
	}

}
