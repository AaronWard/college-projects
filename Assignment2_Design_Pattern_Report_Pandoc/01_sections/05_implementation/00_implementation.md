# Implementation


## Updating GUI Using The Abstract Factory Pattern

In order to update the graphical user interface within the program the abstract factory pattern is used. An abstract factory class called ```PanelFactory``` is made to act as an interface for the panels that will be used. This class has one method called get panel. A centre panel of type ```FactoryPanel``` is initialized and added to the JFrame. Initially, it is set to the ```HomePanel``` as it is the first page that the user will see when they start the program. 

```
		public void setCenterPanel(){
			panel = new HomePanel();
			centrePanel = panel.getPanel();
			mainPanel.add(centrePanel, BorderLayout.CENTER);
		}   
```

The navigation bar buttons and the login button have customer action listener classes which update the gui accordingly by disposing of the current centre panel, and creating a suitable panel object to replace and is then set to the JFrame. This serves as a much more efficient approach to updating the graphical user interface rather than using the ```dispose()``` method as it does not use as much memory. For example: The code below displays an action listener for the "Make A Cake" button.


```
		public class MakeACakeListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
                //Hide the current panel
				centrePanel.setVisible(false);

                //Create new panel object
				panel = new MakeACakePanel();

                //get the panel and set it as the current panel
				centrePanel = panel.getPanel();

                //Add to the view
				mainPanel.add(centrePanel, BorderLayout.CENTER);
			}
		}
```


## Login Using The Singleton Pattern

To implement a user login, The singleton pattern is essential to enforce the rule of one user to be logged in at any given time, as it only allows one instance of ```LoginSingleton``` to be created. Should a user enter their user ID and password, the information is then compared to the data stored within the database. If the user ID exist and the password matches to that of the one assigned with their ID, then the user is granted access to their account. 

![Login Successful](04_assets/05_implementation/LoginCap.PNG){width=80% height=55%}

When the user is granted access, a instance of that user login is created with then parameters of that user. 
```
            //Set the instance of user
            LoginSingleton.getLoginInstance(userID, userName);
```

The ```LoginSingleton``` class contains a constructor that sets the ID and username of that which is passed to the current instance. Additionally, the constructor is declared as static, therefore only allowing one instance of the ```LoginSingleton``` to be created.

```
        public static LoginSingleton getLoginInstance(String id, String name){
		    //Set the ID of the user	
		    LoginSingleton.id = id;
		    LoginSingleton.name = name;
			
	    	//Check if an instance has already been created
			if(loginInstance == null){
					loginInstance = new LoginSingleton();
			}
			return loginInstance;
	    }
```

Ideally, the login in page should be hidden succeeding a user login. In this case the login functionality is still available in order to display the affectiveness of the ```LoginSingleton```. The screen shot below exhibits the case in which a user tries to log into the system if a login instance has already been created.

![Refused Login](04_assets/05_implementation/LoginCap2.PNG){width=80% height=55%}

This is achieved by checking if there is a login ID already created. If the ID has a value then the error message is shown and the user is not allowed to log in. See the code below of the ```SubmitListener``` Action Listener:

```
	//Check for a value
	if(LoginSingleton.getId() != null){
		
		///display error message
		JOptionPane.showMessageDialog(null," You are already logged in as " +
		LoginSingleton.getName() +" with the ID: " + LoginSingleton.getId() +
		"\nSign out to log in as another user.", "", JOptionPane.ERROR_MESSAGE);	
	}
```

## Making A Cake

In order for a user to make a cake, the user is provided with a form in which they can choose multiple different attributes. The options for cake type, size, shape and toppings are provided in the form. The user must complete the form in its entirety to save their cake and save it to the database. 

![Confirm Cake](04_assets/05_implementation/makeACake.PNG){width=80% height=55%}

 Once the user confirms the cake they want to make, the database is then updated by inserting the new cake data.

```
		//Create connection to database
		Connection dbConnection = DriverManager.getConnection(url, user, password);
		//create a statement
		Statement myStatement = dbConnection.createStatement();
		
		//SQL statement for inserting data
		String sqlStatement =  "INSERT INTO cake"
				+ "(id, type, size, toppings, shape)"
				+ "VALUES ('" + LoginSingleton.getId() + "','" + 
													 type +"','"+
													 size + "','" + 
													 topping + "','" + 
													 shape + "')";
		//Execute the statement
		myStatement.executeUpdate(sqlStatement);
```

As seen in the code above, the SQL statement inserts the ID of the ```LoginSingleton``` instance (the user currently logged on), the cake type, size of the cake, the toppings on the cake and the shape of the cake.


## Viewing Cakes Using The Builder Pattern

After a user is logged into their account, the they are then allowed to view the cakes that they have previously made. To achieve this, a query is sent to the database and retrieves all the data from the cake table. After the SQL data is obtained, a new ```Cake``` object is created using the builder pattern and added to an array of cake objects.

```
		int i = 0;
		while (res.next()) 
		{
			//variables are set to compare with the user's input
			type = res.getString("type");
			shape = res.getString("shape");
			size = res.getString("size");
			toppings = res.getString("toppings");
			
			//Creat a new cake object using the
			//builder pattern taken from the SQL data.
			Cake cake = new Cake.Builder().type(type)
										  .shape(shape)
										  .size(size)
										  .toppings(toppings)
										  .build();
			
			//Add cake to the array
			cakesArray[i] = cake;

			i++;
			...
```

The cake names are also added to a ```JList``` to allow users to view the list of cakes previously made. Once a user clicks on a list item,the index of the list selected is is used to retrieve the corresponding index of the ```cakesArray```. The user interface is then updated by retrieving the types, shape, size and toppings of the cake object. As seen in the code below, the labels for displaying information are updated with the correct data for that specific cake.

```
	public void valueChanged(ListSelectionEvent e) {
		//Check the index of the list selected
		JList<String> list = (JList<String>) e.getSource();
		int listSelected = list.getSelectedIndex();
		
		//Set the labels to the attributes in the cake object.
		//taken from the Cakes array
		Cake thisCake = cakesArray[listSelected];
		pickedTypeLabel.setText("Type: " + thisCake.getType());
		pickedShapeLabel.setText("Shape: "  + thisCake.getShape());
		pickedToppingsLabel.setText("Toppings: " + thisCake.getToppings());
		pickedSizeLabel.setText("Size: " + thisCake.getSize());	

		...
```

![Viewing cakes made by a user](04_assets/05_implementation/myCake.PNG){width=80% height=55%}

## Viewing Registered Users With The Composite pattern

The composite pattern is used in the "View Users" page in order to aggregate collections of ```User``` objects into groups. In this case, counties are use as groups. During registration the user has the option of registering from Cork, Dublin and Galway. Although it is not realistic in a real world application to only have three counties, for this case only three are used to exhibit the use of the composite pattern. When viewing the users, the users are grouped by the county that they have registered in. See the screen capture below that displays the users that have been registered in Galway.
 ![View Galway Users](04_assets/05_implementation/viewGalway.PNG){width=80% height=55%}


As seen above, the user with the username "Daire" is selected, and the users information is displayed on the right panel.
This is achieved by querying the database for data by by county. The result set is traversed through and the data is extracted from each row. From this data, a new ```User``` object is created and stored in a User object array.
```
		ResultSet dublinRes = getDublin.executeQuery();
		int i = 0;
		while(dublinRes.next()){

			//Extract data
			userName = dublinRes.getString("name");
			userCounty = dublinRes.getString("county");
			id = dublinRes.getString("id");
			//Add to user array
			usersArrayDublin[i] = new User(userName, userCounty, id);
			i++;
		}
```
The users are also added to a ```UserGroup``` according to their county.
When the a user's name is clicked in the list, a ```UserListener``` class is invoked to display the user object's attributes. The list index which is clicked is used to create a new user from the array of user objects. 
```
	public class UserListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {

			JList<String> list = (JList<String>) e.getSource();
			int listSelected = list.getSelectedIndex();

			//Checks the current list is of the right county
			if(currentUsers.equals("Dublin")){

				//Create a new user Object of the User object in the list index
				User thisUser = usersArrayDublin[listSelected];

				//Update labels
				userNameLabel.setText("Name: " + thisUser.getUserName());
				userCountyLabel.setText("County: "  + thisUser.getCountyName());		
				userIDLabel.setText("User ID: " + thisUser.getUserID());

			...
	}	
```
