package PUTAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder


public class User {
	
	public User(String name, String email, String gender, String status) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("status")
	private String status;

}
