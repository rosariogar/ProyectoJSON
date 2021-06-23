package cadenaDeMandos;

import java.io.*;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MedicinePresentations extends CDM{

	private static final String MEDICINEPRESENTATIONS_TAGNAME = "medicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSREF_FIELD_TAGNAME = "posologyRef";
	private static final String FIELD_SEP = ";";

	public MedicinePresentations(CDM s) {
		super(s);
	}

	public StringBuffer readCategory(JsonReader reader, String name) throws IOException {
		if (name.equals(MEDICINEPRESENTATIONS_TAGNAME)) {
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

	private boolean isArray(JsonReader reader) throws IOException {
		Boolean isArray = false;
		if (reader.peek() == JsonToken.BEGIN_ARRAY) {
			isArray = true;
		}
		return isArray;
	}

	public String readEntry(JsonReader reader) throws IOException {
		String refMedicine = null;
		String refIngActive = null;
		String refInhale = "";
		String dose = "";
		String refPosology = "";
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				refMedicine = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				refIngActive = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					refInhale = reader.nextString();
				} else if (isArray(reader)) {
					reader.beginArray();
					while (reader.hasNext()) {
						refInhale = refInhale + reader.nextString() + ", ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final
					refInhale = refInhale.substring(0, refInhale.length() - 2);
				} else {
					refInhale = "ERROR";
				}
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					dose = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						dose = dose + "(" + reader.nextString() + "), ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final.
					dose = dose.substring(0, dose.length() - 2);
				}
			} else if (name.equals(POSREF_FIELD_TAGNAME)) {
				if (!isArray(reader)) {
					refPosology = reader.nextString();
				} else {
					reader.beginArray();
					while (reader.hasNext()) {
						refPosology = refPosology + reader.nextString() + ", ";
					}
					reader.endArray();
					// Para quitar la coma y el espacio del final
					refPosology = refPosology.substring(0, refPosology.length() - 2);
				}
			} else {
				reader.skipValue();
			}
		}
		return refMedicine + FIELD_SEP + refIngActive + FIELD_SEP + refInhale + FIELD_SEP + dose + FIELD_SEP + refPosology;
	}	
}


