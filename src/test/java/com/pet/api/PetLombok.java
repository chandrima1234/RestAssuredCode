package com.pet.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.api.User;

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

public class PetLombok {
	
	
	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<tag> tags;
	private String status;
	
	@Data
	@NoArgsConstructor
	@Getter
	@AllArgsConstructor
	@Builder
	public static class Category{
		private Integer id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@Getter
	@AllArgsConstructor
	@Builder
	
	public static class tag{
		private Integer id;
		private String name;
	}

}
