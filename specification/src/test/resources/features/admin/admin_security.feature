Feature: Admin app security
In order to manage app security
As a user in admin role of the admin app
I want to manage application users

  Scenario: Login in application as admin
  Given I am an 'admin' of the application
  When I am login in the application
  Then I should see the 'Admin page'

  Scenario: Logout from application
  Given I am an 'admin' of the application
  And I am logged into the application
  When I logout from application
  Then I should see the 'Landing page'

  Scenario: Manage application users
  Given I am an 'admin' of the application
  When I am logged in to application
  And I see the list of available users
  Then I should 'create', 'read', 'update', 'delete' a user named 'Adrian'

  Scenario: Disable an operator
  Given I am an 'admin' of the application
  When I am logged in to application
  And I see the list of available users in all available roles
  And I disable 'Adrian' operator
  Then the user 'Adrian' will not be able to login into application again

  Scenario: Manage application settings
  Given I am an 'admin' of the application
  And I am logging into the application
  And I choose to see the applications settings
  When I I edit one property
  Then I should see result