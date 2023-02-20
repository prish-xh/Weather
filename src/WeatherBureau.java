/*
 * Arrays and ArrayLists of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherBureau {
	/**
	 * Constructor that initializes stations by connecting, loading
	 * and fetching the weather stations serviced by the National 
	 * Weather Service
	 */
	public WeatherBureau() {
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
		ds.load();
		WeatherStation[] stns = ds.fetchArray("WeatherStation", "station/station_name", 
		                                         "station/station_id", "station/state",
		                                         "station/latitude", "station/longitude");

	
	}
	
	/**
	 * Gets all the weather stations as an array
	 * @return he weather stations as an array
	 */

	public WeatherStation[] getAllStationsArray() {
		Activity1.avoidSSLError();
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
		ds.load();
		WeatherStation[] allstns = ds.fetchArray("WeatherStation", "station/station_name", 
	                                         "station/station_id", "station/state",
	                                         "station/latitude", "station/longitude");
		return allstns;

	}
	
	/**
	 * Gets all the weather stations as an ArrayList
	 * @return he weather stations as an ArrayList
	 */
	public ArrayList<WeatherStation> getAllStationsList(){
		Activity1.avoidSSLError();
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
		ds.load();
		ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name", 
		                                                 "station/station_id", "station/state",
		                                                 "station/latitude", "station/longitude");
		return allstns;
	}
	
	/**
	 * Ges the list of weather stations in the specified state
	 * @param state the state to filter with
	 * @return the list of weather stations in the specified state
	 */

	public ArrayList<WeatherStation> getStationsInState(String state){
		ArrayList<WeatherStation> allstns= getAllStationsList();
		ArrayList<WeatherStation> stateStns = new ArrayList<WeatherStation>();
		for (int i = 0; i< allstns.size(); i++) {
			if (state.equals(allstns.get(i).getState())){
				stateStns.add(allstns.get(i));
			}
		}
		return stateStns;
	}

	/**
	 * Returns an ArrayList of all states and provinces that
	 * have a National Weather Service weather station
	 * @return the states and provinces with NWS weather stations
	 */
	public ArrayList<String> getStatesWithStations(){
		ArrayList<WeatherStation> allstns= getAllStationsList();
		ArrayList<String> state = new ArrayList<String>();
		for (int i = 0; i< allstns.size(); i++) {
			boolean isThere = false;
			for (int j = 0; j< state.size(); j++) {
				if(allstns.get(i).getState().equals(state.get(j))) {
					isThere = true;
				}
				
			}
			if (isThere == false) {
				state.add(allstns.get(i).getState());
			}
		}
		
		
		return state;
		
	}

	/**
	 * Returns a Weather Station object given it's station ID
	 * @param stationLookingFor the station ID
	 * @return a Weather Station object given it's station ID; otherwise null
	 */
	public WeatherStation getStation(String stationLookingFor) {
		return null;
	}

	/**
	 * Finds the Weather Station in the specified state with the coldest temperature.
	 * @param state the state
	 * @return An Observation for the weather station with the coldest temperature
	 */
	public Observation getColdestInState(String state) {
			
			// Adding a try catch, because sometimes a weather station is offline.
			try {
				
				ArrayList<WeatherStation> stns = getStationsInState(state);
				Observation display = null;
				
				for (int i = 0; i <stns.size()-1; i++) {
					String id = stns.get(i).getId();
					String id2 = stns.get(i+1).getId();
					Activity1.avoidSSLError();

					DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" +id+ ".xml");
					ds.load();
					DataSource ds2 = DataSource.connect("http://weather.gov/xml/current_obs/" +id2+ ".xml");
					ds2.load();
					
					Observation one = ds. fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_in", "relative_humidity");
					Observation two = ds. fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_in", "relative_humidity");
					
					if (one.colderThan(two)) {
						display = one;
					}
					else {
						display = two;
					}
				}
				return display;
				
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
				System.out.println("Error retrieving observation data for station" );
			}

		
		return null;
	}
	
	/**
	 * Gets a list of all weather stations in a state sorted by their name.
	 * @param state the state
	 * @return list of all weather stations in a state sorted by their name
	 */

	public WeatherStation[] getStationsInStateSortedByName(String state) {
		ArrayList<WeatherStation> stateStns = getStationsInState(state);
		WeatherStation[] display = new WeatherStation[stateStns.size()];
		
		for (int i = 0; i <stateStns.size() -1; i++) {
			for (int j = 1+i; j<stateStns.size(); j++) {
				if (stateStns.get(i).getName().compareTo(stateStns.get(j).getName()) >= 0){
					WeatherStation temp = stateStns.get(i);
					stateStns.set(i,  stateStns.get(j));
					stateStns.set(j,  temp);
				}
			}
		}
		for (int i = 0; i < stateStns.size(); i++) {
			display[i] = stateStns.get(i);
		}
		return display;
		}
	
	/**
	 * Sorts the array of WeatherStation using the Insertion Sort algorithm
	 * @param arr the array of WeatherStation
	 */
	public void insertionSort(WeatherStation[] arr) {
		int index = 0;
		int sorted = 0;
		WeatherStation newval;
		
		
		for(index = 1; index <arr.length; index++) {
			newval = arr[index];
			sorted = index;
			while(sorted >0 && arr[sorted -1].getName().compareTo(newval.getName()) > 0) {
				arr[sorted] = arr[sorted -1];
				sorted--;
				
			}
			arr[sorted] = newval;
		}

	}
	public static void main(String[] args) {
	   WeatherBureau bureau = new WeatherBureau();
//	   WeatherStation[] stations = bureau.getAllStationsArray();
//	   for (WeatherStation ws : stations) {
//		   System.out.println("  " + ws.getId() + ": " + ws.getName());
//	   }
//	   System.out.println("Total number of stations: " + stations.length);
//	   
//	   System.out.println();
	   
	   System.out.println("Getting weather stations in Washington");
	   ArrayList<WeatherStation> waStations = bureau.getStationsInState("WA");
	   for (WeatherStation ws : waStations) {
		   System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }
	   System.out.println("Total number of stations: " + waStations.size());
	   
	   System.out.println();
	   System.out.println("Getting coldest station in Washington");
	   Observation ob = bureau.getColdestInState("WA");
	   System.out.println("Coldest station is - " + ob);
	   System.out.println(ob);
	   
	   System.out.println();
	   System.out.println("Sorting stations in Washington");
	   WeatherStation[] filteredStations = bureau.getStationsInStateSortedByName("WA");
	   for (WeatherStation ws : filteredStations) {
		   System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }


   }
}
