# How Angular Renders Content on the Screen

### 1. index.html
- Contains the custom `<app-root>` element, which will be replaced by an Angular component.
- This is the “entry point” of the Angular app.

### 2. main.ts
- The Angular entry file executed when the website loads.
- Compiled into JavaScript by Angular CLI (via TypeScript).
- No script imports in index.html; Angular CLI injects them automatically.

### 3. Angular CLI
- Compiles TypeScript code into JavaScript.
- Injects necessary script files into the HTML for app execution.

### 4. bootstrapApplication Function
- A core Angular function that bootstraps the app and loads the main Angular component.

### 5. AppComponent
- The root Angular component defined in `app.component.ts`.
- Import and pass this component to `bootstrapApplication` in `main.ts`.

### 6. @Component Decorator
- A TypeScript feature used to define an Angular component.
- Attaches metadata to the class, converting it into a component.
- **Selector:** Defines the custom HTML tag (e.g., `<app-root>`) to be replaced in `index.html`.

### 7. Component Template
- Contains the HTML markup for the component (e.g., title, image).
- Stored in a separate file (e.g., `app.component.html`).
- The content of this template replaces the `<app-root>` tag in `index.html`.

### 8. Component Styles
- Styles scoped specifically to the component, preventing clashes with other components.
- Stored in an external file (e.g., `app.component.css`).

### 9. Execution Flow
1. `index.html` loads with the `<app-root>` element.
2. `main.ts` (via Angular CLI) compiles and injects necessary scripts.
3. The `bootstrapApplication` function initializes Angular and loads the root component.
4. The root component’s template is inserted where `<app-root>` was in `index.html`.
