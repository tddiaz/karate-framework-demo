Feature: Create a Corporate

Background:
  * def Utils = Java.type('apis.Commons')
  * def timestamp = Utils.getTimestamp()
  * def randomId = Utils.randomId()
  * url baseUrl

Scenario: Create Corporate
  * def payload =
    """
    {
      "name": "PS-1234",
      "id": "#(randomId)",
      "idType": "GCCID",
      "entityType": "ENTERPRISE",
      "address1": "dubai",
      "timestamp": "#(timestamp)"
    }
    """
  * def signature =  Utils.signPayload(payload);

  Given path '/banks/corporates'
    And header Authorization = 'Bearer ' + signature
    And header Content-Type = 'application/json'
    And request payload
  When method post
  Then status 201
    And match response == 'corporate created'



