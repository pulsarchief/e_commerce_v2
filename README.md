# E-Commerce API Platform

A comprehensive Spring Boot-based e-commerce backend with robust user management, product catalog, order processing, and automated workflows.

## Features

### User Management
- **Authentication** - JWT-based secure login and registration
- **Role-based Access Control** - Separate admin and customer functionalities
- **Profile Management** - Users can update personal information
- **Account Administration** - Admins can enable/disable accounts

### Product Management
- **Product Catalog** - Complete product listing with details
- **Inventory Control** - Real-time stock tracking
- **Stock Alerts** - Automated notifications for low/out of stock items

### Order Processing
- **Order Creation & Management** - Complete order lifecycle
- **Status Workflow** - Automated transitions (PENDING → PAID → SHIPPED → COMPLETED)
- **Order History** - Track past purchases

### Automation
- **Scheduled Tasks** - Nightly stock checks and order status updates
- **Email Notifications** - Automated alerts for orders and inventory

## Technology Stack

- **Spring Boot** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Data persistence
- **JWT** - Stateless authentication
- **JavaMail** - Email notifications
- **Lombok** - Reduces boilerplate code

## Setup & Installation

1. Clone the repository
   ```
   git clone https://github.com/your-username/e_commerce_v2.git
   cd e_commerce_v2
   ```

2. Create a .env file in the project root with:
   ```
   # Database
   DB_URL=jdbc:postgresql://localhost:5432/ecommerce
   DB_USERNAME=postgres
   DB_PASSWORD=yourpassword

   # JWT
   JWT_KEY=your_secure_jwt_signing_key
   JWT_TIMEOUT=86400000

   # Email
   MAIL_SERVER_USERNAME=your_email@gmail.com
   MAIL_SERVER_PASSWORD=your_app_password
   SENDER_MAIL_ADDRESS=noreply@yourdomain.com
   BCC_MAIL_ADDRESS=admin@yourdomain.com
   ```

3. Build and run the application
   ```
   ./mvnw spring-boot:run
   ```

## API Endpoints

### Authentication
- `POST /auth/register` - Create new user account
- `POST /auth/login` - Authenticate and receive JWT
- `POST /auth/resetPassword` - Change password (authenticated)

### User Management
- `GET /user` - Get current user profile
- `PUT /user/update` - Update current user details
- `DELETE /user` - Delete current user account

### Admin Operations
- `GET /admin/user/{userId}` - View specific user details
- `PUT /admin/user/update/{userId}` - Update any user
- `DELETE /admin/user/{userId}` - Delete user account

### Products & Orders
- Various endpoints for product browsing and order management

## Scheduled Tasks

### Stock Check (Midnight)
Automatically scans inventory and sends email alerts for products that are:
- Out of stock (0 items)
- Low stock (less than 6 items)

### Order Status Transitions (Hourly)
Automatically progresses orders through their lifecycle:
- PENDING → FAILED (after 6 hours)
- PAID → SHIPPED (after 6 hours)
- SHIPPED → COMPLETED (after 6 hours)

## Default Users

The system creates two default accounts on first run:
- **Admin**: Username: `admin`, Password: `admin123`
- **User**: Username: `priyanshu`, Password: `priyanshu`

## Security

- JWT-based authentication
- Password encryption with BCrypt
- Role-based access control
- Secure email notifications

## License

MIT License
