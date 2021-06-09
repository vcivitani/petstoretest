package petstoretest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.junit.Assert;

public class StepDefinitions {

	private static final String BASE_URL = "https://petstore.swagger.io";
	private static final String DEFAULT_TOKEN = "special-key";

	private static Response response;
	private static String jsonString;
	private static Pet pet;

	@Given("I am an authorized user")
	public void i_am_an_authorized_user() {
		// Generate token if needed, this test use the default token
	}

	@When("user gets pets list with status {string}")
	public void user_get_pets_list_with_status(String status) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");

		response = request.get("/v2/pet/findByStatus?status=" + status);

		jsonString = response.asString();
		Gson g = new Gson();
		Pet[] pets = g.fromJson(jsonString, Pet[].class);
		Assert.assertTrue(pets.length > 0);

	}

	@Then("user gets list of pets with status {string}")
	public void user_gets_list_of_pets_with_status(String status) {
		Gson g = new Gson();
		Pet[] pets = g.fromJson(jsonString, Pet[].class);
		for (Pet currentPet : pets) {
			Assert.assertEquals(status, currentPet.getStatus());
		}
	}

	@When("user post pet with status {string}")
	public void user_post_pet_with_status(String string) {

		Pet newPet = new Pet("dog_42");
		Gson g = new Gson();

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("accept", "application/json").header("Content-Type", "application/json");

		response = request.body(g.toJson(newPet)).post("v2/pet");
	}

	@Then("user can assert the pet data")
	public void user_can_assert_the_pet_data() {
		ResponseHelper.assertResponse(200, response, "user can assert the pet data");

		jsonString = response.asString();
		Gson gson = new Gson();
		pet = gson.fromJson(jsonString, Pet.class);
	}

	@Given("a pet is {string}")
	public void a_pet_is(String status) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json").header("api_key", DEFAULT_TOKEN);

		response = request.get("/v2/pet/" + pet.getId());

		jsonString = response.asString();
		Gson g = new Gson();
		Pet pet = g.fromJson(jsonString, Pet.class);
		Assert.assertTrue(pet.getStatus().equals(status));
	}

	@When("user can update pet status as {string}")
	public void user_update_pet_status_as(String status) {
		Gson g = new Gson();

		pet.setStatus(status);

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("accept", "application/json").header("Content-Type", "application/json");

		response = request.body(g.toJson(pet)).put("v2/pet");

		ResponseHelper.assertResponse(200, response, "user can update pet status as " + status);
	}

	@When("user can delete the pet")
	public void user_delete_the_pet() {
		Gson g = new Gson();
		
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("accept", "application/json").header("api_key", DEFAULT_TOKEN).header("Content-Type",
				"application/json");

		response = request.body(g.toJson(pet)).delete("/v2/pet/" + pet.getId());

		ResponseHelper.assertResponse(200, response, "user can delete the pet");
	}
	
}
