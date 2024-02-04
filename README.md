# MealApp

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Description

MealApp is a Kotlin-based Android application that utilizes the MealDB API to provide users with information about meals. The app is built using MVVM architecture, LiveData, Retrofit, Coroutines, and RoomDB for offline storage. Each category consist of multiple meals.
It features multiple pages, including Home, Favorites, and Categories, with the ability to view random meals, popular meals, and food categories. Users can also view detailed information about each meal, add meals to favorites, and remove them using the swipe feature.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Architecture](#architecture)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

1. **Home Page:**
   - Display a random meal.
   - Show a list of popular meals.
   - Provide a list of available food categories.

2. **Meal Detail Page:**
   - View detailed information about a specific meal.
   - Add the meal to your Favorite Meal Section.

3. **Favorites Page:**
   - Manage your favorite meals with CRUD operations.
   - Remove meals from Favorites using the swipe feature.
   - Undo action available for retrieving the deleted meal.

4. **Search Feature for each page** (In Progress)     

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/meal-app.git

## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, utilizing LiveData for data observation,
Retrofit for API communication, Coroutines for asynchronous programming, and RoomDB for offline storage.


