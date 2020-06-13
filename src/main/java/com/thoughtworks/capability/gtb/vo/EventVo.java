package com.thoughtworks.capability.gtb.vo;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itutry
 * @create 2020-05-21_16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventVo {

  private String id;
  private String name;
  private EventType type;

  private LocalDateTime time;
  private UserVo user;

  private static class LocalDateTimeSerializer extends StdSerializer<LocalDateTime>{
    LocalDateTimeSerializer(){
      super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(value.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
  }

  private static class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime>{
    LocalDateTimeDeserializer(){
      super(LocalDateTime.class);

    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      Long time = Long.parseLong(p.getText());
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }
  }


}
