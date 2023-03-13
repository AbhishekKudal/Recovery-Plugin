# Abandoned Cart Recovery System

This project is built using Spring Boot and MySQL database. It is designed to recover abandoned carts for e-commerce businesses. The system handles webhook events triggered by e-commerce providers for abandoned carts and successful orders, and sends out pre-defined messages to customers on a customizable schedule until all scheduled messages are sent or the customer places an order.

## Installation

To install and run this project, please follow these steps:

1. Extract the project zip file.
2. Import the project into your IDE.
3. Install MySQL database and MySQL Workbench on your local machine.
4. Execute the SQL scripts provided in the [Starter-files](Starter-files) directory sequentially using MySQL Workbench to create the necessary database schema and tables.
5. Run the main application in your IDE.

## Work Flow:

When a customer abandons their cart, the `/abandoned-cart` endpoint is hit and the data is stored in the `abandoned_cart_details` table. At the same time, scheduled events are created based on the predefined schedule and event details are stored in the `scheduler` table.

![abandoned_cart_flow.png](src%2Fmain%2Fresources%2Fimages%2Fabandoned_cart_flow.png)

A `schedulerService` runs every 60 seconds and sends out SMS and email messages to the customers according to the events in the `scheduler` table.

When a customer places an order, the `/order` endpoint is hit and the order-related data is stored in the `order` table. At the same time, the status for the `abandoned_cart` and `scheduler` are updated for that respective order.

![order_flow.png](src%2Fmain%2Fresources%2Fimages%2Forder_flow.png)

## Endpoints

The following REST endpoints are available:

* `/abandoned-cart` - Webhook for passing abandoned cart details.
* `/order` - Webhook for passing successful order information.
* `/scheduler` - View page for scheduled events table.
* `/cart-details` - View page for abandoned cart table.

## Usage Example

Suppose a customer adds products to their cart and abandons the checkout at time T. The e-commerce provider's backend triggers a call to the `/abandoned-cart` webhook with below **sample details** of the checkout.

```json
{
    "customerId": 210,
    "cartToken": "9688yujkuiyhsdfgh245",
    "email": "samplecustom4587@gmail.com",
    "contactNumber": 4589786780,
    "emailMarketing": true,
    "smsMarketing": true,
    "createdAt": "2023-03-13T12:24:00+05:30"
}
```

The Application has a pre-defined service that works as follows:

1. Events are created as per the requirement, in this case 3 events are created.
   1. T + 30 minutes
   2. T + 1 day
   3. T + 3 days
2. Scheduler is scanned and events are sent out every minute.
3. If a user places an order, the backend should trigger a call to `/order` webhook wih below sample data:
```json
{  
    "cartToken": "9688yujkuiyhsdfgh245"
}
```

4. Subsequent events will not be processed as the order has been placed. The service will update the scheduled events status to 'Discarded' as well as the abandoned_cart status to 'Order Placed' based on the unique value of cartToken.
