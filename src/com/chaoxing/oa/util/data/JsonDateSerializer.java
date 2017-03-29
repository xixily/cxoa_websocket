package com.chaoxing.oa.util.data;

import java.io.IOException;
import java.util.Date;

/*import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;*/
import org.springframework.stereotype.Component;

import com.chaoxing.oa.util.system.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 将返回的JSON格式中，日期类型转化为字符串类型，用于前台显示
 * 
 * @author dengxf
 * 
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(DateUtil.format(date));
	}

}
