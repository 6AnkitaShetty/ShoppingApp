# ShoppingApp
ShoppingApp is an android app where you as a user can search for a product and scroll through a list of products
and find something interesting to buy.

## Architecture
The app uses `MVVM Clean Architecture` design pattern. 
This provides better separation of concern, easier testing, lifecycle awareness, etc.

## Functionality
The app's functionality includes:
1. Home page where user can search for the products(now it displays list of products with static name)
2. Product detail page with details of the product.
3. Wishlist page which shows all the user saved products
4. Cart page displays all the products added to the cart and also option to place order
5. Order page where user can see the confirmation of the order and you can return to home page

## Technologies Used
1.  [Android appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat), [KTX](https://developer.android.com/kotlin/ktx), [Constraint layout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout), [Material Support](https://material.io/develop/android/docs/getting-started).
2.  [Android View Binding](https://developer.android.com/topic/libraries/view-binding)
3. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
4. [Retrofit](https://square.github.io/retrofit/) for REST API communication
5. [Coroutine](https://developer.android.com/kotlin/coroutines) for Network call
6. [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle), [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
7. [Room](https://developer.android.com/jetpack/androidx/releases/room) for local database.
8. [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) for supporting navigation through the app.
9. [Mockito](https://developer.android.com/training/testing/local-tests) & [Junit](https://developer.android.com/training/testing/local-tests) for Unit testing.
10. [Robolectric](http://robolectric.org/) for Instrumentation testing.
11. [Truth](https://truth.dev/) for Assertion in testing.
12. [Espresso](https://developer.android.com/training/testing/espresso) for UI testing.
