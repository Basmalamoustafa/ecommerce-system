# E-commerce System – Fawry Rise Journey Internship Challenge

This project is a Java-based implementation of a simple e-commerce system, developed as part of the **Fawry Full Stack Internship Challenge**. It demonstrates strong object-oriented design principles and robust handling of various business cases, including product expiration, shipping, cart checkout, and customer balance management.

---

## Features

### Product Types
- `Product`: Basic product with name, price, quantity
- `ExpirableProduct`: Products with expiration dates (Cheese, Biscuits)
- `ShippableProduct`: Products that require shipping and have a weight (TV)
- `ExpirableShippableProduct`: Combines both expiration and shipping features

### Cart and Checkout
- Add products to cart with quantity validation
- Calculate subtotal, shipping fees, and total
- Validate:
  - Product availability
  - Product expiration
  - Customer balance
  - Non-empty cart
- Deduct product quantity and customer balance upon successful checkout
- Print:
  - Shipping notice with item weights
  - Checkout receipt

### Shipping Service
- Products implementing the `ShippableItem` interface are sent to `ShippingService`
- Displays shipped items and total package weight

---

## Test Coverage

Manual test cases are provided in `TestEcommerce.java`, covering:

| Test Case | Description |
|-----------|-------------|
| TC#1 | Successful checkout with mixed products |
| TC#2 | Insufficient customer balance |
| TC#3 | Product out of stock |
| TC#4 | Product expired |
| TC#5 | Empty cart |
| TC#6 | Product with no shipping |

Run it via:
```bash
javac *.java
java TestEcommerce
```
---

## Getting Started

### Prerequisites
- Java 8 or above
- Git (for cloning)

### Run Locally
```bash
# Clone the repository
git clone https://github.com/your-username/ecommerce-system.git
cd ecommerce-system

# Compile the project
javac *.java

# Run the main simulation
java Main

# Run test scenarios
java TestEcommerce

```
---

## Project Structure

```bash
.
├── Main.java                   
├── TestEcommerce.java          
├── Product.java
├── ExpirableProduct.java
├── ShippableProduct.java
├── ExpirableShippableProduct.java
├── Cart.java
├── Checkout.java
├── Customer.java
├── ShippingService.java
├── ShippableItem.java         
└── README.md
```
---

## Assumptions
- Shipping cost = 0.01 * weight (grams) * quantity
- Product weight is stored in grams and displayed in kilograms
- Expired items are determined using LocalDate.now().isAfter(expirationDate)
- No persistence (database or file storage) is used
- ShippingService is simulated using console output
