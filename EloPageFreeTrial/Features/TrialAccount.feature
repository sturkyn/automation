Feature: TrialAccount - Sign Up
  Description: This feature will test sign up for 14 days free trial.

  Scenario: User starts 14 days free trial and signs up
    Given Open browser
    When Enter the url "www.elopage.com/en"
    And Close modal and click on Start Your 14 Day Free Trial
    And Fill registration form
    And Click on accept checkbox
    And Click on create account
    Then User successfully enters elopage portal
