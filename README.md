# Project Title: Android Compose Listings App

## Project Overview

This Android application is developed using Kotlin and Jetpack Compose. The app presents a list of
listings sourced from a provided JSON source. It incorporates various key features and integrations.

## Architecture and Libraries

- **Architecture Pattern:** MVVM (Model-View-ViewModel)
- **Image Loading:** Coil for efficient image loading and caching
- **Dependency Injection:** Dagger Hilt for dependency injection

## Key Features Implemented
1. **Email and password sign-up/sign-in:**
   - Intergrate email and password for user authentications

2. **Google Sign-In Integration:**
    - Integrated Google Sign-In for user authentication.

3. **Map Integration:**
    - Integrated a map view displaying a marker that represents the location based on the given
      coordinates.

4. **Calendar Display:**
    - Included a calendar component that visually highlights and disables booked dates for each
      listing.

5. **Amenity Filtering:**
    - Implemented a feature that allows users to filter listings according to amenities.

6. **Listing Details:**
    - Provided functionality for users to access detailed information about a specific listing.
    - Details include viewing all the photos and the host details.

7. **Booking:**
    - Illustrated a "Book Now" feature that allows users to select a range of dates that are not
      booked and add the number of guests.
