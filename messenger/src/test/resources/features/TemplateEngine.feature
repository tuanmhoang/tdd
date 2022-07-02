Feature: Template Engine Feature

  Scenario: Generate message from CONSOLE
    Given Template generate from Console
    And user param is "Tuan"
    And module param is "TDD"
    And address param is "hello@abc.com"
    When Template Engine generates message
    Then Message "To: hello@abc.com{enter}Hello Tuan,{enter}Today we are studying TDD.{enter}This message is generated based on the CONSOLE template." should be generated

  Scenario: Generate message from FILE
    Given Template generate from File
    And user param for file is "Tuan"
    And module param for file is "TDD"
    And address param for file is "hello@abc.com"
    When Template Engine generates message with file mode
    Then Message "To hello@abc.com{enter}Hello Tuan!{enter}Today we are studying TDD{enter}This message is generated based on the FILE template." should be generated from file mode