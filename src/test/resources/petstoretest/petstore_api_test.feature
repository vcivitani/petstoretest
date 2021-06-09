
Feature: Checks for petstore, API test 
pet store URL: https://petstore.swagger.io

	Background: User generates token for authorisation
		Given I am an authorized user
		 
  Scenario: User is able to get available pets list
    When user gets pets list with status "available"
    Then user gets list of pets with status "available"
    
  Scenario: User is able to post a new pet, update and delete it
    When user post pet with status "available"
    Then user can assert the pet data
 		And user can update pet status as "sold"
 		And user can delete the pet
