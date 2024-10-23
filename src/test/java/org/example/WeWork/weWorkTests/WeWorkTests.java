package org.example.WeWork.weWorkTests;

import org.example.WeWork.weWorkPages.APIMethods;
import org.example.WeWork.weWorkPages.LaunchScreen;
import org.example.WeWork.weWorkPages.MainLandingScreen;
import org.example.base.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashMap;

public class WeWorkTests extends AppiumConfiguration {

//    Test Case 1 : Verify Launch Screen Functionality
//    Objective: Ensure the launch screen displays the loader animation with the logo.
//      Steps:
//           1. Launch the app.
//           2. Observe if the loader animation is displayed.
//           3. Ensure the logo is displayed during the loader animation.
//    Expected Result: Loader animation and logo are displayed.

    @Test(priority = 0)
    public void VerifyLaunchScreenFunctionality() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
    }


//    Test Case 2: Verify App Handles Additional Features
//    Objective: Test any additional available features in the app.
//    Steps:
//            1. Explore all sections of the app.
//            2. Identify any hidden or additional features.
//    Expected Result: Additional features work as expected.


    @Test(priority = 1)
    public void VerifyAllSectionsLoaded() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.verifyAllSectionsDisplayed();
    }

//    Test Case 3 : Verify Location Fetch on Launch Screen
//    Objective: Ensure the app fetches and displays the user's location.
//      Steps:
//            1. Launch the app.
//            2. Wait for the loader to complete.
//            3. Check if the user’s location is fetched.
//            4. Verify the main and secondary addresses are displayed on the next screen.
//    Expected Result: User's location is fetched, and main/secondary addresses are shown.

    @Test(priority = 2)
    public void VerifyLocationFetchOnLaunchScreen() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        String locTxt = MainLandingScreen.verifyLocationInMainScreen();
        Assert.assertTrue(locTxt.contains("Mountain View, California"));
    }

//    Test Case 4: Verify Error View on No Search Results
//    Objective: Ensure the app displays an error view when no results are found.
//    Steps:
//            1. Navigate to the Main Landing Screen.
//            2. Perform a search with no matching results.
//    Expected Result: An error view is displayed.

    @Test(priority = 3)
    public void VerifyErrorViewOnNoSearchResults() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("Independence Day");
        HashMap<String, String> map = MainLandingScreen.searchAndFetchNoResults(true, true);
        Assert.assertEquals(map.get("now-playing"), "No results found.");
        Assert.assertEquals(map.get("top-rated"), "No results found.");
    }

//    Test Case 5: Verify Now Playing Movies Section
//    Objective: Ensure the “Now Playing Movies” section displays movies.
//      Steps:
//            1. Navigate to the Main Landing Screen.
//            2. Scroll to the “Now Playing Movies” section.
//            3. Ensure movies are displayed with correct details.
//    Expected Result: Movies are displayed correctly.

    @Test(priority = 4)
    public void SearchMoviesAndVerifyNowPlaying() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.scrollNowPlayingSection("Terrifier 3");
//        Change the above argument when now playing updates
    }


//    Test Case 6: Verify Top Rated Movies Section
//    Objective: Ensure the “Top Rated Movies” section displays movies.
//      Steps:
//            1. Navigate to the Main Landing Screen.
//            2. Scroll to the “Top Rated Movies” section.
//            3. Ensure movies are displayed with correct details.
//    Expected Result: Top-rated movies are displayed correctly.

    @Test(priority = 5)
    public void SearchMoviesAndVerifyTopRated() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.scrollIntoElementView("Your Name.");
    }


//    Test Case 7: Verify Search Functionality
//    Objective: Ensure the search bar can search movies by name.
//      Steps:
//            1. Navigate to the Main Landing Screen.
//            2. Enter a valid movie name in the search bar.
//            3. Observe the search results.
//            4. Enter an invalid movie name and observe the error.
//    Expected Result: Valid movie results are shown. Error is displayed for invalid names.

    @Test(priority = 6)
    public void SearchMoviesAndVerifyResults() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("Independence Day");
        HashMap<String, String> map = MainLandingScreen.searchAndFetchNoResults(true, true);
        Assert.assertEquals(map.get("now-playing"), "No results found.");
        Assert.assertEquals(map.get("top-rated"), "No results found.");
        MainLandingScreen.clearSearchField("Independence Day");

        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("Never Let Go");

        boolean isDisplayed = MainLandingScreen.scrollNowPlayingSection("Never Let Go");
        Assert.assertTrue(isDisplayed);
        MainLandingScreen.clearSearchField("Never Let Go");

        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("The Godfather");

        isDisplayed = MainLandingScreen.scrollIntoElementView("The Godfather Part II");
        Assert.assertTrue(isDisplayed);
    }


//    Test Case 8: Verify Input Validation for Search Bar
//    Objective: Ensure the search bar handles input validation.
//            Steps:
//            1. Enter various invalid inputs (e.g., special characters, excessively long strings).
//            2. Observe how the app handles these inputs.
//    Expected Result: The app should handle invalid inputs gracefully without crashing or misbehaving.

    @Test(priority = 7)
    public void VerifyInputValidationForSearch() throws InterruptedException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("#@^*$&^");
        HashMap<String, String> map = MainLandingScreen.searchAndFetchNoResults(true, true);
        Assert.assertEquals(map.get("now-playing"), "No results found.");
        Assert.assertEquals(map.get("top-rated"), "No results found.");
        MainLandingScreen.clearSearchField("#@^*$&^");

        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        HashMap<String, String> mapL = MainLandingScreen.searchAndFetchNoResults(true, true);
        Assert.assertEquals(mapL.get("now-playing"), "No results found.");
        Assert.assertEquals(mapL.get("top-rated"), "No results found.");
    }


//    Test Case 9: Verify App Behavior When Location Access is Denied
//    Objective: Ensure the app handles the case where the user denies location access.
//    Steps:
//            1. Launch the app.
//            2. Deny the location permission prompt.
//    Expected Result: The app should handle the denial gracefully, possibly by showing a default location or an error message.

    @Test(priority = 8)
    public void DeniedLocationAccess() throws InterruptedException {
        boolean isDeniedMSGDisplayed = MainLandingScreen.denyLocationInMainScreen();
        Assert.assertTrue(isDeniedMSGDisplayed);
    }


//    Test Case 10: Verify App Behavior When Offline
//    Objective: Ensure the app handles being offline correctly.
//            Steps:
//            1. Launch the app while the device is offline.
//            2. Check if cached content is displayed.
//            3. Perform a search or refresh.
//    Expected Result: Cached content should be displayed, and the app should notify the user about the offline status.


    @Test(priority = 9)
    public void VerifyAppBehaviourWhenOffline() throws InterruptedException, MalformedURLException {
        clickAllowPermission();
        LaunchScreen.isLoaderSymbolDisplayed();
        MainLandingScreen.scrollNowPlayingSection("Terrifier 3");
//        Change the above argument when now playing updates
        MainLandingScreen.scrollIntoElementView("Your Name.");

        goOfflineOnline();
        closeApp();

        launchApp();
        LaunchScreen.launchScreenWithoutInternet();
        LaunchScreen.clickLoadCachedData();
        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("Never Let Go");

        boolean isDisplayed = MainLandingScreen.scrollNowPlayingSection("Never Let Go");
        Assert.assertTrue(isDisplayed);
        MainLandingScreen.clearSearchField("Never Let Go");

        MainLandingScreen.clickSearchButton();
        MainLandingScreen.enterSearchText("The Godfather");

        isDisplayed = MainLandingScreen.scrollIntoElementView("The Godfather Part II");
        Assert.assertTrue(isDisplayed);

        goOfflineOnline();
    }


//    Test Case 11: Verify API Data and Images Loading on Initial Launch
//    Objective: Ensure response fetches the results.
//    Steps:
//            1. Trigger the GET calls.
//            2. Verify that the status code is successful.
//    Expected Result: Movie data and images load correctly on the response body.

    @Test(priority = 10)
    public void VerifyGETCalls() {
        APIMethods.GETConfig();
        APIMethods.GETNowPlaying();
        APIMethods.GETTopRated();
    }

//    Test Case 12: Verify Error Handling for API Failures
//    Objective: Ensure the app handles API failures gracefully.
//            Steps:
//            1. Simulate an API failure (e.g., by disconnecting the network or using invalid API credentials).
//            2. Try loading movies from both "Now Playing" and "Top Rated" sections.
//    Expected Result: The app should display an appropriate error message or screen.

    @Test(priority = 11)
    public void VerifyGETCallsError() {
        goOfflineOnline();  //disconnecting the network
        APIMethods.GETConfig();
        APIMethods.GETNowPlaying();
        APIMethods.GETTopRated();
        goOfflineOnline();  //going online
        APIMethods.GETConfig_withErrorToken();
        APIMethods.GETNowPlaying_withErrorPageCount();
        APIMethods.GETTopRated_withErrorLanguage(); // this must fail as wrong params passed (lang = sdjhfjkh)
    }
}
