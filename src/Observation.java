import core.data.*;
public class Observation implements Comparable{
	
	private String id;
	private String description;
	private double temp;
	private int windDir;
	private double windSpeed;
	private double pressure;
	private int humidity;
	private String iconURLBase;
	private String iconURLName;
	
	
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
		this.iconURLBase = iconURLBase;
		this.iconURLName = iconURLName;
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
		if (windSpeed < 1) {
			return  0;
		}
		else if (1 <= windSpeed && windSpeed <= 3) {
			return 1;
		}
		else if (4 <= windSpeed && windSpeed <= 6) {
			return 2;
		}
		else if (7 <= windSpeed && windSpeed <= 10) {
			return 3;
		}
		else if (11 <= windSpeed && windSpeed <= 16) {
			return 4;
		}
		else if (17 <= windSpeed && windSpeed <= 21) {
			return 5;
		}
		else if (22 <= windSpeed && windSpeed <= 27) {
			return 6;
		}
		else if (28 <= windSpeed && windSpeed <= 33) {
			return 7;
		}
		else if (34 <= windSpeed && windSpeed <= 40) {
			return 8;
		}
		else if (41 <= windSpeed && windSpeed <= 47) {
			return 9;
		}
		else if (48 <= windSpeed && windSpeed <= 55) {
			return 10;
		}
		else if (56 <= windSpeed && windSpeed <= 63) {
			return 11;
		}
		else {
			return 12;
		}
	}
	
	   /**
	    * Returns a string representation of the wind conditions. Summarize the
	    * Beaufort number into 4 categories; calm; breezy; wind flags out; storm 
	    * @return a string representation of the wind conditions
	    */
	   public String getWindConditions() {
		   if (getBeaufortNumber() <= 2) {
			   return "Wind is calm";
		   }
		   else if (getBeaufortNumber() >= 3 && getBeaufortNumber() <= 5) {
			   return "Nice breeze today";
		   }
		   else if (getBeaufortNumber() >= 6 && getBeaufortNumber() <= 9) {
			   return "Wind flags are out";
		   }
		   else {
			   return "Storm is coming";
		   }
	   }
		/**
		 * @return the wind Direction
		 */
		public int getWindDir() {
			return windDir;
		}

		/**
		 * @return a short description of the weather
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @return the barometric pressure in mb
		 */
		public double getPressure() {
			return pressure;
		}

		/**
		 * @return the relative humidity
		 */
		public int getHumidity() {
			return humidity;
		}

		/**
		 * @return the wind speed in knots
		 */
		public double getWindSpeed() {
			return windSpeed;
		}

	/**
	 * Gets the weather station ID
	 * @return the weather station id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Gets the URL to the icon depicting the weather. 
	 * This is a combination of the baseURL and the icon filename
	 * @return A full URL to the icon depicting the weather. 
	 */
	public String getIconURL() {
		return "http://www.iconstore.com/"+ iconURLName;
	}


	/**
	 * @return the temp
	 */
	public double getTemp() {
		return temp;
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
