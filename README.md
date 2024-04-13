**Weather Now And Later** is a native Android application that displays the current weather of a selected city as well as the daily forecast.
This project is built to satisfy a set of instructions and requirements of a previous Vodafone technical assignment.

### Screenshots

<img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/70216ce2-5478-4078-a9ba-543d6403126b" width="217" height="470"> <img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/ded256bd-446f-4420-8a6a-12111b4f2cd0" width="217" height="470">
<img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/e7baf540-4a8d-4737-922c-51be4d15f6ad" width="217" height="470"> <img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/abc5d213-83d3-4986-8ba4-b81adecc1594" width="217" height="470">
<img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/6c070f23-3a05-4f4f-b451-9fc738fe59c6" width="217" height="470"> <img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/9abb11f3-6cec-41be-99fc-3bf2865c21b9" width="217" height="470">

### Architecture
- Clean Architecture principles are applied in the project:
  
  <img src="https://github.com/amrsalah3/WeatherNowAndLater/assets/52531091/f2f904f3-c640-41dd-819d-6bf1925cbfc0" width=300>

- **Project Modules** 

  - **domain**: Contains business rules and does not depend on any other layers. Thus, it can be used with different data layer implementations or different presentation layer implementations (e.g. Kotlin and Compose Multiplatform.). The module contains:
    - Weather and city models
    - Repository interfaces
    - Use cases
  - **data**: Contains data sources and repositories' implementations (Repository business rules/contracts (interfaces) are defined in the domain layer). This module contains:
    - Data sources
    - Data Transfer Objects (response models of data sources)
    - Repository implementations
  - **feature**: Contains the presentation layer including Composables and the ViewModels for each feature. This module contains these sub-modules:
    - City feature (Pick City screen). This feature is implemented using MVVM architecture.
    - Current Weather feature module (Current Weather screen). This feature is implemented using MVVM architecture.
    - 7-Day Weather Forecast feature (Daily Forecast screen). This feature is implemented using MVI architecture.
  - **core**: Contains shared and common components among the project. This module contains:
    - Common dependency injection using Dagger-Hilt
    - Common composable functions used in the UI
  - **weatherdatatools-library**: A library for formatting weather data. This library is published using Maven Local, however, to work on the project forked from this repo, we need to add the module itself as a dependency because Maven Local publishes this module as a local library, not online (central).

### Other Tech Stack

- UI: Jetpack Compose is used for the user interface. 
- Dependency Injection: All DI is done using Dagger-Hilt.
- Testing: Unit tests are added for the domain, data, and weatherdatatools-library modules.
- CI/CD Pipeline: A workflow is set up using GitHub Actions [[link](https://github.com/amrsalah3/WeatherNowAndLater/blob/master/.github/workflows/Build.yaml)] containing the following work: 
  - Linting the code
  - Running tests
  - Generate app APK
- Dark mode is supported.

### Download
Different APKs can be found in the WorkFlow CI/CD builds [here](https://github.com/amrsalah3/WeatherNowAndLater/actions/workflows/Build.yaml).   

### Notes and Further Improvements
There are some improvements to the project that I wanted to make but due to my limited time, I will just mention them below:
- Encapsulate and abstract common gradle module dependencies and their versions that are used in multiple modules.
  This will reduce repeating the dependencies in multiple modules and will guarantee providing one shared version of each library for all the sharing modules.
- Add unit tests for the ViewModels.
- Implement checks for internet connection while using the app, displaying an error when no connection, and provide a swipe-to-refresh functionality.
  Please make sure you have an internet connection while using the app.
- Displaying the latest fetched weather when using the app offline.
- Add city name on current weather screen.
- Use string resources instead of hardcoded texts.
- Display more weather data.
- Adding full documentation for each class, object, and function.
