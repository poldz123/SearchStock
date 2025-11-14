## Setup

- Download Android Studio(Apple Mac)
- Build and Run the project

## Architecture

- MVVM
- Clean Architecture 

Modularized the project into multiple modules that communicate through 
dependency inversion, ensuring high-level modules do not depend on low-level ones. 
Also added a dedicated feature module that contains the main search screen.

Added also the build-logic module to centralize the module configurations

## Libraries

- Koin - Dependency Injection
- Retrofit - Networking library

- Mockito - Mocking test library(See the **data module** repository for sample tests)
