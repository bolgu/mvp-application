Feature: Manage jobs
In order to update the web application data
As a user of admin application in operator role
I want to manage the jobs

    Scenario: Login in application as operator
    Given I am an 'operator' of the application
    When I am logging in to application
    Then I should see the 'Operator page'

    Scenario: Not able to manage users as operator
    Given I am an 'operator' of the application
    When I am logging in to the application
    Then I should not be able to see the 'Admin page'

    Scenario: Manage application jobs specs
    Given I am an 'operator' of the application
    When I am logged in to the application
    And I see the list of available jobs specs
    Then I should be able to 'create' a job named 'Grails Developer'
    And 'read' a job named 'Grails Developer'
    And 'update' a job named 'Grails Developer'
    And 'delete' a job named 'Grails Developer'