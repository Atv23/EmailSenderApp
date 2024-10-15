# Email Sender App

This project is a complete email sender application that consists of a front-end built with Angular and a back-end REST API implemented in Java using Spring Boot. Below are the details for both the front end and back end, along with some visual representations of the user interface.

## Front End

The front end of this application is developed using **Angular**, providing a responsive and user-friendly interface for sending emails. The main features include:

- **Landing Page**: A welcoming interface that introduces the user to the application and its functionalities.
- **Send Email Page**: Users can compose and send emails through this page, providing the recipient's email, subject, and message content.
- **Loading Indicator**: A progress bar that indicates the email is being processed when a user submits the email.

### Screenshots

1. **Landing Page**  
   ![Landing Page](UI%20pics/landing_page.png)

2. **Send Email Page**  
   ![Send Email Page](UI%20pics/send_email_page.png)

3. **Processing Sending**  
   ![Processing Sending](UI%20pics/sending_process.png)

## Back End

The back end is built using **Spring Boot**, exposing a REST API for handling email operations. Key components include:

- **EmailController**: This controller manages the incoming requests related to email operations. It includes endpoints to:
  - **GET /welcome**: A simple endpoint that returns a welcome message.
  - **POST /send**: An endpoint for sending emails. It accepts the email content in JSON format and utilizes JavaMail to process the sending of emails.

### Technologies Used

- Java
- Spring Boot
- JavaMail API
- Maven

## Installation

To run this project locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Atv23/EmailSenderApp.git

## Set up the backend:

1. Navigate to the `backend_java` folder.
2. Install the dependencies using Maven:
   ```bash
   mvn install
3. Run the Spring Boot application.

## Set up the front end:

1. Navigate to the `frontend_angular/emailGUI` folder.
2. Install the dependencies using npm:
    ```bash
    npm install
3. Run the Angular application:
    ```bash
    ng serve

## Contributing
Feel free to contribute to this project by submitting issues or pull requests.