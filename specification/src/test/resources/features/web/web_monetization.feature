Feature: Application monetization
In order to make money
As a application owner
I want to let the users to post paid jobs

  Scenario: Post a job offer
  Given I am an user of the application
  When I am landing on the first page
  And I choose to post a job
  Then I should see a form where I have to enter my job data
  And pay for the job posting
  And I receive a confirmation by email when this is done