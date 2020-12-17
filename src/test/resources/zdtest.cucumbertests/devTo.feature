Feature: basic dev to functionality
  Scenario: Select first podcast and play it
    Given DevTo main page is open
    When User click on podcasts
    And User select first podcast
    Then User can see its title
    And User can play it

  Scenario: Open first video page
    Given DevTo main page is open
    When User click on Videos
    And User click on first video
    Then User can see first video page

  Scenario: Searching for right phrase
    Given DevTo main page is open
    When User search "testing"
    Then Top result should contain the word "testing"