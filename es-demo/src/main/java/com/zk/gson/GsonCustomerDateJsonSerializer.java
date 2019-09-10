package com.zk.gson;

import com.google.gson.*;
import org.elasticsearch.search.DocValueFormat;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>ClassName: GsonCustomerDateJsonSerializer</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/9
 * @since JDK 1.8
 */
public class GsonCustomerDateJsonSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String content = jsonElement.getAsJsonPrimitive().getAsString();
        Long date = Long.valueOf(content);
        Timestamp timestamp = new Timestamp(date);
        return timestamp;
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return  new JsonPrimitive(String.valueOf(date.getTime()));
    }
}
