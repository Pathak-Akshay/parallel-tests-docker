#!/bin/bash

# This script serves as the entrypoint for the Docker container
# It runs TestNG tests based on the module XML file provided

# Check if TESTNG_XML environment variable is set
if [ -z "$TESTNG_XML" ]; then
    echo "Error: TESTNG_XML environment variable is not set"
    exit 1
fi

# Display container info
echo "======================================================"
echo "Container started: $(date)"
echo "Running tests with XML: $TESTNG_XML"
echo "Container ID: $HOSTNAME"
echo "======================================================"

# Run the TestNG tests
mvn test -DtestNG.xml=$TESTNG_XML

# Capture the exit code
EXIT_CODE=$?

# Display completion info
echo "======================================================"
echo "Tests completed: $(date)"
echo "Exit code: $EXIT_CODE"
echo "======================================================"

# Return the exit code from Maven
exit $EXIT_CODE