# springboot-Online-Store-basic-mvc

A simple Spring Boot MVC application to practice CRUD operations. This project allows users to:  
- View a list of products.  
- Create a new product.  
- Edit an existing product.  
- Delete a product.  

---

## Technologies Used  
- **Spring Boot MVC**: For building the web application.  
- **Thymeleaf**: For server-side rendering of HTML templates.  
- **JPA**: For database interactions.  
- **MySQL**: As the database for storing product information.  

---

## Endpoints  

### Product Management  
- `GET /products`  
  Displays all products in the system.  

- `GET /products/create`  
  Displays the form to create a new product.  

- `POST /products/create`  
  Handles the creation of a new product with validation.  

- `GET /products/edit?id={id}`  
  Displays the form to edit a product by its ID.  

- `POST /products/edit?id={id}`  
  Updates the product after validation.  

- `GET /products/delete?id={id}`  
  Deletes a product by its ID.  

---

