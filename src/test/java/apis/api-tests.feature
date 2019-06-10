Feature: Create a Corporate

Background:
  * def Utils = Java.type('apis.Commons')
  * def timestamp = Utils.getTimestamp()
  * url baseUrl

Scenario: Given valid request when post should return 200
  Given path '/banks/corporates'
    And def payload =
    """
    {
      "name": "PS",
      "id": "012345",
      "idType": "GCCID",
      "entityType": "ENTERPRISE",
      "address1": "dubai",
      "timestamp": "#(timestamp)"
    }
    """
    And print payload
    And request payload
    And def signature = Utils.signPayload(payload)
    And print signature
    And headers { Authorization: 'Bearer #(signature)', 'Content-Type': 'application/json' }
  When method post
  Then print response



