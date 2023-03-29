package cinema;

import java.time.LocalDate;
import java.time.LocalTime;

public class CinemaShow {
	
	private static final int basePrice = 50;
	private static final int priceMultiplier = 50;
	
	private String movieName;
	private LocalDate movieDate;
	private LocalTime movieTime;
	private int capacity;
	private int soldTickets;
	private double[][] seats;
	
	public CinemaShow(String movieName, LocalDate movieDate, LocalTime movieTime) {
		this.movieName = movieName;
		this.movieDate = movieDate;
		this.movieTime = movieTime;
	}
	
	public void initializeSeats(int[] theaterDimensions) {
		
		this.seats = new double[theaterDimensions.length][];
		for(int i=0; i<theaterDimensions.length; i++) {
			this.capacity += theaterDimensions[i];
			this.seats[i] = new double[theaterDimensions[i]];
		}
	}
	
	
	public boolean sellSeat(int row, int column) {
		
		if (row < 0 || row > this.seats.length || column < 0 || column > this.seats[row].length)
			return false;
		
		if (this.seats[row][column] == 0) {
			this.seats[row][column] = CinemaShow.assignPrice(this.soldTickets, 
															 this.capacity);
			this.soldTickets++;
			return true;
		} else {
			return false;
		}
		
		
	}
	
	public static double assignPrice(int soldTickets, int totalTickets) {
		return CinemaShow.basePrice + 
				CinemaShow.priceMultiplier*soldTickets/totalTickets;
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public LocalDate getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(LocalDate movieDate) {
		this.movieDate = movieDate;
	}
	public LocalTime getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(LocalTime movieTime) {
		this.movieTime = movieTime;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getSoldTickets() {
		return soldTickets;
	}
	
	public String toString() {
		String output = "";
		for (int r=0; r<seats.length;r++) {
			for (int c=0; c<seats[r].length; c++) {
				output += "[" + seats[r][c] + "]" + "\t";
			}
			output += "\n";
		}
		
		return output;
		
	}
	
	public static void main(String[] args) {
		
		CinemaShow Mario = new CinemaShow("Super Mario Movie",
										  LocalDate.of(2023, 5, 12),
										  LocalTime.of(19, 0));
		
		Mario.initializeSeats(new int[]{5,5,4,6,5,8});
		
		Mario.sellSeat(0, 0);
		Mario.sellSeat(1, 1);
		Mario.sellSeat(1, 2);
		
		System.out.println(Mario);

		
	}

}
