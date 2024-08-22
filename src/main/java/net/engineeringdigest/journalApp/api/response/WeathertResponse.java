package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeathertResponse {

    private Clouds clouds;
    private Main main;

    @Getter
    @Setter
    public class Clouds{
        public int all;
    }

    @Getter
    @Setter
    public class Main{
        public double temp;
        @JsonProperty("feels_like")
        public double feelsLike;
        @JsonProperty("temp_min")
        public double tempMin;
        @JsonProperty("temp_max")
        public double tempMax;
    }

}
