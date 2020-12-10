# Skew Challenge

This repository contains the code to solve the Skew challenge, it is a simple java program that retrieves the order books of a certain currency pair from Bitstamp, prints the information as a JSON to the console and asynchronously saves it to a CSV file.

## Installation

Use [maven](https://maven.apache.org/download.cgi) to test the project.

```bash
mvn test
```
To compile it.

```bash
mvn install
```

And to build it as an executable jar with all its dependencies.

```bash
mvn clean compile assembly:single
```

## Usage

Once the project has been built as an executable jar, inside the /target folder use

```bash
java -jar .\skew-challenge-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Disclaimer
I am not familiar with order books and I saw a lot of them that had the exact same bid/ask price and quantity values so I decided not to save an order book if there was already another one with the same bids/asks price and quantity values.