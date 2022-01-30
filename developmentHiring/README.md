# Running & Tests

- For running application ``
    - Application will start at port 8080

# Technical Details
- Spring Boot is used as application framework
- Main data source is PostgreSQL
- Database create table scpripts under sql folder(There are foreign keys)
# API Details


```sh 

POST /api/unload                              # Unload shipments from vehicle
POST /api/load                                # Load shipments to vehicle
POST /bag/createBag                           # Create a bag to Bag table
POST /deliveryPoint/createDeliveryPonint      # Create a delivery point 
GET  /deliveryPoint/unloadWrongLoadedPackage  # Unload package from bag if it is wrong
POST /shipment/createShipment                 # Create a shipment to Shipment table
POST /vehicle/createVehicle                   # Create a shipment to Vehicle table

User has to create delivery points , bags , shipments and packages related with bags before unload.
I changed the state field in the return value. I wanted to put a more understandable output

PS: Couldn't complete the whole test. Because I had food poisoning after covid,
I did not have enough time for full test coverage. 
I wrote test functions as an example. Sorry for that. Thank you in advance for your review.

