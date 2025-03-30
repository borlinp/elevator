# elevator

# Further Features to Consider
Here are some features that I might add if I kept going
* Stopping elevator by stop button
* Returning elevator to 1st floor on fire alarm
* Call button
* Fireman's override switch
* Door opening/closing
* Restricted floors
* Express floors
* Emergency failures: lighting, emergency breaks, etc.

# Potential Improvements
Here are some potential improvements I might consider if I continued:
* Break-down movement calculator in to smaller functions and/or possbile new classes
* Multi-threaded - initiating Controller should be on a new thread, with main thread allowing interrupts and user input
* Dependency injection - I modeled the code to allow injection, adding Spring to this project
* Continue to create more test cases and coverage
* Discover elevator location on startup. This could be practically done by a data store.
* Better error handling
* Logging
* Possibly refactor to us reactive programming, if rewritten in new paradigm

# Programming Practices
Here are some programming practices I attempted to demonstrate in my code:
* Dependency injection
* Extensibility
* Reactive / Notifier
* Aspect oriented / Appropriate concerns by class
* Easy of use by consumer
* Testing
* Expressive
* Small methods