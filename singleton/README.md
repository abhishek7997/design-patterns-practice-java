## Singleton Design Pattern

It is a creational design pattern.
Affects how objects are created.

### Types

1. Eager initialized singleton
2. Lazy initialized singleton
3. Thread safe singleton (Synchronized getInstance method)
4. Thread safe singleton (Double-checked locking)
5. Bill Pugh singleton (Uses static inner class; JVM itself guarantees thread-safety)
6. static block initialization (eager loading)
7. Singletons using Enums

### Use cases
Singletons are useful, when only one instance of a class is required for the same functionality that is used at multiple places. E.g.,

1. Loggers
2. Resource Managers (Connection manager, Cached pool, etc.)
