package hello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import hello.value.weather.OpenWeather;
import weatherresponse.WeatherResponse;

@RestController
public class WeatherController {

	
	private static final Logger log =  LoggerFactory.getLogger(WeatherController.class);
	
	@GetMapping("/weather")
	WeatherResponse  getWeather(@RequestParam("city1") String city1, @RequestParam("city2") String city2) {
		
		RestTemplate restTemplete = new RestTemplate();
		String city;
		String country;
		final String appId = "8abc5e28455da94fad981ea87e7c6432";
		
		String url1 = "https://api.openweathermap.org/data/2.5/weather?q="+ city1 + "&appId="+ appId;
		String url2 = "https://api.openweathermap.org/data/2.5/weather?q="+ city2 + "&appId="+ appId;
		 log.debug("Fetch url:" + url1);
		 log.debug("Fetch url:" + url2);
		
		OpenWeather weather1 = restTemplete.getForObject(url1, OpenWeather.class);
		OpenWeather weather2 = restTemplete.getForObject(url2, OpenWeather.class);
	
		WeatherResponse response = new WeatherResponse();
		response.setFirstcity(weather1.getName());
		response.setSecondcity(weather2.getName());
		response.setFirsttemp(weather1.getMain().getTemp()-273.15);
		response.setSecondtemp(weather2.getMain().getTemp()-273.15);
	
		return response; 
	}
	
	
	
}
