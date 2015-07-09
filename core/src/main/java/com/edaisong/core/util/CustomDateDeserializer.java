package com.edaisong.core.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final static Logger logger = LoggerFactory.getLogger(CustomDateDeserializer.class);

	@Override
	public Date deserialize(JsonParser paramJsonParser,
			DeserializationContext paramDeserializationContext)
			throws IOException, JsonProcessingException {

		String str = paramJsonParser.getText().trim();
		if (str != null) {
			str = str.replace("/Date(", "").replace(")/", "");
		}

		System.out.println("replace after:" + str);
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			// logger.error("date deserialize error", e);
		}
		return paramDeserializationContext.parseDate(str);
	}
}
