Feature: Create a Corporate

Background:
  * def Utils = Java.type('apis.Commons')
  * def timestamp = Utils.getTimestamp()
  * url baseUrl + '/banks/corporates'

Scenario: Given valid request when post should return 200
  Given def payload = read('payload.json')
  And def signature = Utils.signPayload(payload)
  And print signature


