import core.data.*;
public class Observation implements Comparable{
	
	private String id;
	private String description;
	private double temp;
	private int windDir;
	private double windSpeed;
	private double pressure;
	private int humidity;
	
	
	public static void main(String[] args) {
		Activity1.avoidSSLError();
		String id = "KSEA";
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml"); 
		ds.setCacheTimeout(15);  
		ds.load();
		ds.printUsageString();

	}
	
	/**
	 * Constructs an Observation object with the specified parameters.
	 * toString will output a full observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 * @param windSpeed wind speed in knots
	 * @param pressure barometric pressure in mb
	 * @param humidity relative humidity
	 */
	public Observation(String id, String description, double temp, int windDir, double windSpeed, double pressure, int humidity) {
		this.id = id;
		this.description = description;
		this.temp = temp;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
		this.humidity = humidity;
	}

	/**
	 * Constructs an Observation object with the specified parameters.
	 * toString will output a full observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 * @param windSpeed wind speed in knots
	 * @param pressure barometric pressure in mb
	 * @param humidity relative humidity
	 * @param iconURLBase the base URL, including the domain name for an icon depicting the weather
	 * @param iconURLName the icon name depicting the weather
	 */
	public Observation(String id, String description, double temp, int windDir, double windSpeed, double pressure, int humidity, String iconURLBase, String  iconURLName) {
		this.id = id;
		this.description = description;
		this.temp = temp;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	
	/**
	 * @return a String representing this observation
	 */
	public String toString() {
		return id+": " +temp+ " degrees; " +description+ " (wind: " +windSpeed+ " knots @ " +windDir + " degrees); barometric pressure: "
				+ pressure +"; relativity humidity: " +humidity;
		
	}
	
	/**
	 * Determines if the temperature of this observation is colder than other.
	 * @param other the other other observation
	 * @return true if this observation's temperature is colder than other; otherwise false.
	 */
	public boolean colderThan(Observation other) {
		if(this.getTemp() < other.getTemp()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns the Beaufort number on the Beaufort wind force scale.
	 * @return the Beaufot number for the current observation
	 * For more details, see https://en.wikipedia.org/wiki/Beaufort_scale
	 */
	public int getBeaufortNumber() {
		return -1;
	}
	
	   /**
	    * Returns a string representation of the wind conditions. Summarize the
	    * Beaufort number into 4 categories; calm; breezy; wind flags out; storm 
	    * @return a string representation of the wind conditions
	    */
	   public String getWindConditions() {
		   return null;
	   }
		/**
		 * @return the wind Direction
		 */
		public int getWindDir() {
			return Integer.MIN_VALUE;
		}

		/**
		 * @return a short description of the weather
		 */
		public String getDescription() {
			return null;
		}

		/**
		 * @return the barometric pressure in mb
		 */
		public double getPressure() {
			return Double.MAX_VALUE;
		}

		/**
		 * @return the relative humidity
		 */
		public int getHumidity() {
			return Integer.MIN_VALUE;
		}

		/**
		 * @return the wind speed in knots
		 */
		public double getWindSpeed() {
			return Double.MIN_VALUE;
		}

	/**
	 * Gets the weather station ID
	 * @return the weather station id
	 */
	public String getId() {
		return ;
	}
	/**
	 * Gets the URL to the icon depicting the weather. 
	 * This is a combination of the baseURL and the icon filename
	 * @return A full URL to the icon depicting the weather. 
	 */
	public String getIconURL() {
		return null;
	}


	/**
	 * @return the temp
	 */
	public double getTemp() {
		return Integer.MIN_VALUE;
	}
	/**
	 * Compares this object with the specified object for order.
	 * @return a value > 0 if this observation has a higher temperature than other;
	 *           value < 0 if this observation has a higher temperature than other;
	 *           value = 0 if this observation's temperature is equal to other.
	 * NOTE: Polymorphism is needed for this, so I didn't include this in the project spec.
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof Observation) {
			Observation otherOb = (Observation) other;
			if (this.getTemp() == otherOb.getTemp()) {
				return 0;
			}
			else if (this.getTemp() > otherOb.getTemp()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		return -1;
	}

}
