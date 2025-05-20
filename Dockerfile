FROM maven:3.9-eclipse-temurin-11

WORKDIR /app

# Copy the Maven project
COPY pom.xml .
COPY src ./src

# Copy the entrypoint script
COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Build the project once to cache dependencies
RUN mvn dependency:go-offline

# Command to run when container starts
ENTRYPOINT ["/entrypoint.sh"]