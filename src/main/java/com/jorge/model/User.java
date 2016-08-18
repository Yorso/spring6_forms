package com.jorge.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
		private Long id;
		@NotEmpty // Validating a form using annotations from the Java bean annotation API.
				  // If the validation fails, the form will be shown again to the user with the errors that are to be fixed.
		private String firstName;
		@Min(18) @Max(130) // Validating a form using annotations from the Java bean annotation API
						   // If the validation fails, the form will be shown again to the user with the errors that are to be fixed.
		private Integer age;
		private String country;
		private boolean married;
		private String[] languages;
		private String color;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public boolean isMarried() {
			return married;
		}
		public void setMarried(boolean married) {
			this.married = married;
		}
		public String[] getLanguages() {
			return languages;
		}
		public void setLanguages(String[] languages) {
			this.languages = languages;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		
		
}
