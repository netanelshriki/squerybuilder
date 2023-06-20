# SQueryBuilder

SQueryBuilder is a lightweight Java library that provides an easy way to build SQL queries using the Monad design pattern. It simplifies the process of constructing SQL queries by leveraging the power of monads, making your code more concise and readable.

## Features

- Easy and intuitive query building.
- Supports various types of queries, including SELECT, INSERT, UPDATE, and DELETE.
- Fluent API for constructing queries.

## Getting Started

To start using SQueryBuilder in your Java project, you can include the following Maven dependency:

```xml
<dependency>
    <groupId>net.dev</groupId>
    <artifactId>squerybuilder</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

or by following these steps:
1. Download the latest release of SQueryBuilder from the [releases](https://github.com/example/squerybuilder/releases) page.
2. Add the SQueryBuilder JAR file to your project's dependencies.

## Usage

Here are some examples of how to use SQueryBuilder to build SQL queries:

### Create Query

```java

public class Main {
    public static void main(String[] args) {
        // Create Query
        String createQuery = QueryManager.createQuery()
            .withEntity(new User("john","doe", 34, "johnd@gmail.com","Software Engineer"))
            .fromTable("users")
            .build();
        
            try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
                preparedStatement.execute();
            }

        }
    }
```

a quick reminder to how to write sql query with jdbc 😄:

```java

public class Main {
    public static void main(String[] args) {
        // Create Query
        User user = new User("john","doe", 34, "johnd@gmail.com","Software Engineer");
        String createQuery = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(3, user.getOccupation());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Create Query: Rows affected - " + rowsAffected);
        }

    }
}
```

