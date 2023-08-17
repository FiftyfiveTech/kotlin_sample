# Sample Clean Architecture Android App

This repository contains a sample Android app that showcases the implementation of Clean Architecture using Kotlin, Compose UI, Hilt for dependency injection, Flows for reactive programming, Room Database for local data storage, and MVVM design pattern.

This app contains :
*   User Interface built with **[Jetpack Compose](https://developer.android.com/jetpack/compose)**
*   A single-activity architecture, using **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation)**.
*   A presentation layer that contains a Compose screen (View) and a **ViewModel** per screen (or feature).
*   Reactive UIs using **[Flow](https://developer.android.com/kotlin/flow)** and **[coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** for asynchronous operations.
*   A **data layer** with a repository and two data sources (local using Room and remote).
*   Dependency injection using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).


## Screenshots

<img src="screenshots/home.png" alt="Screenshot">
<img src="screenshots/detail.png" alt="Screenshots">

## Introduction

Clean Architecture is a software architectural pattern that emphasizes the separation of concerns and the organization that helps in promoting maintainability, testability, and scalability.

## Features

*   Display a list of items retrieved from a remote API.
*   Cache the items locally using Room Database.
*   Implement MVVM design pattern for the UI layer.
*   Utilize Hilt for dependency injection.
*   Use Flows for reactive programming.
*   Change theme of the app.


## Architecture Overview

The app follows the Clean Architecture principles, which divides the codebase into layers:

*   Presentation Layer (UI layer): Implements the MVVM pattern, handles UI logic, and interacts with the domain layer. It uses Hilt for dependency injection and Flows for reactive programming.
*   Domain Layer (Business layer): Contains the business logic and use cases of the app. It is independent of the presentation and data layers and defines the core functionality of the app.
*   Data Layer: Handles data operations, including remote API calls and local data storage using Room Database. It provides data sources for the domain layer.

## Structure
```
com
└───fiftyfive
└───nativeandroidtemplate
│   TemplateApplication.kt
│
├───business
│       Photo.kt
│       PhotoLocalRepository.kt
│       PhotoRepository.kt
│
├───data
│   ├───api
│   │       ApiClient.kt
│   │       PhotoService.kt
│   │
│   ├───database
│   │       PhotoDatabase.kt
│   │       PhotoDetailsEntity.kt
│   │       PhotoListDAO.kt
│   │
│   └───repositoryimp
│           PhotoDetailRepositoryImp.kt
│           PhotoRepositoryImp.kt
│
├───di
│       HiltModules.kt
│
├───presentation
│   │   MainActivity.kt
│   │
│   ├───common
│   │       AppThemeSetter.kt
│   │       CustomLayoutTextView.kt
│   │       ErrorComposable.kt
│   │       ScreenContent.kt
│   │
│   ├───navigation
│   │       NavGraph.kt
│   │       Screen.kt
│   │
│   ├───screens
│   │       DetailScreen.kt
│   │       HomeScreen.kt
│   │
│   ├───theme
│   │       Color.kt
│   │       Theme.kt
│   │       Type.kt
│   │
│   ├───ui
│   │   └───theme
│   │           Color.kt
│   │           Theme.kt
│   │           Type.kt
│   │
│   ├───viewmodels
│   │       PhotoDetailViewModel.kt
│   │       PhotoListViewModel.kt
│   │
│   └───viewstates
│           PhotoDetailViewState.kt
│           PhotoListViewState.kt
│
└───util
Util.kt
```

## Opening a sample in Android Studio

To open one of the samples in Android Studio, begin by cloning the repository and checking out one of the branch, and then open the root directory in Android Studio.

Clone the repository:

```
git clone https://github.com/FiftyfiveTech/kotlin_sample.git
```
This step checks out the master branch.

### License


##### 55 Tech

We are relentlessly focusing on digital transformation. Dive deep into the customer cases to know more about the project which we delivered.

