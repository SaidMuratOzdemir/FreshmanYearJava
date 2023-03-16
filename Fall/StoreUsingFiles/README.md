# StoreUsingFiles
This is a Java code to store information about products and customers orders.

## Usage
The code takes in a single argument as input which is used to form the filenames for storing information about products and customer orders. The information about the products is stored in a file with the name `[input argument]_ProductionInfo.txt`, customer orders are stored in a file with the name `[input argument]_Order.txt` and the output of the code includes a receipt of the customer order stored in `[input argument]_Receipt.txt` and any errors in the customer order stored in `[input argument].log`. The final state of the product information after processing the customer orders is stored in a file named `[input argument]_ProductInfoAfterOrder.txt`.

## Functionality
The code reads the information about products and customer orders from the respective files and then performs the following operations:

- Calculates the total amount due for the customer order
- Writes the receipt for the customer order
- Writes any errors that occur in the customer order
- Writes the final state of the product information after processing the customer order

## Author
Said Murat Özdemir

## Created on
 11.12.2022
