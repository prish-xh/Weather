/*
 Represents information about a NWS weather station
*/

import core.data.*;
public class WeatherStation implements Comparable{
   
   /**
    * Constructor 
    * @param name The name of the station
    * @param id the id for the station
    * @param state the state in which this station is located
    * @param lat latitude of this station
    * @param lng longitude of this station
    */
	public static void main(String[] args) {
		Activity1.avoidSSLError();
		String id = "KSEA";
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml"); 
		ds.setCacheTimeout(15);  
		ds.load();
		ds.printUsageString();

	}
	
	private String name;
	private String id;
	private String state;
	private double lat;
	private double lng; 
	
   public WeatherStation(String name, String id, String state, double lat, double lng) {
	   this.name = name;
	   this.id = id;
	   this.state = state;
	   this.lat = lat;
	   this.lng = lng;
	   
    }
   
  /**
   * Gets the ID of the weather station
   * @return the ID of the weather station
   */
   public String getId() { 
      return id;
   }
   
   /**
    * Gets the name of this station
    * @return the name of this station
    */
   public String getName() { 
      return name;
   }
   
   /**
    * Gets state in which this station is located
    * @returns the state in which this station is located
    */
   public String getState() {
	   return state;
   }
   /**
    * Gets latitude for this station
    * @returns the latitude for this station
    */
   public double getLatitude() {
	   return lat;
   }
   /**
    * Gets longitude for this station
    * @returns the longitude for this station
    */
   public double getLongitude() {
	   return lng;
   }

   /**
    * Returns the weather data for the current weather station
    * @return An Observation object representing the ata for the current weather station
    */
   public Observation getCurrentWeather() {
	   Activity1.avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml");
	   ds.load();
	   ds.setCacheTimeout(900);
	   Observation obiWan = ds.fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_mb", "relative_humidity");
	   return obiWan;
			  
	   
   }
   
   
	/**
	 * Compares this object with the specified object for order.
	 * @return a value > 0 if this observation has a name later in the alphabet than other;
	 *           value < 0 if this observation has a name earlier in the alphabet than other;
	 *           value = 0 if this observation's name is equal to other.
	 * NOTE: Polymorphism is needed for this, so I didn't include this in the project spec.
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof WeatherStation) {
			WeatherStation otherOb = (WeatherStation) other;
			return this.getName().compareTo(otherOb.getName());
			}
		return -1;
	}

   
}
