package com.martin.blog.service;

public interface JsonConsumer {

  <T> T getData(String data, Class<T> entidad);
}
