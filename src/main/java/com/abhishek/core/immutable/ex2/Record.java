package com.abhishek.core.immutable.ex2;

import java.util.ArrayList;
import java.util.List;

final class Record {
  private final long id;
  private final String name;
  private final List<String> tokens;
  public Record(long id, String name, List<String> tokens) {
    this.id = id;
    this.name = name;
    this.tokens = new ArrayList<>(tokens);
  }
  public long getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public List<String> getTokens() {
    return new ArrayList<>(tokens);
  }

  @Override
  public String toString() {
    return "Record{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", tokens=" + tokens +
        '}';
  }
}