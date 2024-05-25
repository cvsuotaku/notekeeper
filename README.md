# NoteKeeper API

NoteKeeper is a simple RESTful API for managing notes. It allows users to create, retrieve, update, and delete notes. This project uses Spring Boot and stores notes in memory.

## Table of Contents

- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Data Model](#data-model)
- [Validation](#validation)
- [Error Handling](#error-handling)
- [Running the Application](#running-the-application)
- [Postman Collection](#postman-collection)
- [Swagger UI](#swagger-ui)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/cvsuotaku/notekeeper.git
    cd notekeeper
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Create a new note
- **URL**: `/notes`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
      "title": "Note title",
      "content": "Note content"
  }
  ```
- **Response**:
  ```json
  {
      "status": "201 CREATED",
      "message": "Note successfully created.",
      "data": {
        "id": "47f8fc7d-b507-43e7-bee4-35d7a0fb34c2",
        "title": "a",
        "content": "a"
      }
  }
  ```

### Retrieve all notes
- **URL**: `/notes`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "status": "200 OK",
    "message": "All Notes successfully retrieved.",
    "data": [
        {
            "id": "c4966848-b87a-4d2e-9d71-92a891ccc2f0",
            "title": "Note title",
            "content": "Note content"
        },
        {
            "id": "92b66e09-6e3f-4e83-a5ff-48ded1088aed",
            "title": "Note title",
            "content": "Note content"
        }
    ]
  }
  ```

### Retrieve a specific note by ID
- **URL**: `/notes/{id}`
- **Method**: `GET`
- **Response**:
  ```json
  {
    "status": "200 OK",
    "message": "Successfully retrieved a note by Id.",
    "data": {
        "id": "47f8fc7d-b507-43e7-bee4-35d7a0fb34c2",
        "title": "Note title",
        "content": "Note content"
    }
  }
  ```

### Update a specific note
- **URL**: `/notes/{id}`
- **Method**: `PUT`
- **Response**:
  ```json
  {
    "status": "200 OK",
    "message": "Successfully updated a note.",
    "data": {
        "id": "47f8fc7d-b507-43e7-bee4-35d7a0fb34c2",
        "title": "Note title",
        "content": "Note content"
    }
  }
  ```
- **Response**: `200 OK` or `204 No Content`

### Delete a specific note
- **URL**: `/notes/{id}`
- **Method**: `DELETE`
- **Response**: `204 No Content`
  ```json
  {
    "status": "200 OK",
    "message": "Successfully deleted / removed a note."
  }
  ```
## Data Model

### NoteRequest
```json
{
    "title": "string",
    "content": "string"
}
```

### NoteResponse
```json
{
    "status": "string",
    "message": "string",
    "data": "object"
}
```

## Validation

- The `title` and `content` fields are required and must not be empty.
- The `title` field has a maximum length of 25 characters.

## Error Handling

The API provides basic error handling for common scenarios:

- **400 Bad Request**: Invalid input data
- **404 Not Found**: Note not found

Example error response:
```json
{
    "status": "400 BAD_REQUEST",
    "message": "There's a problem in your request.",
    "errors": {
      "title": "must not be empty",
      "content": "must not be empty"
    }
}
```

```json
{
    "status": "404 NOT_FOUND",
    "message": "Note not found."
}
```

## Running the Application

To run the application locally, execute the following command:
```sh
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## Postman Collection
A Postman collection is available to help you test the API. You can find the collection in the `src/main/resources` folder of the project.

To import the Postman collection:

- Open Postman.
- Click on Import in the top left corner.
- Select Upload Files.
- Choose the NoteKeeper.postman_collection.json file from the src/main/resources folder.
- Click Import.

This will import the collection and you will be able to test the API endpoints directly from Postman.

## Swagger UI
The API documentation is available via Swagger UI. To access it, navigate to:
http://localhost:8080/swagger-ui/index.html

Swagger UI provides a user-friendly interface to interact with the API, view available endpoints, and see request/response formats.

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

This `README.md` provides an overview of the NoteKeeper API, including installation instructions, API endpoints, data models, validation rules, and error handling. Adjust the content as needed to fit your project's specifics.