![Workflow result](https://github.com/msomu/OkhttpIssues/workflows/Check/badge.svg)

## :scroll: Description

SquareOkhttpIssues is a sample app to show issues and comments at a single repository square/okhttp,
built with [Jetpack Compose](https://developer.android.com/jetpack/compose). The goal of the sample
is to showcase the current UI capabilities of Compose using MVVM pattern using Dagger Hilt!

## :camera_flash: Screenshots

<img src="/results/screenshot_1.png" width="260">
&emsp;<img src="/results/screenshot_2.png" width="260">

## :bulb: Features

This sample contains two screens: a list of issues, a detail page for issue with their comments.

## Android development

This Sample app attempts to use the latest cutting edge libraries and tools. As a summary:

* Entirely written in [Kotlin](https://kotlinlang.org/).
* UI completely written in [Jetpack Compose](https://developer.android.com/jetpack/compose)
* Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) throughout.
* Uses many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/), including: Room, Lifecycle, Navigation.
* Uses [Hilt](https://dagger.dev/hilt/) for dependency injection

## Development setup

First off, you require the latest [Android Studio Arctic Fox](https://developer.android.com/studio/preview) release to be able to build the app. This is because the project is written in [Jetpack Compose](https://developer.android.com/jetpack/compose)

### Main Issue List

Package [`com.msomu.squareissues.ui.screen`][1]

This screen [`HomeScreen.kt`][3] shows the list of issues and their status and a short 200
characters description.
[`NavGraph.kt`][2] configures the navigation routes and actions in the app.

[1]: app/src/main/java/com/msomu/squareissues/ui/screen

[2]: app/src/main/java/com/msomu/squareissues/ui/NavGraph.kt

[3]: app/src/main/java/com/msomu/squareissues/ui/screen/HomeScreen.kt

### Issue detail

Package [`com.msomu.squareissues.ui.screen`][4]

This screen [`IssueDetailScreen.kt`][5]  dives into the Issue Details and showing the comments on
the issues.

[4]: app/src/main/java/com/msomu/squareissues/ui/screen
[5]: app/src/main/java/com/msomu/squareissues/ui/screen/IssueDetailScreen.kt

### Data

The data in the sample is fetched from the `api.github.com` and stored in the Database for caching
purposes. The app uses the MVVM pattern. Where the data flows from the repository to the View Model.
The Viewmodel observes the changes with viewModelScope and the UI collects the flow as State.
The State is consumed on the UI, so as the data changes the state changes and the UI is recomposed.

### UI and Instrumentation testing

Run UI tests from Android Studio or with the `./gradlew connectedCheck` command.
* The Test for the UI at [`HomeScreen.kt`][6] is written at [`HomeScreenTest.kt`][7]
* The Test for the Database DAO is at [`IssueDao.kt`][7] is written at [`IssueDaoTest.kt`][8] and same
for the Comment

[6]: app/src/main/java/com/msomu/squareissues/ui/screen/HomeScreen.kt
[7]: app/src/androidTest/java/com/msomu/squareissues/ui/screen/HomeScreenTest.kt

### Unit testing

* A [`FakeIssueRepository.kt`][8] is created to mimic the work of [`DefaultIssueRepository.kt`][9]
* The Test for the ViewModel at [`HomeViewModel.kt`][10] is written at [`HomeViewModelTest.kt`][11]
* The Test for the DateUtil at [`DateUtil.kt`][12] is written at [`DateUtilKtTest.kt`][13]


[8]: app/src/test/java/com/msomu/squareissues/repository/FakeIssueRepository.kt
[9]: app/src/main/java/com/msomu/squareissues/repository/DefaultIssueRepository.kt
[10]: app/src/main/java/com/msomu/squareissues/ui/screen/HomeViewModel.kt
[11]: app/src/test/java/com/msomu/squareissues/ui/screen/HomeViewModelTest.kt
[12]: app/src/main/java/com/msomu/squareissues/util/DateUtil.kt
[13]: app/src/test/java/com/msomu/squareissues/util/DateUtilKtTest.kt
## License

```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```