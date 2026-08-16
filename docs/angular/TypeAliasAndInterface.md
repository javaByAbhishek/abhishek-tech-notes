# Study Notes on TypeScript Object Types in Angular

## 1. **Outsourcing Object Types**
- Complex object types can be defined separately to improve readability.
- TypeScript allows you to create a **type alias** for this purpose.

## 2. **Creating a Type Alias**
- Use the `type` keyword followed by a name (convention: start with an uppercase letter).
- Example:
```typescript
  type User = {
  id: string;
  avatar: string;
  name: string;
};
```
- Use the type alias (User) wherever the type is needed in your code.

## 3. **Using Interfaces**
   An alternative to type aliases is to create an interface.
   Defined with the interface keyword.
- Example:
```typescript
interface User {
  id: string;
  avatar: string;
  name: string;
};
```
## usages
```typescript
@Input({ required: true }) user!: User;
```
## Key Differences
- **Type Alias:**
Can define any type (object, union, intersection, etc.).
- **Interface:**
Specifically for defining object types.
More common in Angular projects.

