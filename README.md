# Transferwise Superheroes.
An Android Application written in Java to fetch names of Marvel superheroes from an API and display the data in the form of a list.

### Features
- On clicking an item in the list, the item expands with more information.     
- The API data is cached for offline use. If the app is used without Internet connection it will show previously downloaded content.  

### API Reference
JSON Data is fetched from : [Get superheroes](https://simplifiedcoding.net/demos/marvel)

### Libraries used
- Retrofit : Used for fetching the data. 
- Mockito: Used to test the Presenter layer. 

### Packages
Model  . 
Contains the serializable model class Hero.java . 

Adapter . 
Contains the HeroAdapter.java class . 
Inflates the view for each item in the recycler view. Glide is used to load the images from the API . 

Network . 
Contains the following classes:  
``ApiClient.java`` : Has the implementation of the Retrofit library and the Retrofit caching. The check for network connectivity is done in this class and data is updated to the cache.  
``ApiInterface`` : Has the GET request . 
ConnectionDetector.java : Function to check for network connectivity on the device . 

hero_list . 
Contains the following classes:  
``HeroListActivity.java`` :   
``initUI() `` : Initializes the RecyclerView and the progressDialog   
``setDataToRecyclerView()``: Populates the list .  
``heroListPresenter = new HeroListPresenter(getApplicationContext(), this);``: Initializes the Presenter . 
``HeroListContract`` Interface : Has the methods for the Model-View-Presenter . 
``HeroListModel.java`` : Uses Retrofit instance and fetches the data from the API in a background thread using `` call.enqueue`` . 
``HeroListPresenter.java`` : Requests the data from the server and populates the RecyclerView with a list of Heroes . 

#### Unit Test:  
``HeroListPresenterTest.java``: Uses Mockito to test the Presentation layer  

