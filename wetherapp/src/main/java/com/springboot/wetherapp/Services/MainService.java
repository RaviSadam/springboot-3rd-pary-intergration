package com.springboot.wetherapp.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.wetherapp.Mappers.LocationMapper;
import com.springboot.wetherapp.Mappers.WetherDataMapper;



@Service
public class MainService {

    private final ObjectMapper mapper;
    private final SimpleDateFormat format;
    private LocationMapper locationMapper;

    @Autowired
    public MainService(ObjectMapper mapper,SimpleDateFormat format){
        this.format=format;
        this.mapper=mapper;
        this.locationMapper=null;
    }

    public WetherDataMapper getWetherReport(String location) {
        try{

            if(location==null){
                if(this.locationMapper==null)
                    this.locationMapper=this.getCurrentLocation();
                location=this.locationMapper.getCity();
            }


            HttpClient client=HttpClient.newHttpClient();

            //preparing request
            HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create("https://weather-by-api-ninjas.p.rapidapi.com/v1/weather?city="+location))
		    .header("X-RapidAPI-Key", "67494e6b29msh84011a1c42c22d7p147c51jsnbeff1eb148fb")
		    .header("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
		    .method("GET", HttpRequest.BodyPublishers.noBody())
		    .build();

            //sending https request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            //converting json to DataMapper object
            WetherDataMapper data=mapper.readValue(response.body(),WetherDataMapper.class);
            
            //paring seconds to time
            if(data.getError()==null){
                data.setSunrise(this.getTime(Long.parseLong(data.getSunrise())));
                data.setSunset(this.getTime(Long.parseLong(data.getSunset())));
            }
            return data;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    //gives exact time using milliseconds
    private String getTime(long t){
        Date date=new Date(t*1000);
        String time=format.format(date);
        return time.split(" ")[1];
    }

    //api that gives gives the current location details
    private LocationMapper getCurrentLocation(){
        try{
            String apiUrl = "https://ipinfo.io";
            RestTemplate restTemplate = new RestTemplate();
            String locationData = restTemplate.getForObject(apiUrl, String.class);
            return mapper.readValue(locationData, LocationMapper.class);
        }
        catch(Exception e){
            return null;
        }
    }
    public String getLocation(){
        return this.locationMapper.getCity();
    }
    
}
