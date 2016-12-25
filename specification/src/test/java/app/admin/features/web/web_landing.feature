Feature: Web application landing page
In order to find a job
As a user the web application
I want to get relevant information

  Scenario: List of jobs
  Given I am an user of the application
  When I am landing on the first page
  Then I should see a list of jobs

  Scenario: Filter jobs
  Given I am an user of the application
  When I am landing on the first page
  Then I should filter jobs by different criteria

  Scenario: Search jobs by a criteria
  Given I am an user of the application
  When I am landing on the first page
  And I search a job by a criteria
  Then I should get a list of searched jobs

  Scenario: Apply to a job
  Given I am an user of the application
  When I am landing on the first page
  And I apply to a job
  Then I am redirecting to the job source

  Scenario: Subscribe for job offers
  Given I am an user of the application
  When I am landing on the first page
  And I enter my email and the  send schedule
  Then I will receive emails