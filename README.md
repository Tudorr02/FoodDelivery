
The application offers a user-friendly interface for customers to place orders, while restaurants can manage these orders through the backend services. The application includes data storage services to ensure persistence of user and order information.

## Features
- **Object-Oriented Principles**: Utilizes OOP principles for a modular and extensible structure.
- **Collections**: Uses Java collections (List, Map) for efficient data management.
- **Service Classes**: Service classes to manage business logic and operations.
- **Custom Exceptions**: Custom exceptions for handling specific application errors.
- **CSV Data Storage Management**: Manages data storage using CSV files, with a Singleton service for reading and writing data.
- **Auditing Service**: Logs actions performed in the application.
- **Graphical User Interface**: Built with Swing for an interactive user experience.

## User Authentication
- **Log In / Sign Up**: Users can register and authenticate as delivery personnel or customers.
    <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/07465618-46b7-4cc6-97df-e0b7de0a1a85" width="300" height="400">
    <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/85bac79c-5401-4595-8af6-641984499940" width="500" height="400">


## Delivery Man Functionalities
- **Order Management**: Delivery personnel can view, take, and deliver orders.
  <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/2dc015f6-0234-4109-b308-7b31321c51a4" width="600" height="500">


- **Profile Management**: Delivery personnel can modify their profile.
  <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/6e15916f-c728-4fa6-a29e-99e693349bdb" width="600" height="500">


## Customer Functionalities
- **Restaurant Selection**: Customers can select a restaurant and view details like reviews and price range.
  <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/57d7d847-2928-4e3e-b7f2-bb637c3c4190" width="600" height="500">

- **Food Item Selection**: Customers can choose available food items and add them to the shopping cart.
  <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/ec17d293-3d68-4aa1-913c-0d3f4d24ea1f" width="600" height="500">


- **Shopping Cart Management**: Customers can edit item quantity or delete items. Items from multiple restaurants cannot be mixed.
 
    <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/a1ca2afd-f5ad-461f-8c58-803341d9d440" width="300" height="400">
    <img src="https://github.com/Tudorr02/FoodDelivery/assets/92024989/433d1396-0ac8-43f0-94c9-e461a3ed8669" width="500" height="400">
  - **Order Placement**: Customers can choose to pay on delivery with card/cash.
  - **Order Type**: Customers can choose to pick up the order or have it delivered (10% additional charge).
  - **Promo Codes**: Apply promo codes for price reduction.
- **Order Updates**: Customers can see order status updates in their account.
    - **In Progress** ( PickUp Order ) : Order is not ready to be picked up .
    - **Ready** ( PickUp Order ) : Order is ready to be picked up .
    - **In Progress** ( Delivery Order ) : Order has not been taken by delivery personnel yet.
      ![image](https://github.com/Tudorr02/FoodDelivery/assets/92024989/784a54bc-7b64-4ac5-83b5-8662657f1681)

    - **In Delivery**: Order is being delivered.
     ![image](https://github.com/Tudorr02/FoodDelivery/assets/92024989/860faf23-9676-4785-b226-d323989c9865)

    - **Completed**: Order delivered to the customer.
     ![image](https://github.com/Tudorr02/FoodDelivery/assets/92024989/5f9d731c-6eba-4d31-953f-c42800184ef6)

## Technologies Used
- **Programming Language**: Java
- **Frameworks and Libraries**: Swing for UI, custom data storage services, various utility classes.




