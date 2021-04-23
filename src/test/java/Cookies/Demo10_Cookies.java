package Cookies;



import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
public class Demo10_Cookies {
	
	
	//Validate cookies
	
		@Test(priority=1)
		public void testCookies()
		{
			given()
				.when()
					.get("http://jsonplaceholder.typicode.com/photos")
				.then()
					.cookie("__cfduid","d2e6826318e8c2e588a1b6c4d144ba64b1610793035");
		}

		//get Cookies
		@Test(priority=2)
		public void testGetCookies()
		{
			Response response=
					given()
						.when()
							.get("http://jsonplaceholder.typicode.com/photos");
			
			//Get Single/Specific cookie from Response
			String cookie_value=response.getCookie("__cfduid");
			System.out.println("Value of Cookie is:"+cookie_value);
			
			//Get All cookies from Response
			Map<String,String>cookies_values=response.getCookies();
			
			for(Map.Entry entry:cookies_values.entrySet())
			{
				System.out.println(entry.getKey()+"==>"+entry.getValue());
			}
				
		}
		
		//get Details of Cookies
		@Test(priority=3)
			public void testDetailedCookies()
			{
				Response response=get("http://jsonplaceholder.typicode.com/photos");
				
				//Get cookie
				Cookie cookie_info=response.getDetailedCookie("__cfduid");
				
				System.out.println(cookie_info.hasExpiryDate());
				System.out.println(cookie_info.getExpiryDate());
				
				System.out.println(cookie_info.hasValue());
				System.out.println(cookie_info.getValue());
				
					
			}
		

}
