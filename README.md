![FavoritePage](https://github.com/anirudh797/EasyFood/assets/53581159/a92cd617-3156-4231-8dbc-a1c7a3ce7926)# MealApp

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
   - ![HomePage](https://github.com/anirudh797/EasyFood/assets/53581159/cc62dafa-ac93-461a-adcb-2b86dd7286b8)

2. **Meal Detail Page:**
   - View detailed information about a specific meal.
   - Add the meal to your Favorite Meal Section.
   - ![MealDetail](https://github.com/anirudh797/EasyFood/assets/53581159/d10673b7-204c-4bf8-bcc1-86eb04073031)

3. **Favorites Page:**
   - Manage your favorite meals with CRUD operations.
   - Remove meals from Favorites using the swipe feature.
   - Undo action available for retrieving the deleted meal.
   - ![FavoritePage](https://github.com/anirudh797/EasyFood/assets/53581159/07a94517-533c-42c1-a6ec-ffdb10453c9e)
     
4. **Category Page**
   - List Meals by Available Category
   - Count for meals by Each Category
   - ![CategoryPage](https://github.com/anirudh797/EasyFood/assets/53581159/ae37df4d-f5a6-4a56-9915-f309f561d22b)


5. **Search Feature for each page** (In Progress)     

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/meal-app.git

## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, utilizing LiveData for data observation,
Retrofit for API communication, Coroutines for asynchronous programming, and RoomDB for offline storage.


