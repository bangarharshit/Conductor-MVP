package com.harshitbangar.conductormvp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Observation {

  @JsonProperty(value = "v")
  BigDecimal verifiedWaterLevel;

  public Observation() {}

  public static Builder with() {
    return new Builder();
  }

  public BigDecimal getVerifiedWaterLevel() {
    return verifiedWaterLevel;
  }

  public static class Builder {

    BigDecimal verifiedWaterLevel;

    public Builder verifiedWaterLevel(BigDecimal verifiedWaterLevel) {
      this.verifiedWaterLevel = verifiedWaterLevel;
      return this;
    }

    public Observation build() {
      Observation observation = new Observation();
      observation.verifiedWaterLevel = verifiedWaterLevel;
      return observation;
    }

  }

}
