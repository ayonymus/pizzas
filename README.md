# Pizzas

A simple android application built with Clean Architecture and Jetpack Compose. This app allows users to select pizza flavors and complete an order.

## Features
- Fetches a list of pizza flavors from a static JSON file. 
- Allows users to select one or two flavors for a pizza.
- Allows users to clear selection at the same time
- Calculates the price of the pizza based on the selected flavors.
- Presents an order confirmation screen with a summary of the selected pizza and its price.

## Core Components

**Data Layer**: Handles data operations. Fetches the pizza flavors from the JSON file using a Retrofit API call.
**Domain Layer**: Contains the business logic of the application. It has use-cases to fetch pizza flavors and calculate the total price of the pizza.
**Presentation Layer**: Interacts with the UI of the application. Contains the ViewModel and UI

## Implementation Details

**ViewModel**: PizzaOrderViewModel is responsible for fetching the list of pizzas and calculating the price of the selected pizzas.
Ui state management is implemented **MVI** style: there is one single UI state object that is exposed with a state flow to the the UI.
The ViewModel has one single `handleIntent()` method that receives intents from the UI.

**UseCases**: FetchPizzasUseCase fetches the list of pizzas. CalculatePizzaPriceUseCase calculates the price of the selected pizzas.
**UI**: OrderScreen is a composable function that displays the list of pizzas and allows users to select their desired flavors. SummaryScreen shows a summary of the selected pizza and its price.


## Testing

The application includes a couple of unit tests, however there are no UI tests. 
I usually add unit tests for all usecases and ViewModels, that is, any sensible components.

## Setup

- Clone the repository. 
- Open the project in Android Studio.
- Run the application or execute the tests.

## Libraries Used

- Jetpack Compose
- Retrofit
- Coroutines
- Flow
- LiveData
- ViewModel
- JUnit

## Future Improvements

 - Add error handling. (right now only a Timber.e log)
 - Improve UI/UX. (right now ugly af)
 - Add more features
 - Add UI tests

Please note that this is a minimal implementation and serves as a starting point to build a full-fledged pizza delivery application.
I also cut some corners in terms of unit testing, and UI.

### Original task

A small pizza delivery company was looking for growth and wanted to develop their own pizza delivery app. They hired you to develop this app.

In order to keep this small, we'll only work on a small portion of that app: the menu and flavor selection, plus a confirmation screen.

Your task:

Find a JSON file with pizza flavors here: http://static.mozio.com/mobile/tests/pizzas.json


Write an app that has the following features:

- A menu of pizza flavors (read from the JSON above) where the user can select the flavors they want. A pizza can have one flavor (full pizza of the same flavor) or two flavors (half/half)
- To keep the scope small, only one pizza can be chosen at a time. No need for a shopping cart
- Flavors have their own prices. Total pizza price is calculated based on the price of each half  (i.e. if a flavor costs $10, a half of this flavor will cost $5 and a full pizza of this flavor $10)

- A button to finish ordering, which takes the user to an "order successful" screen. Simply show a summary of the selected pizza and the final price
- This scope includes only the flavor selection, pricing and confirmation. Extras are not necessarily a pro

- Bonus: Write unit tests for your code

Here's what we will be evaluating in your solution:

- This project is a broad definition by design. We're less concerned about a very specific spec and more interested in the solution you come up with
- App works as per the scope above and doesn't crash
- General code quality, documentation, project structure...

Here's what we will NOT be evaluating:

- UI/UX. Your app will provide UI, but using unstyled/raw components is perfectly acceptable (and encouraged). No need to hire a designer :). However, do make an effort to make it easy enough to use from a user's perspective.
- Any extra features not described above