# elevator

# Assumptions
This was a common elevator in a building that only travelled up and down.

The elevator would complete all requests in a single direction before changing directions. 

I was creating this as if I was working for an elevator company.  An assumption in that
is the company would likely like to sell modular elevators to meet the need of
various clients.  In example, they could request differing panels for buttons or 
desire the elevators to function differently in different buildings as products and customers
grew over time of the company.

# Features Implemented
* Interior button panel in elevator that could have 0 to _n_ number of buttons associated with floors to stop at
* 0 to _n_ number of floors in building
* Each floor has a panel that has one or two buttons that requests travel up or down
* Initial button press prioritized initial travel

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
* Respect the floor identifier in the Floor object instead of relying on the index of the array
* Break-down movement calculator in to smaller functions and/or possibly new classes
* Multi-threaded - initiating Controller should be on a new thread, with main thread allowing interrupts and user input
* Dependency injection - I modeled the code to allow injection. Adding Spring to this project would be a next step.
* Continue to create more test cases and coverage
* Discover elevator location on startup. This could be practically done by a data store.
* Better error handling
* Logging
* Possibly refactor to use reactive programming, if rewritten in new paradigm

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
