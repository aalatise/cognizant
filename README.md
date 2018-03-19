# cognizant
To test Restful Service, please download postman client. You would need a Goggle account and chrome.

Go to the Postman download page at http://www.getpostman.com/

From your Google Chrome browser and click Chrome App. 
You will be taken to the Chrome webstore page; click the + ADD TO CHROME button to install the Postman tool in your browser

Now a Google login page will appear to ask you to log in. Log in using your Google account. 
A confirmation dialog will be shown on your screen asking your permission to add the Postman extension to your browser; 
click the Add app button. 

Now open your Google Chrome browser and enter the URL chrome://apps/
web page will be loaded with all the available apps for your Chrome browser. 

Just click on the Postman icon to launch the Postman app. Before launching Postman just ensure our webstore project is running.

Now, in the Postman app, enter the target URL as illustrated below for various RESTfull functionality
set the request method accordingly and set request object (i.e JSON object ) press the Send button

SELECT	READ	http://localhost:8080/company/rest/emp/E1236

SELECT ALL	READ	http://localhost:8080/company/rest/emp/all

INSERT	CREATE	http://localhost:8080/company/rest/emp/add/E111

UPDATE	UPDATE	http://localhost:8080/company/rest/emp/update/E111/name=alfred&salary=100000

DELETE	DELETE	http://localhost:8080/company/rest/emp/delete/E111

