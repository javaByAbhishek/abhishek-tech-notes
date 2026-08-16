# Angular Conditional Rendering Notes

## Using `@if` for Conditional Content
- **Purpose**: To render content based on certain conditions.
- **Syntax**: `@if(condition) { ... }`
    - Example: Check if `selectedUser` is defined.
```html
@if (selectedUser) {
<app-tasks [name]="selectedUser? selectedUser.name: ''" />
}
```
- If `selectedUserId` is undefined, `selectedUser` will also be undefined.
- **Implementation**:
    - Use curly braces to wrap the markup that should be displayed when the condition is true.
    - TypeScript understands that `selectedUser` will be defined inside the block.


## Adding Fallback Content
- **Using `@else`**:
    - Provides a fallback message when no user is selected.
    - Example:
      ```html
      @else {
        <p id="fallback">Select a user to see their tasks.</p>
      }
      ```
    - The fallback text will display initially and will change when a user is selected.

## Using `@for` for Lists
- **Purpose**: To iterate over lists and render items.
- **Syntax**: `@for(user of users) { ... }`
    - Example: Render a list of tasks for the selected user.
- **Implementation**: Similar to `@if`, you wrap the markup in curly braces.
```html
@for(user of users; track user.id){
<li>
    <app-user [user]="user" (select)="onSelectUser($event)" />
</li>
}
```

## Legacy Syntax for Conditional Rendering
- **Older Angular Versions**: Use `*ngIf` and `*ngFor`.

### `*ngFor` Syntax
>*ngFor="let user of users"
```html
<li *ngFor="let user of users">
    <app-user [user]="user" (select)="onSelectUser($event)" />
</li>
```
- This is a structural directive to repeat elements.
- Requires importing NgFor from @angular/common.

### `*ngIf` Syntax
- **Syntax**:
```html
  *ngIf="selectedUser"
```

- If TypeScript doesn't recognize selectedUser, an exclamation mark (!) may be necessary to assert its existence.

## Handling Else with ng-template
For displaying fallback content:
```html
<ng-template #fallback>
    <p>Select a user to see their tasks.</p>
</ng-template>

<app-tasks *ngIf="selectedUser; else fallback" [name]="selectedUser!.name" />

```
- Must import *ngIf from @angular/common for use.

## Summary
- The new @if, @else, and @for syntax were introduced in Angular 17 for conditional and iterative rendering.
- Legacy methods with *ngIf and *ngFor are still applicable for older versions.
- Both approaches allow for effective management of conditional content and list rendering in Angular applications.

