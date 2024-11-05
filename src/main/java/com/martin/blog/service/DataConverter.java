package com.martin.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements JsonConsumer {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T getData(String data, Class<T> entidad) {
    try {
      return mapper.readValue(data, entidad);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("No se pudo tranformar la data: " + e.getMessage());
    }
  }
}
