# Arpit-Sharma
# GeoLocator

GeoLocator is a Java-based program that determines the nearest country based on given geographical coordinates (latitude and longitude). It uses a CSV file containing country centroids (latitude, longitude, country code, and country name) to calculate the nearest country.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Example](#example)
- [CSV File Format](#csv-file-format)

## Introduction

GeoLocator is a simple Java application designed to find the nearest country for a given set of geographical coordinates. It reads country centroid data from a CSV file and uses the Haversine formula to calculate the distance between the given coordinates and each country's centroid.

## Features

- Reads country centroid data from a CSV file.
- Calculates the nearest country based on given geographical coordinates.
- Outputs the nearest country code and full name.

## Prerequisites

- Java Development Kit (JDK) 8 or higher

## Installation

1. Clone the repository or download the source code.
   ```bash
   git clone https://github.com/Arpit606/ArpitSharma_Repsitory1_javafile

2.Ensure you have the required CSV file (country_centroids.csv) in the project directory.

## Usage

1. Compile the Java program.
   
   javac GeoLocator.java

2.Run the program.

   java GeoLocator
## Example

The program contains example coordinates for testing purposes:

India: (23.338222, 78.953843)
Russia: (64.226766, 101.052230)
China: (35.228869, 100.981302)

The output will be the nearest country for each of these coordinates.

Coordinates: (23.338222, 78.953843) -> Country: IN (India)

Coordinates: (64.226766, 101.052230) -> Country: RU (Russia)

Coordinates: (35.228869, 100.981302) -> Country: CN (China)

## CSV File Format

The CSV file (country_centroids.csv) should be formatted as follows:

code,latitude,longitude,countryFullName

IN,20.5937,78.9629,India

RU,61.5240,105.3188,Russia

CN,35.8617,104.1954,China

