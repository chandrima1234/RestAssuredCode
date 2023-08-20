package GETAPIs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter

public class productLombok {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	

@Data
@NoArgsConstructor
@AllArgsConstructor
	public static class Rating{
		private double rate;
		private int count;
	}
	
	
	
}
