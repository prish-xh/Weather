/*
 * Arrays and ArrayLists of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherBureau {
	WeatherStation[] stations;
	
	/**
	 * Constructor that initializes stations by connecting, loading
	 * and fetching the weather stations serviced by the National 
	 * Weather Service
	 */
	public WeatherBureau() {
	
	}
	
	/**
	 * Gets all the weather stations as an array
	 * @return he weather stations as an array
	 */

	public WeatherStation[] getAllStationsArray() {
		return null;
	}
	
	/**
	 * Gets all the weather stations as an ArrayList
	 * @return he weather stations as an ArrayList
	 */
	public ArrayList<WeatherStation> getAllStationsList(){
		return null;
	}
	
	/**
	 * Ges the list of weather stations in the specified state
	 * @param state the state to filter with
	 * @return the list of weather stations in the specified state
	 */

	public ArrayList<WeatherStation> getStationsInState(String state){
		return null;
	}

	/**
	 * Returns an ArrayList of all states and provinces that
	 * have a National Weather Service weather station
	 * @return the states and provinces with NWS weather stations
	 */
	public ArrayList<String> getStatesWithStations(){
		return null;
		
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
		
		// Traverse all weathe stations in a state
		
			// Adding a try catch, because sometimes a weather station is offline.
			try {
				// add code to get the weather for a station
			}catch(Exception e) {
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
		return null;
	}
	/**
	 * Sorts the array of WeatherStation using the Insertion Sort algorithm
	 * @param arr the array of WeatherStation
	 */
	public void insertionSort(WeatherStation[] arr) {

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