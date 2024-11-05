package com.martin.blog.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.martin.blog.model.Article;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocalConsumer {

  @Value("${url.data}")
  private String BASE_URL;

  public void writeListData(List<Article> articles) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    try {
      mapper.writeValue(new File(BASE_URL), articles);
    } catch (IOException e) {
      throw new RuntimeException("No se pudo escribir el archivo: " + e.getMessage());
    }
  }

  public List<Article> readData() {
    ObjectMapper mapper = new ObjectMapper();
    List<Article> articles = new ArrayList<>();

    try {
      File file = new File(BASE_URL);
      if (file.exists()) {
        articles = mapper.readValue(file, new TypeReference<>() {
        });
      } else {
        System.out.println("El archivo no existe.");
      }
    } catch (IOException e) {
      throw new RuntimeException("No se pudo leer el archivo: " + e.getMessage());
    }
    return articles;
  }
}
