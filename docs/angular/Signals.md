# State Management and Signals in Angular

## Introduction to Signals
- **Signals**: A newer mechanism introduced in Angular 16, which are special values that notify Angular when they change.
- **Purpose**: Allows Angular to update the UI more efficiently compared to traditional state management.

## Creating a Signal
1. **Import Signal Function**: Import the `Signal` function from Angular core.
2. **Declare a Signal**:
    - Create a Signal property in the component class, replacing the existing state variable (e.g., `selectedUser`).
    - Initialize the Signal with a value (e.g., a user object).
    - Example:
      ```typescript
      selectedUser = signal(initialUserObject);
      ```

## Updating a Signal
- **Changing Values**: Update a Signal using the `set` method.
    - Example:
      ```typescript
      selectedUser.set(newUserObject);
      ```

## Reading a Signal in a Template
- To access the value of a Signal in a template, call the Signal as a function:
  ```html
  <div>{{ selectedUser() }}</div>
  ```
- This tells Angular to set up a subscription, ensuring the UI updates whenever the Signal's value changes.

## Change Detection with Signals vs. Traditional Methods
 - **Traditional Method:** Angular uses Zone.js to create zones around components, listening for events that trigger state changes. This can be less efficient due to broad checks on all components.
 - **With Signals:** Angular avoids using Zone.js, enabling more fine-grained and efficient change detection. Signals notify Angular specifically about changes, reducing unnecessary checks.