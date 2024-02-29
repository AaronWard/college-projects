# System Design

## Graphical User Interface

The design of the graphical user interface plays a vital role in the achieving the goals for this program. A simple and easy to use GUI should be implemented in order to help users to instinctively use the program without experience or confusion. To achieve this, many of the graphical views share the same basic structure. Additionally, Every view uses a consistant nagivation bar to provide easy navigation throughout the program.


### Home

The home view consists of a basic 3 panel structure that provides some information about the shop. On the left and bottom right hand, panels are used to give brief information about the shop. A section is also allocated for a picture slide show, which displays a series of cakes continously. The home view is unique in its design as it does not share a common structure to any other view.

![Home Panel](04_assets/04_system_design/home.jpg){width=80% height=80%}


###Login

The login page consists of very simple interface design, with two input fields for a user to enter their user ID and password. 

![Login Panel](04_assets/04_system_design/Login.jpg){width=70% height=55%}


###Register

The register page shares a similar structure to the login page, in terms of a simple input field section in the middle of the view for minimal visual intake. The input fields should take a users name, a chosen password and a county which they are from. 

![Register Panel](04_assets/04_system_design/Register.jpg){width=80% height=55%}

###Make A Cake

This panel possesses a slightly more complex design choice. Both sections for user interaction and information displayal are kept seperate to enforce simplicity. On the left side, a section is devoted to allowing users to decide the recipe for their cake. Dropdown bars are given to provide a means to the user choose their cake type, shape, toppings and size. During interaction with this section, the right section (for information) is updated accordingly. Once a user has finished making a cake, buttons are provided to allow a user to save their cake recipe. Or, should a user wish to restart, a button allows a the user reset the form and start again.

![Make A Cake Panel](04_assets/04_system_design/MakeACake.jpg){width=80% height=55%}


### My Cakes

This panel shares similarities with the 'Make A Cake' panel as it segregates user interaction from information. Although, this section is only viewable if a user is logged into the system. The left section displays a list of cakes a user has previously saved and displays them in order. When a user clicks on the cake name, the right panel is updated with the cake information and the recipe is displayed.

![My Cakes Panel](04_assets/04_system_design/MyCakes.jpg){width=80% height=55%}


### View Users

In addition to the 'My Cakes' and 'Make A Cake' panels, this section shares the same information and interaction seperation structure. The view consists of a left panel with three buttons, each used for updating the list of users groups by their county. In which case, if a user clicked on the ```Dublin``` button, a list of user's names is displayed. If a user is to click on one of them user's name, their information is displayed in the informational panel. 

![View Panel](04_assets/04_system_design/ViewUsers.jpg){width=80% height=55%}


## Design Patterns

Design patterns are desribed as reusable solution to a programming problem that is commonly encountered or a template of how to solve a specific problem (Bautista, 2017). Design patterns are categorized into three different groups: Creation, Structural and Behavioral. Creation patterns provide mechanisms for instantiation which, in turn, makes for easier object creation. Structural patterns dwell upon relationships between seperate enitites and allows work between those enities simpler and more efficient. Lastly, Behavioral patterns are used for the communication across entities.
Many design patterns have been considered during the process of planning and design for this project. Although, only four are utilised in the case of this program as they meet the requirements for what the system needs. 


### The Singleton Pattern

The singleton design pattern is a creational pattern that limits the creation objects as it only allows one instance of an object to be created at any given time. This can be achieved by creating a static variable (which can only be instantiated once) or to use exception handling. In the case of a user login in the system proposed, a singleton is essential as it will only allow one user to be logged into the system at any given time. 


### The Builder Pattern

The builder pattern is a creational pattern which aims to seperate the construction of complex objects from the representation. A builder class constructs several objects into one and returns a final product. The builder pattern also improves modularity of code and hides details. In this case, the builder pattern is suitable because cake objects made up of multiple other objects can be instantiated efficiently at runtime when needed.


### The Composite Pattern

The composite pattern is ideal for cases in which objects needed to be associated into groups. The composite pattern composes objects in a hierarchial tree order: The composite object is knows as a group, and an object within that group is known as a leaf. In regards to the program specifications, the composite pattern is applicable as we can have user objects to be displayed, and also organise those user objects into groups of counties accordingly, depending on the user data obtained from the database.


### The Abstract Factory Pattern

The abstract factory pattern is a creational design pattern that can return a factory object that can return several groups of classes. This is achieved by creating an interface for creating related objects without specifying the concrete class. This is also beneficial as it encapsulates data from the client. As the view of the graphical user interface needs to change, the abstract factory pattern can be used to update the view by returning a new panel object. 




