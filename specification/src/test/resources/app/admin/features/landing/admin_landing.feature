Feature: Admin landing
  In order to administrate the application
  As a application administrator
  I want to go to the welcome page and see instructions information

  Scenario: Landing on admin site
    Given I am an 'administrator' of the application
    And I know the url of the administration application as 'http://localhost:8080'
    When I am landing on the first page
    Then I should see a welcome message 'Welcome to Grails'