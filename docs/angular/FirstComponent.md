# Building a First Component in Angular : Header Componen

## Overview
- **Goal**: To build a header component as the first step in an Angular application.

## Existing Component
- **AppComponent**: The main component already present, consisting of multiple files working together (standard practice in Angular).

## Creating the Header Component
1. **File Structure**:
    - Create a new file named `header.component.ts` (following naming conventions):
        - Starts with the component’s purpose (header).
        - Includes `.component` to indicate it's a component.
        - Uses `.ts` for TypeScript code.

2. **Defining the Class**:
    - Export a class named `HeaderComponent`:
        - Class name should start with an identifier of the component's role (e.g., `Header`).
        - The class body can remain empty for now.

3. **Using the Component Decorator**:
    - Import `Component` from Angular's core package.
    - Use the `@Component` decorator to define the component:
        - Add a configuration object.
        - Set the **selector** to `app-header` (follows convention of two words separated by a dash to avoid clashing with built-in HTML elements).

4. **Template and Styles**:
    - Recommended to use an external HTML file for the template:
        - Create `header.component.html` for markup.
        - Link it in the TypeScript file using `templateUrl`.
    - For styling, you can define styles in an external CSS file (not covered in detail here).

5. **Standalone Component**:
    - Set the `standalone` property to `true` to mark it as a standalone component, which is easier to use and connect.

## Using the Header Component
1. **Adding to index.html**:
    - Initially, you could add `<app-header></app-header>` in `index.html`, but it won't render anything since Angular needs to be informed of the component.

2. **Bootstrap Application**:
    - Typically, `bootstrapApplication` is called with the main component (e.g., AppComponent).
    - Instead of bootstrapping the header component directly, use it within the main app component.

## Registering the Header Component
1. **Usage in AppComponent**:
    - Replace the placeholder header in the app component's template with `<app-header></app-header>`.
    - You will receive an error: "app-header is not a known element" because Angular doesn't automatically scan for components.

2. **Importing the Header Component**:
    - Import `HeaderComponent` into the `AppComponent` TypeScript file (without the file extension).
    - Add it to the `imports` array in the app component's configuration object to register it.

## Final Steps
- After saving and running the development server, the error should be resolved, and the header component should display correctly on the screen.

## Conclusion
- This process demonstrates how to create and register a new Angular component, adhering to Angular's structure and component-based architecture. You'll learn more about component interaction and data exchange as you progress.
