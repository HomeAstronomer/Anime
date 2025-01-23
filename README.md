# Anime App

## Overview
Anime App is an Android application built using Jetpack Compose, Retrofit, and HILT. The app allows users to view a list of anime and watch YouTube videos related to them. This project focuses on simplicity and functionality while demonstrating modern Android development practices.

## Features
- Built using Jetpack Compose for modern UI development.
- Retrofit integration for network calls.
- Dependency injection implemented with HILT.
- Displays a list of the first 25 anime items on the dashboard.
- Plays only YouTube videos (no support for other video platforms).

## Assumptions
- Only YouTube videos are supported for playback.
- Paging is not implemented; the dashboard fetches and displays only the first 25 items.
- Chat GPT could be used for readme

## Tech Stack
- **Jetpack Compose**: For building declarative and reactive UI.
- **Retrofit**: For seamless API integration.
- **HILT**: For managing dependency injection.
- **Kotlin**: Programming language for the entire codebase.

## Scope for Improvement
1. **Error Handling**:
   - Improve handling of network or API failures.
   - Implement error states in the repository using sealed classes to differentiate between success and error states.
2. **UI Enhancements**:
   - Introduce better animations for a smoother user experience.
3. **Local Storage**:
   - Add Room database integration to make the app local-first.
4. **Repository Improvements**:
   - Enhance the repository layer to handle more business logic and manage error states effectively.


## Contact
For queries or suggestions, reach out at:
- **Email**: angorule@gmail.com
- **GitHub**: [HomeAstronomer](https://github.com/HomeAstronomer)
- **LinkedIn**: [Atharva Gorule](https://www.linkedin.com/in/atharva-gorule/)

