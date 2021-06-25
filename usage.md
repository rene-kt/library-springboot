# Getting started
First of all, if you're using the application locally:

just use ```localhost:8000/``` instead of ```https://renejr-ecommerce.herokuapp.com/```


### The first step 

You need to create an account, then, acess the endpoint as the image is showing, and do the request. 
![image](https://user-images.githubusercontent.com/49681380/123428543-4d188880-d59c-11eb-982a-74918d3667c1.png)
 
 > Check the body of the request to verify if anything gone wrong.
 
 ### The second step

After you sign up, now you can log in into the system, through the following endpoint 

![image](https://user-images.githubusercontent.com/49681380/123428791-923cba80-d59c-11eb-855f-b9457a1fd77e.png)

> again, check the status code to make sure that everything is alright 

### The third step 

This is the most important step. This system uses Spring Security, and this frameworks denies all endpoints that doesn't have the authorization token in their headers. 
Then, after your login, go and check the headers of the json, and search for Authorization, just like this: 

![image](https://user-images.githubusercontent.com/49681380/123429005-d203a200-d59c-11eb-999f-51f61875b89a.png)

Copy it, and saves it in some place. Because, if you don't have access to it, every endpoints will return a status: 403 DENIED.

### Final step 

Just go into any endpoint, and paste your token, like this: 

![image](https://user-images.githubusercontent.com/49681380/123429182-08d9b800-d59d-11eb-866e-63d65234b9d7.png)


If you have been doing everything right, you're gonna receive a ```HttpStatus.OK```, Otherwise, it'll return a ```HttpStatus.FORBIDENN``` just like this: 

![image](https://user-images.githubusercontent.com/49681380/123429399-4cccbd00-d59d-11eb-8887-69b2cf22e2bd.png)


**If any exception happens, the API will return to you exactly what's going on and how to fix it. For example:** 

![image](https://user-images.githubusercontent.com/49681380/123429721-a59c5580-d59d-11eb-8c19-76613d412ba0.png)


**Now, you're perfectly able to run this application with any doubt.** 
