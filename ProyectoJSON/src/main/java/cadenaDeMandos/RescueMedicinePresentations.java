package cadenaDeMandos;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class RescueMedicinePresentations extends CDM{

	private static final String RESCUEMEDICINEPRESENTATION_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDICINEREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTIVEINGREDIENTS_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = ";";

	public RescueMedicinePresentations(CDM s) {
		super(s);
	}

	// debido a que en esta parte del archivo .json tenemos los datos organizados de manera especial en forma de arrays,
	// creamos un metodo boolean que nos identifique previamente si la etiqueta se refiere a un string normal o a un array
	private boolean isArray(JsonReader reader) throws IOException {
		Boolean isArray = false;
		// el funcionamiento es simple, sabemos que un array tiene la forma: [_;...;_] 
		// al hacer el reader.peek() comprobamos el primer elemento del campo y si coincide con '[' el metodo devolvera TRUE
		// para saber si el primer elemento es un '[' usamos el enum JsonToken.BEGIN_ARRAY
		if (reader.peek() == JsonToken.BEGIN_ARRAY) {
			isArray = true;
		}
		return isArray;
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(RESCUEMEDICINEPRESENTATION_TAGNAME)) {
			return super.everyPart(reader, name);
		} else {
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
		String refMedicine = null;
		String refIngActive = null;
		String refInhaler = null;
		String dose = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDICINEREF_FIELD_TAGNAME)) {
				refMedicine = reader.nextString();
			} else if (name.equals(ACTIVEINGREDIENTS_FIELD_TAGNAME)) {
				refIngActive = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				if(!isArray(reader)) {
					refInhaler = reader.nextName();
				}
				else if (isArray(reader)) {
					reader.beginArray();
					while (reader.hasNext()) {
						refInhaler = refInhaler + reader.nextString() + ",";
					}
					reader.endArray();
				}
				
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				if(!isArray(reader)) {
					dose = reader.nextName();
				}
				else if (isArray(reader)) {
					reader.beginArray();
					while (reader.hasNext()) {
						dose = dose + reader.nextString() + ",";
					}
					reader.endArray();
				}
			} else {
				reader.skipValue();
			}
		}
		return refMedicine + FIELD_SEPARATOR + refIngActive + FIELD_SEPARATOR + refInhaler + FIELD_SEPARATOR + dose;
	}
}
